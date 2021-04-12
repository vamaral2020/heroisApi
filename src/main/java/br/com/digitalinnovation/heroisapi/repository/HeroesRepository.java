package br.com.digitalinnovation.heroisapi.repository;

import br.com.digitalinnovation.heroisapi.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {
}
