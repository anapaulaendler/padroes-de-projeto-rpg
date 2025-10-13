package models.armas;
import interfaces.IArma;
import models.efeitos.EfeitoEspecial;
import models.personagens.Personagem;

public abstract class Arma<T extends EfeitoEspecial> implements IArma {
    public int danoBase;
    public int custoMana;
    public T efeitoEspecial;

    protected Arma(int danoBase, int custoMana, T efeitoEspecial) {
        this.danoBase = danoBase;
        this.custoMana = custoMana;
        this.efeitoEspecial = efeitoEspecial;
    }

    @Override
    public void atacar(Personagem atacador, Personagem atacado) {
        atacador.mana -= this.custoMana;
        atacado.vida -= this.danoBase;
    }
}
