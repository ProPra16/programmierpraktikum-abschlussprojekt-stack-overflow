package GUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	Label l1;
	Label l2;	
	Label l3;
	Label l4;
	Label l5;
	Label l6;
	TextField t1;
	int breite;
	int hoehe;
	int i; //i ist 1, wenn der Compile-Vorgang funktioniert hat


	
	
	public static void main(String[] args) {
		launch(args);	

	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		fenster = arg0;
		arg0.setTitle("TDDT");
		
		t1 = new TextField();
		//Muss noch angepasst werden!
		
		l1 = new Label("TDD-Trainer");
		l1.setTranslateX(5);
		l1.setTranslateY(-160);
		
		l2 = new Label("*Credits*");
		l2.setTranslateX(5);
		l2.setTranslateY(160);
		
		l3 = new Label("TDD-Trainer");
		l3.setTranslateX(165);
		l3.setTranslateY(10);
		
		l4 = new Label("*Credits*");
		l4.setTranslateX(170);
		l4.setTranslateY(270);
		
		l5 = new Label("TEST");
		l5.setTranslateX(180);
		l5.setTranslateY(30);
		
		l6 = new Label("CODE");
		l6.setTranslateX(180);
		l6.setTranslateY(30);
		
		s1 = new Button();
		s1.setText("Normal-Codes");
		s1.setTranslateX(5);
		s1.setTranslateY(-90);
		s1.setOnAction(this);
		
		s2 = new Button();
		s2.setText("Babysteps-Codes");
		s2.setTranslateX(5);
		s2.setTranslateY(0);
		s2.setOnAction(this);
		
		s3 = new Button();
		s3.setText("Quit");
		s3.setTranslateX(5);
		s3.setTranslateY(90);
		s3.setOnAction(this);
		
		s4 = new Button();
		s4.setText("Quit");
		s4.setTranslateX(345);
		s4.setTranslateY(270);
		s4.setOnAction(this);
		
		s5 = new Button();
		s5.setText("Menue");
		s5.setTranslateX(5);
		s5.setTranslateY(270);
		s5.setOnAction(this);
		
		s6 = new Button();
		s6.setText("Weiter");
		s6.setTranslateX(345);
		s6.setTranslateY(220);
		
		s7 = new Button();
		s7.setText("Weiter");
		s7.setTranslateX(345);
		s7.setTranslateY(220);

		
		StackPane rahmen = new StackPane();
		rahmen.getChildren().addAll(l1,l2,s1,s2,s3);
		sc1 = new Scene(rahmen, 200, 400);
		arg0.setScene(sc1);
		arg0.show();
		
	}

	public void handle(ActionEvent event){
		if(event.getSource()==s1){
			breite = 400;
			hoehe = 300;
			sc2 = new Scene(create(1));
			fenster.setScene(sc2);
		}
		if(event.getSource()==s2){
			breite = 400;
			hoehe = 300;
			sc2 = new Scene(create(2));
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
		}
		if(event.getSource()==s6){
			if(i == 1){
				breite = 400;
				hoehe = 300;
				sc3 = new Scene(editorTest());
				fenster.setScene(sc3);
				}
			else{//Error code...
				
			}
		}
		if(event.getSource()==s7){
			if(i == 1){
				breite = 400;
				hoehe = 300;
				sc4 = new Scene(editorCode());
				fenster.setScene(sc4);
			}
			else{//Error code...
				}
			}
		}


	private Parent create(int a) {
		   
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(l3,l4,s4,s5,s6);
        if(a == 1){
        	//Reader für nicht-Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	i = 1; //spaeter dann Compile-Code
        	s6.setOnAction(this);
        	
        }
        if(a == 2){
        	//Reader für Babysteps Codes
        	//AuswahlBox-Klasse (im folgenden zum testen:)
        	i = 1; //spaeter dann Compile-Code
        	s6.setOnAction(this);
        }
       
        return root;
		
		
	}
	
	private Parent editorTest() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(l3,l4,l5,t1,s4,s5,s7);
		i = 1; //spaeter dann Compile-Code
		s7.setOnAction(this);
		return root;
	}

	private Parent editorCode() {
		Pane root = new Pane();
        root.setPrefSize(breite, hoehe);
		root.getChildren().addAll(l3,l4,l6,t1,s4,s5,s6);
		i = 1; //spaeter dann Compile-Code
		s6.setOnAction(this);
		return root;
	}
}

