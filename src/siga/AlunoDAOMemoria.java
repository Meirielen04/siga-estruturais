package siga;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Implementação em memória, concluída na Aula 8. Pronta. */
public class AlunoDAOMemoria implements AlunoDAO {

    private final Map<String, Aluno> armazem = new LinkedHashMap<>();

    @Override
    public void inserir(Aluno aluno) {
        if (armazem.containsKey(aluno.getMatricula())) {
            throw new IllegalStateException(
                    "Já existe aluno com a matrícula " + aluno.getMatricula());
        }
        armazem.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return armazem.get(matricula);
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(armazem.values());
    }
}
