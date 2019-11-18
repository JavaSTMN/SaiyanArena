public abstract class Minion extends Card implements IAttacking, ITarget {
    private int attack;
    private int lifePoints;
    protected IMinionDieEvent dieEvent;


    public Minion(String name, int manaCost) {
        super(name, manaCost);
    }

    public void addDieEvent(IMinionDieEvent dieEvent){
        this.dieEvent = dieEvent;
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
    }

    @Override
    public void takeDamage(int amount) {
        removeLifePoints(amount);

        if(amount <= 0)
            die();
    }
}
