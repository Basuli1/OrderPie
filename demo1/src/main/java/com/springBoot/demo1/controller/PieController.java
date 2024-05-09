package com.springBoot.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springBoot.demo1.models.Pie;
import com.springBoot.demo1.services.PieService;

@Controller
@RequestMapping("pie")
public class PieController {
	
	private PieService pieService;
	
	@Autowired
	public PieController(PieService pieService) {
		this.pieService=pieService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Pie> getAllPie(){
		return pieService.getPieList();
	}
	
	@GetMapping(params="pieName")
	public @ResponseBody ResponseEntity<Pie>findPie(@RequestParam String pieName){
		return new ResponseEntity<>(pieService.findPie(pieName),HttpStatus.OK);
	}
	
	@GetMapping("/calories/{limit}")
	public @ResponseBody ResponseEntity<List<Pie>> getPieByCalories(@PathVariable int limit){
//		List<Pie> calorieList = pieService.getPiesByCalories(limit);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(calorieList);
		return new ResponseEntity<>(pieService.getPiesByCalories(limit),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{pieName}")
	public @ResponseBody ResponseEntity<String> deletePie(@PathVariable String pieName){
		pieService.deletePie(pieName);
		return ResponseEntity.accepted().body("successfully deleted");
	}
	
	@PatchMapping("/patch")
	public @ResponseBody ResponseEntity<String> patchPie(@RequestParam String pieName,
										@RequestParam(defaultValue="0",required=false) int calories,
										@RequestParam(defaultValue="0",required=false) int slicesAvailable){
		
		pieService.patchPie(pieName, calories, slicesAvailable);
		
		return ResponseEntity.ok()
				.body("successfully updated pie");
	}
	
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<Pie> updatePie(@RequestBody Pie updatedPie){
		return new ResponseEntity<>(pieService.updatePie(updatedPie),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<Pie> createPie(@RequestBody Pie newPie){
		pieService.addPie(newPie);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(newPie);
	}
}
