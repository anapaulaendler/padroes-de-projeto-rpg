package models.efeitos;

/** Baseando-se na descrição inicial das classes, como:
 *  
 * "Espada Longa
 * Dano Base: 15
 * Efeito Especial: "Corte Profundo" - Chance de 30% de causar sangramento (dano 
 * adicional de 5 por 3 turnos)
 * Custo Mana: 0
 * Requisito: Força ≥ 10"
 * 
 * Imagina-se que a segunda parte da implementação, "Efeitos Especiais: 1. Implemente o 
 * sistema de StatusEffect" esteja se referindo à própria classe EfeitoEspecial.
 * 
 * Por estar na descrição iniciais da classe, parte da implementação (definição de 
 * classes) já tinha sido feita na primeira etapa, porém a criação da lógica de utilização
 * e a utilização em si foram implementadas na segunda etapa.
*/
public abstract class EfeitoEspecial implements Cloneable {
    public String nome;
    public String descricao;
    public int turnosAtivo;
    public int dano;

    protected EfeitoEspecial(String nome, String descricao, int turnosAtivo, int dano) {
        this.nome = nome;
        this.descricao = descricao;
        this.turnosAtivo = turnosAtivo;
        this.dano = dano;
    }
    
    @Override
    public EfeitoEspecial clone() {
        try {
            return (EfeitoEspecial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar efeito especial", e);
        }
    }
}
