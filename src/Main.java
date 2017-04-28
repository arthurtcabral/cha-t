import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Scanner teclado;

	public static void main(String[] args) throws IOException {
		teclado = new Scanner(System.in);
		
		String nomeArquivo = "Usuarios.txt";
		UsuarioConversa usuarioConversa = new UsuarioConversa(new File(nomeArquivo));
		int op = 0;
		
		do{
			System.out.println("1 - logar");
			System.out.println("2 - sair");
			op = teclado.nextInt();
			teclado.nextLine();
		
		if(op == 1){
			System.out.println("Digite o usuario: ");
			String usuario = teclado.next();
			System.out.println("Digite a senha: ");
			String senha = teclado.next();
			
			Usuario user = usuarioConversa.fazLogin(usuario, senha);
			if (user != null){
				int opcao = 0;
				do{
				System.out.println("1 - ler mensagem");				
				System.out.println("2 - escrever mensagem");
				System.out.println("3 - fazer loggof");
				opcao = teclado.nextInt();
				teclado.nextLine();
				
				if(opcao == 1){
					System.out.println("Digite o usuário da mensagem");
					String outroUsuario = teclado.next();
					teclado.nextLine();
					usuarioConversa.leMensagens(outroUsuario);
				}else if(opcao == 2){
					System.out.println("Digite o destinatario");
					String destinatario = teclado.next();
					teclado.nextLine();
					System.out.println("Digite a mensagem:");
					String mensagem = teclado.nextLine();
					usuarioConversa.escreveMensagem(destinatario, mensagem);
				}else if(opcao == 3){
					System.out.println("Fazendo loggof...");
				}else{
					System.out.println("Opcão inválida");
				}
				
				}while(opcao != 3);
				
				
			}else{
				System.out.println("Usuário ou senha inválidos");				
			}
			
		}else if(op == 2){
			System.out.println("Encerrando programa...");
		}else{
			System.out.println("Opção inválida");
		}
		
		}while(op != 2);

	}

}
