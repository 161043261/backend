import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        final String docBase = new File("./src/main/webapp").getAbsolutePath();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        // contextPath = "servlet"
        // docBase = "./src/main/webapp"
        Context context = tomcat.addWebapp("/serve", docBase);
        WebResourceRoot root = new StandardRoot(context);
        final String webAppMount = "/WEB-INF/classes";
        final String base = new File("./target/classes").getAbsolutePath();
        final String internalPath = "/";
        root.addPreResources(
                new DirResourceSet(root, webAppMount, // the webAppMount must begin with "/"
                        base, internalPath));
        context.setResources(root);
        tomcat.start();
        tomcat.getServer().await();
    }
}
