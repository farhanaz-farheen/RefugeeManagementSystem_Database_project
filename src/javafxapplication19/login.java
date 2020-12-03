/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Oishi
 */
public class login {
    static Stage primarystage = Main.primaryStage;
    public static void fields(Connection con){
        BorderPane root = new BorderPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Scene scene = new Scene(root,1100,900);
       //Scene scene = new Scene(root);
       //primarystage.setFullScreen(true);
       scene.getStylesheets().add(login.class.getResource("style.css").toExternalForm());
        //root.setId("pane");
        scene.getStylesheets().add(login.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
       gridpane.setHgap(10);
       gridpane.setVgap(20);
       gridpane.setPadding(new Insets(25, 25, 25, 25));
       //Button btn = new Button("name");
       Text lab = new Text("Enter password:");
       Label message = new Label();
       PasswordField pass = new PasswordField();
       Button btn2 = designs.button("Login");
       Button b2 = designs.button("Back");
       
       lab.setFont(Font.font("System", FontWeight.BOLD, 30));
       message.setFont(Font.font("System", FontWeight.BOLD, 25));
       message.setTextFill(Color.RED);
       //btn2.setMaxWidth(80);
       b2.setMaxWidth(60);
       gridpane.add(lab, 0, 0);
       gridpane.add(pass, 0, 1);
       gridpane.add(b2, 0, 3);
       gridpane.add(message, 0, 2,3,1);
       gridpane.add(btn2,2,3);
       b2.setOnAction((ActionEvent event) -> {
          Main.homePage();
       });
       btn2.setOnAction((ActionEvent event) -> {
          String pas = pass.getText();
          String str = "abcd";
          if(pas.equals(str)){
              Main.AuthorityHomePage();
          }else{
              message.setTextFill(Color.FIREBRICK);
              message.setText("Incorrect password");
          }
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
}
