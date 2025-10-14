package interfaces;

import models.personagens.Personagem;

public interface IArma {
    boolean validarUsoDeArma(Personagem atacador);
    void atacar(Personagem atacador, Personagem atacado);
}