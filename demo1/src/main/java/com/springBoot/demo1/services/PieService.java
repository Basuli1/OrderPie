package com.springBoot.demo1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.demo1.exception.ResourceNotFoundException;
import com.springBoot.demo1.models.Pie;
import com.springBoot.demo1.repositories.PieRepository;
@Service
public class PieService {
	
	private PieRepository pieRepository;
	
	@Autowired
	public PieService(PieRepository pieRepository) {
		this.pieRepository = pieRepository;
	}

	
	//services
	
	public List<Pie> getPieList(){
		return pieRepository.findAll();
	}

	public Pie findPie(String pieName) throws ResourceNotFoundException {
		return pieRepository.findById(pieName)
				.orElseThrow(()->new ResourceNotFoundException(pieName+" not available,please check all the available pie"));
	}
	
	
	//TODO: create custom JPQL
	public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException{
		List<Pie> caloriePieList = pieRepository.findByCaloriesLessThan(limit);
		if(caloriePieList.isEmpty()) {
			throw new ResourceNotFoundException("No pies are found in the range of "+limit);
		}
		return caloriePieList;
	}
	
	public void deletePie(String pieName) {
		pieRepository.deleteById(pieName);
	}
	
	
	
	public void patchPie(String pieName,int calories,int slicesAvailable) throws ResourceNotFoundException{
		Pie pie = pieRepository.findById(pieName)
				.orElseThrow(()->new ResourceNotFoundException(pieName+" not found, please try another"));
		
		if(calories>0)pie.setCalories(calories);
		if(slicesAvailable>0)pie.setSlicesAvailable(slicesAvailable);
		pieRepository.save(pie);
		
	}
	
	public Pie updatePie(Pie updatedPie) throws ResourceNotFoundException{
		Pie pie = pieRepository.findById(updatedPie.getPieName())
				.orElseThrow(()->new ResourceNotFoundException(updatedPie.getPieName()+" not found, please try another"));
		
		pieRepository.save(pie);
		return pie;
	}
	
	public void addPie(Pie newPie) {
		pieRepository.saveAndFlush(newPie);
	}
	

}
