package interfaces;

import java.util.List;

import models.personagens.Personagem;

public interface IPersonagensFactory {
    List<String> getNomesPersonagensDisponiveis();
    Personagem criarPersonagem(String nome);
    Personagem criarPersonagemAleatorio();
    String getDescricaoPersonagem(String nome);
}
