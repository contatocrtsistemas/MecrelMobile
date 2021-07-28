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
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IEXControleDAO;
import appmecrel.com.br.appmecrel.model.validacao.EXFabricanteModel;

/**
 * Created by usuario on 20/08/2015.
 */

public class EXControleDAO implements IEXControleDAO {

    private static SQLiteDatabase db;
    private static EXControleDAO instancia;
    private Banco banco;
    private Context contexto;


    public EXControleDAO(Context contexto){
        banco =  Banco.getInstance(contexto);
        this.contexto = contexto;
    }

    public static EXControleDAO getInstance(Context contexto){
        if(instancia == null){
            synchronized (EXControleDAO.class){
                instancia = new EXControleDAO(contexto);
            }
        }
        return instancia;
    }

    @Override
    public void inserir(EXControle exControle) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("xcoid", banco.getSequencial("excontrole"));
        preencher(exControle, valores);
        Log.i("INSERIU CONTROLE..: " , ""+ db.insert("excontrole", null, valores));
        db.close();
    }

    @Override
    public void alterar(EXControle exControle) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        Log.i("TESTE", "VEIO AKI ALTERAR");
        valores.put("xcoid", exControle.getId());
        preencher(exControle, valores);
        db.update("excontrole", valores, "xcoid = ? ", new String[]{String.valueOf(exControle.getId())});
        Log.i("TESTE", "VEIO AKI ALTERAR DEU ERRO");
        db.close();
    }

    @Override
    public void deletar(int idControle) throws DefaultException {
        db = banco.getWritableDatabase();
        db.delete("excontrole", "xcoid = ?", new String[]{String.valueOf(idControle)});
        db.close();
    }

    @Override
    public List<EXControle> buscar(int tipoConsulta, String informacao) throws DefaultException {
        List<EXControle> exControles = new ArrayList<EXControle>();
        String consulta = "SELECT * FROM excontrole  ";
        db = banco.getReadableDatabase();
        Cursor cursor = null;
        if (informacao.equals("")) {
            if(tipoConsulta == 3){
                consulta += " WHERE xcostatus = 0 ";
                cursor = db.rawQuery(consulta, null);
            }else if(tipoConsulta == 4){
                consulta += " WHERE xcostatus = 1 ";
                cursor = db.rawQuery(consulta, null);
            }else{
                cursor = db.query("excontrole",null,null,null,null,null,"xcoid ASC");
            }
        }else if(tipoConsulta == 0){
            consulta = consulta + " WHERE xcocodigo = ? ";
        }else if(tipoConsulta == 1){
            consulta = consulta + " ex, fabricantes fab WHERE (fab.fabid = ex.fabid) AND fab.fabnome LIKE  ? ";
        }else if(tipoConsulta == 3){
            consulta = consulta + " WHERE xcocodigo = ? AND xcostatus = 0";
        }else if(tipoConsulta == 4){
            consulta = consulta + " WHERE xcocodigo = ? AND xcostatus = 1";
        }
        if (!informacao.equals("")) {
            if(tipoConsulta == 2){
                consulta += " WHERE xcostatus = 0 ";
                cursor = db.rawQuery(consulta, null);
            }else{
                cursor = db.rawQuery(consulta, new String[]{informacao});
            }
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                EXControle exControle = new EXControle();
                preencher(exControle, cursor);
                exControles.add(exControle);
            }while(cursor.moveToNext());
        }
        db.close();
        return exControles;
    }

    @Override
    public EXControle buscarEXControle(int idControle) throws DefaultException {
        return null;
    }

    private void preencher(EXControle exControle, Cursor cursor) {
        try{
            exControle.setId(cursor.getInt(0));
            exControle.setNumeroServico(cursor.getInt(1));
            exControle.setCodigo(cursor.getInt(2));
            exControle.setNumero(cursor.getString(3));
            exControle.setMesAno(cursor.getString(4));
            exControle.getFabricante().setId(cursor.getInt(5));
            EXFabricanteModel exFabricanteModel = new EXFabricanteModel(contexto);
            exControle.setFabricante(exFabricanteModel.tratarBusca(String.valueOf(exControle.getFabricante().getId())));
            exControle.setTipo(cursor.getInt(6));
            exControle.setCapacidade(cursor.getDouble(7));
            exControle.setpTrabalho(cursor.getString(8));
            exControle.setNormaFabricacao(cursor.getString(9));
            exControle.setpNivel(cursor.getInt(10));
            exControle.setPintura(cursor.getInt(11));
            exControle.setPesoPV(cursor.getDouble(12));
            exControle.setEnsBaixaPressao(cursor.getDouble(13));
            exControle.setPesoPC(cursor.getDouble(14));
            exControle.setvL(cursor.getDouble(15));
            exControle.setcMaxima(cursor.getDouble(16));
            exControle.setEnsAltaPressao(cursor.getDouble(17));
            exControle.setDvm(cursor.getDouble(18));
            exControle.setDvp(cursor.getDouble(19));
            exControle.setDvm10(cursor.getDouble(20));
            exControle.setResultado(cursor.getInt(21));
            exControle.setNumeroOrdem(cursor.getString(22));
            exControle.setSeloCertificacao(cursor.getString(23));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            exControle.setDataRecarga(new java.sql.Date(dateFormat.parse(cursor.getString(24)).getTime()));
            exControle.setDataProximaRecarga(new java.sql.Date(dateFormat.parse(cursor.getString(25)).getTime()));
            exControle.setObs(cursor.getString(26));
            exControle.setStatus(cursor.getInt(27));
            exControle.setCheioVazio(cursor.getInt(28));
        }catch (Exception e){
            Log.e("ERRO AO PREENCHER O EXCONTROLE", e.getMessage());
        }
    }

    private void preencher(EXControle exControle, ContentValues valores){

        valores.put("xconumeroservico", exControle.getNumeroServico());
        valores.put("xcocodigo", exControle.getCodigo());
        valores.put("xconumero", exControle.getNumero());
        valores.put("xcomesano", exControle.getMesAno());
        valores.put("fabid", exControle.getFabricante().getId());
        valores.put("xcotipo", exControle.getTipo());
        valores.put("xcocapacidade", exControle.getCapacidade());
        valores.put("xcoptrabalho", exControle.getpTrabalho());
        valores.put("xconormafabricacao", exControle.getNormaFabricacao());
        valores.put("xcopnivel", exControle.getpNivel());
        valores.put("xcopintura", exControle.getPintura());
        valores.put("xcopesopv ", exControle.getPesoPV());
        valores.put("xcoensbaixapressao", exControle.getEnsBaixaPressao());
        valores.put("xcopesopc", exControle.getPesoPC());
        valores.put("xcovl", exControle.getvL());
        valores.put("xcocmaxima", exControle.getcMaxima());
        valores.put("xcoensaltapressao", exControle.getEnsAltaPressao());
        valores.put("xcodvm", exControle.getDvm());
        valores.put("xcodvp", exControle.getDvp());
        valores.put("xcodvm10", exControle.getDvm10());
        valores.put("xcoresultado", exControle.getResultado());
        valores.put("xconumeroordem", exControle.getNumeroOrdem());
        valores.put("xcoselocertificacao", exControle.getSeloCertificacao());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        valores.put("xcodatarecarga", dateFormat.format(exControle.getDataRecarga()));
        valores.put("xcodataproximarecarga", dateFormat.format(exControle.getDataProximaRecarga()));
        valores.put("xcoobs", exControle.getObs());
        valores.put("xcostatus", exControle.getStatus());
        valores.put("xcocheiovazio", exControle.getCheioVazio());
    }

    public void deleteAll() throws  Exception{
        db = banco.getWritableDatabase();
        db.delete("excontrole", null, null);
        db.close();
    }

    public void setStatus(int idControle, int status) throws DefaultException, Exception{
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("xcostatus", status);
        db.update("excontrole", valores, "xcoid = ? ",
                new String[]{String.valueOf(idControle)});
        db.close();
    }


}
