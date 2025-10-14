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
        _arma.atacar(atacador, vitima);
    }
}
