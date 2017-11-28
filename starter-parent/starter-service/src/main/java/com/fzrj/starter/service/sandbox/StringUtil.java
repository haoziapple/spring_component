package com.fzrj.starter.service.sandbox;

import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2017/11/28.
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return null == str || str.trim().equals("");
    }

    public static final String trim(String str) {
        if (isEmpty(str)) return "";
        return str.trim();
    }

    public static final String trim(String str, String defaultVal) {
        if (isEmpty(str)) return defaultVal;
        return str.trim();
    }

    /**
     * 根据字符串值得到是否为真值
     *
     * @param str 　转入的字符串
     * @return　　boolean
     */
    public static boolean toBoolean(String str) {
        boolean t = false;
        if (str.toLowerCase().equals("true") || str.equals("1") || str.toLowerCase().equals("yes")
                || str.toLowerCase().endsWith("y")) {
            t = true;
        }
        return t;
    }

    /**
     * 将字符串转换为整形
     *
     * @param str
     * @param i
     * @return
     */
    public static int toInt(String str, int i) {
        int result = 0;
        try {
            result = java.lang.Integer.parseInt(str);
        } catch (Exception ex) {
            result = i;
        }
        return result;
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static String toString(Integer cnt) {
        if (null == cnt) {
            return "";
        }
        return String.valueOf(cnt);
    }

    public static String toString(@SuppressWarnings("rawtypes")
                                          List list) {
        StringBuffer buffer = new StringBuffer();
        for (Object object : list) {
            buffer.append(object.toString());
            buffer.append(";");
        }
        return buffer.toString();
    }

    public static long hashCode(CharSequence cs) {
        long h = 0xBB40E64DA205B064L;
        long hmult = 7664345821815920749L;

        long[] byteTable = new long[256];
        long h2 = 0x544B2FBACAAF1684L;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 31; j++) {
                h2 = (h2 >>> 7) ^ h2;
                h2 = (h2 << 11) ^ h2;
                h2 = (h2 >>> 10) ^ h2;
            }
            byteTable[i] = h2;
        }

        final int len = cs.length();
        for (int i = 0; i < len; i++) {
            char ch = cs.charAt(i);
            h = (h * hmult) ^ byteTable[ch & 0xff];
            h = (h * hmult) ^ byteTable[(ch >>> 8) & 0xff];
        }
        return h;
    }

    /**
     * 将一个字串转换成规定长度的字串，不足的部分根据参数决定是否以空格补齐 此方法从OS上直接拷过来的
     *
     * @param str，原字串
     * @return 如果原字串为null则返回null，否则返回去除多余空格后的字串
     */
    public static final String toSizeString(String str, int size, boolean fixedLength) {
        try {
            str = str.trim();
            int length = str.getBytes("gbk").length;
            if (length > size) {
                String lastOne = "";
                StringBuffer buffer = new StringBuffer();
                for (String s : str.split("")) {
                    buffer.append(s);
                    if (buffer.toString().getBytes("gbk").length > size) {
                        return lastOne;
                    }
                    lastOne = buffer.toString();
                }
                return str;
            } else {
                if (fixedLength) {
                    StringBuffer sb = new StringBuffer(str);
                    for (int i = 0; i < size - length; i++) {
                        sb.append(' ');
                    }
                    return new String(sb.toString().getBytes(), "utf-8");
                }
                return new String(str.getBytes(), "utf-8");
            }
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 将一个字串转换成规定长度的字串，不足以空格补齐
     *
     * @param str，原字串
     * @return 如果原字串为null则返回null，否则返回去除多余空格后的字串
     */
    public static final String toSizeString(String str, int size) {
        StringBuffer sb = new StringBuffer("");
        if (str == null) {
            str = "";
        }
        sb.append(trim(str));
        // int len = sb==null ? 0 : sb.toString().getBytes().length;
        int len = sb.toString().getBytes().length; // added by Fan Yuansheng
        if (len > size) {
            byte[] bs = sb.toString().getBytes();
            return new String(bs, 0, size);
            // sb.delete(size,len);
        } else {
            for (int i = 0; i < size - len; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public static final String toSizeString(Integer obj, int size) {
        StringBuffer sb = new StringBuffer("");
        if (obj != null) {
            sb.append("" + obj.intValue());
        }
        int len = sb.length();
        if (len >= size) {
            sb.delete(size, len);
        } else {
            for (int i = 0; i < size - len; i++) {
                sb.insert(0, "0");
            }
        }
        return sb.toString();
    }

    public static final String toSizeString(Double obj, int size) {
        if (obj == null) return "";
        DecimalFormat df1 = new DecimalFormat("000000.00");
        return df1.format(obj);
    }

    /**
     * 将List转换为字符串
     *
     * @param list
     * @param split 分隔符
     * @return
     */
    public static final String listToString(List<String> list, String split) {
        StringBuffer sb = new StringBuffer("");
        if (null == list || list.size() == 0) {
            return "";
        }
        for (String str : list) {
            String st = trim(str);
            sb.append(st).append(split);
        }
        String returnStr = sb.toString();
        returnStr = returnStr.substring(0, returnStr.lastIndexOf(split));
        return trim(returnStr);
    }

    /**
     * 将字符串放入List中，并去重
     *
     * @param name
     * @return
     */
    public static final List<String> addToList(List<String> roundNameList, String name) {

        if (!roundNameList.contains(name)) {
            roundNameList.add(name);
        }
        return roundNameList;
    }

    public static <T> String join(String sep, List<T> list) {
        StringBuffer buffer = new StringBuffer();
        if (list.size() == 0) {
            return "";
        }
        for (T t : list) {
            buffer.append(t.toString()).append(sep);
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    /**
     * 获取数组中第一个元素
     *
     * @param strs
     * @return
     */
    public static String getTopOneFromStrings(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        return strs[0];
    }

    public static final String dealSUSString(String str) {// @I5A
        if (isEmpty(str))
            return "";
        StringBuffer sb = new StringBuffer("");
        String tempStr = str.trim();
        for (int i = 0; i < tempStr.length(); i++) {
            char c = tempStr.charAt(i);
            if (("" + c).getBytes().length > ("" + c).length()) {
                byte[] bs = ("" + c).getBytes();
                byte[] b1 = ("|").getBytes(); //124
                if (bs[0] == b1[0] || bs[1] == b1[0]) {
                    sb.append("??");
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String[] split(String source, String delim) {
        int i = 0;
        int l = 0;
        if (source == null || delim == null) {
            return new String[0];
        }
        if (source.equals("") || delim.equals("")) {
            return new String[0];
        }

        StringTokenizer st = new StringTokenizer(source, delim);
        l = st.countTokens();
        String[] s = new String[l];
        while (st.hasMoreTokens()) {
            s[i++] = st.nextToken().trim();
        }
        return s;
    }

    /**
     * UTF-8转码为GBK
     *
     * @param msg
     * @return
     */
    public static String utfToGbk(String msg) {
        if (isEmpty(msg)) {
            return "";
        }
        String temp = "";
        try {
            //先转换为UNICODE字符串
            String unicodeStr = new String(msg.getBytes(), "utf-8");
            //转换为GBK编码数据
            byte[] gbk = unicodeStr.getBytes("GBK");
            return new String(gbk, "GBK");
        } catch (Exception e) {
        }
        return temp;
    }

    /**
     * GBK转为utf8
     *
     * @param msg GBK编码字符串
     * @return
     */
    public static String GbkToUtf8(String msg) {
        try {
            //转换为GBK编码数据
            byte[] gbk = msg.getBytes("utf-8");
            return new String(gbk, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 处理首字母特殊字符
     *
     * @param str    处理字符
     * @param symbol 符号
     * @author zhao_shengteng
     */
    public static String trimInitialsSpecialSymbol(String str, String symbol) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(symbol)) {
            return str;
        }
        int indexOf = str.indexOf(symbol);
        //if(NumberConstant.IntDef.INT_0 == indexOf){
        if (0 == indexOf) {
            str = str.substring(1);
        }
        return str;
    }
}
