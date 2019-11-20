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

    public T get(int index) {
        return cards.get(index);
    }

    public T peekFirst() {
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isFull() {
        return cards.size() >= maxElements;
    }

    public boolean isEmpty() {
        return cards.size() <= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CardContainerIterator<T>(this);
    }
}

class CardContainerIterator<T extends Card> implements Iterator<T> {
    private CardContainer<T> container;
    private int position;

    public CardContainerIterator(CardContainer<T> container) {
        this.container = container;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        if(position < container.size())
            return true;

        return false;
    }

    @Override
    public T next() {
        if(position == container.size())
            throw new NoSuchElementException();

        position++;
        return container.get(position - 1);
    }
}
