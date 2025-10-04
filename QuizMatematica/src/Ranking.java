import java.io.*;
import java.util.*;

// Classe utilitária para gerenciar a leitura e escrita dos scores em arquivos.
public class Ranking {

    // Método auxiliar para padronizar os nomes dos arquivos de ranking.
    private static String getFileName(int nivel) {
        return "ranking" + nivel + ".txt";
    }

    // Salva o score de um jogador no arquivo do nível correspondente.
    public static void salvarScore(int nivel, String iniciais, int score) {
        String fileName = getFileName(nivel);

        // O bloco try-with-resources garante que os arquivos são fechados automaticamente.
        try (FileWriter fw = new FileWriter(fileName, true); // O 'true' abre o arquivo em modo de adição (append).
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println(iniciais + " " + score); // Escreve a nova pontuação no final do arquivo.

        } catch (IOException e) {
            // Informa caso ocorra um erro na escrita do arquivo.
            System.out.println("Erro ao salvar score: " + e.getMessage());
        }
    }

    /*
     * Esse método é feito para que possa ser ordenado os rankings, armazenando-os
     * em uma classe List para ser realizado a ordenação
     */

    public static List<PlayerScore> getRankingOrdenado(int nivel) {
        String fileName = getFileName(nivel);
        List<PlayerScore> scores = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNext()) {
                String iniciais = sc.next();
                int pontos = sc.nextInt();
                scores.add(new PlayerScore(iniciais, pontos));
            }
        } catch (FileNotFoundException e) {
            // Se o arquivo não existe, apenas retorna a lista vazia.
            // A responsabilidade de avisar o usuário será do método que for exibir.
        }

        // Ordena a lista antes de retorná-la.
        scores.sort(Comparator.comparingInt(PlayerScore::getScore).reversed());
        return scores;
    }

    // Mostra o ranking de um nível específico, lendo a lista de player de,tal
    // nivel.
    public static void mostrarRanking(int nivel) {
        System.out.println("\n=== RANKING - Nível " + nivel + " ===");

        // Chama o novo método para obter a lista já ordenada.
        List<PlayerScore> scores = getRankingOrdenado(nivel);

        // Apenas exibe o resultado.
        if (scores.isEmpty()) {
            System.out.println("Ainda não há pontuações salvas para este nível.");
        } else {
            System.out.printf("%-5s %-10s %-10s\n", "Pos.", "Iniciais", "Score");
            System.out.println("----------------------------");
            int posicao = 1;
            for (PlayerScore ps : scores) {
                System.out.printf("%-5d %-10s %-10d\n", posicao++, ps.getIniciais(), ps.getScore());
            }
        }
    }

    private static void reescreverArquivo(int nivel, List<PlayerScore> scores) {
        String fileName = getFileName(nivel);
        // Desta vez, o 'false' no FileWriter vai SOBRESCREVER o arquivo.
        try (FileWriter fw = new FileWriter(fileName, false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            for (PlayerScore ps : scores) {
                out.println(ps.getIniciais() + " " + ps.getScore());
            }

        } catch (IOException e) {
            System.out.println("Erro ao reescrever o ranking: " + e.getMessage());
        }
    }

    public static void removerRanking(Scanner sc, int nivel) {

        List<PlayerScore> lista = getRankingOrdenado(nivel);

        System.out.println("Iniciais ---- Score");

        for (PlayerScore x : lista)
            System.out.println(x.getIniciais() + "    ----    " + x.getScore());

        System.out.print("Digite as iniciais que você deseja remover: (3 letras) ou (SAIR) ");
        String iniciaisParaRemover = sc.next().toUpperCase();

        if (iniciaisParaRemover.equals("SAI")) {
            return;
        } else {
            // Remove da lista usando a lógica correta.
            boolean removeu = lista.removeIf(playerScore -> playerScore.getIniciais().equals(iniciaisParaRemover));

            if (removeu) {
               // Salva a lista modificada de volta no arquivo.
                reescreverArquivo(nivel, lista);
                System.out.println("Removido com sucesso!!");
            } else {
                System.out.println("Iniciais não encontradas no ranking.");
            }
        }
    }
}
