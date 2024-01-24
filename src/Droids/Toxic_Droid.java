package Droids;

import Droids.Effects.Attack;
import Droids.Effects.Effect;

public class Toxic_Droid extends Droid{

    public Toxic_Droid() {
        super("Toxic Droid", 700, 150, 2);
    }

    @Override
    public Attack Support_ability() {
        return new Attack(-100, true, null);
    }

    @Override
    public Attack Special_ability() {
        if (timer >= cool_down) {
            timer = 0;
            return new Attack(100, true, new Effect(4, 0, 0, -50));
        } else
            return null;
    }
}
