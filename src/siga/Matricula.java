package siga;

/** Entidade de domínio. Pronta — não precisa ser alterada nesta atividade. */
public class Matricula {

    private final Aluno aluno;
    private final String codigoTurma;
    private final double desconto;

    public Matricula(Aluno aluno, String codigoTurma, double desconto) {
        this.aluno = aluno;
        this.codigoTurma = codigoTurma;
        this.desconto = desconto;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public double getDesconto() {
        return desconto;
    }

    @Override
    public String toString() {
        return aluno.getNome() + " em " + codigoTurma
                + " (desconto de " + (int) (desconto * 100) + "%)";
    }
}
