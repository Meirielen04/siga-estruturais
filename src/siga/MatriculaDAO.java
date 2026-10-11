package siga;

import java.util.List;

/** Porta de persistência das matrículas. Pronta. */
public interface MatriculaDAO {

    void inserir(Matricula matricula);

    List<Matricula> listarTodas();
}
