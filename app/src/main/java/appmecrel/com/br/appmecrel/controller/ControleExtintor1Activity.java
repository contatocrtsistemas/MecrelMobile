package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.listas.ExControleItemList;
import appmecrel.com.br.appmecrel.model.listas.ExControleListConsulta;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;

public class ControleExtintor1Activity extends Activity {

    private ListView lvControle;
    private ExControleListConsulta exControleList;
    private ArrayList<ExControleItemList> itens;
    private LinearLayout linearLayout;
    //private int imagemFundo = R.drawable.fundoconsulta;
    //private int imagemFundoLands = R.drawable.fundolands;
    private Spinner spControleConsulta;
    private ImageView btnControleConsulta;
    private EXControleModel exControleModel;
    private EditText edtControleConsulta;
    private int tela = 0;
    int currentOrientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_extintor1);
        inicializaObjetos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controle_extintor1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onResume(){
        super.onResume();
        //mudaImagemFundo();
    }

    private void inicializaObjetos(){
        mudaImagemFundo();
        exControleModel = new EXControleModel(ControleExtintor1Activity.this);

        lvControle = (ListView) findViewById(R.id.lvControleExtintores);
        lvControle.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<CharSequence> adapterTiposConsulta = ArrayAdapter.createFromResource(this, R.array.tipo_consulta, R.layout.spinner_item);
        adapterTiposConsulta.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spControleConsulta = (Spinner) findViewById(R.id.spControleConsulta);
        spControleConsulta.setAdapter(adapterTiposConsulta);

        btnControleConsulta = (ImageView) findViewById(R.id.btnControleConsulta);
        edtControleConsulta = (EditText) findViewById(R.id.edtControleConsulta);
        btnControleConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtControleConsulta.getText().length() > 0)
                {
                    listaControles(spControleConsulta.getSelectedItemPosition(), edtControleConsulta.getText().toString());
                }else{
                    if( spControleConsulta.getSelectedItemPosition() == 3)
                        listaControles(4, edtControleConsulta.getText().toString());
                    else if ( spControleConsulta.getSelectedItemPosition() == 2)
                        listaControles(3, edtControleConsulta.getText().toString());
                    else
                        listaControles(0, "");
                }
            }
        });
        listaControles(0, "");
    }

    private void eventosClicks(){

    }

    private void listaControles(int tipoConsulta, String informacao){
        try{
            List<EXControle> exControles;
            exControles = exControleModel.tratarBusca(tipoConsulta, informacao);
            itens = new ArrayList<ExControleItemList>();
            for (EXControle item : exControles) {
                ExControleItemList exControleItemList = new ExControleItemList(item);
                itens.add(exControleItemList);
            }
            exControleList = new ExControleListConsulta(ControleExtintor1Activity.this, itens);
            lvControle.setAdapter(exControleList);
            lvControle.setCacheColorHint(Color.TRANSPARENT);
        }
        catch(Exception e){

        }
    }

    private void mudaImagemFundo(){

        currentOrientation = getResources().getConfiguration().orientation;
        /*
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout = (LinearLayout) findViewById(R.id.layoutCliente2Lands);
            Funcoes.inserirImagemFundo(getApplication(), linearLayout, imagemFundoLands);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            linearLayout = (LinearLayout) findViewById(R.id.layoutCliente2);
            Funcoes.inserirImagemFundo(getApplication(), linearLayout,imagemFundo);
        }*/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1) {
            listaControles(0, "");
        }
    }

}
