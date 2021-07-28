package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.Cliente;
import appmecrel.com.br.appmecrel.model.dao.ClienteDAO;

/**
 * Created by win7 on 04/03/2015.
 */
public class ClienteModel {
    private Context contexto;
    private ClienteDAO clienteDAO;

    public ClienteModel(Context contexto){
        this.contexto = contexto;
    }

    public void tratarInsercaoCliente(Cliente cliente)  {
        if (cliente.getCliNome() == null) {

        } else {
            clienteDAO = clienteDAO.getInstance(contexto);
            try {
                clienteDAO.inserir(cliente);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }

    public void tratarAlteracaoCliente(Cliente cliente)  {
        if (cliente.getCliNome() == null) {

        } else {
            clienteDAO = clienteDAO.getInstance(contexto);
            try {
                clienteDAO.alterar(cliente);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }

    public void tratarExclusaoCliente(int clienteId) {
        if (clienteId <= 0) {

        } else {
            clienteDAO = clienteDAO.getInstance(contexto);
            try {
                clienteDAO.deletar(clienteId, false);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }

    public void tratarExclusaoCliente(int clienteId, boolean deletaTudo) {
        if (clienteId <= 0) {

        } else {
            clienteDAO = clienteDAO.getInstance(contexto);
            try {
                clienteDAO.deletar(clienteId, deletaTudo);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }
    public List<Cliente> tratarBusca(int tipoConsulta, String informacao){
        clienteDAO =clienteDAO.getInstance(contexto);
        try {
            return clienteDAO.buscar(tipoConsulta,informacao);
        } catch (DefaultException e) {
            e.getMensagem();
            return  null;
        }
    }

    public Cliente tratarBuscaCliente(String idCliente){
        clienteDAO = clienteDAO.getInstance(contexto);
        try {
            return clienteDAO.buscarCliente(idCliente, true);
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    public Cliente tratarBuscaCliente(String idCliente, boolean id){
        clienteDAO = clienteDAO.getInstance(contexto);
        try {
            return clienteDAO.buscarCliente(idCliente, id);
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

}
