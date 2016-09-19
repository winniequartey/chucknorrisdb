package net.djomeda.tutorial.mutualssl.controller;

import net.djomeda.tutorial.mutualssl.json.Jokes;
import net.djomeda.tutorial.mutualssl.json.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by joseph on 9/18/16.
 */
@RestController
public class CallICNDBController {

    @RequestMapping(value = "/jokes", method =POST)
    public Response getJokes(@RequestParam(value = "count", defaultValue = "2") Integer count){



        if(null == count){
            return new Response("Failure","Param count can't be null", null);
        }

        if(count <= 1) {
            return new Response("Failure","Param count should be greater than one", null);
        }

        RestTemplate restTemplate = new RestTemplate();
        Jokes jokes = restTemplate.getForObject(String.format("http://api.icndb.com/jokes/random/%d", count),Jokes.class);

        if(null == jokes){
            return new Response("Failure", "Error contacting upstream joke provider", null);
        }
        return new Response("Success","", jokes.value);
    }
}
