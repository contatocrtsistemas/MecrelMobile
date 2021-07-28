package appmecrel.com.br.appmecrel.util;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import appmecrel.com.br.appmecrel.model.bean.Configuracao;
import appmecrel.com.br.appmecrel.model.bean.Configuracoes;
import appmecrel.com.br.appmecrel.model.validacao.ConfiguracaoModel;

/**
 * Created by Lucas on 22/06/2015.
 */
public class API {

    private static ConfiguracaoModel configuracaoModel;

    public static Configuracoes configuracoes = new Configuracoes();

    public static String msgChaveSistema = "";

    public static String senhaEntrada = "";

    public static void geraConfiguracoes(Context contexto){

         configuracaoModel = new ConfiguracaoModel(contexto);

        configuracoes = configuracaoModel.tratarBusca();

        if(configuracoes.getServidor() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("servidor");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

        if(configuracoes.getPorta() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("porta");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

        if(configuracoes.getDiretorio() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("diretorio");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

        if(configuracoes.getUsuario() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("usuario");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

        if(configuracoes.getSenha() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("senha");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

        if(configuracoes.getCnpj() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("cnpj");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }
        if(configuracoes.getChaveSistema() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("chavesistema");
            configuracao.setValor(".");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }if(configuracoes.getEnviar() == ""){
            Configuracao configuracao = new Configuracao();
            configuracao.setNome("enviar");
            configuracao.setValor("0");
            configuracaoModel.tratarInsercao(configuracao);
            configuracoes.add(configuracao);
        }

    }

    public static void gravarConfiguracoes(){
        for(Configuracao item : configuracoes){
            configuracaoModel.tratarAlteracao(item);
        }
    }

    public static boolean verificaChave(Context contexto){
        boolean retorno = false;
        String strLocal = configuracoes.getChaveSistema();
        String strDataVencimento = "";
        Date dataAtual = new Date();
        Date dataVencimento;
        String cnpj = configuracoes.getCnpj();
        int qtdDias;
        long mileAtual;
        long mileVenc;
        try{
            if(strLocal.length() == 18) {

                strDataVencimento = strLocal.substring(16, 17) + strLocal.substring(4, 5) + '/' + strLocal.substring(2, 3)
                        + strLocal.substring(3, 4) + '/' + '2' + strLocal.substring(8, 9) + strLocal.substring(17, 18)
                        + strLocal.substring(15, 16);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dataVencimento = new java.sql.Date(format.parse(strDataVencimento).getTime());


                Log.i("TESTE", "CNPJ.: " + cnpj.substring(5, 6) + "  CHAVE.: " + strLocal.substring(0, 1));
                Log.i("TESTE", "CNPJ.: " + cnpj.substring(4, 5) + "  CHAVE.: " + strLocal.substring(5, 6));
                Log.i("TESTE", "CNPJ.: " + cnpj.substring(3, 4) + "  CHAVE.: " + strLocal.substring(7, 8));
                Log.i("TESTE", "CNPJ.: " + cnpj.substring(6, 7) + "  CHAVE.: " + strLocal.substring(12, 13));
                Log.i("TESTE", "CNPJ.: " + cnpj.substring(1, 2) + "  CHAVE.: " + strLocal.substring(14, 15));
                Log.i("TESTE", "DATA.: " + dataAtual.before(dataVencimento));
                if ((dataAtual.before(dataVencimento))
                        &&(cnpj.substring(5, 6).equals(strLocal.substring(0, 1)))
                        &&(cnpj.substring(4, 5).equals(strLocal.substring(5, 6)))
                        &&(cnpj.substring(3, 4).equals(strLocal.substring(7, 8)))
                        &&(cnpj.substring(6, 7).equals(strLocal.substring(12, 13)))
                        &&(cnpj.substring(1, 2).equals(strLocal.substring(14, 15)))){

                    retorno = true;

                    mileAtual = dataAtual.getTime();
                    mileVenc = dataVencimento.getTime();
                    long diasLocal = mileVenc - mileAtual;
                    int dias = Math.round((int) (diasLocal / (24 * 60 * 60 * 1000)));

                    if (dias <= 2) {
                        msgChaveSistema =  "Falta(m) " + (dias + 1) + "dia(s) para o Sistema Expirar ! Entre em contato com a CRT Sistemas " +
                                " através do Telefone(85)3022.0391 e solicite sua Chave. Deseja Gerar Agora ?";
                    }else{
                        msgChaveSistema = "";
                    }
                }
            }
        }catch(Exception e){

        }

        return retorno;
    }
}
