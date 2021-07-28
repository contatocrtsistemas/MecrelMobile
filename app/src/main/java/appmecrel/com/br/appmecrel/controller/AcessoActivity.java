package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.util.API;
import appmecrel.com.br.appmecrel.util.Funcoes;

public class AcessoActivity extends Activity {

    private Button btnAcesso;
    private EditText edtAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acesso);
        inicializaObjetos();
        eventosClicks();
    }

    @Override
    protected  void onResume(){
        super.onResume();
    }

    private void eventosClicks(){
        btnAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtAcesso.getText().toString().equals("crtncx724")){
                    if (API.verificaChave(AcessoActivity.this)) {
                        if (!edtAcesso.getText().toString().equals("")) {
                            if (edtAcesso.getText().toString().equals("123")) {
                                API.senhaEntrada = edtAcesso.getText().toString();
                                edtAcesso.setText(null);
                                if(API.msgChaveSistema.equals("")){
                                    Intent intent = new Intent();
                                    intent.setClass(AcessoActivity.this, PrincipalActivity.class);
                                    startActivity(intent);
                                }else{
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(AcessoActivity.this);
                                    alerta.setTitle("Atenção!!");
                                    alerta.setMessage(API.msgChaveSistema);
                                    alerta.setCancelable(false);
                                    alerta.setNeutralButton("Gerar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setClass(AcessoActivity.this, ChaveActivity.class);
                                            startActivityForResult(intent, 1);
                                        }
                                    });
                                    alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setClass(AcessoActivity.this, PrincipalActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                    alerta.show();
                                }
                            } else {
                                Funcoes.exibirMensagem(AcessoActivity.this, "Erro", "Senha incorreta!");
                                edtAcesso.setText(null);
                            }
                        } else {
                            Funcoes.exibirMensagem(AcessoActivity.this, "Erro", "Necessário informar a senha!");
                        }
                    }else {
                        if (!edtAcesso.getText().toString().equals("")) {
                            if (edtAcesso.getText().toString().equals("123")) {
                                API.senhaEntrada = edtAcesso.getText().toString();
                                edtAcesso.setText(null);
                                Intent intent = new Intent();
                                intent.setClass(AcessoActivity.this, ChaveActivity.class);
                                startActivityForResult(intent, 1);
                            } else {
                                Funcoes.exibirMensagem(AcessoActivity.this, "Erro", "Senha incorreta!");
                                edtAcesso.setText(null);
                            }
                        } else {
                            Funcoes.exibirMensagem(AcessoActivity.this, "Erro", "Necessário informar a senha!");
                        }
                    }
                }else{
                    API.senhaEntrada = edtAcesso.getText().toString();
                    edtAcesso.setText(null);
                    Intent intent = new Intent();
                    intent.setClass(AcessoActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void inicializaObjetos(){
        API.geraConfiguracoes(this);
        btnAcesso = (Button) findViewById(R.id.btnAcesso);
        edtAcesso = (EditText) findViewById(R.id.edtAcesso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acesso, menu);
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



    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == 1){
            Intent intent = new Intent();
            intent.setClass(AcessoActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }else if(resultCode == 2){
            finish();
        }
    }
}
