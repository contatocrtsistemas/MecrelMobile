package appmecrel.com.br.appmecrel.model.listas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import appmecrel.com.br.appmecrel.R;

/**
 * Created by usuario on 20/08/2015.
 */
public class ExtintorMangueiraList extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ExtintorMangueiraItemList> itens;
    private View selectedView;
    private int lastPosition = -1;

    public ExtintorMangueiraList(Context context, List<ExtintorMangueiraItemList> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public ExtintorMangueiraItemList getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Suporte item;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.extintormangueira_item_list, null);

            item = new Suporte();
            item.tvListNoOrc = (TextView) convertView.findViewById(R.id.tvListNoOrc);
            item.tvListNomeCliente = (TextView) convertView.findViewById(R.id.tvListNomeCliente);
            item.tvListIdentificacao = (TextView) convertView.findViewById(R.id.tvListIdentificacao);
            item.tvListMesAnoFabricacao = (TextView) convertView.findViewById(R.id.tvListMesAnoFabricacao);
            convertView.setTag(item);
        }else{

            item = (Suporte) convertView.getTag();
        }

        ExtintorMangueiraItemList itemList = itens.get(position);
        item.tvListNoOrc.setText(itemList.getExtintorMangueira().getOrcCodigo());
        item.tvListNomeCliente.setText(itemList.getExtintorMangueira().getCliente().getCliNome());
        item.tvListIdentificacao.setText(itemList.getExtintorMangueira().getIdentificacao());
        item.tvListMesAnoFabricacao.setText(itemList.getExtintorMangueira().getMesAnoFabricacao());

        return convertView;
    }

    private class Suporte{
        TextView tvListNoOrc;
        TextView tvListNomeCliente;
        TextView tvListIdentificacao;
        TextView tvListMesAnoFabricacao;
    }
    public void setSelectedView(View selectedView, int position) {
        this.lastPosition = position;
        if (this.selectedView != null) {
            this.selectedView.setBackgroundColor(Color.WHITE);
        }
        this.selectedView = selectedView;
        this.selectedView.setBackgroundColor(Color.argb(200, 135, 206, 255));
    }

    public View getSelectedView() {
        return selectedView;
    }

}
