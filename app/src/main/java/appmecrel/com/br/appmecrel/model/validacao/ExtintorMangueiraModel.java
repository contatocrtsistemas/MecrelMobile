package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.dao.EXControleDAO;
import appmecrel.com.br.appmecrel.model.dao.ExtintorMangueiraDAO;

/**
 * Created by usuario on 25/08/2015.
 */
public class ExtintorMangueiraModel {


    private Context contexto;
    private ExtintorMangueiraDAO extintorMangueiraDAO;



    public ExtintorMangueiraModel(Context contexto) {
        this.contexto = contexto;
    }

    public void tratarInsercao(ExtintorMangueira extintorMangueira){
        /*  if (String.valueOf(extintorMangueira.getCliente().getCliId()).equals("0")) {

        } else {}*/
            extintorMangueiraDAO = extintorMangueiraDAO.getInstance(contexto);
            try {
                extintorMangueiraDAO.inserir(extintorMangueira);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        //}
    }

    public void tratarAlteracao(ExtintorMangueira extintorMangueira){
        if(String.valueOf(extintorMangueira.getCliente().getCliId()).equals("0")){

        }else{
            extintorMangueiraDAO = extintorMangueiraDAO.getInstance(contexto);
            try{
                extintorMangueiraDAO.alterar(extintorMangueira);
            }catch(DefaultException e){
                e.getMessage();
            }
        }
    }

    public void tratarExclusao(int extintorControleId){
        if(extintorControleId <= 0){}
        else{
            extintorMangueiraDAO = extintorMangueiraDAO.getInstance(contexto);
            try{
                extintorMangueiraDAO.deletar(extintorControleId);
            }catch(DefaultException e){ e.getMessage(); }

        }
    }

    public ExtintorMangueira tratarBusca(String extintorMangueiraId){
        extintorMangueiraDAO = extintorMangueiraDAO.getInstance(contexto);
        try {
            return extintorMangueiraDAO.bucarExtintorMangueira(Integer.parseInt(extintorMangueiraId));
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    public List<ExtintorMangueira> tratarBusca(int tipoConsulta, String informacao){
        extintorMangueiraDAO =extintorMangueiraDAO.getInstance(contexto);
        try {
            return extintorMangueiraDAO.buscar(tipoConsulta, informacao);
        } catch (DefaultException e) {
            e.getMensagem();
            return  null;
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    public void setStatus(int idControle,int status){
        extintorMangueiraDAO =extintorMangueiraDAO.getInstance(contexto);
        try {
            extintorMangueiraDAO.setStatus(idControle, status);
        } catch (DefaultException e) {
            e.getMensagem();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteAll(){
        extintorMangueiraDAO =extintorMangueiraDAO.getInstance(contexto);
        try {
            extintorMangueiraDAO.deleteAll();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
