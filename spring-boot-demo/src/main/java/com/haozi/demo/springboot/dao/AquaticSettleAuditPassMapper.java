package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticSettleAuditPass;

public interface AquaticSettleAuditPassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String settleKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    int insert(AquaticSettleAuditPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    int insertSelective(AquaticSettleAuditPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    AquaticSettleAuditPass selectByPrimaryKey(String settleKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticSettleAuditPass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_settle_audit_pass
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticSettleAuditPass record);
}