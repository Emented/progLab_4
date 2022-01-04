package heroes;

import exceptions.FeelingNewVerseException;
import places.Places;

import java.util.Objects;

public class Bear extends AbstractAnimalHero {

    public Bear(String name, Places place, Feelings feeling) {
        super(name, 0, place, feeling);
    }

    public void refuseOffer() {
        if (isOfferedToDoSmthg()) {
            System.out.println("'" + getName() + "' отказался от предложения");
            setOfferedToDoSmthg(false);
        } else {
            System.out.println("герою '" + getName() + "' ничего не предлагали");
        }
    }

    public void tellAboutDecidedAction(InterfaceOfBearsAction action) {
        if (action == null) throw new NullPointerException("action can't be null");
        System.out.println("'" + getName() + "' сказал, что он собирается " + action.getName());
    }

    public void feelNewVerse() throws FeelingNewVerseException {
        if (isOfferedToDoSmthg()) {
            final double FEEL_CHANCE = 0.85;
            // локальный класс шумелки, т.к. она появляется только в результате того, что герой думает о ней и используется только в этом методе
            class Shumelka {
                private final String name;

                public Shumelka(String name) {
                    if (name.equals("")) throw new IllegalArgumentException("Name must be at least 1 symbol");
                    this.name = name;
                }

                private void comeToMind(AbstractAnimalHero hero) {
                    if (hero == null) throw new NullPointerException("hero can't be null");
                    System.out.println("'" + name + "' пришел в голову герою '" + hero.getName() + "', и он почувствовал это");
                    refuseOffer();
                }
            }
            if (Math.random() > FEEL_CHANCE) {
                throw new FeelingNewVerseException("Пух не вспомнил куплет Шумелки");
            }
            Shumelka verse = new Shumelka("куплет Шумелки");
            verse.comeToMind(this);
        }
    }

    // внутренний класс действия медведя
    public class waitForSmbd implements InterfaceOfBearsAction {
        AbstractAnimalHero hero;
        String name;

        public waitForSmbd(AbstractAnimalHero hero) {
            if (hero == null) throw new NullPointerException("hero can't be null");
            this.hero = hero;
            name = "подождать героя '" + hero.getName() + "'";
        }

        @Override
        public void tellAboutAction(Bear teller) {
            if (teller == null) throw new NullPointerException("Teller can't be null");
            System.out.println("'" + teller.getName() + "' решил подождать героя '" + hero.getName() + "'");
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof waitForSmbd)) return false;
            waitForSmbd that = (waitForSmbd) o;
            return hero.equals(that.hero) && name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hero, name);
        }

        @Override
        public String toString() {
            return getClass().getName() + "[name:" + name + "]";
        }
    }
}
