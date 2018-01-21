package br.com.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.curso.model.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {
	

}
 