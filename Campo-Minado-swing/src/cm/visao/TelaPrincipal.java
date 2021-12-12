package cm.visao;

import cm.modelo.Tabuleiro;

import javax.swing.*;
import java.util.Random;

public class TelaPrincipal extends JFrame {


	public TelaPrincipal(){
        Random aleatorio = new Random();
        Tabuleiro tabuleiro = new Tabuleiro(16,30,aleatorio.nextInt(51)+5);
        PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
        add(painelTabuleiro);

        setTitle("Campo Minado");
        setSize(690,438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }

}
