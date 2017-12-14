package com.fzrj.starter.intf.bean.common;

public abstract class Constants
{
	public static final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";

	public static final String DEFAULT_PASSWORD = "123456";

	public static final String ONE = "1";

	public static final String ZERO = "0";

	// 状态常量
	public static abstract class Status
	{
		public static final String[] KEYS = { "0", "1" };

		public static final String[] VALUES = { "无效", "有效" };
		// 无效标识
		public static final String INVALID_KEY = KEYS[0];
		public static final String INVALID_VALUE = KEYS[0];
		// 有效标识
		public static final String VALID_KEY = KEYS[1];
		public static final String VALID_VALUE = KEYS[1];
	}
}
