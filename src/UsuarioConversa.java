import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsuarioConversa implements IUsuarioConversa {

	private String usuarios[][];
	private Usuario usuarioLogado;
	private static String ARQUIVO_CONVERSA = "Conversas.txt";

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
				if (nickname.equals(usuarios[i][0]) && senha.equals(usuarios[i][1])) {
					usuarioLogado = new Usuario(nickname, senha);
					return usuarioLogado;
				}
			}
			return null;
		}
		return null;
	}

	@Override
	public void leMensagens(String outroUsuario) throws IOException {
		if (!usuarioExiste(outroUsuario)) {
			return;
		}
		ArrayList<String> conversa = new ArrayList<>();

		try {
			FileReader fr = new FileReader(ARQUIVO_CONVERSA);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				String [] mensagem = line.split(";");
				String remetente = mensagem[1];
				String destinatario =  mensagem[2];
				
				if(remetente.equals(this.usuarioLogado.getNickname()) && destinatario.equals(outroUsuario)){
					conversa.add(line);
				}
				else if(remetente.equals(outroUsuario) && destinatario.equals(this.usuarioLogado.getNickname())){
					conversa.add(line);
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String mensagem : conversa) {
			System.out.println(getMensagemFormatada(mensagem));
		}

	}

	private String getMensagemFormatada(String mensagem){
		String [] vetorMensagem = mensagem.split(";");
		return vetorMensagem[1] + " - " + vetorMensagem[0] + "\n" + vetorMensagem[3] + "\n---------------------------------------------------------------------------------";
	}
	
	private boolean usuarioExiste(String nickname){
		if (this.usuarios != null) {
			for (int i = 0; i < usuarios.length; i++) {
				if (nickname.equals(usuarios[i][0])) {
					return true;
				}
			}
			System.out.println("O usuário " + nickname + " não existe.");
			return false;
		}
		System.out.println("Não há usuários cadastrados.");
		return false;
	}
	
	@Override
	public void escreveMensagem(String destinatario, String mensagem) throws IOException {
		if (!usuarioExiste(destinatario)) {
			return;
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

		File f = new File(ARQUIVO_CONVERSA);
		try {
			FileWriter fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			// data;nickname;destinatario;mensagem.
			pw.println(dateFormat.format(date) + ";" + usuarioLogado.getNickname() + ";" + destinatario + ";" + mensagem);
			pw.close();
		} catch (IOException e) {
			System.out.println("Erro ao salvar mensagem.");
		}

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
