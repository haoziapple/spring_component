package com.haozi.demo.springboot.bean.po;

public class QrtzSimpleTriggersKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.SCHED_NAME
     *
     * @mbggenerated
     */
    private String schedName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.TRIGGER_NAME
     *
     * @mbggenerated
     */
    private String triggerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.TRIGGER_GROUP
     *
     * @mbggenerated
     */
    private String triggerGroup;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.SCHED_NAME
     *
     * @return the value of qrtz_simple_triggers.SCHED_NAME
     *
     * @mbggenerated
     */
    public String getSchedName() {
        return schedName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.SCHED_NAME
     *
     * @param schedName the value for qrtz_simple_triggers.SCHED_NAME
     *
     * @mbggenerated
     */
    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.TRIGGER_NAME
     *
     * @return the value of qrtz_simple_triggers.TRIGGER_NAME
     *
     * @mbggenerated
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.TRIGGER_NAME
     *
     * @param triggerName the value for qrtz_simple_triggers.TRIGGER_NAME
     *
     * @mbggenerated
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.TRIGGER_GROUP
     *
     * @return the value of qrtz_simple_triggers.TRIGGER_GROUP
     *
     * @mbggenerated
     */
    public String getTriggerGroup() {
        return triggerGroup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.TRIGGER_GROUP
     *
     * @param triggerGroup the value for qrtz_simple_triggers.TRIGGER_GROUP
     *
     * @mbggenerated
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
}