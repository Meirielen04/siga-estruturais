# SIGA — Atividade de padrões estruturais: Adapter e Facade (código final)

**Técnicas de Programação II (TP2) · Aula 10** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Este é o **código final** da atividade prática da Aula 10. 

## Estrutura do projeto

```
siga-estruturais/
└── src/
    └── siga/
        ├── Aluno.java                 (entidade de domínio; pronta)
        ├── Matricula.java             (entidade de domínio; pronta)
        ├── AlunoDAO.java              (interface da Aula 7; pronta)
        ├── AlunoDAOMemoria.java       (implementação da Aula 8; pronta)
        ├── MatriculaDAO.java          (interface; pronta)
        ├── MatriculaDAOMemoria.java   (implementação; pronta)
        ├── SecretariaLegadoWS.java    (COMPONENTE EXTERNO — não alterar)
        ├── ValidadorCpf.java          (subsistema de matrícula; pronta)
        ├── ControleVagas.java         (subsistema de matrícula; pronta)
        ├── CalculadoraDesconto.java   (subsistema de matrícula; pronta)
        ├── NotificadorEmail.java      (subsistema de matrícula; pronta)
        ├── ImportadorAlunos.java      (deslizes 1A e 2 — corrigido)
        ├── RelatorioSituacao.java     (deslize 1B — corrigido)
        ├── FonteDeAlunos.java         (Adapter — interface)
        ├── AdaptadorSecretariaLegado.java (Adapter — implementação)
        ├── TelaMatricula.java         (deslize 3 — corrigido)
        ├── FachadaMatricula.java      (Facade)
        └── Main.java                  (demonstra os três deslizes)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior (`java -version` para verificar).

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -encoding UTF-8 -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

Execute antes de ler o restante deste arquivo: a saída **mostra os três deslizes acontecendo**.

## Os três deslizes propositais resolvidos

| # | Deslize | Onde | Por que é um problema |
|---|---|---|---|
| 1 | **Conversão duplicada e divergente** | `ImportadorAlunos` e `RelatorioSituacao` | O conhecimento do formato legado (coluna 0 é matrícula, 2 é a situação em letra) foi escrito duas vezes. Alguém corrigiu o tratamento da letra minúscula em um dos dois e não no outro: a aluna `2024003` aparece **inativa** no importador e **ativa** no relatório. |
| 2 | **Dependência de classe concreta** | `ImportadorAlunos` | O importador recebe `SecretariaLegadoWS` no construtor. Trocar a origem dos alunos exige alterar o importador, e testá-lo exige o serviço externo. Violação do DIP (Aula 3). |
| 3 | **Subsistema exposto à apresentação** | `TelaMatricula` | A tela recebe **seis colaboradores** e conhece a ordem correta de chamá-los — reservar a vaga só depois de gravar a matrícula. Essa ordem é regra de negócio e não deveria morar na tela. |

## Etapa 1 - Localização da duplicação

Os dois trechos duplicados estão em `ImportadorAlunos` com `importar` e `RelatorioSituacao` com `imprimir()`, ambos percorrem `consultarTabelaAlunos()` e criam um aluno, só que apenas `RelatorioSituacao` trata para ignorar letra minuscula com `equalsIgnoreCase`. Os dois conhecem o que cada coluna faz no formato legado, que `linha[0]` é referente a matrícula, `linha[1]` é nome e `linha[2]` com letra `A` é ativo e `I` inativo; mas em `RelatorioSituacao` aceita `A` e `a`. Na saida a situação da aluna Ana sai como ativo no relatório e inativo no importador, tendo como consequência uma inconsistência.

## Etapa 5 - Justificativa dos padrões

### Adapter - `ImportadorAlunos` e `RelatorioSituacao`

O problema: as duas classes conheciam direto a `SecretariaLegadoWS` e cada uma traduzia o formato legado do seu jeito - por isso as versões divergiram. Criei a interface `FonteDeAlunos` e a classe `AdaptadorSecretariaLegado`, que faz essa tradução uma única vez. Isso é um **Adapter**: adapta algo que não pode ser mudado para o formato que o resto do sistema espera.

### Facade - `TelaMatricula`

O problema: a tela conhecia os seis colaboradores da matrícula e a ordem certa de chamá-los, que é regra de negócio e não deveria estar na tela. Criei a `FachadaMatricula`, com um único método `matricular(...)` que faz essa orquestração por dentro. Isso é uma **Facade**: esconde um subsistema complexo atrás de uma interface simples.

## Tarefas realizadas

Siga as etapas da ficha de atividade prática:

1. **Localizar a duplicação.** Identifique os dois trechos que convertem o formato legado e descreva, neste README, em que ponto exato eles divergem.
2. **Criar o Adapter.** Escreva a interface `FonteDeAlunos`, com o método `List<Aluno> listar()`, e a classe `AdaptadorSecretariaLegado`, que a implementa recebendo `SecretariaLegadoWS` por composição. A conversão deve passar a existir em **um único lugar**, com a regra da letra minúscula aplicada uma vez só.
3. **Refatorar o cliente.** `ImportadorAlunos` deve passar a receber `FonteDeAlunos`, e não mais a classe legada. Ao terminar, a palavra `SecretariaLegadoWS` não deve aparecer em nenhum lugar do importador. Ajuste `RelatorioSituacao` para consumir a mesma fonte.
4. **Criar a Facade.** Escreva `FachadaMatricula` com a operação `matricular(String matricula, String cpf, String codigoTurma)`, movendo para ela a orquestração hoje presente na tela. `TelaMatricula` deve passar a receber **um** colaborador e a conter apenas a chamada e o tratamento de erro da interface.
5. **Justificar.** Explique neste README por que cada caso pediu o padrão escolhido, descrevendo **o problema** antes de nomear o padrão.

## Critério de sucesso

Ao final, verifique que: (a) a situação da aluna `2024003` é a **mesma** nos dois pontos do sistema; (b) `ImportadorAlunos` não menciona `SecretariaLegadoWS`; (c) `TelaMatricula` tem **um** atributo colaborador, não seis; (d) `SecretariaLegadoWS` continua **exatamente como veio**; e (e) o programa continua produzindo as mesmas matrículas e os mesmos erros de antes.

> O último item é importante: uma refatoração estrutural bem feita **não muda o comportamento externo** do sistema. Se a saída mudou — fora a correção da divergência do deslize 1 —, algo se perdeu no caminho.

## Padrão de entrega

Conforme a ficha de atividade prática: identificadores em português, um arquivo `.java` por classe pública, código formatado, entrega no repositório Git com README e commits descritivos. O uso de IA para gerar o código é proibido nesta atividade (ver seção 5.3 da ficha).

> **Projeto Integrador:** a Etapa 2, com entrega na Aula 13, exige a aplicação justificada de ao menos um padrão estrutural. O que você produzir aqui pode ser aproveitado nela — desde que a justificativa descreva o problema do SIGA, e não apenas o nome do padrão.
