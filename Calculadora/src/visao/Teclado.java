package visao;

import modelo.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teclado extends JPanel implements ActionListener {

    private final Color COR_CINZA_ESCURO = new Color(68,68,68);
    private final Color COR_CINZA_CLARO = new Color(99,99,99);
    private final Color COR_LARANJA = new Color(242,163,60);

    public Teclado() {

        GridBagLayout layout = new GridBagLayout();
       // Altera o local do botoes da tela em linhas e colunas.
        GridBagConstraints c = new GridBagConstraints();

        setLayout(layout);
        //Faz o teclado ocupar a parte que ele fica toda.
        c.weightx = 1;
        c.weighty = 1;

        //Preenche os espaços em branco entre os botoes.
        c.fill = GridBagConstraints.BOTH;

        //Linha 1
       //c.gridwidth Seta o tamanho do botao.
        c.gridwidth = 2;
        adicionarBotao("AC",COR_CINZA_ESCURO,c,0,0);
        c.gridwidth = 1;
        adicionarBotao("%",COR_CINZA_ESCURO,c,2,0);
        adicionarBotao("/",COR_LARANJA,c,3,0);

        //Linha 2
        adicionarBotao("7",COR_CINZA_CLARO,c,0,1);
        adicionarBotao("8",COR_CINZA_CLARO,c,1,1);
        adicionarBotao("9",COR_CINZA_CLARO,c,2,1);
        adicionarBotao("*",COR_LARANJA,c,3,1);

        //Linha 3
        adicionarBotao("4",COR_CINZA_CLARO,c,0,2);
        adicionarBotao("5",COR_CINZA_CLARO,c,1,2);
        adicionarBotao("6",COR_CINZA_CLARO,c,2,2);
        adicionarBotao("-",COR_LARANJA,c,3,2);
        //Linha 4
        adicionarBotao("1",COR_CINZA_CLARO,c,0,3);
        adicionarBotao("2",COR_CINZA_CLARO,c,1,3);
        adicionarBotao("3",COR_CINZA_CLARO,c,2,3);
        adicionarBotao("+",COR_LARANJA,c,3,3);
        //Linha 5
        c.gridwidth = 2;
        adicionarBotao("0",COR_CINZA_CLARO,c,0,4);
        c.gridwidth = 1;
        adicionarBotao(",",COR_CINZA_CLARO,c,2,4);
        adicionarBotao("=",COR_LARANJA,c,3,4);
/*
Seta o lugar do botao. O x na horizontal e o y na vetical.
        c.gridx = 0;
        c.gridy = 0;*/

        /* //Setar o numero de linhas e colunas do teclado.
        setLayout(new GridLayout(5,4));

        //Adicionando os botoes, a cor e o texto.
        add(new Botao("AC",COR_CINZA_ESCURO));
        add(new Botao("+/-",COR_CINZA_ESCURO));
        add(new Botao("%",COR_CINZA_ESCURO));
        add(new Botao("/",COR_LARANJA));

        add(new Botao("7",COR_CINZA_CLARO));
        add(new Botao("8",COR_CINZA_CLARO));
        add(new Botao("9",COR_CINZA_CLARO));
        add(new Botao("*",COR_LARANJA));

        add(new Botao("7",COR_CINZA_CLARO));
        add(new Botao("8",COR_CINZA_CLARO));
        add(new Botao("9",COR_CINZA_CLARO));
        add(new Botao("*",COR_LARANJA));


        add(new Botao("7",COR_CINZA_CLARO));
        add(new Botao("8",COR_CINZA_CLARO));
        add(new Botao("9",COR_CINZA_CLARO));
        add(new Botao("*",COR_LARANJA));


        add(new Botao("7",COR_CINZA_CLARO));
        add(new Botao("8",COR_CINZA_CLARO));
        add(new Botao("9",COR_CINZA_CLARO));
        add(new Botao("*",COR_LARANJA));*/


    }

    private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
// Metodo que vai setar o botao e seu local na tela com o constraints.
        c.gridx = x;
        c.gridy = y;

        Botao botao = new Botao(texto,cor);
        //Adiciona cada botao para ter uma ação no actionPerformed.
        botao.addActionListener(this);
        add(botao,c);
}

    @Override
    public void actionPerformed(ActionEvent e) {
//Pega o valor digitado.
        if (e.getSource() instanceof JButton){
    JButton botao =(JButton) e.getSource();
    //Chama o metodo na memoria para adicionar o texto do botao digitado nela.
    Memoria.getInstancia().processarComando(botao.getText());
}


    }
}
