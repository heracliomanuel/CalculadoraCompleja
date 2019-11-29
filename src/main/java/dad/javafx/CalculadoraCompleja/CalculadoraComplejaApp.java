package dad.javafx.CalculadoraCompleja;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraComplejaApp extends Application{

	//view
			private TextField operando1Text;
			private TextField operando2Text;
			private TextField operandoImaginario1Text;
			private TextField operandoImaginario2Text;
			private TextField resultadoText;
			private TextField resultadoImaginarioText;
			
			private ComboBox<String> operadorCombo;
			
			private Separator separador = new Separator();

			
			
		//model
			
			private Complejo operando1= new Complejo();
		//	private DoubleProperty operandoImaginario1 = new SimpleDoubleProperty();
			private Complejo operando2 = new Complejo();
		//	private DoubleProperty operandoImaginario2 = new SimpleDoubleProperty();
			private Complejo resultado = new Complejo();
		//	private DoubleProperty resultadoImaginario = new SimpleDoubleProperty();

			private StringProperty operacion = new SimpleStringProperty();
			
			
			public void start(Stage primaryStage) throws Exception{

			operando1Text = new TextField();
			operando1Text.setPrefColumnCount(4);
			
			operandoImaginario1Text = new TextField();
			operandoImaginario1Text.setPrefColumnCount(4);
			
			operando2Text = new TextField();
			operando2Text.setPrefColumnCount(4);
			
			operandoImaginario2Text = new TextField();
			operandoImaginario2Text.setPrefColumnCount(4);
			
			resultadoText = new TextField();
			resultadoText.setPrefColumnCount(4);
			resultadoText.setDisable(true);
			
			resultadoImaginarioText = new TextField();
			resultadoImaginarioText.setPrefColumnCount(4);
			resultadoImaginarioText.setDisable(true);
			
			operadorCombo = new ComboBox<String>();
			operadorCombo.getItems().addAll("+","-","*","/");
			
			//HBox root = new HBox();
			
			HBox root = new HBox(5, operando1Text,new Label("+"),operandoImaginario1Text,new Label("i"));
			HBox root1 = new HBox(5, operando2Text,new Label("+"),operandoImaginario2Text,new Label("i"));
			HBox root2 = new HBox(5, resultadoText,new Label("+"),resultadoImaginarioText,new Label("i"));
			VBox root4 = new VBox(5,operadorCombo);
			VBox root5 = new VBox(5,root,root1,separador,root2);
			VBox root6 = new VBox(5,new Label("="));
			HBox root7 = new HBox(5, root4, root5, root6);
		
			root.setAlignment(Pos.CENTER);
			root1.setAlignment(Pos.CENTER);
			root2.setAlignment(Pos.CENTER);
			root4.setAlignment(Pos.CENTER);
			root5.setAlignment(Pos.CENTER);
			root6.setAlignment(Pos.CENTER);
			root7.setAlignment(Pos.CENTER);

			
			Scene scene = new Scene(root7,320, 200);
			
			primaryStage.setTitle("Calculadora Compleja");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//bindeos
			
			operando1Text.textProperty().bindBidirectional(operando1.realProperty(), new NumberStringConverter());
			operandoImaginario1Text.textProperty().bindBidirectional(operando1.imaginarioProperty(), new NumberStringConverter());
		
			operando2Text.textProperty().bindBidirectional(operando2.realProperty(), new NumberStringConverter());
			operandoImaginario2Text.textProperty().bindBidirectional(operando2.imaginarioProperty(), new NumberStringConverter());

			resultadoText.textProperty().bindBidirectional(resultado.realProperty(), new NumberStringConverter());
			resultadoImaginarioText.textProperty().bindBidirectional(resultado.imaginarioProperty(), new NumberStringConverter());

			
			//Operación
			
			operacion.bind(operadorCombo.getSelectionModel().selectedItemProperty());
			operacion.addListener((o, ov, nv) -> onOperaccionChanged(nv));
		
			operadorCombo.getSelectionModel().selectFirst();
			
			
			}
			
			private void onOperaccionChanged(String nv) {
				switch (nv) {
				case "+": 
							resultado.realProperty().bind(operando1.realProperty().add(operando2.realProperty()));
							resultado.imaginarioProperty().bind(operando1.imaginarioProperty().add(operando2.imaginarioProperty()));
		
							break;
				case "-": 
							resultado.realProperty().bind(operando1.realProperty().subtract(operando2.realProperty()));
							resultado.imaginarioProperty().bind(operando1.imaginarioProperty().subtract(operando2.imaginarioProperty()));
							break;
				case "*": 	
					resultado.realProperty().bind((operando1.realProperty().multiply(operando2.realProperty()).subtract(
							 operando1.imaginarioProperty().multiply(operando2.imaginarioProperty()))));

					resultado.imaginarioProperty().bind((operando1.realProperty().multiply(operando2.imaginarioProperty()).add(
							operando1.imaginarioProperty().multiply(operando2.realProperty()))));
							break;
							
				case "/": 
					
					
					DoubleProperty c = new SimpleDoubleProperty();
					DoubleProperty d = new SimpleDoubleProperty();
			  	 
				 c.bind((operando1.realProperty().multiply(operando2.realProperty())).add(
									operando1.imaginarioProperty().multiply(operando2.imaginarioProperty())));
				
				 d.bind((operando2.imaginarioProperty().multiply(operando2.imaginarioProperty())).add(
									 operando2.realProperty().multiply(operando2.realProperty())));
				
				 resultado.realProperty().bind(c.divide(d));
				 
			  	 // Vuelvo a declarar nuevas
				 
			  	 c = new SimpleDoubleProperty();
			  	 d = new SimpleDoubleProperty();
			  	 
				 c.bind((operando1.imaginarioProperty().multiply(operando2.realProperty())).subtract(
							operando1.realProperty().multiply(operando2.imaginarioProperty())));
		
				 d.bind((operando2.realProperty().multiply(operando2.realProperty())).add(
							 operando2.imaginarioProperty().multiply(operando2.imaginarioProperty())));
		 
				
				resultado.imaginarioProperty().bind(c.divide(d));
						
				break;
				}
			}
			//operando1Text,operandoImaginario1Text,operando2Text,operandoImaginario2Text,resultadoText,resultadoImaginarioText)
	
	
	
	
	
			public static void main(String[] args) {
				launch(args);
			}

	
}
