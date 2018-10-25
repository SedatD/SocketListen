package smarteq.com.socketlisten;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener, OnTCPMessageRecievedListener {

    private ArrayList<LinearLayout> layoutList = new ArrayList<>();
    private int totalKoltuk = 0;
    private LinearLayout layout1, layout2, layout3, layout4, layout5, layout6;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        // dev2 BF96A5 3032 ..1.01......101..
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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
