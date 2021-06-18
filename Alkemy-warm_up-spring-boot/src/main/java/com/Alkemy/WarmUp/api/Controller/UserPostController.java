package com.Alkemy.WarmUp.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Alkemy.WarmUp.api.Model.UserPost;
import com.Alkemy.WarmUp.api.Service.UserPostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserPostController {
	
	@Autowired
	UserPostService userPostSer;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/posts")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<UserPost> findAll(){
		return userPostSer.findAll();
	}
	
	
	@GetMapping("/id/posts/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public UserPost findById(@PathVariable Long id) {
		return userPostSer.findById(id);
	}
	
	
	@GetMapping("titulo/post")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> findByTitulo(@RequestParam String titulo){
		try {			
			return ResponseEntity.status(HttpStatus.OK).body(userPostSer.findByTitulo(titulo));			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
		
	}
	
	
	@GetMapping("/contenido/post")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> findByContenido(@RequestParam String contendo){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userPostSer.findByContenido(contendo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}
	
	
	@GetMapping("/imagen/post")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> findByImagen(@RequestParam String imagen){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userPostSer.findByImagen(imagen));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}
	
	@GetMapping("/categoria/post")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> findByCategoria(@RequestParam String categoria){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userPostSer.findByCategoria(categoria));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
		}
	}
	
	
	@PostMapping("/create/post")
	@PreAuthorize("hasRole('ADMIN')")
	//@ResponseStatus(HttpStatus.CREATED) 
	public UserPost create(@RequestBody UserPost user) {
		return userPostSer.save(user);		
	}
	
	
	@PutMapping("/update/posts/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.ACCEPTED) 
	public UserPost modify(@RequestBody UserPost userPost , @PathVariable Long id) {
		UserPost postUpdate = userPostSer.findById(id);
		
		postUpdate.setTitulo(userPost.getTitulo());
		postUpdate.setContenido(userPost.getContenido());
		postUpdate.setImagen(userPost.getImagen());
		postUpdate.setCategoria(userPost.getCategoria());
					
		return  userPostSer.save(postUpdate);
	}
	
	
	@DeleteMapping("/delete/post/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	//@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Long id) {
		userPostSer.delete(id);
	}
	
	
}
