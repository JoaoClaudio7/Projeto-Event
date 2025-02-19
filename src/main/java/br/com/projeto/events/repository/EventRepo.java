package br.com.projeto.events.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.projeto.events.model.Event;



public interface EventRepo extends CrudRepository<Event, Integer>{
    public Event findByPrettyName(String prettyName);
}
