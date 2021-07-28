package appmecrel.com.br.appmecrel.model;

/**
 * Created by Alison Rodrigues on 02/03/2015.
 */
public abstract class Tabelas {

    public static final String CONFIGURACOES = "CREATE TABLE configuracoes(cfgnome VARCHAR(30) PRIMARY KEY," +
            "cfgvalor VARCHAR(1000));";

    public static final String SEQUENCIAL = " CREATE TABLE sequencial ( " +
            " seqtabela   VARCHAR(30)," +
            " seqproximo  INTEGER" +
            " );";

    public static final String FABRICANTES = "CREATE TABLE fabricantes(" +
            "fabid INTEGER PRIMARY KEY," +
            "fabnome VARCHAR(100));";

    public static final String EXCONTROLE = "CREATE TABLE EXCONTROLE(" +
                                            " xcoid INTEGER PRIMARY KEY," +
                                            " xconumeroservico INTEGER, " +
                                            " xcocodigo INTEGER," +
                                            " xconumero VARCHAR(100)," +
                                            " xcomesano VARCHAR(50)," +
                                            " fabid INTEGER," +
                                            " xcotipo INTEGER," +
                                            " xcocapacidade NUMERIC(18,2)," +
                                            " xcoptrabalho VARCHAR(100)," +
                                            " xconormafabricacao VARCHAR(300)," +
                                            " xcopnivel INTEGER," +
                                            " xcopintura INTEGER," +
                                            " xcopesopv NUMERIC(18,2)," +
                                            " xcoensbaixapressao NUMERIC(18,2)," +
                                            " xcopesopc NUMERIC(18,2)," +
                                            " xcovl NUMERIC(18,2)," +
                                            " xcocmaxima NUMERIC(18,2)," +
                                            " xcoensaltapressao NUMERIC(18,2)," +
                                            " xcodvm NUMERIC(18,2)," +
                                            " xcodvp NUMERIC(18,2)," +
                                            " xcodvm10 NUMERIC(18,2)," +
                                            " xcoresultado INTEGER," +
                                            " xconumeroordem VARCHAR(100)," +
                                            " xcoselocertificacao VARCHAR(100)," +
                                            " xcodatarecarga DATE," +
                                            " xcodataproximarecarga DATE," +
                                            " xcoobs VARCHAR(1000)," +
                                            " xcostatus INTEGER," +
                                            " xcocheiovazio INTEGER)"+
                                            ";";

    public static final String EXTINTORMANGUEIRA =  "CREATE TABLE EXTINTORMANGUEIRAS (" +
                                                    "    EMAID                      INTEGER," +
                                                    "    CLIID                      INTEGER," +
                                                    "    ORCCODIGO                  INTEGER," +
                                                    "    EMADATA                    DATE," +
                                                    "    EMAORDEM                   INTEGER," +
                                                    "    EMAIDENTIFICACAO           VARCHAR(10)," +
                                                    "    EMAMARCADODUTO             VARCHAR(10)," +
                                                    "    EMAMARCADAUNIAO            VARCHAR(10)," +
                                                    "    EMADIAMETRO                INTEGER," +
                                                    "    EMACOMPNORMAL              VARCHAR(10)," +
                                                    "    EMATIPO                    INTEGER," +
                                                    "    EMAMESANOFABRICACAO        VARCHAR(10)," +
                                                    "    EMAPRESSAOENSAIO           VARCHAR(10)," +
                                                    "    EMAPROXIMAINSPECAO         VARCHAR(10)," +
                                                    "    EMAPROXIMAMANUTENCAO       VARCHAR(10)," +
                                                    "    EMACOMPRIMENTOREAL         VARCHAR(10)," +
                                                    "    EMACARCACAREVESTIMENTO     INTEGER," +
                                                    "    EMAUNIOES                  INTEGER," +
                                                    "    EMACOMPLUVA                VARCHAR(10)," +
                                                    "    EMAVEDACAOBORRACHA         INTEGER," +
                                                    "    EMAMARCACAO                INTEGER," +
                                                    "    EMAENSAIOHIDROSTATICO      INTEGER," +
                                                    "    EMAREEMPATACAO             INTEGER," +
                                                    "    EMACOMPRIMENTOFINAL        VARCHAR(10)," +
                                                    "    EMASUBSTITUICAOUNIOES      INTEGER," +
                                                    "    EMASUBSTITUICAOVEDACOES    INTEGER," +
                                                    "    EMASUBSTITUICAOANEIS       INTEGER," +
                                                    "    EMANOVOENSAIOHIDROSTATICO  INTEGER," +
                                                    "    EMALIMPEZA                 INTEGER," +
                                                    "    EMASECAGEM                 INTEGER," +
                                                    "    EMARESULTADOFINAL          INTEGER," +
                                                    "    EMASTATUS                  INTEGER," +
                                                    "    EMANOEXTINTOR              VARCHAR(50)," +
                                                    "    EMADESCPECA                VARCHAR(200)," +
                                                    "    EMANORDEM                  VARCHAR(200)" +
                                                    ");";

    public static final String CLIENTE = "CREATE TABLE clientes ( " +
            "  cliid integer not null ,  " +
            "  clinome varchar(200)   ,  " +
            "  clicpfcnpj varchar(20) ,  " +
            "  CONSTRAINT PK_cliid_01 PRIMARY KEY(cliid) " +
            "  );";

    /**
     * Aqui Começa os inserts Basicos.
     *
     **/

    public static final String ADM = "INSERT INTO VENDEDORES VALUES (0, 'MASTER', 'crtncx724');";

    public static final String GRUPO = "INSERT INTO GRUPOS VALUES(0, 'PADRAO');";

    public static final String FABRICANTE = "INSERT INTO FABRICANTES VALUES (0, 'PADRAO');";

    public static final String SEQUENCIAL_EXCONTROLE = "INSERT INTO sequencial  VALUES('excontrole', 100000) ";

    /**
    *
    *  Ao criar uma tabela ou um insert padrão adicionar nas Strings abaixo.
    *
    * */

     public static final String TABELAS = CONFIGURACOES + "\n" + FABRICANTES + "\n" + SEQUENCIAL + "\n" +
                                          EXCONTROLE + "\n" + EXTINTORMANGUEIRA + "\n" + CLIENTE;

    public static final String INSERTS_PADRAO = SEQUENCIAL_EXCONTROLE;
}
