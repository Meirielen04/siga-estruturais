package siga;

/** Entidade de domínio. Pronta — não precisa ser alterada nesta atividade. */
public class Aluno {

    private final String matricula;
    private final String nome;
    private boolean ativo;
    private boolean bolsista;

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.ativo = true;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isBolsista() {
        return bolsista;
    }

    public void setBolsista(boolean bolsista) {
        this.bolsista = bolsista;
    }

    @Override
    public String toString() {
        return matricula + " - " + nome + (ativo ? " (ativo)" : " (inativo)");
    }
}
