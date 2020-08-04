package net.banatech.sdm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;


public class Main extends Application {
    final int row = 9;
    final int col = 30;

    @Override
    public void start(Stage stage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        stage.setTitle("sdm");
//        stage.setScene(new Scene(root, 300, 275));
//        stage.show();
        Seat[][] seatList1 = new Seat[row][col];
        Seat[][] seatList2 = new Seat[row][col];
        Pane flight1 = new Pane();
        Pane flight2 = new Pane();
        VBox vBox1 = new VBox(10d);
        VBox vBox2 = new VBox(10d);

        flight1.getChildren().add(vBox1);
        flight2.getChildren().add(vBox2);

        Pane input1 = new VBox();
        Pane input2 = new VBox();
        Label label1 = new Label("Seat 0-0");
        Label label2 = new Label("Seat 0-0");
        input1.getChildren().add(label1);
        input2.getChildren().add(label2);

        HBox name1 = new HBox();
        HBox name2 = new HBox();
        Label nameLabel1 = new Label("氏名: ");
        Label nameLabel2 = new Label("氏名: ");
        TextField nameField1 = new TextField();
        TextField nameField2 = new TextField();
        name1.getChildren().add(nameLabel1);
        name1.getChildren().add(nameField1);
        name2.getChildren().add(nameLabel2);
        name2.getChildren().add(nameField2);
        input1.getChildren().add(name1);
        input2.getChildren().add(name2);

        HBox telNumber1 = new HBox();
        HBox telNumber2 = new HBox();
        Label telNumberLabel1 = new Label("電話番号");
        Label telNumberLabel2 = new Label("電話番号");
        TextField telNumberField1 = new TextField();
        TextField telNumberField2 = new TextField();
        telNumber1.getChildren().add(telNumberLabel1);
        telNumber1.getChildren().add(telNumberField1);
        telNumber2.getChildren().add(telNumberLabel2);
        telNumber2.getChildren().add(telNumberField2);
        input1.getChildren().add(telNumber1);
        input2.getChildren().add(telNumber2);

        Button reserveButton1 = new Button("予約");
        Button reserveButton2 = new Button("予約");
        input1.getChildren().add(reserveButton1);
        input2.getChildren().add(reserveButton2);
        reserveButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField1.getText();
                String telNumber = telNumberField1.getText();
                int row = Integer.parseInt(label1.getText().substring(5, 6));
                int col = Integer.parseInt(label1.getText().substring(7, 8));
                Boolean isSuccess = seatList1[row][col].reserve(name, telNumber);
                if(isSuccess){
                    label1.setText(label1.getText()+"予約済み");
                }
            }
        });
        reserveButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField1.getText();
                String telNumber = telNumberField1.getText();
                int row = Integer.parseInt(label2.getText().substring(5, 6));
                int col = Integer.parseInt(label2.getText().substring(7, 8));
                Boolean isSuccess = seatList2[row][col].reserve(name, telNumber);
                if(isSuccess){
                    label2.setText(label2.getText()+"予約済み");
                }
            }
        });

        Button cancelButton1 = new Button("キャンセル");
        Button cancelButton2 = new Button("キャンセル");
        input1.getChildren().add(cancelButton1);
        input2.getChildren().add(cancelButton2);
        cancelButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameField1.setText("");
                telNumberField1.setText("");
                seatList1[0][0].cancel();
                label1.setText(label1.getText().substring(0, label1.getText().length() - "予約済み".length()));
            }
        });
        cancelButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameField2.setText("");
                telNumberField2.setText("");
                seatList2[0][0].cancel();
                label2.setText(label2.getText().substring(0, label2.getText().length() - "予約済み".length()));
            }
        });

        for(int i = 0; i < row; i++){
            HBox hBox1 = new HBox(10d);
            HBox hBox2 = new HBox(10d);
            vBox1.getChildren().add(hBox1);
            vBox2.getChildren().add(hBox2);
            for(int j = 0; j < col; j++){
                Button button1 = new Button(String.format("%d-%d", i, j));
                Button button2 = new Button(String.format("%d-%d", i, j));
                int finalI = i;
                int finalJ = j;
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(seatList1[finalI][finalJ].getIsReserved()){
                            label1.setText(String.format("Seat %d-%d予約済み", finalI, finalJ));
                            Subscriber subscriber = seatList1[finalI][finalJ].getSubscriber();
                            nameField1.setText(subscriber.getName());
                            telNumberField1.setText(subscriber.getTelNumber());
                        }else{
                            label1.setText(String.format("Seat %d-%d", finalI, finalJ));
                            nameField1.setText("");
                            telNumberField1.setText("");
                        }
                    }
                });
                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(seatList2[finalI][finalJ].getIsReserved()){
                            label2.setText(String.format("Seat %d-%d予約済み", finalI, finalJ));
                            Subscriber subscriber = seatList2[finalI][finalJ].getSubscriber();
                            nameField2.setText(subscriber.getName());
                            telNumberField2.setText(subscriber.getTelNumber());
                        }else{
                            label2.setText(String.format("Seat %d-%d", finalI, finalJ));
                            nameField2.setText("");
                            telNumberField2.setText("");
                        }
                    }
                });
                hBox1.getChildren().add(button1);
                hBox2.getChildren().add(button2);
                seatList1[i][j] = new Seat(i, j, new Date(), 1);
                seatList2[i][j] = new Seat(i, j, new Date(), 2);
            }
        }
        vBox1.getChildren().add(input1);
        vBox2.getChildren().add(input2);



        Tab tabA = new Tab("01便");
        tabA.setContent(flight1);

        Tab tabB = new Tab("02便");
        tabB.setContent(flight2);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tabA,tabB);


        Pane pane = new Pane();
        pane.getChildren().addAll(tabPane);


        Scene scene = new Scene(pane);


        stage.setTitle("大岡山航空 予約システム");
        stage.setScene(scene);
        stage.setWidth(1500);
        stage.setHeight(500);


        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
