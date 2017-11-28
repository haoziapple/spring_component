package com.fzrj.starter.service.sandbox;

/**
 * Created by Administrator on 2017/11/28.
 */
public enum Channel implements IEnum {
    IOS("IOS"), OS("OS"), MOS("MOS"), MWOS("MWOS"), TDD("TDD");


    private final String value;

    private Channel(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
