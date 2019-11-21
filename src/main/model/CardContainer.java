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


    public void add(T card) throws IndexOutOfBoundsException {
        if(maxElements >= 0) {
          if(cards.size() >= maxElements) {
              return;
          }
        }

        try {
            cards.add(card);
        }
        catch(IndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public void remove(T card) {
        cards.remove(card);
    }

    public T get(int index) throws IndexOutOfBoundsException {
        try {
            return cards.get(index);
        }
        catch(IndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    public T peekFirst() throws IndexOutOfBoundsException {
        try {
            return cards.remove(0);
        }
        catch(IndexOutOfBoundsException ex)  {
            throw ex;
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
    public T next() throws NoSuchElementException {
        if(position == container.size())
            throw new NoSuchElementException();

        position++;
        return container.get(position - 1);
    }
}
