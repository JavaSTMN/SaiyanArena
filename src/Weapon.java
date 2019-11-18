public class Weapon extends Minion implements IAttacking {
    private int attack;
    private int durability;

    public Weapon(String name, int manaCost) {
        super(name, manaCost);
    }

    @Override
    protected void die() {
        dieEvent.onDie();
    }

    @Override
    public void attack(ITarget target) {
        target.takeDamage(attack);

        durability -= 1;

        if(durability  <= 0)
            die();
    }
}
