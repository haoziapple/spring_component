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
 * https://developer.mozilla.org/en-US/docs/Browser_detection_using_the_user_agent
 * </pre>
 */
public abstract class BrowserDetector {
    private static final String UNKNOWN = "<Unknown>";
    private static final String OPR = "opr";
    private static final String OPERA = "opera";
    private static final String SAFARI = "safari";
    private static final String CHROMIUM = "chromium";
    private static final String CHROME = "chrome";
    private static final String SEAMONKEY = "seamonkey";
    private static final String FIREFOX = "firefox";

    public static String detect(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isEmpty(userAgent)) {
            return UNKNOWN;
        }

        Map<String, String> tokens = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("${symbol_escape}${symbol_escape}w+/[${symbol_escape}${symbol_escape}d${symbol_escape}${symbol_escape}.]+");
        Matcher matcher = pattern.matcher(userAgent);
        while (matcher.find()) {
            tokens.put(matcher.group().split("/")[0].toLowerCase(), matcher.group());
        }

        // firefox
        if (tokens.containsKey(FIREFOX) && !tokens.containsKey(SEAMONKEY)) {
            return tokens.get(FIREFOX);
        }

        // seamonkey
        if (tokens.containsKey(SEAMONKEY)) {
            return tokens.get(SEAMONKEY);
        }

        // chrome
        if (tokens.containsKey(CHROME) && !tokens.containsKey(CHROMIUM)) {
            return tokens.get(CHROME);
        }

        // chromium
        if (tokens.containsKey(CHROMIUM)) {
            return tokens.get(CHROMIUM);
        }

        // safari
        if (tokens.containsKey(SAFARI) && !tokens.containsKey(CHROME) && !tokens.containsKey(CHROMIUM)) {
            return tokens.get(SAFARI);
        }

        // opera
        if (tokens.containsKey(OPERA)) {
            return tokens.get(OPERA);
        }
        if (tokens.containsKey(OPR)) {
            return tokens.get(OPR);
        }

        // ie
        matcher = Pattern.compile(";${symbol_escape}${symbol_escape}s*(MSIE [${symbol_escape}${symbol_escape}d${symbol_escape}${symbol_escape}.]+);").matcher(userAgent);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return UNKNOWN;
    }
}
