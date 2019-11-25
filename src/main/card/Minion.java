package main.card;

public class Minion extends Card implements IAttacking, ITarget {
    private int attack;
    private int lifePoints;

    private boolean sleep;

    public Minion(String name, int manaCost, int attack, int lifePoints) {
        super(name, manaCost);

        this.attack = attack;
        this.lifePoints = lifePoints;
        sleep = true;
    }

    public int getAttack() {
        return attack;
    }

    public void addAttack(int amount) {
        attack += amount;
        listener.refresh();
    }

    public void removeAttack(int amount) {
        attack = subtractResetZero(attack, amount);
        listener.refresh();
    }

    public void heal(int amount) {
        addLifePoints(amount);
        listener.refresh();
    }

    public void addLifePoints(int amount) {
        lifePoints += lifePoints;
    }

    public void removeLifePoints(int amount) {
        lifePoints = subtractResetZero(lifePoints, amount);
        listener.refresh();
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

    @Override
    public void attack(ITarget target) {
        target.takeDamage(attack);
        sleep = true;
    }

    @Override
    public void takeDamage(int amount) {
        removeLifePoints(amount);
        listener.refresh();
    }

    public void active() {
        sleep = false;
        listener.refresh();
    }

    public boolean canAttack() {
        return sleep;
    }

    public boolean isDead() {
        return lifePoints <= 0;
    }
}
