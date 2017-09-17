package br.com.restaurantevilaprudente.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoadPage {

	public static void main(String[] args) {
		try {

			String content = ""
+ "<!DOCTYPE html>"
+ "<html>"
+ "<head>"
+ "<meta charset='utf-8'>"
+ "<title>Pedido</title>"
+ "</head>"
+ "<body>"
+ "<h1>Testando</h1>"
+ "This is the content <br> to write into file"
+ "</body>"
+ "</html>";

			File file = new File("WebContent/pedidos/pedido.jsp");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

