import java.time.LocalDateTime;
import java.util.Scanner;

// Classe central que gerencia a lógica do Quiz.
public class Quiz {
    private final static int NUMQUESTOES = 5; // Constante para facilitar a manutenção do número de questões.
    private Questao[] questao;
    private Correcao[] correcao;
    private Pontuacao pontuacao;
    private int level;
    private int acertos;

    public Quiz(int level) {

        this.level = level;
        questao = new Questao[NUMQUESTOES];
        correcao = new Correcao[NUMQUESTOES];

        // Prepara o quiz, criando as questões com base no nível de dificuldade.
        for (int i = 0; i < NUMQUESTOES; i++) {
            questao[i] = new Questao(i+1, level);

        }

    }

    // Método que executa o quiz, fazendo as perguntas e coletando as respostas.
    public void aplicar(Scanner sc) {
        LocalDateTime t1 = LocalDateTime.now();// Marca o tempo de início.

        // Loop que "chama" as questões que já foram criadas
        System.out.println("### Quiz Nivel: " + level + " ###\n");
        for (int i = 0; i < NUMQUESTOES; i++) {
            System.out.println();
            System.out.println("Questão " + questao[i].getIdQuestao() + ":");
            System.out.println(questao[i].exibirEnunciado());

            int tentativas = 0;
            
            boolean acertou = false;

            // Loop que controla a lógica de até 2 tentativas por questão.
            while (tentativas < 2 && !acertou) {

                int resposta = sc.nextInt();
                tentativas++;

                if (questao[i].resposta(resposta)) {
                    acertou = true;
                    acertos ++;  // Incrementa o contador de acertos do quiz.
                    System.out.println("Você acertou na tentativa " + tentativas + "!");
                } else if (tentativas < 2) {
                    System.out.println("Você errou, tem apenas mais uma chance! Tente novamente:");
                } else {
                    System.out.println("Você errou as 2 tentativas. A resposta correta era: " + questao[i].getGabarito());
                }

            }
             // Registra o resultado da questão (acerto/erro e tentativas).
            correcao[i] = new Correcao(questao[i].getIdQuestao(), acertou, tentativas);
            
        }
        System.out.println();
        LocalDateTime t2 = LocalDateTime.now(); // Marca o tempo de fim.
        pontuacao = new Pontuacao(t1, t2, level);
       
    }

    // retorna a pontuação de acordo com os acertos. Metodo feito para evitar duplicação de conta e codigo.
    public int getPontosFinais(){
        
        return pontuacao.contarPontos(acertos);
    }

    // Exibe os resultados finais de forma organizada.
    public void gerarRelatorio(int pontos) {
        Menu.limparTela();
        // O printf formata o texto para que seja exibido como uma tabela alinhada.
        System.out.printf("%-10s %-10s %-10s\n","Questão",   "Situação",   "Num Tentativas"); 
        for (int i = 0; i < NUMQUESTOES; i++) {
            System.out.println("------------------------------------");
           System.out.printf("%-8d | %-8s | %10d\n", 
                                    correcao[i].getId(), 
                                    correcao[i].verificarSituacao(), 
                                    correcao[i].getTentativas());
            
        }
        System.out.println();
        
        System.out.println("Chegou ao fim, seu tempo de quiz foi: " + pontuacao.getTotalTempo().toSeconds() + " segundos.\nSeu score foi de " + pontos + " pontos\n");
           
    }

    //salva o score e mostra a tabela já atualizada
    public void guardarScore (String iniciais, int pontos){

        Ranking.salvarScore(level, iniciais, pontos);
        Ranking.mostrarRanking(level);
    }

}