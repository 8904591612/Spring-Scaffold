package com.example.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
	
}