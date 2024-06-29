package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import Control.Principal; // Para que funcione la funcion reiniciarPartida



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Cargo el archivo fxml en el main pasandole la ruta
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Principal.fxml"));
	         Parent root = loader.load(); //Analiza el archivo FXML creado y crea las gerarquias 
	         
			 Principal controller = loader.getController();//giarda los controladores
	         controller.setPrimaryStage(primaryStage);  // Asigna el Stage al controlador
	        

			// Declaramos y montamos el escaner que leera el archivo .fxml
			Scene scene = new Scene(root);
			primaryStage.setTitle("jose");
			primaryStage.setScene(scene);
			primaryStage.show();

			// Aquesta línea permet afegir estils CSS al projecte
			scene.getStylesheets().add(getClass().getResource("/gui/styles.css").toExternalForm());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
