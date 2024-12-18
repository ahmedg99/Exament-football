package tn.spring.springboot.Services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceMatch;
import tn.spring.springboot.entities.Equipe;
import tn.spring.springboot.entities.MatchFootball;
import tn.spring.springboot.repositories.EquipeRepository;
import tn.spring.springboot.repositories.JoueurRepository;
import tn.spring.springboot.repositories.MachRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ServiceMatchImp implements IServiceMatch {

    @Autowired
    MachRepository machRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    JoueurRepository joueurRepository;


    @Override
    public MatchFootball ajouterMatchFootballEtAffecterEquipe(MatchFootball matchFootball, Integer idEquipe1, Integer idEquipe2) {
        Equipe equipe1 = equipeRepository.findById(idEquipe1)
                .orElseThrow(() -> new IllegalArgumentException("Equipe with ID " + idEquipe1 + " not found"));
        Equipe equipe2 = equipeRepository.findById(idEquipe2)
                .orElseThrow(() -> new IllegalArgumentException("Equipe with ID " + idEquipe2 + " not found"));

        if (equipe1.equals(equipe2)) {
            throw new IllegalArgumentException("A match cannot have the same team playing against itself.");
        }

        List<Equipe> equipeList = Arrays.asList(equipe1, equipe2);

        matchFootball.setEquipes(equipeList);

        return machRepository.save(matchFootball);
    }


    @Override
    public MatchFootball reporterMatchFootball(Integer idMatch, Date dateReportee) {
        MatchFootball matchFootball = machRepository.findById(idMatch)
                .orElseThrow(() -> new IllegalArgumentException("Match with ID " + idMatch + " not found"));

        Date currentDate = new Date();

        if (matchFootball.getDateMatch().before(currentDate)) {
            throw new IllegalArgumentException("Cannot postpone match: Match is already played. Match date: "
                    + matchFootball.getDateMatch() + ", Current date: " + currentDate);
        }

        int countInjuredPlayers = joueurRepository.countByEquipeAndBlessureIsTrue(matchFootball.getEquipes().get(0))
                + joueurRepository.countByEquipeAndBlessureIsTrue(matchFootball.getEquipes().get(1));

        if (countInjuredPlayers >= 2) {
            matchFootball.setDateMatch(dateReportee);
            return machRepository.save(matchFootball);
        } else {
            throw new IllegalArgumentException("Cannot postpone match: fewer than 2 injured players. Injured players count: " + countInjuredPlayers);
        }
    }


}
