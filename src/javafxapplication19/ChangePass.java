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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 *
 * @author Oishi
 */
public class ChangePass {
    static Stage primarystage = Main.primaryStage;
    public static void passedit(Connection con,String s){
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
       //Button btn = new Button("name");
       scene.getStylesheets().add(ChangePass.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(ChangePass.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");

       Text greet = new Text("Change your password:");
       Text fn = new Text("New Password: ");
       Text ln = new Text("Re-enter new Password: ");
       PasswordField o1 = new PasswordField();
       PasswordField n1 = new PasswordField();
       Label mess = new Label();
       
       greet.setFont(Font.font("System", FontWeight.MEDIUM, 20));
       fn.setFont(Font.font("System", FontWeight.MEDIUM, 17));
       ln.setFont(Font.font("System", FontWeight.MEDIUM, 17));
       
       Button b2 = designs.button("Back");
       Button b3 = designs.button("Change");
       gridpane.add(greet, 0, 0);
       gridpane.add(fn, 0, 2);
       gridpane.add(ln, 0, 3);
       gridpane.add(o1,1,2);
       gridpane.add(n1,1,3);
       gridpane.add(b2,0,5);
       gridpane.add(b3,2,5);
       gridpane.add(mess, 0, 4);
       
       
       b2.setOnAction((ActionEvent event) -> {
          EditDoc.editstuff(con, s);
       });
       b3.setOnAction((ActionEvent event) -> {
          if(o1.getText().length()<=0||n1.getText().length()<=0){
              mess.setText("Please enter the information");
          }
          else if(!o1.getText().equals(n1.getText())){
              mess.setText("Passwords don't match!");
          }else{
              try {
                  String sql = "UPDATE PASS SET PASSWORD ='" + o1.getText() + "' WHERE DOCTOR_NURSE_OFFICER_ID="+s;
                  System.out.println(sql);
                  PreparedStatement se = con.prepareStatement(sql);
                  se.executeUpdate();
                  UpdatedINFO.message(con, s);
              } catch (SQLException ex) {
                  Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
       
       
    }
}
