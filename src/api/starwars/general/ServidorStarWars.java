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
        System.out.println("  BACKEND JAVA ACTIVO EN PUERTO <3 " + puerto);
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
            int episodioInput = 1; // por defecto
            
            if (query != null && query.startsWith("numero=")) {
                try {
                    String valorParam = query.split("=")[1];
                    episodioInput = Integer.parseInt(valorParam);
                    
                    // Validar el rango (1 al 7) en el servidor
                    if (episodioInput < 1 || episodioInput > 7) {
                        String errorJson = "{\"error\": \"Por favor, debe poner solo números y del 1 al 7.\"}";
                        enviarRespuesta(intercambio, 400, errorJson);
                        return;
                    }
                } catch (NumberFormatException e) {
                    String errorJson = "{\"error\": \"Por favor, debe poner solo números y del 1 al 7.\"}";
                    enviarRespuesta(intercambio, 400, errorJson);
                    return;
                }
            }

            try {
                // Mapear el número de episodio ingresado al ID real de la API de Star Wars
                int idRealApi = episodioInput;
                if (episodioInput == 1) idRealApi = 4;
                else if (episodioInput == 2) idRealApi = 5;
                else if (episodioInput == 3) idRealApi = 6;
                else if (episodioInput == 4) idRealApi = 1;
                else if (episodioInput == 5) idRealApi = 2;
                else if (episodioInput == 6) idRealApi = 3;
                else if (episodioInput == 7) idRealApi = 7;

                // 1. Java consulta la API externa con el ID correcto
                ConsultaPelicula consulta = new ConsultaPelicula();
                Pelicula pelicula = consulta.buscaPelicula(idRealApi);

                // 2. Java procesa y formatea el JSON con Gson
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonRespuesta = gson.toJson(pelicula);

                // 3. Java guarda el archivo .json en el disco del servidor
                GeneradorDeArchivo generador = new GeneradorDeArchivo();
                generador.guardarJson(pelicula);
                System.out.println("[JAVA] Archivo JSON guardado en disco: " + pelicula.title() + ".json");

                // 4. Java responde al Frontend con el JSON procesado
                enviarRespuesta(intercambio, 200, jsonRespuesta);

            } catch (Exception e) {
                e.printStackTrace(); 
                String mensajeError = e.getMessage() != null ? e.getMessage() : "Error desconocido";
                String errorJson = "{\"error\": \"No encontré esa película (" + mensajeError.replace("\"", "'") + ").\"}";
                enviarRespuesta(intercambio, 500, errorJson);
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