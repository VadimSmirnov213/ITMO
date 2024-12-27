import classes.BodyPart;
import classes.CelestialBody;
import classes.CreatureObj;
import classes.Neznaika;
import classes.Ponchik;
import classes.SpaceBody;
import classes.SpaceBoots;
import enums.BodyType;
import enums.Clarification;
import enums.ETC_enum;
import enums.Extra;
import enums.ExwNorm;
import enums.LegsPosition;
import enums.Light;
import enums.UpsidePosition;
import enums.WalkTypes;
import enums.WeightlessnessTime;
import exceptions.InvalidArgumentException;
import exceptions.InvalidValueException;
import classes.Location;

import java.util.ArrayList;

public class Main {
    private static String checkValue(String value) {
        if (value == null || value.isEmpty()) { // Проверка на null или пустое значение
            throw new InvalidValueException();
        }
        return value;
    }
    public static void main(String[] args) {

        String neznaikaName = "Незнайка";
        String ponchikName = "Пончик";

        if (neznaikaName == null || neznaikaName.isEmpty()) { // Проверка на null или пустое значение
            throw new InvalidValueException();
        }
        Neznaika Neznaika = new Neznaika(neznaikaName); 

        if (ponchikName == null || ponchikName.isEmpty()) { 
            throw new InvalidValueException();
        }
        Ponchik Ponchik = new Ponchik(ponchikName);

        
        CelestialBody Moon = null;
        CelestialBody Earth = null;
        CelestialBody Sun = null;

        try {
            Moon = new CelestialBody("Луна", 1.62);
            Earth = new CelestialBody("Земля", 9.81);
            Sun = new CelestialBody("Солнце", 274);
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
            return; 
        }

        String skyname = "Земля";
        String starname = "Небо";

        if (skyname == null || skyname.isEmpty()) { // Проверка на null или пустое значение
            throw new InvalidValueException();
        }
        SpaceBody Sky = new SpaceBody(skyname); 

        if (starname == null || starname.isEmpty()) { // Проверка на null или пустое значение
            throw new InvalidValueException();
        }
        SpaceBody Star = new SpaceBody(starname);



        CreatureObj Grasshopper = new CreatureObj(checkValue("Кузнечик"));
        CreatureObj Flea = new CreatureObj(checkValue("Блоха"));

        Location MoonSurface = new Location(checkValue("Лунная поверхность"));
        Location World = new Location(checkValue("Мировое пространство"));
        Location Mountain = new Location(checkValue("Горы"));
        Location Mountain_one = new Location(checkValue("Гора"));
        Location Moon_road = new Location(checkValue("лунная дорожка"));
        Location hillock = new Location(checkValue("пригорок"));

        SpaceBoots Boots = new SpaceBoots(checkValue("Космические сапоги"));

        BodyPart hand = new BodyPart("рука");
        BodyPart head = new BodyPart("голова");
        BodyPart blood = new BodyPart("кровь");
        BodyPart org = new BodyPart("организм");
        BodyPart legs = new BodyPart("ноги");
        BodyPart vessel = new BodyPart("кровеносные сосуды");
        BodyPart brain = new BodyPart("мозг");

        /////////////////////////////////////////////////////////////////////////////////////////
        
        Mountain.mountain_up(Mountain, Mountain_one);
        Moon_road.road(hillock);
        Moon_road.light(Light.LG);
        System.out.println("Будто кто-то нарочно посыпал в " + Moon_road + " каменистую почву " + Moon + " песком или медом");
        Neznaika.steps(Moon_road);
        Ponchik.opinion();
        Ponchik.threw_up_hands();
        Ponchik.went_obediently(Neznaika);

        /////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Некоторые воображают, что как только им удасться попасть на " + Moon + ", они сейчас же примутся прыгать по её поверхности словно " + Grasshopper);
        Moon.exactcomparison(Moon, Earth);

        Neznaika.avoid();
        Ponchik.avoid();

        Moon.comparison(Earth, Neznaika, Ponchik);

        Neznaika.dontfeel();
        Ponchik.dontfeel();

        Neznaika.weightlessness(WeightlessnessTime.LONG);
        Ponchik.weightlessness(WeightlessnessTime.LONG);

        Neznaika.forget();
        Ponchik.forget();

        Neznaika.getweight(Moon, Earth);
        Ponchik.getweight(Moon, Earth);

        Neznaika.jump(Moon, Grasshopper, Flea);
        Ponchik.jump(Moon, Grasshopper, Flea);

        ArrayList<String> persons = new ArrayList<>();
        persons.add(Moon.getName());
        persons.add(Mountain.getName());
        persons.add(Ponchik.getName());
        persons.add(Neznaika.getName());

        Ponchik.feeling(LegsPosition.UP, persons);
    
        Neznaika.walkedAhead(WalkTypes.AHEAD);
        Ponchik.walkedAhead(WalkTypes.AHEAD);
        
        Neznaika.upsideDown1(UpsidePosition.UP);
          
        Neznaika.imagine(Neznaika, MoonSurface, Sky, Star, Sun, Boots);

        Neznaika.afraid(Neznaika, Boots, World, Moon);

        Neznaika.force(Neznaika, Boots);   

        ///////////////////////////////////////////////////////////
        
        
        System.out.println("Такие " + ExwNorm.NOTNORM + " ощущения объяснялись так, что благодаря " + ETC_enum.LESSN + " силы тяжести на " + 
        Moon + " " + ETC_enum.LESS + " количество " + blood + " в " + org + " притягивалось к" + BodyType.DOWN + "части тела, то есть к " +
        legs);
        System.out.println("Оставшееся в " + BodyType.UP + " части тела " + Extra.EXTRA + " количество " +
        blood + " оказывало на " + vessel + " которые находились в " +
        brain + ETC_enum.MORE + " давление, то есть такое давление, которое бывает у нас, когда нам случается повиснуть вниз " +
        head);
        Ponchik.feeling_over();
        Ponchik.feeling_about_all(UpsidePosition.UP);
        Ponchik.afraid_about_state(Clarification.VERY);
        Ponchik.understood(UpsidePosition.UP, UpsidePosition.DOWN, hand, head);
        Neznaika.justice();
        Neznaika.strong();
        Neznaika.thinner_than(Ponchik);
    }
}