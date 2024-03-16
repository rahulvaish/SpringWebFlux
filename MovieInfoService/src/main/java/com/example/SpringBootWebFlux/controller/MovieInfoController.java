package com.example.SpringBootWebFlux.controller;

import com.example.SpringBootWebFlux.entity.MovieInfo;
import com.example.SpringBootWebFlux.service.MovieInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
public class MovieInfoController {

    @Autowired
    private MovieInfoService movieInfoService;

    Sinks.Many<MovieInfo> movieInfoSink = Sinks.many().replay().latest();

    //0-1 Mono
    @PostMapping("/addMovieInfo")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo) {
        Mono<MovieInfo> monoMovieInfo =  movieInfoService.addMovieInfo(movieInfo);
        System.out.println(monoMovieInfo);
        return monoMovieInfo.doOnNext(savedMovieInfo -> movieInfoSink.tryEmitNext(savedMovieInfo));
    }

    //0-1 Mono
    @GetMapping("/getMovieInfoById/{id}")
    public Mono<MovieInfo> getMovieInfoById(@PathVariable String id) {
        return movieInfoService.getMovieInfoById(id);
    }

    //0-n Flux
    @GetMapping("/getAllMovieInfo")
    public Flux<MovieInfo> getAllMovieInfo() {
        return movieInfoService.getAllMovieInfo();
    }

    //0-n Flux
    @GetMapping("/getMovieInfoByYear/{year}")
    public Flux<MovieInfo> getMovieInfoByYear(@PathVariable Integer year) {
        return movieInfoService.getMovieInfoByYear(year);
    }
}
