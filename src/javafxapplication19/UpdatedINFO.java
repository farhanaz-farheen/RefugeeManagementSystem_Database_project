/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafxapplication19.Docprofile;
/**
 *
 * @author Oishi
 */
public class UpdatedINFO {
    static Stage primarystage = Main.primaryStage;
    public static void message(Connection con,String s){
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        scene.getStylesheets().add(UpdatedINFO.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(UpdatedINFO.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");
       //Button btn = new Button("name");
       Text gree1 = new Text("SUCCESS!");
       Text greet = new Text("Your profile has been updated!");
       gridpane.setAlignment(Pos.CENTER);
       Button btn = designs.button("Profile");
       Button btn2 = designs.button("Logout");
       gree1.setFont(Font.font("System", FontWeight.BOLD, 50));
       greet.setFont(Font.font("System", FontWeight.BOLD, 30));
       
       gridpane.add(greet,0,1);
       gridpane.add(gree1,0,0);
       gridpane.add(btn,0,3);
       gridpane.add(btn2,1,3);
       btn.setOnAction((ActionEvent event) -> {
          Docprofile.profile(con, s);
       });
       btn2.setOnAction((ActionEvent event) -> {
          Main.homePage();
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
}
