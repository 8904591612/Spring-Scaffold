package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.model.Data;
import com.example.repository.DataRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DataService {

	@Autowired
	private DataRepository dataRepository;

	public List<Data> findAll() {
		return dataRepository.findAll();
	}

	public Data findOne(Integer id) {
		return dataRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Data save(Data entity) {
		return dataRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Data entity) {
		dataRepository.delete(entity);
	}

}
	
