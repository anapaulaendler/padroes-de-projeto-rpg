package models.personagens;

import java.util.List;

import models.armas.TipoArma;

public abstract class Personagem {
    public int vida;
    public int mana;
    public AtributosBase atributos;
    public String habilidadePassiva;
    public List<TipoArma> armasUtilizaveis;

    protected Personagem(int vida, int mana, AtributosBase atributos, String habilidadePassiva, List<TipoArma> armasUtilizaveis) {
        this.vida = vida;
        this.mana = mana;
        this.atributos = atributos;
        this.habilidadePassiva = habilidadePassiva;
        this.armasUtilizaveis = armasUtilizaveis;
    }

    public boolean temDestrezaSuficiente(int valorMinimo) {
        return this.atributos.destreza >= valorMinimo;
    }

    public boolean temInteligenciaSuficiente(int valorMinimo) {
        return this.atributos.inteligencia >= valorMinimo;
    }

    public boolean temForcaSuficiente(int valorMinimo) {
        return this.atributos.forca >= valorMinimo;
    }
}
