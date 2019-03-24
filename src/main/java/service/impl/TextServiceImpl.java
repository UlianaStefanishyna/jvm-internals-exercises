package service.impl;

import exception.CustomRuntimeException;
import service.TextService;

public class TextServiceImpl implements TextService {

    @Override
    public String staticText() {
        return "HHHHH";
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
