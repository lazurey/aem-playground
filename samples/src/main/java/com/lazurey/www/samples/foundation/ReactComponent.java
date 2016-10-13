package com.lazurey.www.samples.foundation;

import org.apache.commons.io.IOUtils;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;


public abstract class ReactComponent {

    private static final String CLIENT_SIDE = "client-side";
    private static final String SERVER_SIDE = "server-side";
    private static final String RENDER_PROPERTY = "renderside";
    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager(null);

    protected final Node component;
    protected final Node current;

    protected ReactComponent(Node component, Node current) {
        this.component = component;
        this.current = current;
    }

    public String render() throws Exception {
         return isClientSideRendering() ? renderClientSide() : renderServerSide();
    }

    public abstract String getDataAsJson() throws Exception;

    private boolean isClientSideRendering() throws RepositoryException {
        return CLIENT_SIDE.equalsIgnoreCase(getRender());
    }

    private String getRender() throws RepositoryException {
        return current.hasProperty(RENDER_PROPERTY) ? current.getProperty(RENDER_PROPERTY).getString() : SERVER_SIDE;
    }

    private String renderServerSide() throws Exception {
        ScriptEngine engine = templateEngine();
        Invocable invocable = (Invocable) engine;
        try {
            Object html = invocable.invokeFunction("renderServer", getDataAsJson());
            return String.valueOf(html);
        } catch (Exception e) {
            return "Error when rendering the component: " + e.toString();
        }
    }

    private String renderClientSide() throws Exception {
        ScriptEngine engine = templateEngine();
        Invocable invocable = (Invocable) engine;
        try {
//            Object html = invocable.invokeFunction("renderClient", getDataUri());
//            return String.valueOf(html);
            return getPlaceholderHTML();
        } catch (Exception e) {
            return "Error when rendering the component (client-side): " + e.toString();
        }
    }

    private ScriptEngine templateEngine() throws Exception {
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
        try {
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("nashorn-polyfill.js")));
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("react.min.js")));
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("react-dom-server.min.js")));
        } catch (Exception e) {
            throw new Exception("Error when loading JS libraries: " + e.toString());
        }
        try {
            nashorn.eval(getTemplate());
        } catch (Exception e) {
            throw new Exception("Error when loading the template: " + e.toString());
        }
        return nashorn;
    }

    protected String getTemplate() throws Exception {
        Binary template = component.getNode("component.jsx/jcr:content").getProperty("jcr:data").getBinary();
        return IOUtils.toString(template.getStream());
    }

    private String getDataUri() throws RepositoryException {
        return current.getPath() + ".json";
    }

    private String getPlaceholderHTML() {
        return "<div id='comp-placeholder'></div>";
    }
}
