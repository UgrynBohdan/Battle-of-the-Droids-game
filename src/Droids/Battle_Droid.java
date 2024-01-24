package Droids;

import Droids.Effects.Attack;
import Droids.Effects.Effect;

public class Battle_Droid extends Droid {

    public Battle_Droid() {
        super("Battle Droid", 1000, 200, 3);
    }

    @Override
    public Attack Support_ability() { // Накладає щит на союзників
        return new Attack(0, true, new Effect(2, 0, 0.25, 0));
    }
    // дає щит на 2 атаки суперника

    @Override
    public Attack Special_ability() { // Супер удар
        if (timer >= cool_down) {
            timer = 0;
            return new Attack(current_damage * 3, false, null);
        } else
            return null;
    }


}
