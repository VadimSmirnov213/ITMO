import pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Victini("", 1);
        Pokemon p2 = new Bergmite("", 1);
        Pokemon p3 = new Avalugg("", 1);
        Pokemon p4 = new Igglybuff("", 1);
        Pokemon p5 = new Jigglypuff("", 1);
        Pokemon p6 = new Wigglytuff("", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();

    }
}