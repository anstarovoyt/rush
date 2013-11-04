package ru.naumen;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * You can run app with embedded jetty:<br>
 * java -cp . App
 */
public class App
{
    private static final String WEBAPPS_PATH = "../nau-challenge/src/main/webapp";
    private static final String WEB_XML_PATH = "../nau-challenge/src/main/webapp/WEB-INF/web.xml";

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setDescriptor(WEB_XML_PATH);
        context.setResourceBase(WEBAPPS_PATH);
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        server.start();
        server.join();
    }
}
