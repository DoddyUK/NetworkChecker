package net.michaeldodd.networkinfo.model;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class NetworkInterfaceInfo {
    public abstract String name();
    public abstract String address();
    public abstract String gateway();
}
