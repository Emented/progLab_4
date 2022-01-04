package heroes;

public abstract class AbstractFlowersKeeper {
    private int amountOfFlowers;

    public int getAmountOfFlowers() {
        return amountOfFlowers;
    }

    public void setAmountOfFlowers(int amount) {
        if (amount < 0) throw new IllegalArgumentException("amountOfFlowers must be at least 0");
        this.amountOfFlowers = amount;
    }
}
