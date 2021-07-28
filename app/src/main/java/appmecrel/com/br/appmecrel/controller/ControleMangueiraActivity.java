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
import appmecrel.com.br.appmecrel.model.bean.Cliente;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.listas.ExControleItemList;
import appmecrel.com.br.appmecrel.model.listas.ExControleList;
import appmecrel.com.br.appmecrel.model.validacao.ClienteModel;
import appmecrel.com.br.appmecrel.model.validacao.EXFabricanteModel;
import appmecrel.com.br.appmecrel.model.validacao.ExtintorMangueiraModel;
import appmecrel.com.br.appmecrel.util.Funcoes;

import static appmecrel.com.br.appmecrel.R.id.edtDescPeca;
import static appmecrel.com.br.appmecrel.R.id.edtNoExtintor;

public class ControleMangueiraActivity extends Activity {

    private EditText edtNoOS, edtIdenficacao, edtMarcaDutoFlexivel, edtMarcaUniao, edtMesAnoFabricacao, edtPressaEnsaio,
                     edtProximaInspecao, edtProximaManutencao, edtCompReal, edtCompFinal,
                     edtClienteId, edtClienteNome, edtNoExtintor, edtDescPeca, edtNOrdemMangueira;
    private Spinner spDiametro, spCompNominal, spTipo, spCarcacaRevestimento, spUnioes, spVedacaoBorracha, spMarcacao,
            spEnsaioHidrostatico, spReempatacao, spSubstUnioes, spSubstVedacao, spSubstAneis, spNovoEnsaioHidro, spLimpeza,
            spSecagem, spResultadoFinal;
    private Button btnFinalizar, btnCancelar, btnSair;
    private ExControleList exControleList;
    private Calendar cal = Calendar.getInstance();
    private int mYear = cal.get(Calendar.YEAR);
    private int mMonth = cal.get(Calendar.MONTH);
    private int mDay = cal.get(Calendar.DAY_OF_MONTH);
    private int opcao = 0;
    static final int DATE_DIALOG_ID = 0;
    private int posicao = -1;
    //private Ex exControleModel;
    private ExtintorMangueiraModel extintorMangueiraModel;
    private ClienteModel clienteModel;
    private Cliente clienteLocal;
    private int tela;
    ProgressDialog dialog;
    List<EXControle> exControles;
    String arquivoExportacao = "";
    int contadorSequencial;
    boolean achouArquivo;
    private boolean mostraMengagem;
    private Toast toast;
    private long lastBackPressTime = 0;
    ExtintorMangueira extintorMangueiraLocal;
    private final int CLIENTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_mangueira);
        inicializaObjetos();
        eventosClicks();
        if(getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getSerializableExtra("extintormangueira") != null){
                this.extintorMangueiraLocal = (ExtintorMangueira) intent.getSerializableExtra("extintormangueira");
                preencheControleLocal(extintorMangueiraLocal);
                tela = 1;
            }
        }

    }

    private void preencheControleLocal(ExtintorMangueira extintorMangueiraLocal) {
        edtClienteId.setText(String.valueOf(extintorMangueiraLocal.getCliente().getCliId()));
        edtClienteNome.setText(extintorMangueiraLocal.getCliente().getCliNome());
        edtNoOS.setText(String.valueOf(extintorMangueiraLocal.getOrcCodigo()));
        edtIdenficacao.setText(extintorMangueiraLocal.getIdentificacao());
        edtMarcaDutoFlexivel.setText(extintorMangueiraLocal.getMarcaDuto());
        edtMarcaUniao.setText(extintorMangueiraLocal.getMarcaUniao());
        edtMesAnoFabricacao.setText(extintorMangueiraLocal.getMesAnoFabricacao());
        edtPressaEnsaio.setText(extintorMangueiraLocal.getPressaoEnsaio());
        edtProximaInspecao.setText(extintorMangueiraLocal.getProximaInspecao());
        edtProximaManutencao.setText(extintorMangueiraLocal.getProximaManutencao());
        edtCompReal.setText(extintorMangueiraLocal.getCompReal());
        edtCompFinal.setText(extintorMangueiraLocal.getCompFinal());
        edtNoExtintor.setText(extintorMangueiraLocal.getNoExtintor());
        edtDescPeca.setText(extintorMangueiraLocal.getDescPeca());

        spDiametro.setSelection(extintorMangueiraLocal.getDiametro());
        spCompNominal.setSelection(extintorMangueiraLocal.getCompNormal());
        spTipo.setSelection(extintorMangueiraLocal.getTipo());
        spUnioes.setSelection(extintorMangueiraLocal.getUnioes());
        spVedacaoBorracha.setSelection(extintorMangueiraLocal.getVedacaoBorracha());
        spMarcacao.setSelection(extintorMangueiraLocal.getMarcacao());
        spCarcacaRevestimento.setSelection(extintorMangueiraLocal.getCarcacaRevestimento());
        spEnsaioHidrostatico.setSelection(extintorMangueiraLocal.getEnsaioHidrostatico());
        spReempatacao.setSelection(extintorMangueiraLocal.getReempatacao());
        spSubstUnioes.setSelection(extintorMangueiraLocal.getSubUnioes());
        spSubstVedacao.setSelection(extintorMangueiraLocal.getDiametro());
        spSubstAneis.setSelection(extintorMangueiraLocal.getDiametro());
        spNovoEnsaioHidro.setSelection(extintorMangueiraLocal.getDiametro());
        spLimpeza.setSelection(extintorMangueiraLocal.getDiametro());
        spSecagem.setSelection(extintorMangueiraLocal.getDiametro());
        spResultadoFinal.setSelection(extintorMangueiraLocal.getDiametro());
        edtNOrdemMangueira.setText(extintorMangueiraLocal.getNOrdem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_controle_extintor, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleMangueiraActivity.this);
        alertDialog.setTitle("Sair");
        alertDialog.setMessage("Deseja realmente sair da tela?");
        alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ControleMangueiraActivity.super.onBackPressed();
            }
        });
        alertDialog.setNegativeButton("Cancelar", null);
        alertDialog.show();
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
        edtNoOS = (EditText) findViewById(R.id.edtNoOS);
        edtIdenficacao = (EditText) findViewById(R.id.edtIdentificacao);
        edtMarcaDutoFlexivel = (EditText) findViewById(R.id.edtMarcaDutoFlexivel);
        edtMarcaUniao = (EditText) findViewById(R.id.edtMarcaUniao);
        edtMesAnoFabricacao = (EditText) findViewById(R.id.edtMesAnoFabricacao);
        edtPressaEnsaio = (EditText) findViewById(R.id.edtPreEnsaio);
        edtProximaInspecao = (EditText) findViewById(R.id.edtProximaInspecao);
        edtProximaManutencao       = (EditText) findViewById(R.id.edtProximaManutencao);
        edtCompReal = (EditText) findViewById(R.id.edtCompReal);
        edtCompFinal = (EditText) findViewById(R.id.edtCompFinal);
        edtClienteId = (EditText) findViewById(R.id.edtClienteId);
        edtClienteNome = (EditText) findViewById(R.id.edtClienteNome);
        edtNoExtintor = (EditText) findViewById(R.id.edtNoExtintor);
        edtDescPeca = (EditText) findViewById(R.id.edtDescPeca);

        spDiametro = (Spinner) findViewById(R.id.spDiametro);
        spCompNominal = (Spinner) findViewById(R.id.spCompNominal);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        spUnioes= (Spinner) findViewById(R.id.spUnioes);
        spVedacaoBorracha= (Spinner) findViewById(R.id.spVedacao);
        spMarcacao= (Spinner) findViewById(R.id.spMarcacao);
        spCarcacaRevestimento = (Spinner) findViewById(R.id.spCarcacaRevestimento);
        spEnsaioHidrostatico= (Spinner) findViewById(R.id.spEnsaioHidrostatico);
        spReempatacao= (Spinner) findViewById(R.id.spReempatacao);
        spSubstUnioes= (Spinner) findViewById(R.id.spSubstituicaoUnioes);
        spSubstVedacao= (Spinner) findViewById(R.id.spSubstituicaoVedacao);
        spSubstAneis= (Spinner) findViewById(R.id.spSubstituicaoAneis);
        spNovoEnsaioHidro= (Spinner) findViewById(R.id.spNovoEnsaioHidrostatico);
        spLimpeza= (Spinner) findViewById(R.id.spLimpeza);
        edtNOrdemMangueira = (EditText) findViewById(R.id.edtNOrdemMangueira);
        spSecagem= (Spinner) findViewById(R.id.spSecagem);
        spResultadoFinal= (Spinner) findViewById(R.id.spResultado);

        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSair = (Button) findViewById(R.id.btnSair);

        extintorMangueiraModel = new ExtintorMangueiraModel(ControleMangueiraActivity.this);
        extintorMangueiraLocal = new ExtintorMangueira();
        clienteModel = new ClienteModel(ControleMangueiraActivity.this);
        clienteLocal = new Cliente();

        /*
            Adapter Resultados.
         */
        ArrayAdapter<CharSequence> adapterResultados = ArrayAdapter.createFromResource(this, R.array.resultado, R.layout.spinner_item);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCarcacaRevestimento.setAdapter(adapterResultados);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spUnioes.setAdapter(adapterResultados);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spVedacaoBorracha.setAdapter(adapterResultados);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMarcacao.setAdapter(adapterResultados);
        adapterResultados.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEnsaioHidrostatico.setAdapter(adapterResultados);

        /*
        *   Adapter Sim.Não
        * */

        ArrayAdapter<CharSequence> adapterSimNao = ArrayAdapter.createFromResource(this, R.array.simnao, R.layout.spinner_item);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spReempatacao.setAdapter(adapterSimNao);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spSubstUnioes.setAdapter(adapterSimNao);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spSubstVedacao.setAdapter(adapterSimNao);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spSubstAneis.setAdapter(adapterSimNao);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spLimpeza.setAdapter(adapterSimNao);
        adapterSimNao.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spSecagem.setAdapter(adapterSimNao);

        /*
            Adapter Resultado Final
         */

        ArrayAdapter<CharSequence> adapterResultadoFinal = ArrayAdapter.createFromResource(this, R.array.resultado_final, R.layout.spinner_item);
        adapterResultadoFinal.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spResultadoFinal.setAdapter(adapterResultadoFinal);

        /*
        *   Adapter Tipo
        *
        * */

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.tipo_mangueira, R.layout.spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spTipo.setAdapter(adapterTipo);

        /*
        *   Adapter Diametro
        *
        * */

        ArrayAdapter<CharSequence> adapterDiametro = ArrayAdapter.createFromResource(this, R.array.diametro, R.layout.spinner_item);
        adapterDiametro.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spDiametro.setAdapter(adapterDiametro);

        /*
        *   Adapter Diametro
        *
        * */

        ArrayAdapter<CharSequence> adapterCompNominal = ArrayAdapter.createFromResource(this, R.array.comp_nominal, R.layout.spinner_item);
        adapterCompNominal.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCompNominal.setAdapter(adapterCompNominal);

        /*
            Adapter Novo Ensaio Hidro
         */

        ArrayAdapter<CharSequence> adapterNovoEnsaio = ArrayAdapter.createFromResource(this, R.array.novo_ensaio, R.layout.spinner_item);
        adapterNovoEnsaio.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spNovoEnsaioHidro.setAdapter(adapterNovoEnsaio);


    }

    private void eventosClicks(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tela == 1){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleMangueiraActivity.this);
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
                    limpaTela();
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(edtClienteId.getText().toString().equals("")){
                        Funcoes.exibirMensagem(ControleMangueiraActivity.this, "Controle de Mangueira",
                                                "Necessário informar o Cliente");
                    }else{
                        finalizaProcesso();
                    }
                }catch (Exception e){
                    Log.i("ControleMangueira", "Erro: "+ e.getMessage());
                    Log.i("INSERIR", "ERRO AO INSERIR");
                }
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ControleMangueiraActivity.this);
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

        edtClienteId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Cliente cliente = clienteModel.tratarBuscaCliente(edtClienteId.getText().toString());
                    if (cliente != null) {
                        edtClienteNome.setText(cliente.getCliNome());
                        clienteLocal = cliente;
                    } else {
                        edtClienteNome.setText("");
                        edtClienteId.setText("");
                        Toast.makeText(ControleMangueiraActivity.this, "Cliente nao encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        edtClienteNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(ControleMangueiraActivity.this, Cliente2Activity.class);
            intent.putExtra("consultaCliente", "1");
            startActivityForResult(intent, CLIENTE);
            }
        });

    }

    private void finalizaProcesso() throws Exception{
        List<ExtintorMangueira>  extintorMangueiraList= new ArrayList<ExtintorMangueira>();
        if(tela == 0){
            extintorMangueiraLocal.setStatus(0);
            extintorMangueiraLocal.setData(new Date());
            preencheControle(extintorMangueiraLocal);
            extintorMangueiraModel.tratarInsercao(extintorMangueiraLocal);
            limpaTela();
            Funcoes.exibirMensagem(ControleMangueiraActivity.this, "Controle de Extintores", "Operação realizado com sucesso!");
        }else if(tela == 1){
            extintorMangueiraLocal.setStatus(0);
            preencheControle(extintorMangueiraLocal);
            extintorMangueiraModel.tratarAlteracao(extintorMangueiraLocal);
            limpaTela();
            AlertDialog.Builder alert = new AlertDialog.Builder(ControleMangueiraActivity.this);
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
    private void limpaTela() {
        edtNoOS.setText(null);
        edtIdenficacao.setText(null);
        edtMarcaDutoFlexivel.setText(null);
        edtMarcaUniao.setText(null);
        edtMesAnoFabricacao.setText(null);
        edtPressaEnsaio.setText(null);
        edtProximaInspecao.setText(null);
        edtProximaManutencao.setText(null);
        edtCompReal.setText(null);
        edtCompFinal.setText(null);
        edtNOrdemMangueira.setText(null);
    }

    private void preencheControle(ExtintorMangueira extintorMangueira){
       try{
           extintorMangueira.getCliente().setCliId(Integer.parseInt(getTexto(edtClienteId)));
           extintorMangueira.setOrcCodigo(Integer.parseInt(getTexto(edtNoOS)));
           extintorMangueira.setIdentificacao(getTexto(edtIdenficacao));
           extintorMangueira.setMarcaDuto(getTexto(edtMarcaDutoFlexivel));
           extintorMangueira.setMarcaUniao(getTexto(edtMarcaUniao));
           extintorMangueira.setMesAnoFabricacao(getTexto(edtMesAnoFabricacao));
           extintorMangueira.setPressaoEnsaio(getTexto(edtPressaEnsaio));
           extintorMangueira.setProximaInspecao(getTexto(edtProximaInspecao));
           extintorMangueira.setProximaManutencao(getTexto(edtProximaManutencao));
           extintorMangueira.setCompReal(getTexto(edtCompReal));
           extintorMangueira.setCompFinal(getTexto(edtCompFinal));

           extintorMangueira.setDiametro(spDiametro.getSelectedItemPosition());
           extintorMangueira.setCompNormal(spCompNominal.getSelectedItemPosition());
           extintorMangueira.setTipo(spTipo.getSelectedItemPosition());
           extintorMangueira.setUnioes(spUnioes.getSelectedItemPosition());
           extintorMangueira.setVedacaoBorracha(spVedacaoBorracha.getSelectedItemPosition());
           extintorMangueira.setMarcacao(spMarcacao.getSelectedItemPosition());
           extintorMangueira.setCarcacaRevestimento(spCarcacaRevestimento.getSelectedItemPosition());
           extintorMangueira.setEnsaioHidrostatico(spEnsaioHidrostatico.getSelectedItemPosition());
           extintorMangueira.setReempatacao(spReempatacao.getSelectedItemPosition());
           extintorMangueira.setSubUnioes(spSubstUnioes.getSelectedItemPosition());
           extintorMangueira.setSubVedacoes(spSubstVedacao.getSelectedItemPosition());
           extintorMangueira.setSubAneis(spSubstAneis.getSelectedItemPosition());
           extintorMangueira.setNovoEnsaioHidrostatico(spNovoEnsaioHidro.getSelectedItemPosition());
           extintorMangueira.setNOrdem(edtNOrdemMangueira.getText().toString());
           extintorMangueira.setLimpeza(spLimpeza.getSelectedItemPosition());
           extintorMangueira.setSecagem(spSecagem.getSelectedItemPosition());
           extintorMangueira.setResultadoFinal(spResultadoFinal.getSelectedItemPosition());
           extintorMangueira.setNoExtintor(edtNoExtintor.getText().toString());
           extintorMangueira.setDescPeca(edtDescPeca.getText().toString());
       }catch (Exception e){
            Log.i("ERRO", "ERRO AO PREENCHER O CONTROLE DE EXTINTOR");
       }
    }

    private String getTexto(EditText editText){
        return  editText.getText().toString().replaceAll("\n", "").replace("\n", "");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CLIENTE){
            if(data != null){
                edtClienteNome.setText(data.getStringExtra("nome"));
                edtClienteId.setText(data.getStringExtra("id"));
                clienteLocal.setCliNome(data.getStringExtra("nome"));
                clienteLocal.setCliId(Integer.parseInt(data.getStringExtra("id")));
            }
        }
    }


}
