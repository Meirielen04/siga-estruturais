package siga;

/** Peça do subsistema de matrícula. Pronta (apenas imprime no console). */
public class NotificadorEmail {

    public void enviarConfirmacao(Aluno aluno, Matricula matricula) {
        System.out.println("   [e-mail] Confirmação enviada a " + aluno.getNome()
                + ": " + matricula);
    }
}
