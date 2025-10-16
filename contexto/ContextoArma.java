package contexto;

import models.armas.Arma;
import models.personagens.Personagem;

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