package appmecrel.com.br.appmecrel.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alisson on 02/03/2015.
 */
public class Banco extends SQLiteOpenHelper {


    public static final String NOME_DB = "ControleCrtMecrel";
    public static final int VERSAO = 19;
    private String nometabela;
    private int proximo;
    private Context contexto;
    public static Banco instance;


    public Banco(Context contexto){
   		super(contexto,NOME_DB,null, VERSAO);
		this.contexto = contexto;
    }

    public static Banco getInstance(Context contexto){
        if (instance == null){
            synchronized (Banco.class){
                if (instance == null) {

                    instance = new Banco(contexto);

                    //db = contexto.openOrCreateDatabase(Banco.NOME_DB,Banco.VERSAO,null);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] sql = Tabelas.TABELAS.split("\n");
        String[] insertsPadrao = Tabelas.INSERTS_PADRAO.split("\n");
        db.beginTransaction();
        try
        {
            // Cria a tabela e testa os dados
            ExecutarComandosSQL(db, sql);
            ExecutarComandosSQL(db, insertsPadrao);
            db.setTransactionSuccessful();
        }
        catch (SQLException e)
        {
            Log.e("Erro ao criar as tabelas e testar os dados", e.toString());
        }
        finally
        {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try
        {
            Cursor cursor = db.query("sqlite_master", new String[]{"name"}, "type = ?", new String[]{"table"}, null, null, "name ASC");
            //String[] sql = "SELECT name FROM sqlite_master WHERE type='table'ORDER BY name".split("\n");
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    db.execSQL("DROP TABLE IF EXISTS " + cursor.getString(0));
                }while (cursor.moveToNext());
            }

                db.setTransactionSuccessful();
        }
        catch (SQLException e)
        {
            Log.e("Erro ao atualizar as tabelas e testar os dados", e.toString());
        }
        finally
        {
            db.endTransaction();
        }

        onCreate(db);
    }

    private void ExecutarComandosSQL(SQLiteDatabase db, String[] sql)
    {
        for( String s : sql )
            if (s.trim().length()>0)
                db.execSQL(s);
    }

    public int getSequencial(String nomeTabela) {
        int sequencial = 0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query("sequencial", new String[]{"seqproximo"}, "seqtabela = ? ", new String[]{nomeTabela}, null, null, null);
            //select seqtabela sequencial where seqtabela = nomeTabela;
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                sequencial = cursor.getInt(0) + 1;
                ContentValues valores = new ContentValues();
                valores.put("seqtabela",nomeTabela);
                valores.put("seqproximo",""+sequencial);
                db.update("sequencial",valores, "seqtabela = ?", new String[]{nomeTabela});
            }
            else {
                ContentValues valores = new ContentValues();
                valores.put("seqtabela",nomeTabela);
                valores.put("seqproximo","1");
                db.insert("sequencial",null,valores);
                sequencial = 1;
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return sequencial;
    }
}
