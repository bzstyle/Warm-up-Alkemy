package com.Alkemy.WarmUp.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Alkemy.WarmUp.api.Model.UserPost;
import com.Alkemy.WarmUp.api.Repository.UserPostRepository;


@Service
public class UserPostServiceImpl implements UserPostService {
	
	@Autowired
	UserPostRepository userPostRepo;

	@Override
	@Transactional(readOnly = true)
	public List<UserPost> findAll() {
		
		return userPostRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UserPost findById(long id) {
		
		return userPostRepo.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserPost> findByTitulo(String titulo) throws Exception {
		try {			
			List<UserPost> user = userPostRepo.findByTituloContaining(titulo);
			return user;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserPost> findByContenido(String contenido) throws Exception {
		try {			
			List<UserPost> user = userPostRepo.findByContenidoContaining(contenido);
			return user;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserPost> findByImagen(String imagen) throws Exception {
		try {			
			List<UserPost> user = userPostRepo.findByImagenContaining(imagen);
			return user;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserPost> findByCategoria(String categoria) throws Exception {
		try {			
			List<UserPost> user = userPostRepo.findByCategoriaContaining(categoria);
			return user;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public UserPost save(UserPost userPost) {
		
		return userPostRepo.save(userPost);
	}

	@Override
	public void delete(Long id) {
		userPostRepo.deleteById(id);
		
	}
	
	
	
}
