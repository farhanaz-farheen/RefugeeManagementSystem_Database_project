package javafxapplication19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class InsertFunctions {
    static  ArrayList<String> countries;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static ScrollPane root;
    static Label warning;
    public static void selectTable() {
        BufferedReader in = null;
        try {
            countries = new ArrayList<>(250);
            File f = new File("D:\\Lecture slides , pdfs\\2 2\\216\\Project\\countries");
            in = new BufferedReader(new FileReader(f));
            String s;
            while((s=in.readLine())!=null){
                countries.add(s);
            }
            primaryStage = Main.primaryStage;
            Label title= new Label("Select for insertion");
            title.setTextFill(Color.WHITE);
            title.setUnderline(true);
            title.setFont(Font.font("Castellar", FontWeight.BOLD, 40));
            BorderPane root = new BorderPane();
            root.setId("background-one");
            
            
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene1 = new Scene(root, 1100,900);
            scene1.getStylesheets().add(InsertFunctions.class.getResource("css1.css").toExternalForm());
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
                insertIntoRef();
            }); 
            Button vb= new Button("Volunteer");
            vb.setId("com-button");
            //vb.setPrefSize(400, 50);
            gridg.add(vb, 0, 2);
            vb.setOnAction((ActionEvent event) -> {
                volunteer.insert1();
            }); 
            Button eb= new Button("Educational/Occupational Info");
            eb.setId("com-button");
            //eb.setPrefSize(400, 50);
            gridg.add(eb, 0, 3);
            eb.setOnAction((ActionEvent event) -> {
                insertIntoEDU_OCC();
            }); 
            Button dnob= new Button("Doctor/Nurse/Officer");
            dnob.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(dnob, 0, 4);
            dnob.setOnAction((ActionEvent event) -> {
                DocNurseTable.docnurse(Main.conn.con);
            });
            Button cb= new Button("Complaint");
            cb.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(cb, 0, 5);
            cb.setOnAction((ActionEvent event) -> {
                insertIntoCom();
            });
            
            Button back , next;
            back = new Button("");
            back.setId("back-one");
            back.setPrefSize(120, 40);
            back.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            /*
            next = new Button("");
            next.setId("next-one");
            next.setPrefSize(120, 40);
             
            next.setOnAction((ActionEvent event) -> {
                if(drop.getValue()!=null){
                    String str = drop.getValue().toString();
                    if(str.equals("REFUGEE"))   insertIntoRef();
                    else if(str.equals("EDUCATIONAL_OCCUPATIONAL_INFO"))     insertIntoEDU_OCC();
                    else if(str.equals("VOLUNTEER"))    volunteer.insert1();
                    else if(str.equals("COMPLAINT"))    insertIntoCom();
                    else    DocNurseTable.docnurse(Main.conn.con);
                }
            }); 
            
            gridg.add(next, 2, 2);
            */
            
            gridg.add(back, 0, 6);
            root.setCenter(gridg);
            primaryStage.setTitle("Refugee Registration Management");
            primaryStage.setScene(scene1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InsertFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void insertIntoRef(){
        
        refugee.firstnm = null;
        refugee.lastnm = null;
        refugee.countrynm = null;
        refugee.gender = null;
        refugee.bloodGroup = null;
        refugee.year = null;
        refugee.month = null;
        refugee.day = null;
        refugee.passport = null;
        refugee.ethtrisecgrp = null;
        refugee.religion = null;
        refugee.father = null;
        refugee.mother = null;
        refugee.legal = null;
        refugee.spouse = null;
        refugee.insert1(null,null,null,null,null);
    }
    
    public static void insertIntoEDU_OCC() {
        Edu_Occ.refugeeID = null;
        Edu_Occ.institutenm = null;
        Edu_Occ.countrynm = null;
        Edu_Occ.state = null;
        Edu_Occ.city = null;
        Edu_Occ.endyear = null;
        Edu_Occ.degree = null;
        Edu_Occ.specialization = null;
        Edu_Occ.occupation = null;
        Edu_Occ.insert1(null);
    }
    
    public static void insertIntoCom() {
        complaint.comag= null;
        complaint.combyref = null;
        complaint.combyvol = null;
        complaint.misdeed = null;
        complaint.by= null;
        complaint.insert1();
    }
}
