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
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.listas.ExControleItemList;
import appmecrel.com.br.appmecrel.model.listas.ExControleListConsulta;
import appmecrel.com.br.appmecrel.model.listas.ExtintorMangueiraConsulta;
import appmecrel.com.br.appmecrel.model.listas.ExtintorMangueiraItemList;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;
import appmecrel.com.br.appmecrel.model.validacao.ExtintorMangueiraModel;

public class ControleMangueira1Activity extends Activity {

    private ListView lvMang;
    private ExtintorMangueiraConsulta extintorMangueiraConsulta;
    private ArrayList<ExtintorMangueiraItemList> itens;
    private LinearLayout linearLayout;
    //private int imagemFundo = R.drawable.fundoconsulta;
    //private int imagemFundoLands = R.drawable.fundolands;
    private Spinner spMangConsulta;
    private ImageView btnMangConsulta;
    private ExtintorMangueiraModel extintorMangueiraModel;
    private EditText edtMangConsulta;
    private int tela = 0;
    int currentOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_mangueira1);
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
        extintorMangueiraModel = new ExtintorMangueiraModel(ControleMangueira1Activity.this);

        lvMang = (ListView) findViewById(R.id.lvMang);
        lvMang.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<CharSequence> adapterTiposConsulta = ArrayAdapter.createFromResource(this, R.array.tipo_consulta, R.layout.spinner_item);
        adapterTiposConsulta.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMangConsulta = (Spinner) findViewById(R.id.spMangConsulta);
        spMangConsulta.setAdapter(adapterTiposConsulta);

        btnMangConsulta = (ImageView) findViewById(R.id.btnMangConsulta);
        edtMangConsulta = (EditText) findViewById(R.id.edtMangConsulta);
        btnMangConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtMangConsulta.getText().length() > 0)
                {
                    listaControles(spMangConsulta.getSelectedItemPosition(), edtMangConsulta.getText().toString());
                }else{
                    if( spMangConsulta.getSelectedItemPosition() == 3)
                        listaControles(4, edtMangConsulta.getText().toString());
                    else if ( spMangConsulta.getSelectedItemPosition() == 2)
                        listaControles(3, edtMangConsulta.getText().toString());
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
            List<ExtintorMangueira> extintorMangueiras;
            extintorMangueiras = extintorMangueiraModel.tratarBusca(tipoConsulta, informacao);
            itens = new ArrayList<ExtintorMangueiraItemList>();
            for (ExtintorMangueira item : extintorMangueiras) {
                ExtintorMangueiraItemList extintorMangueiraItemList = new ExtintorMangueiraItemList(item);
                itens.add(extintorMangueiraItemList);
            }
            extintorMangueiraConsulta = new ExtintorMangueiraConsulta(ControleMangueira1Activity.this, itens);
            lvMang.setAdapter(extintorMangueiraConsulta);
            lvMang.setCacheColorHint(Color.TRANSPARENT);
        }
        catch(Exception e){

        }
    }

    private void mudaImagemFundo(){
        currentOrientation = getResources().getConfiguration().orientation;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1) {
            listaControles(0, "");
        }
    }

}
