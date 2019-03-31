package gl.procamp.task.classloader;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) {
        String replaced = fileName.replace('.', File.separatorChar);
        String name = replaced + ".class";
        System.out.println(name);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                name);
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
