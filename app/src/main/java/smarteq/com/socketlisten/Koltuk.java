package smarteq.com.socketlisten;

import android.widget.Button;

/**
 * Created by SD
 * on 19.12.2018.
 */

public class Koltuk {

    private Button button;
    private boolean status;
    private int baseIndex;
    private int currentIndex;

    public Koltuk(Button button, boolean status, int baseIndex, int currentIndex) {
        this.button = button;
        this.status = status;
        this.baseIndex = baseIndex;
        this.currentIndex = currentIndex;
    }

    public Button getButton() {
        return button;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBaseIndex() {
        return baseIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

}
