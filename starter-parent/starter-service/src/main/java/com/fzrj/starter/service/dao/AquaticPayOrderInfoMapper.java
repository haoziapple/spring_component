package com.fzrj.starter.service.dao;

import com.fzrj.starter.service.po.AquaticPayOrderInfo;

public interface AquaticPayOrderInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    int insert(AquaticPayOrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    int insertSelective(AquaticPayOrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    AquaticPayOrderInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticPayOrderInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_pay_order_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticPayOrderInfo record);
}