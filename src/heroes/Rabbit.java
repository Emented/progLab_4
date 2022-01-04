package heroes;

import places.Place;
import places.Places;

import java.util.Objects;

public class Rabbit extends AbstractAnimalHero {
    public Rabbit(String name, Places place, Feelings feeling) {
        super(name, 0, place, feeling);
    }

    public void goOut(Place toPlace) {
        if (toPlace == null) throw new NullPointerException("Place hero can't be null");
        if (toPlace.getPlace().isPossibileToGetInside()) {
            System.out.println("'" + getName() + "' ушел из места '" + getPlace().getName() + "' в место: '" + toPlace.getPlace().getName() + "'");
            setPlace(toPlace.getPlace());
        } else {
            System.out.println("сюда нельзя войти");
        }

    }

    public void meetAnotherHero(AbstractAnimalHero anotherHero) {
        if (anotherHero == null) throw new NullPointerException("Another hero can't be null");
        if (getPlace().equals(anotherHero.getPlace())) {
            System.out.println("'" + getName() + "' втретил '" + anotherHero.getName() + "' в месте '" + getPlace().getName() + "'");
        } else {
            setPlace(anotherHero.getPlace());
            System.out.println("'" + getName() + "' втретил '" + anotherHero.getName() + "' в месте '" + getPlace().getName() + "'");
        }
    }

    public void greetSmbd(AbstractAnimalHero hero, String whatFor) {
        if (hero == null) throw new NullPointerException("hero can't be null");
        if (whatFor.equals("")) throw new IllegalArgumentException("reason must be at least 1 symbol");
        hero.setFeeling(Feelings.HAPPYNESS);
        System.out.println("'" + getName() + "' поблагодарил героя '" + hero.getName() + "' " + whatFor);
    }

    public void offerSmbd(AbstractAnimalHero anotherHero, String offer) {
        if (anotherHero == null) throw new NullPointerException("Another hero can't be null");
        if (offer.equals("")) throw new IllegalArgumentException("offer must be at least 1 symbol");
        if (!anotherHero.isOfferedToDoSmthg()) {
            anotherHero.setOfferedToDoSmthg(true);
            System.out.println("'" + getName() + "' предложил герою '" + anotherHero.getName() + "' " + offer);
        } else {
            System.out.println("этому герою уже что-то предложили");
        }
    }

    public void tellAboutMustAction(InterfaceOfRabbitsAction action, AbstractAnimalHero listener) {
        if (action == null) throw new NullPointerException("action can't be null");
        action.tellAboutAction(Rabbit.this);
        if (listener != null) {
            offerSmbd(listener, action.getName());
        }
    }

    // внутренний класс действия кролика
    public class visitHero implements InterfaceOfRabbitsAction {
        private final String name;
        AbstractAnimalHero hero;

        public visitHero(AbstractAnimalHero hero) {
            if (hero == null) throw new NullPointerException("hero can't be null");
            this.hero = hero;
            name = "навестить героя '" + hero.getName() + "'";
        }

        public String getName() {
            return name;
        }

        public void tellAboutAction(Rabbit teller) {
            if (teller == null) throw new NullPointerException("teller hero can't be null");
            System.out.println("'" + teller.getName() + "' должен навестить героя '" + hero.getName() + "'");
        }

        public void performAction(Rabbit teller) {
            if (teller == null) throw new NullPointerException("teller hero can't be null");
            if (getPlace().equals(hero.getPlace())) {
                System.out.println("'" + teller.getName() + "' навестил '" + hero.getName() + "' в месте '" + getPlace().getName() + "'");
            } else {
                setPlace(hero.getPlace());
            }
            System.out.println("'" + teller.getName() + "' навестил '" + hero.getName() + "' в месте '" + getPlace().getName() + "'");

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof visitHero)) return false;
            visitHero visitHero = (visitHero) o;
            return Objects.equals(name, visitHero.name) && hero.equals(visitHero.hero);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hero);
        }

        @Override
        public String toString() {
            return getClass().getName() + "[name:" + name + "]";
        }
    }
}

