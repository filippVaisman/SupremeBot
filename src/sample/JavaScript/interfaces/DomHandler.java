package sample.JavaScript.interfaces;

import org.w3c.dom.Element;

public interface DomHandler {

    Element getElementById(String id);
    Element getElementByClass(String className, int index);
    int getElementsLengthByClass(String className);
    void clickElementById(String id);
    void clickElementByClass(String className, int index);




}
