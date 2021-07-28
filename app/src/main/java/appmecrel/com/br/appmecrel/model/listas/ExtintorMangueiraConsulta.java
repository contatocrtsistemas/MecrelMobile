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

import java.util.List;

import appmecrel.com.br.appmecrel.R;
import appmecrel.com.br.appmecrel.controller.ControleMangueiraActivity;
import appmecrel.com.br.appmecrel.model.bean.ExtintorMangueira;
import appmecrel.com.br.appmecrel.model.validacao.ExtintorMangueiraModel;

/**
 * Created by usuario on 25/08/2015.
 */
public class ExtintorMangueiraConsulta extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ExtintorMangueiraItemList> itens;
    private View selectedView;
    private int lastPosition = -1;
    private Context contexto;
    private View viewInfo;
    private int posicao;
    private int posicaoSelecionada;
    private AlertDialog dialogo;
    private ExtintorMangueiraModel extintorMangueiraModel;
    private ExtintorMangueira extintorMangueiraCorrente;
    private Activity activity;
    private ImageView imagem;
    private TextView tvStatus;

    public ExtintorMangueiraConsulta(Context context, List<ExtintorMangueiraItemList> itens) {
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
    public ExtintorMangueiraItemList getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void preencheViewControle(View viewInfo, ExtintorMangueiraItemList extintorMangueiraItemList, int position) {

        TextView tvInfoMangNoOs = (TextView) viewInfo.findViewById(R.id.tvInfoMangNoOs);
        TextView tvInfoMangCliente = (TextView) viewInfo.findViewById(R.id.tvInfoMangCliente);
        TextView tvInfoMangIdentificacao = (TextView) viewInfo.findViewById(R.id.tvInfoMangIdentificacao);
        TextView tvInfoMangDutoFlexivel = (TextView) viewInfo.findViewById(R.id.tvInfoMangDutoFlexivel);
        TextView tvInfoMangMarcaUniao = (TextView) viewInfo.findViewById(R.id.tvInfoMangMarcaUniao);
        TextView tvInfoMangDiametro = (TextView) viewInfo.findViewById(R.id.tvInfoMangDiametro);
        TextView tvInfoMangCompNominal = (TextView) viewInfo.findViewById(R.id.tvInfoMangCompNominal);
        TextView tvInfoMangTipo = (TextView) viewInfo.findViewById(R.id.tvInfoMangTipo);
        TextView tvInfoMangMesAnoFabricacao = (TextView) viewInfo.findViewById(R.id.tvInfoMangMesAnoFabricacao);
        TextView tvInfoMangPreEnsaio = (TextView) viewInfo.findViewById(R.id.tvInfoMangPreEnsaio);
        TextView tvInfoMangProximaInspecao = (TextView) viewInfo.findViewById(R.id.tvInfoMangProximaInspecao);
        TextView tvInfoMangProximaManutencao = (TextView) viewInfo.findViewById(R.id.tvInfoMangProximaManutencao);
        TextView tvInfoMangCompReal = (TextView) viewInfo.findViewById(R.id.tvInfoMangCompReal);
        TextView tvInfoMangCarcaca = (TextView) viewInfo.findViewById(R.id.tvInfoMangCarcaca);
        TextView tvInfoMangUnioes = (TextView) viewInfo.findViewById(R.id.tvInfoMangUnioes);
        TextView tvInfoMangVedacao = (TextView) viewInfo.findViewById(R.id.tvInfoMangVedacao);
        TextView tvInfoMangMarcacao = (TextView) viewInfo.findViewById(R.id.tvInfoMangMarcacao);
        TextView tvInfoMangEnsaioHidro = (TextView) viewInfo.findViewById(R.id.tvInfoMangEnsaioHidro);
        TextView tvInfoMangReempatacao = (TextView) viewInfo.findViewById(R.id.tvInfoMangReempatacao);
        TextView tvInfoMangCompFinal = (TextView) viewInfo.findViewById(R.id.tvInfoMangCompFinal);
        TextView tvInfoMangSubUnioes = (TextView) viewInfo.findViewById(R.id.tvInfoMangSubUnioes);
        TextView tvInfoMangSubVedacoes = (TextView) viewInfo.findViewById(R.id.tvInfoMangSubVedacoes);
        TextView tvInfoMangSubAneis = (TextView) viewInfo.findViewById(R.id.tvInfoMangSubAneis);
        TextView tvInfoMangNovoEnsaioHidro = (TextView) viewInfo.findViewById(R.id.tvInfoMangNovoEnsaioHidro);
        TextView tvInfoMangLimpeza = (TextView) viewInfo.findViewById(R.id.tvInfoMangLimpeza);
        TextView tvInfoMangSecagem = (TextView) viewInfo.findViewById(R.id.tvInfoMangSecagem);
        TextView tvInfoMangStatus = (TextView) viewInfo.findViewById(R.id.tvInfoMangStatus);
        TextView tvNOrdem = (TextView) viewInfo.findViewById(R.id.tvNOrdem);
        Button btnInfoAlterarControle = (Button) viewInfo.findViewById(R.id.btnInfoAlterarMang);
        Button btnInfoExcluirControle = (Button) viewInfo.findViewById(R.id.btnInfoExcluirMang);
        Button btnInfoCancelarControle = (Button) viewInfo.findViewById(R.id.btnInfoCancelarMang);

        preencheTexto(tvInfoMangNoOs, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getOrcCodigo()));
        preencheTexto(tvInfoMangCliente, extintorMangueiraItemList.getExtintorMangueira().getCliente().getCliNome());
        preencheTexto(tvInfoMangIdentificacao, extintorMangueiraItemList.getExtintorMangueira().getIdentificacao());
        preencheTexto(tvInfoMangDutoFlexivel, extintorMangueiraItemList.getExtintorMangueira().getMarcaDuto());
        preencheTexto(tvInfoMangMarcaUniao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getMarcaUniao()));
        preencheTexto(tvInfoMangDiametro, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getDiametro()));
        preencheTexto(tvInfoMangCompNominal, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getCompNormal()));
        preencheTexto(tvInfoMangMesAnoFabricacao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getMesAnoFabricacao()));
        preencheTexto(tvInfoMangPreEnsaio, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getPressaoEnsaio()));
        preencheTexto(tvInfoMangProximaInspecao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getProximaInspecao()));
        preencheTexto(tvInfoMangProximaManutencao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getProximaManutencao()));
        preencheTexto(tvInfoMangCompReal, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getCompReal()));
        preencheTexto(tvInfoMangCarcaca, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getCarcacaRevestimento()));
        preencheTexto(tvInfoMangUnioes, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getUnioes()));
        preencheTexto(tvInfoMangVedacao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getVedacaoBorracha()));
        preencheTexto(tvInfoMangMarcacao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getMarcacao()));
        preencheTexto(tvInfoMangEnsaioHidro, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getEnsaioHidrostatico()));
        preencheTexto(tvInfoMangReempatacao, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getReempatacao()));
        preencheTexto(tvInfoMangCompFinal, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getCompFinal()));
        preencheTexto(tvInfoMangSubUnioes, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getSubUnioes()));
        preencheTexto(tvInfoMangSubVedacoes, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getSubVedacoes()));
        preencheTexto(tvInfoMangSubAneis, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getSubAneis()));
        preencheTexto(tvInfoMangNovoEnsaioHidro, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getNovoEnsaioHidrostatico()));
        preencheTexto(tvInfoMangLimpeza, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getLimpeza()));
        preencheTexto(tvInfoMangSecagem, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getSecagem()));
        preencheTexto(tvNOrdem, String.valueOf(extintorMangueiraItemList.getExtintorMangueira().getNOrdem()));

        if(extintorMangueiraItemList.getExtintorMangueira().getStatus() == 0){
            tvInfoMangStatus.setText("Pendente");
        }else{
            tvInfoMangStatus.setText("Envidado");
        }
        extintorMangueiraCorrente = extintorMangueiraItemList.getExtintorMangueira();
        this.posicao = position;
        btnInfoAlterarControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ControleMangueiraActivity.class);
                intent.putExtra("extintormangueira", extintorMangueiraCorrente);
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
                        extintorMangueiraModel = new ExtintorMangueiraModel(contexto);
                        extintorMangueiraModel.tratarExclusao(extintorMangueiraCorrente.getId());
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Suporte item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.extintormangueira_item_listconsulta, null);

            item = new Suporte();
            item.tvListNoOS = (TextView) convertView.findViewById(R.id.tvListNoOS);
            item.tvListNomeCliente = (TextView) convertView.findViewById(R.id.tvListNomeCliente);
            item.tvListIdentificacao = (TextView) convertView.findViewById(R.id.tvListIdentificacao);
            item.tvListMesAnoFabricacao = (TextView) convertView.findViewById(R.id.tvListMesAnoFabricacao);
            item.imageListControleInfo = (ImageView) convertView.findViewById(R.id.imageListControleInfo);
            item.imageStatusEnvio = (ImageView) convertView.findViewById(R.id.imageStatusEnvio);
            //item.tvListStatus = (TextView) convertView.findViewById(R.id.tvListStatus);
            item.imageListControleInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = LayoutInflater.from(contexto);
                    AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
                    viewInfo = inflater.inflate(R.layout.layout_informacoes_extintormangueira, null);
                    preencheViewControle(viewInfo, itens.get(posicaoSelecionada), posicaoSelecionada);
                    alert.setView(viewInfo);
                    dialogo = alert.create();
                    dialogo.show();
                    // alert.show();
                }
            });
            item.imageStatusEnvio.setOnClickListener(

                    new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            try {
                                imagem = (ImageView) v.findViewById(R.id.imageStatusEnvio);
                                //tvStatus = (TextView) v.findViewById(R.id.tvListStatus);
                                int position = (Integer) v.getTag();
                                if(itens.get(position).getExtintorMangueira().getStatus() == 1) {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
                                    posicaoSelecionada = position;
                                    alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ExtintorMangueiraModel extintorMangueiraModel = new ExtintorMangueiraModel(contexto);
                                            extintorMangueiraModel.setStatus(itens.get(posicaoSelecionada).getExtintorMangueira().getId(), 0);
                                            itens.get(posicaoSelecionada).getExtintorMangueira().setStatus(0);
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
        } else {
            item = (Suporte) convertView.getTag();
        }

        ExtintorMangueiraItemList itemList = itens.get(position);
        item.tvListNoOS.setText(String.valueOf(itemList.getExtintorMangueira().getOrcCodigo()));
        item.tvListNomeCliente.setText(itemList.getExtintorMangueira().getCliente().getCliNome());
        item.tvListIdentificacao.setText(String.valueOf(itemList.getExtintorMangueira().getIdentificacao()));
        item.tvListMesAnoFabricacao.setText(itemList.getExtintorMangueira().getMesAnoFabricacao());
        item.imageStatusEnvio.setTag(position);
        if (itemList.getExtintorMangueira().getStatus() == 0){
            //item.tvListStatus.setText("Pendente");
            item.imageStatusEnvio.setImageResource(R.drawable.padlockopen);
        }
        else{
            //item.tvListStatus.setText("Enviado");
            item.imageStatusEnvio.setImageResource(R.drawable.padlockclosed);
        }
        return convertView;
    }

    private class Suporte{

        TextView tvListNoOS;
        TextView tvListNomeCliente;
        TextView tvListIdentificacao;
        TextView tvListMesAnoFabricacao;
        TextView tvListStatus;
        ImageView imageListControleInfo;
        ImageView imageStatusEnvio;

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

    private void preencheTexto(TextView textView, String texto){
        textView.setText(texto);
    }
}
