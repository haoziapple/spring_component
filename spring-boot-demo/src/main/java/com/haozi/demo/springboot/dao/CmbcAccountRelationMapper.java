package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.CmbcAccountRelation;

public interface CmbcAccountRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer accountId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    int insert(CmbcAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    int insertSelective(CmbcAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    CmbcAccountRelation selectByPrimaryKey(Integer accountId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CmbcAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cmbc_account_relation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CmbcAccountRelation record);
}