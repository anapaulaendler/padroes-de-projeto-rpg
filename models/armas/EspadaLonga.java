package models.armas;
import models.efeitos.CorteProfundo;
import models.personagens.Personagem;

public class EspadaLonga extends Arma<CorteProfundo> {

    public EspadaLonga() {
        super(10, 5, new CorteProfundo(), TipoArma.ESPADA, 10, 0.3, 0.7);
    }

    // Requisito: Força ≥ 10
    @Override
    public boolean validarUsoDeArma(Personagem atacador) {
        return atacador.temForcaSuficiente(this.preRequisitoValorMinino) && atacador.armasUtilizaveis.contains(this.tipoArma);
    }   
}
