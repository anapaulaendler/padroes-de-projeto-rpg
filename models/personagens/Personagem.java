package models.personagens;

import java.util.*;

import contexto.ContextoArma;
import models.armas.Arma;
import models.armas.TipoArma;
import models.efeitos.EfeitoEspecial;

public abstract class Personagem {
    public int vida;
    public int mana;
    public AtributosBase atributos;
    public String habilidadePassiva;
    public List<TipoArma> armasUtilizaveis;
    public List<EfeitoEspecial> efeitosAtivos;
    private ContextoArma _contextoArma;
    
    protected Personagem(int vida, int mana, AtributosBase atributos, String habilidadePassiva, List<TipoArma> armasUtilizaveis, Arma<?> armaInicial) {
        this.vida = vida;
        this.mana = mana;
        this.atributos = atributos;
        this.habilidadePassiva = habilidadePassiva;
        this.armasUtilizaveis = armasUtilizaveis;
        this.efeitosAtivos = new ArrayList<>(); // Jogador começa sem efeitos ativos

        this._contextoArma = new ContextoArma(armaInicial);
    }

    //#region ARMAS
    public boolean equiparArma(Arma<?> novaArma) {
        if (!novaArma.validarUsoDeArma(this)) return false;

        _contextoArma.setArma(novaArma);
        System.out.println(this.getClass().getSimpleName() + " equipou " + novaArma.getClass().getSimpleName());
        return true;
    }

    public void atacar(Personagem alvo) {
        if (_contextoArma != null) {
            _contextoArma.atacar(this, alvo);
        } else {
            System.out.println(this.getClass().getSimpleName() + " não tem arma equipada!");
        }
    }
    //#endregion ARMAS

    //#region EFEITOS
    public void adicionarEfeito(EfeitoEspecial efeito) {
        // Se o efeito já existe, renova a duração
        for (EfeitoEspecial efeitoExistente : efeitosAtivos) {
            if (efeitoExistente.getClass().equals(efeito.getClass())) {
                efeitoExistente.turnosAtivo = efeito.turnosAtivo;
                System.out.println("Efeito " + efeito.nome + " renovado em " + this.getClass().getSimpleName());
                return;
            }
        }
        
        // Se não existe, adiciona novo
        this.efeitosAtivos.add(efeito);
        System.out.println(this.getClass().getSimpleName() + " recebeu efeito: " + efeito.nome);
    }
    
    public void processarEfeitos() {
        if (efeitosAtivos.isEmpty()) return;
        
        Iterator<EfeitoEspecial> iterator = efeitosAtivos.iterator();
        List<EfeitoEspecial> efeitosParaRemover = new ArrayList<>();
        
        while (iterator.hasNext()) {
            EfeitoEspecial efeito = iterator.next();
            
            if (efeito.dano > 0) {
                this.vida -= efeito.dano;
                System.out.println(this.getClass().getSimpleName() + " sofreu " + efeito.dano + " de dano de " + efeito.nome);
            }
            
            efeito.turnosAtivo--;
            System.out.println(efeito.nome + " agora tem " + efeito.turnosAtivo + " turnos restantes");
            
            if (efeito.turnosAtivo <= 0) {
                efeitosParaRemover.add(efeito);
                System.out.println(efeito.nome + " expirou em " + this.getClass().getSimpleName());
            }
        }
        
        efeitosAtivos.removeAll(efeitosParaRemover);
    }
    //#endregion EFEITOS

    //#region BOOLEANS
    public boolean temDestrezaSuficiente(int valorMinimo) {
        return this.atributos.destreza >= valorMinimo;
    }

    public boolean temInteligenciaSuficiente(int valorMinimo) {
        return this.atributos.inteligencia >= valorMinimo;
    }

    public boolean temForcaSuficiente(int valorMinimo) {
        return this.atributos.forca >= valorMinimo;
    }
    //#endregion BOOLEANS
}
