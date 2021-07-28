package appmecrel.com.br.appmecrel.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import appmecrel.com.br.appmecrel.R;


/**
 * Created by usuario on 19/06/2015.
 */
public class ConfigConsultaSqlActivity extends Fragment {

    private ListView lvConsultaSQL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(  R.layout.configuracoes_consultasql, container, false);
        inicializaObjetos(v);
        return v;
    }

    private void inicializaObjetos(View view) {
        lvConsultaSQL = (ListView) view.findViewById(R.id.lvConsultaSQL);
    }
}
