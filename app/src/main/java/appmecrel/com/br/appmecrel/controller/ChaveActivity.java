package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.util.API;
import appmecrel.com.br.appmecrel.util.Funcoes;


public class ChaveActivity extends Activity {

    int currentOrientation;
    private LinearLayout linearLayout;
    private int imagemFundo = R.drawable.telalogin;
    private TextView tvCnpj;
    private EditText edtChave;
    private Button btnChaveDestravar, btnChaveCancelar;
    private EditText edtLocal ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chave);
        inicializaObjetos();
        eventosClicks();
    }

    private void inicializaObjetos(){
       // mudaImagemFundo();
        API.geraConfiguracoes(this);

        tvCnpj = (TextView) findViewById(R.id.tvCnpj);
        edtChave = (EditText) findViewById(R.id.edtChave);
        btnChaveDestravar = (Button) findViewById(R.id.btnChaveDestravar);
        btnChaveCancelar = (Button) findViewById(R.id.btnChaveCancelar);
        String cnpjLocal;
        if(!API.configuracoes.getCnpj().equals(""))
            cnpjLocal = API.configuracoes.getCnpj();
        else
            cnpjLocal = "14377073000101";
        String cnpj = "";
        if(cnpjLocal.length() == 14){
            cnpj = cnpjLocal.substring(0,2) + ".";
            cnpj += cnpjLocal.substring(2,5) + ".";
            cnpj += cnpjLocal.substring(5,8) + "/";
            cnpj += cnpjLocal.substring(8,12) + "-";
            cnpj += cnpjLocal.substring(12,14);
        }else{
            cnpj = cnpjLocal;
        }

        tvCnpj.setText(cnpj);
    }

    public void eventosClicks(){
        btnChaveDestravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String strLocal = edtChave.getText().toString();
                    String strDataVencimento = "";
                    Date dataAtual = new Date();
                    Date dataVencimento;
                    String cnpj;
                    if(!API.configuracoes.getCnpj().equals(""))
                        cnpj = API.configuracoes.getCnpj();
                    else
                        cnpj = "14377073000101";
                    strDataVencimento = strLocal.substring(16, 17) + strLocal.substring(4, 5) + '/' + strLocal.substring(2, 3)
                            + strLocal.substring(3, 4) + '/' + '2' + strLocal.substring(8, 9) + strLocal.substring(17, 18)
                            + strLocal.substring(15, 16);
                    //Log.i("TESTE")
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dataVencimento = new java.sql.Date(format.parse(strDataVencimento).getTime());
                    Calendar c = Calendar.getInstance();
                    c.setTime(dataAtual);
                    c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);

                    Log.i("TESTE", format.format(dataVencimento.getTime()));
                    Log.i("ATUAL", format.format(dataAtual.getTime()));
                    Log.i("VENCIMENTO + 1", format.format(c.getTime()));
                    if (dataAtual.after(dataVencimento) /*|| (dataVencimento.after(c.getTime()))*/
                            || (!cnpj.substring(5, 6).equals(strLocal.substring(0, 1)))
                            ||(!cnpj.substring(4, 5).equals(strLocal.substring(5, 6)))
                            ||(!cnpj.substring(3, 4).equals(strLocal.substring(7, 8)))
                            ||(!cnpj.substring(6, 7).equals(strLocal.substring(12, 13)))
                            ||(!cnpj.substring(1, 2).equals(strLocal.substring(14, 15)))){
                        Funcoes.exibirMensagem(ChaveActivity.this, "Controle CRT", "Codigo Invalido!");
                    }else{
                        API.configuracoes.setChaveSistema(strLocal);
                        API.gravarConfiguracoes();
                        setResult(1);
                        finish();
                    }

                    }catch (Exception e){
                        Log.i("TESTE", e.getMessage());
                }
            }
        });

        btnChaveCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(2);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chave, menu);
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
