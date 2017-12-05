#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * http://www.geekpedia.com/code47_Detect-operating-system-from-user-agent-string.html
 * </pre>
 */
public abstract class OSDetector {
    private static final String UNKNOWN = "<Unknown>";
    private static Map<String, Pattern> patterns = new HashMap<String, Pattern>();

    static {
        patterns.put("Win311", Pattern.compile("Win16"));
        patterns.put("Win95", Pattern.compile("(Windows 95)|(Win95)|(Windows_95)"));
        patterns.put("WinME", Pattern.compile("(Windows 98)|(Win 9x 4.90)|(Windows ME)"));
        patterns.put("Win98", Pattern.compile("(Windows 98)|(Win98)"));
        patterns.put("Win2000", Pattern.compile("(Windows NT 5.0)|(Windows 2000)"));
        patterns.put("WinXP", Pattern.compile("(Windows NT 5.1)|(Windows XP)"));
        patterns.put("WinServer2003", Pattern.compile("(Windows NT 5.2)"));
        patterns.put("WinVista", Pattern.compile("(Windows NT 6.0)"));
        patterns.put("Win7", Pattern.compile("(Windows NT 6.1)"));
        patterns.put("WinNT", Pattern.compile("(Windows NT 4.0)|(WinNT4.0)|(WinNT)|(Windows NT)"));
        patterns.put("OpenBSD", Pattern.compile("OpenBSD"));
        patterns.put("SunOS", Pattern.compile("SunOS"));
        patterns.put("Linux", Pattern.compile("(Linux)|(X11)"));
        patterns.put("MacOS", Pattern.compile("(Mac_PowerPC)|(Macintosh)"));
        patterns.put("QNX", Pattern.compile("QNX"));
        patterns.put("BeOS", Pattern.compile("BeOS"));
        patterns.put("OS2", Pattern.compile("OS/2"));
        patterns.put("SearchBo",
                Pattern.compile("(nuhk)|(Googlebot)|(Yammybot)|(Openbot)|(Slurp)|(MSNBot)|(Ask Jeeves/Teoma)|(ia_archiver)"));
    }

    public static String detect(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isEmpty(userAgent)) {
            return UNKNOWN;
        }

        Matcher matcher = Pattern.compile("(Android [${symbol_escape}${symbol_escape}d${symbol_escape}${symbol_escape}.]+);").matcher(userAgent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        matcher = Pattern.compile("((?:iPhone|iPad) OS [${symbol_escape}${symbol_escape}d_${symbol_escape}${symbol_escape}.]+)").matcher(userAgent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        for (Map.Entry<String, Pattern> entry : patterns.entrySet()) {
            if (entry.getValue().matcher(userAgent).find()) {
                return entry.getKey();
            }
        }
        return "<Unknown>";
    }
}
