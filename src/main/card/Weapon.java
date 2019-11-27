package main.card;

public class Weapon extends Card implements IAttacking {
    private int attack;
    private int durability;
    private boolean active;

    public Weapon(String name, int manaCost) {
        super(name, manaCost);
        active = false;
    }

    @Override
    public void attack(ITarget target) {
        target.takeDamage(attack);
        durability -= 1;
        active = false;
    }

    @Override
    public boolean canAttack() {
        return active;
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

    public void active() {
        active = true;
    }
}
