import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Scanner teclado;

	public static void main(String[] args) throws IOException {
		teclado = new Scanner(System.in);
		
		String nomeArquivo = "";
		UsuarioConversa usuarioConversa = new UsuarioConversa(new File(nomeArquivo));
			
		// TODO: Implementar menu de login.
		// TODO: Implementar menu (1- Ler mensagens; 2- Escrever mensagem).
		// TODO: Implementar Submenus (Para as escolhas 1 e 2).

	}

}
