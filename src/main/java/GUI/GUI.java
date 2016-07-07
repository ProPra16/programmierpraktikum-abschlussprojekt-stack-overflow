package GUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUI extends Application implements EventHandler<ActionEvent>{
	Stage fenster;
	Scene scMenu;
	Scene scList;
	Scene scTest;
	Scene scCode;
	Button btnNormal;
	Button btnBabystep;
	Button btnQuitxtTest;
	Button btnQuitxtCode;
	Button btnMenu;
	Button btnNextxtTest;
	Button btnNextxtCode;
	Button btnTimer;
	Label lbTitel;
	Label lbCredits1;	
	Label lbRepeat;
	Label lbRepeatBlue;
	Label lbCredits2;
	Label lbList;
	Label lbTest;
	Label lbCode;
	Label lbListb;
	Label lbCodeb;
	Label lbTestb;
	TextArea txtTest;
	TextArea txtCode;
	int breite;
	int hoehe;
	int status; //status ist 1, wenn der Compile-Vorgang funktioniert hat
	Controller ctrl;
	TableView table;


	
	@Override
	public void start(Stage arg0) throws Exception {
		fenster = arg0;
		arg0.setTitle("TDDT");
		ctrl = new Controller();
		table = new TableView();



		txtTest = new TextArea();
		txtCode = new TextArea();
		//Muss noch angepasst werden!


		lbTitel = new Label("TDD-Trainer");
		lbTitel.setTranslateX(5);
		lbTitel.setTranslateY(-160);
		lbTitel.getStyleClass().add("Label_Titel");
		
		lbCredits1 = new Label("*Made by: Stack-Overflow*");
		lbCredits1.setTranslateX(5);
		lbCredits1.setTranslateY(160);
		
		lbCredits2 = new Label("*Made by: Marc Hilgenberg,Leonard Hack,Kelly Williams,Benno Bongaertz*");
		lbCredits2.setTranslateX(600);
		lbCredits2.setTranslateY(580);
		
		lbList = new Label("1.Choose Exercise");
		lbList.setTranslateX(200);
		lbList.setTranslateY(50);
		lbList.setFont(new Font("Arial", 15));
		lbList.setTextFill(Color.web("#FFFFFF"));
		lbListb = new Label("1.Choose Exercise");
		lbListb.setTranslateX(200);
		lbListb.setTranslateY(50);
		lbListb.setFont(new Font("Arial", 15));
		lbListb.setTextFill(Color.web("#2196F3"));
		
		lbTest = new Label("2.Write a Test");
		lbTest.setTranslateX(400);
		lbTest.setTranslateY(50);
		lbTest.setFont(new Font("Arial", 15));
		lbTest.setTextFill(Color.web("#FFFFFF"));
		lbTestb = new Label("2.Write a Test");
		lbTestb.setTranslateX(400);
		lbTestb.setTranslateY(50);
		lbTestb.setFont(new Font("Arial", 15));
		lbTestb.setTextFill(Color.web("#2196F3"));
		
		lbCode = new Label("3.Write the Code");
		lbCode.setTranslateX(600);
		lbCode.setTranslateY(50);
		lbCode.setFont(new Font("Arial", 15));
		lbCode.setTextFill(Color.web("#FFFFFF"));
		lbCodeb = new Label("3.Write the Code");
		lbCodeb.setTranslateX(600);
		lbCodeb.setTranslateY(50);
		lbCodeb.setFont(new Font("Arial", 15));
		lbCodeb.setTextFill(Color.web("#2196F3"));
		
		lbRepeat = new Label("4.Repeat");
		lbRepeat.setTranslateX(800);
		lbRepeat.setTranslateY(50);
		lbRepeat.setFont(new Font("Arial", 15));
		lbRepeat.setTextFill(Color.web("#FFFFFF"));
		lbRepeatBlue = new Label("4.Repeat");
		lbRepeatBlue.setTranslateX(800);
		lbRepeatBlue.setTranslateY(50);
		lbRepeatBlue.setFont(new Font("Arial", 15));
		lbRepeatBlue.setTextFill(Color.web("#2196F3"));
		
		btnNormal = new Button();
		btnNormal.setText("Normal-Codes");
		btnNormal.setTranslateX(5);
		btnNormal.setTranslateY(-90);
		btnNormal.setOnAction(this);
		btnNormal.setId("button");

		
		btnBabystep = new Button();
		btnBabystep.setText("Babysteps-Codes");
		btnBabystep.setTranslateX(5);
		btnBabystep.setTranslateY(0);
		btnBabystep.setOnAction(this);
		btnBabystep.setId("button");
		
		btnQuitxtTest = new Button();
		btnQuitxtTest.setText("Quit");
		btnQuitxtTest.setTranslateX(5);
		btnQuitxtTest.setTranslateY(90);
		btnQuitxtTest.setOnAction(this);
		btnQuitxtTest.setId("button");
		
		btnQuitxtCode = new Button();
		btnQuitxtCode.setText("Quit");
		btnQuitxtCode.setTranslateX(10);
		btnQuitxtCode.setTranslateY(510);
		btnQuitxtCode.setOnAction(this);
		btnQuitxtCode.setId("button");
		
		btnMenu = new Button();
		btnMenu.setText("Menu");
		btnMenu.setTranslateX(375);
		btnMenu.setTranslateY(510);
		btnMenu.setOnAction(this);
		btnMenu.setId("button");
		
		btnNextxtTest = new Button();
		btnNextxtTest.setText("Next");
		btnNextxtTest.setTranslateX(600);
		btnNextxtTest.setTranslateY(510);
		btnNextxtTest.setId("button");
		
		btnNextxtCode = new Button();
		btnNextxtCode.setText("Next");
		btnNextxtCode.setTranslateX(600);
		btnNextxtCode.setTranslateY(510);
		btnNextxtCode.setId("button");
		
		btnTimer = new Button();
		btnTimer.setText("*Timer*");
		btnTimer.setTranslateX(500);
		btnTimer.setTranslateY(510);


		
		StackPane rahmen = new StackPane();
		rahmen.getChildren().addAll(lbTitel,lbCredits1,btnNormal,btnBabystep,btnQuitxtTest);
		scMenu = new Scene(rahmen, 200, 400);
		scMenu.getStylesheets().add("GUI/stylesheetscMenu.css");
		arg0.setScene(scMenu);
		arg0.show();
		
	}

	public void handle(ActionEvent event){
		if(event.getSource()==btnNormal){
			breite = 1000;
			hoehe = 600;
			scList = new Scene(create(1));
			scList.getStylesheets().add("GUI/stylesheetSCX.css");
			fenster.setScene(scList);
		}
		if(event.getSource()==btnBabystep){
			breite = 1000;
			hoehe = 600;
			scList = new Scene(create(2));
			scList.getStylesheets().add("GUI/stylesheetSCX.css");
			fenster.setScene(scList);
		}
		if(event.getSource()==btnQuitxtTest){
			System.exit(0);
		}
		if(event.getSource()==btnQuitxtCode){
			System.exit(0);
		}
		if(event.getSource()==btnMenu){
			fenster.setScene(scMenu);
			fenster.setFullScreen(false);
		}
		if(event.getSource()==btnNextxtTest){
			if(status == 1){
				breite = 1000;
				hoehe = 600;
				scTest = new Scene(editorTest());
				scTest.getStylesheets().add("GUI/stylesheetSCX.css");
				fenster.setScene(scTest);

				}
			else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("");
				alert.setHeaderText("How...?");
				alert.setContentText("This should not happen...");
				alert.showAndWait();
			}
		}
		if(event.getSource()==btnNextxtCode){
			if(status == 1){
				breite = 1000;
				hoehe = 600;
				scCode = new Scene(editorCode());
				scCode.getStylesheets().add("GUI/stylesheetSCX.css");
				fenster.setScene(scCode);

			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("");
				alert.setHeaderText("How...?");
				alert.setContentText("This should not happen...");
				alert.showAndWait();
			}
		}
	}


	private Parent create(int a) {
		ctrl.setupTable(table,a);
		table.setPrefWidth(breite*0.75);
		table.setPrefHeight(hoehe*0.6);
		table.setLayoutX(breite*0.1);
		table.setLayoutY(hoehe*0.2);

		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRepeat,lbCredits2,lbListb,lbTest,lbCode,btnQuitxtCode,btnMenu,btnNextxtTest,table);
        if(a == 1){

        	//Reader fuer nicht-Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	status = 1; //spaeter dann Compile-Code
        	btnNextxtTest.setOnAction(this);
        	
        }
        if(a == 2){
        	//Reader fuer Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	status = 1; //spaeter dann Compile-Code
        	btnNextxtTest.setOnAction(this);
        }
       
        return root;
		
		
	}
	
	private Parent editorTest() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRepeat,lbCredits2,lbList,lbTestb,lbCode,txtTest,txtCode,btnQuitxtCode,btnMenu,btnNextxtCode,btnTimer);
		txtTest.setPrefWidth(breite*0.35);
		txtTest.setPrefHeight(hoehe*0.6);
		txtTest.setLayoutX(breite*0.1);
		txtTest.setLayoutY(hoehe*0.2);
		txtTest.setText("Test");
		txtCode.setPrefWidth(breite*0.35);
		txtCode.setPrefHeight(hoehe*0.6);
		txtCode.setLayoutX(breite*0.6);
		txtCode.setLayoutY(hoehe*0.2);
		txtCode.setText("Code");
		txtCode.setEditable(false);
		txtTest.setEditable(true);
		System.out.println("test writing stage");
		status = 1; //spaeter dann Compile-Code
		btnNextxtCode.setOnAction(this);
		return root;
	}

	private Parent editorCode() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRepeat,lbCredits2,lbList,lbTest,lbCodeb,txtCode,txtTest,btnQuitxtCode,btnMenu,btnNextxtTest,btnTimer);
		txtTest.setPrefWidth(breite*0.35);
		txtTest.setPrefHeight(hoehe*0.6);
		txtTest.setLayoutX(breite*0.6);
		txtTest.setLayoutY(hoehe*0.2);
		txtTest.setText("Test");
		txtTest.setEditable(false);
		txtCode.setEditable(true);
		txtCode.setPrefWidth(breite*0.35);
		txtCode.setPrefHeight(hoehe*0.6);
		txtCode.setLayoutX(breite*0.1);
		txtCode.setLayoutY(hoehe*0.2);
		txtCode.setText("Code");
		status = 1; //spaeter dann Compile-Code
		System.out.println("code writing stage");
		btnNextxtTest.setOnAction(this);
		return root;
	}
}
