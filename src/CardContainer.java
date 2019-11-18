import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CardContainer<T extends Card> {
    private ArrayList<T> cards;
    private int maxElements;

    public CardContainer(int maxElements) {
        cards = new ArrayList<T>();
        this.maxElements = maxElements;
    }

    public CardContainer() {
        this(-1);
    }


    public void add(T card) {
        if(maxElements >= 0) {
          if(cards.size() >= maxElements) {
              return;
          }
        }

        cards.add(card);
    }

    public void remove(T card) {
        cards.remove(card);
    }

    public T peekFirst() {
        return cards.remove(0);
    }

    public T peekLast() {
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean isEmpty() {
        return cards.size() <= 0;
    }
}
