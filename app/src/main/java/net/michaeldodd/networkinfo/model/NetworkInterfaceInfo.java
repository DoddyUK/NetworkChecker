package net.michaeldodd.networkinfo.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class NetworkInterfaceInfo {
    public abstract int type();
    public abstract String name();
    public abstract String typeName();
    public abstract String address();
    public abstract String gateway();
    public abstract boolean isIPV6();

    public static NetworkInterfaceInfo create(int type, String name, String typeName, String address, String gateway, boolean isIPV6) {
        return new AutoValue_NetworkInterfaceInfo(type, name, typeName, address, gateway, isIPV6);
    }
}
