import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* Classe feita para gerenciar a interação do usuario, delegando as chamadas, criando quiz, mostrandos regras e o ranking */
public class Menu {

    // Apenas exibe as opções do menu principal.
    public static void exibirMenu() {
        limparTela();
        System.out.println("####### Bem vindo ao cálcula Quiz #######");
        System.out.println("1 - Iniciar Quiz\n2 - Ranking\n3 - Regras\n4 - Sair");
        System.out.print("Digite a sua opção: ");

    }

    // Controla o fluxo principal da aplicação, mantendo o menu em loop.
    public static void executarMenu(Scanner sc) {

        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();

            // Direciona a ação do programa com base na escolha do usuário.
            switch (opcao) {
                case 1:
                    limparTela();
                    System.out.print("Então, vamos lá!\n");
                    mostrarNivel();

                    int nivel = selecionarNivel(sc);

                    if (nivel == 0) { // Se o usuário digitou 0, volta para o menu principal
                        continue;
                    } else {
                        limparTela();
                        Quiz quiz = new Quiz(nivel);
                        quiz.aplicar(sc);
                        fimQuiz(quiz, sc, nivel);
                    }
                    break;

                case 2:
                    limparTela();
                    System.out.println("O que você deseja:\n1 - Mostrar ranking\n2 - Deletar um score\n0 - Voltar");
                    System.out.print("Digite um numero: ");
                    int n = 0;

                    // Valida a entrada para o sub-menu do ranking.
                    boolean valido = false;
                    while (!valido) {
                        n = sc.nextInt();
                        valido = verificaNum(n, 2, 0);
                        if (!valido)
                            System.out.print("Nível inválido, tente novamente: ");
                    }

                    if (n == 0)
                        continue; // Volta ao menu principal.

                    else if (n == 1)  // Lógica para mostrar o ranking.
                        exibirRanking(sc);

                     else  // Lógica para deletar score.
                        deletarRanking(sc);
                    

                    break;

                case 3: // Mostra as regras
                    limparTela();
                    sc.nextLine(); // Limpa o buffer do scanner.
                    textoRegra();
                    System.out.println("Pressione qualquer tecla para continuar!");
                    sc.nextLine(); // Pausa a execução.
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Opção invalida, digite novamente:");
                    break;
            }
        } while (opcao != 4); // O loop continua até que o usuário escolha a opção 4.

        sc.close();
    }

    // Método auxiliar que apenas exibe o menu de seleção de nível.
    public static void mostrarNivel() {

        System.out.println("Qual nivel você deseja?\n" +
                "1 - Facil\n2 - Médio\n3 - Dificil\n0 - Sair");
        System.out.print("Digite um numero: ");
    }

    // Método utilitário para validar se um número está dentro de um intervalo (min
    // e max).
    public static boolean verificaNum(int opcao, int max, int min) {

        return (opcao <= max && opcao >= min);

    }

    // Centraliza a lógica de pedir e validar a escolha do nível. Evita código
    // repetido.
    public static int selecionarNivel(Scanner sc) {

        int nivel = 0;
        boolean valido = false;
        while (!valido) {
            nivel = sc.nextInt();
            valido = verificaNum(nivel, 3, 0);
            if (!valido)
                System.out.print("Nível inválido, tente novamente: ");

        }
        return nivel;
    }

    public static void exibirRanking(Scanner sc) { // Método para realizar uma das opções do case 2 ( visualizar no caso)
        limparTela();
        System.out.println("### Visualizar ###");
        mostrarNivel();
        int nivel = selecionarNivel(sc);

        if (nivel == 0)
            return;

        sc.nextLine(); // Limpa o buffer do scanner antes de pausar.
        Ranking.mostrarRanking(nivel);
        System.out.println("Pressione qualquer tecla para continuar!");
        sc.nextLine(); // Pausa a execução.

    }

    public static void deletarRanking(Scanner sc) {   // Método para realizar uma das opções do case 2 ( deletar no caso)

        limparTela();
        System.out.println("### Deletar ###");
        mostrarNivel();
        int nivel = selecionarNivel(sc);

        if (nivel == 0)
            return;

        sc.nextLine(); // Limpa o buffer do scanner.
        Ranking.removerRanking(sc, nivel);
        System.out.println("Pressione qualquer tecla para continuar!");
        sc.nextLine(); // Pausa a execução.

    }

    // Orquestra os eventos após o término do quiz.
    public static void fimQuiz(Quiz aux, Scanner sc, int nivel) {
        limparTela();
        System.out.println("Gerando relatorio...");
        System.out.println("Pressione qualquer tecla para continuar!");
        sc.nextLine(); // Limpeza de buffer.
        sc.nextLine(); // Pausa.

        // Calcula os pontos uma única vez e reutiliza o resultado.
        int pontos = aux.getPontosFinais();
        aux.gerarRelatorio(pontos);

        // Lógica para pedir as iniciais e salvar o score
        try {
            System.out.print("Digite suas iniciais (3 letras) para salvar no ranking: ");
            String iniciais = sc.next().toUpperCase().substring(0, 3);
            aux.guardarScore(iniciais, pontos); // Passa os pontos já calculados.

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Iniciais inválidas. O score não foi salvo.");
        }

        System.out.println("Obrigado por joga!");
        System.out.println("Pressione qualquer tecla para continuar!");
        sc.nextLine();
        sc.nextLine();
    }

    // Limpa a tela do console (funciona em Windows, Linux e Mac).
    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Não foi possível limpar a tela: " + e.getMessage());
        }
    }

    // Lê e exibe as regras a partir de um arquivo externo 'regras.txt'.
    public static void textoRegra() {

        try {
            List<String> linhas = Files.readAllLines(Paths.get("regras.txt"));
            for (String linha : linhas) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar regras: " + e.getMessage());

        }

    }
}
