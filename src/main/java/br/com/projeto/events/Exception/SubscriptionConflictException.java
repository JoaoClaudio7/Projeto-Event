package br.com.projeto.events.Exception;

public class SubscriptionConflictException extends RuntimeException{
    public SubscriptionConflictException(String msg){
        super(msg);
    }
}
