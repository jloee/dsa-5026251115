package lw01.prelab;
public abstract class PrintJob implements Chargeable{
    private String id;
    private int pages;

    protected PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("pages must be positive");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
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