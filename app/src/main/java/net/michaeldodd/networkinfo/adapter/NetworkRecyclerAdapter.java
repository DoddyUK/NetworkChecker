package net.michaeldodd.networkinfo.adapter;

import android.net.ConnectivityManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.michaeldodd.networkinfo.R;
import net.michaeldodd.networkinfo.model.NetworkInterfaceInfo;

import java.util.ArrayList;
import java.util.List;

public class NetworkRecyclerAdapter extends RecyclerView.Adapter<NetworkRecyclerAdapter.NetworkViewHolder> {

    private final List<NetworkInterfaceInfo> interfaces = new ArrayList<>();

    @Override
    public NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_interface_info, parent, false);
        return new NetworkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NetworkViewHolder holder, int position) {
        NetworkInterfaceInfo info = interfaces.get(position);
        holder.lblDeviceName.setText(info.name());
        holder.imgIcon.setImageResource(getIconForInterface(info.type()));
        setOrHide(holder.lblDeviceType, info.typeName());
        setOrHide(holder.lblIpAddress, info.address());
        setOrHide(holder.lblGateway, info.gateway());
    }

    @Override
    public int getItemCount() {
        return interfaces.size();
    }

    public void setItems(List<NetworkInterfaceInfo> interfaces) {
        this.interfaces.clear();
        this.interfaces.addAll(interfaces);
    }

    @DrawableRes
    public int getIconForInterface(int type) {
        switch (type) {
            case ConnectivityManager.TYPE_ETHERNET:
                return R.drawable.ic_settings_ethernet_black_24dp;

            case ConnectivityManager.TYPE_WIFI:
                return R.drawable.ic_network_wifi_black_24dp;

            case ConnectivityManager.TYPE_MOBILE:
            default:
                return R.drawable.ic_network_cell_black_24dp;
        }
    }

    private void setOrHide(TextView view, @Nullable String text) {
        if (TextUtils.isEmpty(text)) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setText(text);
            view.setVisibility(View.VISIBLE);
        }

    }

    public void add(NetworkInterfaceInfo interfaceInfo) {
        this.interfaces.add(interfaceInfo);
    }

    public class NetworkViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView lblDeviceName;
        TextView lblDeviceType;
        TextView lblIpVersion;
        TextView lblIpAddress;
        TextView lblGateway;

        public NetworkViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon);
            lblDeviceName = itemView.findViewById(R.id.lbl_interface_name);
            lblDeviceType = itemView.findViewById(R.id.lbl_card_interface_type);
            lblIpVersion = itemView.findViewById(R.id.lbl_card_ip_version);
            lblIpAddress = itemView.findViewById(R.id.lbl_card_ip_address);
            lblGateway = itemView.findViewById(R.id.lbl_card_gateway);
        }
    }
}
