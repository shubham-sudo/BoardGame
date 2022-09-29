public class ListenGameEvent implements IGameEventListener {

    /**
     * Listen the event published in any game and store it somewhere,
     * currently its showing into the console.
     * 
     * @param event The GameEvent object.
     */
    @Override
    public void listen(GameEvent event) {
        System.out.println("---");
        System.out.println("New Event: " + event);
        System.out.println("---");
    }
}
