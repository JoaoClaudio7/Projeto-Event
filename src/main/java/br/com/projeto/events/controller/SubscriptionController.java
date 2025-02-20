package br.com.projeto.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.events.DTO.ErrorMessage;
import br.com.projeto.events.DTO.SubscriptionResponse;
import br.com.projeto.events.DTO.UserIndicationNotFoundException;
import br.com.projeto.events.Exception.EventNotFoundException;
import br.com.projeto.events.Exception.SubscriptionConflictException;
import br.com.projeto.events.model.User;
import br.com.projeto.events.service.SubscriptionService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable (required = false) Integer userId) {
        try{
            SubscriptionResponse res =  service.createNewSubscription(prettyName, subscriber, userId);
        if(res != null){
            return ResponseEntity.ok(res);
        }
        
        }catch(EventNotFoundException e){
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        }catch(SubscriptionConflictException ex){
            return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
        } catch(UserIndicationNotFoundException exx){
            return ResponseEntity.status(404).body(new ErrorMessage(exx.getMessage()));
        }
        return ResponseEntity.badRequest().build();
    }
    
}
