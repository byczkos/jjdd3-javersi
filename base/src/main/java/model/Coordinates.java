package model;

public final class Coordinates {

    private double longitude;
    private double latitude;

    public Coordinates(double longtitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinates() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongtitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}