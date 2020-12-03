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
public class EduQuery {
    static Stage primarystage = Main.primaryStage;
     static String reffirst;
     static String reflast;
     static String insname;
     static String cityname;
     static String statename;
     static String countryname;
     static String endyear;
     static String degree;
     static String occupation;
     static String specialization;
     
     static int a1;
     static int a2;
     static int a3;
     static int a4;
     static int a5;
     static int a6;
     static int a7;
     static int a8;
     static int a9;
     static int a10;
    
     
    public static void queryedu(Connection con){
     reffirst=null;
     reflast=null;
     insname=null;
     cityname=null;
     statename=null;
     countryname=null;
     endyear=null;
     degree=null;
     occupation=null;
     specialization=null;
     
     
     a1=0;
     a2=0;
     a3=0;
     a4=0;
     a5=0;
     a6=0;
     a7=0;
     a8=0;
     a9=0;
     a10=0;
     
     
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       scene.getStylesheets().add(EduQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(EduQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
       //Button btn = new Button("");
       Text greet = new Text("Enter details");
       greet.setFont(Font.font("System", FontWeight.BOLD, 30));
       Text refugeefirst = new Text("First Name of Refugee");
       Text refugeelast = new Text("Last Name of Refugee");
       Text institute = new Text("Name of Institute");
       Text city = new Text("City");
       Text state = new Text("State/Province");
       Text country = new Text("Country");
       Text end = new Text("Ending year");
       Text deg = new Text("Degree");
       Text spe = new Text("Specialization(Not for children)");
       Text occ = new Text("Occupation");
       
       refugeefirst.setFont(Font.font("System", FontWeight.BOLD, 25));
       refugeelast.setFont(Font.font("System", FontWeight.BOLD, 25));
       institute.setFont(Font.font("System", FontWeight.BOLD, 25));
       city.setFont(Font.font("System", FontWeight.BOLD, 25));
       state.setFont(Font.font("System", FontWeight.BOLD, 25));
       country.setFont(Font.font("System", FontWeight.BOLD, 25));
       end.setFont(Font.font("System", FontWeight.BOLD, 25));
       deg.setFont(Font.font("System", FontWeight.BOLD, 25));
       spe.setFont(Font.font("System", FontWeight.BOLD, 25));
       occ.setFont(Font.font("System", FontWeight.BOLD, 25));
       
       
       
       TextField reffn1 = new TextField();
       TextField refln1 = new TextField();
       TextField ins1 = new TextField();
       TextField city1 = new TextField();
       TextField state1 = new TextField();
       TextField country1 = new TextField();
       TextField end1 = new TextField();
       TextField deg1 = new TextField();
       TextField spe1 = new TextField();
       TextField occ1 = new TextField();
              
       
       Button btn = new Button("ADD");
       Button btn1 = new Button("ADD");
       Button btn2 = new Button("ADD");
       Button btn3 = new Button("ADD");
       Button btn4 = new Button("ADD");
       Button btn5 = new Button("ADD");
       Button btn6 = new Button("ADD");
       Button btn7 = new Button("ADD");
       Button btn8 = new Button("ADD");
       Button btn9 = new Button("ADD");
       

       Button done = new Button("DONE");
       Button back = new Button("BACK");
       gridpane.add(greet,0,0);
       gridpane.add(refugeefirst,0,2);
       gridpane.add(refugeelast,0,3);
       gridpane.add(institute,0,4);
       gridpane.add(city,0,5);
       gridpane.add(state,0,6);
       gridpane.add(country,0,7);
       gridpane.add(end,0,8);
       gridpane.add(deg,0,9);
       gridpane.add(spe,0,10);
       gridpane.add(occ,0,11);
       
       gridpane.add(reffn1,1,2);
       gridpane.add(refln1,1,3);
       gridpane.add(ins1,1,4);
       gridpane.add(city1,1,5);
       gridpane.add(state1,1,6);
       gridpane.add(country1,1,7);     
       gridpane.add(end1,1,8);
       gridpane.add(deg1,1,9);
       gridpane.add(spe1,1,10);
       gridpane.add(occ1,1,11);
       
       gridpane.add(btn,2,2);
       gridpane.add(btn1,2,3);
       gridpane.add(btn2,2,4);
       gridpane.add(btn3,2,5);
       gridpane.add(btn4,2,6);
       gridpane.add(btn5,2,7);
       gridpane.add(btn6,2,8);
       gridpane.add(btn7,2,9);
       gridpane.add(btn8,2,10);
       gridpane.add(btn9,2,11);
       
       
       gridpane.add(done,2,13);
       
       gridpane.add(back,0,13);
       
       Label warn1 = new Label();
       Label warn2 = new Label();
       Label warn3 = new Label();
       Label warn4 = new Label();
       Label warn5 = new Label();
       Label warn6 = new Label();
       Label warn7 = new Label();
       Label warn8 = new Label();
       Label warn9 = new Label();
       Label warn10 = new Label();
       
       warn1.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn2.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn3.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn4.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn5.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn6.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn7.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn8.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn9.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn10.setFont(Font.font("System", FontWeight.BOLD, 18));
       
       gridpane.add(warn1,3,2);
       gridpane.add(warn2,3,3);
       gridpane.add(warn3,3,4);
       gridpane.add(warn4,3,5);
       gridpane.add(warn5,3,6);
       gridpane.add(warn6,3,7);
       gridpane.add(warn7,3,8);
       gridpane.add(warn8,3,9);
       gridpane.add(warn9,3,10);
       gridpane.add(warn10,3,11);
       
       //static String docfirst=null;
       
       back.setOnAction((ActionEvent event) -> {
           queryFunctions.selectTable();
       });
       
       btn.setOnAction((ActionEvent event) -> {          
          if(reffn1.getText().length()<=0){
              warn1.setText("Please enter something!");
          }else{
              if(a1==0){
                  reffirst = "'" + reffn1.getText().toUpperCase() + "'";
                  a1++;
              }else {
                  reffirst = reffirst + ",'" + reffn1.getText().toUpperCase() + "'";
                  //a1++;
              }
          }
          reffn1.clear();
       });
       btn1.setOnAction((ActionEvent event) -> {          
          if(refln1.getText().length()<=0){
              warn2.setText("Please enter something!");
          }else{
              if(a2==0){
                  reflast = "'" + refln1.getText().toUpperCase() + "'";
                  a2++;
              }else {
                  reflast = reflast + ",'" + refln1.getText().toUpperCase() + "'";
              }
          }
          refln1.clear();
       });
       btn2.setOnAction((ActionEvent event) -> {          
          if(ins1.getText().length()<=0){
              warn3.setText("Please enter something!");
          }else{
              if(a3==0){
                  insname = "'" + ins1.getText().toUpperCase() + "'";
                  a3++;
              }else {
                  insname = insname + ",'" + ins1.getText().toUpperCase() + "'";
              }
          }
          ins1.clear();
       });
       btn3.setOnAction((ActionEvent event) -> {          
          if(city1.getText().length()<=0){
              warn4.setText("Please enter something!");
          }else{
              //docspe = docspe + "'" + spe1.getText() + "',";
              if(a4==0){
                  cityname = "'" + city1.getText().toUpperCase() + "'";
                  a4++;
              }else {
                  cityname = cityname + ",'" + city1.getText().toUpperCase() + "'";
              }
          }
          city1.clear();
       });
       btn4.setOnAction((ActionEvent event) -> {          
          if(state1.getText().length()<=0){
              warn5.setText("Please enter something!");
          }else{
              //docspe = docspe + "'" + spe1.getText() + "',";
              if(a5==0){
                  statename = "'" + state1.getText().toUpperCase() + "'";
                  a5++;
              }else {
                  statename = statename + ",'" + state1.getText().toUpperCase() + "'";
              }
          }
          state1.clear();
       });
       btn5.setOnAction((ActionEvent event) -> {          
          if(country1.getText().length()<=0){
              warn6.setText("Please enter something!");
          }else{
              if(a6==0){
                  countryname = "'" + country1.getText().toUpperCase() + "'";
                  a6++;
              }else {
                  countryname = countryname + ",'" + country1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          country1.clear();
       });
       btn6.setOnAction((ActionEvent event) -> { 
           int y;
           y=0;
          if(end1.getText().length()<=0){
              warn7.setText("Please enter something!");
          }else{
              try{
               Integer.parseInt(end1.getText());
              }catch (NumberFormatException ex){
                warn7.setText("Please enter valid year!");
                y=1;
              }
              if(y==0){
                warn7.setText(null);
                if(a7==0){
                  endyear = end1.getText();
                  a7++;
              }else {
                  endyear = endyear + "," + end1.getText() + "";
                }
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          end1.clear();
       });
       btn7.setOnAction((ActionEvent event) -> {          
          if(deg1.getText().length()<=0){
              warn8.setText("Please enter something!");
          }else{
              if(a8==0){
                  degree = "'" + deg1.getText().toUpperCase() + "'";
                  a8++;
              }else {
                  degree = degree + ",'" + deg1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          deg1.clear();
       });
       btn8.setOnAction((ActionEvent event) -> {          
          if(spe1.getText().length()<=0){
              warn9.setText("Please enter something!");
          }else{
              if(a9==0){
                  specialization = "'" + spe1.getText().toUpperCase() + "'";
                  a9++;
              }else {
                  specialization = specialization + ",'" + spe1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          spe1.clear();
       });
       btn9.setOnAction((ActionEvent event) -> {          
          if(occ1.getText().length()<=0){
              warn10.setText("Please enter something!");
          }else{
              if(a10==0){
                  occupation = "'" + occ1.getText().toUpperCase() + "'";
                  a10++;
              }else {
                  occupation = occupation + ",'" + occ1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          occ1.clear();
       });
       
       done.setOnAction((ActionEvent event) -> {          
          String reffirst1=null;
          String reflast1=null;
          String insname1=null;
          String cityname1=null;
          String statename1=null;
          String countryname1=null;
          String endyear1=null;
          String degree1=null;
          String specialization1=null;
          String occupation1=null;
          
          int i=0;
          String finalwhere;
          finalwhere=null;
          if(reffirst!=null){
              reffirst1 = " WHERE R.FIRST_NAME IN (" + reffirst + ")";
              System.out.println("here1");
              i++;
              finalwhere=reffirst1;
          }
          if(reflast!=null){
              if(i>0){
                  reflast1 = " OR R.LAST_NAME IN (" + reflast + ")";
              }else{
                 reflast1=" WHERE R.LAST_NAME IN (" + reflast + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=reflast1;}
              else finalwhere=finalwhere+reflast1;
              i++;
          }
          if(insname!=null){
              if(i>0){
                  insname1 = " OR I.INSTITUTE_NAME IN (" + insname + ")";
              }else{
                insname1=" WHERE I.INSTITUTE_NAME IN (" + insname + ")";  
              }
              
              //i++;
              if(i==0){finalwhere=insname1;}
              else finalwhere=finalwhere+insname1;
              System.out.println("here3");
              i++;
          }
          if(cityname!=null){
              if(i>0){
                  cityname1 = " OR L.CITY IN (" + cityname + ")";
              }else{
                 cityname1=" WHERE L.CITY IN (" + cityname + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=cityname1;}
              else finalwhere=finalwhere+cityname1;
              i++;
          }
          if(statename!=null){
              if(i>0){
                  statename1 = " OR L.STATE IN (" + statename + ")";
              }else{
                 statename1=" WHERE L.STATE IN (" + statename + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=statename1;}
              else finalwhere=finalwhere+statename1;
              i++;
          }
          if(countryname!=null){
              if(i>0){
                  countryname1 = " OR L.COUNTRY IN (" + countryname + ")";
              }else{
                  countryname1=" WHERE L.COUNTRY IN (" + countryname + ")";
              }
              
              //i++;
              if(i==0){finalwhere=countryname1;}
              else finalwhere=finalwhere+countryname1;
              System.out.println("here4");
              i++;
          }
          if(endyear!=null){
              if(i>0){
                  endyear1 = " OR EDU.ENDYEAR IN (" + endyear + ")";
              }else{
                  endyear1=" WHERE EDU.ENDYEAR IN (" + endyear + ")";
              }
              
              //i++;
              if(i==0){finalwhere=endyear1;}
              else finalwhere=finalwhere+endyear1;
              System.out.println("here4");
              i++;
          }
          if(degree!=null){
              if(i>0){
                  degree1 = " OR EDU.DEGREE IN (" + degree + ")";
              }else{
                  degree1=" WHERE EDU.DEGREE IN (" + degree + ")";
              }
              
              //i++;
              if(i==0){finalwhere=degree1;}
              else finalwhere=finalwhere+degree1;
              System.out.println("here4");
              i++;
          }
          if(specialization!=null){
              if(i>0){
                  specialization1 = " OR EDU.SPECIALIZATION IN (" + specialization + ")";
              }else{
                  specialization1=" WHERE EDU.SPECIALIZATION IN (" + specialization + ")";
              }
              
              //i++;
              if(i==0){finalwhere=specialization1;}
              else finalwhere=finalwhere+specialization1;
              System.out.println("here4");
              i++;
          }
          if(occupation!=null){
              if(i>0){
                  occupation1 = " OR EDU.OCCUPATION IN (" + occupation + ")";
              }else{
                  occupation1=" WHERE EDU.OCCUPATION IN (" + occupation + ")";
              }
              
              //i++;
              if(i==0){finalwhere=occupation1;}
              else finalwhere=finalwhere+occupation1;
              System.out.println("here4");
              i++;
          }
          
          //String finalwhere = docfirst1+doclast1+docspe1+reffirst1+reflast1+refdiag1+refmedi1;
          System.out.println(finalwhere);
          String sql = "SELECT (R.FIRST_NAME||' '||R.LAST_NAME) Refugee,I.INSTITUTE_NAME Institute,L.CITY City,L.STATE State,L.COUNTRY Country,EDU.ENDYEAR EndingYear,EDU.DEGREE Degree,EDU.SPECIALIZATION Specialization,EDU.OCCUPATION Occupation\n" +
"FROM REFUGEE R JOIN EDUCATIONAL_OCCUPATIONAL_INFO EDU\n" +
"ON (R.REFUGEE_ID=EDU.REFUGEE_ID)\n" +
"JOIN INSTITUTE I\n" +
"ON (EDU.INSTITUTE_ID=I.INSTITUTE_ID)\n" +
"JOIN LOCATION L\n" +
"ON (I.LOCATION_ID=L.LOCATION_ID)";
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
