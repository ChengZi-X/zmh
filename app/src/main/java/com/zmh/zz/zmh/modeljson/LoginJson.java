package com.zmh.zz.zmh.modeljson;

import java.io.Serializable;

public class LoginJson implements Serializable {

    /**
     * code : 200
     * desc : QJ5S7GMcs9LSTLta5D60cE6BDqWGjQ2O
     * datas : null
     * data : {"id":"36f29df568154fd69cc16c212797a9b9","point":0,"level":"1","status":"1","email":"123@qq.com","loginPwd":"9cbf8a4dcb8e30682b927f352d6559a0","registerTime":1509354908290,"lastLoginTime":1509181325850,"mobile":"18337152300"}
     * outputPage : null
     */

    private int code;
    private String desc;
    private Object datas;
    private DataBean data;
    private Object outputPage;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getOutputPage() {
        return outputPage;
    }

    public void setOutputPage(Object outputPage) {
        this.outputPage = outputPage;
    }

    public static class DataBean {
        /**
         * id : 36f29df568154fd69cc16c212797a9b9
         * point : 0
         * level : 1
         * status : 1
         * email : 123@qq.com
         * loginPwd : 9cbf8a4dcb8e30682b927f352d6559a0
         * registerTime : 1509354908290
         * lastLoginTime : 1509181325850
         * mobile : 18337152300
         */

        private String id;
        private int point;
        private String level;
        private String status;
        private String email;
        private String loginPwd;
        private long registerTime;
        private long lastLoginTime;
        private String mobile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLoginPwd() {
            return loginPwd;
        }

        public void setLoginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
