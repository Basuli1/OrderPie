package com.springBoot.demo1.services;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.demo1.exception.ResourceNotFoundException;
import com.springBoot.demo1.models.Consumer;
import com.springBoot.demo1.repositories.ConsumerRepository;

@Service
public class ConsumerService {
	
	private ConsumerRepository consumerRepository;
	private PieService pieService;
	
	@Autowired
	public ConsumerService(ConsumerRepository consumerRepository, PieService pieService) {
		this.consumerRepository = consumerRepository;
		this.pieService = pieService;
	}
	
	//service
	public void register(Consumer newConsumer) {
		consumerRepository.saveAndFlush(newConsumer);
	}
	
	//TODO: custom query
	public void login(String username,String password)throws AuthenticationException{
		consumerRepository.findByUsernameAndPassword(username, password);
	}
	
	//order a pie
	public void orderPie(String username,String pieName) {
		Consumer consumer = consumerRepository.findById(username)
				.orElseThrow(()->new ResourceNotFoundException(username+" was not found in the list"));
		consumer.setLastPie(pieService.findPie(pieName));
		consumerRepository.saveAndFlush(consumer);
	}
	
	
}
