public class Hero implements ITarget {
    private final int MAX_LIFE_POINTS = 30;
    private int lifePoints;

    private HeroClass heroClass;

    public Hero(HeroClass heroClass) {
        this.heroClass = heroClass;

        lifePoints = MAX_LIFE_POINTS;
    }

    @Override
    public void takeDamage(int amount) {
        subtractLifePoints(amount);
    }

    public int getLifePoints()  {
        return lifePoints;
    }

    private void subtractLifePoints(int amount) {
        lifePoints = subtractResetZero(lifePoints, amount);
    }

    private int subtractResetZero(int value, int amount) {
        value -= amount;

        if(value <=0 )
            value = 0;

        return value;
    }
}
