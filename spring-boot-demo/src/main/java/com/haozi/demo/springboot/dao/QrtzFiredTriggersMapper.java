package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzFiredTriggers;
import com.haozi.demo.springboot.bean.po.QrtzFiredTriggersKey;

public interface QrtzFiredTriggersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzFiredTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    int insert(QrtzFiredTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    int insertSelective(QrtzFiredTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    QrtzFiredTriggers selectByPrimaryKey(QrtzFiredTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QrtzFiredTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_fired_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QrtzFiredTriggers record);
}