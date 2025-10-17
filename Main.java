import factory.ArmasFactory;
import factory.PersonagensFactory;
import models.personagens.Personagem;
import contexto.Batalha;

import java.util.List;
import java.util.Scanner;

/**
 *  SEPARAÇÃO DE RESPONSABILIDADES:
 *  
 * - Batalha: Gerencia o fluxo de combate por turnos
 * - Personagem: Estado do personagem e delegação de ataques (Strategy Context)
 * - ContextoArma: Interface de estratégia de ataque 
 * - Armas: Implementações concretas de estratégias (Concrete Strategies)
 * - ArmasFactory: Fornecedor de estratégias disponíveis (não parte do Strategy)
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            
        System.out.println("JOGO DE RPG: PADRÕES DE PROJETO");
        
        ArmasFactory fabricaArmas = new ArmasFactory();
        PersonagensFactory fabricaPersonagens = new PersonagensFactory();

        Personagem jogador = selecionarPersonagem(scanner, fabricaPersonagens);
        
        // Seleção aleatória do inimigo. Garante que não seja o mesmo do jogador.
        Personagem inimigo = fabricaPersonagens.criarPersonagemAleatorio();
        while (inimigo.getClass().getSimpleName().equals(jogador.getClass().getSimpleName())) {
            inimigo = fabricaPersonagens.criarPersonagemAleatorio();
        }
        
        System.out.println("\nSeu inimigo vai ser: " + inimigo.getClass().getSimpleName());
        System.out.println("   " + fabricaPersonagens.getDescricaoPersonagem(inimigo.getClass().getSimpleName()));
        
        // Mostra armas disponíveis
        System.out.println("\nARMAS DISPONÍVEIS NO JOGO:");
        fabricaArmas.getNomesArmasDisponiveis().forEach(arma -> 
            System.out.println("  - " + arma)
        );
        
        System.out.println("\nA batalha vai começar! Pressione ENTER para continuar...");
        scanner.nextLine();
        
        Batalha batalha = new Batalha(jogador, inimigo);
        batalha.iniciarBatalha();
        
        scanner.close();
    }
    
    private static Personagem selecionarPersonagem(Scanner scanner, PersonagensFactory fabrica) {
        System.out.println("\nSELECIONE SEU PERSONAGEM:");
        
        List<String> personagensDisponiveis = fabrica.getNomesPersonagensDisponiveis();
        
        for (int i = 0; i < personagensDisponiveis.size(); i++) {
            String nomePersonagem = personagensDisponiveis.get(i);
            String descricao = fabrica.getDescricaoPersonagem(nomePersonagem);
            System.out.println((i + 1) + ". " + descricao);
        }
        
        System.out.println((personagensDisponiveis.size() + 1) + ". Personagem Aleatório");
        
        Personagem personagemSelecionado = null;
        
        while (personagemSelecionado == null) {
            System.out.print("\nEscolha o número do personagem: ");
            
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();
                
                if (escolha > 0 && escolha <= personagensDisponiveis.size()) {
                    String nomePersonagem = personagensDisponiveis.get(escolha - 1);
                    personagemSelecionado = fabrica.criarPersonagem(nomePersonagem);
                    System.out.println("Você escolheu: " + nomePersonagem);
                } 
                else if (escolha == personagensDisponiveis.size() + 1) {
                    personagemSelecionado = fabrica.criarPersonagemAleatorio();
                    System.out.println("Personagem aleatório: " + personagemSelecionado.getClass().getSimpleName());
                }
                else {
                    System.out.println("Número inválido! Tente novamente...");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite um número...");
                scanner.nextLine(); 
            }
        }
        
        return personagemSelecionado;
    }
}