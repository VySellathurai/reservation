package fr.esipe.zookeeper.tp.reservation.model;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by Vyach on 14/02/2018.
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private String name;
    private String reference;

    public Client(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }

    public Client() {

    }

    public Client(String name) {

        if (name != null || name != "") {

            int length = 4;
            boolean useLetters = true;
            boolean useNumbers = true;
            this.reference = RandomStringUtils.random(length, useLetters, useNumbers);
            this.name = name;

            logger.info("reference generation : " + this.reference);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String toString() {

        return "{" +
                "\"name\":" + "\"" + this.getName() + "\"," +
                "\"reference\":" + "\"" + this.getReference() + "\"" +
                "}";
    }
}
