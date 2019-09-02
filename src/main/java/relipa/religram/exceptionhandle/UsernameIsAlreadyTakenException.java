package relipa.religram.exceptionhandle;

public class UsernameIsAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameIsAlreadyTakenException() {
        super("Username is already taken");
    }

}