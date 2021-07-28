package appmecrel.com.br.appmecrel.model.bean;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Lucas on 27/04/2015.
 */
public class Imagem extends Application {

    private ImageView img; //Imagem
    private LinearLayout bgimg; //Layout da Activity
    private Bitmap nav; //A Imagem em formato Bitmap
    private Bitmap background; //Background em formato Bitmap
    private BitmapDrawable bg; //Background em formato Drawable

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public LinearLayout getBgimg() {
        return bgimg;
    }

    public void setBgimg(LinearLayout bgimg) {
        this.bgimg = bgimg;
    }

    public Bitmap getNav() {
        return nav;
    }

    public void setNav(Bitmap nav) {
        this.nav = nav;
    }

    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }

    public BitmapDrawable getBg() {
        return bg;
    }

    public void setBg(BitmapDrawable bg) {
        this.bg = bg;
    }
}
