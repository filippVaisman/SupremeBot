package sample.JavaScript;

import sample.JavaScript.interfaces.ScriptGenerator;

public class SimpleScriptGenerator implements ScriptGenerator {

    @Override
    public String getElementById(String id) {
        return String.format("document.getElementById('%s');",id);
    }

    @Override
    public String getElementByClass(String className, int index) {

        return String.format("document.getElementsByClassName('%s')[%d];",className,index);
    }

    @Override
    public String getElementsByClassLength(String className) {
        return String.format("document.getElementsByClassName('%s').length;",className);
    }

    @Override
    public String clickElementById(String id) {
        return String.format("document.getElementById('%s').click();",id);
    }

    @Override
    public String clickElementByClassName(String className, int index) {
        return String.format("document.getElementsByClassName('%s')[%d].click();",className,index);
    }
}
