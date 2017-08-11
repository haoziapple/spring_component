package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzCalendars;
import com.haozi.demo.springboot.bean.po.QrtzCalendarsKey;

public interface QrtzCalendarsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzCalendarsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    int insert(QrtzCalendars record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    int insertSelective(QrtzCalendars record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    QrtzCalendars selectByPrimaryKey(QrtzCalendarsKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(QrtzCalendars record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_calendars
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(QrtzCalendars record);
}