/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

//import java.awt.Image;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


class Connect {

    public Connection con;

    public void CreateConnection(String user, String pass) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB1", user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CloseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class Main extends Application {

    public static Connect conn;
    public static Stage primaryStage;
     static int scrHeight;
    static int scrWidth;
    static int line;
    
    static  ArrayList<String> countries;
    static Dimension screenSize;
    @Override
    public void start(Stage primaryStage) {
        countries= new ArrayList<>(250);
        try {
            File f = new File("D:\\Lecture slides , pdfs\\2 2\\216\\Project\\countries");
            BufferedReader in  = new BufferedReader(new FileReader(f));
            String s;
            while((s=in.readLine())!=null){
                countries.add(s);
            }
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Refugee Registration Management");
            primaryStage.show();
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            scrHeight= (int) (screenSize.getHeight()-200);
            scrWidth= (int) (screenSize.getWidth()-200);
            
            home1();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void home1(){
        BorderPane root = new BorderPane();
        
        //root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, 1100, 900); 
               
        //scene.getStylesheets().add("C:\\Users\\Oishi\\Desktop\\mycss\\style.css");
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("buttongr.css").toExternalForm());
        GridPane gridpane = new GridPane();
        Text greet = new Text();
        Text greet1 = new Text();
        Text sub = new Text();
        root.setId("pane");
        Text dummy = new Text("\n\n\n\n\n\n");
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(50, 50, 50, 50));
        //Button btn = designs.button("PROCEED");
        greet.setFont(Font.font("System", FontWeight.BOLD, 45));
        greet1.setFont(Font.font("System", FontWeight.BOLD, 45));
        sub.setFont(Font.font("System", FontWeight.BOLD,FontPosture.ITALIC,24));
        sub.setFill(Color.RED);
        gridpane.add(greet,0,0);
        gridpane.add(greet1,0,1);
        gridpane.add(sub,0,2);
        gridpane.add(dummy,0,3);
        //btn.setMaxSize(120, 200);
        //gridpane.add(btn, 0, 8);
        //gridpane.add(btn2, 0, 2);
        //gridpane.add(btn3, 0, 3);
        //btn.setAlignment(Pos.CENTER);
        
        
        
        Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
line=0;
timeline.getKeyFrames().add(
new KeyFrame(javafx.util.Duration.seconds(1), (ActionEvent event) -> {
line++;
if(line==1||line==2||line==3)
    greet.setText("REFUGEE MANAGEMENT");
if(line==2||line==3)
greet1.setText("DATABASE");
if(line==3)
sub.setText("Fighting the Humanitarian Crisis");
if (line > 3) {
timeline.stop();
homePage();
}
} // KeyFrame event handler
));
timeline.playFromStart();
        
        
        
        
        
        ObservableList<Node> ch = gridpane.getChildren();
        for (int i = 0; i < ch.size(); i++) {
            gridpane.setHalignment(ch.get(i), HPos.CENTER);
            gridpane.setValignment(ch.get(i), VPos.CENTER);
        }
        root.setCenter(gridpane);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        //primaryStage.setFullScreen(true);
        //btn.setOnAction((ActionEvent event) -> {
          // homePage();
       // });
    }

