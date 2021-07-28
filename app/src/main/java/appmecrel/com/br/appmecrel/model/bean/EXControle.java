package appmecrel.com.br.appmecrel.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lucas on 20/08/2015.
 */
public class EXControle implements Serializable{

    private int id;
    private int numeroServico;
    private int codigo;
    private String numero;
    private String mesAno;
    private EXFabricante fabricante;
    private int tipo;
    private double capacidade;
    private String pTrabalho;
    private String normaFabricacao;
    private int pNivel;
    private int pintura;
    private double pesoPV;
    private double pesoPC;
    private double vL;
    private double cMaxima;
    private double ensAltaPressao;
    private double dvm;
    private double dvp;
    private double dvm10;
    private double ensBaixaPressao;
    private int resultado;
    private String numeroOrdem;
    private String seloCertificacao;
    private Date dataRecarga;
    private Date dataProximaRecarga;
    private String obs;
    private int status;
    private int cheioVazio;

    public EXControle(){
        fabricante = new EXFabricante();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroServico() {
        return numeroServico;
    }

    public void setNumeroServico(int numeroServico) {
        this.numeroServico = numeroServico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public EXFabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(EXFabricante fabricante) {
        this.fabricante = fabricante;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public String getpTrabalho() {
        return pTrabalho;
    }

    public void setpTrabalho(String pTrabalho) {
        this.pTrabalho = pTrabalho;
    }

    public String getNormaFabricacao() {
        return normaFabricacao;
    }

    public void setNormaFabricacao(String normaFabricacao) {
        this.normaFabricacao = normaFabricacao;
    }

    public int getpNivel() {
        return pNivel;
    }

    public void setpNivel(int pNivel) {
        this.pNivel = pNivel;
    }

    public int getPintura() {
        return pintura;
    }

    public void setPintura(int pintura) {
        this.pintura = pintura;
    }

    public double getPesoPV() {
        return pesoPV;
    }

    public void setPesoPV(double pesoPV) {
        this.pesoPV = pesoPV;
    }

    public double getPesoPC() {
        return pesoPC;
    }

    public void setPesoPC(double pesoPC) {
        this.pesoPC = pesoPC;
    }

    public double getvL() {
        return vL;
    }

    public void setvL(double vL) {
        this.vL = vL;
    }

    public double getcMaxima() {
        return cMaxima;
    }

    public void setcMaxima(double cMaxima) {
        this.cMaxima = cMaxima;
    }

    public double getEnsAltaPressao() {
        return ensAltaPressao;
    }

    public void setEnsAltaPressao(double ensAltaPressao) {
        this.ensAltaPressao = ensAltaPressao;
    }

    public double getDvm() {
        return dvm;
    }

    public void setDvm(double dvm) {
        this.dvm = dvm;
    }

    public double getDvp() {
        return dvp;
    }

    public void setDvp(double dvp) {
        this.dvp = dvp;
    }

    public double getDvm10() {
        return dvm10;
    }

    public void setDvm10(double dvm10) {
        this.dvm10 = dvm10;
    }

    public double getEnsBaixaPressao() {
        return ensBaixaPressao;
    }

    public void setEnsBaixaPressao(double ensBaixaPressao) {
        this.ensBaixaPressao = ensBaixaPressao;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getNumeroOrdem() {
        return numeroOrdem;
    }

    public void setNumeroOrdem(String numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public String getSeloCertificacao() {
        return seloCertificacao;
    }

    public void setSeloCertificacao(String seloCertificacao) {
        this.seloCertificacao = seloCertificacao;
    }

    public Date getDataRecarga() {
        return dataRecarga;
    }

    public void setDataRecarga(Date dataRecarga) {
        this.dataRecarga = dataRecarga;
    }

    public Date getDataProximaRecarga() {
        return dataProximaRecarga;
    }

    public void setDataProximaRecarga(Date dataProximaRecarga) {
        this.dataProximaRecarga = dataProximaRecarga;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getCheioVazio() {
        return cheioVazio;
    }

    public void setCheioVazio(int cheioVazio) {
        this.cheioVazio = cheioVazio;
    }
}
