package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticSettleFailOrder;

public interface AquaticSettleFailOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    int insert(AquaticSettleFailOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    int insertSelective(AquaticSettleFailOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    AquaticSettleFailOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticSettleFailOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_fail_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticSettleFailOrder record);
}