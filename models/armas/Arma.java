package models.armas;
import java.util.Random;

import interfaces.IArma;
import models.efeitos.EfeitoEspecial;
import models.personagens.Personagem;

public abstract class Arma<T extends EfeitoEspecial> implements IArma {
    public int danoBase;
    public int custoMana;
    public T efeitoEspecial;
    public TipoArma tipoArma;
    public int preRequisitoValorMinino;
    public double chanceAcertoCritico;
    public double chanceAplicarEfeito;

    protected Arma(int danoBase, int custoMana, T efeitoEspecial, TipoArma tipoArma, int preRequisitoValorMinino, double chanceAcertoCritico, double chanceAplicarEfeito) {
        this.danoBase = danoBase;
        this.custoMana = custoMana;
        this.efeitoEspecial = efeitoEspecial;
        this.tipoArma = tipoArma;
        this.preRequisitoValorMinino = preRequisitoValorMinino;
        this.chanceAcertoCritico = chanceAcertoCritico;
        this.chanceAplicarEfeito = chanceAplicarEfeito; 
    }

    @Override
    public void atacar(Personagem atacador, Personagem vitima) {
        if (!validarUsoDeArma(atacador)) {
            System.out.println(atacador + " não pode usar " + this.getClass().getSimpleName());
            return; 
        }

        atacador.mana -= this.custoMana;

        int danoFinal = calcularDanoComCritico(this.danoBase);
        vitima.vida -= danoFinal;

        tentarAplicarEfeito(vitima);
    }

    //#region UTILS
    private int calcularDanoComCritico(int danoBase) {
        Random random = new Random();
        if (random.nextDouble() < this.chanceAcertoCritico) {
            int danoCritico = (int)(danoBase * 1.5);
            System.out.println("Crítico! Dano aumentado para " + danoCritico + "!");
            return danoCritico;
        }
        return danoBase;
    }

    private void tentarAplicarEfeito(Personagem vitima) {
        if (this.efeitoEspecial != null) {
            Random random = new Random();
            if (random.nextDouble() < this.chanceAplicarEfeito) {
                EfeitoEspecial efeitoParaAplicar = this.efeitoEspecial.clone();
                vitima.adicionarEfeito(efeitoParaAplicar);
                
                System.out.println("Efeito " + efeitoParaAplicar.nome + " aplicado em " + 
                                vitima.getClass().getSimpleName() + " por " + 
                                efeitoParaAplicar.turnosAtivo + " turnos");
            }
        }
    }
    //#endregion UTILS

}
