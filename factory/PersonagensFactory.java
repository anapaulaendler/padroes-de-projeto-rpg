package factory;

import models.personagens.*;
import java.util.*;
import java.util.function.Supplier;

import interfaces.IPersonagensFactory;

/**
 * Fábrica de Personagens - Implementação do Factory Pattern 
 * 
 * Sugere-se primeiro ler o que foi comentado no arquivo ArmasFactory.
 * De forma semelhante, como para Armas eu preciso deixar o usuário trocar de arma 
 * durante a batalha, foi considerado mais simples utilizar uma lógica semelhante 
 * para a seleção (e não troca, o sistema não permite troca de
 * personagens durante a batalha) durante o que precede a batalha.
 *  
 * 1. JUSTIFICATIVA:
 * - Centraliza o ponto de acesso aos personagens dentro da aplicação.
 * 
 * 2. OBSERVAÇÃO:
 * - Dentro da partida, o usuário não pode trocar de personagem pensando que uma nova
 * instância seria criada durante a troca e, por isso, a vida e o mana do personagem
 * seriam resetados.
 */
public class PersonagensFactory implements IPersonagensFactory {
    private final Map<String, Supplier<Personagem>> registroPersonagens;

    public PersonagensFactory() {
        this.registroPersonagens = new HashMap<>();
        registrarPersonagensPadrao();
    }

    private void registrarPersonagensPadrao() {
        registrarPersonagem("Guerreiro", Guerreiro::new);
        registrarPersonagem("Arqueiro", Arqueiro::new);
        registrarPersonagem("Mago", Mago::new);
        registrarPersonagem("Paladino", Paladino::new);
    }

    private void registrarPersonagem(String nome, Supplier<Personagem> fabrica) {
        registroPersonagens.put(nome, fabrica);
    }

    public List<String> getNomesPersonagensDisponiveis() {
        List<String> nomes = new ArrayList<>(registroPersonagens.keySet());
        Collections.sort(nomes); // Ordena alfabeticamente
        return nomes;
    }

    public Personagem criarPersonagem(String nome) {
        Supplier<Personagem> fabrica = registroPersonagens.get(nome);
        if (fabrica == null) {
            throw new IllegalArgumentException("Personagem não encontrado: " + nome);
        }
        return fabrica.get();
    }

    public Personagem criarPersonagemAleatorio() {
        List<String> nomes = getNomesPersonagensDisponiveis();
        if (nomes.isEmpty()) {
            throw new IllegalStateException("Nenhum personagem registrado na fábrica");
        }
        String nomeAleatorio = nomes.get(new Random().nextInt(nomes.size()));
        return criarPersonagem(nomeAleatorio);
    }

    public String getDescricaoPersonagem(String nome) {
        try {
            Personagem personagem = criarPersonagem(nome);
            return String.format("%s - Vida: %d, Mana: %d, Habilidade: %s", 
                nome, personagem.vida, personagem.mana, personagem.habilidadePassiva);
        } catch (Exception e) {
            return nome + " - Informações não disponíveis";
        }
    }
}