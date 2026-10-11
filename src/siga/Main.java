package siga;

/**
 * Demonstra os três deslizes acontecendo. Execute e observe a saída:
 * é dela que partem as etapas da ficha de atividade prática.
 */
public class Main {

    public static void main(String[] args) {
        SecretariaLegadoWS servicoLegado = new SecretariaLegadoWS();
        AlunoDAO alunoDAO = new AlunoDAOMemoria();

        System.out.println("=== SIGA — Aula 10: Adapter e Facade (código inicial) ===");

        System.out.println();
        System.out.println("1) Importação a partir do sistema legado");
        ImportadorAlunos importador = new ImportadorAlunos(servicoLegado, alunoDAO);
        System.out.println("   Alunos importados: " + importador.importar());
        System.out.println("   Situação segundo o importador:");
        for (Aluno aluno : alunoDAO.listarTodos()) {
            System.out.println("     " + aluno);
        }

        System.out.println();
        System.out.println("2) O mesmo dado, convertido por outro trecho do sistema");
        new RelatorioSituacao(servicoLegado).imprimir();
        System.out.println("   >> DESLIZE 1: a aluna 2024003 aparece INATIVA acima e ATIVA aqui.");
        System.out.println("      A conversão existe em dois lugares, e as versões divergiram.");
        System.out.println("   >> DESLIZE 2: o importador depende da classe concreta do legado.");

        System.out.println();
        System.out.println("3) Matrícula pela tela");
        Aluno bolsista = alunoDAO.buscarPorMatricula("2024001");
        bolsista.setBolsista(true);

        TelaMatricula tela = new TelaMatricula(
                new ValidadorCpf(),
                alunoDAO,
                new ControleVagas(),
                new CalculadoraDesconto(),
                new MatriculaDAOMemoria(),
                new NotificadorEmail());

        tela.aoClicarEmMatricular("2024001", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024002", "111", "DSM-2A");
        tela.aoClicarEmMatricular("2024009", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024004", "123.456.789-09", "DSM-2B");
        System.out.println("   >> DESLIZE 3: a tela precisou receber SEIS colaboradores");
        System.out.println("      e conhecer a ordem das chamadas, que é regra de negócio.");

        System.out.println();
        System.out.println("Sua tarefa: ver o README.md e a ficha de atividade prática.");
    }
}
