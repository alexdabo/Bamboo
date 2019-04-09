package com.bamboo.api;

import javax.servlet.http.HttpServletRequest;

public class Router {

    String routeName;
    HttpServletRequest req;

    public Router(String routeName) {
        this.routeName = routeName;
    }

    public Router(HttpServletRequest req, String routeName) {
        this.routeName = routeName;
        this.req = req;
    }

    public void setRequest(HttpServletRequest req) {
        this.req = req;
    }

    public String getRoute() {
        String route = "";
        String url = null;
        url = (String) req.getAttribute("javax.servlet.forward.request_uri");
        url = url == null ? ((HttpServletRequest) req).getRequestURI() : url;

        boolean assignable = false;
        String[] routes = url.split("/");
        for (int i = 0; i < routes.length; i++) {
            if (routes[i].equals(routeName)) {
                assignable = true;
            }
            if (assignable) {
                if (!isNumber(routes[i])) {
                    route = route.concat("/" + routes[i]);
                }
            }
        }
        return route;
    }

    public String getParam(String parameter) {
        String param = "";
        String url = null;
        url = (String) req.getAttribute("javax.servlet.forward.request_uri");
        url = url == null ? ((HttpServletRequest) req).getRequestURI() : url;
        String[] routes = url.split("/");
        for (int i = 0; i < routes.length; i++) {
            if (routes[i].equals(parameter)) {
                param = routes[i + 1];
            }
        }
        return param;
    }

    private boolean isNumber(String string) {
        boolean isnumber = false;
        try {
            Double num = Double.parseDouble(string);
            isnumber = true;
        } catch (NumberFormatException e) {
            isnumber = false;
        }

        return isnumber;
    }
}
