package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzPausedTriggerGrpsKey;

public interface QrtzPausedTriggerGrpsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_paused_trigger_grps
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzPausedTriggerGrpsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_paused_trigger_grps
     *
     * @mbggenerated
     */
    int insert(QrtzPausedTriggerGrpsKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_paused_trigger_grps
     *
     * @mbggenerated
     */
    int insertSelective(QrtzPausedTriggerGrpsKey record);
}