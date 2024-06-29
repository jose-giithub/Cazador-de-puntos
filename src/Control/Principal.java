package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;//para cerrar la ventana


public class Principal {
	private Stage primaryStage;//referencia a la funcion para cerrar la bentana
	@FXML
	private Button idBotonReinicio;

	@FXML
	private Label idLabelRecord;


	@FXML
	private TextField idTextSumaPuntos;

	@FXML
	private GridPane idGridPaneCasillas;

	@FXML
	private Button idBoton1;
	@FXML
	private Button idBoton2;
	@FXML
	private Button idBoton3;
	@FXML
	private Button idBoton4;
	@FXML
	private Button idBoton5;
	@FXML
	private Button idBoton6;
	@FXML
	private Button idBoton7;
	@FXML
	private Button idBoton8;
	@FXML
	private Button idBoton9;
	@FXML
	private Button idBoton10;
	@FXML
	private Button idBoton11;
	@FXML
	private Button idBoton12;
	@FXML
	private Button idBoton13;
	@FXML
	private Button idBoton14;
	@FXML
	private Button idBoton15;
	@FXML
	private Button idBoton16;

	//creo un array list vacio donde atizare todos los botones
	private ArrayList<Button> arrayListBotones = new ArrayList<Button>();

	//metodo de java para inicializar los botones inportados
	public void initialize() {
		// Atizamos todos lobo botones al arrayList
		arrayListBotones.add(idBoton1);
		arrayListBotones.add(idBoton2);
		arrayListBotones.add(idBoton3);
		arrayListBotones.add(idBoton4);
		arrayListBotones.add(idBoton5);
		arrayListBotones.add(idBoton6);
		arrayListBotones.add(idBoton7);
		arrayListBotones.add(idBoton8);
		arrayListBotones.add(idBoton9);
		arrayListBotones.add(idBoton10);
		arrayListBotones.add(idBoton11);
		arrayListBotones.add(idBoton12);
		arrayListBotones.add(idBoton13);
		arrayListBotones.add(idBoton14);
		arrayListBotones.add(idBoton15);
		arrayListBotones.add(idBoton16);  
	}

	//Controlador para empezar y reiniciar el panel 
	@FXML
	void reiniciarPartida(ActionEvent event) {
		//habilito los botones
		Button botonClicado = (Button) event.getSource();
		for (Button botonesArrayList : arrayListBotones) {
			botonesArrayList.setDisable(false);
		}
		System.out.println("boton empezar" + idBotonReinicio.getText());
		//si al presionar el boton el texto que tiene es Empezar 
		if ("Empezar".equals(idBotonReinicio.getText())) {
			//habilito todos los botones
			System.out.println("boton empezar");
			idTextSumaPuntos.setText("0");

		}


		//si presionas el boton se modifica ha Reiniciar la partida
		idBotonReinicio.setText("Reiniciar la partida");

		ArrayList<String> arrayListLetras = new  ArrayList<>(Arrays.asList("W","O","O",
				"O","X","O","O","O","O","X","O","O","O","O","O","O","O","W","O","O"));
		//Desordeno el array con el metodo Shuffle
		Collections.shuffle(arrayListLetras);
		//recorro el array de botones y le atizo la letra a cada boton
		for (int i = 0; i < arrayListBotones.size(); i++) {
			//Añado ? al texto que se muestra del boton
			arrayListBotones.get(i).setText("?");
			// Usamos userData para almacenar el valor real sin mostrarlo
			arrayListBotones.get(i).setUserData(arrayListLetras.get(i)); 
		}
		Scanner ficheroEntrada;
		try {
			//declaro el fichero
			ficheroEntrada = new Scanner(new File("fichero.txt"));
			System.out.println("Numero fichero");
			System.out.println(ficheroEntrada.nextInt()+"");
			String recorFichero = ficheroEntrada.next();

			//añado el recor al programa
			idLabelRecord.setText("recorFichero");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String titulo = "error";
			String mensaje = "Fichero no encontrado";
			Funciones.mostrarAlerta(titulo, mensaje);
			System.err.println("Error con el fichero");
		}
	}
	//controlador botones gridPanel
	@FXML
	void mostrarLetra(ActionEvent event) {


		Button botonClicado = (Button) event.getSource();
		try {
			//Capturo el boton clicado. hacemos un Casting a button, el evento nos dice
			//que boton ha sido clicado y con get accedemos a su información

			//si el boton aun tiene ? muestro la info guardada en getUserData()
			//en formato STR
			if ("?".equals(botonClicado.getText())) {
				botonClicado.setText(botonClicado.getUserData().toString());
				//desaxcrivo este boton para que no se pueda clicar mas veces 
				botonClicado.setDisable(true);
			}

		} catch (NullPointerException e) {
			String titulo = "error";
			String mensaje = "No has iniciado la partida";
			Funciones.mostrarAlerta(titulo, mensaje);
			System.err.println("No has iniciado la partida");
		}
		//si es O sumamos 1 punto a la puntuación total
		//Capturar el texto del TextField
		String puntosTotalesStr = idTextSumaPuntos.getText();
		//Si es O sumamos 1
		Integer puntosTotales = Integer.parseInt(puntosTotalesStr); //parseamos a Ingeger
		if ("O".equals(botonClicado.getText())) {
			puntosTotales+=1;
			System.out.println("Letra es O, los puntos son: "+puntosTotales);

		}
		//Si es W se duplica
		if ("W".equals(botonClicado.getText())) {
			//si putos totales es diferente a 0
			if (puntosTotales>0) {
				puntosTotales*=2;//ESto se puede hacer?
				idTextSumaPuntos.setText(Integer.toString(puntosTotales));
				System.out.println("Letra es W, los puntos son: "+puntosTotales);
			}

		}

		//añado el resultado
		idTextSumaPuntos.setText(Integer.toString(puntosTotales));	

		//Muestro el record de putos
		String recordStr=  idLabelRecord.getText();

		Integer record = Integer.parseInt(recordStr);

		if (puntosTotales>record) {//Si puntosTotales son mas grandes que recor refresco el recor
			record = puntosTotales;
			idLabelRecord.setText(Integer.toString(record));
			System.out.println(" nuevo record" + record);

		}


		//		//Si es X palmas
		if ("X".equals(botonClicado.getText())) {
			// Desactivar todos los botones y el botón de reinicio
			for (Button botonesArrayList : arrayListBotones) {
				botonesArrayList.setDisable(true);
			}
			idBotonReinicio.setText("Empezar");
			//idBotonReinicio.setDisable(true);
			System.out.println("Has perdido la partida.");
			return; // Salir del método para evitar más ejecuciones

		}


	}

	/**
	 * Controlador para cerrar la ventana  de Scene builder
	 * @param event
	 */
	@FXML
	void salirPartida(ActionEvent event) {
		primaryStage.close();//cierra la ventana 

	}

	//función que carga la Scene al Stage
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
