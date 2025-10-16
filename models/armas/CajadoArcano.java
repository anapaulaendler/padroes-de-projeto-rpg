package models.armas;

import models.efeitos.BolaDeFogo;
import models.personagens.Personagem;

public class CajadoArcano extends Arma<BolaDeFogo> {

    public CajadoArcano() {
        super(8, 25, new BolaDeFogo(), TipoArma.CAJADO, 12, 0.5, 0.5);
    }

    // Requisito: Inteligência ≥ 12
    @Override
    public boolean validarUsoDeArma(Personagem atacador) {
        return atacador.temInteligenciaSuficiente(this.preRequisitoValorMinino) && atacador.armasUtilizaveis.contains(this.tipoArma);
    }   
}
