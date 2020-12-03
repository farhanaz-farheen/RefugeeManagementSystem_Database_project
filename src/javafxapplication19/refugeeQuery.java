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
import javafx.scene.control.CheckBox;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class refugeeQuery {
    static Stage primaryStage;
    static Scene scene;
    static BorderPane root;
    static GridPane grid;
    static String refid = null;
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
    static Connection con;
    public static void query1() {
        primaryStage= Main.primaryStage;
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:\n(Press Add More for the corresponding\nvalue to be used and input more values)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,3,3);
        Label ridl = new Label("Refugee ID :");
        Label fnl = new Label("First Name :");
        Label lnl = new Label(" Last Name :");
        ridl.setFont(Font.font("System", FontWeight.BOLD, 20));
        lnl.setFont(Font.font("System", FontWeight.BOLD, 20));
        fnl.setFont(Font.font("System", FontWeight.BOLD, 20));
        grid.add(ridl, 0, 3);
        grid.add(fnl, 0, 4);
        grid.add(lnl, 0, 5);
        String btn = "ADD";
        TextField ridtx = new TextField();
        TextField fntx = new TextField();
        TextField lntx = new TextField();
        grid.add(ridtx, 1, 3);
        grid.add(fntx, 1, 4);
        grid.add(lntx, 1, 5);
        Button ridb = new Button(btn);
        Button fnb = new Button(btn);
        Button lnb = new Button(btn);
        grid.add(ridb, 2, 3);
        grid.add(fnb, 2, 4);
        grid.add(lnb, 2, 5);
        ridb.setOnAction((ActionEvent event) -> {
            String s= ridtx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(refid==null){
                        refid = "in("+s;
                    }
                    else{
                        refid = refid + ","+s;
                    }
                    ridtx.clear();
                }catch(NumberFormatException ex){

                }
        });
        fnb.setOnAction((ActionEvent event) -> {
            String s= fntx.getText();
            if(s!=null && s.length()>0){
                if(firstnm==null){
                    firstnm= "in('"+s.toLowerCase();
                }
                else{
                    firstnm=firstnm+"','"+s.toLowerCase();
                }
                fntx.clear();
            }
            
        });
        lnb.setOnAction((ActionEvent event) -> {
            String s= lntx.getText();
            if(s!=null && s.length()>0){
                if(lastnm==null){
                    lastnm= "in('"+s.toLowerCase();
                }
                else{
                    lastnm=lastnm+"','"+s.toLowerCase();
                }
                lntx.clear();
            }
            
        });
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            queryFunctions.selectTable();
        });
        grid.add(back, 0, 6);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            query2();
        });
        grid.add(next, 0, 7);
        scene.getStylesheets().add(refugeeQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(refugeeQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void query2() {
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(20);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:");
        title.setUnderline(true);
        scene.getStylesheets().add(refugeeQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(refugeeQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,2,1);
        Label bgl = new Label("Blood Group:");
        bgl.setFont(Font.font("System", FontWeight.BOLD, 20));
        ObservableList<String> bg = FXCollections.observableArrayList("OP", "AP" ,"BP","ABP","ON", "AN" ,"BN","ABN");
        GridPane g1= new GridPane();
        g1.add(bgl, 0, 0);
        g1.setVgap(15);
        CheckBox[] bgcb = new CheckBox[8];
        for(int i=0;i<8;i++){
            bgcb[i]= new CheckBox(bg.get(i));
            g1.add(bgcb[i], 0, i+1);
        }
        CheckBox []gnd= new CheckBox[3];
        gnd[0]= new CheckBox("Male");
        gnd[1]= new CheckBox("Female");
        gnd[2]= new CheckBox("Others");
        GridPane g2= new GridPane();
        Label ggg = new Label("Gender:");
        g2.add(ggg, 0, 0);
        ggg.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        g2.setVgap(15);
        for(int i=0;i<3;i++){
            g2.add(gnd[i], 0, i+1);
        }
        grid.add(g1, 0, 1);
        grid.add(g2, 1, 1);
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            query1();
        });
        grid.add(back, 0, 2);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            for(int i=0;i<8;i++){
                if(bgcb[i].isSelected()){
                    if(bloodGroup==null)    bloodGroup="in('"+bgcb[i].getText();
                    else     bloodGroup= bloodGroup+"','"+bgcb[i].getText();
                }
            }
            for(int i=0;i<3;i++){
                if(gnd[i].isSelected()){
                    if(gender==null)    gender="in('"+gnd[i].getText();
                    else     gender= gender+"','"+gnd[i].getText();
                }
            }
            query3();
        });
        grid.add(next, 1, 2);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void query3() {
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:\n(Press Add More for the corresponding\nvalue to be used and input more values)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,3,3);
        String btn = "ADD";
        scene.getStylesheets().add(refugeeQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(refugeeQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        int i=3;
        Label cl = new Label("Country :");
        cl.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField ctx = new TextField();
        Button cb = new Button(btn);
        grid.add(cl,0, i);
        grid.add(ctx, 1, i);
        grid.add(cb, 2, i);
        i++;
        Label pl = new Label("Passport No :");
        pl.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField ptx = new TextField();
        Button pb = new Button(btn);
        grid.add(pl,0, i);
        grid.add(ptx, 1, i);
        grid.add(pb, 2, i);
        i++;
        Label etsl = new Label("Ethnic/Tribal\n/Sectarian\nGroup:");
        TextField etstx = new TextField();
        etsl.setFont(Font.font("System", FontWeight.BOLD, 20));
        Button etsb = new Button(btn);
        grid.add(etsl,0, i);
        grid.add(etstx, 1, i);
        grid.add(etsb, 2, i);
        i++;
        Label rl = new Label("Religion :");
        rl.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField rtx = new TextField();
        Button rb = new Button(btn);
        grid.add(rl,0, i);
        grid.add(rtx, 1, i);
        grid.add(rb, 2, i);
        i++;
        cb.setOnAction((ActionEvent event) -> {
            String s= ctx.getText();
            if(s!= null && s.length()>0){
                if(countrynm==null){
                    countrynm= "in('"+s;
                }
                else{
                    countrynm= countrynm+"','"+s;
                }
                ctx.clear();
            }
        });
        pb.setOnAction((ActionEvent event) -> {
            String s= ptx.getText();
            if(s!= null && s.length()>0){
                if(passport==null){
                    passport= "in('"+s;
                }
                else{
                    passport= passport+"','"+s;
                }
                ptx.clear();
            }
        });
        etsb.setOnAction((ActionEvent event) -> {
            String s= etstx.getText();
            if(s!= null && s.length()>0){
                if(ethtrisecgrp==null){
                    ethtrisecgrp= "in('"+s;
                }
                else{
                    ethtrisecgrp= ethtrisecgrp+"','"+s;
                }
                etstx.clear();
            }
        });
        rb.setOnAction((ActionEvent event) -> {
            String s= rtx.getText();
            if(s!= null && s.length()>0){
                if(religion==null){
                    religion= "in('"+s;
                }
                else{
                    religion= religion+"','"+s;
                }
                rtx.clear();
            }
        });
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            query2();
        });
        grid.add(back, 0, i);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            query4();
        });
        grid.add(next, 2, i);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void query4() {
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        scene.getStylesheets().add(refugeeQuery.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeQuery.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:\n(Press Add More for the corresponding\nvalue to be used and input more values)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,3,3);
        String btn= "ADD";
        int i=3;
        String text = "ID of Father :";
        Label fl = new Label(text);
        fl.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField ftx= new TextField();
        Button fb= new Button(btn);
        grid.add(fl, 0, i);
        grid.add(ftx, 1, i);
        grid.add(fb, 2, i);
        fb.setOnAction((ActionEvent event) -> {
            String s= ftx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(father==null){
                        father = "in("+s;
                    }
                    else{
                        father = father + ","+s;
                    }
                    ftx.clear();
                }catch(NumberFormatException ex){

                }
        });
        i++;
        text= "ID of Mother:";
        Label ml = new Label(text);
        ml.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField mtx= new TextField();
        Button mb= new Button(btn);
        grid.add(ml, 0, i);
        grid.add(mtx, 1, i);
        grid.add(mb, 2, i);
        mb.setOnAction((ActionEvent event) -> {
            String s= mtx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(mother==null){
                        mother = "in("+s;
                    }
                    else{
                        mother = mother + ","+s;
                    }
                    mtx.clear();
                }catch(NumberFormatException ex){

                }
        });
        i++;
        text= "ID of Spouse:";
        Label sl = new Label(text);
        sl.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField stx= new TextField();
        Button sb= new Button(btn);
        grid.add(sl, 0, i);
        grid.add(stx, 1, i);
        grid.add(sb, 2, i);
        sb.setOnAction((ActionEvent event) -> {
            String s= stx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(spouse==null){
                        spouse = "in("+s;
                    }
                    else{
                        spouse = spouse + ","+s;
                    }
                    stx.clear();
                }catch(NumberFormatException ex){

                }
        });
        i++;
        text= "ID of Legal\nGuardian :";
        Label ll = new Label(text);
        ll.setFont(Font.font("System", FontWeight.BOLD, 20));
        TextField ltx= new TextField();
        Button lb= new Button(btn);
        grid.add(ll, 0, i);
        grid.add(ltx, 1, i);
        grid.add(lb, 2, i);
        lb.setOnAction((ActionEvent event) -> {
            String s= ltx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(legal==null){
                        legal = "in("+s;
                    }
                    else{
                        legal = legal + ","+s;
                    }
                    ltx.clear();
                }catch(NumberFormatException ex){

                }
        });
        i++;
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            query2();
        });
        grid.add(back, 0, i);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            String sql1="select r.refugee_id,r.first_name,r.last_name,r.gender,r.date_of_birth,r.bloodgroup,r.country_of_origin,r.passport_no,r.religion,r.ethnic_tribal_sectarian_group,r.father_id,r.mother_id,r.spouse_id,r.legalguardian_id,f.first_name||' '||f.last_name \"Name of Father\",m.first_name||' '||m.last_name \"Name of Mother\",s.first_name||' '||s.last_name \"Name of Spouse\",l.first_name||' '||l.last_name \"Name of Legal Guardian\"\n" +
                    "from refugee r left outer join refugee f on(r.father_id=f.refugee_id)\n" +
                    "left outer join refugee m on(r.mother_id= m.refugee_id)\n" +
                    "left outer join refugee s on(r.spouse_id=s.refugee_id)\n" +
                    "left outer join refugee l on (r.legalguardian_id= l.refugee_id)";
            String sql= " where ";
            if(refid!=null)     sql= sql+"r.refugee_id "+refid+") or ";
            if(father!=null)     sql= sql+"r.father_id "+father+") or ";
            if(mother!=null)     sql= sql+"r.mother_id "+mother+") or ";
            if(spouse!=null)     sql= sql+"r.spouse_id "+spouse+") or ";
            if(legal!=null)     sql= sql+"r.legalguardian_id "+legal+") or ";
            if(firstnm!=null)     sql= sql+"r.first_name "+firstnm+"') or ";
            if(lastnm!=null)     sql= sql+"r.last_name "+lastnm+"') or ";
            if(countrynm!=null)     sql= sql+"r.country_of_origin "+countrynm+"') or ";
            if(gender!=null)     sql= sql+"r.gender "+gender+"') or ";
            if(bloodGroup!=null)     sql= sql+"r.bloodgroup "+bloodGroup+"') or ";
            if(religion!=null)     sql= sql+"r.religion "+religion+"') or ";
            if(passport!=null)     sql= sql+"r.passport_no "+passport+"') or ";
            if(ethtrisecgrp!=null)     sql= sql+"r."
                + "ethnic_tribal_sectarian_group "+ethtrisecgrp+"') or ";
            if(sql.equals(" where ")){
                query5(sql1);
            }   
            else{
                sql1= sql1+ sql.substring(0,sql.length()-4);
                query5(sql1.toUpperCase());
            }
        });
        grid.add(next, 2, i);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void query5(String sql) {
        try {
            System.out.println(sql);
            con= Main.conn.con;
            VBox vb= new VBox();
            vb.setSpacing(20);
            root= new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugeeQuery.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeQuery.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            primaryStage.setScene(scene);
            root.setCenter(vb);
            vb.setPadding(new Insets(30));
            grid = new GridPane();
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
                    int numpart = Integer.parseInt(temp.getText().substring(8,temp.getText().length()-3));
                    querydetails(comsl.get(numpart),sql);
                });
                //btns.add(i,temp);
                grid.add(temp, 0, i);
                i++;
            }
            Button back = new Button("Back");
            Button next = new Button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            back.setOnAction((ActionEvent event)->{
                queryFunctions.selectTable();
            });
            vb.getChildren().addAll(dl,root1,back,next);
            back.setId("com-button");
            next.setId("com-button");
        } catch (SQLException ex) {
            Logger.getLogger(refugeeQuery.class.getName()).log(Level.SEVERE, null, ex);
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
                    "Refugee ID", "First Name", "Last Name", "Date of Birth","Gender","Blood Group","Country of Origin",
                    "Passport No", "Ethnic/Tribal/Sectarian Group","Religion","ID of Father","ID of Mother"
                    ,"ID of Spouse","ID of Legal Guardian");
            PreparedStatement st = con.prepareStatement("Select * from refugee where refugee_id="+idref);
            ResultSet rs = st.executeQuery();
            rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            for(int i=1;i<15;i++){
                //nm.get(i-1) +":"+
                if(rs.getObject(i)==null)   vals.add(i-1,"");
                else if(i==4)   vals.add(i-1, rs.getObject(i).toString().substring(0, 10));
                else    vals.add(i-1, rs.getObject(i).toString());
                
            }
            String sp;
            sp = rs.getString(15);
            System.out.println(sp);
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
            grid = new GridPane();
            
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(refugeeUpdate.class.getResource("scroll1.css").toExternalForm());
            root.setId("background-three");
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Details:");
            title.setUnderline(true);
            title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
            ScrollPane scr= new ScrollPane(grid);
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            Label []col = new Label[14];
            Label []val = new Label[14];
            for(int i=0;i<14;i++){
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
            }
            hb.add(scr, 0, 0);
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
            primaryStage.setScene(scene);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(refugeeUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
