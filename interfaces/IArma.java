package interfaces;

import models.personagens.Personagem;

public interface IArma {
    boolean temRequisito(int definidor);
    void atacar(Personagem atacador, Personagem atacado);
}