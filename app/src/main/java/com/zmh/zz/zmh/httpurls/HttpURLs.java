package com.zmh.zz.zmh.httpurls;

public class HttpURLs {
    //内部测试域名
    //public static String IP = "http://";
    //正式域名
    public static final String IP = "http://a.unionpro.zhongmenghui.com/";
    public static final String HOSTUSER = IP + "user/";
    public static final String LOGIN = HOSTUSER + "login.shtml";//登录
    public static final String GETCODE = HOSTUSER + "getCode.shtml";//注册->获得验证码
    public static final String VEFTCODE = HOSTUSER + "veFtCode.shtml";//注册-校验手机号->校验验证码
    public static final String REGISTER = HOSTUSER + "register.shtml";//注册-确定注册
    public static final String GETFCODE = HOSTUSER + "getFCode.shtml";//忘记密码-修改手机号-修改邮箱->获取验证码
    public static final String FORGETCODE = HOSTUSER + "forgetCode.shtml";//忘记密码-校验手机号->校验验证码
    public static final String FORGET = HOSTUSER + "forget.shtml";//忘记密码-重置密码
    public static final String MODIFYPASSWORD = HOSTUSER + "modifyPassword.shtml";//修改密码
    public static final String MODIFYMOBILECODE = HOSTUSER + "modifyMobileCode.shtml";//修改手机号->校验验证码
    public static final String MODIFYNEWMOBILE = HOSTUSER + "modifyNewMobile.shtml";//修改绑定手机号
    public static final String MODIFYEMAILCODE = HOSTUSER + "modifyEmailCode.shtml";//修改邮箱->校验验证码
    public static final String MODIFYEMAIL = HOSTUSER + "modifyEmail.shtml";//修改绑定邮箱
    public static final String USERIDENTITY = HOSTUSER + "userIdentity.shtml ";//实名认证
    public static final String UPDATEBASE64 = HOSTUSER + "updateBase64.shtml ";//意见反馈
}