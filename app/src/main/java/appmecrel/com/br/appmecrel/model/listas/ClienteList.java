package appmecrel.com.br.appmecrel.model.listas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.exception.DefaultException;
import appmecrel.com.br.appmecrel.model.bean.Cliente;

/**
 * Created by usuario on 04/03/2015.
 */
public class ClienteList extends BaseAdapter implements SectionIndexer {


    private LayoutInflater mInflater;
    private List<ClienteItemList> itens;
    private Context context;
    private Cliente clienteCorrente;
    private View viewInfo;
    private AlertDialog dialogo;
    private Activity activity;
    private int position;
    private View selectedView;
    private int lastPosition = -1;
    private HashMap<String, Integer> indexer;
    private String[] sections;

    public ClienteList(Context context, List<ClienteItemList> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = (Activity) context;
        indexer = new HashMap<String, Integer>();

        int size = itens.size();
        for (int index = 0; index < size; index++) {
            String item = itens.get(index).getCliente().getCliNome();
            String chave = item.substring(0, 1);
            chave = chave.toUpperCase();
            if (!indexer.containsKey(chave)) {
                indexer.put(chave, index);
            }
        }

        ArrayList<String> sectionList = new ArrayList<String>(indexer.keySet());
        Collections.sort(sectionList);

        sections = new String[sectionList.size()];
        sections = sectionList.toArray(sections);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public ClienteItemList getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        Suporte item;

       // if(convertView == null) {
            convertView = mInflater.inflate(R.layout.cliente_item_list, null);

        item = new Suporte();
        item.tvListClienteNome = (TextView) convertView.findViewById(R.id.tvListClienteNome);
        this.position = position;
        ClienteItemList itemPedido = itens.get(position);
        item.tvListClienteNome.setText(itemPedido.getCliente().getCliId() + " - " +
                                        itemPedido.getCliente().getCliNome());
        return convertView;

    }
    public void setSelectedView(View selectedView, int position) {
        this.lastPosition = position;
        if (this.selectedView != null) {
            this.selectedView.setBackgroundColor(Color.WHITE);
        }
        this.selectedView = selectedView;
        this.selectedView.setBackgroundColor(Color.argb(200, 135, 206, 255));
    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return indexer.get(sections[sectionIndex]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    private class Suporte{
        TextView tvListClienteNome;
    }
}
