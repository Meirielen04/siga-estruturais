package siga;

/**
 * DESLIZE 1 (parte B) — a MESMA conversão, escrita de novo, em outro lugar.
 *
 * Aqui alguém percebeu que a situação às vezes vem em minúscula e corrigiu.
 * A correção não chegou ao ImportadorAlunos: as duas versões já divergem,
 * e o mesmo aluno aparece ativo em um lugar e inativo no outro.
 */
public class RelatorioSituacao {

    private final SecretariaLegadoWS servicoLegado;

    public RelatorioSituacao(SecretariaLegadoWS servicoLegado) {
        this.servicoLegado = servicoLegado;
    }

    public void imprimir() {
        System.out.println("   Situação segundo o relatório:");
        for (String[] linha : servicoLegado.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[0], linha[1]);
            aluno.setAtivo("A".equalsIgnoreCase(linha[2]));   // aqui trata
            System.out.println("     " + aluno);
        }
    }
}
