package com.fzrj.starter.service.sandbox;

/**
 * Created by Administrator on 2017/11/28.
 */
public enum Brand implements IEnum {
    NONE("none"), KFC("KFC"), PHHS("PHHS"), ED("ED"), KFC_PRE("KFC_PRE"), EGIFT("EGIFT"), KFC_PRIVILEGE("KFC_PRIVILEGE"), PHDI_MALL("PHDI_MALL"), KFC_PRE_BRAND("KFC_PRE_BRAND"), KFC_BRAND("KFC_BRAND"), PHDI_PRE("PHDI_PRE"), PHDI_DELIVERY("PHDI_DELIVERY"), KFC_BAK("KFC_BAK"), PHHS_BAK("PHHS_BAK"), ED_BAK("ED_BAK"), KFC_PRE_BAK("KFC_PRE_BAK"), KFC_BRAND_BAK("KFC_BRAND_BAK"), TB_PRE("TB_PRE"), TB_PRE_BAK("TB_PRE_BAK"), PHHS_ED("PHHS_ED");

    private final String value;

    private Brand(String value) {
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

    public static Brand fromString(String brand) {
        brand = brand.toUpperCase();
        for (Brand br : Brand.values()) {
            if (br.getValue().equals(brand)) {
                return br;
            }
        }
        throw new IllegalArgumentException(String.format("Illegal brand %s",
                brand));
    }
}
