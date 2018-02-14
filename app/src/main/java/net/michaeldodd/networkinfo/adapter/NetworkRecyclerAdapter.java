package net.michaeldodd.networkinfo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.michaeldodd.networkinfo.model.NetworkInterfaceInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkRecyclerAdapter extends RecyclerView.Adapter<NetworkRecyclerAdapter.NetworkViewHolder> {

    private final List<NetworkInterfaceInfo> interfaces = new ArrayList<>();

    @Override
    public NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NetworkViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return interfaces.size();
    }

    public void setItems(List<NetworkInterfaceInfo> interfaces) {
        this.interfaces.clear();
        this.interfaces.addAll(interfaces);
    }

    public void add(NetworkInterfaceInfo interfaceInfo) {
        this.interfaces.add(interfaceInfo);
    }

    public class NetworkViewHolder extends RecyclerView.ViewHolder {

        public NetworkViewHolder(View itemView) {
            super(itemView);
        }
    }
}
