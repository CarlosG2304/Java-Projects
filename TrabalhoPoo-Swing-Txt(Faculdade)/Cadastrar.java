package trabalhopoo;

import java.awt.EventQueue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import trabalhopoo.modelo.GerenciadorMetodos;

public class Cadastrar {

			private JFrame frame;
			private JTextField textField;
			private JTextField textFieldUsuario;
			private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastrar window = new Cadastrar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				GerenciadorMetodos cadastrar = new GerenciadorMetodos();
				
			// obtem e coloca a imagem dentro de uma BufferedImage
					BufferedImage img = null;
					
					try {
						img = ImageIO.read(new File("books.jpg"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					// Cria e configura a janela
					JFrame janela = new JFrame();
					janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					janela.setSize(679, 600);
					janela.getContentPane().setLayout(null);
					//Vai centralizar a janela na tela
					janela.setLocationRelativeTo(null);
					
					JButton btCadastrar = new JButton("Cadastrar");
					btCadastrar.setBackground(Color.GREEN);
					btCadastrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							
							
							//Pega o usuario e senha digitados. 
							cadastrar.setUsuario(textFieldUsuario.getText());
							cadastrar.setSenha(passwordField.getText());
							
							//Vai verificar se todos os campos foram preenchidos,
							//se não, ira pedi para o usuario preencher todos os campos e dizer quais campos estão vazios
							if(passwordField.getText().equals("") && textFieldUsuario.getText().equals("")) {
								JOptionPane.showMessageDialog(btCadastrar, "Preencha todos os campos!  Campos Usuario e Senha Vazio!");
							}else if(textFieldUsuario.getText().equals("")){
								JOptionPane.showMessageDialog(btCadastrar, "Preencha todos os campos!  Campo Usuario Vazio!");
							}else if(passwordField.getText().equals("")) {
								JOptionPane.showMessageDialog(btCadastrar, "Preencha todos os campos!  Campo Senha Vazio!");
							
							//Verifica se o usuario ja esta cadastrado,
							//se ja estiver so ira mostra para o usuario que ja estar cadastrado,
							//se não vai chama o metodo CadastrarUsuarios para cadastrar esse usuario, 
							//e o AnotarLivros com strings vazias so para ele criar o arquivo txt que vai amazenar a lista de livros desse usuario. 
							} else if(cadastrar.VericarUsuarioRepetido()) {
							JOptionPane.showMessageDialog(btCadastrar, "Usuario ja cadastrado!");
							}else {
						    cadastrar.CadastrarUsuarios();
							cadastrar.AnotarLivros("", "", "", "");
							JOptionPane.showMessageDialog(btCadastrar, "Usuario Cadastrado com sucesso!");	
							}
						}
					});
					btCadastrar.setBounds(223, 367, 190, 38);
					janela.getContentPane().add(btCadastrar);
					
					JButton btLimpar = new JButton("Limpar");
					btLimpar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object[] options = {"NÃO", "SIM" };

							int escolha = JOptionPane.showOptionDialog(btLimpar,
							"Deseja realmente limpar? ",
							"Alerta!",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,
							null,
							options,
							options[1]//Opcão setada em Sim [1]
							);
							if (escolha == 0) { //Não limpar
							} else if(escolha == 1){ // Sim limpar
							//Ação do btn Limpar
							textFieldUsuario.setText("");
							passwordField.setText("");
							}//Fim da Ação do btn Limpar
							
						}
					});
					btLimpar.setBounds(273, 328, 88, 29);
					janela.getContentPane().add(btLimpar);
					
				 JButton btnVoltar = new JButton("Voltar Para a Tela de Login");
				 btnVoltar.setBackground(Color.BLUE);
				 btnVoltar.setForeground(Color.BLACK);
				 btnVoltar.addActionListener(new ActionListener() {
				 	public void actionPerformed(ActionEvent e) {
				 		
				 		//Vai chamar a classe Login,
				 		//e vai deixar de mostra a tela de cadastro.
				 		Login voltar = new Login();
				 		janela.setVisible(false); 
				 	}
				 });
				 btnVoltar.setBounds(219, 453, 207, 52);
			     janela.getContentPane().add(btnVoltar);
					
					JLabel lblTitulo = new JLabel("Cadastre seu Usuario e Senha Abaixo:");
					lblTitulo.setForeground(Color.WHITE);
					lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
					lblTitulo.setVerticalAlignment(SwingConstants.TOP);
					lblTitulo.setFont(new Font("Calibri", Font.BOLD, 35));
					lblTitulo.setBounds(56, 78, 561, 71);
					janela.getContentPane().add(lblTitulo);
					
		            textFieldUsuario = new JTextField();
					textFieldUsuario.setBounds(210, 178, 216, 45);
					janela.getContentPane().add(textFieldUsuario);
					textFieldUsuario.setColumns(10);
					
					JLabel lblUsuario = new JLabel("Usuario:");
					lblUsuario.setFont(new Font("Calibri", Font.BOLD, 15));
					lblUsuario.setForeground(Color.WHITE);
					lblUsuario.setBounds(150, 188, 88, 29);
					janela.getContentPane().add(lblUsuario);
					
					passwordField = new JPasswordField();
					passwordField.setBounds(210, 269, 216, 45);
					janela.getContentPane().add(passwordField);
					passwordField.setColumns(10);
							
				   JLabel lblSenha = new JLabel("Senha:");
				   lblSenha.setFont(new Font("Calibri", Font.BOLD, 15));
				   lblSenha.setForeground(Color.WHITE);
				   lblSenha.setBounds(150, 269, 53, 29);
				   janela.getContentPane().add(lblSenha);
					
					// Cria e configura o texto no JLabel
					JLabel lblImagem = new JLabel();
					lblImagem.setBounds(0, 0,665, 563);
					lblImagem.setFont(new Font("Calibri", Font.BOLD, 80));
					lblImagem.setForeground(Color.BLUE);
					lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
					
								
								ImageIcon imageIcon = new ImageIcon(img);
								lblImagem.setIcon(imageIcon);
								
								// mostra a janela e adiciona o label a ela
								janela.setVisible(true);
								janela.getContentPane().add(lblImagem);
								
						}	

}
