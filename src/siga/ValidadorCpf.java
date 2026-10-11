package siga;

/** Peça do subsistema de matrícula. Pronta. */
public class ValidadorCpf {

    public boolean valido(String cpf) {
        if (cpf == null) {
            return false;
        }
        String apenasDigitos = cpf.replaceAll("\\D", "");
        return apenasDigitos.length() == 11;
    }
}
