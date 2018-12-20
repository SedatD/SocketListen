package smarteq.com.socketlisten;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class DynamicVersionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnTCPMessageRecievedListener {

    private final String TAG = "DynamicVersionActivity";

    private ArrayList<LinearLayout> layoutList = new ArrayList<>();
    private int totalKoltuk = 0;
    private LinearLayout layout1, layout2, layout3, layout4, layout5, layout6;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_version);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout5 = findViewById(R.id.layout5);
        layout6 = findViewById(R.id.layout6);

        layoutList.add(layout1);
        layoutList.add(layout2);
        layoutList.add(layout3);
        layoutList.add(layout4);
        layoutList.add(layout5);
        layoutList.add(layout6);

        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Spinner spinner4 = findViewById(R.id.spinner4);
        Spinner spinner5 = findViewById(R.id.spinner5);
        Spinner spinner6 = findViewById(R.id.spinner6);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);

        TCPCommunicator writer = TCPCommunicator.getInstance();
        TCPCommunicator.addListener(this);
        writer.init(8888);

        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                TCPCommunicator.writeToSocket(jsonReadyForSend);
            }
        });
        thread.start();*/
    }

    @Override
    public void onTCPMessageRecieved(String message) {
        final String theMessage = message;
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    refreshSquares(theMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        i++;
        switch (adapterView.getId()) {
            case R.id.spinner1:
                dynamicSquares(layout1, i);
                break;
            case R.id.spinner2:
                dynamicSquares(layout2, i);
                break;
            case R.id.spinner3:
                dynamicSquares(layout3, i);
                break;
            case R.id.spinner4:
                dynamicSquares(layout4, i);
                break;
            case R.id.spinner5:
                dynamicSquares(layout5, i);
                break;
            case R.id.spinner6:
                dynamicSquares(layout6, i);
                break;
        }

        totalKoltuk = 0;
        for (int j = 0; j < layoutList.size(); j++) {
            totalKoltuk += layoutList.get(j).getChildCount();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

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

    public void refreshSquares(String hamData) {
        // dev1 BF96A5 3032 ..1.01......101..
        String devName = hamData.split(" ")[0];
        int devNumber = Integer.parseInt(String.valueOf(devName.charAt(3)));
        int startPoint = (devNumber - 1) * 6;
        int endPoint = devNumber * 6;
        String byteData = hamData.split(" ")[3];
        byteData = byteData.replaceAll("\\.", "");

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

}
