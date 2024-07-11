public class DateModifier {
    private int day;
    private float pricePercent;

    public DateModifier(int day, float pricePercent){
        this.day = day;
        this.pricePercent = pricePercent;
    }

    public void setPricePercent(float pricePercent) {
        this.pricePercent = pricePercent;
    }

    public float getPricePercent() {
        return pricePercent;
    }

    public int getDay() {
        return day;
    }
}
