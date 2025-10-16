package models.personagens;

import java.util.*;

import models.armas.CajadoArcano;
import models.armas.TipoArma;

public class Mago extends Personagem {

    public Mago() {
        super(70, 150, new AtributosBase(5, 7, 18), "Regeneração de Mana", 
            List.of(TipoArma.CAJADO, TipoArma.ADAGA),
            new CajadoArcano());
    }

}
