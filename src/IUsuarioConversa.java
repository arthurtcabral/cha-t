import java.io.IOException;

public interface IUsuarioConversa {

	Usuario fazLogin(String nickname, String senha);
	void leMensagens(String outroUsuario) throws IOException;
	void escreveMensagem(String destinatario, String mensagem) throws IOException;
	
}
