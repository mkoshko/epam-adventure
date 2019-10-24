package by.koshko.cyberwikia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper {

    public RequestWrapper(final HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(final String name) {
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(final String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String[] getParameterValues(final String name) {
        String[] values = super.getParameterValues(name);
        if (values == null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    private String cleanXSS(final String value) {
        String encoded = value;
        encoded = encoded.replace("<", "&lt;")
                .replace(">", "&gt;");
        encoded = encoded.replaceAll("\\(", "&#40;")
                .replaceAll("\\)", "&#41;");
        encoded = encoded.replace("'", "&#39;");
        encoded = encoded.replaceAll("eval\\((.*)\\)", "");
        encoded = encoded.replaceAll("[\"\'][\\s]*javascript:(.*)[\"\']",
                "\"\"");
        encoded = encoded.replace("script", "");
        return encoded;
    }
}
