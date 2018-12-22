package smarteq.com.socketlisten;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FixedVersionActivity extends AppCompatActivity implements OnTCPMessageRecievedListener, View.OnClickListener, View.OnLongClickListener {

    private final String TAG = "FixedVersionActivity";

    private ArrayList<Koltuk> koltukList = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_version);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetView();
                Toast.makeText(FixedVersionActivity.this, "Koltuklar resetlendi", Toast.LENGTH_SHORT).show();
            }
        });

        fillKoltukList();

        initClicksForKoltuk();

        initLongClicksForKoltuk();

        TCPCommunicator writer = TCPCommunicator.getInstance();
        TCPCommunicator.addListener(this);
        writer.init(8888);
    }

    private void fillKoltukList() {
        koltukList = new ArrayList<>();

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton11), true, 1, 1));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton12), true, 2, 2));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton13), true, 3, 3));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton14), true, 4, 4));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton15), true, 5, 5));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton16), true, 6, 6));

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton21), true, 7, 7));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton22), true, 8, 8));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton23), true, 9, 9));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton24), true, 10, 10));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton25), true, 11, 11));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton26), true, 12, 12));

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton31), true, 13, 13));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton32), true, 14, 14));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton33), true, 15, 15));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton34), true, 16, 16));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton35), true, 17, 17));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton36), true, 18, 18));

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton41), true, 19, 19));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton42), true, 20, 20));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton43), true, 21, 21));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton44), true, 22, 22));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton45), true, 23, 23));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton46), true, 24, 24));

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton51), true, 25, 25));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton52), true, 26, 26));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton53), true, 27, 27));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton54), true, 28, 28));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton55), true, 29, 29));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton56), true, 30, 30));

        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton61), true, 31, 31));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton62), true, 32, 32));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton63), true, 33, 33));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton64), true, 34, 34));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton65), true, 35, 35));
        koltukList.add(new Koltuk((Button) findViewById(R.id.imageButton66), true, 36, 36));

        renderKoltuk();
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

        ArrayList<Koltuk> koltukListActiveOnly = new ArrayList<>();
        for (int i = 0; i < koltukList.size(); i++) {
            if (koltukList.get(i).getStatus())
                koltukListActiveOnly.add(koltukList.get(i));
        }

        int j = startPoint;
        for (int i = 0; i < byteData.length(); i++) {
            String oneDigit = byteData.substring(i, i + 1);
            if (j == koltukListActiveOnly.size())
                break;
            if (oneDigit.equals("1")) {
                changeKoltukRenk(koltukListActiveOnly.get(j), getResources().getColor(R.color.colorGreen));
            } else {
                changeKoltukRenk(koltukListActiveOnly.get(j), getResources().getColor(R.color.colorRed));
            }
            j++;
        }

    }

    private void changeKoltukStatus(View view) {
        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == view.getId()) {
                koltukList.get(i).setStatus(!koltukList.get(i).getStatus());
                int renk = koltukList.get(i).getStatus() ? getResources().getColor(R.color.colorYellow) : getResources().getColor(R.color.colorGray);
                changeKoltukRenk(koltukList.get(i), renk);
                break;
            }
    }

    private void changeKoltukRenk(Koltuk koltuk, int renk) {
        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == koltuk.getButton().getId()) {
                koltukList.get(i).getButton().setBackgroundColor(renk);
                break;
            }
    }

    private void changeKoltukText(Koltuk koltuk, String text) {
        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == koltuk.getButton().getId()) {
                koltukList.get(i).getButton().setText(text);
                break;
            }
    }

    private void swapKoltuk(View view, int swapIndex) {
        int temp = -1;

        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == view.getId())
                temp = koltukList.get(i).getCurrentIndex();

        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getCurrentIndex() == swapIndex)
                if (temp != -1)
                    if (!koltukList.get(i).getStatus()) {
                        Toast.makeText(FixedVersionActivity.this, "Pasif koltukla yer değiştirilemez", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        koltukList.get(i).setCurrentIndex(temp);
                        koltukList.get(i).getButton().setText(temp + "");
                    }

        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == view.getId()) {
                koltukList.get(i).setCurrentIndex(swapIndex);
                koltukList.get(i).getButton().setText(swapIndex + "");
            }

    }

    private void resetView() {
        fillKoltukList();
    }

    private void renderKoltuk() {
        for (int i = 0; i < koltukList.size(); i++) {
            changeKoltukRenk(koltukList.get(i), getResources().getColor(R.color.colorYellow));
            changeKoltukText(koltukList.get(i), koltukList.get(i).getBaseIndex() + "");
        }
    }

    @Override
    public void onClick(final View view) {

        for (int i = 0; i < koltukList.size(); i++)
            if (koltukList.get(i).getButton().getId() == view.getId())
                if (!koltukList.get(i).getStatus()) {
                    Toast.makeText(FixedVersionActivity.this, "Pasif koltuk değiştirilemez", Toast.LENGTH_SHORT).show();
                    return;
                }

        LayoutInflater layoutInflater = LayoutInflater.from(FixedVersionActivity.this);
        View dialogView = layoutInflater.inflate(R.layout.dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FixedVersionActivity.this);
        alertDialogBuilder.setView(dialogView);

        Spinner spinnerSwap = (Spinner) dialogView.findViewById(R.id.spinnerSwap);
        final int[] swapliIndex = {0};

        spinnerSwap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                swapliIndex[0] = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                swapliIndex[0] = -1;
            }
        });

        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("TAMAM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (swapliIndex[0] == 0)
                                    Toast.makeText(FixedVersionActivity.this, "Bir koltuk seçin", Toast.LENGTH_SHORT).show();
                                else
                                    swapKoltuk(view, swapliIndex[0]);
                            }
                        })
                .setNegativeButton("İPTAL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onLongClick(final View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(FixedVersionActivity.this).create();
        alertDialog.setTitle("Seçtiğiniz koltuğun durumu değiştirilecektir");
        // alertDialog.setMessage("this is my app");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                changeKoltukStatus(view);
                dialog.dismiss();
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "İPTAL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();

        return true;
    }

}
