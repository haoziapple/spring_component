package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticUserBillInfo;

public interface AquaticUserBillInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    int insert(AquaticUserBillInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    int insertSelective(AquaticUserBillInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    AquaticUserBillInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticUserBillInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bill_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticUserBillInfo record);
}