🎮 Calcula Quiz

Um quiz de matemática em Java, desenvolvido a partir de uma atividade da disciplina de IPOI (Introdução à Programação Orientada a Objetos) na UFLA.
O jogo permite ao usuário responder questões de multiplicação em diferentes níveis de dificuldade, acumular pontos e salvar sua pontuação em um ranking no estilo retrô (inspirado em fliperamas antigos).
_________________________________________________________________________________________________________________________________________________________________________________________________________________
✨ Funcionalidades
* **Menu Interativo:** Navegação completa pela aplicação, incluindo iniciar jogo, ver ranking, ler regras e sair.
* **Três Níveis de Dificuldade:** Fácil, Médio e Difícil, com intervalos de números que aumentam o desafio.
* **Sistema de Pontuação:** Score calculado com base no número de acertos, no nível de dificuldade e no tempo de resposta.
* **Ranking Persistente:** As pontuações são salvas em arquivos `.txt` separados por nível.
* **Ordenação de Ranking:** Exibição do ranking em ordem decrescente de pontuação.
* **Gerenciamento de Scores:** Funcionalidade para remover pontuações do ranking.
* **Interface Limpa:** Uso de função para limpar a tela do console, melhorando a experiência do usuário.


_________________________________________________________________________________________________________________________________________________________________________________________________________________
## 🛠️ Tecnologias e Conceitos Aplicados
Este projeto foi uma oportunidade para aplicar e aprofundar conhecimentos em diversas áreas do desenvolvimento com Java:

* **Programação Orientada a Objetos (POO):**
    * Forte **separação de responsabilidades** em classes distintas (`Menu`, `Quiz`, `Ranking`, `PlayerScore`, etc.).
    * Aplicação do **Princípio da Responsabilidade Úunica (SRP)** e **DRY (Don't Repeat Yourself)**.
    * **Encapsulamento** para proteger os dados internos das classes.
* **Manipulação de Arquivos (Java I/O & NIO):**
    * Leitura e escrita em arquivos de texto para persistência de dados.
    * Uso de `try-with-resources` para gerenciamento seguro de `FileWriter`, `PrintWriter` e `Scanner`.
    * Leitura de arquivos de texto com `Files.readAllLines`.
* **Java Collections Framework:**
    * Uso de `List` e `ArrayList` para manipulação de dados em memória.
    * Ordenação de objetos com a interface `Comparator` e expressões Lambda.
* **Tratamento de Exceções:**
    * Implementação de blocos `try-catch` para lidar com erros de I/O (`IOException`, `FileNotFoundException`) e de entrada do usuário (`StringIndexOutOfBoundsException`), tornando a aplicação mais robusta.
* **API `java.time`:**
    * Utilização de `LocalDateTime` e `Duration` para o cálculo preciso do tempo de resposta.

_________________________________________________________________________________________________________________________________________________________________________________________________________________
## ⚙️ Como Executar

Para rodar o projeto, você precisará ter o **Java JDK (versão 11 ou superior)** instalado.


# 1. Clone o repositório
git clone [link do seu repositório aqui]

# 2. Navegue até a pasta 'src' do projeto
cd [caminho para a past<img width="1280" height="720" alt="image" src="https://github.com/user-attachments/assets/536fae0a-b253-4358-bc6d-ae23fa80aeeb" />
a do projeto]/src

# 3. Compile todos os arquivos .java
javac *.java

# 4. Execute a classe Main
java Mainn

_________________________________________________________________________________________________________________________________________________________________________________________________________________
## 📂 Estrutura do Projeto

O código foi arquitetado com as seguintes classes:

* `Main.java`: Ponto de entrada da aplicação. Responsável por inicializar o `Scanner` e delegar a execução para a classe `Menu`.
* `Menu.java`: Gerencia toda a interface com o usuário, a navegação e o fluxo da aplicação.
* `Quiz.java`: Orquestra uma partida do quiz, aplicando as questões e calculando os resultados.
* `Ranking.java`: Classe utilitária que lida com toda a lógica de leitura, escrita, ordenação e remoção de scores nos arquivos `.txt`.
* `Questao.java`: Modela uma única questão, gerando os números aleatórios com base na dificuldade.
* `Pontuacao.java`: Encapsula a lógica de cálculo do score final.
* `Correcao.java`: Armazena os dados do resultado de uma questão respondida.
* `PlayerScore.java`: Modela a pontuação de um jogador para facilitar a ordenação do ranking.

_________________________________________________________________________________________________________________________________________________________________________________________________________________
📌 Próximos passos
 Adicionar novos tipos de questões (soma, subtração, divisão)
 Criar versão com interface gráfica (JavaFX ou Swing)
 Melhorar persistência (salvar em banco de dados simples)
 _________________________________________________________________________________________________________________________________________________________________________________________________________________
👨‍💻 Autor

Projeto desenvolvido por Alexandre Bortone durante estudos de Java e POO na UFLA.

📂 Confira também no LinkedIn: [https://www.linkedin.com/in/alexandre-bortone/]
