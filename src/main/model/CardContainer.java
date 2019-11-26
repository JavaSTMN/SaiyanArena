package main.model;

import main.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CardContainer<T extends Card> implements Iterable<T> {
    private ArrayList<T> cards;
    private int maxElements;

    public CardContainer(int maxElements) {
        cards = new ArrayList<>();
        this.maxElements = maxElements;
    }

    public <T extends Card> CardContainer<T> Clone() {
        return new CardContainer(this);
    }

    private CardContainer(CardContainer<T> source) {
        this(source.maxElements);

        for(T card : source) {
            cards.add(card);
        }
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

    public T get(int index) throws IndexOutOfBoundsException {
        return cards.get(index);
    }

    public T peekFirst() throws IndexOutOfBoundsException {
        try{
            return cards.remove(0);
        }
        catch(IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isFull() {
        if(maxElements < 0)
            return false;

        return cards.size() >= maxElements;
    }

    public boolean isEmpty() {
        return cards.size() <= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CardContainerIterator<>(this);
    }
}

class CardContainerIterator<T extends Card> implements Iterator<T> {
    private CardContainer<T> container;
    private int position;

    CardContainerIterator(CardContainer<T> container) {
        this.container = container;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < container.size();
    }

    @Override
    public T next() throws NoSuchElementException {
        if(position == container.size())
            throw new NoSuchElementException();

        position++;
        return container.get(position - 1);
    }
}
