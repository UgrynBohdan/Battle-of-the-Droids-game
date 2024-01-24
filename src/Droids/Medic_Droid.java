package Droids;

import Droids.Effects.Attack;
import Droids.Effects.Effect;

public class Medic_Droid extends Droid {
    public Medic_Droid() {
        super("Medic Droid", 500, 100, 2);
    }


    @Override
    public Attack Support_ability() {
        return new Attack(-200, false, null);
    }
    // хілить зразу 1 раз

    @Override
    public Attack Special_ability() {
        if (timer >= cool_down) {
            timer = 0;
            return new Attack(-100, true, new Effect(2, 0, 0, 100));
        } else
            return null; // 2 - 3; 1 - 2; 0 - 1
    }
    // хілить зразу і ще 2 раунди
}
