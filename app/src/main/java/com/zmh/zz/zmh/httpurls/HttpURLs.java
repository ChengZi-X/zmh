package com.zmh.zz.zmh.httpurls;

/**
 * Created by Administrator
 */

public class HttpURLs {
    //public final static String BASE_URL = "http://www.hsydining-hall.com";
    public final static String IP = "http://192.168.1.105/";
    public static final String HOSTUSER = IP + "user/";
    public static final String LOGIN = HOSTUSER + "login.shtml";//登录
    public static final String GETCODE = HOSTUSER + "getCode.shtml";//注册-获得验证码
    public static final String VEFTCODE = HOSTUSER + "veFtCode.shtml";//注册-校验手机号,验证码
    public static final String REGISTER = HOSTUSER + "register.shtml";//注册-确定注册
    public static final String GETFCODE = HOSTUSER + "getFCode.shtml";//忘记密码-获取验证码
    public static final String FORGETCODE = HOSTUSER + "forgetCode.shtml";//忘记密码-验证手机号,验证码
    public static final String FORGET = HOSTUSER + "forget.shtml";//忘记密码-修改密码


}
