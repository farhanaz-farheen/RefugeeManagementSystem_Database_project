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
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class checkupDelete {
    static Connection con;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static String docid;
    static  String refid;
    static  String docname;
    public static void delcheck() {
        primaryStage= Main.primaryStage;
        con= Main.conn.con;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        root.setCenter(grid);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(checkupDelete.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(checkupDelete.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(checkupDelete.class.getResource("fonts.css").toExternalForm());
        root.setId("background-eight");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter ID for Deletion");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        Label dl = new Label("Doctor/Nurse\n/Officer ID: ");
        dl.setId("textthree");
        TextField dt= new TextField();
        dt.setText(docid);
        grid.add(dl,0,1);
        grid.add(dt,1,1);
        Label rl = new Label("Refugee_ID: ");
        rl.setId("textthree");
        TextField rt= new TextField();
        grid.add(rl,0,2);
        grid.add(rt,1,2);
        Button add= new Button("Add");
        add.setId("bwbutton");
        grid.add(add, 2, 2);
        Label warn= new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 3,3,1);
        Button back = designs.button("Back");
        grid.add(back, 0, 4);
        back.setOnAction((ActionEvent event)->{
            Main.AuthorityHomePage();
        });
        add.setOnAction((ActionEvent event)->{
            String s= rt.getText();
            boolean v= true;
            if(s!=null && s.length()>0){
                try{
                    Integer.parseInt(s);
                }catch(NumberFormatException ex){
                    v= false;
                }
                if(v){
                    if(refid==null){
                        refid= "in ("+s;
                    }
                    else{
                        refid= refid+","+s;
                    }
                }
            }
            
        });
        Button next= designs.button("Next");
        next.setOnAction((ActionEvent event)->{
            warn.setText(null);
            String s= dt.getText();
            boolean v= true;
            if(s!=null && s.length()>0){
                try{
                    Integer.parseInt(s);
                }catch(NumberFormatException ex){
                    v= false;
                }
                if(v){
                    docid = s;
                    if(refid!=null){
                        try {
                            String sql= "Select doctor_nurse_officer_firstname||' '||doctor_nurse_officer_lastname from doctor_nurse_officer where doctor_nurse_officer_id="+docid;
                            PreparedStatement pst= con.prepareStatement(sql);
                            ResultSet rs= pst.executeQuery();
                            if(rs.next()){
                                docname = rs.getString(1);
                                sql= "select * from prescription where doctor_nurse_officer_id="+docid+" and refugee_id "+refid+")";
                                System.out.println(sql);
                                pst= con.prepareStatement(sql);
                                rs= pst.executeQuery();
                                if(rs.next()){
                                    delcheck1();
                                }
                                else{
                                    warn.setText("No records found");
                                }
                            }
                            else{
                                warn.setText("Doctor_nurse_officer ID not found");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(checkupDelete.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    else{
                        warn.setText("No valid refugee_id given");
                    }
                }
                else{
                    warn.setText("Enter valid Doctor/Nurse/Officer ID");
                }
            }
        });
        grid.add(next,2,4);
        back.setId("bwbutton");
        next.setId("bwbutton");
    }
    
    public static void delcheck1() {
        try {
            VBox vb= new VBox();
            vb.setSpacing(10);
            ScrollPane root1= new ScrollPane();
            root = new BorderPane();
            grid = new GridPane();
            scene = new Scene(root, 1100, 900);
            root.setCenter(grid);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(checkupDelete.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(checkupDelete.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(checkupDelete.class.getResource("fonts.css").toExternalForm());
            root.setId("background-eight");
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            primaryStage.setScene(scene);
            vb.setPadding(new Insets(30));
            Label dl= new Label("Doctor\nID: "+docid+"\nName:"+docname);
            dl.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            String sql= "SELECT R.REFUGEE_ID PATIENT_ID,(R.FIRST_NAME|| ' ' || R.LAST_NAME) PATIENT_NAME,D.DIAGNOSIS_NAME DIAGNOSIS,D.MEDICINE_NAME MEDICINE,D.diagnosis_id da,p.prescription_serial pre \n" +
            "FROM REFUGEE R JOIN PRESCRIPTION P\n" +
            "ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
            "JOIN DOCTOR_NURSE_OFFICER DNO\n" +
            "ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
            "LEFT OUTER JOIN DIAGNOSIS D\n" +
            "ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
            "WHERE DNO.DOCTOR_NURSE_OFFICER_ID ="+docid+" and R.refugee_id "+refid+")";
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            Text []colnm= new Text[totcol-2];
            ArrayList<Button> btns= new ArrayList<>();
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol-2;i++){
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
            ArrayList<String> prids= new ArrayList<>();
            ArrayList<String> diaids= new ArrayList<>();
            Button temp1= new Button();
            btns.add(temp1);
            int i=1;
            diaids.add(0,"");
            prids.add(0,"");
            boolean nodt= true;
            while(rs.next()){
                nodt= false;
                diaids.add(i,rs.getString("da"));
                prids.add(i,rs.getString("pre"));
                for(int j=0;j<totcol-2;j++){
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
                Button temp = new Button("Select#"+i+":");
                temp.setOnAction((ActionEvent event)->{
                    try {
                        int numpart= Integer.parseInt(temp.getText().substring(7, temp.getText().length()-1));
                        String sql1= "delete from diagnosis where diagnosis_id="+diaids.get(numpart);
                        System.out.println(sql);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                        sql1= "select count(*) from diagnosis where prescription_serial="+prids.get(numpart);
                        System.out.println(sql1);
                        stp= con.prepareStatement(sql1);
                        ResultSet rst= stp.executeQuery();
                        rst.next();
                        int count= rst.getInt(1);
                        if(count==0){
                            sql1= "delete from prescription where prescription_serial="+prids.get(numpart);
                            System.out.println(sql1);
                            stp= con.prepareStatement(sql1);
                            stp.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(checkupDelete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    delcheck1();
                });
                btns.add(i,temp);
                grid.add(btns.get(i), 0, i);
                i++;
            }
            if(nodt){
                grid.getRowConstraints().add(new RowConstraints(50));
                StackPane sp= new StackPane();
                sp.setId("border-one");
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                Label nodatal= new Label("No Data Found");
                nodatal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                sp.getChildren().add(nodatal);
                grid.add(sp,0 , 1,totcol-1,1);
            }
            Button next = designs.button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            Button back = designs.button("Back");
            back.setOnAction((ActionEvent event)->{
                delcheck();
            });
            root1.setContent(grid);
            back.setId("bwbutton");
            next.setId("bwbutton");
            vb.getChildren().addAll(dl,root1,back,next);
            root.setCenter(vb);
        } catch (SQLException ex) {
            Logger.getLogger(checkupDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
