package com.aweika.common.response;

public class WebResponse {

	private String code;		// 前端通过code来判断这次请求是否成功
	private Object msg;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Object getMsg() {
		return msg;
	}

	public WebResponse setMsg(Object msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static WebResponse resSuccess(String codeMsg, Object data) {
		WebResponse res = new WebResponse();
		res.setCode(ResCode.SUCCESS);
		if (codeMsg == null || "".equals(codeMsg.trim())) {
			res.setMsg("查询成功");
		} else {
			res.setMsg(codeMsg);
		}
		res.setData(data);
		return res;
	}

	public static WebResponse resFail(String codeMsg, Object data) {
		WebResponse res = new WebResponse();
		res.setCode(ResCode.FAIL);
		if (codeMsg == null || "".equals(codeMsg.trim())) {
			res.setMsg("查询失败");
		} else {
			res.setMsg(codeMsg);
		}
		res.setData(data);
		return res;
	}

	public static WebResponse resException(Object codeMsg, Object data) {
		WebResponse res = new WebResponse();
		res.setCode(ResCode.EXCEPTION);
		if (codeMsg == null || (codeMsg instanceof String && "".equals(((String)codeMsg).trim()))) {
			res.setMsg("系统异常");
		} else {
			res.setMsg(codeMsg);
		}
		res.setData(data);
		return res;
	}
}
