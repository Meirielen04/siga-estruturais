package siga;

/**
 * COMPONENTE EXTERNO — simula o serviço da secretaria, escrito por outra equipe.
 *
 * NÃO ALTERE ESTA CLASSE. Ela representa uma dependência de terceiros cujo
 * código-fonte você não controla: a tarefa é adaptá-la, não modificá-la.
 *
 * Formato devolvido: cada linha é {matricula, nomeCompleto, situacao},
 * em que a situação vem como "A" (ativo) ou "I" (inativo) — e, por descuido
 * do sistema de origem, às vezes em minúscula.
 */
public class SecretariaLegadoWS {

    public String[][] consultarTabelaAlunos() {
        return new String[][] {
            {"2024001", "Maria Silva",    "A"},
            {"2024002", "Joao Souza",     "I"},
            {"2024003", "Ana Pereira",    "a"},
            {"2024004", "Carlos Almeida", "A"}
        };
    }
}
