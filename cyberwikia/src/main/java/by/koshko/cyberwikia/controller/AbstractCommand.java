package by.koshko.cyberwikia.controller;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.bean.Role;
import by.koshko.cyberwikia.bean.ServiceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

public abstract class AbstractCommand {

    private final Logger logger = LogManager.getLogger(getClass());
    private Set<Role> roles = new HashSet<>();

    public Logger getLogger() {
        return logger;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> newRoles) {
        roles = newRoles;
    }

    public abstract Forward execute(HttpServletRequest request,
                                    HttpServletResponse response);

    protected void setErrors(final HttpSession session,
                             final ServiceResponse response) {
        session.setAttribute("errors", response.errorList());
    }

    protected void setScript(final HttpServletRequest request, final String scriptSrc) {
        List<String> scripts = (List<String>) request.getAttribute("scripts");
        if (scripts != null) {
            scripts.add(scriptSrc);
        } else {
            List<String> newScripts = new ArrayList<>();
            newScripts.add(scriptSrc);
            request.setAttribute("scripts", newScripts);
        }
    }

    protected RawData fillRawData(final Part part) throws IOException {
        if (part != null && part.getSize() != 0) {
            logger.debug("Part size: {}", part.getSize());
            RawData rawData = new RawData();
            rawData.setContentType(part.getContentType());
            rawData.setIn(part.getInputStream());
            return rawData;
        } else {
            return null;
        }
    }

    protected Forward sendBack(final HttpServletRequest request) {
        String from = request.getParameter("from");
        return new Forward(Objects.requireNonNullElse(from, "index.html"), true);
    }

    protected Forward sendError(final int error) {
        Forward forward = new Forward();
        forward.setError(true);
        forward.getAttributes().put("error", error);
        return forward;
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
