package appmecrel.com.br.appmecrel.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.Banco;
import appmecrel.com.br.appmecrel.model.bean.Configuracao;
import appmecrel.com.br.appmecrel.model.bean.Configuracoes;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IConfiguracaoDAO;

/**
 * Created by usuario on 22/06/2015.
 */
public class ConfiguracaoDAO implements IConfiguracaoDAO {
    private static SQLiteDatabase db;
    private static ConfiguracaoDAO instacia;
    private Banco banco;

    public ConfiguracaoDAO(Context contexto) { this.banco = Banco.getInstance(contexto); }

    public static ConfiguracaoDAO getInstance(Context contexto){

        if(instacia == null){
            synchronized (ConfiguracaoDAO.class){
                instacia = new ConfiguracaoDAO(contexto);
            }
        }
        return instacia;
    }
    @Override
    public void inserir(Configuracao configuracao) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("cfgnome", configuracao.getNome());
        valores.put("cfgvalor", configuracao.getValor());
        db.insert("configuracoes", null, valores);
        db.close();
    }

    @Override
    public void alterar(Configuracao configuracao) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("cfgnome", configuracao.getNome());
        valores.put("cfgvalor", configuracao.getValor());
        db.update("configuracoes", valores, "cfgnome = ?", new String[]{configuracao.getNome()});
        db.close();
    }

    @Override
    public void deletar(String nomeConfiguracao) throws DefaultException {
        db = banco.getWritableDatabase();
        db.delete("configuracoes", "cfgnome = ?", new String[]{nomeConfiguracao});
        db.close();
    }

    @Override
    public Configuracoes buscar() throws DefaultException {

        Configuracoes configuracoes = new Configuracoes();
        db = banco.getReadableDatabase();
        Cursor cursor = db.query("configuracoes",null,null,null,null,null,"cfgnome ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Configuracao configuracao = new Configuracao();
                preencher(configuracao, cursor);
                configuracoes.add(configuracao);

            }while(cursor.moveToNext());
        }
        db.close();
        return configuracoes;
    }
    private void preencher(Configuracao configuracao, Cursor cursor) {
        configuracao.setNome(cursor.getString(0));
        configuracao.setValor(cursor.getString(1));
    }
}
