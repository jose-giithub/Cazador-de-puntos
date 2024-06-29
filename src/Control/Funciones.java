package Control;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Funciones {


	/**
	 * Función que muestra el error si algo sale ma. Recive un 2 Str 
	 * Muestra el mensaje de error conun alert de ERROR
	 * @param titulo
	 * @param mensaje
	 */
	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	/**
	 * Función que pasa de celsius a farenheit recive un double 
	 * @param celsius
	 * @return un double
	 */
	public static double deCelsiusAFarenheit(double celsius) {
		return  ((celsius * 9) / 5) + 32;
	} 

	/**
	 * Función que pasa de farenheit a celsiusrecive un double 
	 * @param celsius
	 * @return un double
	 */
	public static double deFarenheitAcelsius(double farenheit) {	
		return ((farenheit - 32) * 5) / 9;
	} 


	public static boolean validarISBN(String ISBNstr) {	
		//ISBN pruebas Valido  0-201-48558-4
		//ISBN pruebas no VAlido  0-670-03441-X

		//***** qutamos los giones  ****
		String ISBNstrSinGiones = ISBNstr.replaceAll("-", "");
		//*******validar que tiene de 10 caracteres si no los tiene  mensaje iban no valido
		if (ISBNstrSinGiones.length() != 10 ) {

			String titulo = "Error";
			String mensaje = "ISBN no tiene 10 dijitos";
			Funciones.mostrarAlerta(titulo, mensaje);
			System.out.println(titulo +  "\n" + mensaje) ;

		}else {
			String ISBNStr9DigitosStr = ISBNstrSinGiones.substring(0,9);//guardo los 9 primeros dijitos
			String ISBNSControlStr = ISBNstrSinGiones.substring(9);//gaurdo el ultimo Str

			//****parsear los digitos de str a int
			int ISBN9Digitos =0;
			try {//verifico si todos son int
				ISBN9Digitos = Integer.parseInt(ISBNStr9DigitosStr);
				//System.out.println("Isbn parseado a int, str " + ISBNStr9DigitosStr + " int "+  ISBN9Digitos);
			} catch (NumberFormatException e) {
				String titulo = "Error";
				String mensaje = "ISBN solo puede ser numeros excepto el ultimo";
				Funciones.mostrarAlerta(titulo, mensaje);
				System.out.println(titulo +  "\n" + mensaje) ;
				return false;
			}
			//*********verifico que el control es un int o un str
			int digitoControl =0;
			if (ISBNSControlStr.equalsIgnoreCase("X")) {//si es x es 10
				digitoControl = 10;

			}else if (!ISBNSControlStr.equalsIgnoreCase("X")) { // si no es x parseo

				try {//verifico si es int
					digitoControl = Integer.parseInt(ISBNSControlStr);

				} catch (NumberFormatException e) {
					String titulo = "Error";
					String mensaje = "El ultimo digito solo puede ser X o numeros";
					Funciones.mostrarAlerta(titulo, mensaje);
					System.out.println(titulo +  "\n" + mensaje) ;
					return false;
				}

			}
			//***** multiplico cada numero de manera secuencial y gaurdo el resultado ISBN9Digitos
			int suma =0;

			int[] arrayInt = new int[ISBNStr9DigitosStr.length()];//creo un array de 9 posiciones
			for (int i = 0; i < arrayInt.length; i++) {//recorro el nuevo int y dentro guardo cada num en una posicion del array
				arrayInt[i]= Character.getNumericValue(ISBNStr9DigitosStr.charAt(i));
				int multiplicacion = arrayInt[i] * (i+1);
				suma+= multiplicacion;
				//System.out.println("Multi " + arrayInt[i]+ " * " + (i+1) + " = " + multiplicacion);
			}

			//***** divido el la suma entre 11 me quedon con el resto y redondeo
			int restoSuma =suma %11 ;
			//si restoruma es ==  digitodigitoControl isb ok si no isb no ok
			if (restoSuma == digitoControl) {
				System.out.println("ISBN " + ISBNstr +" es valido");
				System.out.println("Digito de control " + digitoControl+ " es igual que la division"  + restoSuma);
				return true;
			}else {
				System.out.println("ISBN " + ISBNstr +" no es valido");
				System.out.println("Digito de control " + digitoControl+ " no es igual que la division"  + restoSuma);
				return false;
			}

		}
		return true;
	} 



}
