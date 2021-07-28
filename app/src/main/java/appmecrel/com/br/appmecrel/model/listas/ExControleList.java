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
public class ExControleList extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ExControleItemList> itens;
    private View selectedView;
    private int lastPosition = -1;

    public ExControleList(Context context, List<ExControleItemList> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public ExControleItemList getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.excontrole_item_list, null);

            item = new Suporte();
            item.tvListNoCert = (TextView) convertView.findViewById(R.id.tvListNoCert);
            item.tvListNoFabric = (TextView) convertView.findViewById(R.id.tvListNoFabric);
            item.tvListMesAno = (TextView) convertView.findViewById(R.id.tvListMesAno);
            item.tvListMarca = (TextView) convertView.findViewById(R.id.tvListMarca);
            item.tvListCapacidade = (TextView) convertView.findViewById(R.id.tvListCapacidade);
            item.tvListNoOS = (TextView) convertView.findViewById(R.id.tvListNoOS);

            convertView.setTag(item);
        }else{

            item = (Suporte) convertView.getTag();
        }

        ExControleItemList itemList = itens.get(position);
        item.tvListNoCert.setText(String.valueOf(itemList.getExControle().getNumeroServico()));
        item.tvListNoFabric.setText(itemList.getExControle().getNumero());
        item.tvListMesAno.setText(String.valueOf(itemList.getExControle().getMesAno()));
        item.tvListMarca.setText(itemList.getExControle().getFabricante().getNome());
        item.tvListCapacidade.setText(String.format("%.2f", itemList.getExControle().getCapacidade()));
        item.tvListNoOS.setText(String.valueOf(itemList.getExControle().getCodigo()));

        return convertView;
    }

    private class Suporte{

        TextView tvListNoCert;
        TextView tvListNoFabric;
        TextView tvListMesAno;
        TextView tvListMarca;
        TextView tvListCapacidade;
        TextView tvListNoOS;

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
