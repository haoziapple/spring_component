package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.QrtzLocksKey;

public interface QrtzLocksMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_locks
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(QrtzLocksKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_locks
     *
     * @mbggenerated
     */
    int insert(QrtzLocksKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrtz_locks
     *
     * @mbggenerated
     */
    int insertSelective(QrtzLocksKey record);
}