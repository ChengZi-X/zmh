package com.zmh.zz.zmh.modeljson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Serializable;
import java.util.List;

/**
 * File name : JsonResult.java Author : yinsq Date : 2016年8月30日下午3:15:08 Desc :
 * TODO Update History :
 * <ul>
 * <li></li>
 * <li></li>
 * </ul>
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String desc;
    private List<T> datas;
    private T data;

    private OutputPage outputPage;

    public JsonResult() {
        super();
        this.code = 200;
    }

    public JsonResult(int code) {
        super();
        this.code = code;
    }

    public JsonResult(int code, String desc) {
        super();
        this.code = code;
        this.desc = desc;
    }

    @SuppressWarnings("unchecked")
    public JsonResult(String jsonStr, Class<T> clazz) {
        JsonResult<JSONObject> jsonResult = JSONObject.parseObject(jsonStr, JsonResult.class);

        this.code = jsonResult.getCode();
        this.desc = jsonResult.getDesc();

        this.outputPage = jsonResult.getOutputPage();
        if (jsonResult.getData() != null)
            this.data = JSONObject.parseObject(jsonResult.getData().toJSONString(), clazz);
        if (jsonResult.getDatas() != null)
            this.datas = JSONObject.parseArray(JSONObject.toJSONString(jsonResult.getDatas()), clazz);
    }

    @Override
    public String toString() {
        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
        return JSON.toJSONString(this, feature);
    }

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

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public OutputPage getOutputPage() {
        return outputPage;
    }

    public void setOutputPage(OutputPage outputPage) {
        this.outputPage = outputPage;
    }

    public static class OutputPage implements Serializable {

        /**
         * 字段或域定义：<code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 1L;

        private int showCount /* = 15 */; // 每页显示记录数
        private int totalPage; // 总页数
        private int totalResult; // 总记录数
        private int currentPage; // 当前页
        private int currentResult; // 当前记录起始索引

        public OutputPage() {

        }


        public OutputPage(int showCount) {
            this.showCount = showCount;
        }

        public int getTotalPage() {
            if (totalResult % showCount == 0)
                totalPage = totalResult / showCount;
            else
                totalPage = totalResult / showCount + 1;
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalResult() {
            return totalResult;
        }

        public void setTotalResult(int totalResult) {
            this.totalResult = totalResult;
        }

        public int getCurrentPage() {
            if (currentPage <= 0)
                currentPage = 1;
            if (currentPage > getTotalPage())
                currentPage = getTotalPage();
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {

            this.showCount = showCount;
        }

        public int getCurrentResult() {
            currentResult = (getCurrentPage() - 1) * getShowCount();
            if (currentResult < 0)
                currentResult = 0;
            return currentResult;
        }

        public void setCurrentResult(int currentResult) {
            this.currentResult = currentResult;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

}
