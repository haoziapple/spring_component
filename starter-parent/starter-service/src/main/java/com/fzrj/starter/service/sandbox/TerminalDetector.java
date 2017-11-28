package com.fzrj.starter.service.sandbox;

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

        Matcher matcher = Pattern.compile("(Android [\\d\\.]+);").matcher(userAgent);
        if (matcher.find()) {
            return "Android";
        }
        matcher = Pattern.compile("((?:iPhone|iPad) OS [\\d_\\.]+)").matcher(userAgent);
        if (matcher.find()) {
            return "iOS";
        }
        return "PC";
    }
}
