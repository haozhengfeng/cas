package org.haozf.common.model;

import org.jasig.cas.authentication.principal.SimplePrincipal;

public class SessionContext {
	public static ThreadLocal<SimplePrincipal> principal = new ThreadLocal<SimplePrincipal>();
}
