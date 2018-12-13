package com.johnson.toolkit.array;

import java.util.regex.Pattern;

/**
 * Created by jhg on 2018-09-18.
 */
public class ArrayUtils {


    private static final Pattern INT_PATTERN = Pattern.compile("^[+-]?\\d+$");
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^[+-]?\\d*\\.\\d+$");

    public ArrayUtils() {
    }

    /**
     * 将数组转为以逗号间隔的字符串
     * @param array
     * @return
     */
    public static String parseString(Object[] array){
        return parseString(array, ",");
    }

    /**
     *
     * @param array  转换的数组
     * @param delimiter  分割符号
     * @return
     */
    public static String parseString(Object[] array, String delimiter) {
        if(array == null) {
            return "";
        } else {
            int delimiterLength = delimiter.length();
            if(array.length == 0) {
                return "";
            } else if(array.length == 1) {
                return array[0] == null?"":array[0].toString();
            } else {
                int length = 0;

                for(int result = 0; result < array.length; ++result) {
                    if(array[result] != null) {
                        length += array[result].toString().length();
                    }

                    if(result < array.length - 1) {
                        length += delimiterLength;
                    }
                }

                StringBuilder var6 = new StringBuilder(length);

                for(int i = 0; i < array.length; ++i) {
                    if(array[i] != null) {
                        var6.append(array[i].toString());
                    }

                    if(i < array.length - 1) {
                        var6.append(delimiter);
                    }
                }

                return var6.toString();
            }
        }
    }




}
