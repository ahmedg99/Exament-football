package tn.spring.springboot.Services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceEquipe;
import tn.spring.springboot.entities.Equipe;
import tn.spring.springboot.repositories.EquipeRepository;

@Service
@Slf4j
public class ServiceEquipeImp implements IServiceEquipe {

    @Autowired
    EquipeRepository equipeRepository ;


    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        log.info("l'équipe est ajoutée avec succès");
        return equipeRepository.save(equipe);
    }
}
