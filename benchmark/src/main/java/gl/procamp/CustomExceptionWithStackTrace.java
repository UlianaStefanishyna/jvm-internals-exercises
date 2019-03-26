package gl.procamp;

public class CustomExceptionWithStackTrace extends RuntimeException {
    public CustomExceptionWithStackTrace(String message){
        super(message, null, true, true);
    }
}
