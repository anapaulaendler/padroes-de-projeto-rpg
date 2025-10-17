package models.personagens;

import java.util.Arrays;
import models.armas.TipoArma;
import models.armas.MachadoDeGuerra;

public class Paladino extends Personagem {
    
    public Paladino() {
        super(110, 70, new AtributosBase(15, 9, 12), "Devoção Divina", 
              Arrays.asList(TipoArma.ESPADA, TipoArma.MACHADO, TipoArma.CAJADO),
              new MachadoDeGuerra());
    }

    @Override
    public void atacar(Personagem alvo) {
        super.atacar(alvo);
        ativarDevocaoDivina();
    }

    private void ativarDevocaoDivina() {
        java.util.Random random = new java.util.Random();
        if (random.nextDouble() < 0.3) { 
            int cura = (int)(110 * 0.1); 
            this.vida += cura;
            System.out.println("Paladino cura " + cura + " de vida!");
        }
    }
}