package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
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
import sample.JavaScript.SimpleDomHandler;




public class Main extends Application {
    private Button getProduct;
    private Button addToCart;
    private Button buy;
    private DataUserLoader userLoader;
    private DataProductLoader productLoader;
    private Stage primaryStage;
    private HandlerEventGenerator handlerEventGenerator;
    private static Logger logger;


    @Override
    public void start(Stage primaryStage) throws Exception{

        logger = new Logger();
        this.primaryStage = primaryStage;
        try {
            setSecondStage();
        }catch (Exception e){
        logger.log(e.getMessage());
        e.printStackTrace();
        }

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

            productLoader = DataProductLoader.getDataProductLoader();
            productLoader.setValues(itemColor.getText(),itemSize.getText(),itemName.getText());
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

            userLoader = DataUserLoader.getDataUserLoader();
            userLoader.setValues(
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
//                            logger.log(view.getEngine().getLocation());
//                        }
//                    }
//        });


        view.getEngine().setJavaScriptEnabled(true);

        refresh.setOnAction(event -> {
            view.getEngine().reload();
        });



        SimpleDomHandler handler= new SimpleDomHandler(view.getEngine());
        handlerEventGenerator = new HandlerEventGenerator(handler);
        getProduct.setOnAction(handlerEventGenerator.getProductHandler());

        addToCart.setOnAction(handlerEventGenerator.getAddToCartHandler());

        buy.setOnAction(handlerEventGenerator.getBuyHandler());


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
                logger.log("Size found");
            }
        }
    }


    private void findColor(SimpleDomHandler handler){

        int length = handler.getElementsLengthByClass("style-images");
        logger.log(length);
        for(int i =0 ; i < length+1; i++){
            if(!handler.getElementById("style-name").getTextContent().equals(productLoader.getColor())){
                handler.execute(String.format("document.getElementsByClassName('style-images')[%d].children[0].click();",i));
            }else{
                logger.log("Color found");
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
        logger.log(engine.getUserAgent());
        engine.setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36");
        engine.load("http://www.supremenewyork.com/mobile/#categories/new");
        return view;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