    public static void homePage() {
        /*
        Pane root= new Pane();
        Scene scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(Main.class.getResource("backgrounds.css").toExternalForm());
        root.setId("background-three");
        Button btn = new Button("");
        Button btn2 = designs.button("");
        Button btn3= designs.button("");
        btn.setId("authback");
        btn2.setId("dnoback");
        btn3.setId("guestback");
        btn.setPrefSize(310, 270);
        btn2.setPrefSize(310, 270);
        btn3.setPrefSize(310, 270);
        btn.setAlignment(Pos.CENTER);
        btn2.setAlignment(Pos.CENTER);
        btn.setLayoutX(80);
        btn.setLayoutY(100);
        btn2.setLayoutX(430);
        btn2.setLayoutY(320);
        btn3.setLayoutX(780);
        btn3.setLayoutY(540);
        root.getChildren().addAll(btn,btn2,btn3);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("BLABLA");
        btn.setOnAction((ActionEvent event) -> {
            login page1 = new login();
            page1.fields(conn.con);
        });
        btn2.setOnAction((ActionEvent event) -> {
            DocLogin.doclog(conn.con);
        });
        btn3.setOnAction((ActionEvent event) -> {
            GeneralQuery.querystuff(conn.con);
        });*/
        BorderPane root= new BorderPane();
        GridPane grid= new GridPane();
        grid.setHgap(40);
        grid.setVgap(50);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        root.setCenter(grid);
        Scene scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(Main.class.getResource("backgrounds.css").toExternalForm());
        root.setId("background-six");
        Button btn = new Button("");
        Button btn2 = designs.button("");
        Button btn3= designs.button("");
        btn.setId("authback");
        btn2.setId("dnoback");
        btn3.setId("guestback");
        btn.setPrefSize(310, 270);
        btn2.setPrefSize(310, 270);
        btn3.setPrefSize(310, 270);
        btn.setAlignment(Pos.CENTER);
        btn2.setAlignment(Pos.CENTER);
        /*
        btn.setLayoutX(80);
        btn.setLayoutY(100);
        btn2.setLayoutX(430);
        btn2.setLayoutY(320);
        btn3.setLayoutX(780);
        btn3.setLayoutY(540);*/
        Label title= new Label("Main Menu");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 80));
        //primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        
        grid.add(title, 0, 0,3,1);
        grid.setHalignment(title, HPos.CENTER);
        grid.add(btn, 0, 1);
        grid.add(btn2, 1, 1);
        grid.add(btn3, 2, 1);
        btn.setOnAction((ActionEvent event) -> {
            login page1 = new login();
            page1.fields(conn.con);
        });
        btn2.setOnAction((ActionEvent event) -> {
            DocLogin.doclog(conn.con);
        });
        btn3.setOnAction((ActionEvent event) -> {
            GeneralQuery.querystuff(conn.con);
        });
    }
    

    public static void AuthorityHomePage() {
        BorderPane borderPane = new BorderPane();
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(borderPane, 1100, 900);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
        grid.setId("panepk");
        Text greet = new Text("WELCOME");
        Text greet1 = new Text("TO");
        Text greet2 = new Text("YOUR");
        Text greet3 = new Text("PROFILE");
        
        greet.setFill(Color.WHITESMOKE);
        greet1.setFill(Color.WHITESMOKE);
        greet2.setFill(Color.WHITESMOKE);
        greet3.setFill(Color.WHITESMOKE);
        
        grid.add(greet,0,0);
        grid.add(greet1,0,1);
        grid.add(greet2,0,2);
        grid.add(greet3,0,3);
        greet.setFont(Font.font("System", FontWeight.BOLD, 40));
        greet1.setFont(Font.font("System", FontWeight.BOLD, 40));
        greet2.setFont(Font.font("System", FontWeight.BOLD, 40));
        greet3.setFont(Font.font("System", FontWeight.BOLD, 40));
     ToolBar toolbar = new ToolBar();
     HBox statusbar = new HBox();
     Node appContent = new ListView();
     ObservableList<String> items = FXCollections.observableArrayList("Insert", "Search","Update","Delete","Logout");
        ListView<String> list = new ListView<>(items);
        list.getStylesheets().addAll(Main.class.getResource("list.css").toExternalForm());
     //borderPane.set(toolbar);
     list.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						// change the label text value to the newly selected
						// item.
						//label.setText("You Selected " + newValue);
                                                if(newValue.equals("Insert")){
                                                    InsertFunctions.selectTable();
                                                }else if(newValue.equals("Search")){
                                                    queryFunctions.selectTable();
                                                }else if(newValue.equals("Update")){
                                                    updateFunctions.updatePage();
                                                }else if(newValue.equals("Delete")){
                                                    deleteFunctions.selectTable();
                                                }else if(newValue.equals("Logout")){
                                                    homePage();
                                                }
					}
				});

		//listViewPanel.getChildren().addAll(m_listView, label);
		//root.getChildren().addAll(listViewPanel);
     borderPane.setCenter(grid);
     borderPane.setLeft(list);
     borderPane.setBottom(statusbar);
    
    //borderpane.setCenter();
    primaryStage.setScene(scene);

    }
    
    

    public static void main(String[] args) throws SQLException {
        conn = new Connect();
        conn.CreateConnection("parry", "allen");
        launch(args);
        conn.CloseConnection();
    }
}
