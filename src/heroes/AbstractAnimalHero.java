package heroes;

import places.Place;
import places.Places;

import java.util.Objects;

public abstract class AbstractAnimalHero extends AbstractFlowersKeeper {
    private final String name;
    private Places place;
    private Feelings feeling;
    private boolean isOfferedToDoSmthg;

    public AbstractAnimalHero(String name, int amountOfFlowers, Places place, Feelings feeling) {
        if (name.equals("")) throw new IllegalArgumentException("Name must be at least 1 symbol");
        if (place == null) throw new NullPointerException("place can't be null");
        if (feeling == null) throw new NullPointerException("feeling can't be null");
        if (amountOfFlowers < 0) throw new IllegalArgumentException("amountOfFlowers must be at least 0");
        setAmountOfFlowers(amountOfFlowers);
        this.name = name;
        this.place = place;
        this.feeling = feeling;
        isOfferedToDoSmthg = false;
    }

    public Feelings getFeeling() {
        return feeling;
    }

    public void setFeeling(Feelings feeling) {
        if (feeling == null) throw new NullPointerException("Another hero can't be null");
        this.feeling = feeling;
    }

    public boolean isOfferedToDoSmthg() {
        return isOfferedToDoSmthg;
    }

    public void setOfferedToDoSmthg(boolean offeredToDoSmthg) {
        isOfferedToDoSmthg = offeredToDoSmthg;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        if (place == null) throw new NullPointerException("place can't be null");
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void takeFlowersFrom(AbstractFlowersKeeper giver, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount of flowers must be more then 0");
        if (giver == null) throw new NullPointerException("giver can't be null");
        if (giver.getAmountOfFlowers() - amount >= 0) {
            giver.setAmountOfFlowers(giver.getAmountOfFlowers() - amount);
            setAmountOfFlowers(getAmountOfFlowers() + amount);
        } else {
            System.out.println("?????????????????????? ???????????????????? ????????????");
        }
        if (giver instanceof AbstractAnimalHero) {
            System.out.println("'" + getName() + "' ???????? ?? '" + ((AbstractAnimalHero) giver).getName() + "' " + amount + " ????????????");
        } else {
            System.out.println("'" + getName() + "' ???????????? ?? ?????????? '" + ((Place) giver).getPlace().getName() + "' " + amount + " ????????????");
        }
    }

    public void moveFlowersTo(AbstractFlowersKeeper accepter, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount of flowers must be more then 0");
        if (accepter == null) throw new NullPointerException("accepter can't be null");
        if (getAmountOfFlowers() - amount >= 0) {
            setAmountOfFlowers(getAmountOfFlowers() - amount);
            accepter.setAmountOfFlowers(accepter.getAmountOfFlowers() + amount);
        } else {
            System.out.println("?????????????????????????? ???????????????????? ????????????");
        }
        if (accepter instanceof AbstractAnimalHero) {
            System.out.println("'" + getName() + "' ?????????? ?????????? '" + ((AbstractAnimalHero) accepter).getName() + "' " + amount + " ????????????");
        } else {
            System.out.println("'" + getName() + "' ?????????????? ?? ?????????? '" + ((Place) accepter).getPlace().getName() + "' " + amount + " ????????????");
        }
    }

    public void checkFlowers() {
        if (getAmountOfFlowers() == 0) {
            setFeeling(Feelings.SADNESS);
            System.out.println("?? '" + getName() + "' ???? ???????? ????????????, ???? ???????????????????? '" + getFeeling().getNameOfFeeling() + "'");
        } else {
            setFeeling(Feelings.HAPPYNESS);
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name:" + name + "]";
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) return true;
        if (!(anotherObject instanceof AbstractAnimalHero)) return false;
        AbstractAnimalHero anotherHero = (AbstractAnimalHero) anotherObject;
        return name.equals(anotherHero.name) && place == anotherHero.place && feeling == anotherHero.feeling && isOfferedToDoSmthg == anotherHero.isOfferedToDoSmthg && getAmountOfFlowers() == anotherHero.getAmountOfFlowers();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place, feeling, isOfferedToDoSmthg, getAmountOfFlowers());
    }
}
