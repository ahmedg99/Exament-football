package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.MatchFootball;

import java.util.Date;


@EnableJpaRepositories
public interface MachRepository extends JpaRepository<MatchFootball,Integer> {

    public MatchFootball findByDateMatch(Date dateMatch ) ;



}
