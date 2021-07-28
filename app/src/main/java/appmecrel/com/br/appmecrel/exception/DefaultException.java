package appmecrel.com.br.appmecrel.exception;

/**
 * Created by win7 on 04/03/2015.
 */
public class DefaultException extends Exception {
    private String mensagem;

    public DefaultException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return mensagem;
    }
}
