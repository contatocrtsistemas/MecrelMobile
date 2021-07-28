package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.Configuracao;
import appmecrel.com.br.appmecrel.model.bean.Configuracoes;
import appmecrel.com.br.appmecrel.model.dao.ConfiguracaoDAO;


/**
 * Created by usuario on 22/06/2015.
 */
public class ConfiguracaoModel {

    private Context contexto;
    private ConfiguracaoDAO configuracaoDAO;


    public ConfiguracaoModel(Context contexto) {
        this.contexto = contexto;
    }

    public void tratarInsercao(Configuracao configuracao){
        if (configuracao.getNome() == null) {

        } else {
            configuracaoDAO = ConfiguracaoDAO.getInstance(contexto);
            try {
                configuracaoDAO.inserir(configuracao);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }

    public void tratarAlteracao(Configuracao configuracao){
        if(configuracao.getNome() == null){

        }else{
            configuracaoDAO =ConfiguracaoDAO.getInstance(contexto);
            try{
                configuracaoDAO.alterar(configuracao);
            }catch(DefaultException e){
                e.getMessage();
            }
        }
    }

    public void tratarExclusao(String nomeConfiguracao){
        if(nomeConfiguracao.length() <= 0){}
        else{
            configuracaoDAO = ConfiguracaoDAO.getInstance(contexto);
            try{
                configuracaoDAO.deletar(nomeConfiguracao);
            }catch(DefaultException e){ e.getMessage(); }

        }
    }



    public Configuracoes tratarBusca(){
        configuracaoDAO =ConfiguracaoDAO.getInstance(contexto);
        try {
            return configuracaoDAO.buscar();
        } catch (DefaultException e) {
            e.getMensagem();
            return  null;
        }
    }
}
