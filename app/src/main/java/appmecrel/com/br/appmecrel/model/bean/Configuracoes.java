package appmecrel.com.br.appmecrel.model.bean;

import java.util.ArrayList;

/**
 * Created by usuario on 22/06/2015.
 */
public class Configuracoes extends ArrayList<Configuracao> {

    private String getInformacao(String nome){
        String valor = "";
        for(Configuracao item : this){
            if(item.getNome().equals(nome)){
                valor =  item.getValor();
                break;
            }
        }
        return valor;
    }

    private void setInformacao(String nome,String valor){

        for(Configuracao item : this){
            if(item.getNome().equals(nome)){
                item.setValor(valor);
                break;
            }
        }
    }

    public String getServidor(){
        return getInformacao("servidor");
    }

    public String getPorta(){
        return getInformacao("porta");
    }

    public String getDiretorio(){
        return getInformacao("diretorio");
    }

    public String getUsuario(){
        return getInformacao("usuario");
    }

    public String getSenha(){
        return getInformacao("senha");
    }

    public String getCnpj(){
        return getInformacao("cnpj");
    }

    public String getEnviar() { return getInformacao("enviar"); }

    public String getChaveSistema(){ return  getInformacao("chavesistema"); }

    public void setServidor(String valor){
        setInformacao("servidor", valor);
    }

    public void setPorta(String valor){
        setInformacao("porta", valor);
    }

    public void setDiretorio(String valor){
        setInformacao("diretorio", valor);
    }

    public void setUsuario(String valor){
         setInformacao("usuario", valor);
    }

    public void setSenha(String valor){
         setInformacao("senha", valor);
    }

    public void setCnpj(String valor){
        setInformacao("cnpj", valor);
    }

    public void setChaveSistema(String valor){
        setInformacao("chavesistema", valor);
    }

    public void setEnviar(Boolean valor){
        if(valor)
            setInformacao("enviar", "1");
        else
            setInformacao("enviar", "0");
    }
}
