package sample.JavaScript;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Element;
import sample.JavaScript.interfaces.DomHandler;

public class SimpleDomHandler implements DomHandler{

    private WebEngine engine;
    private SimpleScriptGenerator scriptGenerator;


    public SimpleDomHandler(WebEngine engine) {
        this.engine = engine;
        scriptGenerator = new SimpleScriptGenerator();
    }

    @Override
    public Element getElementById(String id) {
        return (Element) engine.executeScript(scriptGenerator.getElementById(id));
    }

    @Override
    public Element getElementByClass(String className, int index) {
        return (Element) engine.executeScript(scriptGenerator.getElementByClass(className,index));
    }

    @Override
    public int getElementsLengthByClass(String className) {
        return Integer.parseInt(engine.executeScript(scriptGenerator.getElementsByClassLength(className)).toString());
    }

    @Override
    public void clickElementById(String id) {
        engine.executeScript(scriptGenerator.clickElementById(id));
    }

    @Override
    public void clickElementByClass(String className, int index) {
        engine.executeScript(scriptGenerator.clickElementByClassName(className,index));
    }

    public int getElementLengthById(String id){
        return Integer.parseInt(engine.executeScript(String.format("document.getElementById('%s').length",id)).toString());
    }

    public Object execute(String javascriptCode){
        Object e = engine.executeScript(javascriptCode);
//        System.out.println(e);
//        Element req=null;
//        try {
//            req = (Element)e;
//        }catch (ClassCastException exc){
//            System.err.println("Cast problem");
//        }
//
//        if(req==null)
//            return null;
//        else
        return e;
    }

}
