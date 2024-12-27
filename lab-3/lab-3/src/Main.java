import classes.CelestialBody;
import classes.CreatureObj;
import classes.Neznaika;
import classes.Ponchik;
import classes.SpaceBody;
import classes.SpaceBoots;
import enums.LegsPosition;
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

        String skyname = "Небо";
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

        SpaceBoots Boots = new SpaceBoots(checkValue("Космические сапоги"));


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
        persons.add(Mountain.name());
        persons.add(Ponchik.getName());
        persons.add(Neznaika.getName());
        Ponchik.feeling(LegsPosition.UP, persons);
    
        Neznaika.walkedAhead(WalkTypes.AHEAD);
        Ponchik.walkedAhead(WalkTypes.AHEAD);
        
        Neznaika.upsideDown1(UpsidePosition.UP);
          
        Neznaika.imagine(Neznaika, MoonSurface, Sky, Star, Sun, Boots);

        Neznaika.afraid(Neznaika, Boots, World, Moon);

        Neznaika.force(Neznaika, Boots);   
    }
}