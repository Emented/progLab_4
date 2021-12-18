package heroes;

import places.Place;
import places.Places;
import times.InterfaceOfTime;

import java.util.Objects;

public class Pyatachok extends AbstractAnimalHero implements InterfaceOfThinking {
    private boolean isSleeping = true;
    private boolean hasAnAimToDoSmthg = false;
    private int degreeOfMem = 0;
    private InterfaceOfPigsAction link;

    public Pyatachok(String name, Places place, Feelings feeling) {
        super(name, 0, place, feeling);
    }

    public void getUp(InterfaceOfTime time) {
        if (time == null) {
            throw new NullPointerException("time can't be null");
        } else {
            isSleeping = false;
            setFeeling(Feelings.HAPPYNESS);
            System.out.println("'" + getName() + "' встал '" + time.getTime() + "'");
        }
    }

    public void setHasAnAimToDoSmthg(boolean hasAnAimToDoSmthg) {
        this.hasAnAimToDoSmthg = hasAnAimToDoSmthg;
    }

    public void decideToDoSmthg(InterfaceOfPigsAction action) {
        if (action == null) {
            throw new NullPointerException("action can't be null");
        } else {
            if (!isSleeping) {
                hasAnAimToDoSmthg = true;
                action.tellAboutDecision(Pyatachok.this);
                link = action;
            } else {
                System.out.println("'" + getName() + "' спит");
            }
        }
    }

    public void performDecidedAction() {
        if (hasAnAimToDoSmthg && !isSleeping) {
            link.performAction(Pyatachok.this);
            hasAnAimToDoSmthg = false;
        } else if (isSleeping) {
            System.out.println("'" + getName() + "' спит");
        } else {
            System.out.println("У '" + getName() + "' нет цели");
        }
    }

    public void moveTo(Place fPoint) {
        if (fPoint == null) {
            throw new NullPointerException("fPoint can't be null");
        } else {
            if (!isSleeping) {
                if (fPoint.getPlace().isPossibileToGetInside()) {
                    System.out.println("'" + getName() + "' побежал в место '" + fPoint.getPlace().getName() + "'");
                    setPlace(fPoint.getPlace());
                } else {
                    System.out.println("сюда нельзя войти");
                }
            } else {
                System.out.println("'" + getName() + "' спит");
            }
        }
    }

    public void moveTo(Places fPoint) {
        if (fPoint == null) {
            throw new NullPointerException("fPoint can't be null");
        } else {
            if (!isSleeping) {
                if (fPoint.isPossibileToGetInside()) {
                    System.out.println("'" + getName() + "' побежал в место '" + fPoint.getName() + "'");
                    setPlace(fPoint);
                } else {
                    System.out.println("сюда нельзя войти");
                }
            } else {
                System.out.println("'" + getName() + "' спит");
            }
        }
    }

    @Override
    public void thinkAbout(AbstractAnimalHero crewmember) {
        if (crewmember == null) {
            throw new NullPointerException("Another hero can't be null");
        } else {
            if (!isSleeping) {
                int amountOfFlowers = crewmember.getAmountOfFlowers();
                System.out.println("'" + getName() + "' подумал об '" + crewmember.getName() + "' и понял, что у него " + ((amountOfFlowers == 0) ? "не было" : amountOfFlowers) + " цветов");
                crewmember.checkFlowers();
                setFeeling(Feelings.COMPASSION);
                System.out.println("'" + getName() + "' испытывал '" + getFeeling().getNameOfFeeling() + "'");
            } else {
                System.out.println("'" + getName() + "' спит");
            }
        }
    }

    @Override
    public void thinkMore(AbstractAnimalHero crewmember) {
        if (crewmember == null) {
            throw new NullPointerException("Another hero can't be null");
        } else {
            if (!isSleeping) {
                setFeeling(Feelings.BIGGERCOMPASSION);
                System.out.println("И чем больше '" + getName() + "' думал об '" + crewmember.getName() + "', тем '" + getFeeling().getNameOfFeeling() + "' он испытывал");
            } else {
                System.out.println("'" + getName() + "' спит");
            }
        }
    }

