package net.michaeldodd.networkinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.RouteInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.michaeldodd.networkinfo.adapter.NetworkRecyclerAdapter;
import net.michaeldodd.networkinfo.model.NetworkInterfaceInfo;

import java.util.ArrayList;
import java.util.List;

import static net.michaeldodd.networkinfo.model.NetworkInterfaceInfo.create;

public class MainActivity extends AppCompatActivity {
    private final NetworkRecyclerAdapter adapter = new NetworkRecyclerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView networkList = findViewById(R.id.lst_network_interfaces);
        networkList.setLayoutManager(new LinearLayoutManager(this));
        networkList.setAdapter(adapter);

        getConnectionInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_refresh:
                getConnectionInfo();
                break;
        }

        return true;
    }

    private void getConnectionInfo() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) return;

        List<NetworkInterfaceInfo> interfaces = new ArrayList<>();

        for (Network network : manager.getAllNetworks()) {
            NetworkInfo info = manager.getNetworkInfo(network);
            LinkProperties properties = manager.getLinkProperties(network);

            List<LinkAddress> links = properties.getLinkAddresses();
            List<RouteInfo> routes = properties.getRoutes();

            String addr = links.isEmpty() ? "" : links.get(0).getAddress().getHostAddress();
            String gateway = routes.isEmpty() ? "" : routes.get(0).getGateway().getHostAddress();

            interfaces.add(create(
                    info.getType(),
                    properties.getInterfaceName(),
                    info.getTypeName(),
                    addr,
                    gateway,
                    false
            ));
        }

        adapter.setItems(interfaces);
        adapter.notifyDataSetChanged();
    }


}
