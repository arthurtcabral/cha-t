import java.io.IOException;

public interface IUsuarioConversa {

	Usuario fazLogin(String nickname, String senha);
	void leMensagens() throws IOException;
	void escreveMensagem(String destinatario, String mensagem) throws IOException;
	
}
