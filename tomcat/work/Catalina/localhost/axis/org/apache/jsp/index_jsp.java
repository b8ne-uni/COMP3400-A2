/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.1
 * Generated at: 2017-10-23 23:27:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


    /*
     * A library file to produce i18n web applications. This can be easily
     * reused from your jsp(s) - just include and call any methods.
     * @author toshi
     */

    // private variable
    HttpServletRequest _req = null;

    // private variable
    String _strResourceName = null;

    /**
     * Set a HttpServletRequest to a private variable.
     * @param request HttpServletRequest
     */
    void setRequest(HttpServletRequest request) {
        _req = request;
    }

    /**
     * Get the private variable of the HttpServletRequest.
     * @return HttpServletRequest
     */
    HttpServletRequest getRequest() {
        return _req;
    }

    /**
     * Set a resouce base name to a private variable.
     * @param resouce The resouce base name
     */
    void setResouceBase(String resource) {
        _strResourceName = resource;
    }

    /**
     * Get the private variable of the resouce base name.
     * @return resouce The resouce base name
     */
    String getResouceBase() {
        return _strResourceName;
    }

    /**
     * Get a ResourceBundle object.
     * @return a ResourceBundle object
     */
    ResourceBundle getRB() {
        String strLocale = getRequest().getParameter("locale");
        ResourceBundle objRb = null;
        Locale objLcl = null;

        if (strLocale!=null) {
            objLcl=new Locale(strLocale,"");
        } else {
            objLcl=getRequest().getLocale();
        }

        Locale.setDefault(objLcl);
        objRb = ResourceBundle.getBundle(getResouceBase(),objLcl);

        return objRb;
    }

    /**
     * Get a list of locale choice
     * @return a list of supported locales
     */
    String getLocaleChoice() {
        String choice = getMessage("locales");
        StringBuffer buf = new StringBuffer();
        
        buf.append("<div align=\"right\">\n");
        buf.append(getMessage("language"));
        buf.append(": ");

        StringTokenizer st = new StringTokenizer(choice);
        String locale = null;
        while (st.hasMoreTokens()) {
            locale = st.nextToken();
            buf.append("[<a href=\"?locale="+ locale +"\">"+ locale +"</a>] ");
        }
        buf.append("\n</div>\n");

        return buf.toString();
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @return The formatted message
     */
    String getMessage(String key) {
        return getMessage(key, null, null, null, null, null);
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @param arg0 The argument to place in variable {0}
     * @return The formatted message
     */
    String getMessage(String key, String arg0) {
        return getMessage(key, arg0, null, null, null, null);
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @param arg0 The argument to place in variable {0}
     * @param arg1 The argument to place in variable {1}
     * @return The formatted message
     */
    String getMessage(String key, String arg0, String arg1) {
        return getMessage(key, arg0, arg1, null, null, null);
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @param arg0 The argument to place in variable {0}
     * @param arg1 The argument to place in variable {1}
     * @param arg2 The argument to place in variable {2}
     * @return The formatted message
     */
    String getMessage(String key, String arg0, String arg1, String arg2) {
        return getMessage(key, arg0, arg1, arg2, null, null);
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @param arg0 The argument to place in variable {0}
     * @param arg1 The argument to place in variable {1}
     * @param arg2 The argument to place in variable {2}
     * @param arg3 The argument to place in variable {3}
     * @return The formatted message
     */
    String getMessage(String key, String arg0, String arg1,
                      String arg2, String arg3) {
        return getMessage(key, arg0, arg1, arg2, arg3, null);
    }

    /**
     * Get a message from i18n.properties with several arguments.
     * @param key The resource key
     * @param arg0 The argument to place in variable {0}
     * @param arg1 The argument to place in variable {1}
     * @param arg2 The argument to place in variable {2}
     * @param arg3 The argument to place in variable {3}
     * @param arg4 The argument to place in variable {4}
     * @return The formatted message
     */
    String getMessage(String key, String arg0, String arg1,
                      String arg2, String arg3, String arg4) {
        String strPattern = getRB().getString(key);

        String [] params = { arg0, arg1, arg2, arg3, arg4 };
        for (int i=0; i<5; i++) {
            if (params[i]!=null) params[i]=replaceAll(params[i],"%20"," ");
        }

        if (arg0!=null) strPattern = replaceAll(strPattern,"{0}",params[0]);
        if (arg1!=null) strPattern = replaceAll(strPattern,"{1}",params[1]);
        if (arg2!=null) strPattern = replaceAll(strPattern,"{2}",params[2]);
        if (arg3!=null) strPattern = replaceAll(strPattern,"{3}",params[3]);
        if (arg4!=null) strPattern = replaceAll(strPattern,"{4}",params[4]);

        return strPattern;
    }

    /**
     * Get a replaced string by the specified message.
     * @param source  The original message
     * @param pattern The key message for replacing
     * @param replace The message to place in the key variable - 'pattern'
     * @return The replaced message
     */
    String replaceAll(String source, String pattern, String replace)
    {
        int i=0;
        boolean ret = false;
        StringBuffer buf = new StringBuffer();

        int lenSource  = source.length();
        int lenPattern = pattern.length();

        for (i=0; i<lenSource; i++) {
            ret = source.regionMatches(i, pattern, 0, lenPattern);
            if (ret) {
                buf.append(source.substring(0,i));
                buf.append(replace);
                buf.append(source.substring(i+lenPattern));
                source = replaceAll(buf.toString(), pattern, replace);
                break;
            }
        }
        return source;
    }

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/i18nLib.jsp", Long.valueOf(1145692612000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\n");
      out.write("\n");

/*
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

      out.write('\n');
      out.write('\n');
      out.write('\n');

/*
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

    // initialize a private HttpServletRequest
    setRequest(request);

    // set a resouce base
    setResouceBase("i18n");

      out.write("\n");
      out.write("\n");
      out.write("<head>\n");
      out.write(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
      out.write(" <title>Apache-Axis</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body bgcolor=\"#FFFFFF\">\n");
      out.write("\n");
      out.write("<h1 align=\"center\">Apache-AXIS</h1>\n");
      out.write("\n");
      out.print( getLocaleChoice() );
      out.write('\n');
      out.write('\n');

  out.print(getMessage("welcomeMessage")+"<p/>");
  out.print(getMessage("operationType"));

      out.write("\n");
      out.write("\n");
      out.write("<ul>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("validationURL") +"\">");
      out.print(getMessage("validation") +"</a> - ");
      out.print(getMessage("validationFootnote00") +"<br>");
      out.print("<i>"+ getMessage("validationFootnote01") +"</i>");
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("serviceListURL") +"\">");
      out.print(getMessage("serviceList") +"</a> - ");
      out.print(getMessage("serviceListFootnote"));
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("callAnEndpointURL") +"\">");
      out.print(getMessage("callAnEndpoint") +"</a> - ");
      out.print(getMessage("callAnEndpointFootnote00") +" ");
      out.print(getMessage("callAnEndpointFootnote01"));
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("visitURL") +"\">");
      out.print(getMessage("visit") +"</a> - ");
      out.print(getMessage("visitFootnote"));
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("adminURL") +"\">");
      out.print(getMessage("admin") +"</a> - ");
      out.print(getMessage("adminFootnote"));
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("  <li>\n");
      out.write("    ");

      out.print("<a href=\""+ getMessage("soapMonitorURL") +"\">");
      out.print(getMessage("soapMonitor") +"</a> - ");
      out.print(getMessage("soapMonitorFootnote"));
    
      out.write("\n");
      out.write("  </li>\n");
      out.write("\n");
      out.write("</ul>\n");
      out.write("\n");

  out.print(getMessage("sideNote") +"<p/>");

      out.write('\n');
      out.write('\n');

  out.print("<h3>"+ getMessage("validatingAxis") +"</h3>");

  out.print(getMessage("validationNote00") +"<p/>");
  out.print(getMessage("validationNote01"));

      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
