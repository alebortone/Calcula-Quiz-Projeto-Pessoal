import java.time.Duration;
import java.time.LocalDateTime;

// Classe responsável por calcular a pontuação final.
public class Pontuacao {

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Duration totalTempo;
    private int peso;

    public Pontuacao(LocalDateTime inicio, LocalDateTime fim, int peso) {

        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;
        totalTempo = Duration.between(inicio, fim); // Calcula o tempo exato decorrido entre o início e o fim da prova.
    }

    // Calcula o score final com base nos acertos, no peso do nível e em um bônus de tempo.
    public int contarPontos(int acertos) {

        double bonusTempo = 0;
        int pontosBase = 0;

        if (acertos ==0){
            return 0;
        }

        if (peso == 1 && acertos > 0) {
            pontosBase = acertos*20;
            bonusTempo += 1000 - (int)(totalTempo.toSeconds() * 10); // faz com que 10 segundos para baixo seja o tempo perfeito
        } else if (peso == 2) {
            pontosBase = acertos*60;
            bonusTempo += 1000 -(int) (totalTempo.toSeconds() * 5); // faz com que 60 segundos para baixo seja o tempo perfeito
        } else {
            pontosBase = acertos*100;
            bonusTempo += 1000 - (int)(totalTempo.toSeconds() * 2); // faz com que 250 segundos para baixo seja o tempo perfeito
        }
        // O criterio usado foi: para questões mais faceis o concluir rápido é mais valioso, já para questões mais difíceis o acerto é o que mais vale .
        
        
        bonusTempo *= acertos/5.0; // dessa forma eu faço uma ponderação do bonus de tempo com a quantidade de acertos, caso não seja 5, não sera o bonus maximo
        int score = (int) (pontosBase + bonusTempo);  // A pontuação é a soma dos acertos + bônus de tempo.
        
        if(score >1000){
            score =1000;  // o score perfeito é 1000, sendo possivel de alcançar no tempo perfeito e acertando todas as questões
        } else if( score < 0){
            score = pontosBase; // previne que a pontuação seja negativa e que mesmo demorando o usuario so perde o bonus.
        }
       
        return score;
    }

    public Duration getTotalTempo(){
        return totalTempo;

    }
}
