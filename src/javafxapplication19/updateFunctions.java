package javafxapplication19;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class updateFunctions {
    static Stage primaryStage;
    public static void updatePage() {
        primaryStage = Main.primaryStage;
            Label title= new Label("Select for Update");
            title.setTextFill(Color.WHITE);
            title.setUnderline(true);
            title.setFont(Font.font("Castellar", FontWeight.BOLD, 40));
            BorderPane root = new BorderPane();
            root.setId("background-one");
            
            
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene1 = new Scene(root, 1100, 900);
            scene1.getStylesheets().add(updateFunctions.class.getResource("css1.css").toExternalForm());
            GridPane gridg = new GridPane();
            gridg.add(title, 0, 0);
            gridg.setAlignment(Pos.CENTER);
            gridg.setHgap(10);
            gridg.setVgap(40);
            gridg.setPadding(new Insets(25, 25, 25, 25));
            
            Button rb= new Button("Refugee");
            rb.setId("com-button");
            //rb.setPrefSize(400, 50);
            gridg.add(rb, 0, 1);
            rb.setOnAction((ActionEvent event) -> {
                refugeeUpdate.refugeeID= null;
                    refugeeUpdate.firstnm = null;
                    refugeeUpdate.lastnm = null;
                    refugeeUpdate.countrynm = null;
                    refugeeUpdate.gender = null;
                    refugeeUpdate.bloodGroup = null;
                    refugeeUpdate.year = null;
                    refugeeUpdate.month = null;
                    refugeeUpdate.day = null;
                    refugeeUpdate.passport = null;
                    refugeeUpdate.ethtrisecgrp = null;
                    refugeeUpdate.religion = null;
                    refugeeUpdate.father = null;
                    refugeeUpdate.mother = null;
                    refugeeUpdate.legal = null;
                    refugeeUpdate.spouse = null;
                    refugeeUpdate.fn = false;
                    refugeeUpdate.ln = false;
                    refugeeUpdate.cn = false;
                    refugeeUpdate.gn = false;
                    refugeeUpdate.bgp = false;
                    refugeeUpdate.dt = false;
                    refugeeUpdate.pp = false;
                    refugeeUpdate.etsg = false;
                    refugeeUpdate.rn = false;
                    refugeeUpdate.fi = false;
                    refugeeUpdate.mi = false;
                    refugeeUpdate.si = false;
                    refugeeUpdate.lgi = false;
                    refugeeUpdate.update(null);
                
            }); 
            Button vb= new Button("Volunteer");
            vb.setId("com-button");
            //vb.setPrefSize(400, 50);
            gridg.add(vb, 0, 2);
            vb.setOnAction((ActionEvent event) -> {
               
                    volunteerUpdate.volid=null;
                    volunteerUpdate.firstnm=null;
                    volunteerUpdate.lastnm=null;
                    volunteerUpdate.fn= false;
                    volunteerUpdate.ln = false;
                    volunteerUpdate.update();
                    
            }); 
            Button eb= new Button("Educational/Occupational Info");
            eb.setId("com-button");
            //eb.setPrefSize(400, 50);
            gridg.add(eb, 0, 3);
            eb.setOnAction((ActionEvent event) -> {
                eduoccupdate.refugeeID = null;
                    eduoccupdate.institutenm = null;
                    eduoccupdate.endyear = null;
                    eduoccupdate.degree = null;
                    eduoccupdate.specialization = null;
                    eduoccupdate.occupation = null;
                    eduoccupdate.instituteID= null;
                    eduoccupdate.chinstituteID=null;
                    eduoccupdate.countrynm=null;
                    eduoccupdate.state=null;
                    eduoccupdate.city= null;
                    eduoccupdate.in= false;
                    eduoccupdate.en= false;
                    eduoccupdate.dg= false;
                    eduoccupdate.sp= false;
                    eduoccupdate.oc= false;
                    eduoccupdate.update();
            }); 
            
            Button back , next;
            back = new Button("");
            back.setId("back-one");
            back.setPrefSize(120, 40);
            back.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            
            gridg.add(back, 0, 4);
            root.setCenter(gridg);
            primaryStage.setScene(scene1);
    }
}
