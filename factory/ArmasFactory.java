package factory;

import java.util.*;
import java.util.function.Supplier;

import interfaces.IArmasFactory;
import models.armas.*;

/**
 * Fábrica de Armas - Implementação do Factory Pattern 
 * 
 * 1. POR QUÊ?
 * Apesar do professor ter indicado utilizar somente o padrão Strategy (que também
 * foi implementando, dentro do package contexto), resolvi utilizar o Factory também porque:
 * 
 * - A classe Batalha precisa de acesso centralizado a todas as armas disponíveis
 * - É necessário obter nomes e instâncias de armas de forma organizada
 * 
 * 2. ALTERNATIVAS CONSIDERADAS:
 * - Reflection:
 * Apesar de ser automatizado, exige dependências externas e preferi simplificar o projeto.
 * 
 * - Registro Estático (nas próprias armas):
 * Mais simples, mas aumenta o acoplamento.
 */
public class ArmasFactory implements IArmasFactory {
    /** 
     * HashMap usando o nome da arma como Key, e o Supplier (interface funcional com o
     * método get()). Assim, o "esqueleto" das armas é salvo em ARMAS, mas elas não 
     * necessariamente são instanciadas durante a inicialização (ou seja, Supplier para 
     * lazy initialization)
     * 
     * Nota-se que tanto nesse arquivo quanto em PersonagensFactory, o Supplier é 
     * chamado de fábrica não por ser uma fábrica em si (no sentido do padrão), mas sim
     * porque ela carrega a estrutura, a receita, o esqueleto da criação do objeto. 
     */
    private final Map<String, Supplier<Arma<?>>> registroArmas;

    public ArmasFactory() {
        this.registroArmas = new HashMap<>();
        registrarArmasPadrao();
    }

    private void registrarArmasPadrao() {
        registrarArma("EspadaLonga", EspadaLonga::new);
        registrarArma("ArcoElfico", ArcoElfico::new);
        registrarArma("CajadoArcano", CajadoArcano::new);
        registrarArma("MachadoDeGuerra", MachadoDeGuerra::new);
    }
    
    private void registrarArma(String nome, Supplier<Arma<?>> fabrica) {
        registroArmas.put(nome, fabrica);
    }
    
    public List<String> getNomesArmasDisponiveis() {
        List<String> nomes = new ArrayList<>(registroArmas.keySet());
        Collections.sort(nomes); // Ordena alfabeticamente
        return nomes;
    }
    
    public Arma<?> criarArma(String nome) {
        Supplier<Arma<?>> fabrica = registroArmas.get(nome);
        if (fabrica == null) {
            throw new IllegalArgumentException("Arma não encontrada: " + nome);
        }
        return fabrica.get();
    }
}