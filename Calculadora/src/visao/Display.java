package visao;

import modelo.Memoria;
import modelo.MemoriaObservador;

import javax.swing.*;
import java.awt.*;


public class Display extends JPanel implements MemoriaObservador {
    //O texto que vai esta contido neste meu JPanel.
    private final JLabel label;

    public Display() {
        //Chama o metodo na memria que vai adicionar o display nos observadores para ela ser notificada das alteraçoes.
        Memoria.getInstancia().adicionarObservador(this);
        Memoria.getInstancia().adicionarObservador(this);
        setBackground(new Color(46,49,50));
        label = new JLabel(Memoria.getInstancia().getTestoAtual());
        //Seta a cor do label.
        label.setForeground(Color.WHITE);
        //Seta respetivamente o nome da fonte,o estilo e o tamanho.
        label.setFont(new Font("courier",Font.PLAIN,30));
        //Seta o lado que fica a fonte na tela.
        // O primeiro numero é o gap horizontal e o segundo o vertical.
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10,25));

        add(label);
    }

    @Override
    public void valorAlterado(String novoValor) {
        //Sempre que ouver uma mudança na memoria essa mundaça ira notificar o display.
        label.setText(novoValor);
    }
}
