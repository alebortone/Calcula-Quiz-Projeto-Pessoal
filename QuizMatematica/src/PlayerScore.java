/**
 * Classe para representar a pontuação de um jogador.
 * Ela agrupa as iniciais e o score em um único objeto,
 * facilitando a ordenação e o gerenciamento dos dados.
 */
public class PlayerScore {
    private String iniciais;
    private int score;

    public PlayerScore(String iniciais, int score) {
        this.iniciais = iniciais;
        this.score = score;
    }

    // Métodos para acessar os dados (getters).
    public String getIniciais() {
        return iniciais;
    }

    public int getScore() {
        return score;
    }
}