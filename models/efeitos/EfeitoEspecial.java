package models.efeitos;

// TODO: Talvez usar uma classe só para EfeitoEspecial e HabilidadePassiva?
public abstract class EfeitoEspecial {
    public String nome;
    public String descricao;
    
    public EfeitoEspecial(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
