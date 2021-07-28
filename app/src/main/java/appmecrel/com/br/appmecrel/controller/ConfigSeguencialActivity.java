package appmecrel.com.br.appmecrel.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.Sequencial;
import appmecrel.com.br.appmecrel.model.validacao.SequencialModel;

/**
 * Created by Lucas on 19/06/2015.
 */
public class ConfigSeguencialActivity extends Fragment {

    private EditText edtSequencialControle;
    private SequencialModel sequencialModel;
    private ArrayList<Sequencial> sequencials;
    int posicao = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.configuracoes_sequencial, container, false);
        inicializaObjetos(v);
        return v;
    }

    @Override
    public void onStop(){
        if(posicao > -1){
            sequencials.get(posicao).setProximo(edtSequencialControle.getText().toString());
            sequencialModel.tratarAlteracao(sequencials.get(posicao));
        }
        super.onStop();
    }

    private void inicializaObjetos(View view) {
        edtSequencialControle = (EditText) view.findViewById(R.id.edtSequencialControle);
        sequencialModel = new SequencialModel(getActivity());
        sequencials = sequencialModel.tratarBusca();
        edtSequencialControle.setText(retornaSequencial("excontrole"));
    }

    private String retornaSequencial(String tabela){
        String valor = "";
        int i = 0;
        for(Sequencial item : sequencials){
            if(item.getTabela().equals(tabela)){
                valor = item.getProximo();
                posicao = i;
                Log.i("SEquencial", "" + posicao + "  " +  tabela);
            }
            i++;
        }
        return valor;
    }
}
