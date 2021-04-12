package br.com.digitalinnovation.heroisapi.service;

import br.com.digitalinnovation.heroisapi.document.Heroes;
import br.com.digitalinnovation.heroisapi.repository.HeroesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

   private final HeroesRepository heroesRepository;


    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public Flux<Heroes> findAll(){
     return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findyById(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> Save(Heroes heroes){
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<Boolean> deleteById(String id){
        this.heroesRepository.deleteById(id);
        return Mono.justOrEmpty(true);
    }
}
