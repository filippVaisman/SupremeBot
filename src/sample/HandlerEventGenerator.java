package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.web.WebEngine;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLSelectElement;
import sample.JavaScript.SimpleDomHandler;

public class HandlerEventGenerator {

    private SimpleDomHandler domHandler;
    private DataProductLoader productLoader;
    private DataUserLoader userLoader;
    Logger logger;

    public HandlerEventGenerator(SimpleDomHandler domHandler) {
        logger = new Logger();
        this.domHandler = domHandler;
        this.productLoader = DataProductLoader.getDataProductLoader();
        this.userLoader = DataUserLoader.getDataUserLoader();
    }

    public EventHandler<ActionEvent> getProductHandler(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    String className = "name";

                    int length = domHandler.getElementsLengthByClass(className);

                    for(int i =0 ; i < length; i++){
                        if(domHandler.getElementByClass(className,i).getTextContent().equals(productLoader.getProductName())){
                            domHandler.clickElementByClass(className,i);
                            break;
                        }
                    }

            }
        };
    }


    public EventHandler<ActionEvent> getAddToCartHandler(){


        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                findColor();
                findSize();
                domHandler.clickElementByClass("cart-button",0);
                new Thread(()->{
                    try {
                        Thread.sleep(500);
                        domHandler.clickElementById("checkout-now");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }
        };

    }


    public EventHandler<ActionEvent> getBuyHandler(){

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HTMLInputElement name = (HTMLInputElement) domHandler.execute("document.getElementById('order_billing_name')");
                name.setValue(userLoader.getName()+" "+userLoader.getSurname());
                ((HTMLInputElement) domHandler.execute("document.getElementById('order_email')")).setValue(userLoader.getEmail());

                ((HTMLInputElement) domHandler.execute("document.getElementById('order_tel')")).setValue(userLoader.getTelephone());

                ((HTMLInputElement) domHandler.execute("document.getElementById('order_billing_address')")).setValue(userLoader.getAddress());
                ((HTMLInputElement) domHandler.execute("document.getElementById('order_billing_city')")).setValue(userLoader.getCity());
                ((HTMLInputElement) domHandler.execute("document.getElementById('order_billing_zip')")).setValue(userLoader.getPostCode());
                ((HTMLInputElement) domHandler.execute("document.getElementById('credit_card_n')")).setValue(userLoader.getcreditCardNumber());
                ((HTMLInputElement) domHandler.execute("document.getElementById('credit_card_cvv')")).setValue(userLoader.getCvv()+"");



                domHandler.execute(String.format("document.getElementById('order_billing_country').value='%s'",userLoader.getCountry()));
                domHandler.clickElementById("order_terms");
                domHandler.execute(String.format("document.getElementById('credit_card_type').value='%s'",userLoader.getCardType()));
                domHandler.execute(String.format("document.getElementById('credit_card_month').value='%s'",userLoader.getmonthCardValid()));
                domHandler.execute(String.format("document.getElementById('credit_card_year').value='%s'",userLoader.getYearCardValid()));



                //Controls

                logger.log("Controls");

                logger.log("Country: "+((HTMLSelectElement)(domHandler.execute("document.getElementById('order_billing_country')"))).getValue());
                logger.log("Card type: "+((HTMLSelectElement)domHandler.execute("document.getElementById('credit_card_type')")).getValue());
                logger.log("Card valid month: "+((HTMLSelectElement)domHandler.execute("document.getElementById('credit_card_month')")).getValue());
                logger.log("Card valid year: "+((HTMLSelectElement) domHandler.execute("document.getElementById('credit_card_year')")).getValue());

                // document.getElementById('credit_card_n').value="5169310006155203";
                // $('#credit_card_type').get(0).selectedIndex =2;
                // $('#order_billing_country').val('PL');
            }
        };

    }


    private void findSize(){
        int length = domHandler.getElementLengthById("size-options");
        for (int i =0 ; i < length;i++){
            if(domHandler.execute(String.format("document.getElementById('size-options').children[%d].text",i)).equals(productLoader.getSize())){
                domHandler.execute(String.format("document.getElementById('size-options').value = document.getElementById('size-options').children[%d].value;",i));
                logger.log("Size found");
            }
        }
    }

    private void findColor(){

        int length = domHandler.getElementsLengthByClass("style-images");
        logger.log(length);
        for(int i =0 ; i < length+1; i++){
            if(!domHandler.getElementById("style-name").getTextContent().equals(productLoader.getColor())){
                domHandler.execute(String.format("document.getElementsByClassName('style-images')[%d].children[0].click();",i));
            }else{
                logger.log("Color found");
                break;
            }
        }

    }



}
