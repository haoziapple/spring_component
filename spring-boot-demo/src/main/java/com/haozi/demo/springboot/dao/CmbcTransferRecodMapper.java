package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.CmbcTransferRecod;

public interface CmbcTransferRecodMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer cmbcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    int insert(CmbcTransferRecod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    int insertSelective(CmbcTransferRecod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    CmbcTransferRecod selectByPrimaryKey(Integer cmbcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CmbcTransferRecod record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_transfer_recod
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CmbcTransferRecod record);
}