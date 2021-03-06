package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticRepetRecordInfo;

public interface AquaticRepetRecordInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    int insert(AquaticRepetRecordInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    int insertSelective(AquaticRepetRecordInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    AquaticRepetRecordInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticRepetRecordInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_repet_record_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticRepetRecordInfo record);
}