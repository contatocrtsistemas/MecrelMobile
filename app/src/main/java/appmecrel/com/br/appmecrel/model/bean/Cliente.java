package appmecrel.com.br.appmecrel.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */

public class Cliente implements Serializable {
    private int cliId;
    private String cliNome;
    private String cliNomeRazao;
    private int cliTipo;
    private String cliCpfCnpj;
    private String cliRg;
    private String cliFone1;
    private String cliFone2;
    private String cliFoneContato;
    private String cliUf;
    private String cliCidade;
    private String cliBairro;
    private String cliEndereco;
    private String cliCep;
    private String cliEmail;
    private String cliSite;
    private String cliObs;
    private double cliLimiteCredito;
    private Date dataCadastro;
    private Date dataUltimaCompra;
    private int status;
    private int vendedorId;

    public Date getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(Date dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public int getCliId() {
        return cliId;
    }

    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliNomeRazao() {
        return cliNomeRazao;
    }

    public void setCliNomeRazao(String cliNomeRazao) {
        this.cliNomeRazao = cliNomeRazao;
    }

    public int getCliTipo() {
        return cliTipo;
    }

    public void setCliTipo(int cliTipo) {
        this.cliTipo = cliTipo;
    }

    public String getCliCpfCnpj() {
        return cliCpfCnpj;
    }

    public void setCliCpfCnpj(String cliCpfCnpj) {
        this.cliCpfCnpj = cliCpfCnpj;
    }

    public String getCliRg() {
        return cliRg;
    }

    public void setCliRg(String cliRg) {
        this.cliRg = cliRg;
    }

    public String getCliFone1() {
        return cliFone1;
    }

    public void setCliFone1(String cliFone1) {
        this.cliFone1 = cliFone1;
    }

    public String getCliFone2() {
        return cliFone2;
    }

    public void setCliFone2(String cliFone2) {
        this.cliFone2 = cliFone2;
    }

    public String getCliFoneContato() {
        return cliFoneContato;
    }

    public void setCliFoneContato(String cliFoneContato) {
        this.cliFoneContato = cliFoneContato;
    }

    public String getCliUf() {
        return cliUf;
    }

    public void setCliUf(String cliUf) {
        this.cliUf = cliUf;
    }

    public String getCliCidade() {
        return cliCidade;
    }

    public void setCliCidade(String cliCidade) {
        this.cliCidade = cliCidade;
    }

    public String getCliBairro() {
        return cliBairro;
    }

    public void setCliBairro(String cliBairro) {
        this.cliBairro = cliBairro;
    }

    public String getCliEndereco() {
        return cliEndereco;
    }

    public void setCliEndereco(String cliEndereco) {
        this.cliEndereco = cliEndereco;
    }

    public String getCliCep() {
        return cliCep;
    }

    public void setCliCep(String cliCep) {
        this.cliCep = cliCep;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliSite() {
        return cliSite;
    }

    public void setCliSite(String cliSite) {
        this.cliSite = cliSite;
    }

    public String getCliObs() {
        return cliObs;
    }

    public void setCliObs(String cliObs) {
        this.cliObs = cliObs;
    }

    public double getCliLimiteCredito() {
        return cliLimiteCredito;
    }

    public void setCliLimiteCredito(double cliLimiteCredito) {
        this.cliLimiteCredito = cliLimiteCredito;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public  void setVendedorId(int vendedorId){
        this.vendedorId = vendedorId;
    }

}
