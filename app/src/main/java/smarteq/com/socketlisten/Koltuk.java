package smarteq.com.socketlisten;

import android.widget.ImageButton;

/**
 * Created by SD
 * on 19.12.2018.
 */

public class Koltuk {

    private ImageButton imageButton;
    private boolean status;
    private int renk;
    private int baseIndex;
    private int currentIndex;

    public Koltuk(ImageButton imageButton, boolean status, int renk, int baseIndex, int currentIndex) {
        this.imageButton = imageButton;
        this.status = status;
        this.renk = renk;
        this.baseIndex = baseIndex;
        this.currentIndex = currentIndex;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRenk() {
        return renk;
    }

    public void setRenk(int renk) {
        this.renk = renk;
    }

    /*public int getBaseIndex() {
        return baseIndex;
    }*/

    /*public void setBaseIndex(int baseIndex) {
        this.baseIndex = baseIndex;
    }*/

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

}
