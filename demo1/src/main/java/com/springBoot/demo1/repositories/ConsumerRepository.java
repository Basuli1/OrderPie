package com.springBoot.demo1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.demo1.models.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer,String>{
	Optional<Consumer> findByUsernameAndPassword(String username,String password);
}
