package com.conversor_moneda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SpringBootApplication
public class ConversorMoneda2Application {

	public static void main(String[] args) {
		SpringApplication.run(ConversorMoneda2Application.class, args);
		ConversorMoneda2Application app = new ConversorMoneda2Application();
		app.principal();
	}

	public JsonObject obtenerValorMoneda(String moneda) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://v6.exchangerate-api.com/v6/f525ba81f9691aa426f15073/latest/" + moneda))
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			return gson.fromJson(response.body(), JsonObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void principal() {
		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.println("**********************************************");
			System.out.println("********** Conversor de Monedas **************");
			System.out.println("**********************************************");
			System.out.println("1. Dolar =>> Peso argentino");
			System.out.println("2. Peso argentino =>> Dolar");
			System.out.println("3. Dolar =>> Real brasileño");
			System.out.println("4. Real brasileño =>> Dolar");
			System.out.println("5. Dolar =>> Peso colombiano");
			System.out.println("6. Peso colombiano =>> Dolar");
			System.out.println("7. Salir");
			System.out.println("**********************************************");
			System.out.println("Elija una opción válida: ");
			System.out.println("**********************************************");
			int opcion = scanner.nextInt();
			System.out.println("Ingrese la cantidad de dinero: ");
			double cantidad = scanner.nextDouble();
			switch (opcion) {	
				case 1:
					JsonObject valorMoneda = obtenerValorMoneda("USD");
					double valor = valorMoneda.get("conversion_rates").getAsJsonObject().get("ARS").getAsDouble();
					System.out.println("El valor de " + cantidad + " dolares en pesos argentinos es: " + cantidad * valor);
					break;
				case 2:
					JsonObject valorMoneda2 = obtenerValorMoneda("ARS");
					double valor2 = valorMoneda2.get("conversion_rates").getAsJsonObject().get("USD").getAsDouble();
					System.out.println("El valor de " + cantidad + " pesos argentinos en dolares es: " + cantidad * valor2);
					break;
				case 3:
					JsonObject valorMoneda3 = obtenerValorMoneda("USD");
					double valor3 = valorMoneda3.get("conversion_rates").getAsJsonObject().get("BRL").getAsDouble();
					System.out.println("El valor de " + cantidad + " dolares en reales brasileños es: " + cantidad * valor3);
					break;
				case 4:
					JsonObject valorMoneda4 = obtenerValorMoneda("BRL");
					double valor4 = valorMoneda4.get("conversion_rates").getAsJsonObject().get("USD").getAsDouble();
					System.out.println("El valor de " + cantidad + " reales brasileños en dolares es: " + cantidad * valor4);
					break;
				case 5:
					JsonObject valorMoneda5 = obtenerValorMoneda("USD");
					double valor5 = valorMoneda5.get("conversion_rates").getAsJsonObject().get("COP").getAsDouble();
					System.out.println("El valor de " + cantidad + " dolares en pesos colombianos es: " + cantidad * valor5);
					break;
				case 6:
					JsonObject valorMoneda6 = obtenerValorMoneda("COP");
					double valor6 = valorMoneda6.get("conversion_rates").getAsJsonObject().get("USD").getAsDouble();
					System.out.println("El valor de " + cantidad + " pesos colombianos en dolares es: " + cantidad * valor6);
					break;
				case 7:
					System.out.println("Gracias por usar nuestro conversor de monedas");
					System.exit(0);
					break;
				default:
					System.out.println("Opción no válida");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
		}
	}
	

}
