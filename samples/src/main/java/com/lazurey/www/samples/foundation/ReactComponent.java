package com.lazurey.www.samples.foundation;

import org.apache.commons.io.IOUtils;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

public abstract class ReactComponent {

    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager(null);

    protected final Node component;
    protected final Node current;

    protected ReactComponent(Node component, Node current) {
        this.component = component;
        this.current = current;
    }

    public String render() throws Exception {
         return renderClientSide();
    }

    private String renderClientSide() throws Exception {
        ScriptEngine engine = templateEngine();
        Invocable invocable = (Invocable) engine;
        try {
            Object html = invocable.invokeFunction("renderClient", "Java Client Invoking you");
            return String.valueOf(html);
        } catch (Exception e) {
            return "Error when rendering the component: " + e.toString();
        }
    }

    private ScriptEngine templateEngine() throws Exception {
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
        try {
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("nashorn-polyfill.js")));
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("react.min.js")));
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("react-dom.min.js")));
        } catch (Exception e ) {
            System.out.println("Error when loading JS libraries: " + e.toString());
        }
        try {
            nashorn.eval(getTemplate());
        } catch (Exception e) {
            System.out.println("Error when loading the template: " + e.toString());
        }
        return nashorn;
    }

    protected String getTemplate() throws Exception {
        Binary template = component.getNode("component.jsx/jcr:content").getProperty("jcr:data").getBinary();
        return IOUtils.toString(template.getStream());
    }
}
