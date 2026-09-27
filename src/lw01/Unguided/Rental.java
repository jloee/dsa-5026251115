public abstract class Rental implements ChargeAble {
    private String id;
    private int days;

    protected Rental(String id, int days) {

        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public abstract String label();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies must be positive");
        }
        return calculateCharge() * copies;
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }

}
