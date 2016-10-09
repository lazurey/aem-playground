package com.lazurey.www.samples.foundation;

import org.apache.commons.io.IOUtils;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;

public abstract class ReactComponent {

    private static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

    protected final Node component;
    protected final Node current;

    protected ReactComponent(Node component, Node current) {
        this.component = component;
        this.current = current;
    }

    public String render() throws Exception {
         return renderClientSide();
//        return "Render from sever";
    }

    private String renderClientSide() throws Exception {
        ScriptEngine engine = templateEngine();
//        engine.eval(getTemplate());
        Invocable invocable = (Invocable) engine;
        Object html = invocable.invokeFunction("renderClient", "Java Client Invoking you");
        return String.valueOf(html);

    }

    private ScriptEngine templateEngine() throws Exception {
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
        try {
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("nashorn-polyfill.js")));
            nashorn.eval(new InputStreamReader(getClass().getResourceAsStream("react.min.js")));
            nashorn.eval(getTemplate());
        } catch (Exception e) {
            throw new Exception("Error when loading javascript libraries.");
        }

        return nashorn;
    }

    protected String getTemplate() throws Exception {
        Binary template = component.getNode("component.jsx/jcr:content").getProperty("jcr:data").getBinary();
        return IOUtils.toString(template.getStream());
    }
}
