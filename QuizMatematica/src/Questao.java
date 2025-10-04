import java.util.Random;

// Classe que modela uma única questão do quiz.
public class Questao {

    private String enunciado;
    private int gabarito;
    private int idQuestao;

    // Construtor que gera números aleatórios para o enunciado com base na dificuldade.
    public Questao(int id, int n) {
        Random numAleatorio = new Random();

        int num1, num2;

        if (n == 1) {
            num1 = numAleatorio.nextInt(8) + 2;
            num2 = numAleatorio.nextInt(8) + 2;

        } else if (n == 2) {
            num1 = numAleatorio.nextInt(40) + 10;
            num2 = numAleatorio.nextInt(15) + 5;

        } else {
            num1 = numAleatorio.nextInt(50) + 50;
            num2 = numAleatorio.nextInt(30) + 20;

        }

        enunciado = String.format("Quanto é %d * %d ? ", num1, num2);
        gabarito = num1 * num2;
        idQuestao = id;

    }

    public String exibirEnunciado() {

        return enunciado;

    }
    
    // Verifica de forma concisa se a resposta do usuário está correta.
    public boolean resposta(int resposta) {
        return resposta == gabarito;
    }

    public int getGabarito() {

        return gabarito;
    }

    public int getIdQuestao() {

        return idQuestao;
    }

}
