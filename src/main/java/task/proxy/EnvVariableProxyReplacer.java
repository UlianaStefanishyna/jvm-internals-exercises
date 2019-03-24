package task.proxy;

import service.TextService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EnvVariableProxyReplacer implements InvocationHandler {

    private final TextService textService;
    private final String port;

    public EnvVariableProxyReplacer(TextService textService, String port) {
        this.textService = textService;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args.length > 0 && args[0] instanceof String && args[0].equals("server.port = ${port}")){
            String arg = (String) args[0];
            args[0] = arg.replace("${port}", this.port);
        }
        String invoke = (String) method.invoke(textService, args);
        return invoke;
    }
}
