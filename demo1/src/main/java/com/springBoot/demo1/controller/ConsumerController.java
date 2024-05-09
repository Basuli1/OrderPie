package com.springBoot.demo1.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.demo1.models.Consumer;
import com.springBoot.demo1.services.ConsumerService;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

	private ConsumerService consumerService;
	
	@Autowired
	public ConsumerController(ConsumerService consumerService) {
		this.consumerService=consumerService;
	}
	
	@PostMapping("login")
	public  ResponseEntity<Object> login(@RequestBody Consumer consumer) throws AuthenticationException{
		consumerService.login(consumer.getUsername(),consumer.getPassword());
		return ResponseEntity.noContent()
				.header("username",consumer.getUsername())
				.build();
	}
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody Consumer consumer){
		consumerService.register(consumer);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("successfully registered");
	}
	
	@PatchMapping("order")
	public ResponseEntity<String> order(@RequestBody String pieName, @RequestHeader("username")String username){
		consumerService.orderPie(username, pieName);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(username+" ordered "+pieName+" pie");
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleUnauthorized(AuthenticationException ex) {
		return ex.getMessage();
	}
	

}
