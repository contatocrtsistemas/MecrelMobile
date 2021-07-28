package appmecrel.com.br.appmecrel.model.dao.interfaces;


import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.Configuracao;
import appmecrel.com.br.appmecrel.model.bean.Configuracoes;

/**
 * Created by usuario on 22/06/2015.
 */
public interface IConfiguracaoDAO {

    public void inserir(Configuracao configuracao) throws DefaultException;

    public void alterar(Configuracao configuracao) throws DefaultException;

    public void deletar(String nomeConfiguracao) throws DefaultException;

    public Configuracoes buscar() throws DefaultException;

}
