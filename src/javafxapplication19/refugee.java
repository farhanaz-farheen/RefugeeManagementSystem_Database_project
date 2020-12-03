package javafxapplication19;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class refugee {
    static  ArrayList<String> countries;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static Label warning;
    static String firstnm = null;
    static String lastnm = null;
    static String countrynm = null;
    static String gender = null;
    static String bloodGroup = null;
    static String year = null;
    static String month = null;
    static String day = null;
    static String passport = null;
    static String ethtrisecgrp = null;
    static String religion = null;
    static String father = null;
    static String mother = null;
    static String legal = null;
    static String spouse = null;
    static String imgUrl= null;
    static Connection con;
    
    public static void insert1(String fname, String lname, String coo, String gndr, String bldgrp) {
        imgUrl= null;
        primaryStage = Main.primaryStage;
        countries = InsertFunctions.countries;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugee.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugee.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Refugee\n Page(1/3)");
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 4, 1);
        Text []star = new Text[5];
        for(int i=0;i<5;i++){
            star[i]= new Text("*");
            star[i].setFill(Color.RED);
            star[i].setId("textone");
        }
        
        //First Row:
        Label fnl = new Label("First Name :");
        fnl.setId("textone");
        TextField fnt = new TextField();
        fnt.setText(fname);
        Label fnn = new Label("At most 50 characters");
        fnn.setId("textone");
        grid.add(star[0], 0, 1);
        grid.add(fnl, 1, 1);
        grid.add(fnt, 2, 1);
        grid.add(fnn, 3, 1);
        
        //2nd Row
        Label lnl = new Label("Last Name :");
        lnl.setId("textone");
        TextField lnt = new TextField();
        lnt.setText(lname);
        Label lnn = new Label("At most 50 characters");
        lnn.setId("textone");
        grid.add(lnl, 1, 2);
        grid.add(lnt, 2, 2);
        grid.add(lnn, 3, 2);
        
        //3rd row
        Label cnl = new Label("Country of\nOrigin :");
        cnl.setId("textone");
        ObservableList<String> country = FXCollections.observableArrayList(countries);
        ComboBox<String> co = new ComboBox<>(country);
        if(coo!=null)   co.setValue(coo);
        grid.add(star[1], 0, 3);
        grid.add(cnl, 1, 3);
        grid.add(co, 2, 3);
        
        //4th row
        Label gl = new Label("Gender :");
        gl.setId("textone");
        ObservableList<String> Gender = FXCollections.observableArrayList("Male", "Female" ,"Others");
        ComboBox<String> gnd = new ComboBox<>(Gender);
        if(gndr!=null)   gnd.setValue(gndr);
        grid.add(star[2], 0, 4);
        grid.add(gl, 1, 4);
        grid.add(gnd, 2, 4);
        
        //5th Row
        Label bgl = new Label("Blood Group:");
        bgl.setId("textone");
        ObservableList<String> bg = FXCollections.observableArrayList("OP", "AP" ,"BP","ABP","ON", "AN" ,"BN","ABN");
        ComboBox<String> bgcb = new ComboBox<>(bg);
        if(bldgrp!=null)   bgcb.setValue(bldgrp);
        grid.add(star[3], 0, 5);
        grid.add(bgl, 1, 5);
        grid.add(bgcb, 2, 5);
        
        Label msg = new Label("indicates compulsory.");
        msg.setId("textone");
        grid.add(star[4], 1, 6);
        grid.setHalignment(star[4], HPos.RIGHT);
        grid.add(msg, 2, 6);
        
        //warn
        Label warn = new Label();
        grid.add(warn, 0, 7,4,1);
        warn.setId("textone");
        warn.setTextFill(Color.FIREBRICK);
        grid.setHalignment(warn, HPos.CENTER);
        
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            InsertFunctions.selectTable();
        });
        grid.add(back, 1, 8);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            if(fnt.getText()== null || co.getValue() == null || gnd.getValue()==null || bgcb.getValue()==null
                ||fnt.getText().length()==0 || fnt.getText().length()>50 || (lnt.getText()!=null && lnt.getText().length()>50)){
                warn.setText("Warning: Please enter valid inputs.");
            }
            else{
                firstnm = fnt.getText();
                lastnm = lnt.getText();
                countrynm = co.getValue();
                gender = gnd.getValue();
                bloodGroup = bgcb.getValue();
                insert2(year,month,day,passport,ethtrisecgrp,religion);
            }
        });
        grid.add(next, 2, 8);
        grid.setHalignment(next, HPos.RIGHT);
        back.setId("com-button");
        next.setId("com-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void insert2(String y, String m,String d,String p,String e, String r){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        int currentMonth = localDate.getMonthValue();
        int currentDay = localDate.getDayOfMonth();
        ArrayList<String> years = new ArrayList<>(201);
        years.add(null);
        for(int i=currentYear;i>=currentYear-200+1;i--){
            years.add(Integer.toString(i));
        }
        Hashtable<Integer, String> allMonths = new Hashtable<Integer, String>(12);
        allMonths.put(1, "JAN");
        allMonths.put(2, "FEB");
        allMonths.put(3, "MAR");
        allMonths.put(4, "APR");
        allMonths.put(5, "MAY");
        allMonths.put(6, "JUN");
        allMonths.put(7, "JUL");
        allMonths.put(8, "AUG");
        allMonths.put(9, "SEP");
        allMonths.put(10, "OCT");
        allMonths.put(11, "NOV");
        allMonths.put(12, "DEC");
        
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugee.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugee.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Refugee\n  Page(2/3)");
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        
        //1st row
        Label dobl = new Label("Date of Birth :");
        dobl.setId("textone");
        grid.add(dobl, 0, 1);
        
        //2nd  Row:
        HBox hb = new HBox(2);
        Label yearLab = new Label("Year:");
        yearLab.setId("textone");
        hb.getChildren().add(yearLab);
        ObservableList<String> yearVal = FXCollections.observableArrayList(years);
        ComboBox<String> yearsCB = new ComboBox<>(yearVal);
        if(year!=null)  yearsCB.setValue(year);
        hb.getChildren().add(yearsCB);
        Label monLab = new Label("Month:");
        monLab.setId("textone");
        hb.getChildren().add(monLab);
        ObservableList<String> monOB = FXCollections.observableArrayList();
        ComboBox<String> monCB = new ComboBox<>(monOB);
        if(month!=null)     monCB.setValue(month);
        hb.getChildren().add(monCB);
        ArrayList<String> months = new ArrayList<>();
        Label dayLab = new Label("Day:");
        dayLab.setId("textone");
        hb.getChildren().add(dayLab);
        ObservableList<String> days = FXCollections.observableArrayList();
        ComboBox<String> dayCB = new ComboBox<>(days);
        if(day!=null)   dayCB.setValue(day);
        hb.getChildren().add(dayCB);
        if(yearsCB.getValue()!=null){
            days.clear();
            monOB.clear();
            months.clear();
            if (Integer.parseInt(yearsCB.getValue()) == currentYear) {
                System.out.println(currentYear);
                System.out.println(currentMonth);
                for (int i = 1; i <= currentMonth; i++) {
                    months.add(allMonths.get(i));
                }
            } else {
                months.addAll(allMonths.values());
            }
            monOB.setAll(months);
        }
        if(monCB.getValue()!=null){
            days.clear();
            for (int i = 1; i < 32; i++) {
                if (i < 10) {
                    days.add("0" + String.valueOf(i));
                } else {
                    days.add(String.valueOf(i));
                }
            }
            if (yearsCB.getValue() != null) {
                int selYear = Integer.parseInt(yearsCB.getValue());

                if (monCB.getValue().equals("FEB")) {
                    if (selYear % 4 != 0) {
                        days.removeAll("29", "30", "31");
                    } else if (selYear % 400 == 0) {
                        days.removeAll("30", "31");
                    } else if (selYear % 100 == 0) {
                        days.removeAll("29", "30", "31");
                    } else {
                        days.removeAll("30", "31");
                    }
                } else {
                    String curMonth = allMonths.get(currentMonth);
                    if (selYear == currentYear && monCB.getValue().equals(curMonth)) {
                        days.clear();
                        for (int i = 1; i <= currentDay; i++) {
                            if (i < 10) {
                                days.add("0" + String.valueOf(i));
                            } else {
                                days.add(String.valueOf(i));
                            }
                        }
                    } else {
                        if (monCB.getValue().equals("APR") || monCB.getValue().equals("JUN") || monCB.getValue().equals("SEP") || monCB.getValue().equals("NOV")) {
                            days.remove("31");
                        }
                    }
                }

            }
        }
        yearsCB.setOnAction((ActionEvent event) -> {
            if(yearsCB.getValue()!=null){
                days.clear();
                monOB.clear();
                months.clear();
                if(Integer.parseInt(yearsCB.getValue())==currentYear){
                    System.out.println(currentYear);
                    System.out.println(currentMonth);
                    for(int i=1;i<=currentMonth;i++){
                        months.add(allMonths.get(i));
                    }
                }
                else{
                    months.addAll(allMonths.values());
                }
                monOB.setAll(months);
            }
            else{
                days.clear();
                monOB.clear();
                months.clear();
            }
        });
        monCB.setOnAction((ActionEvent event) -> {
            if(monCB.getValue()!=null){
                days.clear();
                for(int i=1;i<32;i++){
                    if(i<10){
                        days.add("0"+String.valueOf(i));
                    }
                    else{
                        days.add(String.valueOf(i));
                    }
                }
                System.out.println(days.size());
                if(yearsCB.getValue()!=null){
                    int selYear = Integer.parseInt(yearsCB.getValue());
                   
                    if(monCB.getValue().equals("FEB")){
                        if(selYear%4!=0){
                            days.removeAll("29","30","31");
                        }
                        else if(selYear%400==0){
                            days.removeAll("30","31");
                        }
                        else if(selYear%100==0){
                            days.removeAll("29","30","31");
                        }
                        else{
                            days.removeAll("30","31");
                        }
                    }
                    else{
                        String curMonth = allMonths.get(currentMonth);
                        if(selYear==currentYear && monCB.getValue().equals(curMonth)){
                            days.clear();
                            for(int i=1;i<=currentDay;i++){
                                if(i<10){
                                    days.add("0"+String.valueOf(i));
                                }
                                else{
                                    days.add(String.valueOf(i));
                                }
                            }
                        }
                        else{
                            if(monCB.getValue().equals("APR")||monCB.getValue().equals("JUN")||monCB.getValue().equals("SEP")||monCB.getValue().equals("NOV")){
                                days.remove("31");
                            }
                        }
                    }
                    
                }
            }
            else{
                days.clear();
            }
        });
        grid.add(hb, 0, 2,3,1);
        //3rd row
        Label ppl = new Label("Passport No:");
        ppl.setId("textone");
        TextField pptx= new TextField(); 
        pptx.setText(passport);
        Label ppmsg = new Label("At most 20 characters");  
        ppmsg.setId("textone");
        grid.add(ppl, 0, 3);
        grid.add(pptx, 1, 3);
        grid.add(ppmsg, 2, 3);
        //4th row
        Label etsgl = new Label("Ethnic/Tribal/\nSectarian Group:");
        etsgl.setId("textone");
        TextField etsgtx= new TextField();
        etsgtx.setText(e);
        Label etsgmsg = new Label("At most 50 characters");  
        etsgmsg.setId("textone");
        grid.add(etsgl, 0, 4);
        grid.add(etsgtx, 1, 4);
        grid.add(etsgmsg, 2, 4);
        //5th row
        Label rl = new Label("Religion :");
        rl.setId("textone");
        TextField rtx= new TextField();
        rtx.setText(religion);
        Label rmsg = new Label("At most 50 characters");  
        rmsg.setId("textone");
        grid.add(rl, 0, 5);
        grid.add(rtx, 1, 5);
        grid.add(rmsg, 2, 5);
        
        Label warn = new Label();
        warn.setTextFill(Color.FIREBRICK);
        warn.setId("textone");
        grid.add(warn, 0, 6,3,1);
        grid.setHalignment(warn, HPos.CENTER);
        
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            year = yearsCB.getValue();
            month = monCB.getValue();
            day = dayCB.getValue();
            passport = pptx.getText();
            ethtrisecgrp = etsgtx.getText();
            religion = rtx.getText();
            insert1(firstnm, lastnm, countrynm, gender, bloodGroup);
        });
        grid.add(back, 0, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText(null);
            if(yearsCB.getValue()!=null && (monCB.getValue()==null || dayCB.getValue()==null)){
                warn.setText("Please insert date of birth properly.");
            }
            else if((pptx.getText()!=null && pptx.getText().length()>20) 
                      || (etsgtx.getText()!=null && etsgtx.getText().length()>50) 
                      || (rtx.getText()!=null && rtx.getText().length()>50)){
                warn.setText("Please consider max size.");
            }
            else{
                year = yearsCB.getValue();
                month = monCB.getValue();
                day = dayCB.getValue();
                passport = pptx.getText();
                ethtrisecgrp = etsgtx.getText();
                religion = rtx.getText();
                imageInsert();
                //insert3(father,mother,legal,spouse);
            }
        });
        grid.add(next, 2, 7);
        back.setId("com-button");
        next.setId("com-button");
        root.setId("background-three");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    
    public static String colon(String s){
        if(s.indexOf("'")==0 && s.lastIndexOf("'")==s.length()-1)     return s;
        return "'"+s+"'";
    }
    
    public static void insert3(String f,String m, String l, String s) {
        grid = new GridPane();
        root = new BorderPane();
        root.setId("background-three");
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugee.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugee.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Refugee\n  Page(3/3)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        Label msg = new Label("Enter ID of accompanying people(If any)\n(If information of that person has not been\ninserted, update accordingly after inserting.)");
        grid.add(msg, 0, 1,3,1);
        msg.setId("textone");
        //4th row
        Label fid = new Label("Father's ID:");
        fid.setId("textone");
        TextField fidt = new TextField();
        fidt.setText(f);
        Label fmsg = new Label();
        fmsg.setTextFill(Color.RED);
        grid.add(fid, 0, 4);
        grid.add(fidt, 1, 4);
        grid.add(fmsg, 2, 4);
        //5th row
        Label mid = new Label("Mother's ID:");
        mid.setId("textone");
        TextField midt = new TextField();
        midt.setText(m);
        Label mmsg = new Label();
        mmsg.setTextFill(Color.RED);
        grid.add(mid, 0, 5);
        grid.add(midt, 1, 5);
        grid.add(mmsg, 2, 5);
        //6th row
        Label lid = new Label("Legal\nGuardian's\nID:");
        lid.setId("textone");
        TextField lidt = new TextField();
        lidt.setText(l);
        Label lmsg = new Label();
        lmsg.setTextFill(Color.RED);
        grid.add(lid, 0, 6);
        grid.add(lidt, 1, 6);
        grid.add(lmsg, 2, 6);
        //7th row
        Label sid = new Label("Spouse's ID:");
        sid.setId("textone");
        TextField sidt = new TextField();
        sidt.setText(s);
        Label smsg = new Label();
        smsg.setTextFill(Color.RED);
        grid.add(sid, 0, 7);
        grid.add(sidt, 1, 7);
        grid.add(smsg, 2, 7);
        //8th row for other sql exceptions
        warning = new Label();
        warning.setTextFill(Color.FIREBRICK);
        warning.setId("textone");
        grid.add(warning, 0, 8,3,1);
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            father = fidt.getText();
            mother= midt.getText();
            legal= lidt.getText();
            spouse= sidt.getText();
            insert2(year, month, day, passport, ethtrisecgrp, religion);
        });
        grid.add(back, 0, 9);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            warning.setText(null);
            try {
                con = Main.conn.con;
                String date = null;
                if(year!=null)  date = "'"+day + "-"+month+"-"+year+"'";
                /*
                firstnm = colon(firstnm);
                lastnm = (lastnm==null || lastnm.length()==0)?null:colon(lastnm);
                
                gender = colon(gender);
                bloodGroup = colon(bloodGroup);
                countrynm = colon(countrynm);
                passport = (passport==null || passport.length()==0)?null:colon(passport);
                ethtrisecgrp = (ethtrisecgrp==null)?null:colon(ethtrisecgrp);
                religion = (religion==null)?null:colon(religion);*/
                father = fidt.getText();
                if(father!=null && father.length()==0)  father = null;
                mother = midt.getText();
                if(mother!=null && mother.length()==0)  mother = null;
                legal = lidt.getText();
                if(legal!=null && legal.length()==0)  legal = null;
                spouse = sidt.getText();
                if(spouse!=null && spouse.length()==0)  spouse = null;
                String imgstr= null;
                if(imgUrl!=null)    imgstr="'"+imgUrl+"'";
                String sql = "insert into refugee values(refugee_seq.nextval," + colon(firstnm) + "," + ((lastnm==null || lastnm.length()==0)?null:colon(lastnm))
                        + "," + date +"," + colon(gender) + "," + colon(bloodGroup) + "," + colon(countrynm)+","+
                        ((passport==null || passport.length()==0)?null:colon(passport))
                        + "," + ((ethtrisecgrp==null)?null:colon(ethtrisecgrp)) + "," 
                        + ((religion==null)?null:colon(religion)) + "," + father + "," + mother + "," + spouse + "," + legal;
                sql = sql.toUpperCase();
                sql=sql+","+imgstr+")";
                System.out.println(sql);
                warning.setText("");
                PreparedStatement pst  =con.prepareStatement(sql);
                pst.executeUpdate();
                pst.close();
                insert4();
            } catch (SQLException ex) {
                warning.setText("Invalid Input");
                Logger.getLogger(refugee.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        grid.add(next, 2, 9);
        back.setId("com-button");
        next.setId("com-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void insert4(){
        try {
            HBox hb= new HBox();
            hb.setSpacing(10);
            ScrollPane scr= new ScrollPane();
            Connection con = Main.conn.con;
            ObservableList<String> nm = FXCollections.observableArrayList(
                    "Refugee ID", "First Name", "Last Name", "Date of Birth","Gender","Blood Group","Country of Origin",
                    "Passport No", "Ethnic/Tribal/Sectarian Group","Religion","ID of Father","ID of Mother"
                    ,"ID of Spouse","ID of Legal Guardian");
            PreparedStatement st = con.prepareStatement("Select * from refugee where refugee_id=(select max(refugee_id) from refugee)");
            ResultSet rs = st.executeQuery();
            rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            for(int i=1;i<15;i++){
                //nm.get(i-1) +":"+
                if(rs.getObject(i)==null)   vals.add(i-1,"");
                else if(i==4)   vals.add(i-1, rs.getObject(i).toString().substring(0, 10));
                else    vals.add(i-1, rs.getObject(i).toString());
                
            }
            String idref= rs.getString(1);
            String idspouse= rs.getString(13);
            Image image;
            if(imgUrl==null){
                image= new Image("/images/guest-user.jpg");
            }
            else{
                image= new Image("File:"+imgUrl);
            }
            
            //Image image = new Image("File:"+imgUrl);
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            iv1.setFitHeight(250);
            iv1.setFitWidth(400);
            iv1.setPreserveRatio(true);
            /*
            
 
         // simple displays ImageView the image as is
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
 
         // resizes the image to have width of 100 while preserving the ratio and using
         // higher quality filtering method; this ImageView is also cached to
         // improve performance


         // defines a viewport into the source image (achieving a "zoom" effect) and
         // displays it rotated
         Rectangle2D viewportRect = new Rectangle2D(40, 35, 110, 110);
         System.out.println(url);
         Group root = new Group();
         Scene scene = new Scene(root);
         scene.setFill(Color.BLACK);
         HBox box = new HBox();
         box.getChildren().add(iv1);
         root.getChildren().add(box);
            */
            grid = new GridPane();
            grid.setVgap(10);
            
            root = new BorderPane();
            root.setId("background-three");
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugee.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugee.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(refugee.class.getResource("scroll1.css").toExternalForm());
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            scr.setContent(grid);
            Label title = new Label("Inserted:");
            title.setUnderline(true);
            title.setFont(Font.font("System", FontWeight.BOLD, 40));
            
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            Label []col = new Label[14];
            Label []val = new Label[14];
            for(int i=0;i<14;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" "+nm.get(i)+" : ");
                col[i].setId("textone");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" "+vals.get(i)+" ");
                val[i].setId("textone");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
            }
            hb.getChildren().addAll(scr,iv1);
            GridPane vbox = new GridPane();
            vbox.setVgap(20);
            vbox.add(title, 0, 0,2,1);
            vbox.setAlignment(Pos.CENTER);
            vbox.add(hb, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = designs.button("Next");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            if(idspouse!=null){
                st= con.prepareStatement("update refugee set spouse_id="+idref+" where refugee_id="+idspouse);
                st.executeUpdate();
            }
            scr.setId("background-three");
            vbox.add(next, 0, 2);
            next.setId("com-button");
            root.setCenter(vbox);
            primaryStage.setScene(scene);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(refugee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    static ImageView imageView;
    static StackPane contentPane;
    static BorderPane layout;
    static Label pathl;
 
    public static void imageInsert() {
        //primaryStage.setTitle("Refugee");
        layout = new BorderPane();
        contentPane = new StackPane();
        Scene scene = new Scene(layout, 1100, 900);
        stage= primaryStage;
        layout.setId("background-three");
        scene.getStylesheets().add(refugee.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugee.class.getResource("css1.css").toExternalForm());
        Label title = new Label("Drag and Drop Image:");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid= new GridPane();
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        contentPane.setMaxSize(500, 300);
        contentPane.setPrefSize(500, 300);
        
        contentPane.setOnDragOver((final DragEvent event) -> {
            mouseDragOver(event);
        });
 
        contentPane.setOnDragDropped((final DragEvent event) -> {
            mouseDragDropped(event);
        });
 
         contentPane.setOnDragExited((final DragEvent event) -> {
             contentPane.setStyle("-fx-border-color: #C6C6C6;");
        });
 
        layout.setCenter(grid);
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            insert2(year, month, day, passport, ethtrisecgrp, religion);
        });
        grid.add(back, 0, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            insert3(father,mother,legal,spouse);
        });
        grid.add(title, 1, 0);
        grid.add(contentPane, 1, 1,1,5);
        contentPane.setId("border-one");
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pathl= new Label();
        pathl.setText("Image : ");
        pathl.setId("textone");
        grid.add(pathl,1,6,3,1);
        grid.add(next, 2,7);
        back.setId("com-button");
        next.setId("com-button");
        primaryStage.setScene(scene);
        primaryStage.show();
 
    }
 
    static void addImage(Image i, StackPane pane){
 
        imageView = new ImageView();
        imageView.setImage(i);
        pane.getChildren().add(imageView);
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
    }
    static  Stage stage;
    private static void mouseDragDropped(final DragEvent e) {
          final Dragboard db = e.getDragboard();
          boolean success = false;
          if (db.hasFiles()) {
              success = true;
              // Only get the first file from the list
              final File file = db.getFiles().get(0);
              Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                      System.out.println(file.getAbsolutePath());
                      try {
                          if(!contentPane.getChildren().isEmpty()){
                              contentPane.getChildren().remove(0);
                          }
                          Image img = new Image(new FileInputStream(file.getAbsolutePath()));  

                          addImage(img, contentPane);
                      } catch (FileNotFoundException ex) {
                          Logger.getLogger(refugee.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      pathl.setText(file.getAbsolutePath());
                      imgUrl=file.getAbsolutePath();
                      //show(file.getAbsolutePath());
                  }
              });
          }
          e.setDropCompleted(success);
          e.consume();
      }
 
    
  
    private static void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();
 
        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
 
        if (db.hasFiles()) {
            if (isAccepted) {
                contentPane.setStyle("-fx-border-color: red;"
              + "-fx-border-width: 5;"
              + "-fx-background-color: #C6C6C6;"
              + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }
}

