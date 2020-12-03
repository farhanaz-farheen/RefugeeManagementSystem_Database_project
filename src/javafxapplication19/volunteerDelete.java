
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


public class volunteerDelete {
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
        root.setId("background-two");
        scene.getStylesheets().add(volunteerDelete.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Delete Volunteer Info");
        title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
        title.setTextFill(Color.WHITE);
        grid.add(title, 0, 0,3,1);
        Label inl = new Label("First Name : ");
        inl.setId("textone");
        inl.setTextFill(Color.WHITESMOKE);
        grid.add(inl, 0, 1);
        Label lnl= new Label(" Last Name : ");
        lnl.setId("textone");
        lnl.setTextFill(Color.WHITESMOKE);
        grid.add(lnl, 0, 2);
        String btn = "Add More";
        TextField intx = new TextField();
        grid.add(intx, 1, 1);
        TextField lntx = new TextField();
        grid.add(lntx, 1, 2);
        Button inb = new Button(btn);
        inb.setId("bluebutton");
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
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            deleteFunctions.selectTable();
        });
        grid.add(back, 0, 4);
        Button next = designs.button("Next");
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
            String sql = "select * from volunteer";
            if(frstnm!=null){
                sql= sql+ " where volunteer_firstname "+frstnm+")";
            }
            if(lastnm!=null){
                if(frstnm!=null){
                    sql= sql+ " or volunteer_lastname "+lastnm+")";
                }
                else{
                    sql= sql+ " where volunteer_lastname "+lastnm+")";
                }
            }
            delete2(sql.toUpperCase());
        });
        grid.add(next, 2, 4);
        back.setId("bluebutton");
        next.setId("bluebutton");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void delete2(String sql) {
        try {
            VBox vb= new VBox();
            vb.setSpacing(20);
            grid = new GridPane();
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            root.setId("background-two");
            scene.getStylesheets().add(volunteerDelete.class.getResource("css1.css").toExternalForm());
            primaryStage.setScene(scene);
            //vb.setPadding(new in);
            vb.setPadding(new Insets(30));
            Label dl= new Label("Select the row to delete\n\n");
            dl.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            dl.setTextFill(Color.WHITESMOKE);
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            Text []colnm= new Text[totcol];
            ArrayList<Button> btns= new ArrayList<>();
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol;i++){
                StackPane sp= new StackPane();
                sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                colnm[i]= new Text("  "+rsm.getColumnName(i+1)+"  ");
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
            boolean flg= true;
            while(rs.next()){
                flg= false;
                
                comsl.add(i,rs.getString(1));
                for(int j=0;j<totcol;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":"  "+rs.getString(j+1)+"  ");
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
                    grid.add(sp,j+1,i);
                }
                Button temp = new Button("Select#"+i+":");
                temp.setId("bwbutton");
                temp.setOnAction((ActionEvent event)->{
                    try {
                        int numpart= Integer.parseInt(temp.getText().substring(7, temp.getText().length()-1));
                        String sql1= "delete from volunteer where volunteer_id="+comsl.get(numpart);
                        System.out.println(sql1);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Logger.getLogger(volunteerDelete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    delete2(sql);
                });
                btns.add(i,temp);
                grid.add(btns.get(i), 0, i);
                i++;
            }
            if(flg){
                Label nodata= new Label("No Data Found");
                nodata.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                grid.add(nodata, 0, 1,totcol+1,1);
                grid.setHalignment(nodata, HPos.CENTER);
            }
            Button next = new Button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            next.setId("com-button");
            Button back= new Button("Back");
            back.setOnAction((ActionEvent event)->{
                delete();
            });
            back.setId("com-button");
            ScrollPane root1= new ScrollPane(grid);
            vb.getChildren().addAll(dl,root1,back,next);
            vb.setAlignment(Pos.CENTER);
            root.setCenter(vb);
        } catch (SQLException ex) {
            Logger.getLogger(volunteerDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
