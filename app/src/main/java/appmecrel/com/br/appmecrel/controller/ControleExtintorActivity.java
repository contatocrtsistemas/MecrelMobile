package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;
import appmecrel.com.br.appmecrel.model.listas.ExControleItemList;
import appmecrel.com.br.appmecrel.model.listas.ExControleList;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;
import appmecrel.com.br.appmecrel.model.validacao.EXFabricanteModel;
import appmecrel.com.br.appmecrel.util.Funcoes;

public class ControleExtintorActivity extends Activity {

    private EditText edtDataRecarga, edtProximaRecarga, edtMesAno, edtNoCert, edtNoOS, edtNoFabricante,edtCapacidade, edtPTrab,
            edtNormaFabri,edtPesoPV, edtPesoPC, edtVL,edtCMax, edtENSAlta, edtENSBaixa, edtDVMcm3, edtDVM, edtNoOrdem,
            edtSeloCertificacao, edtObs , edtDVPcm3;
    private Spinner spMarca, spTipo,spNMant, spPintura,  spResultado, spCheioVazio;
    private Button btnConfirmaItem, btnCancelaItem, btnFinalizar, btnCancelar, btnSair;
    private ListView lvControleExtintores;
    private ArrayList<ExControleItemList> itens;
    private ExControleList exControleList;
    private Calendar cal = Calendar.getInstance();
    private int mYear = cal.get(Calendar.YEAR);
    private int mMonth = cal.get(Calendar.MONTH);
    private int mDay = cal.get(Calendar.DAY_OF_MONTH);
    private int opcao = 0;
    static final int DATE_DIALOG_ID = 0;
    private int posicao = -1;
    private EXControleModel exControleModel;
    private List<EXFabricante> exFabricantes;
    private EXFabricanteModel exFabricanteModel;
    private EXControle exControleLocal;
    private int tela;
    ProgressDialog dialog;
    List<EXControle> exControles;
    String arquivoExportacao = "";
    int contadorSequencial;
    boolean achouArquivo;
    private boolean mostraMengagem;
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_extintor);
        inicializaObjetos();
        eventosClicks();
        /*if (savedInstanceState != null) {
            itens = savedInstanceState.getParcelableArrayList("itens");
            pedidoList = new PedidoList(Pedido1Activity.this, itens);
            lvPedido.setAdapter(pedidoList);
            lvPedido.setCacheColorHint(Color.TRANSPARENT);
        } else {
            itens = new ArrayList<ExControleItemList>();
        }*/
        itens = new ArrayList<ExControleItemList>();

        if(getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getSerializableExtra("excontrole") != null){
                this.exControleLocal = (EXControle) intent.getSerializableExtra("excontrole");
                preencheControleLocal(exControleLocal);
                tela = 1;
            }
        }
    }

    private void preencheControleLocal(EXControle exControleLocal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtDataRecarga.setText(dateFormat.format(exControleLocal.getDataRecarga()));
        edtProximaRecarga.setText(dateFormat.format(exControleLocal.getDataProximaRecarga()));
        edtMesAno.setText(exControleLocal.getMesAno());
        edtNoCert.setText(String.valueOf(exControleLocal.getNumeroServico()));
        edtNoOS.setText(String.valueOf(exControleLocal.getCodigo()));
        edtNoFabricante.setText(exControleLocal.getNumero());
        edtCapacidade.setText(String.format("%.2f",exControleLocal.getCapacidade()));
        edtPTrab.setText(exControleLocal.getpTrabalho());
        edtNormaFabri.setText(exControleLocal.getNormaFabricacao());
        edtPesoPV.setText(String.format("%.2f",exControleLocal.getPesoPV()));
        edtPesoPC.setText(String.format("%.2f",exControleLocal.getPesoPC()));
        edtVL.setText(String.format("%.2f",exControleLocal.getvL()));
        edtCMax.setText(String.format("%.2f",exControleLocal.getcMaxima()));
        edtENSAlta.setText(String.format("%.2f",exControleLocal.getEnsAltaPressao()));
        edtENSBaixa.setText(String.format("%.2f",exControleLocal.getEnsBaixaPressao()));
        edtDVMcm3.setText(String.format("%.2f",exControleLocal.getDvm()));
        edtDVPcm3.setText(String.format("%.2f",exControleLocal.getDvp()));
        edtDVM.setText(String.format("%.2f",exControleLocal.getDvm10()));
        edtNoOrdem.setText(exControleLocal.getNumeroOrdem());
        edtSeloCertificacao.setText(exControleLocal.getSeloCertificacao());
        edtObs.setText(exControleLocal.getObs());
        spPintura.setSelection(exControleLocal.getPintura());
        spNMant.setSelection(exControleLocal.getpNivel());
        spResultado.setSelection(exControleLocal.getResultado());
        spCheioVazio.setSelection(exControleLocal.getCheioVazio());
        spTipo.setSelection(exControleLocal.getTipo());
        EXFabricanteModel exFabricanteModel = new EXFabricanteModel(ControleExtintorActivity.this);
        List<EXFabricante> exFabricantes = exFabricanteModel.tratarBusca();
        int posicao = 0;
        for(EXFabricante item : exFabricantes){
            if(exControleLocal.getFabricante().getId() == item.getId())
                break;
            posicao++;
        }

        spMarca.setSelection(posicao);
        btnConfirmaItem.setEnabled(false);
        btnCancelaItem.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_controle_extintor, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleExtintorActivity.this);
        alertDialog.setTitle("Sair");
        alertDialog.setMessage("Deseja realmente sair da tela?");
        alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ControleExtintorActivity.super.onBackPressed();
            }
        });
        alertDialog.setNegativeButton("Cancelar", null);
        alertDialog.show();
        /*if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inicializaObjetos(){
        edtDataRecarga = (EditText) findViewById(R.id.edtDataRecarga);
        edtProximaRecarga = (EditText) findViewById(R.id.edtProximaRecarga);
        edtMesAno = (EditText) findViewById(R.id.edtMesAno);
        edtNoCert = (EditText) findViewById(R.id.edtNoCert);
        edtNoOS = (EditText) findViewById(R.id.edtNoOS);
        edtNoFabricante = (EditText) findViewById(R.id.edtNoFabricante);
        edtCapacidade = (EditText) findViewById(R.id.edtCapacidade);
        edtPTrab       = (EditText) findViewById(R.id.edtPTrab);
        edtNormaFabri = (EditText) findViewById(R.id.edtNormaFabri);
        edtPesoPV = (EditText) findViewById(R.id.edtPesoPV);
        edtPesoPC = (EditText) findViewById(R.id.edtPesoPC);
        edtVL = (EditText) findViewById(R.id.edtVL);
        edtCMax = (EditText) findViewById(R.id.edtCMax);
        edtENSAlta = (EditText) findViewById(R.id.edtENSAlta);
        edtENSBaixa = (EditText) findViewById(R.id.edtENSBaixa);
        edtDVMcm3 = (EditText) findViewById(R.id.edtDVMcm3);
        edtDVPcm3 = (EditText) findViewById(R.id.edtDVPcm3);
        edtDVM = (EditText) findViewById(R.id.edtDVM);
        edtNoOrdem = (EditText) findViewById(R.id.edtNoOrdem);
        edtSeloCertificacao      = (EditText) findViewById(R.id.edtSeloCertificacao);
        edtObs = (EditText) findViewById(R.id.edtObs);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        spNMant = (Spinner) findViewById(R.id.spNMant);
        spPintura = (Spinner) findViewById(R.id.spPintura);
        spResultado= (Spinner) findViewById(R.id.spResultado);
        spCheioVazio= (Spinner) findViewById(R.id.spCV);
        lvControleExtintores = (ListView) findViewById(R.id.lvControleExtintores);
        btnConfirmaItem = (Button) findViewById(R.id.btnConfirmaItem);
        btnCancelaItem = (Button) findViewById(R.id.btnCancelaItem);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSair = (Button) findViewById(R.id.btnSair);
        exControleModel = new EXControleModel(ControleExtintorActivity.this);


        ArrayAdapter<CharSequence> adapterTipos = ArrayAdapter.createFromResource(this, R.array.tipos, R.layout.spinner_item);
        adapterTipos.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spTipo.setAdapter(adapterTipos);

        ArrayAdapter<CharSequence> adapterResultados = ArrayAdapter.createFromResource(this, R.array.resultado, R.layout.spinner_item);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spResultado.setAdapter(adapterResultados);
        spResultado.setSelection(5);

        ArrayAdapter<CharSequence> adapterNManutencao = ArrayAdapter.createFromResource(this, R.array.n_manutencao, R.layout.spinner_item);
        adapterNManutencao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spNMant.setAdapter(adapterNManutencao);

        ArrayAdapter<CharSequence> adapterPintura= ArrayAdapter.createFromResource(this, R.array.pintura, R.layout.spinner_item);
        adapterPintura.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spPintura.setAdapter(adapterPintura);

        ArrayAdapter<CharSequence> adapterCheioVazio = ArrayAdapter.createFromResource(this, R.array.cv, R.layout.spinner_item);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCheioVazio.setAdapter(adapterCheioVazio);

        ArrayList<String> marcas = new ArrayList<String>();
        exFabricanteModel = new EXFabricanteModel(ControleExtintorActivity.this);
        exFabricantes = exFabricanteModel.tratarBusca();
        int i = 0;
        for(EXFabricante item : exFabricantes) {
            marcas.add(i, item.getNome());
            i++;
        }
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, marcas);
        adapterMarcas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMarca.setAdapter(adapterMarcas);
    }

    private void eventosClicks(){

        btnConfirmaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNoOS.getText().toString().equals("")){
                    EXControle exControle = new EXControle();
                    preencheControle(exControle);
                    ExControleItemList exControleItemList = new ExControleItemList(exControle);
                    itens.add(exControleItemList);
                    exControleList = new ExControleList(ControleExtintorActivity.this, itens);
                    lvControleExtintores.setAdapter(exControleList);
                    lvControleExtintores.setCacheColorHint(Color.TRANSPARENT);
                    limpaTela(false);
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(ControleExtintorActivity.this);
                    alert.setTitle("Erro!");
                    alert.setMessage("Necessário informar o Numero da OS");
                    alert.setNeutralButton("OK", null);
                    alert.show();
                }
            }
        });

        lvControleExtintores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExControleItemList item = exControleList.getItem(position);
                exControleList.setSelectedView(view, position);
                posicao = position;
            }
        });

        btnCancelaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posicao >= 0){
                    itens.remove(posicao);
                    exControleList = new ExControleList(ControleExtintorActivity.this, itens);
                    lvControleExtintores.setAdapter(exControleList);
                    lvControleExtintores.setCacheColorHint(Color.TRANSPARENT);
                    posicao = -1;
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tela == 1){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleExtintorActivity.this);
                    alertDialog.setTitle("Sair");
                    alertDialog.setMessage("Deseja realmente sair da tela?");
                    alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    alertDialog.setNegativeButton("Cancelar", null);
                    alertDialog.show();
                }
                else
                    limpaTela(true);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    finalizaProcesso();
                }catch (Exception e){
                    Log.i("INSERIR", "ERRO AO INSERIR");
                }
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleExtintorActivity.this);
                alertDialog.setTitle("Sair");
                alertDialog.setMessage("Deseja realmente sair da tela?");
                alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialog.setNegativeButton("Cancelar", null);
                alertDialog.show();
            }
        });

        edtDataRecarga.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                opcao = 0;
                showDialog(DATE_DIALOG_ID);

            }
        });
        edtProximaRecarga.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                opcao = 1;
                showDialog(1);

            }
        });
     /*   edtMesAno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                opcao = 2;
                showDialog(2);

            }
        });*/
    }

    private void finalizaProcesso() throws Exception{
        List<EXControle> exControleList = new ArrayList<EXControle>();

        if(tela == 1){
            exControleLocal.setStatus(0);
            preencheControle(exControleLocal);
            exControleModel.tratarAlteracao(exControleLocal);
            exControleList.add(exControleLocal);
        }else{
            if(itens.size() > 0){
                for(ExControleItemList item : itens){
                    item.getExControle().setStatus(0);
                    exControleModel.tratarInsercao(item.getExControle());
                    exControleList.add(item.getExControle());
                }
                mostraMengagem = true;
            }else{
                mostraMengagem = false;
                Funcoes.exibirMensagem(ControleExtintorActivity.this, "Controle de Extintores", "Nenhum item foi adicionado!");
            }
            /*if(API.configuracoes.getEnviar().equals("1")){
               // exportar();
            }*/
        }

        if(tela == 0){
            limpaTela(true);
            if(mostraMengagem)
                Funcoes.exibirMensagem(ControleExtintorActivity.this, "Controle de Extintores", "Operação realizado com sucesso!");
        }
        if(tela == 1){
            limpaTela(true);
            AlertDialog.Builder alert = new AlertDialog.Builder(ControleExtintorActivity.this);
            alert.setMessage("Operação realizado com sucesso!");
            alert.setTitle("Controle de Extintores");
            alert.setCancelable(false);
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setResult(1);
                    finish();
                }
            });
            alert.show();
        }
    }
