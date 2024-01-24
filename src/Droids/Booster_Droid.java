package Droids;

import Droids.Effects.Attack;
import Droids.Effects.Effect;

public class Booster_Droid extends Droid {
    public Booster_Droid() {
        super("Booster Droid", 800, 150, 2);
    }

    @Override
    public Attack Support_ability() {
        return new Attack(0, false, new Effect(2, 0.25, 0, 0));
    }
    // збільшує урон зразу потім ще раз (тобто на 2 раунди)

    @Override
    public Attack Special_ability() {
        if (timer >= cool_down) {
            timer = 0;
            return new Attack(0, true, new Effect(1, 0.50, 0, 0));
        } else
            return null;
    }
    // збільшує урон зразу лише раз
}
