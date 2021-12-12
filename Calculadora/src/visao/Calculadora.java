package visao;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {

    public Calculadora()  {

        organizarLayout();
        setSize(232,322);
        //Tira a barra de cima da tela.
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void organizarLayout() {
        //Define a tela em:norte,sul,leste,oeste e no centro.
        setLayout(new BorderLayout());

        Display display = new Display();
        //Setando a o tamanho do display.
        display.setPreferredSize(new Dimension(233,60));
        //Metodo add foi recebido por herança.
        //Adicionando o componente display no norte.
        add(display, BorderLayout.NORTH);

        Teclado teclado = new Teclado();
        //Adicionando o componente teclado no centro.
        add(teclado, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
