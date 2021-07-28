package appmecrel.com.br.appmecrel.model.dao.interfaces;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.EXControle;

/**
 * Created by usuario on 20/08/2015.
 */
public interface IEXControleDAO {

    public void inserir(EXControle exControle) throws DefaultException;

    public void alterar(EXControle exControle) throws DefaultException;

    public void deletar(int idControle) throws DefaultException;

    public List<EXControle> buscar(int tipoConsulta, String informacao) throws DefaultException;

    public EXControle buscarEXControle(int idControle) throws  DefaultException;
}
