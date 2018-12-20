package smarteq.com.socketlisten;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FixedVersionActivity extends AppCompatActivity implements OnTCPMessageRecievedListener, View.OnClickListener, View.OnLongClickListener {

    private final String TAG = "FixedVersionActivity";

    private ArrayList<LinearLayout> layoutList = new ArrayList<>();
    private ArrayList<Koltuk> koltukList = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_version);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        fillLayoutList();

        fillKoltukList();

        initClicksForKoltuk();

        initLongClicksForKoltuk();

        TCPCommunicator writer = TCPCommunicator.getInstance();
        TCPCommunicator.addListener(this);
        writer.init(8888);
    }

    private void fillLayoutList() {
        layoutList.add((LinearLayout) findViewById(R.id.layout1));
        layoutList.add((LinearLayout) findViewById(R.id.layout2));
        layoutList.add((LinearLayout) findViewById(R.id.layout3));
        layoutList.add((LinearLayout) findViewById(R.id.layout4));
        layoutList.add((LinearLayout) findViewById(R.id.layout5));
        layoutList.add((LinearLayout) findViewById(R.id.layout6));
    }

    private void fillKoltukList() {
        koltukList = new ArrayList<>();

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton11), true, R.drawable.green_square, 11, 11));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton12), true, R.drawable.green_square, 12, 12));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton13), true, R.drawable.green_square, 13, 13));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton14), true, R.drawable.green_square, 14, 14));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton15), true, R.drawable.green_square, 15, 15));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton16), true, R.drawable.green_square, 16, 16));

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton21), true, R.drawable.green_square, 21, 21));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton22), true, R.drawable.green_square, 22, 22));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton23), true, R.drawable.green_square, 23, 23));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton24), true, R.drawable.green_square, 24, 24));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton25), true, R.drawable.green_square, 25, 25));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton26), true, R.drawable.green_square, 26, 26));

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton31), true, R.drawable.green_square, 31, 31));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton32), true, R.drawable.green_square, 32, 32));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton33), true, R.drawable.green_square, 33, 33));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton34), true, R.drawable.green_square, 34, 34));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton35), true, R.drawable.green_square, 35, 35));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton36), true, R.drawable.green_square, 36, 36));

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton41), true, R.drawable.green_square, 41, 41));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton42), true, R.drawable.green_square, 42, 42));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton43), true, R.drawable.green_square, 43, 43));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton44), true, R.drawable.green_square, 44, 44));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton45), true, R.drawable.green_square, 45, 45));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton46), true, R.drawable.green_square, 46, 46));

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton51), true, R.drawable.green_square, 51, 51));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton52), true, R.drawable.green_square, 52, 52));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton53), true, R.drawable.green_square, 53, 53));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton54), true, R.drawable.green_square, 54, 54));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton55), true, R.drawable.green_square, 55, 55));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton56), true, R.drawable.green_square, 56, 56));

        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton61), true, R.drawable.green_square, 61, 61));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton62), true, R.drawable.green_square, 62, 62));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton63), true, R.drawable.green_square, 63, 63));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton64), true, R.drawable.green_square, 64, 64));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton65), true, R.drawable.green_square, 65, 65));
        koltukList.add(new Koltuk((ImageButton) findViewById(R.id.imageButton66), true, R.drawable.green_square, 66, 66));
    }

    private void initClicksForKoltuk() {
        findViewById(R.id.imageButton11).setOnClickListener(this);
        findViewById(R.id.imageButton12).setOnClickListener(this);
        findViewById(R.id.imageButton13).setOnClickListener(this);
        findViewById(R.id.imageButton14).setOnClickListener(this);
        findViewById(R.id.imageButton15).setOnClickListener(this);
        findViewById(R.id.imageButton16).setOnClickListener(this);

        findViewById(R.id.imageButton21).setOnClickListener(this);
        findViewById(R.id.imageButton22).setOnClickListener(this);
        findViewById(R.id.imageButton23).setOnClickListener(this);
        findViewById(R.id.imageButton24).setOnClickListener(this);
        findViewById(R.id.imageButton25).setOnClickListener(this);
        findViewById(R.id.imageButton26).setOnClickListener(this);

        findViewById(R.id.imageButton31).setOnClickListener(this);
        findViewById(R.id.imageButton32).setOnClickListener(this);
        findViewById(R.id.imageButton33).setOnClickListener(this);
        findViewById(R.id.imageButton34).setOnClickListener(this);
        findViewById(R.id.imageButton35).setOnClickListener(this);
        findViewById(R.id.imageButton36).setOnClickListener(this);

        findViewById(R.id.imageButton41).setOnClickListener(this);
        findViewById(R.id.imageButton42).setOnClickListener(this);
        findViewById(R.id.imageButton43).setOnClickListener(this);
        findViewById(R.id.imageButton44).setOnClickListener(this);
        findViewById(R.id.imageButton45).setOnClickListener(this);
        findViewById(R.id.imageButton46).setOnClickListener(this);

        findViewById(R.id.imageButton51).setOnClickListener(this);
        findViewById(R.id.imageButton52).setOnClickListener(this);
        findViewById(R.id.imageButton53).setOnClickListener(this);
        findViewById(R.id.imageButton54).setOnClickListener(this);
        findViewById(R.id.imageButton55).setOnClickListener(this);
        findViewById(R.id.imageButton56).setOnClickListener(this);

        findViewById(R.id.imageButton61).setOnClickListener(this);
        findViewById(R.id.imageButton62).setOnClickListener(this);
        findViewById(R.id.imageButton63).setOnClickListener(this);
        findViewById(R.id.imageButton64).setOnClickListener(this);
        findViewById(R.id.imageButton65).setOnClickListener(this);
        findViewById(R.id.imageButton66).setOnClickListener(this);
    }

    private void initLongClicksForKoltuk() {
        findViewById(R.id.imageButton11).setOnLongClickListener(this);
        findViewById(R.id.imageButton12).setOnLongClickListener(this);
        findViewById(R.id.imageButton13).setOnLongClickListener(this);
        findViewById(R.id.imageButton14).setOnLongClickListener(this);
        findViewById(R.id.imageButton15).setOnLongClickListener(this);
        findViewById(R.id.imageButton16).setOnLongClickListener(this);

        findViewById(R.id.imageButton21).setOnLongClickListener(this);
        findViewById(R.id.imageButton22).setOnLongClickListener(this);
        findViewById(R.id.imageButton23).setOnLongClickListener(this);
        findViewById(R.id.imageButton24).setOnLongClickListener(this);
        findViewById(R.id.imageButton25).setOnLongClickListener(this);
        findViewById(R.id.imageButton26).setOnLongClickListener(this);

        findViewById(R.id.imageButton31).setOnLongClickListener(this);
        findViewById(R.id.imageButton32).setOnLongClickListener(this);
        findViewById(R.id.imageButton33).setOnLongClickListener(this);
        findViewById(R.id.imageButton34).setOnLongClickListener(this);
        findViewById(R.id.imageButton35).setOnLongClickListener(this);
        findViewById(R.id.imageButton36).setOnLongClickListener(this);

        findViewById(R.id.imageButton41).setOnLongClickListener(this);
        findViewById(R.id.imageButton42).setOnLongClickListener(this);
        findViewById(R.id.imageButton43).setOnLongClickListener(this);
        findViewById(R.id.imageButton44).setOnLongClickListener(this);
        findViewById(R.id.imageButton45).setOnLongClickListener(this);
        findViewById(R.id.imageButton46).setOnLongClickListener(this);

        findViewById(R.id.imageButton51).setOnLongClickListener(this);
        findViewById(R.id.imageButton52).setOnLongClickListener(this);
        findViewById(R.id.imageButton53).setOnLongClickListener(this);
        findViewById(R.id.imageButton54).setOnLongClickListener(this);
        findViewById(R.id.imageButton55).setOnLongClickListener(this);
        findViewById(R.id.imageButton56).setOnLongClickListener(this);

        findViewById(R.id.imageButton61).setOnLongClickListener(this);
        findViewById(R.id.imageButton62).setOnLongClickListener(this);
        findViewById(R.id.imageButton63).setOnLongClickListener(this);
        findViewById(R.id.imageButton64).setOnLongClickListener(this);
        findViewById(R.id.imageButton65).setOnLongClickListener(this);
        findViewById(R.id.imageButton66).setOnLongClickListener(this);
    }

    @Override
    public void onTCPMessageRecieved(String message) {
        final String theMessage = message;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    refreshKoltuk(theMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void refreshKoltuk(String hamData) {
        // dev1 BF96A5 3032 ..1.01......101..
        String devName = hamData.split(" ")[0];
        int devNumber = Integer.parseInt(String.valueOf(devName.charAt(3)));
        int startPoint = (devNumber - 1) * 6;
        int endPoint = devNumber * 6;
        String byteData = hamData.split(" ")[3];
        byteData = byteData.replaceAll("\\.", "");

        int totalKoltuk = 0;
        if (!((devNumber - 1) * 6 <= totalKoltuk))
            return;

        ArrayList<ImageView> imageViewList = new ArrayList<>();
        for (int j = 0; j < layoutList.size(); j++) {
            for (int i = 0; i < layoutList.get(j).getChildCount(); i++) {
                imageViewList.add((ImageView) layoutList.get(j).getChildAt(i));
            }
        }

        int j = startPoint;
        for (int i = 0; i < byteData.length(); i++) {
            String oneDigit = byteData.substring(i, i + 1);
            if (j == imageViewList.size())
                break;
            if (oneDigit.equals("1")) {
                imageViewList.get(j).setImageResource(R.drawable.green_square);
            } else {
                imageViewList.get(j).setImageResource(R.drawable.red_square);
            }
            j++;
        }

    }

    private void changeKoltukStatus(Koltuk koltuk) {
        for (int i = 0; i < koltukList.size(); i++) {
            if (koltukList.get(i).getImageButton().getId() == koltuk.getImageButton().getId()) {
                koltukList.get(i).setStatus(!koltukList.get(i).isStatus());
                break;
            }
        }
    }

    private void changeKoltukRenk(Koltuk koltuk, int renk) {
        for (int i = 0; i < koltukList.size(); i++) {
            if (koltukList.get(i).getImageButton().getId() == koltuk.getImageButton().getId()) {
                koltukList.get(i).setRenk(renk);
                break;
            }
        }
    }

    private void swapKoltuk(Koltuk koltuk, int swapIndex) {
        for (int i = 0; i < koltukList.size(); i++) {
            if (koltukList.get(i).getCurrentIndex() == swapIndex) {
                koltukList.get(i).setCurrentIndex(koltuk.getCurrentIndex());
            }
        }
        for (int i = 0; i < koltukList.size(); i++) {
            if (koltukList.get(i).getImageButton().getId() == koltuk.getImageButton().getId()) {
                koltukList.get(i).setCurrentIndex(swapIndex);
            }
        }
    }

    private void resetView() {
        fillKoltukList();
    }

    // TODO silinecek
    private void dynamicSquares(LinearLayout linLay, int koltukAdet) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(136, 136);
        layoutParams.setMargins(8, 0, 0, 0);

        linLay.removeAllViews();

        for (int i = 0; i < koltukAdet; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.red_square);
            linLay.addView(imageView);
        }
        linLay.requestLayout();
    }

    @Override
    public void onClick(View view) {
        // TODO modal acılacak swap etmek istedigi index alıncak ve
        // swapKoltuk(koltuk,swapIndex);
    }

    @Override
    public boolean onLongClick(View view) {
        // TODO neden returnu var ornegıne bakılacak
        // TODO modal acılacak ve
        // changeKoltukStatus(koltuk);
        return false;
    }

}
