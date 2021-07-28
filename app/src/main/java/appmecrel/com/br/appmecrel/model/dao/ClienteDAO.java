package appmecrel.com.br.appmecrel.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.Banco;
import appmecrel.com.br.appmecrel.model.bean.Cliente;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IClienteDAO;
import appmecrel.com.br.appmecrel.util.API;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */


public class ClienteDAO implements IClienteDAO {
    private static SQLiteDatabase db;
    //    private Context contexto;
    private Banco banco;
    private static ClienteDAO instance;

    public ClienteDAO(Context contexto) {
//        this.contexto = contexto;
        banco = Banco.getInstance(contexto);
        //db = banco.getWritableDatabase();
    }

    public static ClienteDAO getInstance(Context contexto){
        if (instance == null){
            synchronized (ClienteDAO.class){
                if (instance == null) {

                    instance = new ClienteDAO(contexto);

                }
            }
        }
        return instance;
    }

    public void inserir(Cliente cliente) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        if(cliente.getCliId() == 0)
            valores.put("cliid", banco.getSequencial("clientes"));
        else
            valores.put("cliid", cliente.getCliId());
        valores.put("clinome", cliente.getCliNome());
        valores.put("clicpfcnpj", cliente.getCliCpfCnpj());
        Log.i("Linhas Afetadas", "Inseriu: " + db.insert("clientes", null, valores));
        db.close();
    }

    @Override
    public void alterar(Cliente cliente) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("cliid", cliente.getCliId());
        valores.put("clinome", cliente.getCliNome());
        valores.put("clicpfcnpj", cliente.getCliCpfCnpj());
        db.close();
    }

    @Override
    public void deletar(int idCliente, boolean deletaTudo) throws DefaultException {
        db = banco.getWritableDatabase();
        if(deletaTudo){
            db.delete("clientes",null, null);
        }else{
            db.delete("clientes","cliid = ?", new String[]{String.valueOf(idCliente)});
        }
        db.close();

    }

    @Override
    public List<Cliente> buscar(int tipoConsulta, String informacao) throws DefaultException {
        String consultaCliente = "SELECT * FROM clientes  ";

        db = banco.getReadableDatabase();
        List<Cliente> clientes = new ArrayList<Cliente>();
        Cursor cursor = null;
        if (informacao.equals("")) {
            //   cursor = db.query("clientes", null, null, null, null, null, "clinome ASC");
        }else if(tipoConsulta == 0){
            consultaCliente = consultaCliente + " WHERE clinome LIKE  ? ";
            informacao = "" + informacao + "%";
        }else if(tipoConsulta == 1){
            consultaCliente = consultaCliente + "WHERE clinome LIKE  ? ";
            informacao = "%" + informacao + "%";
        }else if(tipoConsulta == 2){
            consultaCliente = consultaCliente + "WHERE cliid = ?";
        }else if(tipoConsulta == 3){
            consultaCliente = consultaCliente + "WHERE clicpfcnpj = ?";
        }

        Log.i("TESTE", consultaCliente);
        if (informacao != "") {

            if(tipoConsulta == 6){
                cursor = db.rawQuery(consultaCliente + " ORDER BY clinome ", null);
            }
            else{
                cursor = db.rawQuery(consultaCliente + " ORDER BY clinome ", new String[]{informacao});
            }
        }else{
            cursor = db.rawQuery(consultaCliente + " ORDER BY clinome ", null);
        }
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Cliente cliente = new Cliente();
                preencheCliente(cliente, cursor);
                clientes.add(cliente);

            }while(cursor.moveToNext());
        }
        db.close();
        return clientes;
    }

    @Override
    public Cliente buscarCliente(String idCliente, boolean id){
        String consultaCliente = "SELECT * FROM clientes  WHERE cliid = ?";
        String consultaClienteNome = "SELECT * FROM clientes WHERE clinome = ?";
        db = banco.getReadableDatabase();
        Cliente cliente = new Cliente();
        Cursor cursor = null;
        Log.i("Teste", "id Cliente Metodo: " + idCliente);
        if(id)
            cursor = db.rawQuery(consultaCliente, new String[]{idCliente});
        else
            cursor = db.rawQuery(consultaClienteNome, new String[]{idCliente});
        Log.i("Teste", "Passo da consulta Qtd:" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            preencheCliente(cliente, cursor);
        }else{
            cliente = null;
        }
        db.close();
        return cliente;
    }

    private void preencheCliente(Cliente cliente, Cursor cursor) {
        try {
            Log.i("Teste", "Vai preencher Cliente ");
            cliente.setCliId(cursor.getInt(0));
            Log.i("Teste", "Vai preencher Cliente " + cliente.getCliId());
            cliente.setCliNome(cursor.getString(1));
            cliente.setCliCpfCnpj(cursor.getString(2));
        }catch (Exception e){
            Log.i("Teste", "Erro ao preencher Cliente " + e.getMessage());
        }
    }
}