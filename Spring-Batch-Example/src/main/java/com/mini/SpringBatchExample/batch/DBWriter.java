package com.mini.SpringBatchExample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import com.mini.SpringBatchExample.entty.Users;
import com.mini.SpringBatchExample.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<Users> {
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public void write(List<? extends Users> user) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Data Saved For Users: " +user);
		userrepo.saveAll(user);
	}

}
