package common.ep.filter;

// https://stackoverflow.com/questions/2811769/adding-an-http-header-to-the-request-in-a-servlet-filter
// http://sandeepmore.com/blog/2010/06/12/modifying-http-headers-using-java/
// http://bijubnair.blogspot.de/2008/12/adding-header-information-to-existing.html

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * allow adding additional header entries to a request
 *
 * @author wf
 *
 */
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    /**
     * construct a wrapper for this request
     *
     * @param request http servlet request
     */
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private final Map<String, String> headerMap = new HashMap<>();

    /**
     * add a header with given name and value
     *
     * @param name header name
     * @param value header value
     */
    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    /**
     * get the Header names
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headerMap.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }
}