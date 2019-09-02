package relipa.religram.exceptionhandle;

public class ApiMissingException extends RuntimeException {

    public ApiMissingException() {
        super("API key is missing or invalid");
    }
}
