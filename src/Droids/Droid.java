package Droids;

import Droids.Effects.Attack;
import Droids.Effects.Effect;

import java.util.ArrayList;

public abstract class Droid {
    // стандартні параметри
    String name;
    double max_health;
    double current_health;
    double base_damage, current_damage;
    int cool_down, timer; // відкат особоливої здібності, і таймер

    // параметри ефектів
    double shield;

    ArrayList<Effect> effects = new ArrayList<>(); // масив ефектів

    public Droid(String name, double max_health, double base_damage, int cool_down) {
        // записуємо стандартні параметри
        this.name = name;
        this.max_health = max_health;
        this.current_health = this.max_health;
        this.base_damage = base_damage;
        this.current_damage = base_damage;
        this.cool_down = cool_down;
        timer = cool_down;
        // записуємо початкові значення ефектів
        shield = 0;
    }

    // перевірка чи живий
    public final boolean Is_alive() {
        return current_health > 0;
    }
    // перевірка, щоб не виходити за рамки
    public final void Change_health(double health) {
        current_health += health;

        if (current_health > max_health)
            current_health = max_health;
        else if (current_health < 0)
            current_health = 0;
    }
    // ефекти
    public final void Add_effect(Effect effect) {
        if (effect != null) {
            effects.add(new Effect(effect.duration, effect.boost_damage, effect.shield, effect.regeneration));
        }
    }

    public final void Turn() {
        this.timer++;
        if (timer > cool_down)
            timer = cool_down;

        for (int i = 0; i < effects.size(); i++) {
            if (effects.get(i).duration < effects.get(i).timer) {
                effects.remove(effects.get(i));
            } else {
                effects.get(i).timer++;
                Change_health(effects.get(i).regeneration);
            }
        }
    }

    public final void Step() { // крок
        shield = 0;
        current_damage = base_damage;

        for (int i = 0; i < effects.size(); i++)
            if (effects.get(i).duration < effects.get(i).timer)
                effects.remove(effects.get(i));

        for (Effect effect : effects) {
            shield += effect.shield;
            current_damage += base_damage * effect.boost_damage;
        }
    }

    // Здібності
    public final Attack Attack() { // звичайна атака
        return new Attack(current_damage, false, null);
    }

    public abstract Attack Support_ability(); // здібність підтримки

    public abstract Attack Special_ability(); // спеціальна здатність


    // отримання дії
    public final void Reaction(Attack attack) {
        if (attack.damage > 0)
            Change_health(-attack.damage * (1 - shield));
        else
            Change_health(-attack.damage);
        Add_effect(attack.effect);
    }


    // вивід інформації///////////////////////////
    public final String get_current_health() {
        return Double.toString(current_health) + '/' + Double.toString(max_health);
    }
    public final String get_damage() {
        return Double.toString(current_damage);
    }
    public final String get_name() {
        return name;
    }
    public final String get_shield() {
        return Double.toString(shield);
    }
    public final String get_cool_down() {
        return Integer.toString(timer) + '/' + Integer.toString(cool_down);
    }
}
