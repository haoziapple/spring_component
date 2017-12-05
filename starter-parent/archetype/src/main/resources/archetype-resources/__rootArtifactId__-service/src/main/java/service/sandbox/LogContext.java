#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.sandbox;

import java.io.Serializable;
import java.util.UUID;

/**
 * 日志上下文对象
 */
public class LogContext implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2215289181522261273L;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 品牌
     */
    private Brand brand;
    /**
     * transaction id
     */
    private String txid;
    /**
     * dt id
     */
    private String dtid;
    /**
     * 用户终端类型
     */
    private String userTermal;
    /**
     * 手机型号
     */
    private String mobile;
    /**
     * 操作系统型号
     */
    private String os;
    /**
     * 浏览器型号
     */
    private String browser;
    /**
     * app 版本
     */
    private String app;
    /**
     * ip
     */
    private String ip;
    /**
     * customer id
     */
    private String cuid;
    /**
     * cookie id, or device id;
     */
    private String cookieId;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getDtid() {
        return dtid;
    }

    public void setDtid(String dtid) {
        this.dtid = dtid;
    }

    public String getUserTermal() {
        return userTermal;
    }

    public void setUserTermal(String userTermal) {
        this.userTermal = userTermal;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String createTxid() {
        this.txid = UUID.randomUUID().toString();
        return this.txid;
    }

    public String toSimpleContext() {
        return "{${symbol_escape}"txid${symbol_escape}":${symbol_escape}"" + this.txid + "${symbol_escape}"}";
    }
}
