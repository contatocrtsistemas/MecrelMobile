package appmecrel.com.br.appmecrel.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import appmecrel.com.br.appmecrel.model.Banco;
import appmecrel.com.br.appmecrel.model.bean.Sequencial;

/**
 * Created by usuario on 03/03/2015.
 */
public class SequencialDAO {

    private static SQLiteDatabase db;
    private static SequencialDAO instancia;
    private Banco banco;

    /*public SequencialDAO(Context contexto){
        db = banco.getWritableDatabase();
        banco =  Banco.getInstance(contexto);
    }*/

    public static SequencialDAO getInstance(Context contexto){
        if(instancia == null){
            synchronized (SequencialDAO.class){
                instancia = new SequencialDAO(contexto);
            }
        }
        return instancia;
    }

    public SequencialDAO(Context contexto){
        Banco banco = new Banco(contexto);
        db = banco.getWritableDatabase();
    }

    public void setSequencial(String nomeTabela, int valor){

        ContentValues valores = new ContentValues();
        valores.put("seqtabela", nomeTabela);
        valores.put("seqproximo", valor);

        Log.i("Linhas Afetadas Seq", "Inseriu: " + db.insert("sequencial", null, valores));
    }

    public void alteraSequencial(Sequencial sequencial){
        ContentValues valores = new ContentValues();
        Log.i("SEQUENCIAL", sequencial.getTabela());
        valores.put("seqtabela", sequencial.getTabela());
        valores.put("seqproximo", sequencial.getProximo());
        db.update("sequencial", valores, "seqtabela = ?", new String[]{sequencial.getTabela()});
    }

    public ArrayList<Sequencial> getSequenciais(){

        ArrayList<Sequencial> sequenciais = new ArrayList<Sequencial>();
        Cursor cursor = db.query("sequencial",null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
               Sequencial sequencial = new Sequencial();
               preencher(sequencial, cursor);
               sequenciais.add(sequencial);
            }while(cursor.moveToNext());
        }
        return sequenciais;
    }

    private void preencher(Sequencial sequencial, Cursor cursor){
        sequencial.setTabela(cursor.getString(0));
        sequencial.setProximo(cursor.getString(1));
    }
}
