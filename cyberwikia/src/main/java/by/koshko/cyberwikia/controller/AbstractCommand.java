package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.Role;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractCommand {
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> newRoles) {
        roles = newRoles;
    }

    abstract Forward execute(HttpServletRequest request, HttpServletResponse response);

    protected Forward sendError(final int error) {
        Forward forward = new Forward();
        forward.setError(true);
        forward.getAttributes().put("error", error);
        return forward;
    }

    protected Forward tryToReturnLastPage(final HttpServletRequest request) {
        Optional<Cookie> optCookie = Stream.of(request.getCookies())
                .filter(c -> c.getName().equals("page"))
                .findFirst();
        return optCookie.map(cookie -> new Forward(cookie.getValue()))
                .orElseGet(() -> new Forward("index.html"));
    }

    public static class Forward {
        private String url;
        private Map<String, Object> attributes = new HashMap<>();
        private boolean isError;
        private boolean isRedirect;
        public Forward() {
        }

        public Forward(final boolean error) {
            isError = error;
        }

        public Forward(final String newUrl) {
            url = newUrl;
        }

        public Forward(final String newUrl, final boolean redirect) {
            url = newUrl;
            isRedirect = redirect;
        }

        public boolean isError() {
            return isError;
        }

        public void setError(final boolean error) {
            isError = error;
        }

        public boolean isRedirect() {
            return isRedirect;
        }

        public void setRedirect(final boolean redirect) {
            isRedirect = redirect;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String newUrl) {
            this.url = newUrl;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(final Map<String, Object> newAttributes) {
            this.attributes = newAttributes;
        }
    }

}
