
// Classe simples que apenas armazena os dados do resultado de uma questão.
public class Correcao {

    private int id;
    private boolean situacao;
    private int tentativas;
  


    public Correcao (int id, boolean situacao, int tentativas){

        this.id = id;
        this.situacao = situacao;
        this.tentativas = tentativas;


    }

    public int getId() {
        return id;
    }

    public int getTentativas() {
        return tentativas;
    }

    public String verificarSituacao() {

        String condicao;

        if(!situacao){
        condicao = "Errou";
        }else{
            condicao = "Acertou";
            
        }

        return condicao;
    }


}
