package br.com.projeto.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.events.model.Event;
import br.com.projeto.events.repository.EventRepo;

@Service
public class EventService {
    @Autowired
    private EventRepo service;

    public Event addNewEvent(Event event) {
        //gerando o pretty name
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ","-"));
        
        return service.save(event);
    }

    public List<Event> getALLEvents() {
        return (List<Event>) service.findAll();
    }

    public ResponseEntity<Event> getByPrettyName(String prettyName) {
        Event evt = service.findByPrettyName(prettyName);
        if(evt != null){
            return ResponseEntity.ok().body(evt);
        }
        return ResponseEntity.notFound().build();
    }
}
