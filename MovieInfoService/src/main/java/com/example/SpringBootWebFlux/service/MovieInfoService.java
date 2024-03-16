package com.example.SpringBootWebFlux.service;

import com.example.SpringBootWebFlux.entity.MovieInfo;
import com.example.SpringBootWebFlux.repository.MovieInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieInfoService {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    private final WebClient webClient;

    @Autowired
    public MovieInfoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8079").build();
    }


    //0-1 Mono
    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        log.info("calling external API" );
        Flux<String> helloWorldFlux = callHelloWorldAPI();
        Mono<String> monoString = helloWorldFlux.collect(Collectors.joining());
        monoString.subscribe(System.out::println);

        log.info("addMovieInfo : {} " , movieInfo );
        return movieInfoRepository.save(movieInfo)
                .log();
    }

    private Flux<String> callHelloWorldAPI() {
        return webClient.get()
                .uri("/getHelloWorld")
                .retrieve()
                .bodyToFlux(String.class);
    }

    //0-1 Mono
    public Mono<MovieInfo> getMovieInfoById(String id) {
        return movieInfoRepository.findById(id).log();
    }

    //0-n Flux
    public Flux<MovieInfo> getAllMovieInfo(){
        return  movieInfoRepository.findAll().log();
    }

    //0-n Flux
    public Flux<MovieInfo> getMovieInfoByYear(Integer year){
        return movieInfoRepository.findByYear(year).log();
    }

}
