package javafxapplication19;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
        next.setOnAction((ActionEvent event) -> {
            if(tabChoice.getValue()!=null){
                String s= tabChoice.getValue();
                if(s.equals("REFUGEE")){
                    refugeeQuery.refid = null;
                    refugeeQuery.firstnm = null;
                    refugeeQuery.lastnm = null;
                    refugeeQuery.countrynm = null;
                    refugeeQuery.gender = null;
                    refugeeQuery.bloodGroup = null;
                    refugeeQuery.year = null;
                    refugeeQuery.month = null;
                    refugeeQuery.day = null;
                    refugeeQuery.passport = null;
                    refugeeQuery.ethtrisecgrp = null;
                    refugeeQuery.religion = null;
                    refugeeQuery.father = null;
                    refugeeQuery.mother = null;
                    refugeeQuery.legal = null;
                    refugeeQuery.spouse = null;
                    refugeeQuery.query1();
                }
                else if(s.equals("VOLUNTEER")){
                    volunteer.volid=null;
                    volunteer.firstnm=null;
                    volunteer.lastnm=null;
                    volunteer.query1();
                }
                else if(s.equals("INSTITUTE")){
                    instituteQuery.institutenm = null;
                    instituteQuery.city = null;
                    instituteQuery.state = null;
                    instituteQuery.countrynm = null;
                    instituteQuery.query1();
                }else if(s.equals("DOCTOR_NURSE_OFFICER")){
                    DocQuery.querydoc(Main.conn.con);
                }else if(s.equals("COMPLAINT")){
                    ComplaintQuery.querycomp(Main.conn.con);
                }else if(s.equals("EDUCATIONAL_OCCUPATIONAL_INFO")){
                    EduQuery.queryedu(Main.conn.con);
                }
            }
                
        });*/

