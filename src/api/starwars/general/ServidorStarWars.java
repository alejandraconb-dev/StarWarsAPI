package api.starwars.general;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorStarWars {
    public static void main(String[] args) throws IOException {
        int puerto = 8080;
        HttpServer servidor = HttpServer.create(new InetSocketAddress(puerto), 0);
        
        // Endpoint que procesa la consulta de Java y genera el JSON
        servidor.createContext("/api/pelicula", new PeliculaHandler());
        servidor.setExecutor(null); // crea un ejecutor por defecto
        
        System.out.println("=========================================");
        System.out.println("  BACKEND JAVA ACTIVO EN PUERTO " + puerto);
        System.out.println("    Java + Gson + FileWriter");
        System.out.println("=========================================");
        servidor.start();
    }

    static class PeliculaHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange intercambio) throws IOException {
            // Permitir CORS para que el HTML/JS local pueda consultar a Java
            intercambio.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            intercambio.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");

            // Obtener el parámetro 'numero' de la URL (ej: /api/pelicula?numero=1)
            String query = intercambio.getRequestURI().getQuery();
            int numeroDePelicula = 1; // por defecto
            
            if (query != null && query.startsWith("numero=")) {
                try {
                    numeroDePelicula = Integer.parseInt(query.split("=")[1]);
                } catch (NumberFormatException e) {
                    String errorJson = "{\"error\": \"Número de película inválido.\"}";
                    enviarRespuesta(intercambio, 400, errorJson);
                    return;
                }
            }

            try {
                // 1. Java consulta la API externa con HttpClient
                ConsultaPelicula consulta = new ConsultaPelicula();
                Pelicula pelicula = consulta.buscaPelicula(numeroDePelicula);

                // 2. Java procesa y formatea el JSON con Gson
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonRespuesta = gson.toJson(pelicula);

                // 3. Java guarda el archivo .json en el disco del servidor (GeneradorDeArchivo)
                GeneradorDeArchivo generador = new GeneradorDeArchivo();
                generador.guardarJson(pelicula);
                System.out.println("[JAVA] Archivo JSON guardado en disco: " + pelicula.title() + ".json");

                // 4. Java responde al Frontend con el JSON procesado
                enviarRespuesta(intercambio, 200, jsonRespuesta);

            } catch (Exception e) {
                String errorJson = "{\"error\": \"No encontré esa película (Java Exception).\"}";
                enviarRespuesta(intercambio, 404, errorJson);
            }
        }

        private void enviarRespuesta(HttpExchange intercambio, int codigoEstado, String respuesta) throws IOException {
            byte[] bytes = respuesta.getBytes("UTF-8");
            intercambio.sendResponseHeaders(codigoEstado, bytes.length);
            OutputStream os = intercambio.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}