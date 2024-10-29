import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    List<DatosDivisas> datosDivisas = new ArrayList<>();
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void obtenerTasaCambio (String monedaOrigen, String monedaDestino, String monto)
            throws IOException, InterruptedException {

        String urlBase = "https://v6.exchangerate-api.com/v6/";
        String claveApi = "37cb4adfef2ef55ad2a3e0e5";

        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(urlBase + claveApi + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + monto))
                    .build();
            HttpResponse<String> respuesta = cliente
                    .send(solicitud, HttpResponse.BodyHandlers.ofString());
            String json = respuesta.body();
            DatosDivisasApi datosDivisasApi = gson.fromJson(json, DatosDivisasApi.class);
            DatosDivisas datos = new DatosDivisas(datosDivisasApi);
            System.out.println(datos);

            datosDivisas.add(datos);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrio error: " + e.getMessage());
        }

    }

    public void mostrarHistorial(){
        System.out.println(datosDivisas);
    }


}
