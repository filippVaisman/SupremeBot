package sample.JavaScript.interfaces;

public interface ScriptGenerator {
    String getElementById(String id);
    String getElementByClass(String className, int index);
    String getElementsByClassLength(String className);
    String clickElementById(String id);
    String clickElementByClassName(String className, int index);
}
