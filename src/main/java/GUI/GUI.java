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
	Scene sc1;
	Scene sc2;
	Scene sc3;
	Scene sc4;
	Button s1;
	Button s2;
	Button s3;
	Button s4;
	Button s5;
	Button s6;
	Button s7;
	Button s8;
	Label l1;
	Label l2;	
	Label l3;
	Label l3b;
	Label l4;
	Label l5;
	Label l6;
	Label l7;
	Label l5b;
	Label l7b;
	Label l6b;
	TextArea t1;
	TextArea t2;
	int breite;
	int hoehe;
	int i; //i ist 1, wenn der Compile-Vorgang funktioniert hat
	Controller ctrl;
	TableView table;


	
	@Override
	public void start(Stage arg0) throws Exception {
		fenster = arg0;
		arg0.setTitle("TDDT");
		ctrl = new Controller();
		table = new TableView();



		t1 = new TextArea();
		t2 = new TextArea();
		//Muss noch angepasst werden!


		l1 = new Label("TDD-Trainer");
		l1.setTranslateX(5);
		l1.setTranslateY(-160);
		l1.getStyleClass().add("Label_Titel");
		
		l2 = new Label("*Made by: Stack-Overflow*");
		l2.setTranslateX(5);
		l2.setTranslateY(160);
		
		l3 = new Label("4.Repeat");
		l3.setTranslateX(800);
		l3.setTranslateY(50);
		l3.setFont(new Font("Arial", 15));
		l3.setTextFill(Color.web("#FFFFFF"));
		l3b = new Label("4.Repeat");
		l3b.setTranslateX(800);
		l3b.setTranslateY(50);
		l3b.setFont(new Font("Arial", 15));
		l3b.setTextFill(Color.web("#2196F3"));
		
		l4 = new Label("*Made by: Marc Hilgenberg,Leonard Hack,Kelly Williams,Benno Bongaertz*");
		l4.setTranslateX(600);
		l4.setTranslateY(580);
		
		l5 = new Label("1.Choose Exercise");
		l5.setTranslateX(200);
		l5.setTranslateY(50);
		l5.setFont(new Font("Arial", 15));
		l5.setTextFill(Color.web("#FFFFFF"));
		l5b = new Label("1.Choose Exercise");
		l5b.setTranslateX(200);
		l5b.setTranslateY(50);
		l5b.setFont(new Font("Arial", 15));
		l5b.setTextFill(Color.web("#2196F3"));
		
		l6 = new Label("2.Write a Test");
		l6.setTranslateX(400);
		l6.setTranslateY(50);
		l6.setFont(new Font("Arial", 15));
		l6.setTextFill(Color.web("#FFFFFF"));
		l6b = new Label("2.Write a Test");
		l6b.setTranslateX(400);
		l6b.setTranslateY(50);
		l6b.setFont(new Font("Arial", 15));
		l6b.setTextFill(Color.web("#2196F3"));
		
		l7 = new Label("3.Write the Code");
		l7.setTranslateX(600);
		l7.setTranslateY(50);
		l7.setFont(new Font("Arial", 15));
		l7.setTextFill(Color.web("#FFFFFF"));
		l7b = new Label("3.Write the Code");
		l7b.setTranslateX(600);
		l7b.setTranslateY(50);
		l7b.setFont(new Font("Arial", 15));
		l7b.setTextFill(Color.web("#2196F3"));
		
		s1 = new Button();
		s1.setText("Normal-Codes");
		s1.setTranslateX(5);
		s1.setTranslateY(-90);
		s1.setOnAction(this);
		s1.setId("button");

		
		s2 = new Button();
		s2.setText("Babysteps-Codes");
		s2.setTranslateX(5);
		s2.setTranslateY(0);
		s2.setOnAction(this);
		s2.setId("button");
		
		s3 = new Button();
		s3.setText("Quit");
		s3.setTranslateX(5);
		s3.setTranslateY(90);
		s3.setOnAction(this);
		s3.setId("button");
		
		s4 = new Button();
		s4.setText("Quit");
		s4.setTranslateX(10);
		s4.setTranslateY(510);
		s4.setOnAction(this);
		s4.setId("button");
		
		s5 = new Button();
		s5.setText("Menue");
		s5.setTranslateX(375);
		s5.setTranslateY(510);
		s5.setOnAction(this);
		s5.setId("button");
		
		s6 = new Button();
		s6.setText("Next");
		s6.setTranslateX(600);
		s6.setTranslateY(510);
		s6.setId("button");
		
		s7 = new Button();
		s7.setText("Next");
		s7.setTranslateX(600);
		s7.setTranslateY(510);
		s7.setId("button");
		
		s8 = new Button();
		s8.setText("*Timer*");
		s8.setTranslateX(500);
		s8.setTranslateY(510);


		
		StackPane rahmen = new StackPane();
		rahmen.getChildren().addAll(l1,l2,s1,s2,s3);
		sc1 = new Scene(rahmen, 200, 400);
		sc1.getStylesheets().add("stylesheetSC1.css");
		arg0.setScene(sc1);
		arg0.show();
		
	}

	public void handle(ActionEvent event){
		if(event.getSource()==s1){
			breite = 1000;
			hoehe = 600;
			sc2 = new Scene(create(1));
			sc2.getStylesheets().add("stylesheetSCX.css");
			fenster.setScene(sc2);
		}
		if(event.getSource()==s2){
			breite = 1000;
			hoehe = 600;
			sc2 = new Scene(create(2));
			sc2.getStylesheets().add("stylesheetSCX.css");
			fenster.setScene(sc2);
		}
		if(event.getSource()==s3){
			System.exit(0);
		}
		if(event.getSource()==s4){
			System.exit(0);
		}
		if(event.getSource()==s5){
			fenster.setScene(sc1);
			fenster.setFullScreen(false);
		}
		if(event.getSource()==s6){
			if(i == 1){
				breite = 1000;
				hoehe = 600;
				sc3 = new Scene(editorTest());
				sc3.getStylesheets().add("stylesheetSCX.css");
				fenster.setScene(sc3);

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
		if(event.getSource()==s7){
			if(i == 1){
				breite = 1000;
				hoehe = 600;
				sc4 = new Scene(editorCode());
				sc4.getStylesheets().add("stylesheetSCX.css");
				fenster.setScene(sc4);

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
		root.getChildren().addAll(l3,l4,l5b,l6,l7,s4,s5,s6,table);
        if(a == 1){

        	//Reader fuer nicht-Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	i = 1; //spaeter dann Compile-Code
        	s6.setOnAction(this);
        	
        }
        if(a == 2){
        	//Reader fuer Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	i = 1; //spaeter dann Compile-Code
        	s6.setOnAction(this);
        }
       
        return root;
		
		
	}
	
	private Parent editorTest() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(l3,l4,l5,l6b,l7,t1,t2,s4,s5,s7,s8);
		t1.setPrefWidth(breite*0.35);
		t1.setPrefHeight(hoehe*0.6);
		t1.setLayoutX(breite*0.1);
		t1.setLayoutY(hoehe*0.2);
		t1.setText("Test");
		t2.setPrefWidth(breite*0.35);
		t2.setPrefHeight(hoehe*0.6);
		t2.setLayoutX(breite*0.6);
		t2.setLayoutY(hoehe*0.2);
		t2.setText("Code");
		t2.setEditable(false);
		System.out.println("test writing stage");
		i = 1; //spaeter dann Compile-Code
		s7.setOnAction(this);
		return root;
	}

	private Parent editorCode() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(l3,l4,l5,l6,l7b,t2,t1,s4,s5,s6,s8);
		t1.setPrefWidth(breite*0.35);
		t1.setPrefHeight(hoehe*0.6);
		t1.setLayoutX(breite*0.6);
		t1.setLayoutY(hoehe*0.2);
		t1.setText("Test");
		t1.setEditable(false);
		t2.setPrefWidth(breite*0.35);
		t2.setPrefHeight(hoehe*0.6);
		t2.setLayoutX(breite*0.1);
		t2.setLayoutY(hoehe*0.2);
		t2.setText("Code");
		i = 1; //spaeter dann Compile-Code
		System.out.println("code writing stage");
		s6.setOnAction(this);
		return root;
	}
}
