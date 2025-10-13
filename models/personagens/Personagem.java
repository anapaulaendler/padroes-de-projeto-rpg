package models.personagens;

import java.util.List;

import models.armas.TipoArma;

public abstract class Personagem {
    public int vida;
    public int mana;
    public AtributosBase atributos;
    public String habilidadePassiva; // TODO: 1. Mudar para uma classe HabilidadePassiva 2. Considerar juntar com EfeitosEspeciais 3. ?? E precisa de EfeitoEspecial? Será que um String não resolve?
    public List<TipoArma> armasUtilizaveis;

    public Personagem(int vida, int mana, AtributosBase atributos, String habilidadePassiva, List<TipoArma> armasUtilizaveis) {
        this.vida = vida;
        this.mana = mana;
        this.atributos = atributos;
        this.habilidadePassiva = habilidadePassiva;
        this.armasUtilizaveis = armasUtilizaveis;
    }
}
