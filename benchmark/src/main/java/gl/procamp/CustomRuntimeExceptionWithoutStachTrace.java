package gl.procamp;

public class CustomRuntimeExceptionWithoutStachTrace extends RuntimeException {
    public CustomRuntimeExceptionWithoutStachTrace(String message) {
        super(message, null, false, false);
    }
}
