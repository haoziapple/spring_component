package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.PosSettleInfo;

public interface PosSettleInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String posSettleKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    int insert(PosSettleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    int insertSelective(PosSettleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    PosSettleInfo selectByPrimaryKey(String posSettleKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PosSettleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pos_settle_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PosSettleInfo record);
}