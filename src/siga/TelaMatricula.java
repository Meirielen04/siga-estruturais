package siga;

/**
 * DESLIZE 3 — a camada de apresentação conhece as SEIS classes do subsistema
 * e, pior, conhece a ORDEM correta de chamá-las, que é regra de negócio.
 *
 * Quando a secretaria pedir matrícula em lote, estas linhas serão copiadas —
 * e, copiadas, divergirão.
 */
public class TelaMatricula {

    private final ValidadorCpf validadorCpf;
    private final AlunoDAO alunoDAO;
    private final ControleVagas controleVagas;
    private final CalculadoraDesconto calculadoraDesconto;
    private final MatriculaDAO matriculaDAO;
    private final NotificadorEmail notificador;

    public TelaMatricula(ValidadorCpf validadorCpf, AlunoDAO alunoDAO,
                         ControleVagas controleVagas,
                         CalculadoraDesconto calculadoraDesconto,
                         MatriculaDAO matriculaDAO,
                         NotificadorEmail notificador) {
        this.validadorCpf = validadorCpf;
        this.alunoDAO = alunoDAO;
        this.controleVagas = controleVagas;
        this.calculadoraDesconto = calculadoraDesconto;
        this.matriculaDAO = matriculaDAO;
        this.notificador = notificador;
    }

    public void aoClicarEmMatricular(String matricula, String cpf, String codigoTurma) {
        try {
            if (!validadorCpf.valido(cpf)) {
                throw new IllegalArgumentException("CPF inválido.");
            }
            Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
            if (aluno == null) {
                throw new IllegalStateException("Aluno não encontrado: " + matricula);
            }
            if (!controleVagas.haVaga(codigoTurma)) {
                throw new IllegalStateException("Turma sem vagas: " + codigoTurma);
            }

            double desconto = calculadoraDesconto.calcular(aluno);
            Matricula nova = new Matricula(aluno, codigoTurma, desconto);

            matriculaDAO.inserir(nova);
            controleVagas.reservar(codigoTurma);
            notificador.enviarConfirmacao(aluno, nova);

            System.out.println("   [tela] Matrícula realizada: " + nova);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("   [tela] Erro: " + e.getMessage());
        }
    }
}
