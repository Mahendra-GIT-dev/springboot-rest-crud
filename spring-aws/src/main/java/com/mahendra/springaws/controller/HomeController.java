package com.mahendra.springaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahendra.springaws.entity.Manager;
import com.mahendra.springaws.exception.ResourceNotFoundException;
import com.mahendra.springaws.repository.ManagerRepo;

@RestController
@RequestMapping("/api/managers")
public class HomeController {

	@Autowired
	private ManagerRepo repo;
	
	@GetMapping("/{id}")
	public Manager getManager(@PathVariable int id) {
		System.out.println("Manager Details ----> "+repo.findById(id));
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With the Specified ID "+id));
	}
	
	@GetMapping("/allManagers")
	public List<Manager> getManagers(){
		System.out.println("List of Managers Present in the Company are ----> "+repo.findAll());
		return repo.findAll();
	}
	
	@PostMapping("/addManager")
	public Manager addManager(@RequestBody Manager manager) {
		System.out.println("Manager Details Added Successfully ... "+manager);
		return repo.save(manager);
	}
	
	@PutMapping("/updateManager/{id}")
	public Manager updateManager(@PathVariable int id, @RequestBody Manager manager){
		
		Manager existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With the Specified ID "+id));
		System.out.println("Existing manager details ----> "+existing);
		
		existing.setFirstName(manager.getFirstName());
		existing.setLastName(manager.getLastName());
		existing.setEmail(manager.getEmail());
		
		System.out.println("Manager After Updation ----> "+existing);
		
		return repo.save(existing);
	}
	
	@DeleteMapping("/delManager/{id}")
	public ResponseEntity<Manager> deleteManager(@PathVariable int id) {
		Manager existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With the Specified ID "+id));
		System.out.println("Before Deleting the Manager .... Manager details are ---->"+existing);
		repo.delete(existing);
		System.out.println("After deleting the Manager details ... ");
		if(existing == null) {
			System.out.println("the manager details were not deleted ... the manager is ---->"+existing);
		}
		else {
			System.out.println("Manager Details Deleted Successfullyyyyyy........");
		}
		return ResponseEntity.ok().build();
	}
	
}
