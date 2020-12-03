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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
/**
 *
 * @author Oishi
 */
public class Inspatient {
    static Stage primarystage = Main.primaryStage;
    public static void refins2(Connection con,String s,String serial,String diag){
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
     scene.getStylesheets().add(Inspatient.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(Inspatient.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");
        //Button btn = new Button("name");
        Text greet = new Text("Medicine(s) you prescribed:");
        Text med = new Text("Medicine name: ");
        TextField medi = new TextField();
        greet.setFont(Font.font("System", FontWeight.MEDIUM, 25));
        med.setFont(Font.font("System", FontWeight.MEDIUM, 20));
       
       Button add = designs.button("ADD MORE");
       Button don = designs.button("DONE");
       Label warn = new Label();
       Label mess = new Label("Must be less than 100 characters");
       warn.setFont(Font.font("System", FontWeight.BOLD, 16));
       mess.setFont(Font.font("System", FontWeight.BOLD, 16));
       gridpane.add(greet,0,0);
       gridpane.add(med,0,1);
       gridpane.add(medi,1,1);
       gridpane.add(add,0,4);
       gridpane.add(don,2,4);
       gridpane.add(warn,0,3);
       gridpane.add(mess,2,1);
       
       add.setOnAction((ActionEvent event) -> {
            if(medi.getText().length()<=0){
                warn.setText("Please press done!");
            }
            else if(medi.getText().length()<100){
                try {
                    String k;
                    k = diag.toUpperCase();
                    String k2 = medi.getText().toUpperCase();
                    String sql = "insert into diagnosis values(diagnosis_seq.nextval,'" + serial + "','" + k + "','" + k2 + "')";
                    System.out.println(sql);
                    PreparedStatement sq = con.prepareStatement(sql);
                    sq.executeUpdate();
                    //Inspatient.refins2(con, s, serial, diag);
                } catch (SQLException ex) {
                    Logger.getLogger(Inspatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                warn.setText("Medicine name must be less than 100 characters!");
            }
            medi.clear();
       });
       don.setOnAction((ActionEvent event) -> {
          if(medi.getText().length()<100){
                try {
                    String sql = "insert into diagnosis values(diagnosis_seq.nextval," + serial + ",'" + diag + "','" + medi.getText() + "')";
                    sql=sql.toUpperCase();
                    System.out.println(sql);
                    PreparedStatement sq = con.prepareStatement(sql);
                    sq.executeUpdate();
                    Docprofile.profile(con, s);
                } catch (SQLException ex) {
                    Logger.getLogger(Inspatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                warn.setText("Medicine name must be less than 100 characters!");
            }
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
}
