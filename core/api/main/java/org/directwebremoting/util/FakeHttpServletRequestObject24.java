package org.directwebremoting.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ReadListener;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Backing class for Servlet 2.4 fake requests.
 */
class FakeHttpServletRequestObject24 // Note: does not implement interface as we are mapping versions in runtime
{
    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getAuthType()
     */
    public String getAuthType()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getCookies()
     */
    public Cookie[] getCookies()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
     */
    public long getDateHeader(String name)
    {
        return -1;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getHeader(java.lang.String)
     */
    public String getHeader(String name)
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
     */
    public Enumeration<String> getHeaders(String name)
    {
        return Collections.enumeration(Collections.<String>emptySet());
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getHeaderNames()
     */
    public Enumeration<String> getHeaderNames()
    {
        return Collections.enumeration(Collections.<String>emptySet());
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
     */
    public int getIntHeader(String name)
    {
        return -1;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getMethod()
     */
    public String getMethod()
    {
        return method;
    }

    /**
     * @see #getMethod()
     */
    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * @see #getMethod()
     */
    private String method = "GET";

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getPathInfo()
     */
    public String getPathInfo()
    {
        return pathInfo;
    }

    /**
     * @see #getPathInfo()
     */
    public void setPathInfo(String pathInfo)
    {
        this.pathInfo = pathInfo;
    }

    /**
     * @see #getPathInfo()
     */
    private String pathInfo;

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getPathTranslated()
     */
    public String getPathTranslated()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getContextPath()
     */
    public String getContextPath()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getContextPath() to remain plausible.");
        return "";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getQueryString()
     */
    public String getQueryString()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getRemoteUser()
     */
    public String getRemoteUser()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
     */
    public boolean isUserInRole(String role)
    {
        return roles.contains(role);
    }

    /**
     * @see #isUserInRole(String)
     */
    public void addUserRole(String role)
    {
        roles.add(role);
    }

    /**
     * @see #isUserInRole(String)
     */
    private final Set<String> roles = new HashSet<String>();

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getUserPrincipal()
     */
    public Principal getUserPrincipal()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getRequestedSessionId()
     */
    public String getRequestedSessionId()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getRequestURI()
     */
    public String getRequestURI()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getRequestURI() to remain plausible.");
        return "/";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getRequestURL()
     */
    public StringBuffer getRequestURL()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getRequestURL() to remain plausible.");
        return new StringBuffer("http://localhost/");
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getServletPath()
     */
    public String getServletPath()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getServletPath() to remain plausible.");
        return "";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getSession(boolean)
     */
    public HttpSession getSession(boolean create)
    {
        if (!create)
        {
            return null;
        }

        return new FakeHttpSession();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#getSession()
     */
    public HttpSession getSession()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
     */
    public boolean isRequestedSessionIdValid()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
     */
    public boolean isRequestedSessionIdFromCookie()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
     */
    public boolean isRequestedSessionIdFromURL()
    {
        return false;
    }

    /**
     * @see jakarta.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
     * @deprecated
     */
    @Deprecated
    public boolean isRequestedSessionIdFromUrl()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getAttribute(java.lang.String)
     */
    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getAttributeNames()
     */
    public Enumeration<String> getAttributeNames()
    {
        return Collections.enumeration(attributes.keySet());
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getCharacterEncoding()
     */
    public String getCharacterEncoding()
    {
        return characterEncoding;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
     */
    public void setCharacterEncoding(String characterEncoding) throws UnsupportedEncodingException
    {
        this.characterEncoding = characterEncoding;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getContentLength()
     */
    public int getContentLength()
    {
        return content.length;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getContentType()
     */
    public String getContentType()
    {
        return contentType;
    }

    /**
     * @see #getContentType()
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * @see #getContentType()
     */
    private String contentType = "text/plain";

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getInputStream()
     */
    public ServletInputStream getInputStream() throws IOException
    {
        return new DelegatingServletInputStream(new ByteArrayInputStream(content));
    }

    /**
     * @see #getInputStream()
     */
    public void setContent(byte[] content)
    {
        this.content = content;
    }

    /**
     * @see #getInputStream()
     */
    public void setContent(String content)
    {
        this.content = content.getBytes();
    }

    /**
     * @see #getInputStream()
     */
    protected byte[] content;

    /**
     * @return "127.0.0.1"
     */
    public String getLocalAddr()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getLocalAddr() to remain plausible.");
        return "127.0.0.1";
    }

    /**
     * @return "localhost"
     */
    public String getLocalName()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getLocalName() to remain plausible.");
        return "localhost";
    }

    /**
     * @return 80
     */
    public int getLocalPort()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getLocalPort() to remain plausible.");
        return 80;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getParameter(java.lang.String)
     */
    public String getParameter(String name)
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getParameterNames()
     */
    public Enumeration<String> getParameterNames()
    {
        return Collections.enumeration(Collections.<String>emptySet());
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getParameterValues(java.lang.String)
     */
    public String[] getParameterValues(String name)
    {
        return null;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getParameterMap()
     */
    public Map<String, String[]> getParameterMap()
    {
        return Collections.emptyMap();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getProtocol()
     */
    public String getProtocol()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getProtocol() to remain plausible.");
        return "HTTP/1.1";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getScheme()
     */
    public String getScheme()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getScheme() to remain plausible.");
        return "http";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getServerName()
     */
    public String getServerName()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getServerName() to remain plausible.");
        return "localhost";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getServerPort()
     */
    public int getServerPort()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getServerPort() to remain plausible.");
        return 80;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getReader()
     */
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRemoteAddr()
     */
    public String getRemoteAddr()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getRemoteAddr() to remain plausible.");
        return "localhost";
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRemoteHost()
     */
    public String getRemoteHost()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getRemoteHost() to remain plausible.");
        return "localhost";
    }

    /**
     * @return 80
     */
    public int getRemotePort()
    {
        log.warn("Inventing data in FakeHttpServletRequest.getRemotePort() to remain plausible.");
        return 80;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#setAttribute(java.lang.String, java.lang.Object)
     */
    public void setAttribute(String name, Object o)
    {
        attributes.put(name, o);
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String name)
    {
        attributes.remove(name);
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getLocale()
     */
    public Locale getLocale()
    {
        return Locale.getDefault();
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getLocales()
     */
    public Enumeration<Locale> getLocales()
    {
        return Collections.enumeration(Arrays.asList(Locale.getDefault()));
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#isSecure()
     */
    public boolean isSecure()
    {
        return false;
    }

    /* (non-Javadoc)
     * @see jakarta.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
     */
    public RequestDispatcher getRequestDispatcher(String path)
    {
        return new RequestDispatcher()
        {
            /* (non-Javadoc)
             * @see jakarta.servlet.RequestDispatcher#include(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse)
             */
            public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException
            {
            }

            /* (non-Javadoc)
             * @see jakarta.servlet.RequestDispatcher#forward(jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse)
             */
            public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException
            {
            }
        };
    }

    /**
     * @see jakarta.servlet.ServletRequest#getRealPath(java.lang.String)
     * @deprecated
     */
    @Deprecated
    public String getRealPath(String path)
    {
        return null;
    }

    /**
     * The character encoding in the supposed request
     */
    private String characterEncoding = null;

    /**
     * The list of attributes
     */
    private final Map<String, Object> attributes = new HashMap<String, Object>();

    /**
     * The log stream
     */
    private static final Log log = LogFactory.getLog(FakeHttpServletRequestObject24.class);
}
