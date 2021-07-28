package appmecrel.com.br.appmecrel.model.listas;

import android.os.Parcel;
import android.os.Parcelable;

import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;

/**
 * Created by usuario on 20/08/2015.
 */
public class ExtintorMangueiraItemList implements Parcelable {


    private ExtintorMangueira extintorMangueira;

    public ExtintorMangueiraItemList() {
        this.extintorMangueira = new ExtintorMangueira();
    }
    public ExtintorMangueiraItemList(ExtintorMangueira extintorMangueira) {
        this.extintorMangueira = extintorMangueira;
    }

    public ExtintorMangueira getExtintorMangueira(){
        return this.extintorMangueira;
    }
    public void setExtintorMangueira(ExtintorMangueira extintorMangueira){
        this.extintorMangueira = extintorMangueira;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExtintorMangueiraItemList> CREATOR = new
            Creator<ExtintorMangueiraItemList>() {

                @Override
                public ExtintorMangueiraItemList createFromParcel(Parcel source) {

                    return new ExtintorMangueiraItemList(source);
                }

                @Override
                public ExtintorMangueiraItemList[] newArray(int size) {

                    throw new UnsupportedOperationException();
                    //return new DeviceEntity[size];
                }
            }
            ;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(extintorMangueira.getCliente().getCliNome());
        dest.writeInt(extintorMangueira.getOrcCodigo());
        dest.writeString(extintorMangueira.getIdentificacao());
        dest.writeString(extintorMangueira.getMesAnoFabricacao());
        dest.writeInt(extintorMangueira.getStatus());
    }

    public ExtintorMangueiraItemList(Parcel source) {
        //Os valores são retornados na ordem em que foram escritos em writeToParcel
        extintorMangueira.getCliente().setCliNome(source.readString());
        extintorMangueira.setOrcCodigo(source.readInt());
        extintorMangueira.setIdentificacao(source.readString());
        extintorMangueira.setMesAnoFabricacao(source.readString());
        extintorMangueira.setStatus(source.readInt());
    }
}
