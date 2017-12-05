#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * http://trac.webkit.org/wiki/DetectingWebKit
 * </pre>
 */
public abstract class TerminalDetector {
    public static String detect(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isEmpty(userAgent)) {
            return null;
        }

        Matcher matcher = Pattern.compile("(Android [${symbol_escape}${symbol_escape}d${symbol_escape}${symbol_escape}.]+);").matcher(userAgent);
        if (matcher.find()) {
            return "Android";
        }
        matcher = Pattern.compile("((?:iPhone|iPad) OS [${symbol_escape}${symbol_escape}d_${symbol_escape}${symbol_escape}.]+)").matcher(userAgent);
        if (matcher.find()) {
            return "iOS";
        }
        return "PC";
    }
}
