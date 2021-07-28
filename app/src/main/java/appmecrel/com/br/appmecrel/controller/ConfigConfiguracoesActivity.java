package appmecrel.com.br.appmecrel.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.util.API;

/**
 * Created by usuario on 19/06/2015.
 */
public class ConfigConfiguracoesActivity extends Fragment {

    private CheckBox chkEnviar;
    private RadioButton radDescontoItem, radDescontoValorTotal;
    private RadioGroup rag;
    private EditText edtCnpj;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    /*@Override
    public void onDestroy(){
        gravaConfiguracoes();
        super.onDestroy();

    }*/
    @Override
    public void onStop(){
        gravaConfiguracoes();
        super.onStop();
    }

    private void gravaConfiguracoes() {
        API.configuracoes.setCnpj(edtCnpj.getText().toString());
      //  API.configuracoes.setEnviar(chkEnviar.isChecked());
        API.gravarConfiguracoes();
    }

    private void inicializaObjetos(View view) {

        edtCnpj = (EditText) view.findViewById(R.id.edtCnpj);
        /*chkEnviar = (CheckBox) view.findViewById(R.id.chkEnviar);
        chkEnviar.setChecked(API.configuracoes.getEnviar().equals("1"));*/
        edtCnpj.setText(API.configuracoes.getCnpj());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.configuracoes_config, container, false);
        inicializaObjetos(v);
        return v;
    }

}
