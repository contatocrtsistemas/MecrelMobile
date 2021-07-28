package appmecrel.com.br.appmecrel.model.dao.interfaces;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.Cliente;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */

public interface IClienteDAO {

    public void inserir(Cliente cliente) throws DefaultException;

    public void alterar(Cliente cliente) throws DefaultException;

    public void deletar(int idCliente, boolean deletaTudo) throws DefaultException;

    public List<Cliente> buscar(int tipoConsulta, String informacao) throws DefaultException;

    public Cliente buscarCliente(String idCliente, boolean id) throws  DefaultException;

}