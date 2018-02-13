package fr.esipe.zookeeper.tp.reservation.web;

/**
 * Created by Vyach on 13/02/2018.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {


    @GetMapping("/")
    @ResponseBody
    public String index() {


        return "Application is running !";
    }

}