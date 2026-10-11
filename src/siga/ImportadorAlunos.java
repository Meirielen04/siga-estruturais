package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * DESLIZE 1 (parte A) — conversão do formato legado feita aqui dentro.
 * DESLIZE 2 — depende da CLASSE CONCRETA SecretariaLegadoWS, e não de uma abstração.
 *
 * Esta classe conhece o formato da matriz do legado: que a coluna 0 é a matrícula,
 * a 1 é o nome e a 2 é a situação em letra. Esse conhecimento deveria estar em um
 * único lugar do sistema — e não está (ver RelatorioSituacao).
 */
public class ImportadorAlunos {

    private final SecretariaLegadoWS servicoLegado;
    private final AlunoDAO dao;

    public ImportadorAlunos(SecretariaLegadoWS servicoLegado, AlunoDAO dao) {
        this.servicoLegado = servicoLegado;
        this.dao = dao;
    }

    public int importar() {
        List<Aluno> alunos = new ArrayList<>();

        for (String[] linha : servicoLegado.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[0], linha[1]);
            aluno.setAtivo("A".equals(linha[2]));   // não trata a letra minúscula
            alunos.add(aluno);
        }

        int importados = 0;
        for (Aluno aluno : alunos) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
