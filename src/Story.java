import exceptions.FeelingNewVerseException;
import heroes.*;
import places.Place;
import places.Places;
import times.EarlyTime;
import times.InterfaceOfTime;


public class Story {

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit("Кролик", Places.HomeOfPyatachek, Feelings.HAPPYNESS);
        Pyatachok pig = new Pyatachok("Пятачёк", Places.HomeOfPyatachek, Feelings.HAPPYNESS);
        Bear puh = new Bear("Пух", Places.HomeOfBear, Feelings.SADNESS);
        Place homeOfP = new Place(0, Places.HomeOfPyatachek);
        Place vase = new Place(0, Places.Vase);
        Place yard = new Place(0, Places.Yard);
        Place field = new Place(100, Places.Field);
        Place homeOfB = new Place(0, Places.HomeOfBear);
        InterfaceOfTime earlyTime = new EarlyTime();

        rabbit.greetSmbd(puh, "за старания");
        rabbit.tellAboutMustAction(rabbit.new visitHero(pig), puh);
        try {
            puh.feelNewVerse();
            puh.tellAboutDecidedAction(puh.new waitForSmbd(pig));
        } catch (FeelingNewVerseException feelingNewVerseException) {
            System.out.println(feelingNewVerseException.getMessage());
            puh.refuseOffer();
        }
        rabbit.goOut(yard);
        pig.getUp(earlyTime);
        pig.decideToDoSmthg(pig.new CollectFlowersAction(field, 5));
        pig.performDecidedAction();
        rabbit.meetAnotherHero(pig);
        pig.decideToDoSmthg(pig.new PutFlowersAction(vase, 5));
        pig.performDecidedAction();

        //Анонимный класс, который используется единожды
        AbstractAnimalHero donkey = new AbstractAnimalHero("Осел Иа", 0, Places.HomeOfDonkey, Feelings.HAPPYNESS) {
        };
        pig.thinkAbout(donkey);
        pig.thinkMore(donkey);
        pig.decideToDoSmthg(pig.new CollectFlowersAction(donkey, 5));
        pig.performDecidedAction();
    }
}