public class queryFunctions {
    static  ArrayList<String> tables;
    public  static Stage primaryStage ;
    public static void selectTable() {
        primaryStage = Main.primaryStage;
            Label title= new Label("Select for Query");
            title.setTextFill(Color.WHITE);
            title.setUnderline(true);
            title.setFont(Font.font("Castellar", FontWeight.BOLD, 40));
            BorderPane root = new BorderPane();
            root.setId("background-one");
            
            
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene1 = new Scene(root, 1100,900);
            scene1.getStylesheets().add(queryFunctions.class.getResource("css1.css").toExternalForm());
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
                refugeeQuery.refid = null;
                refugeeQuery.firstnm = null;
                refugeeQuery.lastnm = null;
                refugeeQuery.countrynm = null;
                refugeeQuery.gender = null;
                refugeeQuery.bloodGroup = null;
                refugeeQuery.year = null;
                refugeeQuery.month = null;
                refugeeQuery.day = null;
                refugeeQuery.passport = null;
                refugeeQuery.ethtrisecgrp = null;
                refugeeQuery.religion = null;
                refugeeQuery.father = null;
                refugeeQuery.mother = null;
                refugeeQuery.legal = null;
                refugeeQuery.spouse = null;
                refugeeQuery.query1();
            }); 
            Button vb= new Button("Volunteer");
            vb.setId("com-button");
            //vb.setPrefSize(400, 50);
            gridg.add(vb, 0, 2);
            vb.setOnAction((ActionEvent event) -> {
                volunteer.volid=null;
                volunteer.firstnm=null;
                volunteer.lastnm=null;
                volunteer.query1();
            }); 
            Button ib= new Button("Institute");
            ib.setId("com-button");
            //eb.setPrefSize(400, 50);
            gridg.add(ib, 0, 3);
            ib.setOnAction((ActionEvent event) -> {
                instituteQuery.institutenm = null;
                instituteQuery.city = null;
                instituteQuery.state = null;
                instituteQuery.countrynm = null;
                instituteQuery.query1();
            }); 
            Button eb= new Button("Educational/Occupational Info");
            eb.setId("com-button");
            //eb.setPrefSize(400, 50);
            gridg.add(eb, 0, 4);
            eb.setOnAction((ActionEvent event) -> {
                EduQuery.queryedu(Main.conn.con);
            }); 
            Button dnob= new Button("Doctor/Nurse/Officer");
            dnob.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(dnob, 0, 5);
            dnob.setOnAction((ActionEvent event) -> {
                DocQuery.querydoc(Main.conn.con);
            });
            Button cb= new Button("Complaint");
            cb.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(cb, 0, 6);
            cb.setOnAction((ActionEvent event) -> {
                ComplaintQuery.querycomp(Main.conn.con);
            });
            Button ab= new Button("Checkup");
            ab.setId("com-button");
            //vb.setPrefSize(400, 50);
            gridg.add(ab, 0, 7);
            ab.setOnAction((ActionEvent event) -> {
                DiagnosisQuery.querydoc(Main.conn.con);
            }); 
            Button back , next;
            back = new Button("");
            back.setId("back-one");
            back.setPrefSize(120, 40);
            back.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            
            gridg.add(back, 0, 8);
            root.setCenter(gridg);
            
            primaryStage.setScene(scene1);
    }
    
    public static void showRes(String sql) {
        try {
            primaryStage= Main.primaryStage;
            VBox vb= new VBox();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(30));
            grid.setPadding(new Insets(30, 30, 30, 30));
            PreparedStatement   st= Main.conn.con.prepareStatement(sql.toUpperCase());
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            Label title = new Label("Query Results:");
            title.setUnderline(true);
            title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
            title.setTextFill(Color.WHITESMOKE);
            vb.getChildren().add(title);
            vb.setSpacing(10);
            Text []colnm= new Text[totcol];
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol;i++){
                StackPane sp= new StackPane();
                sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                colnm[i]= new Text(" "+rsm.getColumnName(i+1)+" ");
                //colnm[i].setId("labelgrid-border");
                //colnm[i].setPrefSize(300, 50);
                colnm[i].setFont(Font.font("Arial", FontWeight.BOLD, 20));
                colnm[i].setFill(Color.WHITESMOKE);
                grid.add(sp, i, 0);
                sp.getChildren().add(colnm[i]);
                sp.setId("border-two");
                //grid.setHalignment(colnm[i], HPos.CENTER);
                //grid.setValignment(colnm[i], VPos.CENTER);
            }
            int i=1;
            while(rs.next()){
                grid.getRowConstraints().add(new RowConstraints(50));
                for(int j=0;j<totcol;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" ");
                    sp.setId("border-one");
                    //templ.setPrefSize(300, 50);
                    //templ.setId("labelgrid-border");
                    templ.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                    //grid.setFillWidth(templ, true);
                    //grid.setFillHeight(templ, true);
                    grid.setFillWidth(sp, true);
                    grid.setFillHeight(sp, true);
                    if(i%2==0){
                        sp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                    sp.getChildren().add(templ);
                    grid.add(sp,j,i);/*
                    grid.setHalignment(sp, HPos.CENTER);
                    grid.setValignment(sp, VPos.CENTER);*/
                } 
                i++;
            }
            if(i==1){
                StackPane sp= new StackPane();
                sp.setId("border-one");
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                grid.getRowConstraints().add(new RowConstraints(50));
                Label nodata= new Label("No Data Found");
                nodata.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                sp.getChildren().add(nodata);
                grid.add(sp, 0, 1,totcol+1,1);
                grid.setHalignment(nodata, HPos.CENTER);
            }
            grid.setSnapToPixel(false);
            Button next = new Button("");
            next.setId("next-one");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            next.setPrefSize(120, 40);
            vb.setAlignment(Pos.CENTER);
            ScrollPane root1 = new ScrollPane(grid);
            grid.setAlignment(Pos.CENTER);
            vb.getChildren().addAll(root1,next);
            BorderPane root= new BorderPane();
            root.setCenter(vb);
            root.setId("background-one");
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(queryFunctions.class.getResource("css1.css").toExternalForm());
            
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(queryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void showRes2(String sql) {
        try {
            primaryStage= Main.primaryStage;
            VBox vb= new VBox();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(30));
            grid.setPadding(new Insets(30, 30, 30, 30));
            PreparedStatement   st= Main.conn.con.prepareStatement(sql.toUpperCase());
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            Label title = new Label("Query Results:");
            title.setUnderline(true);
            title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
            title.setTextFill(Color.WHITESMOKE);
            vb.getChildren().add(title);
            vb.setSpacing(10);
            Text []colnm= new Text[totcol];
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol;i++){
                StackPane sp= new StackPane();
                sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                colnm[i]= new Text(" "+rsm.getColumnName(i+1)+" ");
                //colnm[i].setId("labelgrid-border");
                //colnm[i].setPrefSize(300, 50);
                colnm[i].setFont(Font.font("Arial", FontWeight.BOLD, 20));
                colnm[i].setFill(Color.WHITESMOKE);
                grid.add(sp, i, 0);
                sp.getChildren().add(colnm[i]);
                sp.setId("border-two");
                grid.setHalignment(colnm[i], HPos.CENTER);
                grid.setValignment(colnm[i], VPos.CENTER);
            }
            int i=1;
            while(rs.next()){
                grid.getRowConstraints().add(new RowConstraints(50));
                for(int j=0;j<totcol;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" ");
                    sp.setId("border-one");
                    //templ.setPrefSize(300, 50);
                    //templ.setId("labelgrid-border");
                    templ.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                    //grid.setFillWidth(templ, true);
                    //grid.setFillHeight(templ, true);
                    grid.setFillWidth(sp, true);
                    grid.setFillHeight(sp, true);
                    if(i%2==0){
                        sp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                    sp.getChildren().add(templ);
                    grid.add(sp,j,i);/*
                    grid.setHalignment(sp, HPos.CENTER);
                    grid.setValignment(sp, VPos.CENTER);*/
                } 
                i++;
            }
            if(i==1){
                StackPane sp= new StackPane();
                sp.setId("border-one");
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                grid.getRowConstraints().add(new RowConstraints(50));
                Label nodata= new Label("No Data Found");
                nodata.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                sp.getChildren().add(nodata);
                grid.add(sp, 0, 1,totcol+1,1);
                grid.setHalignment(nodata, HPos.CENTER);
            }
            grid.setSnapToPixel(false);
            Button next = new Button("");
            next.setId("next-one");
            next.setPrefSize(120, 40);
            next.setOnAction((ActionEvent event) -> {
                GeneralQuery.querystuff(Main.conn.con);
            });
            vb.setAlignment(Pos.CENTER);
            ScrollPane root1 = new ScrollPane(grid);
            grid.setAlignment(Pos.CENTER);
            vb.getChildren().addAll(root1,next);
            BorderPane root= new BorderPane();
            root.setCenter(vb);
            root.setId("background-one");
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(queryFunctions.class.getResource("css1.css").toExternalForm());
            
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(queryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
