package visao;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {

    public Botao(String texto, Color cor) {
        //Setar o texto que vai estar nos botoes.
        // Setar a cor dos botoes e a fonte.
        setText(texto);
        setOpaque(true);
        setBackground(cor);
        setFont(new Font("courier",Font.PLAIN,25));
        //Seta a cor da fonte.
        setForeground(Color.WHITE);
        //Seta as cor da borda dos botoes.
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }
}
