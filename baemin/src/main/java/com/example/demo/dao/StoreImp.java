package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Store;

@Repository
public class StoreImp implements StoreDAO {

	@Autowired
	private SqlSession sql;
	
	@Override
	public List<Store> storeList(Map<String, Object> map) {
		return sql.selectList("store.storeList", map);
 	}

}
