package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticAllPriceInfo;

public interface AquaticAllPriceInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    int insert(AquaticAllPriceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    int insertSelective(AquaticAllPriceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    AquaticAllPriceInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticAllPriceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_all_price_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticAllPriceInfo record);
}