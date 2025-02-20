package br.com.projeto.events.DTO;

public class UserIndicationNotFoundException extends RuntimeException {
    public UserIndicationNotFoundException(String msg){
        super(msg);
    }
}
