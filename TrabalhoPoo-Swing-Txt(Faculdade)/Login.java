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
public class Login {

	private JFrame frame;
	private  JTextField textField;
	private  JTextField textFieldUsuario;
	private  JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GerenciadorMetodos login = new GerenciadorMetodos();
		// obtem e coloca a imagem dentro de uma BufferedImage
				BufferedImage img = null;
				
				try {
					img = ImageIO.read(new File("books.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			
				JFrame janela = new JFrame();
				janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				janela.setSize(679, 600);
				janela.getContentPane().setLayout(null);
				//Vai centralizar a janela na tela
				janela.setLocationRelativeTo(null);
				
				JButton btCadastrar = new JButton("Cadastrar  Novo Usu\u00E1rio ");
				btCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						//Vai chamar a clase Cadastrar,
				 		//e vai deixar de mostra a login.
						Cadastrar cadastro = new Cadastrar();
						janela.setVisible(false);
						
					}
				});
				btCadastrar.setBackground(Color.BLUE);
				btCadastrar.setBounds(222, 430, 178, 45);
				janela.getContentPane().add(btCadastrar);
				
				JButton btLimpar = new JButton("Limpar");
				btLimpar.setBackground(Color.YELLOW);
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
				btLimpar.setBounds(272, 346, 85, 32);
				janela.getContentPane().add(btLimpar);
				
				JButton btnLogin = new JButton("Login");
				btnLogin.setBackground(Color.GREEN);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Pega o usuario e senha digitados.
						login.setUsuario(textFieldUsuario.getText());
						login.setSenha(passwordField.getText());
						
						//Vai verificar se todos os campos foram preenchidos,
						//se não, ira pedi para o usuario preencher todos os campos.
					   if(passwordField.getText().equals("") && textFieldUsuario.getText().equals("")) {
							JOptionPane.showMessageDialog(btnLogin, "Preencha todos os campos!");
						}else if(textFieldUsuario.getText().equals("")){
							JOptionPane.showMessageDialog(btnLogin, "Preencha todos os campos!");
						}else if(passwordField.getText().equals("")) {
							JOptionPane.showMessageDialog(btnLogin, "Preencha todos os campos!");
						}
					   //Vai verificar o usuario e senha, se não vai dizer para o usuario que estao incorretas 
						if(login.VericarUsuario()) {
							JOptionPane.showMessageDialog(btnLogin, "Usuario e Senha corretas!");
							//e passa esse usuario para a outra classe com a tela principal.
							//Vai chamar o metodo main da classe e passa esse usuario para a ela.
							TelaGerenciador telaPrincipal = new TelaGerenciador();
							telaPrincipal.main(null,textFieldUsuario.getText());
							//e vai deixar de mostra a tela de cadastro.
							janela.setVisible(false);
						}else {
							JOptionPane.showMessageDialog(btnLogin, "Usuario ou Senha incorreta!");
						}
					}});
				btnLogin.setBounds(257, 388, 117, 32);
				janela.getContentPane().add(btnLogin);
				
				JLabel lblTitulo = new JLabel("Bem Vindo ao Gerenciador De Livros");
				lblTitulo.setForeground(Color.WHITE);
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setVerticalAlignment(SwingConstants.TOP);
				lblTitulo.setFont(new Font("Calibri", Font.BOLD, 35));
				lblTitulo.setBounds(80, 55, 539, 71);
				janela.getContentPane().add(lblTitulo);
				
	            textFieldUsuario = new JTextField();
				textFieldUsuario.setBounds(210, 178, 216, 45);
				janela.getContentPane().add(textFieldUsuario);
				textFieldUsuario.setColumns(10);
				
				JLabel lblUsuario = new JLabel("Usuario:");
				lblUsuario.setFont(new Font("Calibri", Font.BOLD, 15));
				lblUsuario.setForeground(Color.WHITE);
				lblUsuario.setBounds(139, 188, 88, 29);
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
				
							ImageIcon imageIcon = new ImageIcon(img);
							
							janela.setVisible(true);
							
							
							JButton btnFechar = new JButton("Fechar ");
							btnFechar.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									System.exit(0);
								
								}
							});
							btnFechar.setBackground(Color.RED);
							btnFechar.setForeground(Color.BLACK);
							btnFechar.setBounds(210, 485, 204, 50);
							janela.getContentPane().add(btnFechar);
							
							// Cria e configura o texto no JLabel
							JLabel lblImagem = new JLabel();
							lblImagem.setBounds(0, 0,665, 573);
							lblImagem.setFont(new Font("Calibri", Font.BOLD, 80));
							lblImagem.setForeground(Color.BLUE);
							lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
							lblImagem.setIcon(imageIcon);
							janela.getContentPane().add(lblImagem);
		}

}
