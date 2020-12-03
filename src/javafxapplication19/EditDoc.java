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
import javafx.stage.Stage;
/**
 *
 * @author Oishi
 */
public class EditDoc {
    static String idc;
    static Stage primarystage = Main.primaryStage;
    public static void editstuff(Connection con,String s){
        idc=null;
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        scene.getStylesheets().add(EditDoc.class.getResource("animated.css").toExternalForm());
scene.getStylesheets().add(EditDoc.class.getResource("style.css").toExternalForm());        
//Button btn = new Button("name");
        root.setId("panepk");
        //Text sub= new Text("Which detail will you change?");
        
        //sub.setUnderline(true);
        //sub.setFont(Font.font("Castellar", FontWeight.BOLD, 40));
        
        //sub.setFill(Color.WHITE);
       Button btn = designs.button("Change Name");
       Button btn2 = designs.button("Change Password");
       Button btn3 = designs.button("Change Specialization");
       Button btn4 = designs.button("Change Profile Picture");
       
       Button b2 = designs.button("Back");
       //gridpane.add(sub, 0, 0);
       gridpane.add(btn, 0, 3);
       gridpane.add(btn2, 0, 4);
       gridpane.add(btn3, 0, 5);
       gridpane.add(btn4, 0, 6);
       //gridpane.add(message, 1, 1);
       gridpane.add(b2,0,8);
       b2.setOnAction((ActionEvent event) -> {
          Docprofile.profile(con, s);
       });
       btn.setOnAction((ActionEvent event) -> {
           ChangeName.editname(con, s);
       });
       btn2.setOnAction((ActionEvent event) -> {
           ChangePass.passedit(con, s);
       });
       btn3.setOnAction((ActionEvent event) -> {
           ChangeSpec.editspe(con, s);
       });
       btn4.setOnAction((ActionEvent event) -> {
           idc = "update doctor_nurse_officer set docimage =";
           imagedrag.imageInsert2(idc, "profilepic", s);
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
}
