package br.com.curso.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.curso.model.UserModel;
import br.com.curso.repository.UserRepository;
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
	    return userRepository.findAll();
	}
	@PostMapping("/user")
	public UserModel createNote(@Valid @RequestBody UserModel user) {
	    return userRepository.save(user);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getNoteById(@PathVariable(value = "id") Long id) {
	    UserModel user = userRepository.findOne(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(user);
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<UserModel> updateNote(@PathVariable(value = "id") Long id, 
	                                       @Valid @RequestBody UserModel userDetails) {
	    UserModel user = userRepository.findOne(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    
	    user.setNome(userDetails.getNome());
	    user.setSenha(userDetails.getSenha());
	    user.setApelido(userDetails.getApelido());
	    user.setLogin(userDetails.getLogin());
	 

	    UserModel updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<UserModel> deleteNote(@PathVariable(value = "id") Long id) {
	    UserModel user = userRepository.findOne(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    userRepository.delete(user);
	    return ResponseEntity.ok().build();
	}
}
