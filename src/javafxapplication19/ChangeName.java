/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Oishi
 */
public class ChangeName {

    static Stage primarystage = Main.primaryStage;

    public static void editname(Connection con, String s) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100, 900);
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));

        //Button btn = new Button("name");
        scene.getStylesheets().add(ChangeName.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(ChangeName.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");
        Text greet = new Text("Enter New Info (* is compulsory):");
        Text fn = new Text("*New First Name: ");
        Text ln = new Text("New Last Name: ");
        Label f = new Label("Must be less than 50 characters");
        Label l = new Label("Must be less than 50 characters");
        TextField f1 = new TextField();
        TextField l1 = new TextField();
        Label mess = new Label();
        CheckBox ccb = new CheckBox("Remove Last Name");
        greet.setFont(Font.font("System", FontWeight.MEDIUM, 20));
        fn.setFont(Font.font("System", FontWeight.MEDIUM, 17));
        ln.setFont(Font.font("System", FontWeight.MEDIUM, 17));
        mess.setTextFill(Color.RED);
        Button b2 = designs.button("Back");
        Button b3 = designs.button("Change");
        gridpane.add(greet, 0, 0);
        gridpane.add(fn, 0, 2);
        gridpane.add(ln, 0, 3);
        gridpane.add(f1, 1, 2);
        gridpane.add(l1, 1, 3);
        gridpane.add(f, 2, 2);
        gridpane.add(l, 2, 3);
        gridpane.add(b2, 0, 6);
        gridpane.add(b3, 2, 6);
        gridpane.add(mess, 0, 5);
        gridpane.add(ccb, 1, 4);

        b2.setOnAction((ActionEvent event) -> {
            EditDoc.editstuff(con, s);
        });
        b3.setOnAction((ActionEvent event) -> {
            if (l1.getText().length() != 0 && ccb.isSelected()) {
                mess.setText("Invalid Input");
            } else {

                if (f1.getText().length() > 50 || l1.getText().length() > 50 || f1.getText().length() <= 0) {
                    mess.setText("Criteria was not fulfilled");
                } else {
                    try {
                        String sql;
                        if (l1.getText().length() <= 0 && !ccb.isSelected()) {
                            sql = "UPDATE DOCTOR_NURSE_OFFICER SET DOCTOR_NURSE_OFFICER_FIRSTNAME ='" + f1.getText().toUpperCase() + "' WHERE DOCTOR_NURSE_OFFICER_ID=" + s;
                        } else if(l1.getText().length()<=0 && ccb.isSelected()){
                            sql = "UPDATE DOCTOR_NURSE_OFFICER SET DOCTOR_NURSE_OFFICER_FIRSTNAME ='" + f1.getText().toUpperCase() + "',DOCTOR_NURSE_OFFICER_LASTNAME=null WHERE DOCTOR_NURSE_OFFICER_ID=" + s;
                        
                        }else {
                            sql = "UPDATE DOCTOR_NURSE_OFFICER SET DOCTOR_NURSE_OFFICER_FIRSTNAME ='" + f1.getText().toUpperCase() + "',DOCTOR_NURSE_OFFICER_LASTNAME='" + l1.getText().toUpperCase() + "' WHERE DOCTOR_NURSE_OFFICER_ID=" + s;
                        }
                        System.out.println(sql);
                        PreparedStatement se = con.prepareStatement(sql);
                        se.executeUpdate();
                        UpdatedINFO.message(con, s);
                    } catch (SQLException ex) {
                        Logger.getLogger(ChangeName.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
}
