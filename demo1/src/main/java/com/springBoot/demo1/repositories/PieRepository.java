package com.springBoot.demo1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.demo1.models.Pie;

public interface PieRepository extends JpaRepository<Pie,String> {

	List<Pie> findByCaloriesLessThan(int limit);
}
