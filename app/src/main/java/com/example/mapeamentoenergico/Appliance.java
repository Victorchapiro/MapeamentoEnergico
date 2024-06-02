package com.example.mapeamentoenergico;

import android.os.Parcel;
import android.os.Parcelable;

public class Appliance implements Parcelable {
    private String name;
    private double consumption, timeAverage, energicSpent;
    private boolean isChecked;
    private int quantity;

    public Appliance(String name, double consumption, double timeAverage, double energicSpent) {
        this.name = name;
        this.consumption = consumption;
        this.timeAverage = timeAverage;
        this.energicSpent = energicSpent;
        this.isChecked = false;
        this.quantity = 0;
    }

    protected Appliance(Parcel in) {
        name = in.readString();
        isChecked = in.readByte() != 0;
        quantity = in.readInt();
        consumption = in.readDouble();
        timeAverage = in.readDouble();
        energicSpent = in.readDouble();
    }

    public static final Creator<Appliance> CREATOR = new Creator<Appliance>() {
        @Override
        public Appliance createFromParcel(Parcel in) {
            return new Appliance(in);
        }

        @Override
        public Appliance[] newArray(int size) {
            return new Appliance[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getTimeAverage() {
        return timeAverage;
    }

    public void setTimeAverage(double timeAverage) {
        this.timeAverage = timeAverage;
    }

    public double getEnergicSpent() {
        return energicSpent;
    }

    public void setEnergicSpent(double energicSpent) {
        this.energicSpent = energicSpent;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(quantity);
        dest.writeDouble(consumption);
        dest.writeDouble(timeAverage);
        dest.writeDouble(energicSpent);
    }
}
