package br.com.projeto.events.Exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String msg){
        super("Evento "+msg+" Não existe");
    }
}
