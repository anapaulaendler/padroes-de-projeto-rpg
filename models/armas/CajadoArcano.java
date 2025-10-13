package models.armas;

import models.efeitos.BolaDeFogo;

public class CajadoArcano extends Arma<BolaDeFogo> {

    public CajadoArcano() {
        super(8, 25, new BolaDeFogo());
    }

    @Override
    public boolean temRequisito(int definidor) {
        // Requisito: Inteligência ≥ 12
        return definidor >= 12;
    }

    @Override
    public void atacar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atacar'");
    }
    
}
