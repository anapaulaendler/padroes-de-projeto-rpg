package models.armas;
import interfaces.IArma;
import models.efeitos.EfeitoEspecial;

public abstract class Arma<T extends EfeitoEspecial> implements IArma {
    public int danoBase;
    public int custoMana;
    public T efeitoEspecial;

    public Arma(int danoBase, int custoMana, T efeitoEspecial) {
        this.danoBase = danoBase;
        this.custoMana = custoMana;
        this.efeitoEspecial = efeitoEspecial;
    }
}
