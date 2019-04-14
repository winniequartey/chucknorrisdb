package net.djomeda.tutorial.mutualssl.service;

import net.djomeda.tutorial.mutualssl.json.Jokes;
import net.djomeda.tutorial.mutualssl.repository.ChucknorrisdbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

@Service
public class ChucknorrisdbService  {

    @Autowired
    ChucknorrisdbRepository chucknorrisdbRepository;

    public void addChuckNorrisJoke(Iterable chuckJokes){
        chucknorrisdbRepository.save(chuckJokes);
    }

    @Scheduled(fixedDelayString = "${fixed.delay.value}")
    public void fetchRealTransaction() {
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();
        int count =random.nextInt(3);
        restTemplate.postForObject(String.format("http://localhost:8080/jokes?count=%d", count),count,Object.class);
    }
}
