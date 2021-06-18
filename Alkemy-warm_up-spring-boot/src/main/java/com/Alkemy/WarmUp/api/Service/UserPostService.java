package com.Alkemy.WarmUp.api.Service;

import java.util.List;

import com.Alkemy.WarmUp.api.Model.UserPost;

public interface UserPostService {

	 public List<UserPost>findAll();
	    
	    public UserPost findById(long id);
	    
	    public List<UserPost> findByTitulo(String titulo) throws Exception;
	    
	    public List<UserPost> findByContenido(String contenido) throws Exception;
	    
	    public List<UserPost> findByImagen(String imagen) throws Exception;
	    
	    public List<UserPost> findByCategoria(String categoria) throws Exception;
		
		public UserPost save(UserPost userPost);
		
		public void delete(Long id);
	
}
