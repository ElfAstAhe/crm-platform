package org.hsh.crm.ms.infra.ep.filter;

import org.hsh.ms.common.ep.filter.BaseTotalHttpRequestCountFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", filterName = "TotalHttpRequestCountFilter")
public class TotalHttpRequestCountFilter extends BaseTotalHttpRequestCountFilter {
}
