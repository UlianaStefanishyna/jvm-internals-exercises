package task.classloader;

import service.TextService;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {

        while (true) {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class aClass = customClassLoader.findClass("service.impl.TextServiceImpl");
            TextService o = (TextService) aClass.getDeclaredConstructor().newInstance();
            String result = (String) aClass.getMethod("staticText").invoke(o);
            System.out.println(result);
            Thread.sleep(5000);
        }
    }
}