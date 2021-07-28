package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.util.API;


public class PrincipalActivity extends Activity {


    protected Button btnControle, btnConsulta, btnConfiguracoes, btnCarga, btnExtintorMangueira,
                     btnExtintorMangueiraConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inicializaObjetos();
    }

    private void inicializaObjetos() {
        API.geraConfiguracoes(this);
        btnControle = (Button) findViewById(R.id.btnControle);
        btnConsulta = (Button) findViewById(R.id.btnConsulta);
        btnCarga = (Button) findViewById(R.id.btnCarga);
        btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracoes);
        btnExtintorMangueira = (Button) findViewById(R.id.btnExtintorMangueira);
        btnExtintorMangueiraConsulta = (Button) findViewById(R.id.btnExtintorMangueiraConsulta);

        btnControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, ControleExtintorActivity.class);
                startActivity(intent);
            }
        });
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, ControleExtintor1Activity.class);
                startActivity(intent);
            }
        });
        btnCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, CargaActivity.class);
                startActivity(intent);
            }
        });
        btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, ConfiguracoesActivity.class);
                startActivity(intent);
            }
        });
        btnExtintorMangueira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, ControleMangueiraActivity.class);
                startActivity(intent);
            }
        });

        btnExtintorMangueiraConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(PrincipalActivity.this, ControleMangueira1Activity.class);
                startActivity(intent);
            }
        });

        if(!API.senhaEntrada.equals("crtncx724"))
            btnConfiguracoes.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
}
