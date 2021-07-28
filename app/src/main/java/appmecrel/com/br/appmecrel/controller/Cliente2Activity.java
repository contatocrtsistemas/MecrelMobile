package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.Cliente;
import appmecrel.com.br.appmecrel.model.listas.ClienteItemList;
import appmecrel.com.br.appmecrel.model.listas.ClienteList;
import appmecrel.com.br.appmecrel.model.validacao.ClienteModel;
import appmecrel.com.br.appmecrel.util.Funcoes;

public class Cliente2Activity extends Activity {


    private ListView lvClientes;
    private ClienteList clienteList;
//    private  ClienteAdapter clienteAdapter;
    private ArrayList<ClienteItemList> itens;
    private LinearLayout linearLayout;
    private int imagemFundo = R.drawable.fundoconsulta;
    private int imagemFundoLands = R.drawable.fundolands;
    private Spinner spClienteConsulta;
    private ImageView btnClienteConsulta;
    private ClienteModel clienteModel;
    private EditText edtClienteConsulta;
    private int tela = 0;
    int currentOrientation;
    ArrayList<String> clientesSelecao = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente2);
        inicializaObjetos();
        if(getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getStringExtra("consultaCliente") != null){
                Log.i("TESTE", "TELA 1");
                tela = 1;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        mudaImagemFundo();
    }

    private void inicializaObjetos(){
/*        linearLayout = (LinearLayout) findViewById(R.id.layoutCliente2);
        Funcoes.inserirImagemFundo(getApplication(), linearLayout,imagemFundo);
*/
        mudaImagemFundo();
        clienteModel = new ClienteModel(Cliente2Activity.this);

        lvClientes = (ListView) findViewById(R.id.lvClientes);
        lvClientes.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<CharSequence> adapterTiposConsulta = ArrayAdapter.createFromResource(this, R.array.tipoConsulta, R.layout.spinner_item);
        adapterTiposConsulta.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spClienteConsulta = (Spinner) findViewById(R.id.spClienteConsulta);
        spClienteConsulta.setAdapter(adapterTiposConsulta);

        btnClienteConsulta = (ImageView) findViewById(R.id.btnClienteConsulta);
        edtClienteConsulta = (EditText) findViewById(R.id.edtClienteConsulta);
        btnClienteConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtClienteConsulta.getText().length() > 0){
                    listaClientes(spClienteConsulta.getSelectedItemPosition(), edtClienteConsulta.getText().toString());
                }else{
                    listaClientes(0, "");
                }
            }
        });

        edtClienteConsulta.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(edtClienteConsulta.getText().length() > 0){
                        listaClientes(spClienteConsulta.getSelectedItemPosition(), edtClienteConsulta.getText().toString());
                    }else{
                        listaClientes(0, "");
                    }
                    return true;
                }
                return false;
            }
        });

        if (tela != 1)
            listaClientes(0, "");

            lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(tela == 1){

                        ClienteItemList item =  clienteList.getItem(position);
                        Intent intent = new Intent();
                        intent.putExtra("nome", item.getCliente().getCliNome());
                        intent.putExtra("id", String.valueOf(item.getCliente().getCliId()));
                        setResult(1, intent);
                        finish();
                        /*int posicao =  clienteAdapter.getPositionForSection(position);
                        Intent intent = new Intent();
                        Cliente clienteLocal = clienteModel.tratarBuscaCliente(clientesSelecao.get(posicao), false);
                        intent.putExtra("nome", clienteLocal.getCliNome());
                        intent.putExtra("id", String.valueOf(clienteLocal.getCliId()));
                        setResult(1, intent);
                        finish();*/
                    }
                }
            });
        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            listaClientes(0, "");
        }
    }

    private void listaClientes(int tipoConsulta, String informacao){
        try{
            List<Cliente> clientes;
            //clientes = clienteDao.buscar(tipoConsulta, informacao);
            clientes = clienteModel.tratarBusca(tipoConsulta, informacao);
            itens = new ArrayList<ClienteItemList>();
            //int i=0;
            for (Cliente item : clientes) {
              //  if(i < 50){
                    ClienteItemList clienteItem = new ClienteItemList(item);
                    itens.add(clienteItem);
                //    clientesSelecao.add(item.getCliNome());
                 //   i++;
               // }
            }
            clienteList = new ClienteList(Cliente2Activity.this, itens);
            lvClientes.setAdapter(clienteList);
            /*clienteAdapter = new ClienteAdapter(this, clientesSelecao);
            lvClientes.setAdapter(clienteAdapter);*/
            lvClientes.setCacheColorHint(Color.TRANSPARENT);
        }
        catch(Exception e){

        }
    }

    private void mudaImagemFundo(){

        currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Funcoes.inserirImagemFundo(getApplication(), linearLayout, imagemFundoLands);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            linearLayout = (LinearLayout) findViewById(R.id.layoutCliente2);
            Funcoes.inserirImagemFundo(getApplication(), linearLayout,imagemFundo);
        }
    }
}

