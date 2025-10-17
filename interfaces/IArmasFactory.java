package interfaces;

import java.util.List;

import models.armas.Arma;

public interface IArmasFactory {
    List<String> getNomesArmasDisponiveis();
    Arma<?> criarArma(String nome);
}
