import java.util.Scanner;


/* Classe responsavel por incializar o menu */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Menu.executarMenu(sc);

        sc.close();
    }

}
