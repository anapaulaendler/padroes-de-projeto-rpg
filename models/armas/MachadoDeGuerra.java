package models.armas;

import models.personagens.Personagem;
import models.efeitos.Castor;

public class MachadoDeGuerra extends Arma<Castor> {

    public MachadoDeGuerra() {
        super(18, 5, new Castor(), TipoArma.MACHADO, 15, 0.2, 0.25);
    }

    // Requisito: Força ≥ 15
    @Override
    public boolean validarUsoDeArma(Personagem atacador) {
        return atacador.temForcaSuficiente(this.preRequisitoValorMinino) && 
               atacador.armasUtilizaveis.contains(this.tipoArma);
    }
}