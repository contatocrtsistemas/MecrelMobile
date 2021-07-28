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
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.controller.ControleExtintorActivity;
import appmecrel.com.br.appmecrel.model.bean.EXControle;
import appmecrel.com.br.appmecrel.model.validacao.EXControleModel;

/**
 * Created by usuario on 25/08/2015.
 */
public class ExControleListConsulta extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ExControleItemList> itens;
    private View selectedView;
    private int lastPosition = -1;
    private Context contexto;
    private View viewInfo;
    private int posicao;
    private int posicaoSelecionada;
    private AlertDialog dialogo;
    private EXControleModel exControleModel;
    private EXControle exControleCorrente;
    private Activity activity;
    private ImageView imagem;

    public ExControleListConsulta(Context context, List<ExControleItemList> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
        contexto = context;
        activity = (Activity) context;
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
            convertView = mInflater.inflate(R.layout.excontrole_item_listconsulta, null);

            item = new Suporte();
            item.tvListControleNoCert = (TextView) convertView.findViewById(R.id.tvListControleNoCert);
            item.tvListControleNoFabric = (TextView) convertView.findViewById(R.id.tvListControleNoFabric);
            item.tvListControleMesAno = (TextView) convertView.findViewById(R.id.tvListControleMesAno);
            item.tvListControleMarca = (TextView) convertView.findViewById(R.id.tvListControleMarca);
            item.tvListControleCapacidade = (TextView) convertView.findViewById(R.id.tvListControleCapacidade);
            item.tvListControleNoOS = (TextView) convertView.findViewById(R.id.tvListControleNoOS);
            item.tvListControleTipo = (TextView) convertView.findViewById(R.id.tvListControleTipo);
            item.imageListControleInfo = (ImageView) convertView.findViewById(R.id.imageListControleInfo);
            //item.tvListControleStatus = (TextView) convertView.findViewById(R.id.tvListControleStatus);
            item.imageStatusEnvioExtintor = (ImageView) convertView.findViewById(R.id.imageStatusEnvioExtintor);
            this.posicao = position;
            item.imageListControleInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   LayoutInflater inflater = LayoutInflater.from(contexto);
                    AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
                    viewInfo = inflater.inflate(R.layout.layout_informacoes_controle, null);
                    preencheViewControle(viewInfo, itens.get(posicaoSelecionada), posicaoSelecionada);
                    alert.setView(viewInfo);
                    dialogo = alert.create();
                    dialogo.show();
                    // alert.show();
                }
            });

            item.imageStatusEnvioExtintor.setOnClickListener(

                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                int position = (Integer) v.getTag();
                                imagem = (ImageView) v.findViewById(R.id.imageStatusEnvioExtintor);
                                //tvStatus = (TextView) v.findViewById(R.id.tvListStatus);
                                if(itens.get(position).getExControle().getStatus() == 1) {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
                                    posicaoSelecionada = position;
                                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EXControleModel exControleModel1 = new EXControleModel(contexto);
                                            exControleModel1.setStatus(itens.get(posicaoSelecionada).getExControle().getId(), 0);
                                            itens.get(posicaoSelecionada).getExControle().setStatus(0);
                                            imagem.setImageResource(R.drawable.padlockopen);
                                            //tvStatus.setText("Pendente");
                                            //          notifyDataSetChanged();
                                        }
                                    });
                                    alert.setNegativeButton("Não", null);
                                    alert.setTitle("Alteração de Status");
                                    alert.setMessage("Deseja alterar o status?");
                                    alert.show();
                                }
                            }catch (Exception e){

                            }
                        }
                    }
            );
            convertView.setTag(item);
        }else{

            item = (Suporte) convertView.getTag();
        }

        ExControleItemList itemList = itens.get(position);
        item.tvListControleNoCert.setText(String.valueOf(itemList.getExControle().getNumeroServico()));
        item.tvListControleNoFabric.setText(itemList.getExControle().getNumero());
        item.tvListControleMesAno.setText(String.valueOf(itemList.getExControle().getMesAno()));
        item.tvListControleMarca.setText(itemList.getExControle().getFabricante().getNome());
        item.tvListControleCapacidade.setText(String.format("%.2f", itemList.getExControle().getCapacidade()));
        item.tvListControleNoOS.setText(String.valueOf(itemList.getExControle().getCodigo()));
        String[] tipos = contexto.getResources().getStringArray(R.array.tipos);
        item.tvListControleTipo.setText(tipos[itemList.getExControle().getTipo()]);
        item.imageStatusEnvioExtintor.setTag(position);
        /*if(itemList.getExControle().getStatus() == 0)
            item.tvListControleStatus.setText("Pendente");
        else
            item.tvListControleStatus.setText("Enviado");

        */
        if (itemList.getExControle().getStatus() == 0){
            //item.tvListStatus.setText("Pendente");
            item.imageStatusEnvioExtintor.setImageResource(R.drawable.padlockopen);
        }
        else{
            //item.tvListStatus.setText("Enviado");
            item.imageStatusEnvioExtintor.setImageResource(R.drawable.padlockclosed);
        }
        return convertView;
    }

    private void preencheViewControle(View viewInfo, ExControleItemList exControleItemList, int position) {

        TextView tvInfoControleNoCert = (TextView) viewInfo.findViewById(R.id.tvInfoControleNoCert);
        TextView tvInfoControleNoFabric = (TextView) viewInfo.findViewById(R.id.tvInfoControleNoFabric);
        TextView tvInfoControleMesAno = (TextView) viewInfo.findViewById(R.id.tvInfoControleMesAno);
        TextView tvInfoControleMarca = (TextView) viewInfo.findViewById(R.id.tvInfoControleMarca);
        TextView tvInfoControleTipo = (TextView) viewInfo.findViewById(R.id.tvInfoControleTipo);
        TextView tvInfoControleCapacidade = (TextView) viewInfo.findViewById(R.id.tvInfoControleCapacidade);
        TextView tvInfoControleNoOS = (TextView) viewInfo.findViewById(R.id.tvInfoControleNoOS);
        TextView tvInfoControlePTrab = (TextView) viewInfo.findViewById(R.id.tvInfoControlePTrab);
        TextView tvInfoControleNormaFab = (TextView) viewInfo.findViewById(R.id.tvInfoControleNormaFab);
        TextView tvInfoControleNMan = (TextView) viewInfo.findViewById(R.id.tvInfoControleNMan);
        TextView tvInfoControlePV = (TextView) viewInfo.findViewById(R.id.tvInfoControlePV);
        TextView tvInfoControlePC = (TextView) viewInfo.findViewById(R.id.tvInfoControlePC);
        TextView tvInfoControleVL = (TextView) viewInfo.findViewById(R.id.tvInfoControleVL);
        TextView tvInfoControleCMax = (TextView) viewInfo.findViewById(R.id.tvInfoControleCMax);
        TextView tvInfoControleEnsAlta = (TextView) viewInfo.findViewById(R.id.tvInfoControleEnsAlta);
        TextView tvInfoControleDVMcm3 = (TextView) viewInfo.findViewById(R.id.tvInfoControleDVMcm3);
        TextView tvInfoControlePintura = (TextView) viewInfo.findViewById(R.id.tvInfoControlePintura);
        TextView tvInfoControleDVPcm3 = (TextView) viewInfo.findViewById(R.id.tvInfoControleDVPcm3);
        TextView tvInfoControleDVM10 = (TextView) viewInfo.findViewById(R.id.tvInfoControleDVM10);
        TextView tvInfoControleEnsBaixa = (TextView) viewInfo.findViewById(R.id.tvInfoControleEnsBaixa);
        TextView tvInfoControleNoOrdem = (TextView) viewInfo.findViewById(R.id.tvInfoControleNoOrdem);
        TextView tvInfoControleSeloCertificacao = (TextView) viewInfo.findViewById(R.id.tvInfoControleSeloCertificacao);
        TextView tvInfoControleResultado = (TextView) viewInfo.findViewById(R.id.tvInfoControleResultado);
        TextView tvInfoControleDataRecarga = (TextView) viewInfo.findViewById(R.id.tvInfoControleDataRecarga);
        TextView tvInfoControleDataProxRecarga = (TextView) viewInfo.findViewById(R.id.tvInfoControleDataProxRecarga);
        TextView tvInfoControleObs = (TextView) viewInfo.findViewById(R.id.tvInfoControleObs);
        Button btnInfoAlterarControle = (Button) viewInfo.findViewById(R.id.btnInfoAlterarControle);
        Button btnInfoExcluirControle = (Button) viewInfo.findViewById(R.id.btnInfoExcluirControle);
        Button btnInfoCancelarControle = (Button) viewInfo.findViewById(R.id.btnInfoCancelarControle);
        TextView tvInfoControleStatus = (TextView) viewInfo.findViewById(R.id.tvInfoControleStatus);


        tvInfoControleNoCert.setText(String.valueOf(exControleItemList.getExControle().getNumeroServico()));
        tvInfoControleNoFabric.setText(String.valueOf(exControleItemList.getExControle().getNumero()));
        tvInfoControleMesAno.setText(exControleItemList.getExControle().getMesAno());
        tvInfoControleMarca.setText(exControleItemList.getExControle().getFabricante().getNome());
        String[] tipos = contexto.getResources().getStringArray(R.array.tipos);
        tvInfoControleTipo.setText(tipos[exControleItemList.getExControle().getTipo()]);
        tvInfoControleCapacidade.setText(String.format("%.2f", exControleItemList.getExControle().getCapacidade()));
        tvInfoControleNoOS.setText(String.valueOf(exControleItemList.getExControle().getCodigo()));
        tvInfoControlePTrab.setText(exControleItemList.getExControle().getpTrabalho());
        tvInfoControleNormaFab.setText(exControleItemList.getExControle().getNormaFabricacao());
        tvInfoControleNMan.setText(String.valueOf(exControleItemList.getExControle().getpNivel()));
        tvInfoControlePV.setText(String.format("%.2f", exControleItemList.getExControle().getPesoPV()));
        tvInfoControlePC.setText(String.format("%.2f", exControleItemList.getExControle().getPesoPC()));
        tvInfoControleVL.setText(String.format("%.2f", exControleItemList.getExControle().getvL()));
        tvInfoControleCMax.setText(String.format("%.2f", exControleItemList.getExControle().getcMaxima()));
        tvInfoControleEnsAlta.setText(String.format("%.2f", exControleItemList.getExControle().getEnsAltaPressao()));
        tvInfoControleDVMcm3.setText(String.format("%.2f", exControleItemList.getExControle().getDvm()));
        if(exControleItemList.getExControle().getPintura() == 0)
            tvInfoControlePintura.setText("Não");
        else
            tvInfoControlePintura.setText("Sim");
        tvInfoControleDVPcm3.setText(String.format("%.2f", exControleItemList.getExControle().getDvp()));
        tvInfoControleDVM10.setText(String.format("%.2f", exControleItemList.getExControle().getDvm10()));
        tvInfoControleEnsBaixa.setText(String.format("%.2f", exControleItemList.getExControle().getEnsBaixaPressao()));
        tvInfoControleNoOrdem.setText(String.valueOf(exControleItemList.getExControle().getNumeroOrdem()));
        tvInfoControleSeloCertificacao.setText(String.valueOf(exControleItemList.getExControle().getSeloCertificacao()));
        if(exControleItemList.getExControle().getResultado() == 0)
            tvInfoControleResultado.setText("Aprovado");
        else
            tvInfoControleResultado.setText("Reprovado");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvInfoControleDataRecarga.setText(dateFormat.format(exControleItemList.getExControle().getDataRecarga()));
        tvInfoControleObs.setText(exControleItemList.getExControle().getObs());
        tvInfoControleDataProxRecarga.setText(dateFormat.format(exControleItemList.getExControle().getDataProximaRecarga()));
        if(exControleItemList.getExControle().getStatus() == 0){
            tvInfoControleStatus.setText("Pendente");
        }else{
            tvInfoControleStatus.setText("Envidado");
        }
        exControleCorrente = exControleItemList.getExControle();
        this.posicao = position;
        btnInfoAlterarControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ControleExtintorActivity.class);
                intent.putExtra("excontrole", exControleCorrente);
                activity.startActivityForResult(intent, 1);
                dialogo.dismiss();
            }
        });

        btnInfoExcluirControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exControleModel = new EXControleModel(contexto);
                        exControleModel.tratarExclusao(exControleCorrente.getId());
                        itens.remove(posicao);
                        notifyDataSetChanged();
                        dialogo.dismiss();
                    }
                });
                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogo.dismiss();
                    }
                });
                alert.setTitle("Exclusão de Controle de Extintor");
                alert.setMessage("Deseja excluir o Controle de Extintor?");
                alert.show();
            }
        });

        btnInfoCancelarControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
    }

    private class Suporte{

        TextView tvListControleNoCert;
        TextView tvListControleNoFabric;
        TextView tvListControleMesAno;
        TextView tvListControleMarca;
        TextView tvListControleCapacidade;
        TextView tvListControleNoOS;
        TextView tvListControleTipo;
        //TextView tvListControleStatus;
        ImageView imageListControleInfo;
        ImageView imageStatusEnvioExtintor;

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
