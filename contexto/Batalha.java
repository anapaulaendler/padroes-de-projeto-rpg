package contexto;

import models.personagens.Personagem;
import models.armas.Arma;
import models.efeitos.EfeitoEspecial;
import factory.ArmasFactory;

import java.util.List;
import java.util.Scanner;

public class Batalha {
    private Personagem _jogador;
    private Personagem _inimigo;
    private int _turnoAtual;
    private boolean _batalhaAtiva;
    private ArmasFactory _fabricaArmas = new ArmasFactory();
    
    public Batalha(Personagem jogador, Personagem inimigo) {
        this._jogador = jogador;
        this._inimigo = inimigo;
        this._turnoAtual = 1;
        this._batalhaAtiva = true;
    }
    
    public void iniciarBatalha() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("INÍCIO DA BATALHA");
        System.out.println(_jogador.getClass().getSimpleName() + " vs " + _inimigo.getClass().getSimpleName());
        
        while (_batalhaAtiva) {
            System.out.println("\nTURNO " + _turnoAtual);
            
            processarEfeitosTurno();
            
            if (verificarFimDeBatalha()) break;
            
            System.out.println("\nTurno do Jogador");
            executarTurnoJogador(scanner);
            
            if (verificarFimDeBatalha()) break;
            
            processarEfeitosTurno();
            
            if (verificarFimDeBatalha()) break;
            
            System.out.println("\nTurno do Inimigo");
            executarTurnoInimigo();
            
            _turnoAtual++;
        }
        
        scanner.close();
        System.out.println("\nFIM DA BATALHA");
    }
    
    private void processarEfeitosTurno() {
        System.out.println("Processando efeitos...");
        _jogador.processarEfeitos();
        _inimigo.processarEfeitos();
    }
    
    private void executarTurnoJogador(Scanner scanner) {
        System.out.println("Seu status: Vida = " + _jogador.vida + ", Mana = " + _jogador.mana);
        System.out.println("Inimigo: Vida = " + _inimigo.vida);
        
        mostrarEfeitosAtivos();
        
        boolean acaoValida = false;
        while (!acaoValida) {
            System.out.println("\nEscolha uma ação:");
            System.out.println("[1] Atacar");
            System.out.println("[2] Trocar arma");
            System.out.println("[3] Ver armas disponíveis");
            System.out.println("[4] Fugir");
            System.out.print("Opção: ");
            
            int escolha = scanner.nextInt();
            
            switch (escolha) {
                case 1:
                    _jogador.atacar(_inimigo);
                    acaoValida = true;
                    break;
                    
                case 2:
                    trocarArmaJogador(scanner);
                    acaoValida = true;
                    break;
                    
                case 3:
                    mostrarArmasDisponiveis();
                    break;
                    
                case 4:
                    System.out.println("Você fugiu da batalha!");
                    _batalhaAtiva = false;
                    acaoValida = true;
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void trocarArmaJogador(Scanner scanner) {
        System.out.println("\nTrocar Arma");
        mostrarArmasDisponiveis();
        
        System.out.print("Escolha o número da arma: ");
        int escolha = scanner.nextInt();
        
        List<String> armasDisponiveis = _fabricaArmas.getNomesArmasDisponiveis();
        
        if (escolha > 0 && escolha <= armasDisponiveis.size()) {
            String nomeArma = armasDisponiveis.get(escolha - 1);
            try {
                Arma<?> novaArma = _fabricaArmas.criarArma(nomeArma);
                boolean sucesso = _jogador.equiparArma(novaArma);
                if (!sucesso) {
                    System.out.println("Não foi possível equipar " + nomeArma + "!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Número de arma inválido.");
        }
    }
    
    private void mostrarArmasDisponiveis() {
        System.out.println("ARMAS DISPONÍVEIS");
        List<String> armas = _fabricaArmas.getNomesArmasDisponiveis();
        
        if (armas.isEmpty()) {
            System.out.println("Nenhuma arma disponível.");
            return;
        }
        
        for (int i = 0; i < armas.size(); i++) {
            String nomeArma = armas.get(i);
            
            try {
                Arma<?> armaTeste = _fabricaArmas.criarArma(nomeArma);
                boolean podeUsar = armaTeste.validarUsoDeArma(_jogador);
                String status = podeUsar ? "OK" : "X";
                
                System.out.println((i + 1) + ". " + nomeArma + " [" + status + "]");
            } catch (Exception e) {
                System.out.println((i + 1) + ". " + nomeArma + " [Erro]");
            }
        }
    }
    
    private void mostrarEfeitosAtivos() {
        if (!_jogador.efeitosAtivos.isEmpty()) {
            System.out.println("Seus efeitos ativos:");
            for (EfeitoEspecial efeito : _jogador.efeitosAtivos) {
                System.out.println("- " + efeito.nome + " (" + efeito.turnosAtivo + " turnos restantes)");
            }
        }
        if (!_inimigo.efeitosAtivos.isEmpty()) {
            System.out.println("Efeitos ativos no inimigo:");
            for (EfeitoEspecial efeito : _inimigo.efeitosAtivos) {
                System.out.println("- " + efeito.nome + " (" + efeito.turnosAtivo + " turnos restantes)");
            }
        }
    }
    
    private void executarTurnoInimigo() {
        _inimigo.atacar(_jogador);
    }
    
    private boolean verificarFimDeBatalha() {
        if (_jogador.vida <= 0) {
            System.out.println("Você foi derrotado...");
            _batalhaAtiva = false;
            return true;
        }
        
        if (_inimigo.vida <= 0) {
            System.out.println("Você venceu!");            
            _batalhaAtiva = false;
            return true;
        }
        
        return false;
    }
}