package br.com.projeto.events.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projeto.events.model.User;

public interface UserRepo extends CrudRepository<User, Integer>{
    public User findByEmail(String email);
}
