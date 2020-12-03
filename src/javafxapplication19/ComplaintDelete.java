
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

public class ComplaintDelete {
    static String refugeeid;
    static Stage primarystage = Main.primaryStage;
    public static void comdel(Connection con){
        refugeeid=null;
        BorderPane root = new BorderPane();
        Scene scene= new Scene(root, 1100, 900);
        scene.getStylesheets().add(ComplaintDelete.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(ComplaintDelete.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(ComplaintDelete.class.getResource("fonts.css").toExternalForm());
        root.setId("background-five");
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Delete From Complaint Section:");
        title.setUnderline(true);
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
        title.setTextFill(Color.WHITESMOKE);
        gridpane.add(title,0,0,3,1);
        //Button btn = new Button("name");
        Text greet = new Text("Please enter the ID of refugee who was accused:");
        greet.setId("textfive");
        Text refid = new Text("Refugee ID (Accused):");
        refid.setId("texttwo");
        TextField ref = new TextField();
        Button back = new Button("Back");
        Button add = new Button("Add");
        Button don = new Button("Done");
        Label warn = new Label();
        warn.setId("textfour");

        gridpane.add(greet, 0, 1,3,1);
        gridpane.add(refid, 0, 2);
        gridpane.add(ref, 1, 2);
        gridpane.add(add, 2, 2);
        gridpane.add(warn, 0, 3,3,1);
        gridpane.add(don, 2, 4);
        gridpane.add(back,0,4);
        back.setOnAction((ActionEvent event) -> {
            deleteFunctions.selectTable();
        });
        add.setOnAction((ActionEvent event) -> {
            if (ref.getText().length() <= 0) {
                warn.setText("Please enter something!");
            } else {
                int u;
                u = 0;
                try {
                    Integer.parseInt(ref.getText());
                } catch (NumberFormatException ex) {
                    warn.setText("Please enter valid ID!");
                    u = 1;
                }
                if (u == 0) {
                    warn.setText(null);
                    if (refugeeid == null) {
                        refugeeid = ref.getText();
                    } else {
                        refugeeid = refugeeid + "," + ref.getText();
                    }
                }
            }
            ref.clear();
        });
        don.setOnAction((ActionEvent event) -> {
            String sql = "SELECT C.COMPLAINT_AGAINST \"Complaint Against ID\", C.MISDEED MISDEED,R.REFUGEE_ID COMPLAINER_REFUGEE_ID,V.VOLUNTEER_ID COMPLAINER_VOLUNTEER_ID,(R.FIRST_NAME||' '||R.LAST_NAME) COMPLAINER_REFUGEE_NAME,(V.VOLUNTEER_FIRSTNAME||' '||V.VOLUNTEER_LASTNAME) COMPLAINER_VOLUNTEER_NAME,C.COMPLAINT_SERIAL_NUMBER COMSERIAL\n"
                    + "FROM COMPLAINT C LEFT OUTER JOIN REFUGEE R\n"
                    + "ON (C.COMPLAINT_BY_REFUGEE=R.REFUGEE_ID)\n"
                    + "LEFT OUTER JOIN VOLUNTEER V\n"
                    + "ON (C.COMPLAINT_BY_VOLUNTEER=V.VOLUNTEER_ID)";
            if (refugeeid != null) {
                sql = sql + " WHERE C.COMPLAINT_AGAINST IN (" + refugeeid + ")";

            }
            System.out.println(sql);
            comdel1(con, sql.toUpperCase());
        });
        add.setId("bluebutton");
        back.setId("bwbutton");
        don.setId("bwbutton");
        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
    public static void comdel1(Connection con,String sql) {
        try {
            VBox vb= new VBox();
            vb.setSpacing(20);
            BorderPane root = new BorderPane();
            Scene scene= new Scene(root, 1100, 900);
            scene.getStylesheets().add(ComplaintDelete.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(ComplaintDelete.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(ComplaintDelete.class.getResource("fonts.css").toExternalForm());
            root.setId("background-five");
            primarystage.setScene(scene);
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
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            Text []colnm= new Text[totcol];
            ArrayList<Button> btns= new ArrayList<>();
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol-1;i++){
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
                comsl.add(i,rs.getString("COMSERIAL"));
                for(int j=0;j<totcol-1;j++){
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
                        int numpart= Integer.parseInt(temp.getText().substring(7,temp.getText().length()-1));
                        String sql1= "delete from complaint where COMPLAINT_SERIAL_NUMBER="+comsl.get(numpart);
                        System.out.println(sql1);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                    } catch (SQLException ex) {
                        Logger.getLogger(volunteerDelete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comdel1(con, sql);
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
                comdel(con);
            });
            back.setId("com-button");
            ScrollPane root1= new ScrollPane(grid);
            vb.getChildren().addAll(dl,root1,back,next);
            vb.setAlignment(Pos.CENTER);
            root.setCenter(vb);
            
        } catch (SQLException ex) {
            Logger.getLogger(ComplaintDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
VBox vb= new VBox();
            vb.setSpacing(10);
            ScrollPane root= new ScrollPane(vb);
            Scene scene = new Scene(root, 700, 500);
            primarystage.setScene(scene);
            //vb.setPadding(new in);
            vb.setPadding(new Insets(30));
            Label dl= new Label("Select the row to delete");
            
            
            
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(30));
            grid.setGridLinesVisible(true);
            Label []colnm= new Label[totcol-1];
            ArrayList<Button> btns= new ArrayList<>();
            for(int i=0;i<totcol-1;i++){
                colnm[i]= new Label(" "+rsm.getColumnName(i+1)+" ");
                colnm[i].setFont(Font.font("System", FontWeight.BOLD, 15));
                grid.add(colnm[i], i+1, 0);
                
            }
            ArrayList<String> comsl= new ArrayList<>();
            Button temp1= new Button();
            btns.add(temp1);
            int i=1;
            comsl.add(0,"");
            while(rs.next()){
                comsl.add(i,rs.getString("COMSERIAL"));
                for(int j=0;j<totcol-1;j++)
                    grid.add(new Label(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" "),j+1,i);
                Button temp = new Button("Select#"+i+":");
                temp.setOnAction((ActionEvent event)->{
                    try {
                        String sql1= "delete from complaint where complaint_serial_number="+comsl.get(temp.getText().charAt(temp.getText().length()-2)-'0');
                        System.out.println(sql);
                        PreparedStatement stp= con.prepareStatement(sql1);
                        stp.executeUpdate(sql1);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(checkupDelete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comdel1(con,sql);
                });
                btns.add(i,temp);
                grid.add(btns.get(i), 0, i);
                i++;
            }
            
            Button next = designs.button("Home");
            next.setOnAction((ActionEvent event)->{
                Main.AuthorityHomePage();
            });
            vb.getChildren().addAll(dl,grid,next);
*/