package api.starwars.general;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ConsultaPelicula {

    Pelicula buscaPelicula(int numeroDePelicula) {
        URI direccion = URI.create("https://swapi.py4e.com/api/films/" + numeroDePelicula);
        
        // Configuramos el cliente para que siga redirecciones automáticamente (HTTP 301/302)
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .header("User-Agent", "Mozilla/5.0")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Imprimimos en consola el estado para depurar
            System.out.println("Código HTTP SWAPI: " + response.statusCode());

            if (response.statusCode() != 200) {
                throw new RuntimeException("API respondió con código de error: " + response.statusCode());
            }

            Pelicula pelicula = new Gson().fromJson(response.body(), Pelicula.class);
            
            if (pelicula == null) {
                throw new RuntimeException("El JSON de la película llegó vacío.");
            }
            
            return pelicula;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No encontré esa película.");
        }
    }
}
