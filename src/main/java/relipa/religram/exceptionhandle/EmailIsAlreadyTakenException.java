package relipa.religram.exceptionhandle;

public class EmailIsAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailIsAlreadyTakenException() {
        super("Email Is Already Taken");
    }

}