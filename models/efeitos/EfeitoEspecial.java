package models.efeitos;

// TODO: Talvez usar uma classe só para EfeitoEspecial e HabilidadePassiva? (Primeiro vou entender o que é HabilidadePassiva... vida de bingueira não é fácil...)
public abstract class EfeitoEspecial {
    public String nome;
    public String descricao;
    public int turnosAtivo;
    public Integer dano; // ... no C# ia ser só um int?... que frescura...

    protected EfeitoEspecial(String nome, String descricao, int turnosAtivo, Integer dano) {
        this.nome = nome;
        this.descricao = descricao;
        this.turnosAtivo = turnosAtivo;
        this.dano = dano;
    }

    protected EfeitoEspecial(String nome, String descricao, int turnosAtivo) {
        this(nome, descricao, turnosAtivo, null);
    }
}
