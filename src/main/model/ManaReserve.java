package main.model;

public class ManaReserve {
    private final int MANA_MAX_LIMIT = 10;
    private int actualMax;
    private int actualAvailable;

    public ManaReserve() {
        actualMax = 0;
        actualAvailable = 0;
    }

    public boolean hasAvailable(int amount) {
        if(actualAvailable >= amount)
                return true;

        return false;
    }

    public boolean isFull() {
        return actualMax >= MANA_MAX_LIMIT;
    }

    public void use(int amount) {
        actualAvailable -= amount;

        if(actualAvailable <= 0)
            actualAvailable = 0;
    }

    public void fill() {
        if(actualMax >= MANA_MAX_LIMIT) return;

        actualMax++;
    }

    public void refull() {
        actualAvailable = actualMax;
    }
}
