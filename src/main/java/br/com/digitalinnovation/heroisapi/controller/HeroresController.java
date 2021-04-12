package br.com.digitalinnovation.heroisapi.controller;

import br.com.digitalinnovation.heroisapi.document.Heroes;
import br.com.digitalinnovation.heroisapi.repository.HeroesRepository;
import br.com.digitalinnovation.heroisapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.digitalinnovation.heroisapi.constans.HeroesConst.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroresController {


    HeroesService heroesService;
    @Autowired
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroresController.class);

    public HeroresController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesService = heroesService;
        this.heroesRepository = heroesRepository;
    }
    @GetMapping(path = HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems(){
        log.info("requesting the list off all heroes");
        return heroesService.findAll();
    }
    @GetMapping(path=HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("requesting the hero with id{}", id);
        return heroesService.findyById(id)
                .map((item)->new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHeroes(@RequestBody Heroes heroes){
        log.info("A new hero was created");
        return heroesService.Save(heroes);
    }

    @DeleteMapping(path = HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteByIddHero(@PathVariable String id){
        heroesService.deleteById(id);
        log.info("Deleting a hero with id{}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
