package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticUserBankcardInfo;

public interface AquaticUserBankcardInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    int insert(AquaticUserBankcardInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    int insertSelective(AquaticUserBankcardInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    AquaticUserBankcardInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticUserBankcardInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_user_bankcard_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticUserBankcardInfo record);
}