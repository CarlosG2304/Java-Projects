package trabalhopoo.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorMetodos {
	
 private  String usuario;
 private  String senha;
 
 private String nomeDoLivro;
 private String autorDoLivro;
 private String quatPaginas;
 private String id;
 
 private int linhas;
 
 //Metodos Gettes e Settes
 public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
 
public void CadastrarUsuarios() {
	
	//Bloco que ir� tentar executar o c�digo a seguir
	try{
	//FileWriter: Cria um arquivo, append true.
	FileWriter fw = new FileWriter("src/trabalhopoo/Credenciais.txt", true);
	//Imprime o usuario e a senha no arquivo
	PrintWriter pw = new PrintWriter(fw);
	pw.println("Usuario: " + this.usuario.trim());
	pw.println("Senha: " + this.senha.trim());
	pw.println("\n");
	pw.flush();
	pw.close();
	} catch (IOException ex) {
		System.out.println("Arquivo ainda n�o criado");
	}
}

public Boolean VericarUsuario() {

	Boolean autenticador = false;
	//O FileReader: vai ler o arquivo recebendo como parametro o caminho dele.
    //O BufferedReader vai receber como parametro o FileReader e ele vai gerenciar essa leitura.
	try (BufferedReader br = new BufferedReader(new FileReader("src/trabalhopoo/Credenciais.txt"))) {
        //O br.readLine vai ler a primeira linha do arquivo.
		String line = br.readLine();
		//Vai fazer um la�o que vai ler o arquivo todo, ou seja , ate a variavel line ficar igual a null. 
		while (line != null) {
			//Vai verificar o usuario com o .equals() que � a forma de se comparar duas strings.
			//Vai verificar o usuario com o .trim() para tirar o espa�os em branco do usuario.
			//Vai executar o br.readLine para ler a linha de baixo e vericar a senha se e igual a senha que o usuario passo.
			if(line.equals("Usuario: "+usuario.trim()) && br.readLine().equals("Senha: "+senha)) { 
				autenticador = true; 
				//Vai dar um break para sair do la�o na hora que vericar o usuario e a senha.
				break;
				}
			//Vai ler a proxima linha e vai atribuir a variavel line. 
			//Importante para ele ler todas as linhas e n�o ficar em la�o imfinito!
		line = br.readLine();}
		}catch (IOException e) {
		System.out.println("Error: " + e.getMessage());		
		}
	
	return autenticador;		
	
}
public Boolean VericarUsuarioRepetido() {

	Boolean autenticador = false;
	
	try (BufferedReader br = new BufferedReader(new FileReader("src/trabalhopoo/Credenciais.txt"))) {

		String line = br.readLine();
		//Vai fazer um la�o que vai ler o arquivo todo, ou seja , ate a variavel line ficar igual a null. 
		while (line != null) {
			//Vai verificar o usuario com o .trim() para tirar o espa�os em branco e o equalsIgnoreCase para desconsiderar letras maiusculas ou minusculas do usuario.
			if(line.equalsIgnoreCase("Usuario: "+usuario.trim())) { 
				autenticador = true;
				//Vai dar um break para sair do la�o na hora que vericar o usuario.
				break;	
			}
			//Vai ler a proxima linha e vai atribuir a variavel line. 
			//Importante para ele ler todas as linhas e n�o ficar em la�o imfinito!
		line = br.readLine();}
		}catch (IOException e) {
		System.out.println("Error: " + e.getMessage());		
		}
	
	return autenticador;	
	
}

public void AnotarLivros(String nomeDoLivro,String autor,String quatPaginas,String id) {

	
	try{
	//O .trim() tira os espa�os em branco para n�o falhar na hora de buscar o arquivo.	
	FileWriter fw = new FileWriter("src/trabalhopoo/ListaDo"+usuario.trim()+".txt", true);
	//Anota os dados do livro no arquivo txt
	PrintWriter pw = new PrintWriter(fw);
	pw.println(id);
	pw.println(nomeDoLivro);
	pw.println(autor);
	pw.println(quatPaginas);
	pw.println("\n");
	pw.flush();
	pw.close();
	} catch (IOException ex) {
	//O m�todo exibe mensagem de erro no console.
	ex.printStackTrace();
	}
	
}

public List<String> Dados() {
	//ArrayList que vai amazenar os dados da lista do usuario
	List<String> dados = new ArrayList<>();
	
	
	try (BufferedReader br = new BufferedReader(new FileReader("src/trabalhopoo/ListaDo"+usuario.trim()+".txt"))) {
		String line = br.readLine();
		//Vai adicionar a primeira linha no ArrayList.
		dados.add(line);
		//Vai fazer um la�o que vai ler o arquivo todo, ou seja , ate a variavel line ficar igual a null. 
		while (line != null) {
			//Vai ler a proxima linha e vai atribuir a variavel line. 
			//Importante para ele ler todas as linhas e n�o ficar em la�o imfinito!
			line = br.readLine();
		   //Se a linha n�o for nula, e n�o for uma string vazia vai adicionar no ArrayList
			if(!(line == null)) {
				if(!line.isEmpty()) {dados.add(line);}
				}
		}
	
	}catch (IOException e) {
	 
	}
return dados;
}

public void removerLivros(String id) throws IOException {
	List<String> dados = new ArrayList<>();
	
	int i = 0;
	int z = 1; 
	//Convers�o de inteiro para String.
	String x = ""+z;
	
	
	BufferedReader br = new BufferedReader(new FileReader("src/trabalhopoo/ListaDo"+usuario.trim()+".txt"));
	String line = br.readLine();
	//Vai fechar  BufferedReader. 
	br.close();
	//Vai verificar se o arquivo n�o estar vazio para iniciar o precesso de remo��o.
	if(!(line == null)) {
	try (BufferedReader br2 = new BufferedReader(new FileReader("src/trabalhopoo/ListaDo"+usuario.trim()+".txt"))) {
		String line2 = br2.readLine();
		//Vai fazer um la�o que vai ler o arquivo todo, ou seja , ate a variavel line ficar igual a null. 
		while (line2 != null) {
			//Vai verificar se a linha n�o � nula.
			if(!(line2 == null) ) {
				//Vai verificar se a linha � igual a o id, se for vai pula 3 linhas para n�o adicionar elas no ArrayList.
					if(line2.equals(id)) {
						
					br2.readLine();
					br2.readLine();
					br2.readLine();
					line2 = br2.readLine();
					//Vai verificar se a linha n�o � uma string vazia.
					}else if(!line2.isEmpty()) {
						//Vai verificar se a linha se estar na quarta linha ou alguma mutipla de 4.
					if(i%4 == 0) {
						line2 = line2.replaceAll("[1-10000]", "");
						//Vai adicionar o id de cada livro anotado.
						dados.add(x);
						z++;
						i++;
						x = ""+z;
						line2 = br2.readLine();
					}else {
				dados.add(line2);
				line2 = br2.readLine();
				i++;}
					//else para ele pula a linha mesmo se ela for uma string vazia.
					}else {line2 = br2.readLine();}
					
			}
		}
		//Vai fechar  BufferedReader. 
			br2.close();		
	
	}catch (IOException e) {
	System.out.println("Error: " + e.getMessage());		
	}
	

	
	FileWriter fw = new FileWriter("src/trabalhopoo/ListaDo"+usuario+".txt");
	
	  
	    int contadorDeLinhas = 0;
		
	for (String string : dados) {
		
		//Vai depois de 3 linhas escrever a ultima linha e pular 3 linhas
		if((contadorDeLinhas+1)%4 == 0) {
		fw.write(string);
	    fw.write("\n");
		fw.write("\n");
		fw.write("\n");
		
	}else{
		//Vai escrever escrever todas a linhas que n�o cair no if e pula uma linha a cada vez.
		fw.write(string);
		fw.write("\n");
	}
	//Vai adicionar mais um ao contador de linhas a cada repeti��o do la�o for.
	   contadorDeLinhas++;
}
fw.close();}
	
	
}}