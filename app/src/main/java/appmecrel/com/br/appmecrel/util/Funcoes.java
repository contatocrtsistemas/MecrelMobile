package appmecrel.com.br.appmecrel.util;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import appmecrel.com.br.appmecrel.model.bean.Imagem;

/**
 * Created by Lucas on 23/02/2015.
 */
public  class Funcoes {

    public static void exibirMensagem(Context contexto,String titulo, String mensagem ){

        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setNeutralButton("OK", null);
        alert.setTitle(titulo);
        alert.setMessage(mensagem);
        alert.show();
    }

    public static double tiraCaracteresEditText(EditText editText){

        double valor = 0;
        Log.i("Funcao", editText.getText().toString());
        if (editText.getText().toString() != "0,0"){
            valor = Double.parseDouble(editText.getText().toString().replaceAll("\\.", "").replace(",", "."));
        }
        Log.i("Funcao", String.valueOf(valor));
        return valor;
    }

    public static void inserirImagemFundo(Application application, LinearLayout linearLayout, int component){
        try {
            Imagem imagem = (Imagem)application;

            if (imagem.getBgimg() != null)
                imagem.getBgimg().setBackgroundDrawable(null);
            if (imagem.getBg() != null) {
                imagem.getBackground().recycle();
            }
            imagem.setBg(null);

            imagem.setBgimg(linearLayout);

            imagem.setBackground(BitmapFactory.decodeStream(imagem.getResources().openRawResource(component)));
            imagem.setBg(new BitmapDrawable(imagem.getBackground()));
            imagem.getBgimg().setBackgroundDrawable(imagem.getBg());
        }catch (Exception e){

        }
    }

}
