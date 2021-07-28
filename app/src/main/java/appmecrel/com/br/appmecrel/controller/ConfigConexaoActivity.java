package appmecrel.com.br.appmecrel.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.util.API;

/**
 * Created by usuario on 19/06/2015.
 */
public class ConfigConexaoActivity  extends Fragment {

    private EditText edtConexaoServidor, edtConexaoPorta, edtConexaoDiretorio, edtConexaoUsuario, edtConexaoSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop(){
        gravarInformacoes();
        super.onStop();
    }
    /*@Override
    public void onDestroy(){
        gravarInformacoes();
        super.onStop();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.configuracoes_conexao, container, false);
        inicializaObjetos(v);
        return v;
    }

    private void inicializaObjetos(View view) {
        edtConexaoServidor = (EditText) view.findViewById(R.id.edtConexaoServidor);
        edtConexaoPorta = (EditText) view.findViewById(R.id.edtConexaoPorta);
        edtConexaoDiretorio = (EditText) view.findViewById(R.id.edtConexaoDiretorio);
        edtConexaoUsuario = (EditText) view.findViewById(R.id.edtConexaoUsuario);
        edtConexaoSenha = (EditText) view.findViewById(R.id.edtConexaoSenha);

        edtConexaoServidor.setText(API.configuracoes.getServidor());
        edtConexaoPorta.setText(API.configuracoes.getPorta());
        edtConexaoDiretorio.setText(API.configuracoes.getDiretorio());
        edtConexaoUsuario.setText(API.configuracoes.getUsuario());
        edtConexaoSenha.setText(API.configuracoes.getSenha());
    }

    private void gravarInformacoes(){
        API.configuracoes.setServidor(edtConexaoServidor.getText().toString());
        API.configuracoes.setPorta(edtConexaoPorta.getText().toString());
        API.configuracoes.setDiretorio(edtConexaoDiretorio.getText().toString());
        API.configuracoes.setUsuario(edtConexaoUsuario.getText().toString());
        API.configuracoes.setSenha(edtConexaoSenha.getText().toString());
        API.gravarConfiguracoes();
    }
}
