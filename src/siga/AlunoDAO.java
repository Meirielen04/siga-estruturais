package siga;

import java.util.List;

/** Interface do DAO, estabelecida na Aula 7. Pronta. */
public interface AlunoDAO {

    void inserir(Aluno aluno);

    Aluno buscarPorMatricula(String matricula);

    List<Aluno> listarTodos();
}
