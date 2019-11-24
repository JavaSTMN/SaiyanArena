package main.model;

public class ManaReserve {
    private final int MANA_MAX_LIMIT = 10;
    private int actualMax;
    private int actualAvailable;

    public ManaReserve() {
        actualMax = 0;
        actualAvailable = 0;
    }

    public ManaReserve(int amount) {
        actualMax = amount;
        actualAvailable = 0;
    }

    public boolean hasAvailable(int amount) {
        return actualAvailable >= amount;
    }

    public boolean isFull() {
        return actualMax >= MANA_MAX_LIMIT;
    }

    public void use(int amount) {
        if(amount > actualAvailable)
            throw new IllegalStateException("Not enough mana.");

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

    public int getActualAvailable() {
        return actualAvailable;
    }

    public void setActualAvailable(int amount) {
        actualAvailable = amount;
    }

    public int getActualMax() {
        return actualMax;
    }
}
