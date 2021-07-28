package appmecrel.com.br.appmecrel.model.listas;

import android.os.Parcel;
import android.os.Parcelable;

import appmecrel.com.br.appmecrel.model.bean.Cliente;


/**
 * Created by Lucas on 04/03/2015.
 */
public class ClienteItemList implements Parcelable {

    private Cliente cliente;

    public ClienteItemList(){
        this.cliente = null;
    }

    public ClienteItemList(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cliente.getCliNome());
        dest.writeInt(cliente.getCliId());
    }

    public static final Parcelable.Creator<ClienteItemList> CREATOR = new
            Parcelable.Creator<ClienteItemList>() {

                @Override
                public ClienteItemList createFromParcel(Parcel source) {

                    return new ClienteItemList(source);
                }

                @Override
                public ClienteItemList[] newArray(int size) {

                    throw new UnsupportedOperationException();
                    //return new DeviceEntity[size];
                }
            }
            ;

    //Construtor usado pelo android para criar a nossa classe, neste caso DeviceEntity
    public ClienteItemList(Parcel source) {
        //Os valores são retornados na ordem em que foram escritos em writeToParcel
        cliente.setCliNome(source.readString());
        cliente.setCliId(source.readInt());
    }
}
