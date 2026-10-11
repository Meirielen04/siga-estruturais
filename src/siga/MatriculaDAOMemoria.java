package siga;

import java.util.ArrayList;
import java.util.List;

/** Implementação em memória do MatriculaDAO. Pronta. */
public class MatriculaDAOMemoria implements MatriculaDAO {

    private final List<Matricula> armazem = new ArrayList<>();

    @Override
    public void inserir(Matricula matricula) {
        armazem.add(matricula);
    }

    @Override
    public List<Matricula> listarTodas() {
        return new ArrayList<>(armazem);
    }
}
