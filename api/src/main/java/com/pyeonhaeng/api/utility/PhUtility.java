package com.pyeonhaeng.api.utility;

public final class PhUtility {
    private PhUtility(){}


    public static String checkName(String name){
        name = name.replaceAll("[\'\"']", "");
        return name;
    }
}
