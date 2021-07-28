package appmecrel.com.br.appmecrel.model.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PROGRAMAÇÃO on 15/03/2017.
 */

public class ExtintorMangueira implements Serializable{

    private int id;
    private Cliente cliente;
    private int orcCodigo;
    private Date data;
    private int ordem;
    private String identificacao;
    private String marcaDuto;
    private String marcaUniao;
    private int diametro;
    private int compNormal;
    private int tipo;
    private String mesAnoFabricacao;
    private String pressaoEnsaio;
    private String proximaInspecao;
    private String proximaManutencao;
    private String compReal;
    private int carcacaRevestimento;
    private int unioes;
    private String compLuva;
    private int vedacaoBorracha;
    private int marcacao;
    private int ensaioHidrostatico;
    private int reempatacao;
    private String compFinal;
    private int subUnioes;
    private int subVedacoes;
    private int subAneis;
    private int novoEnsaioHidrostatico;
    private int limpeza;
    private int secagem;
    private int resultadoFinal;
    private int status;
    private String noExtintor;
    private String descPeca;
    private String NOrdem;

    public ExtintorMangueira () {cliente = new Cliente(); }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getOrcCodigo() {
        return orcCodigo;
    }

    public void setOrcCodigo(int orcCodigo) {
        this.orcCodigo = orcCodigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getMarcaDuto() {
        return marcaDuto;
    }

    public void setMarcaDuto(String marcaDuto) {
        this.marcaDuto = marcaDuto;
    }

    public String getMarcaUniao() {
        return marcaUniao;
    }

    public void setMarcaUniao(String marcaUniao) {
        this.marcaUniao = marcaUniao;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getCompNormal() {
        return compNormal;
    }

    public void setCompNormal(int compNormal) {
        this.compNormal = compNormal;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMesAnoFabricacao() {
        return mesAnoFabricacao;
    }

    public void setMesAnoFabricacao(String mesAnoFabricacao) {
        this.mesAnoFabricacao = mesAnoFabricacao;
    }

    public String getPressaoEnsaio() {
        return pressaoEnsaio;
    }

    public void setPressaoEnsaio(String pressaoEnsaio) {
        this.pressaoEnsaio = pressaoEnsaio;
    }

    public String getProximaInspecao() {
        return proximaInspecao;
    }

    public void setProximaInspecao(String proximaInspecao) {
        this.proximaInspecao = proximaInspecao;
    }

    public String getProximaManutencao() {
        return proximaManutencao;
    }

    public void setProximaManutencao(String proximaManutencao) {
        this.proximaManutencao = proximaManutencao;
    }

    public String getCompReal() {
        return compReal;
    }

    public void setCompReal(String compReal) {
        this.compReal = compReal;
    }

    public int getCarcacaRevestimento() {
        return carcacaRevestimento;
    }

    public void setCarcacaRevestimento(int carcacaRevestimento) {
        this.carcacaRevestimento = carcacaRevestimento;
    }

    public int getUnioes() {
        return unioes;
    }

    public void setUnioes(int unioes) {
        this.unioes = unioes;
    }

    public String getCompLuva() {
        return compLuva;
    }

    public void setCompLuva(String compLuva) {
        this.compLuva = compLuva;
    }

    public int getVedacaoBorracha() {
        return vedacaoBorracha;
    }

    public void setVedacaoBorracha(int vedacaoBorracha) {
        this.vedacaoBorracha = vedacaoBorracha;
    }

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public int getEnsaioHidrostatico() {
        return ensaioHidrostatico;
    }

    public void setEnsaioHidrostatico(int ensaioHidrostatico) {
        this.ensaioHidrostatico = ensaioHidrostatico;
    }

    public int getReempatacao() {
        return reempatacao;
    }

    public void setReempatacao(int reempatacao) {
        this.reempatacao = reempatacao;
    }

    public String getCompFinal() {
        return compFinal;
    }

    public void setCompFinal(String compFinal) {
        this.compFinal = compFinal;
    }

    public int getSubUnioes() {
        return subUnioes;
    }

    public void setSubUnioes(int subUnioes) {
        this.subUnioes = subUnioes;
    }

    public int getSubVedacoes() {
        return subVedacoes;
    }

    public void setSubVedacoes(int subVedacoes) {
        this.subVedacoes = subVedacoes;
    }

    public int getSubAneis() {
        return subAneis;
    }

    public void setSubAneis(int subAneis) {
        this.subAneis = subAneis;
    }

    public int getNovoEnsaioHidrostatico() {
        return novoEnsaioHidrostatico;
    }

    public void setNovoEnsaioHidrostatico(int novoEnsaioHidrostatico) {
        this.novoEnsaioHidrostatico = novoEnsaioHidrostatico;
    }

    public int getLimpeza() {
        return limpeza;
    }

    public void setLimpeza(int limpeza) {
        this.limpeza = limpeza;
    }

    public int getSecagem() {
        return secagem;
    }

    public void setSecagem(int secagem) {
        this.secagem = secagem;
    }

    public int getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(int resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNoExtintor(){
        return this.noExtintor;
    }

    public void setNoExtintor(String noExtintor){
        this.noExtintor = noExtintor;
    }

    public String getDescPeca(){
        return  this.descPeca;
    }

    public void setDescPeca(String descPeca){
        this.descPeca = descPeca;
    }

    public String getNOrdem() {
        return NOrdem;
    }

    public void setNOrdem(String NOrdem) {
        this.NOrdem = NOrdem;
    }

    public String getCompNormaltoString (){
        if (compNormal == 2) {
            return "30";
        } else if (compNormal == 1) {
            return "20";
        } else {
            return "15";
        }
    }
    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return id + "|" + cliente.getCliId() + "|" + orcCodigo + "|" + dateFormat.format(data) + "|" + ordem + "|" +
          identificacao + "|" + marcaDuto + "|" + marcaUniao + "|" + diametro + "|" + getCompNormaltoString() + "|" +
          tipo + "|" + mesAnoFabricacao + "|" + pressaoEnsaio + "|" + proximaInspecao + "|" + proximaManutencao + "|" +
          compReal + "|" + carcacaRevestimento + "|" + unioes + "|" + compLuva + "|" + vedacaoBorracha + "|" +
          marcacao + "|" + ensaioHidrostatico + "|" + reempatacao + "|" + compFinal + "|" + subUnioes + "|" +
          subVedacoes + "|" + subAneis + "|" + novoEnsaioHidrostatico + "|" + limpeza + "|" +
          secagem + "|" + resultadoFinal + "|" + noExtintor + "|" + descPeca + "|" + NOrdem + "|";
    }
}
