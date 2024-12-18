package tn.spring.springboot.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tn.spring.springboot.entities.enums.EnumExample;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data

public class TableExample implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter

    private int id;
    private String nom ;
    private String prenom ;
    @Enumerated(EnumType.STRING)
    private EnumExample enumExample ;


}