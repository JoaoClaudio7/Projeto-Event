package br.com.projeto.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.events.model.Event;
import br.com.projeto.events.service.EventService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EventController {
    @Autowired
    private EventService service;

    @PostMapping("/events")
    public Event addNewEvent(@RequestBody Event newEvent) {
        
        
        return service.addNewEvent(newEvent);
    }

    @GetMapping("/events")
    public List<Event> getAllEvent() {
        return service.getALLEvents();
    }
    
    @GetMapping("/events/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable String prettyName){
        
        return service.getByPrettyName(prettyName);
    }
}