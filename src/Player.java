public class Player {
    private CardContainer<Card> hand;
    private CardContainer<Card> deck;
    private CardContainer<Minion> board;
    private CardContainer<Card> graveyard;

    private Hero hero;
    private ManaReserve reserve;
    private PlaySide side;

    public Player(Hero hero, CardContainer<Card> hand, CardContainer<Card> deck) {
        this.hero = hero;
        this.hand = hand;
        this.deck = deck;

        board = new CardContainer<Minion>();
        graveyard = new CardContainer<Card>();
    }

    public void drawNextCard() {
        if(!deck.isEmpty()) {
            Card card = deck.peekFirst();
            hand.add(card);
        }
    }

    public void placeInHand(Card card) {
        hand.add(card);
    }

    public void setSide(PlaySide side) {
        this.side =  side;
    }

    public PlaySide getSide() {
        return side;
    }

    public void updateSide() {
        if(side == PlaySide.WAITING_PLAYER) {
            side = PlaySide.ACTIVE_PLAYER;
        }
        else
        {
            side = PlaySide.WAITING_PLAYER;
        }
    }

    public boolean isDead() {
        return hero.getLifePoints() <= 0;
    }

    public ManaReserve getManaReserve() {
        return reserve;
    }

    public CardContainer<Card> getHand() {
        return hand;
    }

    public CardContainer<Card> getDeck() {
        return deck;
    }

    public CardContainer<Minion> getBoard() {
        return board;
    }
}