/*
    private void exportar() {
        ConnectivityManager connMgr =  (ConnectivityManager) getSystemService ( this.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if  ( networkInfo !=  null  && networkInfo . isConnected ())  {
            Log.i("TESTE", "Com internet");
//            dialog = ProgressDialog.show(this, "Crt Sitemas", "Sincronizando Dados . . .", false, true);
  //          dialog.setCancelable(false);
            try
            {
                geraExportacao();
            }
            catch (Exception e)
            {
        //        dialog.dismiss();
                e.printStackTrace();
                Log.i("TESTE","Erro : " + e.getMessage());
            }
        }  else  {
            Funcoes.exibirMensagem(this, "Erro", "Sem conexão com a internet");
            Log.i("TESTE", "Sem internet");
        }


    }

    private void geraExportacao() throws Exception{
        criarTxt();
        upload();
        Log.i("TESTE", "VEIO AKI 2");
        if((exControles != null) && (exControles.size() > 0))
            for(EXControle item : exControles){
                item.setStatus(1);
                exControleModel.tratarAlteracao(item);
            }
    }

    private void upload() throws Exception {
        ConexaoFTP cone = new ConexaoFTP();
        String lstrArq = String.format("/EXCONTROLEPALM.txt");
        cone.conectar(API.configuracoes.getServidor(), API.configuracoes.getUsuario(), API.configuracoes.getSenha(),
                Integer.parseInt(API.configuracoes.getPorta()));
        Log.i("TESTE", "VEIO AKI");
        cone.uploadArquivo(lstrArq, API.configuracoes.getDiretorio()+lstrArq);
       // achouArquivo = achouArquivo();
        cone.desconectar();
    }

   private boolean achouArquivo() throws Exception {
        boolean achou = false;
        String lstrArq = String.format("EXCONTROLEPALM.txt");
        FTPFile[] arquivos = null;//= cone.listarDiretorios(API.configuracoes.getDiretorio());

        if(arquivos != null) {
            int length = arquivos.length;
            for (int i = 0; i < length; ++i) {
                FTPFile f = arquivos[i];
                if (f.isFile()) {
                    if(f.getName().equals(lstrArq)){
                        achou = true;
                        break;
                    }
                    Log.i("TESTE",f.getName());
                }
            }
        }
        return achou;
    }

    private void criarTxt() throws IOException, Exception{
        String lstrNomeArq;
        File arq;
        byte[] dados;
        ArrayList<String> array = new ArrayList<String>();

        lstrNomeArq = String.format("/EXCONTROLEPALM.txt");
        arq = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), lstrNomeArq);
        FileOutputStream fos;
        contadorSequencial = 1;
        exProcessaControle();
        dados = arquivoExportacao.toString().getBytes();
        fos = new FileOutputStream(arq);
        fos.write(dados);
        fos.flush();
        fos.close();
    }

    private void exProcessaControle() throws Exception{
        if (exControles != null)
            exControles.clear();
        exControles = exControleModel.tratarBusca(2, "1");
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        for (EXControle exControle : exControles) {
            arquivoExportacao += contadorSequencial + "=" + exControle.getNumeroServico() + "|" + exControle.getCodigo() + "|" +
                    exControle.getNumero() + "|" + exControle.getMesAno() + "|" +
                    exControle.getFabricante().getId() + "|" + exControle.getTipo() + "|" +
                    exControle.getCapacidade() + "|" + exControle.getpTrabalho() + "|" +
                    exControle.getNormaFabricacao() + "|" + exControle.getpNivel() + "|" +
                    exControle.getPintura()+ "|" + exControle.getPesoPV() + "|" +
                    exControle.getEnsBaixaPressao()+ "|" + exControle.getPesoPC() + "|" +
                    exControle.getvL() + "|" + exControle.getcMaxima() + "|" +
                    exControle.getEnsAltaPressao() + "|" +exControle.getDvm()+ "|" +
                    exControle.getDvp() + "|" + exControle.getDvm10()+ "|" +
                    exControle.getResultado() + "|" + exControle.getNumeroOrdem() + "|" +
                    exControle.getSeloCertificacao() + "|" +dateFormat.format(exControle.getDataRecarga())+ "|" +
                    dateFormat.format(exControle.getDataProximaRecarga()) + "|" + exControle.getObs() + "|" + exControle.getId();
            arquivoExportacao += "\n";
            contadorSequencial++;
        }
    }
*/
    private void limpaTela(boolean limpaTudo) {
        edtDataRecarga.setText(null);
        edtProximaRecarga.setText(null);
        edtMesAno.setText(null);
        edtNoFabricante.setText(null);
        edtCapacidade.setText(null);
        edtPTrab.setText(null);
        edtNormaFabri.setText(null);
        edtPesoPV.setText(null);
        edtPesoPC.setText(null);
        edtVL.setText(null);
        edtCMax.setText(null);
        edtENSAlta.setText(null);
        edtENSBaixa.setText(null);
        edtDVMcm3.setText(null);
        edtDVPcm3.setText(null);
        edtDVM.setText(null);
        edtNoOrdem.setText(null);
        edtSeloCertificacao.setText(null);
        edtObs.setText(null);
        if(limpaTudo){
            edtNoCert.setText(null);
            edtNoOS.setText(null);
            itens.clear();
            exControleList = new ExControleList(ControleExtintorActivity.this, itens);
            lvControleExtintores.setAdapter(exControleList);
            lvControleExtintores.setCacheColorHint(Color.TRANSPARENT);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DATE_DIALOG_ID:
                opcao = 0;
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
            case 1:
                opcao = 1;
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
            case 2:
                opcao = 2;
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        if(opcao == 0) {
            edtDataRecarga.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay < 10 ? "0"+mDay : mDay).append("/").append(((mMonth + 1) < 10 ? "0"+ (mMonth + 1) : (mMonth + 1))).append("/")
                    .append(mYear).append(" "));
        }else if (opcao == 1){
            edtProximaRecarga.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay < 10 ? "0"+mDay : mDay).append("/").append(((mMonth + 1) < 10 ? "0"+ (mMonth + 1) : (mMonth + 1))).append("/")
                    .append(mYear).append(" "));
        }/*else if (opcao == 2){
            edtMesAno.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(((mMonth + 1) < 10 ? "0"+ (mMonth + 1) : (mMonth + 1))).append("/")
                    .append(mYear).append(" "));
        }*/
    }

    private void preencheControle(EXControle exControle){
       try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            if (!edtDataRecarga.getText().toString().equals(""))
                exControle.setDataRecarga(new java.sql.Date(format.parse(edtDataRecarga.getText().toString()).getTime()));
           else
                exControle.setDataRecarga(new Date());
           if (!edtProximaRecarga.getText().toString().equals(""))
                exControle.setDataProximaRecarga(new java.sql.Date(format.parse(edtProximaRecarga.getText().toString()).getTime()));
           else
                exControle.setDataProximaRecarga(new Date());

            exControle.setMesAno(edtMesAno.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setNumeroServico((edtNoCert.getText().toString().equals("") ? 0 : Integer.parseInt(edtNoCert.getText().toString())));
            exControle.setCodigo(Integer.parseInt(edtNoOS.getText().toString()));
            exControle.setNumero(edtNoFabricante.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setCapacidade((edtCapacidade.getText().toString().equals("") ? 0 : Double.parseDouble(edtCapacidade.getText().toString().replaceAll("\\.", "").replace(",", "."))));
           exControle.setpTrabalho(edtPTrab.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setNormaFabricacao(edtNormaFabri.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setPesoPV((edtPesoPV.getText().toString().equals("") ? 0 : Double.parseDouble(edtPesoPV.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setPesoPC((edtPesoPC.getText().toString().equals("") ? 0 : Double.parseDouble(edtPesoPC.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setvL((edtVL.getText().toString().equals("") ? 0 : Double.parseDouble(edtVL.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setcMaxima((edtCMax.getText().toString().equals("") ? 0 : Double.parseDouble(edtCMax.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setEnsAltaPressao((edtENSAlta.getText().toString().equals("") ? 0 : Double.parseDouble(edtENSAlta.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setEnsBaixaPressao((edtENSBaixa.getText().toString().equals("") ? 0 : Double.parseDouble(edtENSBaixa.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setDvm((edtDVMcm3.getText().toString().equals("") ? 0 : Double.parseDouble(edtDVMcm3.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setDvp((edtDVPcm3.getText().toString().equals("") ? 0 : Double.parseDouble(edtDVPcm3.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setDvm10((edtDVM.getText().toString().equals("") ? 0 : Double.parseDouble(edtDVM.getText().toString().replaceAll("\\.", "").replace(",", "."))));
            exControle.setNumeroOrdem(edtNoOrdem.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setSeloCertificacao(edtSeloCertificacao.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setObs(edtObs.getText().toString().replaceAll("\n", "").replace("\n", ""));
            exControle.setPintura(spPintura.getSelectedItemPosition());
            exControle.setpNivel(spNMant.getSelectedItemPosition());
            exControle.setResultado(spResultado.getSelectedItemPosition());
            exControle.setTipo(spTipo.getSelectedItemPosition());
            exControle.setCheioVazio(spCheioVazio.getSelectedItemPosition());
           if(spMarca.getSelectedItemPosition() >= 0){
                exControle.setFabricante(exFabricantes.get(spMarca.getSelectedItemPosition()));
            }
       }catch (Exception e){
            Log.i("ERRO", "ERRO AO PREENCHER O CONTROLE DE EXTINTOR");
       }
    }


}
