package br.com.projeto.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.events.DTO.SubscriptionResponse;
import br.com.projeto.events.DTO.UserIndicationNotFoundException;
import br.com.projeto.events.Exception.EventNotFoundException;
import br.com.projeto.events.Exception.SubscriptionConflictException;
import br.com.projeto.events.model.Event;
import br.com.projeto.events.model.Subscription;
import br.com.projeto.events.model.User;
import br.com.projeto.events.repository.EventRepo;
import br.com.projeto.events.repository.SubscriptionRepo;
import br.com.projeto.events.repository.UserRepo;

@Service
public class SubscriptionService {

    @Autowired
    private EventRepo evtRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubscriptionRepo subRepo;

    public SubscriptionResponse createNewSubscription(String eventName,  User user, Integer userId){
       
        // recuperar o evento pelo nome
        Event evt = (Event) evtRepo.findByPrettyName(eventName);
        // ver se o evnto existe 
        if(evt == null){
            throw new EventNotFoundException(eventName);
        }
        User userRec = userRepo.findByEmail(user.getEmail());
        // ver se o usuario está cadastrado
        if(userRec == null){
            userRec = userRepo.save(user);
        }
        User indicador = null;
        if(userId != null){
            indicador = userRepo.findById(userId).orElse(null);
            if(indicador == null){
                throw new UserIndicationNotFoundException("Usúario "+userId+" indicador não existe");
                }
        }
        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscription(userRec);
        subs.setIndication(indicador);
        Subscription tmpSub = subRepo.findByEventAndSubscription(evt, userRec);
        if(tmpSub != null){
            throw new SubscriptionConflictException("Já existe inscrição para o usuário "+userRec.getName() +" no evento "+evt.getTitle() );
        }

        Subscription res = subRepo.save(subs);
        
        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/"+res.getEvent().getPrettyName()+"/"+res.getSubscription().getId());
    }
}
