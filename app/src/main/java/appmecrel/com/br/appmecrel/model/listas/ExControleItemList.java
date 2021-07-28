package appmecrel.com.br.appmecrel.model.listas;

import android.os.Parcel;
import android.os.Parcelable;

import appmecrel.com.br.appmecrel.model.bean.EXControle;

/**
 * Created by usuario on 20/08/2015.
 */
public class ExControleItemList implements Parcelable {


    private EXControle exControle;

    public ExControleItemList() {
        this.exControle = new EXControle();
    }
    public ExControleItemList(EXControle exControle) {
        this.exControle = exControle;
    }

    public EXControle getExControle(){
        return this.exControle;
    }
    public void setExControle(EXControle exControle){
        this.exControle = exControle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ExControleItemList> CREATOR = new
            Parcelable.Creator<ExControleItemList>() {

                @Override
                public ExControleItemList createFromParcel(Parcel source) {

                    return new ExControleItemList(source);
                }

                @Override
                public ExControleItemList[] newArray(int size) {

                    throw new UnsupportedOperationException();
                    //return new DeviceEntity[size];
                }
            }
            ;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(exControle.getNumeroServico());
        dest.writeString(exControle.getNumero());
        dest.writeString(exControle.getMesAno());
        dest.writeString(exControle.getFabricante().getNome());
        dest.writeDouble(exControle.getCapacidade());
        dest.writeInt(exControle.getCodigo());
        dest.writeInt(exControle.getStatus());
    }

    public ExControleItemList(Parcel source) {

        //Os valores são retornados na ordem em que foram escritos em writeToParcel
        exControle.setNumeroServico(source.readInt());
        exControle.setNumero(source.readString());
        exControle.setMesAno(source.readString());
        exControle.getFabricante().setNome(source.readString());
        exControle.setCapacidade(source.readDouble());
        exControle.setCodigo(source.readInt());
        exControle.setStatus(source.readInt());
    }
}
