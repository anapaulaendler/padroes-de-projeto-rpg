package contexto;

import models.armas.Arma;
import models.personagens.Personagem;

/**
 * Contexto do Strategy Pattern para gerenciar estratégias de ataque (Armas).
 * 
 * 1. RESPONSABILIDADE: 
 * Delegar o comportamento de ataque para estratégias concretas.
 * 
 * 2. STRATEGY PATTERN:
 * - Context: Esta classe (ContextoArma)
 * - Strategy: Interface Arma  
 * - Concrete: EspadaLonga, ArcoElfico, etc.
 * 
 * 3. BENEFÍCIO: 
 * Personagem pode trocar estratégias (armas) em tempo de execução
 * sem alterar sua estrutura interna.
 */
public class ContextoArma {
    private Arma<?> _arma;

    public ContextoArma(Arma<?> arma) {
        this.setArma(arma);
    }

    public void setArma(Arma<?> arma) {
        this._arma = arma;
    }

    public void atacar(Personagem atacador, Personagem vitima) { 
        if (!_arma.validarUsoDeArma(atacador)) {
            System.out.println(atacador.getClass().getSimpleName() + " não pode usar " + _arma.getClass().getSimpleName());
            return;
        }
        _arma.atacar(atacador, vitima);
    }
}