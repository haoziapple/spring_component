package com.fzrj.starter.service.sandbox;

import javax.servlet.http.HttpServletRequest;

/**
 * 这个类服务端使用客户端的品牌,而前台使用自身品牌
 */
public abstract class BrandDetector {

    public static Brand detect(HttpServletRequest request) {
        try {
            String brandString = request.getHeader("BRAND");
            if (brandString == null) {
                return GlobalConfigStorage.getBrand();
            }
            return Brand.fromString(brandString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
