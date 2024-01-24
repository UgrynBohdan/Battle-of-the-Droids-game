package Droids.Effects;

public class Effect {
    double damage;
    // тривалість
    public int duration;
    public int timer;

    // ефекти

    public double regeneration;
    public double boost_damage;
    public double shield;

    public Effect(int duration, double boost_damage, double shield, double regeneration) {
        this.duration = duration;
        this.boost_damage = boost_damage;
        this.shield = shield;
        this.regeneration = regeneration;
        this.timer = 1;
    }


}
