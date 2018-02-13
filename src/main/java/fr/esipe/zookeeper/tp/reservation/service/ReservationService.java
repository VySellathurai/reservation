package fr.esipe.zookeeper.tp.reservation.service;

/**
 * Created by Vyach on 13/02/2018.
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReservationService {

    private String name = "World";

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}