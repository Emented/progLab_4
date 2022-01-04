package places;

public enum Places {
    HomeOfPyatachek("дом пяточка", true),
    HomeOfDonkey("дом осла Иа", true),
    HomeOfBear("дом пуха", true),
    Vase("ваза", false),
    Yard("двор", true),
    Field("лужайка", true);

    private final boolean possibleToGetInside;
    private final String name;

    Places(String name, boolean possibileToGetInside) {
        if (name.equals("")) throw new IllegalArgumentException("Name must be at least 1 symbol");
        this.name = name;
        this.possibleToGetInside = possibileToGetInside;
    }

    public boolean isPossibileToGetInside() {
        return this.possibleToGetInside;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[place:" + name + "]";
    }
}
