package main.card;

public class Weapon extends Card implements IAttacking {
    private int attack;
    private int durability;

    public Weapon(String name, int manaCost) {
        super(name, manaCost);
    }

    @Override
    public void attack(ITarget target) {
        target.takeDamage(attack);
        durability -= 1;
    }

    public void setAttack(int amount) {
        this.attack = amount;
    }

    public int getAttack() {
        return attack;
    }

    public void setDurability(int amount) {
        this.durability = amount;
    }

    public int getDurability() {
        return durability;
    }
}
