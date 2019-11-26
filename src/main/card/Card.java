package main.card;

import main.effects.IEffect;
import main.effects.IUndoable;
import main.events.ICardListener;

import java.util.ArrayList;

public class Card {
    protected int manaCost;
    protected String name;
    protected String description;
    protected ArrayList<IEffect> effects;
    protected ArrayList<IUndoable> undoables;

    protected ICardListener listener;

    public Card(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;

        effects = new ArrayList<IEffect>();
        undoables = new ArrayList<IUndoable>();

        description = "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void addEffect(IEffect effect) {
        effects.add(effect);
    }

    public void addUndoableEffect(IUndoable undoableEffect) {
        undoables.add(undoableEffect);
    }

    public void setListener(ICardListener listener) {
        this.listener = listener;
    }

    public void executeEffects() {
        for(IEffect effect : effects) {
            effect.execute();
        }
    }
}