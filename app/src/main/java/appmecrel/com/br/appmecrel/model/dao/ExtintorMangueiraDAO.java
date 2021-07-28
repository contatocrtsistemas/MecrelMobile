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
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IEXControleDAO;
import appmecrel.com.br.appmecrel.model.dao.interfaces.IExtintorMangueiraDAO;
import appmecrel.com.br.appmecrel.model.validacao.ClienteModel;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */

public class ExtintorMangueiraDAO implements IExtintorMangueiraDAO {

    private static SQLiteDatabase db;
    private static ExtintorMangueiraDAO instancia;
    private Banco banco;
    private Context contexto;

    public ExtintorMangueiraDAO(Context contexto){
        banco =  Banco.getInstance(contexto);
        this.contexto = contexto;
    }

    public static ExtintorMangueiraDAO getInstance(Context contexto){
        if(instancia == null){
            synchronized (ExtintorMangueiraDAO.class){
                instancia = new ExtintorMangueiraDAO(contexto);
            }
        }
        return instancia;
    }

    @Override
    public void inserir(ExtintorMangueira extintorMangueira) throws DefaultException {

        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("emaid", banco.getSequencial("extintormangueiras"));
        preencher(extintorMangueira, valores);
        Log.i("INSERIU Mangueira..: " , ""+ db.insert("extintormangueiras", null, valores));
        db.close();
    }

    @Override
    public void alterar(ExtintorMangueira extintorMangueirae) throws DefaultException {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("emaid", extintorMangueirae.getId());
        preencher(extintorMangueirae, valores);
        db.update("extintormangueiras", valores, "emaid = ? ",
                new String[]{String.valueOf(extintorMangueirae.getId())});
        db.close();
    }

    @Override
    public void deletar(int idMangueira) throws DefaultException {
        db = banco.getWritableDatabase();
        db.delete("extintormangueiras", "emaid = ?", new String[]{String.valueOf(idMangueira)});
        db.close();
    }

