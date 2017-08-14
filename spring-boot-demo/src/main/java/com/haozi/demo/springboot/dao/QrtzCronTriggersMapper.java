package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzCronTriggers;
import com.haozi.demo.springboot.bean.po.QrtzCronTriggersKey;

public interface QrtzCronTriggersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzCronTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    int insert(QrtzCronTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    int insertSelective(QrtzCronTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    QrtzCronTriggers selectByPrimaryKey(QrtzCronTriggersKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QrtzCronTriggers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_cron_triggers
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(QrtzCronTriggers record);
}