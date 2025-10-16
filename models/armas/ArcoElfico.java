package models.armas;

import models.efeitos.ChuvaDeFlechas;
import models.personagens.Personagem;

public class ArcoElfico extends Arma<ChuvaDeFlechas> {

    public ArcoElfico() {
        super(12, 15, new ChuvaDeFlechas(), TipoArma.ARCO, 8, 0.2, 0.8);
    }
    
    // Inicialmente eu ia usar "return atacador.atributos.destreza >= 8", mas fere o princípio de Demeter :p
    // Requisito: Destreza ≥ 8
    @Override
    public boolean validarUsoDeArma(Personagem atacador) {
        return atacador.temDestrezaSuficiente(this.preRequisitoValorMinino) && atacador.armasUtilizaveis.contains(this.tipoArma);
    }
}
