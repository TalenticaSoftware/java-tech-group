package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardThreadExecutor;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.AbstractHttp11Protocol;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        String webappDirLocation = "servlet-async/src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        StandardThreadExecutor executor = new StandardThreadExecutor();
        executor.setName("customExecutor");
        executor.setMaxThreads(1); // Only one Tomcat thread
        executor.setMinSpareThreads(1);
        tomcat.getService().addExecutor(executor);

        // Get the connector and set the executor
        /*
        Connector connector = tomcat.getConnector();
        if (connector.getProtocolHandler() instanceof org.apache.coyote.http11.Http11NioProtocol) {
            org.apache.coyote.http11.Http11NioProtocol protocolHandler = (org.apache.coyote.http11.Http11NioProtocol) connector.getProtocolHandler();
            protocolHandler.setExecutor(executor);
        }*/
        Connector connector = tomcat.getConnector();
        if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>) {
            AbstractHttp11Protocol<?> protocolHandler = (AbstractHttp11Protocol<?>) connector.getProtocolHandler();
            protocolHandler.setExecutor(executor);
        }

        Context ctx = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        Tomcat.addServlet(ctx, "asyncTestServlet", new AsyncTestServlet());
        Tomcat.addServlet(ctx, "testServlet", new TestServlet());
        ctx.addServletMappingDecoded("/test/async", "asyncTestServlet");
        ctx.addServletMappingDecoded("/test", "testServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}

