package com.shuxiajian.frame.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求返回结果集
 *
 * @author pengzx
 * @create 2018-07-27 21:28
 */

public class ResultInfo {

    private boolean successFlag;    //是否成功

    private String msg; //结果消息

    private Object resultObject;    //结果对象

    public boolean isSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultInfo() {
    }

    public ResultInfo(boolean successFlag, String msg) {
        this.successFlag = successFlag;
        this.msg = msg;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",this.msg);
        jsonObject.put("successFlag",this.successFlag);
        return jsonObject;
    }

    public String toJSONtString(){
        return this.toJSONObject().toString();
    }

    public void setSuccess(String msg){
        this.successFlag = true;
        this.msg = msg;
    }

    public void setError(String msg){
        this.successFlag = false;
        this.msg = msg;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }
}
