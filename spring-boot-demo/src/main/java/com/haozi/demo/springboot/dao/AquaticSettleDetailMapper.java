package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticSettleDetail;

public interface AquaticSettleDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    int insert(AquaticSettleDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    int insertSelective(AquaticSettleDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    AquaticSettleDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticSettleDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticSettleDetail record);
}