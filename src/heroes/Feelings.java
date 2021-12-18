package heroes;

public enum Feelings {
    HAPPYNESS("радость"),
    SADNESS("грусть"),
    COMPASSION("сострадание"),
    BIGGERCOMPASSION("большее сострадание");

    private final String feeling;

    Feelings(String feeling) {
        this.feeling = feeling;
    }

    public String getNameOfFeeling() {
        return feeling;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[feeling:" + feeling + "]";
    }
}

