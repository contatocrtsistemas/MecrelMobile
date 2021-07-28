package appmecrel.com.br.appmecrel.model.dao.interfaces;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */

public interface IExtintorMangueiraDAO {

    public void inserir(ExtintorMangueira extintorMangueira) throws DefaultException;

    public void alterar(ExtintorMangueira extintorMangueirae) throws DefaultException;

    public void deletar(int idMangueira) throws DefaultException;

    public List<ExtintorMangueira> buscar(int tipoConsulta, String informacao) throws Exception, DefaultException;

    public ExtintorMangueira bucarExtintorMangueira(int idMangueira) throws  DefaultException;
}
