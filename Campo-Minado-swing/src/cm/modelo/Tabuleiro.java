package cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador {
    private final int linhas;
    private final int colunas;
    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        geraCampos();
        associarVizinhos();
        sotearMinas();
    }
    public void paraCadaCampo(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public int getMinas() {
        return minas;
    }

    private final int minas;

    private final List<Campo> campos = new ArrayList<>();
    private final  List<Consumer<ResultadoEvento>> observadores =
            new ArrayList<>();

    public void registrarObservador(Consumer<ResultadoEvento> observador){
        observadores.add(observador);
    }
    public void notificarObservadores(boolean resultado){
        observadores.stream()
                .forEach(observador -> observador.accept(new ResultadoEvento(resultado)));
    }

    public void abrir(int linha, int coluna){

        campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst()
                .ifPresent(campo ->  campo.abrir());}


    public void alterarMarcação(int linha, int coluna){
        campos.parallelStream().filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst()
                .ifPresent(campo ->  campo.altenarMarcação());
    }
    private void geraCampos() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                Campo campo = new Campo(l,c);
                campo.registrarObservador(this);
                campos.add(campo);
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1: campos){
            for (Campo c2:campos){
                c1.adicionarVizinho(c2);
            }
        }
    }
    private void sotearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();
        do {

            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        }while (minasArmadas < minas);
    }

    public boolean objetivoAlcançado(){
        return campos.stream().allMatch(campo -> campo.objetivoAlcançado());
    }

    public void reiniciar(){
        campos.stream().forEach(c -> c.reiniciar());
        sotearMinas();
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        if (evento == CampoEvento.EXPLODIR){
            mostrarMinas();
            notificarObservadores(false);
        }else if (objetivoAlcançado()){
            System.out.println("Ganhou...");
            notificarObservadores(true);
        }
    }private void mostrarMinas(){

        campos.stream().filter(campo -> campo.isMinado()).filter(campo -> !campo.isMarcado()).forEach(campo ->  campo.setAberto(true));
    }
}
