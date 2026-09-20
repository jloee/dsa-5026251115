package lw01.prelab;

public class Monoprint extends PrintJob {

    public Monoprint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        return getPages() * 500;
    
    }

    @Override
    public String label() {
        return "Mono";
    }
}
