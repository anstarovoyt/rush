package ru.naumen;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("../nau-challenge/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("../nau-challenge/src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();
        server.join();
    }
}
