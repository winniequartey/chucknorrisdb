package net.djomeda.tutorial.mutualssl.controller;

import net.djomeda.tutorial.mutualssl.json.Jokes;
import net.djomeda.tutorial.mutualssl.json.Response;
import net.djomeda.tutorial.mutualssl.service.ChucknorrisdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by joseph on 9/18/16.
 */
@RestController
public class CallICNDBController {

    @Autowired
    ChucknorrisdbService chucknorrisdbService;

    private static final Logger logger = LoggerFactory.getLogger(CallICNDBController.class);

    @RequestMapping(value = "/jokes", method =POST)
    public Response getJokes(@RequestParam(value = "count") Integer count){



        if(null == count){
            logger.error("[Failure] Param count can't be null");
            return new Response("Failure","Param count can't be null", null);
        }

        if(count <= 1) {
            logger.error("[Failure] Param count should be greater than one");
            return new Response("Failure","Param count should be greater than one", null);
        }

        RestTemplate restTemplate = new RestTemplate();
        Jokes jokes = restTemplate.getForObject(String.format("http://api.icndb.com/jokes/random/%d", count),Jokes.class);

        if(null == jokes){
            logger.error("[Failure] Error contacting upstream joke provider");
            return new Response("Failure", "Error contacting upstream joke provider", null);
        }
        logger.info("[Success] Got jokes. Saving to DB");
        chucknorrisdbService.addChuckNorrisJoke(jokes.value);
        return new Response("Success","", jokes.value);
    }
}
