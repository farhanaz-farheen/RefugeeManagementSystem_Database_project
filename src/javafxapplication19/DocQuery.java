package javafxapplication19;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class DocQuery {
     static Stage primarystage = Main.primaryStage;
     static String docfirst;
     static String doclast;
     static String docspe;
     
     static int a1;
     static int a2;
     static int a3;
     
    public static void querydoc(Connection con){
     docfirst=null;
     doclast=null;
     docspe=null;
     
     a2=0;
     a3=0;
     
        BorderPane root = new BorderPane();
       Scene scene = new Scene(root,1100,900);
       scene.getStylesheets().add(DocQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(DocQuery.class.getResource("animated.css").toExternalForm());
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

       
       TextField fn1 = new TextField();
       TextField ln1 = new TextField();
       TextField spe1 = new TextField();
       

       
       Button btn = new Button("ADD");
       Button btn1 = new Button("ADD");
       Button btn2 = new Button("ADD");
       Button back = new Button("BACK");
       Button btn7 = new Button("DONE");
       gridpane.add(greet,0,0);
       gridpane.add(fn,0,2);
       gridpane.add(ln,0,3);
       gridpane.add(spe,0,4);
      
       gridpane.add(btn7,1,7);
       gridpane.add(back,1,8);
       
       gridpane.add(fn1,1,2);
       gridpane.add(ln1,1,3);
       gridpane.add(spe1,1,4);
      
       gridpane.add(btn,2,2);
       gridpane.add(btn1,2,3);
       gridpane.add(btn2,2,4);
       
       Label warn1 = new Label();
       Label warn2 = new Label();
       Label warn3 = new Label();
       
       warn1.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn2.setFont(Font.font("System", FontWeight.BOLD, 18));
       warn3.setFont(Font.font("System", FontWeight.BOLD, 18));
       
       
       gridpane.add(warn1,3,2);
       gridpane.add(warn2,3,3);
       gridpane.add(warn3,3,4);
       
       
       
       //static String docfirst=null;
       
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
       back.setOnAction((ActionEvent event)->{
                //DocQuery.querydoc(con);
                queryFunctions.selectTable();
            });
       
       btn7.setOnAction((ActionEvent event) -> {          
          String docfirst1=null;
          String doclast1=null;
          String docspe1=null;
          
          int i=0;
          String finalwhere;
          finalwhere=null;
          if(docfirst!=null){
              docfirst1 = " WHERE DOCTOR_NURSE_OFFICER_FIRSTNAME IN (" + docfirst + ")";
              System.out.println("here1");
              i++;
              finalwhere=docfirst1;
          }
          if(doclast!=null){
              if(i>0){
                  doclast1 = " OR DOCTOR_NURSE_OFFICER_LASTNAME IN (" + doclast + ")";
              }else{
                 doclast1=" WHERE DOCTOR_NURSE_OFFICER_LASTNAME IN (" + doclast + ")"; 
              }          
             
              System.out.println("here2");
              if(i==0){finalwhere=doclast1;}
              else finalwhere=finalwhere+doclast1;
              i++;
          }
          if(docspe!=null){
              if(i>0){
                  docspe1 = " OR SPECIALIZATION IN (" + docspe + ")";
              }else{
                docspe1=" WHERE SPECIALIZATION IN (" + docspe + ")";  
              }
              
              //i++;
              if(i==0){finalwhere=docspe1;}
              else finalwhere=finalwhere+docspe1;
              System.out.println("here3");
              i++;
          }
          
          //String finalwhere = docfirst1+doclast1+docspe1+reffirst1+reflast1+refdiag1+refmedi1;
          System.out.println(finalwhere);
          String sql = "select DOCTOR_NURSE_OFFICER_ID,(DOCTOR_NURSE_OFFICER_FIRSTNAME|| ' ' || DOCTOR_NURSE_OFFICER_LASTNAME) DoctorsName, SPECIALIZATION\n" +
"FROM DOCTOR_NURSE_OFFICER";
          if(finalwhere!=null){
              sql=sql+finalwhere;
          }
           System.out.println(sql);
          //queryFunctions.showRes(sql);
          query5(sql);
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
            scene.getStylesheets().add(DocQuery.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(DocQuery.class.getResource("css1.css").toExternalForm());
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
                    
                    int numpart= Integer.parseInt(temp.getText().substring(8, temp.getText().length()-3));
                    querydetails(comsl.get(numpart),sql);
                    
                    System.out.println(numpart);
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
                DocQuery.querydoc(con);
            });
            vb.getChildren().addAll(dl,root1,back,next);
            back.setId("com-button");
            next.setId("com-button");
        } catch (SQLException ex) {
            Logger.getLogger(DocQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void querydetails(String idref,String sql1) {
        try{
            GridPane hb= new GridPane();
            StackPane imsp= new StackPane();
            imsp.setMaxSize(500, 300);
            imsp.setPrefSize(500, 300);
            Connection con = Main.conn.con;
            hb.setHgap(10);
            ObservableList<String> nm = FXCollections.observableArrayList(
                    "Doctor/Nurse ID", "First Name", "Last Name", "Specialization");
            PreparedStatement st = con.prepareStatement("Select doctor_nurse_officer_id id,(doctor_nurse_officer_firstname||' '||doctor_nurse_officer_lastname) nm,specialization sp from doctor_nurse_officer where doctor_nurse_officer_id="+idref);
            ResultSet rs = st.executeQuery();
            /*rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            for(int i=1;i<5;i++){
                //nm.get(i-1) +":"+
                if(rs.getObject(i)==null)   vals.add(i-1,"");
                else if(i==4)   vals.add(i-1, rs.getObject(i).toString().substring(0, 10));
                else    vals.add(i-1, rs.getObject(i).toString());
                
            }
            String sp;
            sp = rs.getString(5);
            System.out.println(sp);*/
            
            String pirate=null;
            String jack = null;
            String olive = null;
            while(rs.next()){
                pirate = rs.getString("id");
                jack = rs.getString("nm");
                olive = rs.getString("sp");
            }
            String track = "select docimage di from doctor_nurse_officer where doctor_nurse_officer_id=" + idref;
            PreparedStatement stmt = con.prepareStatement(track);
            ResultSet rst = stmt.executeQuery();
            String sp=null;
            while(rst.next()){
                sp=rst.getString("di");
            }
            
            Image image;
            if(sp==null){
                image= new Image("/images/guest-user.jpg");
            }
            else{
                image= new Image("File:"+sp);
            }
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            imsp.getChildren().add(iv1);
            //iv1.fitWidthProperty().bind(imsp.widthProperty());
            //iv1.fitHeightProperty().bind(imsp.heightProperty());
            iv1.setFitHeight(250);
            iv1.setFitWidth(400);
            
            iv1.setPreserveRatio(true);
            GridPane grid = new GridPane();
            
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(DocQuery.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(DocQuery.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Details:");
            title.setUnderline(true);
            title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
            
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            /*Label []col = new Label[14];
            Label []val = new Label[14];
            for(int i=0;i<4;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" "+nm.get(i)+" : ");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" "+vals.get(i)+" ");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
                col[i].setId("textone");
                val[i].setId("textone");
            }*/
            
            
            Text id = new Text("ID: "+pirate);
            Text namee = new Text("Name: "+jack);
            Text spel = new Text("Specialization: "+olive);
            grid.add(id,0,0);
            grid.add(namee,0,1);
            grid.add(spel,0,2);
            id.setFont(Font.font("Constantia", FontWeight.BOLD, 35));
            namee.setFont(Font.font("Constantia", FontWeight.BOLD, 35));
            spel.setFont(Font.font("Constantia", FontWeight.BOLD, 35));
           // grid.setHgap(40);
            hb.add(grid, 0, 0);
            hb.add(imsp, 1, 0);
            GridPane vbox = new GridPane();
            vbox.setVgap(10);
            vbox.add(title, 0, 0);
            vbox.setAlignment(Pos.CENTER);
            vbox.add(hb, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = new Button("");
            next.setPrefSize(120, 40);
            next.setId("next-one");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            Button back = new Button("");
            back.setPrefSize(120, 40);
            back.setId("back-one");
            back.setOnAction((ActionEvent event) -> {
                query5(sql1);
            });
            vbox.add(next, 0, 3);
            vbox.add(back,0,2);
            root.setCenter(vbox);
            primarystage.setScene(scene);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocQuery.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
