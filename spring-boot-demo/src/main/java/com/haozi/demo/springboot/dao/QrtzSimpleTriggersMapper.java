package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzSimpleTriggers;
import com.haozi.demo.springboot.bean.po.QrtzSimpleTriggersKey;

public interface QrtzSimpleTriggersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzSimpleTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    int insert(QrtzSimpleTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    int insertSelective(QrtzSimpleTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    QrtzSimpleTriggers selectByPrimaryKey(QrtzSimpleTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QrtzSimpleTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_simple_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QrtzSimpleTriggers record);
}