/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxapplication19.Main.homePage;

/**
 *
 * @author Oishi
 */
public class Docprofile {
    static Stage primaryStage = Main.primaryStage;
    public static void profile(Connection con, String s) {
        try {
            BorderPane borderPane = new BorderPane();
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene = new Scene(borderPane, 1100,900);
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(20);
            grid.getStylesheets().addAll(Docprofile.class.getResource("style.css").toExternalForm());
            grid.setId("panepk");
            Text greet = new Text("WELCOME!\n");
            greet.setFill(Color.WHITE);
            greet.setUnderline(true);
            String sql = "select (DOCTOR_NURSE_OFFICER_FIRSTNAME || ' ' ||DOCTOR_NURSE_OFFICER_LASTNAME) NM,SPECIALIZATION,DOCIMAGE DMG from DOCTOR_NURSE_OFFICER where DOCTOR_NURSE_OFFICER_ID="+s;
            //PreparedStatement st = con.prepareStatement(sql);
            //String str = "abcd";
            System.out.println(sql);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //ResultSet r = st.executeQuery();
            String chec = null;
            String che1 = null;
            String dmg1=null;
            while(rs.next()){
                try {
                    chec=rs.getString("NM");
                    che1=rs.getString("SPECIALIZATION");
                    dmg1 = rs.getString("DMG");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Docprofile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Text id = new Text("Your ID: " + s);
            Text name = new Text("Name: " + chec);
            Text specialization = new Text("Specialization: " + che1);
            
            id.setFill(Color.WHITESMOKE);
            name.setFill(Color.WHITESMOKE);
            specialization.setFill(Color.WHITESMOKE);
            
            Image image;
            if(dmg1==null){
                image= new Image("/images/guest-user.jpg");
            }
            else{
                image= new Image("File:"+dmg1);
            }
            
            //Image image = new Image("File:"+imgUrl);
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            iv1.setFitHeight(200);
            iv1.setFitWidth(200);
            iv1.setPreserveRatio(true);
            
            
            
            grid.add(greet,0,0);
            grid.add(iv1,1,0);
            //borderPane.setTop(greet);
            grid.add(id,0,1);
            grid.add(name,0,2);
            grid.add(specialization,0,3);
            greet.setFont(Font.font("System", FontWeight.BOLD, 70));
            id.setFont(Font.font("System", FontWeight.BOLD, 30));
            name.setFont(Font.font("System", FontWeight.BOLD, 30));
            specialization.setFont(Font.font("System", FontWeight.BOLD, 30));
            ToolBar toolbar = new ToolBar();
            HBox statusbar = new HBox();
            Node appContent = new ListView();
            ObservableList<String> items = FXCollections.observableArrayList("Edit Profile","See Patients","See All","Insert New","Logout");
            ListView<String> list = new ListView<>(items);
            list.getStylesheets().addAll(Docprofile.class.getResource("list.css").toExternalForm());
            //borderPane.set(toolbar);
            list.getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        
                        public void changed(
                                ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                            // change the label text value to the newly selected
                            // item.
                            //label.setText("You Selected " + newValue);
                            if(newValue.equals("Edit Profile")){
                                EditDoc.editstuff(con,s);
                            }else if(newValue.equals("See Patients")){
                                ShowSomePAT.someshow(con,s);
                            }else if(newValue.equals("See All")){
                                String sql2 = "select (R.FIRST_NAME|| ' ' || R.LAST_NAME) PatientsName,R.GENDER gender,R.BLOODGROUP bloodgroup,D.DIAGNOSIS_NAME diagnosis,D.MEDICINE_NAME medicine\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"LEFT OUTER JOIN DIAGNOSIS D\n" +
"ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID=" + s;
            Commonquery.showRes(con,sql2,s);
                            }else if(newValue.equals("Insert New")){
                                InsertPatient.refins(con, s);
                            }else if(newValue.equals("Logout")){
                                homePage();
                            }
                        }
                    });
            
            //listViewPanel.getChildren().addAll(m_listView, label);
            //root.getChildren().addAll(listViewPanel);
            borderPane.setCenter(grid);
            borderPane.setLeft(list);
            borderPane.setBottom(statusbar);
            
            //borderpane.setCenter();
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(Docprofile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
