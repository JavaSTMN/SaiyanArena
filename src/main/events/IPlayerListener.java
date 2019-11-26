package main.events;

public interface IPlayerListener {
    void onDrawCard();
    void onPlayCard();
    void refresh();
}
