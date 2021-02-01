package ep.filter;

import common.ep.filter.BaseTotalHttpRequestCountFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", filterName = "TotalHttpRequestCountFilter")
public class TotalHttpRequestCountFilter extends BaseTotalHttpRequestCountFilter {
}
