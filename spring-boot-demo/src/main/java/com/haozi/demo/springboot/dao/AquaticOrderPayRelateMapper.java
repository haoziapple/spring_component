package com.haozi.demo.springboot.dao;

import com.haozi.demo.springboot.bean.po.AquaticOrderPayRelate;

public interface AquaticOrderPayRelateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    int insert(AquaticOrderPayRelate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    int insertSelective(AquaticOrderPayRelate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    AquaticOrderPayRelate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AquaticOrderPayRelate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aquatic_order_pay_relate
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AquaticOrderPayRelate record);
}