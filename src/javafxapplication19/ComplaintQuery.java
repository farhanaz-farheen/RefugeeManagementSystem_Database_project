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
public class ComplaintQuery {
    static Stage primarystage = Main.primaryStage;
     static String crime;
     static String complainvolfn;
     static String complainvolln;
     static String complainreffn;
     static String complainrefln;
     static String againstfn;
     static String againstln;
     
     static int a1;
     static int a2;
     static int a3;
     static int a4;
     static int a5;
     static int a6;
     static int a7;
     
    public static void querycomp(Connection con){
     crime=null;
     complainvolfn=null;
     complainreffn=null;
     againstfn=null;
     complainvolln=null;
     complainrefln=null;
     againstln=null;
     
     a1=0;
     a2=0;
     a3=0;
     a4=0;
     a5=0;
     a6=0;
     a7=0;
     
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       scene.getStylesheets().add(ComplaintQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(ComplaintQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
       //Button btn = new Button("");
       Text greet = new Text("Enter details");
       Text misdeed = new Text("Misdeed Name");
       Text accusorvfn = new Text("First Name of Accusor (Volunteer)");
       Text accusorvln = new Text("Last Name of Accusor (Volunteer)");
       Text accusorrfn = new Text("First Name of Accusor (Refugee)");
       Text accusorrln = new Text("Last Name of Accusor (Refugee)");
       Text accusedfn = new Text("First Name of Accused");
       Text accusedln = new Text("Last Name of Accused");
       
       greet.setFont(Font.font("System", FontWeight.BOLD, 25));
       misdeed.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusorvfn.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusorvln.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusorrfn.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusorrln.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusedfn.setFont(Font.font("System", FontWeight.BOLD, 20));
       accusedln.setFont(Font.font("System", FontWeight.BOLD, 20));
       
       TextField mis1 = new TextField();
       TextField comvfn1 = new TextField();
       TextField comvln1 = new TextField();
       TextField comrf1 = new TextField();
       TextField comrl1 = new TextField();
       TextField agaf1 = new TextField();
       TextField agal1 = new TextField();
       
       
       Button btn = new Button("ADD");
       Button btn1 = new Button("ADD");
       Button btn2 = new Button("ADD");
       Button btn3 = new Button("ADD");
       Button btn4 = new Button("ADD");
       Button btn5 = new Button("ADD");
       Button btn6 = new Button("ADD");
       
       Button back = new Button("BACK");

       Button done = new Button("DONE");
       gridpane.add(greet,0,0);
       gridpane.add(misdeed,0,2);
       gridpane.add(accusorvfn,0,3);
       gridpane.add(accusorvln,0,4);
       gridpane.add(accusorrfn,0,5);
       gridpane.add(accusorrln,0,6);
       gridpane.add(accusedfn,0,7);
       gridpane.add(accusedln,0,8);
       
       gridpane.add(mis1,1,2);
       gridpane.add(comvfn1,1,3);
       gridpane.add(comvln1,1,4);
       gridpane.add(comrf1,1,5);
       gridpane.add(comrl1,1,6);
       gridpane.add(agaf1,1,7);
       gridpane.add(agal1,1,8);
       
             
       
       gridpane.add(btn,2,2);
       gridpane.add(btn1,2,3);
       gridpane.add(btn2,2,4);
       gridpane.add(btn3,2,5);
       gridpane.add(btn4,2,6);
       gridpane.add(btn5,2,7);
       gridpane.add(btn6,2,8);
       
       gridpane.add(done,2,10);
       gridpane.add(back,0,10);
       Label warn1 = new Label();
       Label warn2 = new Label();
       Label warn3 = new Label();
       Label warn4 = new Label();
       Label warn5 = new Label();
       Label warn6 = new Label();
       Label warn7 = new Label();
       
       warn1.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn2.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn3.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn4.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn5.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn6.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn7.setFont(Font.font("System", FontWeight.BOLD, 18));
       
       gridpane.add(warn1,3,2);
       gridpane.add(warn2,3,3);
       gridpane.add(warn3,3,4);
       gridpane.add(warn4,3,5);
       gridpane.add(warn5,3,6);
       gridpane.add(warn6,3,7);
       gridpane.add(warn7,3,8);
       
       
       //static String docfirst=null;
       back.setOnAction((ActionEvent event) -> { 
           queryFunctions.selectTable();
       });
       btn.setOnAction((ActionEvent event) -> {          
          if(mis1.getText().length()<=0){
              warn1.setText("Please enter something!");
          }else{
              if(a1==0){
                  crime = "'" + mis1.getText().toUpperCase() + "'";
                  a1++;
              }else {
                  crime = crime + ",'" + mis1.getText().toUpperCase() + "'";
                  //a1++;
              }
          }
          mis1.clear();
       });
       btn1.setOnAction((ActionEvent event) -> {          
          if(comvfn1.getText().length()<=0){
              warn2.setText("Please enter something!");
          }else{
              if(a2==0){
                  complainvolfn = "'" + comvfn1.getText().toUpperCase() + "'";
                  a2++;
              }else {
                  complainvolfn = complainvolfn + ",'" + comvfn1.getText().toUpperCase() + "'";
              }
          }
          comvfn1.clear();
       });
       btn2.setOnAction((ActionEvent event) -> {          
          if(comvln1.getText().length()<=0){
              warn3.setText("Please enter something!");
          }else{
              if(a3==0){
                  complainvolln = "'" + comvln1.getText().toUpperCase() + "'";
                  a3++;
              }else {
                  complainvolln = complainvolln + ",'" + comvln1.getText().toUpperCase() + "'";
              }
          }
          comvln1.clear();
       });
       btn3.setOnAction((ActionEvent event) -> {          
          if(comrf1.getText().length()<=0){
              warn4.setText("Please enter something!");
          }else{
              //docspe = docspe + "'" + spe1.getText() + "',";
              if(a4==0){
                  complainreffn = "'" + comrf1.getText().toUpperCase() + "'";
                  a4++;
              }else {
                  complainreffn = complainreffn + ",'" + comrf1.getText().toUpperCase() + "'";
              }
          }
          comrf1.clear();
       });
       btn4.setOnAction((ActionEvent event) -> {          
          if(comrl1.getText().length()<=0){
              warn5.setText("Please enter something!");
          }else{
              //docspe = docspe + "'" + spe1.getText() + "',";
              if(a5==0){
                  complainrefln = "'" + comrl1.getText().toUpperCase() + "'";
                  a5++;
              }else {
                  complainrefln = complainrefln + ",'" + comrl1.getText().toUpperCase() + "'";
              }
          }
          comrl1.clear();
       });
       btn5.setOnAction((ActionEvent event) -> {          
          if(agaf1.getText().length()<=0){
              warn6.setText("Please enter something!");
          }else{
              if(a6==0){
                  againstfn = "'" + agaf1.getText().toUpperCase() + "'";
                  a6++;
              }else {
                  againstfn = againstfn + ",'" + agaf1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          agaf1.clear();
       });
       btn6.setOnAction((ActionEvent event) -> {          
          if(agal1.getText().length()<=0){
              warn7.setText("Please enter something!");
          }else{
              if(a7==0){
                  againstln = "'" + agal1.getText().toUpperCase() + "'";
                  a7++;
              }else {
                  againstln = againstln + ",'" + agal1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          agal1.clear();
       });
       
       
       
       done.setOnAction((ActionEvent event) -> {          
          String crime1=null;
          String complainvolfn1=null;
          String complainreffn1=null;
          String againstfn1=null;
          String complainvolln1=null;
          String complainrefln1=null;
          String againstln1=null;
          
          int i=0;
          String finalwhere;
          finalwhere=null;
          if(crime!=null){
              crime1 = " WHERE C.MISDEED IN (" + crime + ")";
              System.out.println("here1");
              i++;
              finalwhere=crime1;
          }
          if(complainvolfn!=null){
              if(i>0){
                  complainvolfn1 = " OR V.VOLUNTEER_FIRSTNAME IN (" + complainvolfn + ")";
              }else{
                 complainvolfn1=" WHERE V.VOLUNTEER_FIRSTNAME IN (" + complainvolfn + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=complainvolfn1;}
              else finalwhere=finalwhere+complainvolfn1;
              i++;
          }
          if(complainvolln!=null){
              if(i>0){
                  complainvolln1 = " OR V.VOLUNTEER_LASTNAME IN (" + complainvolln + ")";
              }else{
                complainvolln1=" WHERE V.VOLUNTEER_LASTNAME IN (" + complainvolln + ")";  
              }
              
              //i++;
              if(i==0){finalwhere=complainvolln1;}
              else finalwhere=finalwhere+complainvolln1;
              System.out.println("here3");
              i++;
          }
          if(complainreffn!=null){
              if(i>0){
                  complainreffn1 = " OR R.FIRST_NAME IN (" + complainreffn + ")";
              }else{
                 complainreffn1=" WHERE R.FIRST_NAME IN (" + complainreffn + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=complainreffn1;}
              else finalwhere=finalwhere+complainreffn1;
              i++;
          }
          if(complainrefln!=null){
              if(i>0){
                  complainrefln1 = " OR R.LAST_NAME IN (" + complainrefln + ")";
              }else{
                 complainrefln1=" WHERE R.LAST_NAME IN (" + complainrefln + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=complainrefln1;}
              else finalwhere=finalwhere+complainrefln1;
              i++;
          }
          if(againstfn!=null){
              if(i>0){
                  againstfn1 = " OR COM.FIRST_NAME IN (" + againstfn + ")";
              }else{
                  againstfn1=" WHERE COM.FIRST_NAME IN (" + againstfn + ")";
              }
              
              //i++;
              if(i==0){finalwhere=againstfn1;}
              else finalwhere=finalwhere+againstfn1;
              System.out.println("here4");
              i++;
          }
          if(againstln!=null){
              if(i>0){
                  againstln1 = " OR COM.LAST_NAME IN (" + againstln + ")";
              }else{
                  againstln1=" WHERE COM.LAST_NAME IN (" + againstln + ")";
              }
              
              //i++;
              if(i==0){finalwhere=againstln1;}
              else finalwhere=finalwhere+againstln1;
              System.out.println("here4");
              i++;
          }
          
          //String finalwhere = docfirst1+doclast1+docspe1+reffirst1+reflast1+refdiag1+refmedi1;
          System.out.println(finalwhere);
          String sql = "select C.MISDEED Misdeed,(V.VOLUNTEER_FIRSTNAME||' '||V.VOLUNTEER_LASTNAME) AccusorVolunteer,(R.FIRST_NAME||' '||R.LAST_NAME) AccusorRefugee,(COM.FIRST_NAME||' '||COM.LAST_NAME) ComplaintAgainst\n" +
"FROM COMPLAINT C JOIN REFUGEE COM\n" +
"ON (C.COMPLAINT_AGAINST=COM.REFUGEE_ID)\n" +
"LEFT OUTER JOIN VOLUNTEER V\n" +
"ON (C.COMPLAINT_BY_VOLUNTEER=V.VOLUNTEER_ID)\n" +
"LEFT OUTER JOIN REFUGEE R\n" +
"ON (C.COMPLAINT_BY_REFUGEE=R.REFUGEE_ID)";
          if(finalwhere!=null){
              sql=sql+finalwhere;
          }
           System.out.println(sql);
          queryFunctions.showRes(sql);
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
}
