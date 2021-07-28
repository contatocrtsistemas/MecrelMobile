package appmecrel.com.br.appmecrel.model.bean;

import java.io.Serializable;

/**
 * Created by usuario on 20/08/2015.
 */
public class EXFabricante implements Serializable{
    private int id;
    private String nome;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
