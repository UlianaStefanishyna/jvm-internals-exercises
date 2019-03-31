package gl.procamp.task.proxy;

import gl.procamp.service.TextService;
import gl.procamp.service.impl.TextServiceImpl;

import java.lang.reflect.Proxy;

public class MainProxy {
    public static void main(String[] args) {

        TextService original = new TextServiceImpl();

        EnvVariableProxyReplacer handler = new EnvVariableProxyReplacer(original, System.getProperty("port"));

        TextService f = (TextService) Proxy.newProxyInstance(
                TextService.class.getClassLoader(),
                new Class[]{TextService.class},
                handler);
        String variable = f.variable("server.port = ${port}");
        System.out.println(variable);
    }

}
