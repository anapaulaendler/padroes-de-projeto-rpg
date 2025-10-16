package models.personagens;

import java.util.*;

import models.armas.ArcoElfico;
import models.armas.TipoArma;

public class Arqueiro extends Personagem {
    
    public Arqueiro() {
        super(90, 80, new AtributosBase(8, 15, 7), "Esquiva", 
            List.of(TipoArma.ARCO, TipoArma.ADAGA),
            new ArcoElfico());
    }

}