    @Override
    public List<ExtintorMangueira> buscar(int tipoConsulta, String informacao) throws Exception, DefaultException {
        List<ExtintorMangueira> extintorMangueiras = new ArrayList<ExtintorMangueira>();
        String consulta = "SELECT * FROM extintormangueiras  ";
        db = banco.getReadableDatabase();
        Cursor cursor = null;
        if (informacao.equals("")) {
            if(tipoConsulta == 3){
                consulta += " WHERE EMASTATUS = 0 ";
                cursor = db.rawQuery(consulta, null);
            }else if(tipoConsulta == 4){
                consulta += " WHERE EMASTATUS = 1 ";
                cursor = db.rawQuery(consulta, null);
            }else{
                cursor = db.query("extintormangueiras",null,null,null,null,null,"emaid ASC");
            }
        }else if(tipoConsulta == 0){
            consulta = consulta + " WHERE ORCCODIGO = ? ";
        }else if(tipoConsulta == 3){
            consulta = consulta + " WHERE ORCCODIGO = ? AND EMASTATUS = 0";
        }else if(tipoConsulta == 4){
            consulta = consulta + " WHERE ORCCODIGO = ? AND EMASTATUS = 1";
        }
        if (!informacao.equals("")) {
            if(tipoConsulta == 2){
                consulta += " WHERE EMASTATUS = 0 ";
                cursor = db.rawQuery(consulta, null);
            }else{
                cursor = db.rawQuery(consulta, new String[]{informacao});
            }
        }

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                ExtintorMangueira extintorMangueira = new ExtintorMangueira();
                preencher(extintorMangueira, cursor);
                extintorMangueiras.add(extintorMangueira);
            }while(cursor.moveToNext());
        }
        db.close();
        return extintorMangueiras;
    }

    private void preencher(ExtintorMangueira extintorMangueira, Cursor cursor) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ClienteModel clienteModel = new ClienteModel(contexto);
        extintorMangueira.setId(cursor.getInt(0));
        extintorMangueira.getCliente().setCliId(cursor.getInt(1));
        extintorMangueira.setCliente(clienteModel.tratarBuscaCliente(String.valueOf(extintorMangueira.getCliente().getCliId())));
        extintorMangueira.setOrcCodigo(cursor.getInt(2));
        extintorMangueira.setData(new java.sql.Date(dateFormat.parse(cursor.getString(3)).getTime()));
        extintorMangueira.setOrdem(cursor.getInt(4));
        extintorMangueira.setIdentificacao(cursor.getString(5));
        extintorMangueira.setMarcaDuto(cursor.getString(6));
        extintorMangueira.setMarcaUniao(cursor.getString(7));
        extintorMangueira.setDiametro(cursor.getInt(8));
        extintorMangueira.setCompNormal(cursor.getInt(9));
        extintorMangueira.setTipo(cursor.getInt(10));
        extintorMangueira.setMesAnoFabricacao(cursor.getString(11));
        extintorMangueira.setPressaoEnsaio(cursor.getString(12));
        extintorMangueira.setProximaInspecao(cursor.getString(13));
        extintorMangueira.setProximaManutencao(cursor.getString(14));
        extintorMangueira.setCompReal(cursor.getString(15));
        extintorMangueira.setCarcacaRevestimento(cursor.getInt(16));
        extintorMangueira.setUnioes(cursor.getInt(17));
        extintorMangueira.setCompLuva(cursor.getString(18));
        extintorMangueira.setVedacaoBorracha(cursor.getInt(19));
        extintorMangueira.setMarcacao(cursor.getInt(20));
        extintorMangueira.setEnsaioHidrostatico(cursor.getInt(21));
        extintorMangueira.setReempatacao(cursor.getInt(22));
        extintorMangueira.setCompFinal(cursor.getString(23));
        extintorMangueira.setSubUnioes(cursor.getInt(24));
        extintorMangueira.setSubVedacoes(cursor.getInt(25));
        extintorMangueira.setSubAneis(cursor.getInt(26));
        extintorMangueira.setNovoEnsaioHidrostatico(cursor.getInt(27));
        extintorMangueira.setLimpeza(cursor.getInt(28));
        extintorMangueira.setSecagem(cursor.getInt(29));
        extintorMangueira.setResultadoFinal(cursor.getInt(30));
        extintorMangueira.setStatus(cursor.getInt(31));
        extintorMangueira.setNoExtintor(cursor.getString(32));
        extintorMangueira.setDescPeca(cursor.getString(33));
        extintorMangueira.setNOrdem(cursor.getString(34));
    }

    private void preencher(ExtintorMangueira extintorMangueira, ContentValues valores) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        valores.put("CLIID", extintorMangueira.getCliente().getCliId());
        valores.put("ORCCODIGO", extintorMangueira.getOrcCodigo());
        valores.put("EMADATA", dateFormat.format(extintorMangueira.getData()));
        valores.put("EMAORDEM", extintorMangueira.getOrdem());
        valores.put("EMAIDENTIFICACAO", extintorMangueira.getIdentificacao());
        valores.put("EMAMARCADODUTO", extintorMangueira.getMarcaDuto());
        valores.put("EMAMARCADAUNIAO", extintorMangueira.getMarcaUniao());
        valores.put("EMADIAMETRO", extintorMangueira.getDiametro());
        valores.put("EMACOMPNORMAL", extintorMangueira.getCompNormal());
        valores.put("EMATIPO", extintorMangueira.getTipo());
        valores.put("EMAMESANOFABRICACAO", extintorMangueira.getMesAnoFabricacao());
        valores.put("EMAPRESSAOENSAIO ", extintorMangueira.getPressaoEnsaio());
        valores.put("EMAPROXIMAINSPECAO", extintorMangueira.getProximaInspecao());
        valores.put("EMAPROXIMAMANUTENCAO", extintorMangueira.getProximaManutencao());
        valores.put("EMACOMPRIMENTOREAL", extintorMangueira.getCompReal());
        valores.put("EMACARCACAREVESTIMENTO", extintorMangueira.getCarcacaRevestimento());
        valores.put("EMAUNIOES", extintorMangueira.getUnioes());
        valores.put("EMACOMPLUVA", extintorMangueira.getCompLuva());
        valores.put("EMAVEDACAOBORRACHA", extintorMangueira.getVedacaoBorracha());
        valores.put("EMAMARCACAO", extintorMangueira.getMarcacao());
        valores.put("EMAENSAIOHIDROSTATICO", extintorMangueira.getEnsaioHidrostatico());
        valores.put("EMAREEMPATACAO", extintorMangueira.getReempatacao());
        valores.put("EMACOMPRIMENTOFINAL", extintorMangueira.getCompFinal());
        valores.put("EMASUBSTITUICAOUNIOES", extintorMangueira.getSubUnioes());
        valores.put("EMASUBSTITUICAOVEDACOES", extintorMangueira.getSubVedacoes());
        valores.put("EMASUBSTITUICAOANEIS", extintorMangueira.getSubAneis());
        valores.put("EMANOVOENSAIOHIDROSTATICO", extintorMangueira.getNovoEnsaioHidrostatico());
        valores.put("EMALIMPEZA", extintorMangueira.getLimpeza());
        valores.put("EMASECAGEM", extintorMangueira.getSecagem());
        valores.put("EMARESULTADOFINAL", extintorMangueira.getResultadoFinal());
        valores.put("EMASTATUS", extintorMangueira.getStatus());
        valores.put("EMANOEXTINTOR", extintorMangueira.getNoExtintor());
        valores.put("EMADESCPECA", extintorMangueira.getDescPeca());
        valores.put("EMANORDEM", extintorMangueira.getNOrdem());
    }

    public void setStatus(int idControle, int status) throws DefaultException, Exception{
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();
        valores.put("EMASTATUS", status);
        db.update("extintormangueiras", valores, "emaid = ? ",
                new String[]{String.valueOf(idControle)});
        db.close();
    }

    @Override
    public ExtintorMangueira bucarExtintorMangueira(int idMangueira) throws DefaultException {
        return null;
    }

    public void deleteAll(){
        db = banco.getWritableDatabase();
        db.delete("extintormangueiras", null, null);
        db.close();
    }
}
