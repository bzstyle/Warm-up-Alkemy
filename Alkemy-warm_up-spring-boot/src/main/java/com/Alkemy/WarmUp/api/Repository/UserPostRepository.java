package com.Alkemy.WarmUp.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Alkemy.WarmUp.api.Model.UserPost;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {
	
	public List<UserPost> findByTituloContaining(String titulo);
    
	 public List<UserPost> findByContenidoContaining(String contenido);
	    
	 public List<UserPost> findByImagenContaining(String imagen);
	 
	 public List<UserPost> findByCategoriaContaining(String categoria);
	

}
