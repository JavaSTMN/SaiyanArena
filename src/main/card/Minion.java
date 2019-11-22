package main.card;

public abstract class Minion extends Card implements IAttacking, ITarget {
    private int attack;
    private int lifePoints;

    private boolean used;

    public Minion(String name, int manaCost) {
        super(name, manaCost);

        used = false;
    }

    public int getAttack() {
        return attack;
    }

    public void addAttack(int amount) {
        attack += amount;
    }

    public void removeAttack(int amount) {
        attack = subtractResetZero(attack, amount);
    }

    public void addLifePoints(int amount) {
        lifePoints += lifePoints;
    }

    public void removeLifePoints(int amount) {
        lifePoints = subtractResetZero(lifePoints, amount);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    private int subtractResetZero(int value, int amount) {
        value -= amount;

        if(value <=0 )
            value = 0;

        return value;
    }

    protected abstract void die();

    @Override
    public void attack(ITarget target) {
        target.takeDamage(attack);
        used = true;
    }

    @Override
    public void takeDamage(int amount) {
        removeLifePoints(amount);

        if(amount <= 0)
            die();
    }

    public void active() {
        used = false;
    }

    public boolean hasBeenUsed() {
        return used;
    }

    public boolean isDead() {
        return lifePoints <= 0;
    }
}
