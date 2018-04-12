package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLSelectElement;
import sample.HttpRequset.SimpleHttpRequest;
import sample.JavaScript.SimpleDomHandler;


public class Main extends Application {
    private Button getProduct;
    private Button addToCart;
    private Button buy;
    private DataUserLoader userLoader;
    private DataProductLoader productLoader;
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
//        setPrimaryStage(primaryStage);
        setSecondStage();
//        setThirdStage();
    }


    private void setThirdStage(){
        StackPane root = new StackPane();

        Scene scene = new Scene(root);
        Stage thirdStage = new Stage();
        thirdStage.setScene(scene);
        root.setPadding(new Insets(40));

        TextField itemName = new TextField();
        TextField itemColor = new TextField();
        TextField itemSize = new TextField();
        TextField url = new TextField();


        itemName.setPromptText("Item name");
        itemColor.setPromptText("Item color");
        itemSize.setPromptText("Item size");

        Button goNext = new Button("Go next");
        Button goBack = new Button("Go back");

        BorderPane buttonsPane = new BorderPane();

        buttonsPane.setRight(goNext);
        buttonsPane.setLeft(goBack);

        goBack.setOnAction(event -> {
            thirdStage.hide();
            setSecondStage();
        });

        goNext.setOnAction(event -> {

            productLoader = new DataProductLoader(itemColor.getText(),itemSize.getText(),itemName.getText());
            thirdStage.hide();
            setPrimaryStage(primaryStage);
        });

        VBox itemInfo = new VBox(10);
        itemInfo.getChildren().addAll(itemName,itemColor,itemSize,buttonsPane);

        root.getChildren().add(itemInfo);
        thirdStage.show();
    }

    private void setSecondStage(){

        StackPane root = new StackPane();

        root.setPadding(new Insets(50));
        Scene scene = new Scene(root,640,480);
        Stage secondStage = new Stage();
        secondStage.setTitle("Bot supreme - Please enter data");
        secondStage.setScene(scene);
        secondStage.show();

        VBox userInfo = new VBox(5);
        TextField name = new TextField();
        TextField surName = new TextField();
        TextField email = new TextField();
        TextField telephone = new TextField();
        TextField address = new TextField();
        TextField city = new TextField();
        TextField postCode = new TextField();
        TextField country = new TextField();
        TextField creditCardNumber = new TextField();
        PasswordField cvv = new PasswordField();
        HBox cardInfo = new HBox(10);
        ComboBox<String> cardType = new ComboBox<>();
        ComboBox<String> monthCardValid= new ComboBox<>();
        ComboBox<Integer> yearCardValid = new ComboBox<>();

        BorderPane buttonWrapper = new BorderPane();
        Button goNext = new Button("Go next");
        buttonWrapper.setRight(goNext);

        cardType.getItems().addAll(
                "visa","master","american_express","solo","paypal"
        );
        monthCardValid.getItems().addAll(
            "01","02","03","04","05","06","07","08","09","10","11","12"
        );
        yearCardValid.getItems().addAll(
          2018,2019,2020,2021,2022,2023,2024,2025,2026,2027,2028
        );

        cardType.getSelectionModel().selectFirst();
        monthCardValid.getSelectionModel().selectFirst();
        yearCardValid.getSelectionModel().selectFirst();


        name.setPromptText("Name");
        surName.setPromptText("SurName");
        email.setPromptText("Email");
        telephone.setPromptText("Telephone");
        address.setPromptText("Address");
        city.setPromptText("City");
        postCode.setPromptText("PostCode");
        country.setPromptText("Country");
        creditCardNumber.setPromptText("Credit card number");
        cvv.setPromptText("Cvv");


        cardInfo.getChildren().addAll(cardType,monthCardValid,yearCardValid);
        userInfo.getChildren().addAll(name,surName,email,telephone,address,city,postCode,country,creditCardNumber,cvv,cardInfo,buttonWrapper);

        goNext.setOnAction(event -> {

            String nameUser = name.getText();
            String surnameUser = surName.getText();
            String emailUser= email.getText();
            String telephoneUser= telephone.getText();
            String addressUser = address.getText();
            String cityUser = city.getText();
            String postCodeUser = postCode.getText();
            String countryUser = country.getText();
            String creditCardNumberUser = creditCardNumber.getText();
            int cvvUser = Integer.parseInt(cvv.getText());
            String cardTypeUser = cardType.getSelectionModel().getSelectedItem();
            String monthCardValidUser = (monthCardValid.getSelectionModel().getSelectedItem());
            int yearCardValidUser= yearCardValid.getSelectionModel().getSelectedItem();

            userLoader = new DataUserLoader(
                    nameUser,surnameUser,emailUser,
                    telephoneUser,addressUser,
                    cityUser,postCodeUser,
                    countryUser,cardTypeUser,creditCardNumberUser,
                    monthCardValidUser,yearCardValidUser,cvvUser
            );
            secondStage.hide();
            setThirdStage();

        });



        root.getChildren().add(userInfo);

    }

    private void setPrimaryStage(Stage primaryStage){
        GridPane root = new GridPane();
        primaryStage.setTitle("Bot Supreme");
        getProduct = new Button("Find product");
        addToCart = new Button("Add to cart");
        buy = new Button("Buy");
        Button refresh = new Button("refresh");
        refresh.setStyle("-fx-spacing: 30");

        HBox box = new HBox(10);
        box.getChildren().addAll(getProduct,addToCart,buy,refresh);
        WebView view = getWebView();


//        view.getEngine().getLoadWorker().stateProperty().addListener(
//                new ChangeListener<State>() {
//                    public void changed(ObservableValue ov, State oldState, State newState) {
//                        if (newState == State.SUCCEEDED) {
//                            System.out.println(view.getEngine().getLocation());
//                        }
//                    }
//        });


        view.getEngine().setJavaScriptEnabled(true);

        refresh.setOnAction(event -> {
            view.getEngine().reload();
        });

        SimpleDomHandler handler= new SimpleDomHandler(view.getEngine());
        getProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String className = "name";

                int length = handler.getElementsLengthByClass(className);

                for(int i =0 ; i < length; i++){
                    if(handler.getElementByClass(className,i).getTextContent().equals(productLoader.getProductName())){
                        handler.clickElementByClass(className,i);
                        break;
                    }
                }

            }
        });

        addToCart.setOnAction((event)->{
            //            for(let c = 0; c< document.getElementsByClassName('style-images').length;c++) {
            findColor(handler);
            findSize(handler);
            handler.clickElementByClass("cart-button",0);
            new Thread(()->{
                try {
                    Thread.sleep(500);
                    handler.clickElementById("checkout-now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        });

        buy.setOnAction(event -> {


//            handler.execute("document.getElementById('order_billing_name').value='"+userLoader.getName()+" "+userLoader.getSurname()+"'");

            HTMLInputElement name = (HTMLInputElement) handler.execute("document.getElementById('order_billing_name')");
            name.setValue(userLoader.getName()+" "+userLoader.getSurname());
            ((HTMLInputElement) handler.execute("document.getElementById('order_email')")).setValue(userLoader.getEmail());

            ((HTMLInputElement) handler.execute("document.getElementById('order_tel')")).setValue(userLoader.getTelephone());

            ((HTMLInputElement) handler.execute("document.getElementById('order_billing_address')")).setValue(userLoader.getAddress());
            ((HTMLInputElement) handler.execute("document.getElementById('order_billing_city')")).setValue(userLoader.getCity());
            ((HTMLInputElement) handler.execute("document.getElementById('order_billing_zip')")).setValue(userLoader.getPostCode());
            ((HTMLInputElement) handler.execute("document.getElementById('credit_card_n')")).setValue(userLoader.getcreditCardNumber());
            ((HTMLInputElement) handler.execute("document.getElementById('credit_card_cvv')")).setValue(userLoader.getCvv()+"");



            handler.execute(String.format("document.getElementById('order_billing_country').value='%s'",userLoader.getCountry()));
            handler.clickElementById("order_terms");
            handler.execute(String.format("document.getElementById('credit_card_type').value='%s'",userLoader.getCardType()));
            handler.execute(String.format("document.getElementById('credit_card_month').value='%s'",userLoader.getmonthCardValid()));
            handler.execute(String.format("document.getElementById('credit_card_year').value='%s'",userLoader.getYearCardValid()));



            //Controls

            System.out.println("Controls");

            System.out.println("Country: "+((HTMLSelectElement)(handler.execute("document.getElementById('order_billing_country')"))).getValue());
            System.out.println("Card type: "+((HTMLSelectElement)handler.execute("document.getElementById('credit_card_type')")).getValue());
            System.out.println("Card valid month: "+((HTMLSelectElement)handler.execute("document.getElementById('credit_card_month')")).getValue());
            System.out.println("Card valid year: "+((HTMLSelectElement) handler.execute("document.getElementById('credit_card_year')")).getValue());

            // document.getElementById('credit_card_n').value="5169310006155203";
            // $('#credit_card_type').get(0).selectedIndex =2;
            // $('#order_billing_country').val('PL');

        });
        root.add(view,0,1);
        root.add(box,0,0);
        primaryStage.setScene(new Scene(root, 640, 480));

        primaryStage.show();
    }

    private void findSize(SimpleDomHandler handler){
        int length = handler.getElementLengthById("size-options");
        for (int i =0 ; i < length;i++){
            if(handler.execute(String.format("document.getElementById('size-options').children[%d].text",i)).equals(productLoader.getSize())){
                handler.execute(String.format("document.getElementById('size-options').value = document.getElementById('size-options').children[%d].value;",i));
                System.out.println("Size found");
            }
        }
    }


    private void findColor(SimpleDomHandler handler){

        int length = handler.getElementsLengthByClass("style-images");

        System.out.println(1);
        System.out.println(length);
        for(int i =0 ; i < length+1; i++){
            if(!handler.getElementById("style-name").getTextContent().equals(productLoader.getColor())){
                handler.execute(String.format("document.getElementsByClassName('style-images')[%d].children[0].click();",i));
            }else{
                System.out.println("Color found");
                break;
            }
        }

    }


    private void addListener(WebEngine engine){
        SimpleDomHandler handler = new SimpleDomHandler(engine);
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                if (t1 == Worker.State.SUCCEEDED) {
                        handler.clickElementById("new-category");
                }
            }
        });
    }

    private WebView getWebView(){
        WebView view = new WebView();
        view.setMinSize(640,480);
        WebEngine engine = view.getEngine();
        System.out.println(engine.getUserAgent());
        engine.setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36");
        engine.load("http://www.supremenewyork.com/mobile/#categories/new");
        return view;
    }


    public static void main(String[] args) {
        SimpleHttpRequest req = new SimpleHttpRequest();
        launch(args);
    }
}
