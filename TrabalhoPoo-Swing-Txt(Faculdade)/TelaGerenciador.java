package trabalhopoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import trabalhopoo.modelo.GerenciadorMetodos;

public class TelaGerenciador {

	private static JFrame frame;
	private static JTable table;
	private static JTextField textFieldNome;
	private static JTextField textFieldAutor;
	private static JTextField textFieldPaginas;

	/**
	 * Launch the application. 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args, String usuario)  {
		
GerenciadorMetodos metodos = new GerenciadorMetodos();
		
		metodos.setUsuario(usuario);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("books.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		try {
			frame.setBounds(100, 100, 661, 641);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Vai centralizar a janela na tela
		frame.setLocationRelativeTo(null);
		ImageIcon imageIcon = new ImageIcon(img);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 37, 388, 541);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JLabel lblPaginas = new JLabel("Paginas:");
		lblPaginas.setForeground(Color.WHITE);
		lblPaginas.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPaginas.setBounds(5, 141, 57, 23);
		
		frame.getContentPane().add(lblPaginas);
		DefaultTableModel model =new DefaultTableModel();
        //Nome das colunas
		Object[] column = {"Id","Nome","Autor","Paginas"};
		//Array de cada parte da linha da tabela
		Object[] row = new Object[4];
		model.setColumnIdentifiers(column);
	   //Vai adicionar os dados em cada linha
		for(int y=0; y<=metodos.Dados().size()-4;y= y+4) {
		row[0] = metodos.Dados().get(y);
		row[1] = metodos.Dados().get(y+1);
		row[2] = metodos.Dados().get(y+2);
		row[3] = metodos.Dados().get(y+3);
	    model.addRow(row);
	}
		//Vai setar o modelo na tabela
		 table.setModel(model);
		
	    JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setForeground(Color.WHITE);
		lblAutor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAutor.setBounds(17, 105, 45, 13);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(17, 66, 45, 13);
		frame.getContentPane().add(lblNome);
		
		JLabel lblTitulo = new JLabel("Digite os dados do livro:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(27, 10, 187, 25);
		frame.getContentPane().add(lblTitulo);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(72, 56, 142, 31);
		frame.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(72, 97, 142, 31);
		frame.getContentPane().add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		textFieldPaginas = new JTextField();
		textFieldPaginas.setBounds(72, 138, 142, 31);
		frame.getContentPane().add(textFieldPaginas);
		textFieldPaginas.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				row[0] = table.getRowCount()+1;
				row[1] = textFieldNome.getText();
				row[2] = textFieldAutor.getText();
				row[3] = textFieldPaginas.getText();
			
				String x = ""+(table.getRowCount()+1);
				
				metodos.AnotarLivros(textFieldNome.getText(), textFieldAutor.getText(), textFieldPaginas.getText(), x);		
				
				model.addRow(row);
			}
		});
		btnAdicionar.setBounds(17, 233, 104, 25);
		frame.getContentPane().add(btnAdicionar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBackground(Color.GREEN);
		btnAtualizar.setForeground(Color.BLACK);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				//Vai seta a visibilidade dessa janela para falso 
				//para ela não fica mais vizivel e vai abrir outra
				////////Cuidado a ordem é importante///////////////////////////////////
				frame.setVisible(false);
			 	main(args, usuario);
				

			}
		});
		btnAtualizar.setBounds(17, 268, 104, 27);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Vai pena o numero da linha clicada pelo usuario e vai atribuir na variavel i
				//Retorna -1 caso o usuario não tiver clicado em nenhuma linha.
				int i = table.getSelectedRow();   
			if(i >= 0) {
				
				Object[] options = {"NÃO", "SIM" };

				int escolha = JOptionPane.showOptionDialog(frame,
				"Deseja realmente excluir essa linha? ",
				"Alerta!",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[1]
				);
				if (escolha == 0) { 
				} else if(escolha == 1){ 
				
					
                String y = ""+(i+1);
                
            
				try {
					metodos.removerLivros(y);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				model.removeRow(i);
			}}else {
				JOptionPane.showMessageDialog(btnRemover, "Selecione a linha que deseja remover");
			}
				}
			}
				
		);
		btnRemover.setBounds(131, 233, 97, 25);
		frame.getContentPane().add(btnRemover);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"NÃO", "SIM" };

				int escolha = JOptionPane.showOptionDialog(frame,
				"Deseja realmente sair? ",
				"Alerta!",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[1]//Opcão setada em Sim [1]
				);
				if (escolha == 0) { //Não limpar
				} else if(escolha == 1){ 
					Login login = new Login();
					frame.setVisible(false);
				} 	
				}
			}
		);
		btnSair.setBackground(Color.RED);
		btnSair.setBounds(131, 268, 97, 28);
		frame.getContentPane().add(btnSair);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setBounds(0, 0, 647, 630);
		lblImagem.setIcon(imageIcon);
		
		frame.getContentPane().add(lblImagem);
		
		
	}


		
	}
	
	
