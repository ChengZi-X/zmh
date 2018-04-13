package com.zmh.zz.zmh.modeljson;

/**
 * Created by Sun-PC on 2018/4/9  0009.
 */

public class UserJson {

    /**
     * code : 200
     * data : {"accountNum":"6000040","bankStatus":4,"email":"","id":"60cb6cc9712f4d1e997657843e6cf9ed","lastLoginTime":1523259429778,"level":"1","loginPwd":"e45912deabd6550959784edc9d298df6","mobile":"18337152301","point":0,"registerChannel":"2","registerTime":1523259424031,"status":"1","verifiedStatus":4}
     */

    private int code;
    private String desc;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * accountNum : 6000040
         * bankStatus : 4
         * email :
         * id : 60cb6cc9712f4d1e997657843e6cf9ed
         * lastLoginTime : 1523259429778
         * level : 1
         * loginPwd : e45912deabd6550959784edc9d298df6
         * mobile : 18337152301
         * point : 0
         * registerChannel : 2
         * registerTime : 1523259424031
         * status : 1
         * verifiedStatus : 4
         */
        private String name;
        private String accountNum;
        private int bankStatus;
        private String email;
        private String id;
        private long lastLoginTime;
        private String level;
        private String loginPwd;
        private String mobile;
        private int point;
        private String registerChannel;
        private long registerTime;
        private String status;
        private int verifiedStatus;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getAccountNum() {
            return accountNum;
        }

        public void setAccountNum(String accountNum) {
            this.accountNum = accountNum;
        }

        public int getBankStatus() {
            return bankStatus;
        }

        public void setBankStatus(int bankStatus) {
            this.bankStatus = bankStatus;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLoginPwd() {
            return loginPwd;
        }

        public void setLoginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public String getRegisterChannel() {
            return registerChannel;
        }

        public void setRegisterChannel(String registerChannel) {
            this.registerChannel = registerChannel;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getVerifiedStatus() {
            return verifiedStatus;
        }

        public void setVerifiedStatus(int verifiedStatus) {
            this.verifiedStatus = verifiedStatus;
        }
    }
}
