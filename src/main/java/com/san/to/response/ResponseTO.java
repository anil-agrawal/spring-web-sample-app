package com.san.to.response;

import javax.servlet.http.HttpServletRequest;

import com.san.util.PropertyHelperUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Base response")
public class ResponseTO {

	@ApiModelProperty(value = "API success status")
	private boolean success;

	@ApiModelProperty(value = "API response code")
	private int code;

	@ApiModelProperty(value = "API response message")
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccessMessage(HttpServletRequest request, String messageCode) {
		this.code = 0;
		this.success = true;
		this.message = PropertyHelperUtil.getMessage(request, messageCode);
	}
}
