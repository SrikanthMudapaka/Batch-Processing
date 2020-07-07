package com.mini.SpringBatchExample.batch;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.mini.SpringBatchExample.entty.Users;

@Component
public class Processor implements ItemProcessor<Users, Users> {

	private static final Map<String, String> DEPT_NAME = new HashMap<>();



	public Processor() {
		// TODO Auto-generated constructor stub
		DEPT_NAME.put("1", "TECHNOLOGY");
		DEPT_NAME.put("2", "OPERATIONS");
		DEPT_NAME.put("3", "ACCOUNTS");
	}



	@Override
	public Users process(Users user) throws Exception {
		// TODO Auto-generated method stub
		String deptCode = user.getDept();
		String dept = DEPT_NAME.get(deptCode);
		user.setDept(dept);
		user.setCreatedDate(new java.util.Date());
		System.out.println(String.format("Converted from [%s] to [%s]",deptCode,dept));
		return user;
	}

}
