package by.koshko.cyberwikia.bean;

import java.io.InputStream;

public class RawData {
    private static String rootPath;
    private InputStream in;
    private String contentType;

    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(final String newRootPath) {
        rootPath = newRootPath;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(final InputStream inputStream) {
        in = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String type) {
        contentType = type;
    }
}
