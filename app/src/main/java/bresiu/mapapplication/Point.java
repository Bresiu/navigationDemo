package bresiu.mapapplication;

public class Point {
    private int counter;
    private float speed;
    private double longitude;
    private double latitude;
    private long time;

    public Point(int counter, float speed, double longitude, double latitude, long time) {
        this.counter = counter;
        this.speed = speed;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

    @Override
    public String toString() {
        return counter + " " + speed + " " + longitude + " " + latitude + " " + time;
    }
}
