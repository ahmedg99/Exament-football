package tn.spring.springboot.Services.Interfaces;


import tn.spring.springboot.entities.MatchFootball;

import java.util.Date;

public interface IServiceMatch {

public MatchFootball ajouterMatchFootballEtAffecterEquipe(MatchFootball matchFootball , Integer idEquipe1 , Integer idequipe2) ;
public  MatchFootball reporterMatchFootball(Integer idMatch  , Date dateReportes) ;
}
