package net.michaeldodd.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.michaeldodd.networkinfo.adapter.NetworkRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView networkList;
    private final NetworkRecyclerAdapter adapter = new NetworkRecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkList = findViewById(R.id.lst_network_interfaces);
        networkList.setLayoutManager(new LinearLayoutManager(this));
        networkList.setAdapter(adapter);
    }

    private void getConnectionInfo() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return;

        for (Network network : manager.getAllNetworks()) {
            LinkProperties properties = manager.getLinkProperties(network);

        }

    }
}
