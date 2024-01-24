package Droids.Effects;

public class Attack {
    public double damage;
    public boolean AoE;
    public Effect effect;

    public Attack(double damage, boolean AoE, Effect effect) {
        this.damage = damage;
        this.AoE = AoE;
        this.effect = effect;
    }
}
