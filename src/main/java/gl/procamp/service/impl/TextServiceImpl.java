package gl.procamp.service.impl;

import gl.procamp.exception.CustomRuntimeException;
import gl.procamp.service.TextService;

public class TextServiceImpl implements TextService {

    @Override
    public String staticText() {
        return "NEW";
    }

    @Override
    public String variable(String variable) {
        return variable;
    }

    @Override
    public String exception(String text) throws RuntimeException {
        throw new CustomRuntimeException(text);
    }
}
