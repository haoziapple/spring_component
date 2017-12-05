#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;

/**
 * 日志工具类，维护logcontext用 <br>
 * 使用HTTPservletrequest的方法，都只应该在IOS使用。
 */
public abstract class LogUtils {
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);
    // JsonUtil 输出format过后的json，不适合日志功能
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            return String.valueOf(object);
        }
    }

    /**
     * 从request中还原logcontext对象，没有则不创建
     *
     * @param request
     * @return
     */
    public static LogContext buildLogContext(HttpServletRequest request) {
        String caller = request.getHeader(LogConstants.LOG_CONTEXT_CALLER);
        if (!StringUtils.isEmpty(caller)) {
            MDC.put(LogConstants.LOG_CONTEXT_CALLER, caller);
        } else {
            MDC.put(LogConstants.LOG_CONTEXT_CALLER, "");
        }
        return buildLogContext(request, false);
    }

    /**
     * 从request中还原logcontext对象，如没有则新建
     *
     * @param request
     * @param createIfNeed
     * @return
     */
    public static LogContext buildLogContext(HttpServletRequest request, boolean createIfNeed) {
        String header = request.getHeader(LogConstants.LOG_CONTEXT_NAME);
        if (header != null) {
            try {
                return objectMapper.readValue(header, LogContext.class);
            } catch (IOException e) {
                // goto header == null
            }
        }

        return createIfNeed ? createLogContext(request) : null;
    }

    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }

    private static String txid() {
        return Integer.toHexString(Holder.numberGenerator.nextInt());
    }

    /**
     * 该方法只应该在IOS前台工程使用。
     * brand从http头或ec.properties取，channel固定为IOS
     *
     * @return 创建新的logcontext对象
     */
    public static LogContext createLogContext(HttpServletRequest request) {
        return createLogContext(request, BrandDetector.detect(request), Channel.IOS.getValue());
    }

    /**
     * brand从http头或ec.properties取
     * channel做参数
     *
     * @param request
     * @param channel
     * @return
     */
    public static LogContext createLogContext(HttpServletRequest request, String channel) {
        return createLogContext(request, BrandDetector.detect(request), channel);
    }

    /**
     * brand，channel做参数
     *
     * @param request
     * @param brand
     * @param channel
     * @return
     */
    public static LogContext createLogContext(HttpServletRequest request, Brand brand, String channel) {
        LogContext logContext = new LogContext();
        logContext.setTxid(txid());
        logContext.setBrowser(BrowserDetector.detect(request));
        logContext.setOs(OSDetector.detect(request));
        logContext.setBrand(brand);
        logContext.setUserTermal(TerminalDetector.detect(request));
        // 这个方法只给ios用
        logContext.setChannel(channel);
        Cookie cookie = WebUtils.getCookie(request, "userCookieID");
        if (cookie != null) {
            logContext.setCookieId(cookie.getValue());
        }
        if (StringUtils.isEmpty(logContext.getCookieId()) && request.getAttribute("userCookieID") != null) {
            logContext.setCookieId(request.getAttribute("userCookieID").toString());
        }
        logContext.setIp(getClientIP(request));

        saveLogContext(logContext);
        if (request.getHeader(LogConstants.LOG_CONTEXT_CALLER) != null) {
            MDC.put(LogConstants.LOG_CONTEXT_CALLER, request.getHeader(LogConstants.LOG_CONTEXT_CALLER));
        } else {
            MDC.put(LogConstants.LOG_CONTEXT_CALLER, "");
        }
        logger.info("Create log context: {}", LogUtils.toString(logContext));
        return logContext;
    }

    /**
     * @return 目前使用的logcontext对象在日志中的string形式
     */
    public static String getLogContextString() {
        return MDC.get(LogConstants.LOG_CONTEXT_NAME);
    }

    /**
     * @return 日志中的简洁格式，只含txid
     */
    public static String getLogContextSimpleString() {
        return MDC.get(LogConstants.LOG_CONTEXT_SIMPLE_NAME);
    }

    /**
     * @return 目前使用的logcontext对象
     */
    public static LogContext getLogContext() {
        String json = MDC.get(LogConstants.LOG_CONTEXT_NAME);
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, LogContext.class);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 保存logcontext对象供使用
     *
     * @param logContext
     */
    public static void updateLogContext(LogContext logContext) {
        saveLogContext(logContext);
        logger.info("Update log context: {}", LogUtils.toString(logContext));
    }

    public static void saveLogContext(LogContext logContext) {
        String val = LogUtils.toString(logContext);
        MDC.put(LogConstants.LOG_CONTEXT_NAME, val);
        MDC.put(LogConstants.LOG_CONTEXT_SIMPLE_NAME, logContext == null ? null : logContext.toSimpleContext());
    }

    public static void saveLogContextString(String logContext, String simpleLogContext) {
        if (logContext != null) {
            MDC.put(LogConstants.LOG_CONTEXT_NAME, logContext);
        }
        if (simpleLogContext != null) {
            MDC.put(LogConstants.LOG_CONTEXT_SIMPLE_NAME, simpleLogContext);
        }
    }

    private static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static String getLocalIP() {
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            return "<Unknown>";
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface element = networkInterfaces.nextElement();
            try {
                if (!element.isLoopback() && !element.isVirtual()
                        && element.isUp()
                        && element.getInterfaceAddresses().size() > 0) {
                    return element.getInterfaceAddresses().get(0).getAddress()
                            .getHostAddress();
                }
            } catch (SocketException e) {
                logger.error("Failed to determine IP:", e);
            }
        }
        return "<Unknown>";
    }

    public static String genRequesterString() {
        return String.format("%s.%s", getLocalIP(),
                GlobalConfigStorage.getProjectName() == null ? "unknown" : GlobalConfigStorage.getProjectName());
    }

    public static void saveAccessInfo(String access) {
        MDC.put(LogConstants.LOG_CONTEXT_ACCESS, access);
    }
}
