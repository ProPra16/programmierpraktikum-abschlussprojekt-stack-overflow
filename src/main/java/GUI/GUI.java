package GUI;
import XMLParser.Excercise;
import XMLParser.XMLReader;
import XMLParser.XMLWriter;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;


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
	Label lbTimer;
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
	Button btnCompilen;
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
	TextArea txtInfo;
	int breite;
	int hoehe;
	int status; //status ist 1, wenn der Compile-Vorgang funktioniert hat
	Controller ctrl;
	TableView table;

	boolean firstTest,firstCode;
	boolean ATDDCheck = false;
	boolean CompileCheck = false;

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
		txtInfo = new TextArea();

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
		
		btnCompilen = new Button();
		btnCompilen.setText("Compile");
		btnCompilen.setTranslateX(468);
		btnCompilen.setTranslateY(370);
		btnCompilen.setId("button");
		
		
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
		
		
		lbTimer = new Label();
		lbTimer.setText("");
		lbTimer.setTranslateX(420);
		lbTimer.setTranslateY(545);
		lbTimer.setId("lblTimer");


		
		StackPane rahmen = new StackPane();
		rahmen.getChildren().addAll(lbTitel,lbCredits1,btnNormal,btnBabystep,btnQuitxtTest);
		scMenu = new Scene(rahmen, 200, 400);
		scMenu.getStylesheets().add("stylesheetSC1.css");
		arg0.setScene(scMenu);
		arg0.show();
		
	}

	public void handle(ActionEvent event){
		if(event.getSource()==btnNormal){
			breite = 1000;
			hoehe = 600;
			scList = new Scene(create(1));
			scList.getStylesheets().add("stylesheetSCX.css");
			fenster.setScene(scList);
		}
		if(event.getSource()==btnBabystep){
			breite = 1000;
			hoehe = 600;
			scList = new Scene(create(2));
			scList.getStylesheets().add("stylesheetSCX.css");
			fenster.setScene(scList);
		}
		if(event.getSource()==btnQuitxtTest){
			System.exit(0);
		}
		if(event.getSource()==btnQuitCode){
			System.exit(0);
		}
		if(event.getSource() == btnSaveAndATDD || event.getSource() == btnSaveAndMenu || event.getSource() == btnSaveAndTest) {
			this.saveCode();
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
				scATDD.getStylesheets().add("stylesheetSCX.css");
				fenster.setScene(scATDD);

				}
		if(event.getSource()==btnNextRefactoring){
			if(txtCode.getText().isEmpty() == true){
				txtInfo.setText("Code is empty!");
			}
			else{
				txtInfo.setText("");
			if(ctrl.compileOnlyTestAndCode(txtTest.getText(),txtCode.getText(),txtCompileMsg,true)) {
				btnCheckTest.setId("button_green");
				btnCheckCODE.setId("button_green");
				breite = 1000;
				hoehe = 600;
				if(ctrl.compileOnlyTestAndCode(txtTest.getText(),txtCode.getText(),txtCompileMsg,false)){ //hier muss der Test überprüft werden, nicht das compilen
					btnCheckTest.setId("button_green");
					if(ctrl.getCurExc().isBabysteps())
						ctrl.stopTimer();
						lbTimer.textProperty().unbind();
						lbTimer.setText("");
						scRefactoring = new Scene(editorRefactoring());
						scRefactoring.getStylesheets().add("stylesheetSCX.css");
						fenster.setScene(scRefactoring);
					}	
				else{
					txtInfo.setText("Test is still RED!");}	
				}
			else {
				txtInfo.setText("Compile Error!");
				txtTest.setEditable(false);
				txtCode.setEditable(true);
			}
			}
		}
		
		if(event.getSource() == btnCompilen) {
			int check=0 ,refactoringCheck = 0;
			boolean[] tmp = ctrl.compileOnlyRefactoring(txtTest.getText(),txtCode.getText(),txtATDD.getText(),ctrl.getCurExc().getAccTestName(),txtCompileMsg);
			if(tmp[0]) {
				refactoringCheck = 1;
			}
			if(tmp[1]) {
				check = 1;
			}

			if (check == 1){
				ATDDCheck = true;
				CompileCheck = true;
				txtInfo.setText("Perfect!");
				btnCheckATDD.setId("button_green");
				btnCheckRefactoring.setId("button_green");
				txtTest.setEditable(false);
				txtCode.setEditable(false);
			}
			else if(refactoringCheck == 1){
				CompileCheck = true;
				ATDDCheck = false;
				btnCheckRefactoring.setId("button_green");
				txtInfo.setText("ATDD is still RED!");
				txtTest.setEditable(false);
				txtCode.setEditable(false);
			}
			
			else{
				txtInfo.setText("Compile failed!");
				ATDDCheck = false;
				CompileCheck = false;
			}
			
			if(CompileCheck == false){
				btnSaveAndTest.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	txtInfo.setText("Please use Compile first!");
			    }
			});
				btnSaveAndMenu.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			        txtInfo.setText("Please use Compile first!");
			    }
			});
				btnSaveAndATDD.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	txtInfo.setText("Please use Compile first!");
			    }
			});
			}
			else{
				btnSaveAndTest.setOnAction(this);
				if(ATDDCheck == true){
					btnSaveAndMenu.setOnAction(this);
					btnSaveAndATDD.setOnAction(this);
					}
				else{
					btnSaveAndMenu.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					        txtInfo.setText("ATDD Test is still RED, please use Save&Test!");
					    }
					});
						btnSaveAndATDD.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	txtInfo.setText("ATDD Test is still RED, please use Save&Test!");
					    }
					});
				}
				}
			
		}
		
		if(event.getSource() == btnSaveAndTest || event.getSource() == btnSaveAndATDD) {
			firstCode = false;
			firstTest = false;
			CompileCheck = false;
			ATDDCheck = false;
			txtInfo.setText("");
			btnCheckTest.setId("button_red");
			btnCheckCODE.setId("button_red");
			btnCheckATDD.setId("button_red");
			btnCheckRefactoring.setId("button_red");
		}
		
		if (event.getSource()==btnBackTest){String code = "";
		for(int i=0;i<ctrl.getCurExc().getClassContent().size();i++) {
			code += ctrl.getCurExc().getClassContent().get(i) + "\n";
		}
		if(firstCode) {
			txtCode.setText(code);
		}}
		
		if(event.getSource()==btnNextTest || event.getSource()==btnBackTest || event.getSource()==btnSaveAndTest){
			if(txtATDD.getText().isEmpty() == true){
				txtInfo.setText("ATDD is empty!");
			}
			else{
				txtInfo.setText("");
			if(status == 1){
				breite = 1000;
				hoehe = 600;
				txtInfo.setText("");
				txtTest.setEditable(true);
				scTest = new Scene(editorTest());
				scTest.getStylesheets().add("stylesheetSCX.css");
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
		}
		if(event.getSource()==btnNextCode){	
			if(txtTest.getText().isEmpty() == true){
				txtInfo.setText("Test is empty!");
			}
			else{
				txtInfo.setText("");
			if(status == 1){
				String code = "";
				if(txtCode.getText().equals("") || txtCode.getText().equals(null) || txtCode.getText().isEmpty()) {

					for(int i=0;i<ctrl.getCurExc().getClassContent().size();i++) {
						code += ctrl.getCurExc().getClassContent().get(i) + "\n";
					}
				}
				else {
					code = txtCode.getText();
				}
				if(ctrl.compileOnlyTestAndCode(txtTest.getText(),code,txtCompileMsg,true)) {
					if(ctrl.getCurExc().isBabysteps())
					ctrl.stopTimer();
					lbTimer.textProperty().unbind();
					lbTimer.setText("");
					breite = 1000;
					hoehe = 600;
					scCode = new Scene(editorCode());
					txtCode.setEditable(true);
					scCode.getStylesheets().add("stylesheetSCX.css");
					fenster.setScene(scCode);
				}
				else {
					txtInfo.setText("Compile Error!");
					txtTest.setEditable(true);
					txtCode.setEditable(false);
				}

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
	}


	private Parent editorRefactoring() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoringb,lbCredits2,lbList,lbTest,lbCode,lbATDD,txtCode,txtInfo,txtTest,txtCompileMsg,btnCompilen,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnSaveAndATDD,btnSaveAndTest,btnSaveAndMenu,btnMenu);
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
		txtTest.setEditable(true);
		txtCode.setEditable(true);
		
		txtInfo.setLayoutX(100);
		txtInfo.setLayoutY(535);
		txtInfo.setPrefWidth(300);
		txtInfo.setPrefHeight(30);
		txtInfo.setEditable(false);

		status = 1; //spaeter dann Compile-Code
		System.out.println("Refactoring-Stage");
		btnCompilen.setOnAction(this);
		if(CompileCheck == false){
			btnSaveAndTest.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	txtInfo.setText("Please use Compile first!");
		    }
		});
			btnSaveAndMenu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        txtInfo.setText("Please use Compile first!");
		    }
		});
			btnSaveAndATDD.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	txtInfo.setText("Please use Compile first!");
		    }
		});
		}
		return root;
	}

	private Parent editorATDD() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTest,lbCode,txtInfo,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,lbATDDb,txtATDD,txtCompileMsg,btnQuitCode,btnMenu,btnNextTest);
		txtATDD.setPrefWidth(breite*0.96);
		txtATDD.setPrefHeight(300);
		txtATDD.setLayoutX(20);
		txtATDD.setLayoutY(hoehe*0.19);
		txtCompileMsg.setLayoutX(20);
		txtCompileMsg.setLayoutY(427);
		txtCompileMsg.setPrefWidth(960);
		txtCompileMsg.setPrefHeight(100);
		txtCompileMsg.setEditable(false);
		txtTest.setEditable(false);
		
		txtInfo.setLayoutX(100);
		txtInfo.setLayoutY(535);
		txtInfo.setPrefWidth(300);
		txtInfo.setPrefHeight(30);
		txtInfo.setEditable(false);
		
		if(firstCode) {
			txtATDD.setText(ctrl.getCurExc().getAccTestCode());
		}
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
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTestb,lbCode,lbATDD,txtTest,txtInfo,txtCode,txtCompileMsg,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnMenu,btnNextCode,btnBackATDD,lbTimer);
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
		txtCode.setEditable(false);
		txtTest.setEditable(true);
		
		
		txtInfo.setLayoutX(100);
		txtInfo.setLayoutY(535);
		txtInfo.setPrefWidth(300);
		txtInfo.setPrefHeight(30);
		txtInfo.setEditable(false);

		System.out.println("test writing stage");
		status = 1; //spaeter dann Compile-Code
		btnNextCode.setOnAction(this);
		btnBackATDD.setOnAction(this);
		if(ctrl.getCurExc().isBabysteps())
		ctrl.startTimer(lbTimer,txtTest,txtCode);

		return root;
	}

	private Parent editorCode() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(lbRefactoring,lbCredits2,lbList,lbTest,lbCodeb,lbATDD,txtCode,txtTest,txtInfo,txtCompileMsg,btnQuitCode,btnCheckList,btnCheckTest,btnCheckCODE,btnCheckATDD,btnCheckRefactoring,btnMenu,btnNextRefactoring,btnBackTest,lbTimer);
		txtTest.setPrefWidth(breite*0.45);
		txtTest.setPrefHeight(300);
		txtTest.setLayoutX(20);
		txtTest.setLayoutY(hoehe*0.19);
		txtCode.setPrefWidth(breite*0.43);
		txtCode.setPrefHeight(300);
		txtCode.setLayoutX(breite*0.55);
		txtCode.setLayoutY(hoehe*0.19);
		
		txtInfo.setLayoutX(100);
		txtInfo.setLayoutY(535);
		txtInfo.setPrefWidth(300);
		txtInfo.setPrefHeight(30);
		txtInfo.setEditable(false);
		
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
		txtTest.setEditable(false);
		txtCode.setEditable(true);

		status = 1; //spaeter dann Compile-Code
		System.out.println("code writing stage");
		btnNextRefactoring.setOnAction(this);
		btnBackTest.setOnAction(this);
		if(ctrl.getCurExc().isBabysteps())
		ctrl.startTimer(lbTimer,txtTest,txtCode);
		return root;
	}


	private void saveCode() {
		String acc_code = txtATDD.getText();
		String test_code = txtTest.getText();
		String code_code = txtCode.getText();
		writeToFile(ctrl.getCurExc().getAccTestName(),acc_code);
		writeToFile(ctrl.getCurExc().getTestClassNames().get(0),test_code);
		writeToFile(ctrl.getCurExc().getClassNames().get(0),code_code);
		XMLReader xmlr = new XMLReader("src/main/resources/TestFile.xml");
		int index = 0;
		List<Excercise> excercises = xmlr.getExcercises();
		for(int i=0;i < excercises.size(); i++) {
			if(excercises.get(i).getDescription().equals(ctrl.getCurExc().getDescription())) {
				// we got our object
				index = i;
			}
		}
		excercises.get(index).setAccTestCode(acc_code);
		excercises.get(index).getTestClassContent().add(0,test_code);
		excercises.get(index).getClassContent().add(0,code_code);
		XMLWriter xmlw = new XMLWriter();
		xmlw.write("src/main/resources/TestFile.xml",excercises);
	}

	private void writeToFile(String filename,String text) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename + ".java"), "utf-8"))) {
			writer.write(text);
		}
		catch(IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error writing File");
			alert.setContentText("Could not write file: " + filename + ".java");

			Exception ex = new FileNotFoundException("Could not write file: " + filename + ".java");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			alert.getDialogPane().setExpandableContent(expContent);

			alert.showAndWait();
		}
	}
}
