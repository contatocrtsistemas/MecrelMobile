package appmecrel.com.br.appmecrel.model.dao.interfaces;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;

/**
 * Created by usuario on 20/08/2015.
 */
public interface IEXFabricanteDAO {

    public void inserir(EXFabricante fabricante) throws DefaultException;

    public void alterar(EXFabricante fabricante) throws DefaultException;

    public void deletar(int idFabricante) throws DefaultException;

    public List<EXFabricante> buscar() throws DefaultException;

    public EXFabricante buscarFabricante(int idFabricante) throws  DefaultException;
}
