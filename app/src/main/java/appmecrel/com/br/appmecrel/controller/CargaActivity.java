package appmecrel.com.br.appmecrel.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTPFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.bean.Cliente;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.bean.EXFabricante;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.validacao.ClienteModel;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;
import appmecrel.com.br.appmecrel.model.validacao.EXFabricanteModel;
import appmecrel.com.br.appmecrel.model.validacao.ExtintorMangueiraModel;
import appmecrel.com.br.appmecrel.util.API;
import appmecrel.com.br.appmecrel.util.ConexaoFTP;
import appmecrel.com.br.appmecrel.util.Funcoes;

public class CargaActivity extends Activity {

    ProgressDialog dialog;
    Button btnImport, btnExport;
    String variavel = "";
    String stringGlobal = "";
    String arquivoExportacao = "";
    int contadorSequencial;
    EditText edtImportacao;
    int processamento = 0;
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();
    private boolean terminouExportacao = false;
    private boolean terminouImportacao = false;
    private boolean achouArquivo = false;
    private boolean baixoOArquivo= false;
    private boolean sobrepor = false;
    private ConexaoFTP cone = new ConexaoFTP();
    private ArrayList<EXFabricante> exFabricantes = new ArrayList<EXFabricante>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    List<EXControle> exControles;
    List<ExtintorMangueira> extintorMangueiras;
    private EXControleModel exControleModel = new EXControleModel(this);
    private ExtintorMangueiraModel extintorMangueiraModel = new ExtintorMangueiraModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        edtImportacao = (EditText) findViewById(R.id.edtImportacao);
        btnImport = (Button) findViewById(R.id.btnImport);
        btnExport = (Button) findViewById(R.id.btnExport);
        ativaTimer();
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   ativaTimer();
                importar();
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                // ativaTimer();
                exportar();
            }
        });


    }

    private void exportar() {
        edtImportacao.setText(null);
        arquivoExportacao = "";
        ConnectivityManager connMgr =  (ConnectivityManager) getSystemService ( CargaActivity.this.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if  ( networkInfo !=  null  && networkInfo . isConnected ())  {
            Log.i("TESTE", "Com internet");
            dialog = ProgressDialog.show(CargaActivity.this, "Crt Sitemas", "Sincronizando Dados . . .", false, true);
            dialog.setCancelable(false);

            new Thread()
            {
                public void run()
                {
                    try
                    {
                        processamento = 10;
                        geraExportacao();
                        if(terminouExportacao)
                            processamento = 70;
                        else
                            processamento = 80;
                        dialog.dismiss();
                    }
                    catch (Exception e)
                    {
                        dialog.dismiss();
                        processamento = 80;
                        Log.i("TESTE","Erro : " + e.getMessage());
                    }

                }
            }.start();
        }  else  {
            Funcoes.exibirMensagem(CargaActivity.this, "Erro", "Sem conexão com a internet");
            Log.i("TESTE", "Sem internet");
        }
    }

    private void importar() {
        edtImportacao.setText(null);
        ConnectivityManager connMgr =  (ConnectivityManager) getSystemService ( CargaActivity.this.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if  ( networkInfo !=  null  && networkInfo . isConnected ())  {
            Log.i("TESTE", "Com internet");
            dialog = ProgressDialog.show(CargaActivity.this, "Crt Sitemas", "Sincronizando Dados . . .", false, true);
            dialog.setCancelable(false);
            //dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar operação", null);

            new Thread() {
                public void run() {
                    try {
                        processamento = 1;
                        geraImportacao();
                        if(terminouImportacao)
                            processamento = 7;
                        else
                            processamento = 8;
                        dialog.dismiss();
                    } catch (Exception e) {
                        dialog.dismiss();
                        processamento = 8;
                        Log.i("TESTE", "Erro : " + e.getMessage());
                    }

                }
            }.start();
        }else{
            Funcoes.exibirMensagem(CargaActivity.this, "Erro", "Sem conexão com a internet");
            Log.i("TESTE", "Sem internet");
        }
    }

    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        //Log.i("Debug", "Timer ativado");
                        if(processamento == 1){
                            edtImportacao.setText("Importação Iniciada..." + "\n" );
                        }
                        else if(processamento == 7){
                            edtImportacao.setText("Importação concluida com Sucesso..." + "\n" );                            //timerAtual.cancel();
                        }
                        else if (processamento == 8){
                            if(!baixoOArquivo)
                                edtImportacao.setText("Arquivo não encontrado. Erro ao Realizar a importação..." + "\n" );
                            else
                                edtImportacao.setText("Erro ao Realizar a importação..." + "\n" );
                            //   timerAtual.cancel();
                        }else if(processamento == 10)
                            edtImportacao.setText("Exportação Iniciada......" + "\n" );
                        else if(processamento == 70){
                            if(achouArquivo)
                                edtImportacao.setText("Exportação concluida com Sucesso..." + "\n" );
                            else
                                edtImportacao.setText("Erro ao Realizar a Exportação..." + "\n" );
                        }
                        else if (processamento == 80){
                            edtImportacao.setText("Erro ao Realizar a Exportação..." + "\n" );
                        }
                    }
                });
            }};
        timerAtual.schedule(task, 300, 300);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carga, menu);
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

    private void geraImportacao(){
        try{
            download("EXCONTROLEPC.txt");
            if(baixoOArquivo){
                carregarTxt();
                terminouImportacao = true;
            }
        }
        catch (Exception e){
            terminouImportacao = false;
        }
    }

    private boolean achouArquivo() throws  Exception{
        boolean achou = false;
        String lstrArq = String.format("EXCONTROLEPALM.txt");
        FTPFile[] arquivos = cone.listarDiretorios(API.configuracoes.getDiretorio());

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

    private void download(String nomeArquivo){
        try
        {
            ConexaoFTP cone = new ConexaoFTP();

            //cone.conectar("ftp.crtsistemas.com.br", "crtsiste", "diogosites", 21);
            cone.conectar(API.configuracoes.getServidor(), API.configuracoes.getUsuario(), API.configuracoes.getSenha(),
                    Integer.parseInt(API.configuracoes.getPorta()));
            Log.i("TESTE","Erro : Conectou");
            String lstrArq = "/" + nomeArquivo;//"/EXCONTROLEPC.txt";
            File lArquivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), lstrArq);
            //cone.downloadArquivo("/Fmenezes", "PCPALM.txt", lArquivo.toString());
            baixoOArquivo = cone.downloadArquivo(API.configuracoes.getDiretorio(), nomeArquivo/*"EXCONTROLEPC.txt"*/, lArquivo.toString());
            Log.i("TESTE",""+baixoOArquivo);
        }
        catch (Exception e)
        {
            baixoOArquivo = false;
            Log.i("TESTE","Erro : " + e.getMessage());
        }
    }

    private void upload() throws Exception{

        String lstrArq = String.format("/EXCONTROLEPALM.txt");
        //cone.conectar("ftp.crtsistemas.com.br", "crtsiste", "diogosites", 21);
        cone.conectar(API.configuracoes.getServidor(), API.configuracoes.getUsuario(), API.configuracoes.getSenha(),
                Integer.parseInt(API.configuracoes.getPorta()));
        //cone.uploadArquivo(lstrArq, "/Fmenezes"+lstrArq);
        Log.i("TESTE", "VEIO AKI");
        cone.uploadArquivo(lstrArq, API.configuracoes.getDiretorio()+lstrArq);
        achouArquivo = achouArquivo();
        Log.i("TESTE", "arquivo" + achouArquivo);
        cone.desconectar();

    }

    private void criarTxt(){
        String lstrNomeArq;
        File arq;
        byte[] dados;
        ArrayList<String> array = new ArrayList<String>();
        try
        {
            lstrNomeArq = String.format("/EXCONTROLEPALM.txt");
            arq = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), lstrNomeArq);
            FileOutputStream fos;
            contadorSequencial = 1;
            exProcessaControle();
            processaControleMangueira();
            dados = arquivoExportacao.toString().getBytes();
            if(sobrepor)
                fos = new FileOutputStream(arq, true);
            else
                fos = new FileOutputStream(arq);
            fos.write(dados);
            fos.flush();
            fos.close();
        }
        catch (Exception e)
        {
            Toast.makeText(CargaActivity.this, "Erro ao criar arquivo!", Toast.LENGTH_LONG);
        }
    }

    private void exProcessaControle() {

        try {
            if (exControles != null)
                exControles.clear();
            exControles = exControleModel.tratarBusca(2, "1");
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            arquivoExportacao = "[EXCONTROLE]";
            arquivoExportacao += "\n";
            for (EXControle exControle : exControles) {
                arquivoExportacao += contadorSequencial + "=" + exControle.getNumeroServico() + "|" + exControle.getCodigo() + "|" +
                        exControle.getNumero() + "|" + exControle.getMesAno().trim() + "|" +
                        exControle.getFabricante().getId() + "|" + exControle.getTipo() + "|" +
                        String.valueOf((int) (exControle.getCapacidade()*100)) + "|" + exControle.getpTrabalho() + "|" +
                        exControle.getNormaFabricacao() + "|" + exControle.getpNivel() + "|" +
                        exControle.getPintura()+ "|" +  String.valueOf((int) (exControle.getPesoPV() * 100)) + "|" +
                        String.valueOf((int) (exControle.getEnsBaixaPressao()))+ "|" +  String.valueOf((int) (exControle.getPesoPC() *100)) + "|" +
                        String.valueOf((int) (exControle.getvL() *100)) + "|" +  String.valueOf((int) (exControle.getcMaxima()*100)) + "|" +
                        String.valueOf((int) (exControle.getEnsAltaPressao() *100)) + "|" +  String.valueOf((int) (exControle.getDvm() *100))+ "|" +
                        String.valueOf((int) (exControle.getDvp()*100)) + "|" +  String.valueOf((int) (exControle.getDvm10() *100))+
                        "|" + exControle.getResultado() + "|" + exControle.getNumeroOrdem() + "|" +
                        exControle.getSeloCertificacao() + "|||" + exControle.getObs() + "|" + exControle.getId() + "|" +
                        exControle.getCheioVazio() + "|";
                arquivoExportacao += "\n";
                contadorSequencial++;
            }
        }catch (Exception e){
            terminouExportacao = false;
        }
    }

    private void processaControleMangueira() {
        try {
            if (extintorMangueiras != null)
                extintorMangueiras.clear();
            extintorMangueiras = extintorMangueiraModel.tratarBusca(2, "1");
            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            arquivoExportacao += "[EXTINTORMANGUEIRA]";
            arquivoExportacao += "\n";
            for (ExtintorMangueira extintorMangueira : extintorMangueiras) {
                arquivoExportacao += contadorSequencial + "=" + extintorMangueira;
                arquivoExportacao += "\n";
                contadorSequencial++;
            }
        }catch (Exception e){
            terminouExportacao = false;
        }
    }

    private void carregarTxt() {
        String lstrNomeArq;
        File arq;
        String lstrlinha;
        boolean cliente = false;
        boolean fabricantes = false;

        try {
            lstrNomeArq = "EXCONTROLEPC.txt";
            StringBuffer string = new StringBuffer();

            arq = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), lstrNomeArq);
            BufferedReader br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null) {

                if (lstrlinha.equals("[CLI_SOB]")) {
                    cliente = true;
                    fabricantes = false;
                    continue;
                } else if (lstrlinha.equals("[FAB_SOB]")) {
                    cliente = false;
                    fabricantes = true;
                }

                if (cliente) {
                    Cliente clienteLocal = new Cliente();
                    preencheClienteImp(clienteLocal, lstrlinha);
                    clienteLocal.setStatus(1);
                    clientes.add(clienteLocal);
                }else if(fabricantes){
                    EXFabricante exFabricante = new EXFabricante();
                    preencheFabricante(exFabricante, lstrlinha);
                    exFabricante.setStatus(1);
                    exFabricantes.add(exFabricante);
                }
            }
            ClienteModel clienteModel = new ClienteModel(this);
            for (Cliente item : clientes) {
                clienteModel.tratarExclusaoCliente(item.getCliId(), false);
                clienteModel.tratarInsercaoCliente(item);
            }
            EXFabricanteModel exFabricanteModel = new EXFabricanteModel(this);
            for (EXFabricante item : exFabricantes) {
                exFabricanteModel.tratarExclusao(item.getId());
                exFabricanteModel.tratarInsercao(item);
            }
            stringGlobal = string.toString();
        } catch (Exception e) {

        }
    }

    private void preencheClienteImp(Cliente clienteLocal, String lstrlinha) {
        String[] strLocal = lstrlinha.split("\\|");

        int i = 0;
        try{
            for(String parte : strLocal){

                switch(i) {
                    case 0:
                        String[] verifica = parte.split("=");
                        if(verifica.length > 1)
                            clienteLocal.setCliCpfCnpj(verifica[1]);
                        else
                            clienteLocal.setCliCpfCnpj("");
                        break;
                    case 1:
                        if(!parte.equals(""))
                            clienteLocal.setCliId(Integer.parseInt(parte));
                        break;
                    case 2:
                        clienteLocal.setCliNome(parte);
                        break;
                }
                i++;
            }
        }catch (Exception e){
            Log.i("ERRO", e.getMessage());
        }
    }

    private void preencheFabricante(EXFabricante exFabricante, String lstrlinha) {
        String[] strLocal = lstrlinha.split("\\|");

        int i = 0;
        try {
            for (String parte : strLocal) {

                switch (i) {
                    case 0:
                        String[] verifica = parte.split("=");
                        exFabricante.setId(Integer.parseInt(verifica[1]));
                        break;
                    case 1:
                        exFabricante.setNome(parte);
                        break;
                }
                i++;
            }
        }catch (Exception e){

        }
    }

    private void geraExportacao(){
        try {
            verificaSeExisteArquivoServidor();
            criarTxt();
            upload();
            if((exControles != null) && (exControles.size() > 0))
                for(EXControle item : exControles){
                    item.setStatus(1);
                    exControleModel.tratarAlteracao(item);
                }

            if((extintorMangueiras != null) && (extintorMangueiras.size() > 0))
                for(ExtintorMangueira item : extintorMangueiras){
                    item.setStatus(1);
                    extintorMangueiraModel.tratarAlteracao(item);
                }
            terminouExportacao = true;
        }catch(Exception e){
            terminouExportacao = false;
        }
    }

    private void verificaSeExisteArquivoServidor() throws Exception{
        cone.conectar(API.configuracoes.getServidor(), API.configuracoes.getUsuario(), API.configuracoes.getSenha(),
                Integer.parseInt(API.configuracoes.getPorta()));
        if (achouArquivo()) {
            download("EXCONTROLEPALM.txt");
            sobrepor = true;
        }else{
            sobrepor = false;
        }
        cone.desconectar();
    }

}
