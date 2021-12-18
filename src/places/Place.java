package places;

import heroes.AbstractFlowersKeeper;

import java.util.Objects;

public class Place extends AbstractFlowersKeeper {
    Places place;

    public Place(int amountOfFlowers, Places place) {
        if (place == null) throw new NullPointerException("place can't be null");
        setAmountOfFlowers(amountOfFlowers);
        this.place = place;
    }

    public Places getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[place: " + place.getName() + "]";
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) return true;
        if (!(anotherObject instanceof Place)) return false;
        Place anotherPlace = (Place) anotherObject;
        return place == anotherPlace.place && getAmountOfFlowers() == anotherPlace.getAmountOfFlowers();
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, getAmountOfFlowers());
    }
}
