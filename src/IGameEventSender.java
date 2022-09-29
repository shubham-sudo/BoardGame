
public interface IGameEventSender {
    /**
     * Add a listener to this sender
     * 
     * @param gEventListener event listener for the game
     */
    void addListener(IGameEventListener gEventListener);

    /**
     * Emit/Send an event to listeners to process it.
     * 
     * @param event Game event
     */
    void emit(GameEvent event);
}