package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import java.util.List;

import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;
import appmecrel.com.br.appmecrel.model.dao.EXFabricanteDAO;

/**
 * Created by usuario on 25/08/2015.
 */
public class EXFabricanteModel {


    private Context contexto;
    private EXFabricanteDAO exFabricanteDAO;



    public EXFabricanteModel(Context contexto) {
        this.contexto = contexto;
    }

    public void tratarInsercao(EXFabricante exFabricante){

        exFabricanteDAO = exFabricanteDAO.getInstance(contexto);
        try {
            exFabricanteDAO.inserir(exFabricante);
        } catch (DefaultException e) {
            e.getMensagem();
        }

    }

    public void tratarAlteracao(EXFabricante exFabricante){
        if(exFabricante.getNome().equals("")){

        }else{
            exFabricanteDAO =exFabricanteDAO.getInstance(contexto);
            try{
                exFabricanteDAO.alterar(exFabricante);
            }catch(DefaultException e){
                e.getMessage();
            }
        }
    }

    public void tratarExclusao(int exFabricanteId){
        if(exFabricanteId <= 0){}
        else{
            exFabricanteDAO = exFabricanteDAO.getInstance(contexto);
            try{
                exFabricanteDAO.deletar(exFabricanteId);
            }catch(DefaultException e){ e.getMessage(); }

        }
    }

    public EXFabricante tratarBusca(String exFabricanteId){
        exFabricanteDAO = exFabricanteDAO.getInstance(contexto);
        try {
            return exFabricanteDAO.buscarFabricante(Integer.parseInt(exFabricanteId));
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    public List<EXFabricante> tratarBusca(){
        exFabricanteDAO = exFabricanteDAO.getInstance(contexto);
        try {
            return exFabricanteDAO.buscar();
        } catch (DefaultException e) {
            e.getMensagem();
            return  null;
        }
    }
}
