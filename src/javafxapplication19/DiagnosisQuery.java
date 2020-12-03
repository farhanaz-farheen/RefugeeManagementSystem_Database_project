/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;
import java.sql.Connection;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javafx.scene.text.Text;
/**
 *
 * @author Oishi
 */
public class DiagnosisQuery {
     static Stage primarystage = Main.primaryStage;
     static String docfirst;
     static String doclast;
     static String docspe;
     static String reffirst;
     static String reflast;
     static String refdiag;
     static String refmedi;
     static int a1;
     static int a2;
     static int a3;
     static int a4;
     static int a5;
     static int a6;
     static int a7;
    public static void querydoc(Connection con){
     docfirst=null;
     doclast=null;
     docspe=null;
     reffirst=null;
     reflast=null;
     refdiag=null;
     refmedi=null;
     a1=0;
     a2=0;
     a3=0;
     a4=0;
     a5=0;
     a6=0;
     a7=0;
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       scene.getStylesheets().add(DiagnosisQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(DiagnosisQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
       GridPane gridpane = new GridPane();
       gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
       //Button btn = new Button("");
       Text greet = new Text("Enter Doctor's details");
       Text fn = new Text("First Name");
       Text ln = new Text("Last Name");
       Text spe = new Text("Specialization");
       
       greet.setFont(Font.font("System", FontWeight.BOLD, 25));
       fn.setFont(Font.font("System", FontWeight.BOLD, 20));
       ln.setFont(Font.font("System", FontWeight.BOLD, 20));
       spe.setFont(Font.font("System", FontWeight.BOLD, 20));
       
       Text greet2 = new Text("Enter patient's details");
       greet2.setFont(Font.font("System", FontWeight.BOLD, 20));
       
       Text fnr = new Text("First Name");
       Text lnr = new Text("Last Name");
       Text diag = new Text("Diagnosis");
       Text medi = new Text("Medicine");
       
       fnr.setFont(Font.font("System", FontWeight.BOLD, 20));
       lnr.setFont(Font.font("System", FontWeight.BOLD, 20));
       diag.setFont(Font.font("System", FontWeight.BOLD, 20));
       medi.setFont(Font.font("System", FontWeight.BOLD, 20));
       
       TextField fn1 = new TextField();
       TextField ln1 = new TextField();
       TextField spe1 = new TextField();
       
       TextField fnr1 = new TextField();
       TextField lnr1 = new TextField();
       TextField diag1 = new TextField();
       TextField medi1 = new TextField();
       
       Button btn = new Button("ADD");
       Button btn1 = new Button("ADD");
       Button btn2 = new Button("ADD");
       Button btn3 = new Button("ADD");
       Button btn4 = new Button("ADD");
       Button btn5 = new Button("ADD");
       Button btn6 = new Button("ADD");
       Button btn7 = new Button("DONE");
       
       Button back = new Button("BACK");
       
       gridpane.add(greet,0,0);
       gridpane.add(fn,0,2);
       gridpane.add(ln,0,3);
       gridpane.add(spe,0,4);
       gridpane.add(greet2,0,6);
       gridpane.add(fnr,0,8);
       gridpane.add(lnr,0,9);
       gridpane.add(diag, 0, 10);
       gridpane.add(medi,0,11);
       gridpane.add(btn7,1,13);
       gridpane.add(back,0,13);
       
       gridpane.add(fn1,1,2);
       gridpane.add(ln1,1,3);
       gridpane.add(spe1,1,4);
       
       gridpane.add(fnr1,1,8);
       gridpane.add(lnr1,1,9);
       gridpane.add(diag1,1, 10);
       gridpane.add(medi1,1,11);
       
       
       gridpane.add(btn,2,2);
       gridpane.add(btn1,2,3);
       gridpane.add(btn2,2,4);
       
       gridpane.add(btn3,2,8);
       gridpane.add(btn4,2,9);
       gridpane.add(btn5,2,10);
       gridpane.add(btn6,2,11);
       
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
       
       gridpane.add(warn4,3,8);
       gridpane.add(warn5,3,9);
       gridpane.add(warn6,3,10);
       gridpane.add(warn7,3,11);
       
       //static String docfirst=null;
       
       back.setOnAction((ActionEvent event) -> { 
           queryFunctions.selectTable();
       });
       
       btn.setOnAction((ActionEvent event) -> {          
          if(fn1.getText().length()<=0){
              warn1.setText("Please enter something!");
          }else{
              if(a1==0){
                  docfirst = "'" + fn1.getText().toUpperCase() + "'";
                  a1++;
              }else {
                  docfirst = docfirst + ",'" + fn1.getText().toUpperCase() + "'";
                  //a1++;
              }
          }
          fn1.clear();
       });
       btn1.setOnAction((ActionEvent event) -> {          
          if(ln1.getText().length()<=0){
              warn2.setText("Please enter something!");
          }else{
              if(a2==0){
                  doclast = "'" + ln1.getText().toUpperCase() + "'";
                  a2++;
              }else {
                  doclast = doclast + ",'" + ln1.getText().toUpperCase() + "'";
              }
          }
          ln1.clear();
       });
       btn2.setOnAction((ActionEvent event) -> {          
          if(spe1.getText().length()<=0){
              warn3.setText("Please enter something!");
          }else{
              //docspe = docspe + "'" + spe1.getText() + "',";
              if(a3==0){
                  docspe = "'" + spe1.getText().toUpperCase() + "'";
                  a3++;
              }else {
                  docspe = docspe + ",'" + spe1.getText().toUpperCase() + "'";
              }
          }
          spe1.clear();
       });
       btn3.setOnAction((ActionEvent event) -> {          
          if(fnr1.getText().length()<=0){
              warn4.setText("Please enter something!");
          }else{
              if(a4==0){
                  reffirst = "'" + fnr1.getText().toUpperCase() + "'";
                  a4++;
              }else {
                  reffirst = reffirst + ",'" + fnr1.getText().toUpperCase() + "'";
              }
              //reffirst = reffirst + "'" + fnr1.getText() + "',";
          }
          fnr1.clear();
       });
       btn4.setOnAction((ActionEvent event) -> {          
          if(lnr1.getText().length()<=0){
              warn5.setText("Please enter something!");
          }else{
              if(a5==0){
                  a5++;
                  reflast = "'" + lnr1.getText().toUpperCase()+ "'";
              }else {
                  reflast = reflast + ",'" + lnr1.getText().toUpperCase() + "'";
              }
              //reflast = reflast + "'" + lnr1.getText() + "',";
          }
          lnr1.clear();
       });
       btn5.setOnAction((ActionEvent event) -> {          
          if(diag1.getText().length()<=0){
              warn6.setText("Please enter something!");
          }else{
              if(a6==0){
                  a6++;
                  refdiag = "'" + diag1.getText().toUpperCase() + "'";
              }else {
                  refdiag = refdiag + ",'" + diag1.getText().toUpperCase() + "'";
              }
              //refdiag = refdiag + "'" + diag1.getText() + "',";
          }
          diag1.clear();
       });
       btn6.setOnAction((ActionEvent event) -> {          
          if(medi1.getText().length()<=0){
              warn7.setText("Please enter something!");
          }else{
              if(a7==0){
                  a7++;
                  refmedi = "'" + medi1.getText().toUpperCase() + "'";
              }else {
                  refmedi = refmedi + ",'" + medi1.getText().toUpperCase() + "'";
              }
              //refmedi = refmedi + "'" + medi1.getText() + "',";
          }
          medi1.clear();
       });
       btn7.setOnAction((ActionEvent event) -> {          
          String docfirst1=null;
          String doclast1=null;
          String docspe1=null;
          String reffirst1=null;
          String reflast1=null;
          String refdiag1=null;
          String refmedi1=null;
          int i=0;
          String finalwhere;
          finalwhere=null;
          if(docfirst!=null){
              docfirst1 = " WHERE D.DOCTOR_NURSE_OFFICER_FIRSTNAME IN (" + docfirst + ")";
              System.out.println("here1");
              i++;
              finalwhere=docfirst1;
          }
          if(doclast!=null){
              if(i>0){
                  doclast1 = " OR D.DOCTOR_NURSE_OFFICER_LASTNAME IN (" + doclast + ")";
              }else{
                 doclast1=" WHERE D.DOCTOR_NURSE_OFFICER_LASTNAME IN (" + doclast + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=doclast1;}
              else finalwhere=finalwhere+doclast1;
              i++;
          }
          if(docspe!=null){
              if(i>0){
                  docspe1 = " OR D.SPECIALIZATION IN (" + docspe + ")";
              }else{
                docspe1=" WHERE D.SPECIALIZATION IN (" + docspe + ")";  
              }
              
              //i++;
              if(i==0){finalwhere=docspe1;}
              else finalwhere=finalwhere+docspe1;
              System.out.println("here3");
              i++;
          }
          if(reffirst!=null){
              if(i>0){
                  reffirst1 = " OR R.FIRST_NAME IN (" + reffirst + ")";
              }else{
                  reffirst1=" WHERE R.FIRST_NAME IN (" + reffirst + ")";
              }
              
              //i++;
              if(i==0){finalwhere=reffirst1;}
              else finalwhere=finalwhere+reffirst1;
              System.out.println("here4");
              i++;
          }
          if(reflast!=null){
              if(i>0){
                  reflast1 = " OR R.LAST_NAME IN (" + reflast + ")";
              }else{
                  reflast1=" WHERE R.LAST_NAME IN (" + reflast + ")";
              }
              
             // i++;
              System.out.println("here5");
              if(i==0){finalwhere=reflast1;}
              else finalwhere=finalwhere+reflast1;
              i++;
          }
          if(refdiag!=null){
              if(i>0){
                  refdiag1 = " OR DI.DIAGNOSIS_NAME IN (" + refdiag + ")";
              }else{
                  refdiag1=" WHERE DI.DIAGNOSIS_NAME IN (" + refdiag + ")";
              }
              
              
              if(i==0){
                  System.out.println("OFCOURSE");
                  finalwhere=refdiag1;
              }
              else{ 
                  finalwhere=finalwhere+refdiag1;
              }
              i++;
          }
          if(refmedi!=null){
              if(i>0){
                  refmedi1 = " OR DI.MEDICINE_NAME IN (" + refmedi + ")"; 
              }else{
                  refmedi1=" WHERE DI.MEDICINE_NAME IN (" + refmedi + ")";
              }
              
              if(i==0){finalwhere=refmedi1;}
              else finalwhere=finalwhere+refmedi1;
              i++;
          }
          //String finalwhere = docfirst1+doclast1+docspe1+reffirst1+reflast1+refdiag1+refmedi1;
          System.out.println(finalwhere);
          String sql = "select (D.DOCTOR_NURSE_OFFICER_FIRSTNAME|| ' ' || D.DOCTOR_NURSE_OFFICER_LASTNAME) DoctorsName, D.SPECIALIZATION,(R.FIRST_NAME|| ' ' ||R.LAST_NAME) RefugeeName,DI.DIAGNOSIS_NAME Diagnosis,DI.MEDICINE_NAME Medicine\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID = P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER D\n" +
"ON (D.DOCTOR_NURSE_OFFICER_ID = P.DOCTOR_NURSE_OFFICER_ID)\n" +
"JOIN DIAGNOSIS DI\n" +
"ON (DI.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)";
          if(finalwhere!=null){
              sql=sql+finalwhere;
          }
           System.out.println(sql);
          queryFunctions.showRes(sql);
          //query5(sql);
       });
       root.setCenter(gridpane);
       primarystage.setScene(scene);
       primarystage.show();
    }
    
    public static void query5(String sql) {
        try {
            System.out.println(sql);
            Connection con= Main.conn.con;
            VBox vb= new VBox();
            vb.setSpacing(20);
            BorderPane root= new BorderPane();
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(DiagnosisQuery.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(DiagnosisQuery.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            primarystage.setScene(scene);
            root.setCenter(vb);
            vb.setPadding(new Insets(30));
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            ScrollPane root1= new ScrollPane(grid);
            
            Label dl= new Label("Query Results");
            dl.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            
            Text []colnm= new Text[totcol];
            ArrayList<Button> btns= new ArrayList<>();
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
                grid.add(sp, i+1, 0);
                sp.getChildren().add(colnm[i]);
                sp.setId("border-two");
            }
            ArrayList<String> comsl= new ArrayList<>();
            Button temp1= new Button();
            btns.add(temp1);
            int i=1;
            comsl.add(0,"");
            while(rs.next()){
                comsl.add(i,rs.getString(1));
                for(int j=0;j<totcol;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":"  "+rs.getString(j+1)+"  ");
                    sp.setId("border-one");
                    templ.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                    grid.setFillWidth(sp, true);
                    grid.setFillHeight(sp, true);
                    if(i%2==0){
                        sp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                    sp.getChildren().add(templ);
                    grid.add(sp,j+1,i);
                }
                Button temp = new Button("Details#"+i+" >>");
                grid.setHalignment(temp, HPos.RIGHT);
                temp.setId("bwbutton");
                temp.setOnAction((ActionEvent event)->{/*
                    try {
                        System.out.println(comsl.get(temp.getText().charAt(temp.getText().length()-4)-'0'));
                        String sql1= "delete from refugee where refugee_id="+comsl.get(temp.getText().charAt(temp.getText().length()-4)-'0');
                        System.out.println(sql1);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Logger.getLogger(refugeeQuery.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    //querydetails(comsl.get(temp.getText().charAt(temp.getText().length()-4)-'0'),sql);
                });
                //btns.add(i,temp);
                grid.add(temp, 0, i);
                i++;
            }
            Button back = new Button("Back");
            Button next = new Button("Profile");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            back.setOnAction((ActionEvent event)->{
                DiagnosisQuery.querydoc(con);
            });
            vb.getChildren().addAll(dl,root1,back,next);
            back.setId("com-button");
            next.setId("com-button");
        } catch (SQLException ex) {
            Logger.getLogger(DiagnosisQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
