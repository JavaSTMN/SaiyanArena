package test;

import main.model.ManaReserve;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManaReserveTest {

    private ManaReserve CreateFilledReserve(int startMana) {
        ManaReserve reserve = new ManaReserve(startMana);
        reserve.setActualAvailable(startMana);

        return reserve;
    }

    @Test
    void maxManaFiveHasAvailableForAmountOfFour() {
        ManaReserve reserve = CreateFilledReserve(5);

        int amount = 4;

        boolean excepted = true;

        assertEquals(excepted, reserve.hasAvailable(amount));
    }

    @Test
    void maxManaFiveHasAvailableForAmountOfSix() {
        ManaReserve reserve = CreateFilledReserve(5);

        int amount = 6;

        boolean excepted = false;

        assertEquals(excepted, reserve.hasAvailable(amount));
    }

    @Test
    void isFull() {
        ManaReserve reserve = CreateFilledReserve(10);

        boolean excepted = true;

        boolean actual = reserve.isFull();

        assertEquals(excepted, actual);
    }

    @Test
    void useWithManaMaxTwo() {
        ManaReserve reserve = CreateFilledReserve(2);

        int amountForUse = 1;

        int excepted = 1;

        reserve.use(amountForUse);
        int actual = reserve.getActualAvailable();

        assertEquals(excepted, actual);
    }

    @Test
    void fillWithManaMaxZero() {
        ManaReserve reserve = new ManaReserve();
        int excepted = 1;

        reserve.fill();
        int actualMax = reserve.getActualMax();

        assertEquals(excepted, actualMax);
    }

    @Test
    void refullWithManaMaxFive() {
        ManaReserve reserve = new ManaReserve(5);
        int excepted = 5;

        reserve.refull();
        int actual = reserve.getActualAvailable();

        assertEquals(excepted, actual);
    }
}