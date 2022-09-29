import java.util.*;

public class PublishGameEvent implements IGameEventSender {

    private final List<IGameEventListener> eventListeners;

    /**
     * Create new game event publisher and initialize listeners list.
     */
    public PublishGameEvent() {
        this.eventListeners = new ArrayList<>();
    }

    /**
     * Add new listener or say like listener is subscribing to this publisher
     * 
     * @see IGameEventSender#addListener(IGameEventListener)
     */
    public void addListener(IGameEventListener gEventListener) {
        this.eventListeners.add(gEventListener);
    }

    /**
     * Notify all the listener subscribed for this publisher
     * 
     * @see IGameEventSender#emit(GameEvent)
     */
    @Override
    public void emit(GameEvent event) {
        for (IGameEventListener gEventListener : eventListeners) {
            gEventListener.listen(event);
        }
    }
}
