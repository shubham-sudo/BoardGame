public class NoSuchPlayingPieceException extends Exception {
    /**
     * Create NoSuchPlayingPieceException object to throw
     * 
     * @param errorMessage message for the error
     */
    public NoSuchPlayingPieceException(String errorMessage) {
        super(errorMessage);
    }
}