    public void repeat(PhraseOfHero phrase) {
        if (phrase == null) {
            throw new NullPointerException("phrase can't be null");
        } else {
            if (!isSleeping && hasAnAimToDoSmthg) {
                String currentPhrase = phrase.getPhrase();
                degreeOfMem++;
                System.out.println("'" + getName() + "' повторял '" + currentPhrase + "'");
                System.out.println("Уровень его запоминания увеличился на 1, теперь он составляет " + degreeOfMem);
            } else if (isSleeping) {
                System.out.println("'" + getName() + "' спит");
            } else {
                System.out.println("у '" + getName() + "' нет цели, ему нечего запоминать");
            }
        }
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) return true;
        if (!(anotherObject instanceof Pyatachok)) return false;
        if (!super.equals(anotherObject)) return false;
        Pyatachok anotherPyatachok = (Pyatachok) anotherObject;
        return isSleeping == anotherPyatachok.isSleeping && hasAnAimToDoSmthg == anotherPyatachok.hasAnAimToDoSmthg && degreeOfMem == anotherPyatachok.degreeOfMem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isSleeping, hasAnAimToDoSmthg, degreeOfMem);
    }

    // статичный enum класс содержащий фразы пятачка
    private enum PhraseOfHero {
        First("Иа, фиалки"), Second("Фиалки, Иа-Иа");

        private final String phrase;

        PhraseOfHero(String phrase) {
            this.phrase = phrase;
        }

        public String getPhrase() {
            return phrase;
        }

        @Override
        public String toString() {
            return getClass().getName() + "[phrase:" + phrase + "]";
        }
    }

    // внутренний класс, описывающий действие пятачка по сбору цветов
    public class CollectFlowersAction implements InterfaceOfPigsAction {
        private final AbstractFlowersKeeper flowersKeeper;
        private final int amount;

        public CollectFlowersAction(AbstractFlowersKeeper flowersKeeper, int amount) {
            if (flowersKeeper == null) throw new NullPointerException("flowersKeeper can't be null");
            this.flowersKeeper = flowersKeeper;
            this.amount = amount;
        }

        @Override
        public void tellAboutDecision(Pyatachok teller) {
            if (flowersKeeper instanceof AbstractAnimalHero) {
                System.out.println("'" + teller.getName() + "' решил сорвать цветы для героя '" + ((AbstractAnimalHero) flowersKeeper).getName() + "' в количестве : " + amount);
            } else {
                System.out.println("'" + teller.getName() + "' решил сорвать цветы в месте '" + ((Place) flowersKeeper).getPlace().getName() + "' в количестве : " + amount);
            }
        }

        @Override
        public void performAction(Pyatachok performer) {
            if (flowersKeeper instanceof AbstractAnimalHero) {
                if (performer.getPlace() != Places.Field) {
                    performer.moveTo(Places.Field);
                    performer.repeat(PhraseOfHero.First);
                    performer.repeat(PhraseOfHero.Second);
                }
            } else {
                if (((Place) flowersKeeper).getPlace().equals(performer.getPlace())) {
                    performer.takeFlowersFrom(flowersKeeper, amount);
                } else {
                    performer.moveTo(((Place) flowersKeeper));
                    performer.takeFlowersFrom(flowersKeeper, amount);
                }
            }
        }

        @Override
        public String toString() {
            return getClass().getName() + "[name: сбор цветов]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CollectFlowersAction)) return false;
            CollectFlowersAction anotherAction = (CollectFlowersAction) o;
            return amount == anotherAction.amount && flowersKeeper.equals(anotherAction.flowersKeeper);
        }

        @Override
        public int hashCode() {
            return Objects.hash(flowersKeeper, amount);
        }
    }

    // внутренний класс, описывающий действие пятачка по передаче цветов
    public class PutFlowersAction implements InterfaceOfPigsAction {
        private final AbstractFlowersKeeper accepter;
        private final int amount;

        public PutFlowersAction(AbstractFlowersKeeper accepter, int amount) {
            if (accepter == null) throw new NullPointerException("accepter can't be null");
            this.accepter = accepter;
            this.amount = amount;
        }

        @Override
        public void tellAboutDecision(Pyatachok teller) {
            if (accepter instanceof AbstractAnimalHero) {
                System.out.println("'" + teller.getName() + "' решил дать цветы '" + ((AbstractAnimalHero) accepter).getName() + "' в количестве : " + amount);
            } else {
                System.out.println("'" + teller.getName() + "' решил положить цветы в место '" + ((Place) accepter).getPlace().getName() + "' в количестве : " + amount);
            }
        }

        @Override
        public void performAction(Pyatachok performer) {
            if (accepter instanceof AbstractAnimalHero) {
                if (((AbstractAnimalHero) accepter).getPlace().equals(performer.getPlace())) {
                    performer.moveFlowersTo(accepter, amount);
                } else {
                    performer.moveTo(((AbstractAnimalHero) accepter).getPlace());
                    performer.moveFlowersTo(accepter, amount);
                }
            } else {
                if (((Place) accepter).getPlace().equals(Places.Vase)) {
                    if (((Place) accepter).getPlace().equals(Places.HomeOfPyatachek)) {
                        performer.moveFlowersTo(accepter, amount);
                    } else {
                        performer.moveTo(Places.HomeOfPyatachek);
                        performer.moveFlowersTo(accepter, amount);
                    }
                } else {
                    if (((Place) accepter).getPlace().equals(performer.getPlace())) {
                        performer.moveFlowersTo(accepter, amount);
                    } else {
                        performer.moveTo(((Place) accepter).getPlace());
                        performer.moveFlowersTo(accepter, amount);
                    }
                }
            }
        }

        @Override
        public String toString() {
            return getClass().getName() + "[name: передача цветов]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PutFlowersAction)) return false;
            PutFlowersAction anotherAction = (PutFlowersAction) o;
            return amount == anotherAction.amount && accepter.equals(anotherAction.accepter);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accepter, amount);
        }
    }
}
