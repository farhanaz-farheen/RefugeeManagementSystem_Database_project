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
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class refugeeDelete {
    static Stage primaryStage;
    static Scene scene;
    static BorderPane root;
    static GridPane grid;
    static Connection con;
    static  String frstnm,lastnm;
    public static void delete() {
        con= Main.conn.con;
        primaryStage= Main.primaryStage;
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugeeDelete.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugeeDelete.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter Refugee Name to delete");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0,3,1);
        Label inl = new Label("First Name : ");
        inl.setId("textone");
        grid.add(inl, 0, 1);
        Label lnl= new Label(" Last Name : ");
        lnl.setId("textone");
        grid.add(lnl, 0, 2);
        String btn = "Add More";
        TextField intx = new TextField();
        intx.setText(frstnm);
        grid.add(intx, 1, 1);
        TextField lntx = new TextField();
        lntx.setText(lastnm);
        grid.add(lntx, 1, 2);
        Button inb = new Button(btn);
        inb.setId("greenbtn");
        grid.add(inb, 1, 3);
        inb.setOnAction((ActionEvent event) -> {
            String s= intx.getText();
            if(s!=null && s.length()>0)
                if (frstnm== null) {
                    frstnm = "in ('" + s+"'";
                } else {
                    frstnm = frstnm + ",'" + s+"'";
                }
            intx.clear();
            s= lntx.getText();
            if(s!=null && s.length()>0)
                if (lastnm== null) {
                    lastnm = "in ('" + s+"'";
                } else {
                    lastnm = lastnm + ",'" + s+"'";
                }
            lntx.clear();
        });
        Button back = new Button("<< Back");
        back.setOnAction((ActionEvent event) -> {
            deleteFunctions.selectTable();
        });
        grid.add(back, 0, 4);
        Button next = new Button("Next >>");
        next.setOnAction((ActionEvent event) -> {
            String s= intx.getText();
            if(s!=null && s.length()>0)
                if (frstnm== null) {
                    frstnm = "in ('" + s+"'";
                } else {
                    frstnm = frstnm + ",'" + s+"'";
                }
            intx.clear();
            s= lntx.getText();
            if(s!=null && s.length()>0)
                if (lastnm== null) {
                    lastnm = "in ('" + s+"'";
                } else {
                    lastnm = lastnm + ",'" + s+"'";
                }
            lntx.clear();
            String sql = "select refugee_id,first_name,last_name,gender,date_of_birth,bloodgroup,country_of_origin,passport_no,religion,ethnic_tribal_sectarian_group,father_id,mother_id,spouse_id,legalguardian_id\n" +
            "from refugee";
            if(frstnm!=null){
                sql= sql+ " where first_name "+frstnm+")";
            }
            if(lastnm!=null){
                if(frstnm!=null){
                    sql= sql+ " or last_name "+lastnm+")";
                }
                else{
                    sql= sql+ " where last_name "+lastnm+")";
                }
            }
            delete2(sql.toUpperCase());
        });
        back.setId("bwbutton");
        next.setId("bwbutton");
        grid.add(next, 2, 4);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void delete2(String sql) {
        try {
            VBox vb= new VBox();
            vb.setSpacing(20);
            root= new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugeeDelete.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeDelete.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            root.setCenter(vb);
            vb.setPadding(new Insets(30));
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            ScrollPane root1= new ScrollPane(grid);
            
            Label dl= new Label("Select the row to delete");
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
            boolean nodata= true;
            while(rs.next()){
                nodata= false;
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
                Button temp = new Button("Delete#"+i+" >>");
                grid.setHalignment(temp, HPos.RIGHT);
                temp.setId("bwbutton");
                temp.setOnAction((ActionEvent event)->{
                    try {
                        int numpart= Integer.parseInt(temp.getText().substring(7, temp.getText().length()-3));
                        System.out.println(comsl.get(numpart));
                        String sql1= "delete from refugee where refugee_id="+comsl.get(numpart);
                        System.out.println(sql1);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Logger.getLogger(refugeeDelete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    delete2(sql);
                });
                //btns.add(i,temp);
                grid.add(temp, 0, i);
                i++;
            }
            if(nodata){
                grid.getRowConstraints().add(new RowConstraints(50));
                StackPane sp= new StackPane();
                sp.setId("border-one");
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                Label nodatal= new Label("No Data Found");
                nodatal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                sp.getChildren().add(nodatal);
                grid.add(sp,0 , 1,totcol+1,1);
            }
            Button back = new Button("Back");
            Button next = new Button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            back.setOnAction((ActionEvent event)->{
                delete();
            });
            vb.getChildren().addAll(dl,root1,back,next);
            back.setId("com-button");
            next.setId("com-button");
            
        } catch (SQLException ex) {
            Logger.getLogger(refugeeDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
