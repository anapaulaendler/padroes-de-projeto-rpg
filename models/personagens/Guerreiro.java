package models.personagens;

import java.util.List;

import models.armas.TipoArma;

public class Guerreiro extends Personagem {

    public Guerreiro() {
        super(120, 50, new AtributosBase(15, 8, 5), "Pele Dura", List.of(TipoArma.ESPADA, TipoArma.MACHADO));
    }
    
}
