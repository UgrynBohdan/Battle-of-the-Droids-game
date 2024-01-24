import Droids.*;
import Droids.Effects.Attack;

import java.util.Scanner;

public class Player {
    Droid[] droids = new Droid[3];

    public Player(String[] numbers) {
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(numbers[i]);
            switch (n) {
                case 1 -> droids[i] = new Battle_Droid();
                case 2 -> droids[i] = new Medic_Droid();
                case 3 -> droids[i] = new Booster_Droid();
                case 4 -> droids[i] = new Toxic_Droid();
            }
        }
    }

    public boolean AllDroidsDead() {
        for (Droid droid : droids)
            if (droid.Is_alive())
                return false;
        return true;
    }

    public boolean DroidAlive(int i) {
        return droids[i].Is_alive();
    }

    //////////////////////////////
    public void Step() { // виконує крок для всіх дроїдів
        for (Droid droid : droids)
            if (droid.Is_alive())
                droid.Step();
    }
    public void Turn() {
        for (Droid droid : droids)
            if (droid.Is_alive())
                droid.Turn();
    }
    public Attack Play(int num, int step) { // гравець ходить
        if (!droids[num].Is_alive())
            return null;
        return switch (step) {
            case 1 -> droids[num].Attack();
            case 2 -> droids[num].Support_ability();
            case 3 -> droids[num].Special_ability();
            default -> null;
        };

    }

    public void Receive(int target, Attack attack) { // отримати шкоду
        if (attack.AoE) {
            for (Droid droid : droids)
                if (droid.Is_alive())
                    droid.Reaction(attack);
        }else
            if (droids[target].Is_alive())
                droids[target].Reaction(attack);
    }


    // Виведення інформації/////////////////////////
    public String get_name(int i) {
        return droids[i].get_name();
    }
    public String get_droid_health(int i) {
        return droids[i].get_current_health();
    }
    public String get_droid_damage(int i) {
        return droids[i].get_damage();
    }
    public String get_droid_shield(int i) {
        return droids[i].get_shield();
    }
    public String get_droid_cool_down(int i) {
        return droids[i].get_cool_down();
    }

}





