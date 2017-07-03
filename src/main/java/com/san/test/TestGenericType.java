package com.san.test;

import com.san.db.PaginatedData;
import com.san.domain.User;
import com.san.util.CommonUtil;

public class TestGenericType {
	public static void main(String[] args) {
		PaginatedData<User> data = new PaginatedData<User>(null, null);
		System.out.println(CommonUtil.getGenericParameterTypeOfClass(data.getClass(), 0));
	}
}
