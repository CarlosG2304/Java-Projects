package cm.modelo;



import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<Campo>();
    private  List <CampoObservador> observadores = new ArrayList<>();
    //private List<BiConsumer<Campo, CampoEvento>> observadors = new ArrayList<>();
    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }
    public void notificarObservadores(CampoEvento evento){
        observadores.stream()
                .forEach(observador -> observador.eventoOcorreu(this,evento));
    }
    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = linha != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = (linha - vizinho.linha);
        int deltaColuna = (coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral != 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        }else if (deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }
    }
   public void altenarMarcação() {
        if (!aberto) {
            marcado = !marcado;}
    if (marcado){
    notificarObservadores(CampoEvento.MARCAR);
    }else {
        notificarObservadores(CampoEvento.DESMARCAR);
    }
    }
   public boolean abrir(){
        if (!aberto && !marcado ){
            aberto = true;
            if (minado) {
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }
            setAberto(true);


 if (vizinhançaSegura()){
     vizinhos.forEach(v ->  v.abrir());
 }
 return true;
        }else {
            return  false;
        }
    }
  public   boolean vizinhançaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);

    }
void minar(){
        minado = true;
    }
    public boolean isMinado(){
        return minado;
    }
    public boolean isMarcado(){
        return marcado;
    }

   void setAberto(boolean aberto) {
        this.aberto = aberto;

        if (aberto){
            notificarObservadores(CampoEvento.ABRIR);
        }
    }

    public boolean isAberto(){
        return aberto;
    }   public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    boolean objetivoAlcançado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }
  public   int minasNaVizinhamça(){
        return (int) vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
    aberto =  false;
    minado =  false;
    marcado = false;
    notificarObservadores(CampoEvento.REINICIAR);
    }


}
