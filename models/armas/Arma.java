package models.armas;
import interfaces.IArma;
import models.efeitos.EfeitoEspecial;
import models.personagens.Personagem;

public abstract class Arma<T extends EfeitoEspecial> implements IArma {
    public int danoBase;
    public int custoMana;
    public T efeitoEspecial;
    public TipoArma tipoArma;
    public int preRequisitoValorMinino;

    protected Arma(int danoBase, int custoMana, T efeitoEspecial, TipoArma tipoArma, int preRequisitoValorMinino) {
        this.danoBase = danoBase;
        this.custoMana = custoMana;
        this.efeitoEspecial = efeitoEspecial;
        this.tipoArma = tipoArma;
        this.preRequisitoValorMinino = preRequisitoValorMinino;
    }

    @Override
    public void atacar(Personagem atacador, Personagem vitima) {
        if (!validarUsoDeArma(atacador)) {
            System.out.println(atacador + " não pode usar " + this.getClass().getSimpleName());
            return; 
        }

        atacador.mana -= this.custoMana;
        vitima.vida -= this.danoBase;
    }
}
