package cz.muni.fi.pa165.rest.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * We need to rewrite headers to allow for cross-origin access for some
 * operations e.g. DELETE. Without this, you might get messages such as "HTTP
 * Status 405 - Request method 'DELETE' not supported" when trying to DELETE,
 * PUT or POST Here we are allowing all origins "Access-Control-Allow-Origin",
 * "*" and all operations "Access-Control-Allow-Methods", "GET, POST, PUT,
 * DELETE, OPTIONS"
 *
 * @author brossi
 */
public class AllowOriginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.

        //NECHYTAT finta, lebo quasar "strasne mudry", si nacachoval stare responses
//        response.setHeader("Content-Type","application/hal+json");


        response.setDateHeader("Expires", 0); // Proxies.
        return true;
    }

}
