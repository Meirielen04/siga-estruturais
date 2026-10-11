package siga;

import java.util.HashMap;
import java.util.Map;

/** Peça do subsistema de matrícula. Pronta. */
public class ControleVagas {

    private final Map<String, Integer> vagasPorTurma = new HashMap<>();

    public ControleVagas() {
        vagasPorTurma.put("DSM-2A", 2);
        vagasPorTurma.put("DSM-2B", 0);
    }

    public boolean haVaga(String codigoTurma) {
        return vagasPorTurma.getOrDefault(codigoTurma, 0) > 0;
    }

    public void reservar(String codigoTurma) {
        int restantes = vagasPorTurma.getOrDefault(codigoTurma, 0);
        if (restantes <= 0) {
            throw new IllegalStateException("Sem vagas em " + codigoTurma);
        }
        vagasPorTurma.put(codigoTurma, restantes - 1);
    }
}
