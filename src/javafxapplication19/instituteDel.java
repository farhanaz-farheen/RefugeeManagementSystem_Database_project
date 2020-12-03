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


public class instituteDel {
    static Stage primaryStage;
    static Scene scene;
    static BorderPane root;
    static GridPane grid;
    static String institutenm;
    static Connection con;
    public static void delete() {
        institutenm= null;
        con= Main.conn.con;
        primaryStage= Main.primaryStage;
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(instituteDel.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(instituteDel.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(instituteDel.class.getResource("fonts.css").toExternalForm());
        root.setId("background-seven");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter Institute Name to delete");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
        title.setTextFill(Color.WHITESMOKE);
        grid.add(title, 0, 0,3,1);
        Label inl = new Label("Institute Name :");
        inl.setId("texttwo");
        grid.add(inl, 0, 1);
        String btn = "Add";
        TextField intx = new TextField();
        grid.add(intx, 1, 1);
        Button inb = new Button(btn);
        grid.add(inb, 2, 1);
        inb.setOnAction((ActionEvent event) -> {
            String s= intx.getText();
            if(s!=null && s.length()>0)
                if (institutenm == null) {
                    institutenm = "in ('" + s;
                } else {
                    institutenm = institutenm + "','" + s;
                }
            intx.clear();
        });
        Button back = new Button("<< Back");
        back.setOnAction((ActionEvent event) -> {
            Main.AuthorityHomePage();
        });
        grid.add(back, 0, 2);
        Button next = new Button("Next >>");
        next.setOnAction((ActionEvent event) -> {
            String sql = "select i.institute_name,count(e.institute_id) \"Total Refugees\",l.city,l.state,l.country,i.institute_id\n" +
                        "from institute i left outer join educational_occupational_info e\n" +
                        "on(i.institute_id= e.institute_id)\n" +
                        "join location l\n" +
                        "on(i.location_id= l.location_id)";
            if(institutenm!=null){
                sql= sql+ " where i.institute_name "+institutenm+"')";
            }
            sql= sql + " group by i.institute_id,i.institute_name,l.city,l.state,l.country";
            delete2(sql.toUpperCase());
        });
        back.setId("bwbutton");
        next.setId("bwbutton");
        inb.setId("bluebutton");
        grid.add(next, 2, 2);
        root.setCenter(grid);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
    }
    public static void delete2(String sql) {
        System.out.println(sql);
        try {
            VBox vb= new VBox();
            vb.setSpacing(20);
            ScrollPane root1= new ScrollPane(vb);
            //vb.setPadding(new in);
            vb.setPadding(new Insets(30));
            Label dl= new Label("Select the row to delete");
            dl.setFont(Font.font("Arial", FontWeight.BOLD, 40));
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            grid = new GridPane();
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(instituteDel.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(instituteDel.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(instituteDel.class.getResource("fonts.css").toExternalForm());
            root.setId("background-seven");
            grid.setAlignment(Pos.CENTER);
            ArrayList<Button> btns= new ArrayList<>();
            Text []colnm= new Text[totcol];
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol-1;i++){
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
                //grid.setHalignment(colnm[i], HPos.CENTER);
                //grid.setValignment(colnm[i], VPos.CENTER);
            }
            /*
            for(int i=0;i<totcol-1;i++){
                colnm[i]= new Label(" "+rsm.getColumnName(i+1)+" ");
                colnm[i].setFont(Font.font("System", FontWeight.BOLD, 15));
                grid.add(colnm[i], i+1, 0);
                
            }*/
            ArrayList<String> comsl= new ArrayList<>();
            Button temp1= new Button();
            btns.add(temp1);
            int i=1;
            comsl.add(0,"");
            boolean nodt= true;
            while(rs.next()){
                nodt= false;
                comsl.add(i,rs.getString(totcol));
                for(int j=0;j<totcol-1;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" ");
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
                temp.setOnAction((ActionEvent event)->{
                    try {
                        int numpart= Integer.parseInt(temp.getText().substring(7, temp.getText().length()-1));
                        String sql1= "delete from educational_occupational_info where institute_id="+comsl.get(numpart);
                        System.out.println(sql);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                        sql1= "delete from institute where institute_id="+comsl.get(numpart);
                        System.out.println(sql);
                        stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Logger.getLogger(instituteDel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    delete2(sql);
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
                grid.add(sp,0 , 1,totcol,1);
            }
            Button next = new Button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            Button back = new Button("Back");
            back.setOnAction((ActionEvent event)->{
                delete();
            });
            root1.setContent(grid);
            root.setCenter(vb);
            vb.getChildren().addAll(dl,root1,back,next);
            back.setId("bwbutton");
            next.setId("bwbutton");
            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(instituteDel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
