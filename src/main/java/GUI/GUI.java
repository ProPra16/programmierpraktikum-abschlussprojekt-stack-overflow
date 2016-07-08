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
	Scene scATDD;
	Scene scRefactoring;
	Button btnNormal;
	Button btnBabystep;
	Button btnQuitxtTest;
	Button btnQuitCode;
	Button btnMenu;
	Button btnNextTest;
	Button btnNextCode;
	Button btnNextATDD;
	Button btnNextRefactoring;
	Button btnTimer;
	Button btnBackTest;
	Button btnBackATDD;
	Button btnSaveAndMenu;
	Button btnSaveAndTest;
	Button btnSaveAndATDD;
	Button btnCheckATDD;
	Button btnCheckList;
	Button btnCheckCODE;
	Button btnCheckTest;
	Button btnCheckRefactoring;
	Label lbTitel;
	Label lbCredits1;	
	Label lbRefactoring;
	Label lbRefactoringb;
	Label lbCredits2;
	Label lbList;
	Label lbTest;
	Label lbCode;
	Label lbATDD;
	Label lbListb;
	Label lbCodeb;
	Label lbTestb;
	Label lbATDDb;
	TextArea txtTest;
	TextArea txtCode;
	TextArea txtATDD;
	TextArea txtCompileMsg;
	int breite;
	int hoehe;
	int status; //status ist 1, wenn der Compile-Vorgang funktioniert hat
	Controller ctrl;
	TableView table;

	boolean firstTest,firstCode;


	
	@Override
	public void start(Stage arg0) throws Exception {
		fenster = arg0;
		fenster.setResizable(false);
		arg0.setTitle("TDDT");
		ctrl = new Controller();
		table = new TableView();

		firstTest = true;
		firstCode = true;

		txtTest = new TextArea();
		txtCode = new TextArea();
		txtATDD = new TextArea();
		txtCompileMsg = new TextArea();
		//Muss noch angepasst werden!


		lbTitel = new Label("TDD-Trainer");
		lbTitel.setTranslateX(5);
		lbTitel.setTranslateY(-160);
		lbTitel.getStyleClass().add("Label_Titel");
		
		lbCredits1 = new Label("*Made by: Stack-Overflow*");
		lbCredits1.setTranslateX(5);
		lbCredits1.setTranslateY(160);
		
		lbCredits2 = new Label("*Made by: Marc Hilgenberg,Leonard Hack,Kelly Williams,Benno Bongaertz*");
		lbCredits2.setTranslateX(500);
		lbCredits2.setTranslateY(580);
		
		lbList = new Label("1.Choose Exercise");
		lbList.setTranslateX(150);
		lbList.setTranslateY(50);
		lbList.setFont(new Font("Arial", 15));
		lbList.setTextFill(Color.web("#FFFFFF"));
		lbListb = new Label("1.Choose Exercise");
		lbListb.setTranslateX(150);
		lbListb.setTranslateY(50);
		lbListb.setFont(new Font("Arial", 15));
		lbListb.setTextFill(Color.web("#2196F3"));
		
		lbATDD = new Label("2.Write a ATDD");
		lbATDD.setTranslateX(300);
		lbATDD.setTranslateY(50);
		lbATDD.setFont(new Font("Arial", 15));
		lbATDD.setTextFill(Color.web("#FFFFFF"));
		lbATDDb = new Label("2.Write a ATDD");
		lbATDDb.setTranslateX(300);
		lbATDDb.setTranslateY(50);
		lbATDDb.setFont(new Font("Arial", 15));
		lbATDDb.setTextFill(Color.web("#2196F3"));
		
		lbTest = new Label("3.Write a Test");
		lbTest.setTranslateX(450);
		lbTest.setTranslateY(50);
		lbTest.setFont(new Font("Arial", 15));
		lbTest.setTextFill(Color.web("#FFFFFF"));
		lbTestb = new Label("3.Write a Test");
		lbTestb.setTranslateX(450);
		lbTestb.setTranslateY(50);
		lbTestb.setFont(new Font("Arial", 15));
		lbTestb.setTextFill(Color.web("#2196F3"));
		
		lbCode = new Label("4.Write the Code");
		lbCode.setTranslateX(600);
		lbCode.setTranslateY(50);
		lbCode.setFont(new Font("Arial", 15));
		lbCode.setTextFill(Color.web("#FFFFFF"));
		lbCodeb = new Label("4.Write the Code");
		lbCodeb.setTranslateX(600);
		lbCodeb.setTranslateY(50);
		lbCodeb.setFont(new Font("Arial", 15));
		lbCodeb.setTextFill(Color.web("#2196F3"));
		
		lbRefactoring = new Label("5.Refactoring");
		lbRefactoring.setTranslateX(750);
		lbRefactoring.setTranslateY(50);
		lbRefactoring.setFont(new Font("Arial", 15));
		lbRefactoring.setTextFill(Color.web("#FFFFFF"));
		lbRefactoringb = new Label("5.Refactoring");
		lbRefactoringb.setTranslateX(750);
		lbRefactoringb.setTranslateY(50);
		lbRefactoringb.setFont(new Font("Arial", 15));
		lbRefactoringb.setTextFill(Color.web("#2196F3"));
		
		btnCheckList = new Button();
		btnCheckList.setText("   ");
		btnCheckList.setTranslateX(200);
		btnCheckList.setTranslateY(20);
		btnCheckList.setOnAction(this);
		btnCheckList.setId("button_red");
		
		btnCheckATDD = new Button();
		btnCheckATDD.setText("   ");
		btnCheckATDD.setTranslateX(340);
		btnCheckATDD.setTranslateY(20);
		btnCheckATDD.setOnAction(this);
		btnCheckATDD.setId("button_red");
		
		btnCheckTest = new Button();
		btnCheckTest.setText("   ");
		btnCheckTest.setTranslateX(490);
		btnCheckTest.setTranslateY(20);
		btnCheckTest.setOnAction(this);
		btnCheckTest.setId("button_red");
		
		btnCheckCODE = new Button();
		btnCheckCODE.setText("   ");
		btnCheckCODE.setTranslateX(650);
		btnCheckCODE.setTranslateY(20);
		btnCheckCODE.setOnAction(this);
		btnCheckCODE.setId("button_red");
		
		btnCheckRefactoring = new Button();
		btnCheckRefactoring.setText("   ");
		btnCheckRefactoring.setTranslateX(790);
		btnCheckRefactoring.setTranslateY(20);
		btnCheckRefactoring.setOnAction(this);
		btnCheckRefactoring.setId("button_red");
		
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
		
		btnQuitCode = new Button();
		btnQuitCode.setText("Quit");
		btnQuitCode.setTranslateX(20);
		btnQuitCode.setTranslateY(535);
		btnQuitCode.setOnAction(this);
		btnQuitCode.setId("button");
		
		btnMenu = new Button();
		btnMenu.setText("Menu");
		btnMenu.setLayoutX(20);
		btnMenu.setLayoutY(40);
		btnMenu.setOnAction(this);
		btnMenu.setId("button");
		
		btnNextTest = new Button();
		btnNextTest.setText("Next");
		btnNextTest.setTranslateX(905);
		btnNextTest.setTranslateY(535);
		btnNextTest.setId("button");
		
		btnBackTest = new Button();
		btnBackTest.setText("Back");
		btnBackTest.setTranslateX(810);
		btnBackTest.setTranslateY(535);
		btnBackTest.setId("button");
		
		btnNextCode = new Button();
		btnNextCode.setText("Next");
		btnNextCode.setTranslateX(905);
		btnNextCode.setTranslateY(535);
		btnNextCode.setId("button");
		
		btnNextATDD = new Button();
		btnNextATDD.setText("Next");
		btnNextATDD.setTranslateX(905);
		btnNextATDD.setTranslateY(535);
		btnNextATDD.setId("button");
		
		btnBackATDD = new Button();
		btnBackATDD.setText("Back");
		btnBackATDD.setTranslateX(810);
		btnBackATDD.setTranslateY(535);
		btnBackATDD.setId("button");
		
		btnNextRefactoring = new Button();
		btnNextRefactoring.setText("Next");
		btnNextRefactoring.setTranslateX(905);
		btnNextRefactoring.setTranslateY(535);
		btnNextRefactoring.setId("button");
		
		btnSaveAndTest = new Button();
		btnSaveAndTest.setText("Save&Test");
		btnSaveAndTest.setTranslateX(850);
		btnSaveAndTest.setTranslateY(535);
		btnSaveAndTest.setId("button");
		
		btnSaveAndATDD = new Button();
		btnSaveAndATDD.setText("Save&ATDD");
		btnSaveAndATDD.setTranslateX(700);
		btnSaveAndATDD.setTranslateY(535);
		btnSaveAndATDD.setId("button");
		
		btnSaveAndMenu = new Button();
		btnSaveAndMenu.setText("Save&Menu");
		btnSaveAndMenu.setTranslateX(550);
		btnSaveAndMenu.setTranslateY(535);
		btnSaveAndMenu.setId("button");
		
		
		btnTimer = new Button();
		btnTimer.setText("*Timer*");
		btnTimer.setTranslateX(480);
		btnTimer.setTranslateY(535);


		
		StackPane rahmen = new StackPane();
		rahmen.getChildren().addAll(lbTitel,lbCredits1,btnNormal,btnBabystep,btnQuitxtTest);
		scMenu = new Scene(rahmen, 200, 400);
		scMenu.getStylesheets().add("GUI/stylesheetSC1.css");
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
		if(event.getSource()==btnQuitCode){
			System.exit(0);
		}
		if(event.getSource()==btnMenu || event.getSource()==btnSaveAndMenu){
			txtCode.setText("");
			txtTest.setText("");
			btnCheckList.setId("button_red");
			btnCheckATDD.setId("button_red");
			btnCheckTest.setId("button_red");
			btnCheckCODE.setId("button_red");
			btnCheckRefactoring.setId("button_red");
			txtCompileMsg.setText("");
			fenster.setScene(scMenu);
			fenster.setFullScreen(false);
			firstCode = true;
			firstTest = true;
			txtATDD.setText("");
		}
		if(event.getSource()==btnNextATDD || event.getSource()==btnBackATDD || event.getSource()==btnSaveAndATDD){
				btnCheckList.setId("button_green");
				breite = 1000;
				hoehe = 600;
				scATDD = new Scene(editorATDD());
				scATDD.getStylesheets().add("GUI/stylesheetSCX.css");
				fenster.setScene(scATDD);

				}
		if(event.getSource()==btnNextRefactoring){
			breite = 1000;
			hoehe = 600;
			scRefactoring = new Scene(editorRefactoring());
			scRefactoring.getStylesheets().add("GUI/stylesheetSCX.css");
			fenster.setScene(scRefactoring);

			}
		if(event.getSource() == btnSaveAndTest || event.getSource() == btnSaveAndATDD) {
			firstCode = false;
			firstTest = false;
		}
		if(event.getSource()==btnNextTest || event.getSource()==btnBackTest || event.getSource()==btnSaveAndTest){
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
		if(event.getSource()==btnNextCode){
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


	private Parent editorRefactoring() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoringb,lbCredits2,lbList,lbTest,lbCode,lbATDD,txtCode,txtTest,txtCompileMsg,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnSaveAndATDD,btnSaveAndTest,btnSaveAndMenu,btnMenu);
		txtTest.setPrefWidth(breite*0.45);
		txtTest.setPrefHeight(300);
		txtTest.setLayoutX(20);
		txtTest.setLayoutY(hoehe*0.19);
		txtCode.setPrefWidth(breite*0.43);
		txtCode.setPrefHeight(300);
		txtCode.setLayoutX(breite*0.55);
		txtCode.setLayoutY(hoehe*0.19);
		txtCompileMsg.setLayoutX(20);
		txtCompileMsg.setLayoutY(427);
		txtCompileMsg.setPrefWidth(960);
		txtCompileMsg.setPrefHeight(100);
		txtCompileMsg.setEditable(false);
		txtCompileMsg.setText("error code hier...");
		txtTest.setEditable(true);
		txtCode.setEditable(true);

		status = 1; //spaeter dann Compile-Code
		System.out.println("Refactoring-Stage");
		btnSaveAndATDD.setOnAction(this);
		btnSaveAndMenu.setOnAction(this);
		btnSaveAndTest.setOnAction(this);
		return root;
	}

	private Parent editorATDD() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTest,lbCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,lbATDDb,txtATDD,txtCompileMsg,btnQuitCode,btnMenu,btnNextTest);
		txtATDD.setPrefWidth(breite*0.96);
		txtATDD.setPrefHeight(300);
		txtATDD.setLayoutX(20);
		txtATDD.setLayoutY(hoehe*0.19);
		txtCompileMsg.setLayoutX(20);
		txtCompileMsg.setLayoutY(427);
		txtCompileMsg.setPrefWidth(960);
		txtCompileMsg.setPrefHeight(100);
		txtCompileMsg.setEditable(false);
		txtCompileMsg.setText("error code hier...");
		txtTest.setEditable(false);
		status = 1; //spaeter dann Compile-Code
		System.out.println("ATDD writing stage");
		btnNextTest.setOnAction(this);
		return root;
	}

	private Parent create(int a) {
		ctrl.setupTable(table,a);
		table.setPrefWidth(breite*0.75);
		table.setPrefHeight(hoehe*0.6);
		table.setLayoutX(breite*0.1);
		table.setLayoutY(hoehe*0.2);

		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbListb,lbTest,lbCode,lbATDD,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnMenu,btnNextATDD,table);
        if(a == 1){

        	//Reader fuer nicht-Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	status = 1; //spaeter dann Compile-Code
        	btnNextATDD.setOnAction(this);
        	
        }
        if(a == 2){
        	//Reader fuer Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	status = 1; //spaeter dann Compile-Code
        	btnNextATDD.setOnAction(this);
        }
       
        return root;
		
		
	}
	
	private Parent editorTest() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTestb,lbCode,lbATDD,txtTest,txtCode,txtCompileMsg,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnMenu,btnNextCode,btnBackATDD,btnTimer);
		txtTest.setPrefWidth(breite*0.45);
		txtTest.setPrefHeight(300);
		txtTest.setLayoutX(20);
		txtTest.setLayoutY(hoehe*0.19);
		String test = "";
		for(int i=0;i<ctrl.getCurExc().getTestClassContent().size();i++) {
			test += ctrl.getCurExc().getTestClassContent().get(i) + "\n";
		}
		if(firstTest) {
			txtTest.setText(test);
		}
		txtCode.setPrefWidth(breite*0.43);
		txtCode.setPrefHeight(300);
		txtCode.setLayoutX(breite*0.55);
		txtCode.setLayoutY(hoehe*0.19);
		txtCompileMsg.setLayoutX(20);
		txtCompileMsg.setLayoutY(427);
		txtCompileMsg.setPrefWidth(960);
		txtCompileMsg.setPrefHeight(100);
		txtCompileMsg.setEditable(false);
		txtCompileMsg.setText("error code hier...");
		txtCode.setEditable(false);
		txtTest.setEditable(true);

		System.out.println("test writing stage");
		status = 1; //spaeter dann Compile-Code
		btnNextCode.setOnAction(this);
		btnBackATDD.setOnAction(this);
		return root;
	}

	private Parent editorCode() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTest,lbCodeb,lbATDD,txtCode,txtTest,txtCompileMsg,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnMenu,btnNextRefactoring,btnBackTest,btnTimer);
		txtTest.setPrefWidth(breite*0.45);
		txtTest.setPrefHeight(300);
		txtTest.setLayoutX(20);
		txtTest.setLayoutY(hoehe*0.19);
		txtCode.setPrefWidth(breite*0.43);
		txtCode.setPrefHeight(300);
		txtCode.setLayoutX(breite*0.55);
		txtCode.setLayoutY(hoehe*0.19);
		String code = "";
		for(int i=0;i<ctrl.getCurExc().getClassContent().size();i++) {
			code += ctrl.getCurExc().getClassContent().get(i) + "\n";
		}
		if(firstCode) {
			txtCode.setText(code);
		}
		txtCompileMsg.setLayoutX(20);
		txtCompileMsg.setLayoutY(427);
		txtCompileMsg.setPrefWidth(960);
		txtCompileMsg.setPrefHeight(100);
		txtCompileMsg.setEditable(false);
		txtCompileMsg.setText("error code hier...");
		txtTest.setEditable(false);
		txtCode.setEditable(true);

		status = 1; //spaeter dann Compile-Code
		System.out.println("code writing stage");
		btnNextRefactoring.setOnAction(this);
		btnBackTest.setOnAction(this);
		return root;
	}
}
