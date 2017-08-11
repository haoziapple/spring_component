package com.haozi.demo.springboot.bean.po;

public class QrtzSimpleTriggers extends QrtzSimpleTriggersKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.REPEAT_COUNT
     *
     * @mbggenerated
     */
    private Long repeatCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.REPEAT_INTERVAL
     *
     * @mbggenerated
     */
    private Long repeatInterval;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrtz_simple_triggers.TIMES_TRIGGERED
     *
     * @mbggenerated
     */
    private Long timesTriggered;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.REPEAT_COUNT
     *
     * @return the value of qrtz_simple_triggers.REPEAT_COUNT
     *
     * @mbggenerated
     */
    public Long getRepeatCount() {
        return repeatCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.REPEAT_COUNT
     *
     * @param repeatCount the value for qrtz_simple_triggers.REPEAT_COUNT
     *
     * @mbggenerated
     */
    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.REPEAT_INTERVAL
     *
     * @return the value of qrtz_simple_triggers.REPEAT_INTERVAL
     *
     * @mbggenerated
     */
    public Long getRepeatInterval() {
        return repeatInterval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.REPEAT_INTERVAL
     *
     * @param repeatInterval the value for qrtz_simple_triggers.REPEAT_INTERVAL
     *
     * @mbggenerated
     */
    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrtz_simple_triggers.TIMES_TRIGGERED
     *
     * @return the value of qrtz_simple_triggers.TIMES_TRIGGERED
     *
     * @mbggenerated
     */
    public Long getTimesTriggered() {
        return timesTriggered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrtz_simple_triggers.TIMES_TRIGGERED
     *
     * @param timesTriggered the value for qrtz_simple_triggers.TIMES_TRIGGERED
     *
     * @mbggenerated
     */
    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }
}