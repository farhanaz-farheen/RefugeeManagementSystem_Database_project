/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 *
 * @author Oishi
 */
public class Commonquery {
    static Stage primaryStage = Main.primaryStage;
    public static void showRes(Connection con,String sql,String s) {
        
        try {
            primaryStage= Main.primaryStage;
            VBox vb= new VBox();
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(30));
            grid.setPadding(new Insets(30, 30, 30, 30));
            PreparedStatement   st= Main.conn.con.prepareStatement(sql.toUpperCase());
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            Label title = new Label("Query Results:");
            title.setUnderline(true);
            title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
            title.setTextFill(Color.WHITESMOKE);
            vb.getChildren().add(title);
            vb.setSpacing(10);
            Text []colnm= new Text[totcol];
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
                grid.add(sp, i, 0);
                sp.getChildren().add(colnm[i]);
                sp.setId("border-two");
                grid.setHalignment(colnm[i], HPos.CENTER);
                grid.setValignment(colnm[i], VPos.CENTER);
            }
            int i=1;
            while(rs.next()){
                grid.getRowConstraints().add(new RowConstraints(50));
                for(int j=0;j<totcol;j++){
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
                    grid.add(sp,j,i);/*
                    grid.setHalignment(sp, HPos.CENTER);
                    grid.setValignment(sp, VPos.CENTER);*/
                } 
                i++;
            }
            if(i==1){
                StackPane sp= new StackPane();
                sp.setId("border-one");
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                grid.getRowConstraints().add(new RowConstraints(50));
                Label nodata= new Label("No Data Found");
                nodata.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                sp.getChildren().add(nodata);
                grid.add(sp, 0, 1,totcol+1,1);
                grid.setHalignment(nodata, HPos.CENTER);
            }
            grid.setSnapToPixel(false);
            Button next = new Button("Go To Profile");
            next.setId("com-button");
            next.setOnAction((ActionEvent event) -> {
                Docprofile.profile(con, s);
            });
            vb.setAlignment(Pos.CENTER);
            ScrollPane root1 = new ScrollPane(grid);
            vb.getChildren().addAll(root1,next);
            BorderPane root= new BorderPane();
            root.setCenter(vb);
            root.setId("background-one");
            Scene scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(Commonquery.class.getResource("css1.css").toExternalForm());
            
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(Commonquery.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
/*
VBox vb= new VBox();
            vb.setPadding(new Insets(30));
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(30));
            PreparedStatement   st= Main.conn.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            Label title = new Label("Query Results:");
            Button btn = new Button("Profile");
            title.setUnderline(true);
            title.setFont(Font.font("System", FontWeight.BOLD, 18));
            vb.getChildren().addAll(title,grid);
            vb.setSpacing(10);
            grid.setGridLinesVisible(true);
            Label []colnm= new Label[totcol];
            for(int i=0;i<totcol;i++){
                colnm[i]= new Label(" "+rsm.getColumnName(i+1)+" ");
                colnm[i].setFont(Font.font("System", FontWeight.BOLD, 15));
                grid.add(colnm[i], i, 0);
            }
            int i=1;
            while(rs.next()){
                for(int j=0;j<totcol;j++)
                    grid.add(new Label(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" "),j,i);
                i++;
            }
            grid.add(btn,totcol,i+1);
            btn.setOnAction((ActionEvent event) -> {
                
            });
            ScrollPane root = new ScrollPane(vb);
            Scene scene = new Scene(root, 800, 550);
            primarystage.setScene(scene);

*/