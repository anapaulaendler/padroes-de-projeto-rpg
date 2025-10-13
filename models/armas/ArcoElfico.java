package models.armas;

import models.efeitos.ChuvaDeFlechas;

public class ArcoElfico extends Arma<ChuvaDeFlechas> {

    public ArcoElfico() {
        super(12, 15, new ChuvaDeFlechas());
    }

    @Override
    public boolean temRequisito(int definidor) {
        // Requisito: Destreza ≥ 8
        return definidor >= 8;
    }
}
