package models.armas;
import models.efeitos.CorteProfundo;

public class EspadaLonga extends Arma<CorteProfundo> {

    public EspadaLonga() {
        super(10, 5, new CorteProfundo());
    }

    @Override
    public boolean temRequisito(int definidor) {
        // Requisito: Força ≥ 10
        return definidor >= 10;
    }    
}
