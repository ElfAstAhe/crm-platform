package org.hsh.crm.ms.audit.ep.filter;

import common.ep.filter.BaseEndToEndRequestIdFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", description = "EndToEndRequestFilter")
public class EndToEndRequestIdFilter extends BaseEndToEndRequestIdFilter {
}
