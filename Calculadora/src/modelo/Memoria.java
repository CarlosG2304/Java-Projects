package modelo;

import java.util.ArrayList;
import java.util.List;


public class Memoria {

    private enum TipoComando {
            ZERAR/*,SINAL*/,NUMERO,DIV,MULT,SUB,SOMA,PORCENTAGEM,IGUAL,VIRGULA;
    };

    private static final  Memoria instancia = new Memoria();

    private TipoComando ultimaOperaçao = null;
    private boolean substituir = false;
    private String testoAtual = "";
    private String testoBuffer = "";

    private final List<MemoriaObservador> observadores =
            new ArrayList<>();

    private Memoria() {

    }
     public static Memoria getInstancia(){
       //Metodo para pegar a instacia privada
        return instancia;
     }
     public void adicionarObservador(MemoriaObservador observador){
        observadores.add(observador);
     }

    public String getTestoAtual(){
        return testoAtual.isEmpty() ? "0" : testoAtual;
    }

    public void processarComando(String testo){
        //Vai adicionar o valor ao testoAtual e notificar todos os observadores.
       TipoComando tipoComando = detectarTipoComando(testo);

      if (tipoComando == null){
          return;
        }else if (tipoComando == tipoComando.ZERAR){
          testoAtual = "";
          testoBuffer = "";
          substituir = false;
          ultimaOperaçao = null;
      }/*else if (tipoComando == tipoComando.SINAL && testoAtual.contains("-")){
          //Vai tirar o sinal de "-" se tiver.
          testoAtual = testoAtual.substring(1);
      } else if (tipoComando == tipoComando.SINAL && !testoAtual.contains("-")){
          testoAtual ="-"+ testoAtual;
      } */else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA){
          //Se substituir for verdadeiro troque o textoAtual se não adicione ao textoAtual.
          testoAtual = substituir ? testo : testoAtual + testo;
          substituir = false;
      }else if (tipoComando == TipoComando.PORCENTAGEM){
          testoBuffer = testoAtual;
          testoAtual += "%";
          substituir = true;
          testoAtual = obterResultadoOperacao();
          ultimaOperaçao = tipoComando;
      }
      else{
          //Se pressionar os botoes de multiplicação, divisão, subtração ou soma.
          substituir = true;
          testoAtual = obterResultadoOperacao();
          testoBuffer = testoAtual;
          ultimaOperaçao = tipoComando;
      }
        observadores.forEach(o -> o.valorAlterado(getTestoAtual()) );
    }

    private String obterResultadoOperacao() {
      //Se não ouver ultima operação ou for o "=" ,não faz nada so retorna o textoAtual.
        if (ultimaOperaçao == null || ultimaOperaçao == TipoComando.IGUAL){
        return testoAtual;}
      //Vai converter a string para double e trocar a virgula pelo ponto.
      double numeroBuffer = Double.parseDouble(testoBuffer.replace(",","."));
      double numeroAtual = Double.parseDouble(testoAtual.replace(",","."));
      double resultado = 0;

      if (ultimaOperaçao == TipoComando.SOMA){
          resultado = numeroBuffer + numeroAtual;
      }else if (ultimaOperaçao == TipoComando.SUB){
          resultado = numeroBuffer - numeroAtual;
      }else if (ultimaOperaçao == TipoComando.MULT){
          resultado = numeroBuffer * numeroAtual;
      }else if (ultimaOperaçao == TipoComando.DIV){
          resultado = numeroBuffer / numeroAtual;}
          else if (ultimaOperaçao == TipoComando.PORCENTAGEM){
          double PorcentoBuffer = Double.parseDouble(testoBuffer.replace("%",""));
              resultado = numeroAtual * (PorcentoBuffer/100);
      }
      //Vai converter o double para string e trocar o ponto por virgula
      String resultadoString = Double.toString(resultado).replace(".",",");
     //Verifica se o numero é inteiro,ou seja, se termina com ",0".
     boolean inteiro = resultadoString.endsWith(",0");
     //Se for inteiro troca o ",0" no final por uma string vazia.
     return inteiro ? resultadoString.replace(",0","") : resultadoString;
    }

    private TipoComando detectarTipoComando(String testo) {
    //Filtra a eventual tentativa do usuario de por varios 0 a esquerda do valor.
        if (testoAtual.isEmpty() && testo == "0"){
        return null;
    }
       try {
           //Verifica se o texto digitado pode ser convertido para inteiro
           // ,logo retona o valor digitado como numero.
           Integer.parseInt(testo);
           return TipoComando.NUMERO;
       }catch (NumberFormatException e){
           // Quando não for numero...
           if ("AC".equals(testo)){
               return TipoComando.ZERAR;
           }else if ("/".equals(testo)){
               return TipoComando.DIV;
           }else if ("*".equals(testo)){
               return TipoComando.MULT;
           }else if ("+".equals(testo)){
               return TipoComando.SOMA;
           }else if ("-".equals(testo)){
               return TipoComando.SUB;
           }else if ("=".equals(testo)) {
               return TipoComando.IGUAL;
           }/*else if ("±".equals(testo)){
               return TipoComando.SINAL;}*/
               else if ("%".equals(testo)){
               return TipoComando.PORCENTAGEM;
           }else if (",".equals(testo) && !testoAtual.contains(",")){
               return TipoComando.VIRGULA;
           }
       }

        return null;
    }
}
