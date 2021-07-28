package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.dao.EXControleDAO;

/**
 * Created by usuario on 25/08/2015.
 */
public class EXControleModel {


    private Context contexto;
    private EXControleDAO exControleDAO;



    public EXControleModel(Context contexto) {
        this.contexto = contexto;
    }

    public void tratarInsercao(EXControle exControle){
        if (String.valueOf(exControle.getCodigo()).equals("")) {

        } else {
            exControleDAO = exControleDAO.getInstance(contexto);
            try {
                exControleDAO.inserir(exControle);
            } catch (DefaultException e) {
                e.getMensagem();
            }
        }
    }

    public void tratarAlteracao(EXControle exControle){
        if(String.valueOf(exControle.getCodigo()).equals("")){

        }else{
            exControleDAO =exControleDAO.getInstance(contexto);
            try{
                exControleDAO.alterar(exControle);
            }catch(DefaultException e){
                e.getMessage();
            }
        }
    }

    public void tratarExclusao(int exControleId){
        if(exControleId <= 0){}
        else{
            exControleDAO = exControleDAO.getInstance(contexto);
            try{
                exControleDAO.deletar(exControleId);
            }catch(DefaultException e){ e.getMessage(); }

        }
    }

    public EXControle tratarBusca(String exControleId){
        exControleDAO = exControleDAO.getInstance(contexto);
        try {
            return exControleDAO.buscarEXControle(Integer.parseInt(exControleId));
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    public List<EXControle> tratarBusca(int tipoConsulta, String informacao){
        exControleDAO =exControleDAO.getInstance(contexto);
        try {
            return exControleDAO.buscar(tipoConsulta, informacao);
        } catch (DefaultException e) {
            e.getMensagem();
            return  null;
        }
    }

    public void setStatus(int idControle,int status){
        exControleDAO =exControleDAO.getInstance(contexto);
        try {
            exControleDAO.setStatus(idControle, status);
        } catch (DefaultException e) {
            e.getMensagem();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteAll(){
        exControleDAO =exControleDAO.getInstance(contexto);
        try {
            exControleDAO.deleteAll();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
