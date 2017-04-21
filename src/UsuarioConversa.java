import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UsuarioConversa implements IUsuarioConversa {

	private String usuarios[][];
	private Usuario usuarioLogado;

	// Este construtor instancia a matriz e a preenche.
	public UsuarioConversa(File file) throws IOException {
		int numeroUsuarios = this.numeroUsuariosArquivo(file);
		usuarios = new String[numeroUsuarios][2];
		
		int linhaMatriz = 0;
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		String[] linhaQuebrada;
		while ((linha = br.readLine()) != null) {
			linhaQuebrada = linha.split(";");
			usuarios[linhaMatriz][0] = linhaQuebrada[0];
			usuarios[linhaMatriz][1] = linhaQuebrada[1];
			linhaMatriz++;
		}
	}

	@Override
	public Usuario fazLogin(String nickname, String senha) {
		if (this.usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (nickname.equals(usuarios[i][0])
						&& senha.equals(usuarios[i][1])) {
					usuarioLogado = new Usuario(nickname, senha);
					return usuarioLogado;
				}
			}
			return null;
		}
		return null;
	}

	@Override
	public void leMensagens() throws IOException {
		// TODO: Ler todas as mensagens recebidas e enviadas. Se possível,
		// ordenar por ordem alfabética do destinatário.

	}

	@Override
	public void escreveMensagem(String destinatario, String mensagem)
			throws IOException {
		// TODO: Escrever mensagem para um destinatário específico, existente no
		// Usuarios.txt.

	}

	private int numeroUsuariosArquivo(File file) throws IOException {
		int numeroUsuarios = 0;
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linha;
		while ((linha = br.readLine()) != null) {
			numeroUsuarios++;
		}
		br.close();
		return numeroUsuarios;
	}

}
