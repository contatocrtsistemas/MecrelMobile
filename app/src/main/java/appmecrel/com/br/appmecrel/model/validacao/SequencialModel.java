package appmecrel.com.br.appmecrel.model.validacao;

import android.content.Context;

import java.util.ArrayList;

import appmecrel.com.br.appmecrel.model.bean.Sequencial;
import appmecrel.com.br.appmecrel.model.dao.SequencialDAO;

/**
 * Created by Lucas on 22/06/2015.
 */

public class SequencialModel {

    private Context contexto;
    private SequencialDAO sequencialDAO;

    public SequencialModel(Context contexto) {
        this.contexto = contexto;
    }

    public void tratarAlteracao(Sequencial sequencial){
        sequencialDAO = new SequencialDAO(contexto);
        try{
            sequencialDAO.alteraSequencial(sequencial);
        }catch (Exception e){

        }
    }

    public ArrayList<Sequencial> tratarBusca(){
        sequencialDAO = new SequencialDAO(contexto);//sequencialDAO.getInstance(contexto);
        try {
            return sequencialDAO.getSequenciais();
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }
}
