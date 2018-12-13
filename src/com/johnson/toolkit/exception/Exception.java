package com.johnson.toolkit.exception;

/**
 * Created by jhg on 2018-09-18.
 */
public class Exception {


    /**
     * 获取异常信息
     * @param e
     * @return
     */
    public static String getErrorMessage(Throwable e) {
        if (e == null)
            return "Unknown error";
        while (e.getCause() != null) {
            e = e.getCause();
        }
        if (e.getMessage() != null)
            return e.getMessage();
        else
            return e.getClass().getName();
    }



}
