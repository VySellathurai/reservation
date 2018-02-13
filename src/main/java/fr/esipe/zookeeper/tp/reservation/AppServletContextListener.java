package fr.esipe.zookeeper.tp.reservation;

/**
 * Created by Vyach on 13/02/2018.
 */
import javax.servlet.ServletContextEvent;

import org.springframework.stereotype.Component;

/**
 * Simple {@link AppServletContextListener} to test gh-2058.
 */

public class AppServletContextListener  {


    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*** contextInitialized");
    }


    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("*** contextDestroyed");
    }

}