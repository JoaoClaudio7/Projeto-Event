package br.com.projeto.events.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projeto.events.model.Event;
import br.com.projeto.events.model.Subscription;
import br.com.projeto.events.model.User;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer>{
    public Subscription findByEventAndSubscription(Event evt, User user);
}
