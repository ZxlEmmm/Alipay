package com.aistar.util;

public class SeverResponse {
    private int status;
    private String Msg;
    private Object data;


    public static SeverResponse addSuccess() {
        return new SeverResponse(1, MsgUtil.ADD_SUCCESS, MsgUtil.ADD_SUCCESS);
    }

    public static SeverResponse addFail() {
        return new SeverResponse(0, MsgUtil.ADD_Fail, MsgUtil.ADD_Fail);
    }

    public static SeverResponse updateSuccess() {
        return new SeverResponse(1, MsgUtil.UPDATE_SUCCESS, MsgUtil.UPDATE_SUCCESS);
    }

    public static SeverResponse updateFail() {
        return new SeverResponse(0, MsgUtil.UPDATE_Fail, MsgUtil.UPDATE_Fail);
    }

    public static SeverResponse deleteSuccess() {
        return new SeverResponse(1, MsgUtil.DELETE_SUCCESS, MsgUtil.DELETE_SUCCESS);
    }

    public static SeverResponse deleteFail() {
        return new SeverResponse(0, MsgUtil.DELETE_Fail, MsgUtil.DELETE_Fail);
    }

    public static SeverResponse noRegister() {
        return new SeverResponse(1, MsgUtil.NOT_REGISTER, MsgUtil.NOT_REGISTER);
    }

    public static SeverResponse hasRegister() {
        return new SeverResponse(0, MsgUtil.HAS_REGISTER, MsgUtil.HAS_REGISTER);
    }

    public static SeverResponse noLogin() {
        return new SeverResponse(1, MsgUtil.NOT_LOGIN, MsgUtil.NOT_LOGIN);
    }

    public static SeverResponse hasLogin() {
        return new SeverResponse(0, MsgUtil.HAS_LOGIN, MsgUtil.HAS_LOGIN);
    }

    public static SeverResponse getSuccess(Object data) {
        SeverResponse response = new SeverResponse(1, MsgUtil.GET_SUCCESS);
        response.setData(data);
        return response;
    }

    public static SeverResponse noData() {
        return new SeverResponse(0, MsgUtil.NO_DATA, MsgUtil.NO_DATA);
    }


    public static SeverResponse getFail() {
        return new SeverResponse(0, MsgUtil.GET_Fail, MsgUtil.GET_Fail);
    }

    public static SeverResponse getFail(Object msg) {
        return new SeverResponse(0, MsgUtil.GET_Fail,msg);
    }

    public SeverResponse() {
    }

    public SeverResponse(int status, String msg) {
        this.status = status;
        Msg = msg;
    }

    public SeverResponse(int status, String msg, Object data) {
        this.status = status;
        Msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeverResponse{" +
                "status=" + status +
                ", Msg='" + Msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
