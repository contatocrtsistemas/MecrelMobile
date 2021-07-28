package appmecrel.com.br.appmecrel.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;
import appmecrel.com.br.appmecrel.model.validacao.ExtintorMangueiraModel;
import appmecrel.com.br.appmecrel.util.Funcoes;


public class ConfigBackupActivity extends Fragment {
    private Button btnBackup, btnRestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.configuracoes_backup, container, false);
        inicializaObjetos(v);
        return v;
    }

    private void inicializaObjetos(View v){
        btnBackup = (Button) v.findViewById(R.id.btnBackup);
        btnRestore= (Button) v.findViewById(R.id.btnRestore);
        eventosClick();
    }

    private void eventosClick() {
        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backupSistema();

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EXControleModel exControleModel = new EXControleModel(getActivity());
                        ExtintorMangueiraModel extintorMangueiraModel = new ExtintorMangueiraModel(getActivity());
                        exControleModel.deleteAll();
                        extintorMangueiraModel.deleteAll();
                    }
                });
                alert.setNegativeButton("Não", null);
                alert.setTitle("Limpeza da base de dados");
                alert.setMessage("Deseja excluir todos os " +
                                 "Controle de Extintor e Controles de Mangueira?");
                alert.show();
            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoreSistema();
            }
        });

    }

    private void backupSistema() {
        try {
            // Caminho de Origem do Seu Banco de Dados
            InputStream in = new FileInputStream(
                    new File(Environment.getDataDirectory()
                            + "/data/appmecrel.com.br.appmecrel/databases/ControleCrtMecrel"));
            // Caminho de Destino do Backup do Seu Banco de Dados
            OutputStream out = new FileOutputStream(new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            + "/ControleCrtMecrel.db"));
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Funcoes.exibirMensagem(getActivity(), "Backup do Sistema", "Backup realizado com sucesso");
        } catch (FileNotFoundException e) {
            Funcoes.exibirMensagem(getActivity(), "Backup do Sistema", "Erro ao realizar o backup: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Funcoes.exibirMensagem(getActivity(), "Backup do Sistema", "Erro ao realizar o backup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void restoreSistema() {
        try {
            // Caminho do Backup Banco de Dados
            InputStream in = new FileInputStream(
                    new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            + "/ControleCrtMecrel.db"));

            // Caminho de Destino do Backup do Seu Banco de Dados
            OutputStream out = new FileOutputStream(new File(
                    Environment.getDataDirectory()
                            + "/data/appmecrel.com.br.appmecrel/databases/ControleCrtMecrel"));



            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Funcoes.exibirMensagem(getActivity(), "Restore do Sistema", "Restore realizado com sucesso");
        } catch (FileNotFoundException e) {
            Funcoes.exibirMensagem(getActivity(), "Restore do Sistema", "Erro ao realizar o restore: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Funcoes.exibirMensagem(getActivity(), "Restore do Sistema", "Erro ao realizar o restore: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
