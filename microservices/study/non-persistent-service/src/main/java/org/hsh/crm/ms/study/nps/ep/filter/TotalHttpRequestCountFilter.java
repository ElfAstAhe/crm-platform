package org.hsh.crm.ms.study.nps.ep.filter;

import org.hsh.ms.common.ep.filter.BaseTotalHttpRequestCountFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", filterName = "TotalHttpRequestCountFilter")
public class TotalHttpRequestCountFilter extends BaseTotalHttpRequestCountFilter {
}