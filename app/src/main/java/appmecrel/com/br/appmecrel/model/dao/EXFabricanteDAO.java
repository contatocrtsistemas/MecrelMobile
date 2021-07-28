package appmecrel.com.br.appmecrel.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.Banco;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IEXFabricanteDAO;

/**
 * Created by usuario on 20/08/2015.
 */
public class EXFabricanteDAO implements IEXFabricanteDAO {

    private static SQLiteDatabase db;
    private static EXFabricanteDAO instancia;
    private Banco banco;

    public EXFabricanteDAO(Context contexto){ banco =  Banco.getInstance(contexto);}

    public static EXFabricanteDAO getInstance(Context contexto){
        if(instancia == null){
            synchronized (EXFabricanteDAO.class){
                instancia = new EXFabricanteDAO(contexto);
            }
        }
        return instancia;
    }

    @Override
    public void inserir(EXFabricante fabricante) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        if(fabricante.getId() != 0)
            valores.put("fabid",String.valueOf(fabricante.getId()));
        else
            valores.put("fabid", banco.getSequencial("fabricantes"));
        valores.put("fabnome", fabricante.getNome());
        db.insert("fabricantes", null, valores);
        db.close();
    }

    @Override
    public void alterar(EXFabricante fabricante) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("fabid", String.valueOf(fabricante.getId()));
        valores.put("fabnome", fabricante.getNome());
        db.update("fabricantes", valores, "fabid = ? ", new String[]{String.valueOf(fabricante.getId())});
        db.close();
    }

    @Override
    public void deletar(int idFabricante) throws DefaultException {
        db = banco.getWritableDatabase();
        db.delete("fabricantes", "fabid = ?", new String[]{String.valueOf(idFabricante)});
        db.close();
    }

    @Override
    public List<EXFabricante> buscar() throws DefaultException {

        List<EXFabricante> fabricantes = new ArrayList<EXFabricante>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.query("fabricantes",null,null,null,null,null,"fabnome ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                EXFabricante fabricante = new EXFabricante();
                preencher(fabricante, cursor);
                if((fabricante.getNome() != null) && (fabricante.getNome() != "") ) {
                    fabricantes.add(fabricante);
                }

            }while(cursor.moveToNext());
        }
        db.close();
        return fabricantes;

    }

    private void preencher(EXFabricante fabricante, Cursor cursor) {
        fabricante.setId(cursor.getInt(0));
        fabricante.setNome(cursor.getString(1));
    }

    @Override
    public EXFabricante buscarFabricante(int idFabricante) throws DefaultException {
        String consultaFabricante = "SELECT * FROM fabricantes WHERE fabid = ?";
        db = banco.getReadableDatabase();
        EXFabricante fabricante = new EXFabricante();
        Cursor cursor = db.rawQuery(consultaFabricante, new String[]{ String.valueOf(idFabricante)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            preencher(fabricante, cursor);
        }else{
            fabricante = new EXFabricante();
        }
        db.close();
        return fabricante;
    }
}
