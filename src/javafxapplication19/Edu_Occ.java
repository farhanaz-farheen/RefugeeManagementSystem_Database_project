package javafxapplication19;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Edu_Occ {
    static  ArrayList<String> countries;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static String refugeeID = null;
    static Connection con;
    static String institutenm = null;
    static String countrynm = null;
    static String state = null;
    static String city = null;
    static String endyear = null;
    static String degree = null;
    static String specialization = null;
    static String occupation = null;
    
    public static void insert1(String r) {
        con = Main.conn.con;
        primaryStage = Main.primaryStage;
        countries = InsertFunctions.countries;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(Edu_Occ.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("fonts.css").toExternalForm());
        Label title = new Label("Insert Educational/Occupational Information\n   Page(1/3)");
        title.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setId("background-four");
        
        grid.add(title, 0, 0, 3, 1);
        Label ridl = new Label("Refugee ID:");
        ridl.setId("texttwo");
        TextField ridtx = new TextField();
        ridtx.setText(r);
        Label ridwarn = new Label();
        ridwarn.setId("textfour");
        grid.add(ridl, 0, 1);
        grid.add(ridtx, 1, 1);
        grid.add(ridwarn, 2, 1);
        
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            InsertFunctions.selectTable();
        });
        grid.add(back, 0, 2);
        Button next = new Button("Next");
        
        next.setOnAction((ActionEvent event) -> {
            ridwarn.setText("");
            refugeeID = ridtx.getText();
            if(refugeeID==null || refugeeID.length()<7){
                ridwarn.setText("Please insert valid ID");
            }
            else{
                try{
                    int refid = Integer.parseInt(refugeeID);
                    PreparedStatement st = con.prepareStatement("select refugee_id from refugee where refugee_id="+refugeeID);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        insert2(institutenm,city,state,countrynm);
                    }
                    else{
                        ridwarn.setText("ID not found");
                    }
                }catch(NumberFormatException ex){
                    ridwarn.setText("Enter number");
                } catch (SQLException ex) {
                    ridwarn.setText(ex.getMessage());
                }
            }
        });
        grid.add(next, 2, 2);
        back.setId("new-button");
        next.setId("new-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void insert2(String inm,String ct, String st, String cn) {
        
        Text []star = new Text[4];
        for(int i=0;i<4;i++){
            star[i]= new Text("*");
            star[i].setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 24));
            star[i].setFill(Color.RED);
        }
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(Edu_Occ.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("fonts.css").toExternalForm());
        Label title = new Label("Insert Educational/Occupational Information\n   Page(2/3)");
        title.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setId("background-four");
        grid.add(title, 0, 0, 4, 1);
        //1st row
        Label inl = new Label("Institute Name :");
        inl.setId("texttwo");
        TextField intx = new TextField();
        intx.setText(inm);
        Label inn = new Label("At most 100 characters");
        inn.setId("textthree");
        grid.add(star[0], 0, 1);
        grid.add(inl, 1, 1);
        grid.add(intx, 2, 1);
        grid.add(inn, 3, 1);
        //2nd row
        Label cnl = new Label("Country :");
        cnl.setId("texttwo");
        ObservableList<String> country = FXCollections.observableArrayList(countries);
        ComboBox<String> co = new ComboBox<>(country);
        if(cn!=null)   co.setValue(cn);
        grid.add(star[1], 0, 2);
        grid.add(cnl, 1, 2);
        grid.add(co, 2, 2);
        //3rd row
        Label stl = new Label("State :");
        stl.setId("texttwo");
        TextField stt = new TextField();
        stt.setText(st);
        Label stn = new Label("At most 50 characters");
        stn.setId("textthree");
        grid.add(stl, 1, 3);
        grid.add(stt, 2, 3);
        grid.add(stn, 3, 3);
        //4th row
        Label ctl = new Label("City :");
        ctl.setId("texttwo");
        TextField ctt = new TextField();
        ctt.setText(ct);
        Label ctn = new Label("At most 50 characters");
        ctn.setId("textthree");
        grid.add(star[2], 0, 4);
        grid.add(ctl, 1, 4);
        grid.add(ctt, 2, 4);
        grid.add(ctn, 3, 4);
        //
        Label msg = new Label("indicates compulsory.");
        msg.setId("texttwo");
        grid.add(star[3], 1, 5);
        grid.setHalignment(star[3], HPos.RIGHT);
        grid.add(msg, 2, 5);
        //warn
        Label warn = new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 6,4,1);
        grid.setHalignment(warn, HPos.CENTER);
        
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            insert1(refugeeID);
        });
        grid.add(back, 1, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            institutenm = intx.getText();
            countrynm = co.getValue();
            state= stt.getText();
            city= ctt.getText();
            if(institutenm== null || institutenm.length()==0 || countrynm==null || city==null || city.length()==0){
                warn.setText("Insert compulsory ones");
            }
            else if(institutenm.length()>100 || city.length()>50 || (state!=null && state.length()>50) ){
                warn.setText("Consider max length");
            }
            else{
                insert3(endyear, degree, specialization, occupation);
            }
        });
        grid.add(next, 2, 7);
        grid.setHalignment(next, HPos.RIGHT);
        back.setId("new-button");
        next.setId("new-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static String colon(String s){
        if(s.indexOf("'")==0 && s.lastIndexOf("'")==s.length()-1)     return s;
        return "'"+s+"'";
    }
    
    public static void insert3(String y, String d,String s,String o) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        ArrayList<String> years = new ArrayList<>(201);
        years.add(null);
        for(int i=currentYear;i>=currentYear-200+1;i--){
            years.add(Integer.toString(i));
        }
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(Edu_Occ.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(Edu_Occ.class.getResource("fonts.css").toExternalForm());
        Label title = new Label("Insert Educational/Occupational Information\n   Page(3/3)");
        title.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setId("background-four");
        grid.add(title, 0, 0, 4, 1);
        Text []star = new Text[2];
        for(int i=0;i<2;i++){
            star[i]= new Text("*");
            star[i].setFill(Color.RED);
            star[i].setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 24));
        }
        //1st row  
        ObservableList<String> yearVal = FXCollections.observableArrayList(years);
        ComboBox<String> yearsCB = new ComboBox<>(yearVal);
        if(y!=null)     yearsCB.setValue(y);
        Label yearLab = new Label("End Year:");
        yearLab.setId("texttwo");
        grid.add(yearLab, 1, 1);
        grid.add(yearsCB, 2, 1);
        //2nd row
        Label dl = new Label("Degree :");
        TextField dt = new TextField();
        dt.setText(d);
        dl.setId("texttwo");
        Label dnn = new Label("At most 100 characters");
        dnn.setId("textthree");
        grid.add(dl, 1, 2);
        grid.add(dt, 2, 2);
        grid.add(dnn, 3, 2);
        //3rd row
        Label ocl = new Label("Occupation :");
        ocl.setId("texttwo");
        TextField oct = new TextField();
        oct.setText(o);
        Label ocn = new Label("At most 50 characters");
        ocn.setId("textthree");
        grid.add(star[0], 0, 3);
        grid.add(ocl, 1, 3);
        grid.add(oct, 2, 3);
        grid.add(ocn, 3, 3);
        //4th row
        Label spl = new Label("Specialization :");
        spl.setId("texttwo");
        TextField spt = new TextField();
        spt.setText(s);
        Label spn = new Label("At most 100 characters");
        spn.setId("textthree");
        grid.add(spl, 1, 4);
        grid.add(spt, 2, 4);
        grid.add(spn, 3, 4);
        //5th row
        Label msg = new Label("indicates compulsory.");
        msg.setId("texttwo");
        grid.add(star[1], 1, 5);
        grid.setHalignment(star[1], HPos.RIGHT);
        grid.add(msg, 2, 5);
        //6th row
        Label warn = new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 6,4,1);
        grid.setHalignment(warn, HPos.CENTER);
        //buttons
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            insert2(institutenm,city,state,countrynm);
        });
        grid.add(back, 1, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            endyear= yearsCB.getValue();
            degree = dt.getText();
            occupation = oct.getText();
            specialization = spt.getText();
            if(occupation==null || occupation.length()==0)    warn.setText("Insert compulsory ones");
            else if(occupation.length()>50 || (degree!=null && degree.length()>100) || (specialization!=null && specialization.length()>100)){
                warn.setText("Consider max length");
            }
            else{
                try {
                    String st=null,sp=null,dg=null;
                    if(state!=null && state.length()==0)    st= null;
                    else if(state!=null) st = colon(state);
                    if(degree!= null && degree.length()==0) dg= null;
                    else if(degree!=null)    dg = colon(degree);
                    if(specialization!= null && specialization.length()==0) sp= null;
                    else if(specialization!=null)   sp = colon(specialization);
                    String sql = "begin locinseduocc(" + colon(city) + "," +  st + "," + colon(countrynm) + "," + colon(institutenm)+ "," +
                            refugeeID + ","+ endyear + "," + dg + "," + colon(occupation) + "," + sp + "," +"?,?);end;";
                    System.out.println(sql);
                    sql= sql.toUpperCase();
                    CallableStatement cst = con.prepareCall(sql);
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.registerOutParameter(2, Types.INTEGER);
                    
                    cst.execute();
                    int ver = cst.getInt(1);
                    
                    String insid= String.valueOf(cst.getInt(2));
                    if(ver==1){
                        insert4(insid);
                    }
                    else{
                        warn.setText("Couldn't insert");
                    }
                } catch (SQLException ex) {
                    warn.setText("Couldn't insert");
                    Logger.getLogger(Edu_Occ.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 2, 7);
        grid.setHalignment(next, HPos.RIGHT);
        back.setId("new-button");
        next.setId("new-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void insert4(String insid){
        try {
            grid = new GridPane();
            ObservableList<String> vals = FXCollections.observableArrayList();
            //String sql  = "select * from institute where institute_id = " + insid;
            ObservableList<String> nm = FXCollections.observableArrayList("Refugee_ID","Name","Institute Name","City","State","Country"
                                                                         ,"End Year","Degree","Occupation","Specialization");
            String sql  = "select first_name||' '||last_name as Name from refugee where refugee_id = " + refugeeID;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Label [][]l = new Label[10][2];
            String fullnm = rs.getString(1);
            vals.add(refugeeID);
            vals.add(fullnm);
            sql  = "select * from institute where institute_id = " + insid;
            pst= con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getString(2));
            sql = "select * from location where location_id="+rs.getString(3);
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getString(2));
            vals.add(rs.getObject(3)==null?"":rs.getString(3));
            vals.add(rs.getString(4));
            sql = "select * from educational_occupational_info where refugee_id="+refugeeID+" AND institute_id="+insid;
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getObject(3)==null?"":rs.getString(3));
            vals.add(rs.getObject(4)==null?"":rs.getString(4));
            vals.add(rs.getString(5));
            vals.add(rs.getObject(6)==null?"":rs.getString(6));
            Label []col = new Label[10];
            Label []val = new Label[10];
            for(int i=0;i<10;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" "+nm.get(i)+" : ");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                col[i].setId("texttwo");
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" "+vals.get(i)+" ");
                val[i].setId("texttwo");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
            }
            
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            Label title = new Label("Inserted Educational/Occupational Information-");
            title.setUnderline(true);
            title.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
            scene.getStylesheets().add(Edu_Occ.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(Edu_Occ.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(Edu_Occ.class.getResource("fonts.css").toExternalForm());
            GridPane vbox = new GridPane();
            vbox.setVgap(20);
            vbox.add(title, 0, 0);
            vbox.setAlignment(Pos.CENTER);
            vbox.add(grid, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = new Button();
            next.setId("next-one");
            next.setPrefSize(120, 40);
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            root.setId("background-four");
            vbox.add(next, 0, 2);
            root.setCenter(vbox);
            primaryStage.setScene(scene);
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Edu_Occ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
