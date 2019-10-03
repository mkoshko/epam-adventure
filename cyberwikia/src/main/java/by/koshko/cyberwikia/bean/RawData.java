package by.koshko.cyberwikia.bean;

import java.io.InputStream;

public class RawData {

    private InputStream in;
    private String contentType;
    private String contextPath;

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

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(final String contextPath) {
        this.contextPath = contextPath;
    }
}
