package Tests;


import org.junit.jupiter.api.Test;

import sample.JavaScript.SimpleScriptGenerator;
import sample.JavaScript.interfaces.ScriptGenerator;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class ScriptGeneratorTest  {

    @Test
    public void getElementById_example_script(){

        SimpleScriptGenerator generator = new SimpleScriptGenerator();
        String script = generator.getElementById("example");
        assertEquals("document.getElementById('example')",script);
        assertNotNull(script);
    }

    @Test
    public void getElementByClass_example_2_script(){

        ScriptGenerator generator = new SimpleScriptGenerator();
        String script = generator.getElementByClass("example",2);
        assertEquals("document.getElementsByClassName('example')[2]",script);
        assertNotNull(script);
    }

    @Test
    public void getElementsByClassLength_link_script(){

        SimpleScriptGenerator generator = new SimpleScriptGenerator();
        String script = generator.getElementsByClassLength("link");
        assertEquals("document.getElementsByClassName('link').length",script);
        assertNotNull(script);
    }


    @Test
    public void clickElementById_example_script() {
        SimpleScriptGenerator generator = new SimpleScriptGenerator();
        String script = generator.clickElementById("link");
        assertEquals("document.getElementById('link').click()",script);
        assertNotNull(script);
    }

}
