import java.util.*;
import java.io.IOException;

public class Conversor {
    private Scanner scanner = new Scanner(System.in);
    private ApiService apiService = new ApiService();

    private Map<Integer, String> divisas = Map.of(
            1, "USD",  // Dólar estadounidense
            2, "EUR",  // Euro
            3, "COP",  // Peso colombiano
            4, "MXN",  // Peso mexicano
            5, "BRL",  // Real brasileño
            6, "ARS",  // Peso argentino
            7, "CLP",  // Peso chileno
            8, "PEN",  // Sol peruano
            9, "UYU",  // Peso uruguayo
            10, "CAD"  // Dólar canadiense
    );

    public void realizarConversion() {

        System.out.println("\nSELECCIONE MONEDA DE ORIGEN:");
        mostrarMenuDivisas();
        int origen = obtenerSeleccion();
        String monedaOrigen = divisas.get(origen);

        System.out.println("\nSELECCIONE MONEDA DE DESTINO:");
        mostrarMenuDivisas();
        int destino = obtenerSeleccion();
        String monedaDestino = divisas.get(destino);

        System.out.print("\nIngrese la cantidad a convertir: ");
        String monto = restriccion(scanner, "^[0-9]+(\\.[0-9]+)?$");

        try {
            apiService.obtenerTasaCambio(monedaOrigen, monedaDestino, monto);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
        }
    }

    public void mostrarHistorial() {
        apiService.mostrarHistorial();
    }

    private void mostrarMenuDivisas() {
        System.out.println("""
        1. Dólar estadounidense.    6. Peso argentino.
        2. Euro.                    7. Peso chileno.
        3. Peso colombiano.         8. Sol peruano.
        4. Peso mexicano.           9. Peso uruguayo.
        5. Real brasileño.         10. Dólar canadiense.
        """);
    }

    private int obtenerSeleccion() {
        int seleccion;
        while (true) {
            System.out.print("Ingrese el número correspondiente: ");
            String entrada = restriccion(scanner, "^[0-9]+$");
            seleccion = Integer.parseInt(entrada);
            if (divisas.containsKey(seleccion)) break;
            System.out.println("Selección no válida. Intente nuevamente.");

        }
        return seleccion;
    }

    public static String restriccion(Scanner scanner, String regex) {
        String entrada = "";
        while (true) {
            entrada = scanner.nextLine();
            if (entrada.matches(regex)) {
                break;
            } else {
                System.out.println("Entrada inválida. Ingrese solo números.");
            }
        }
        return entrada;
    }

}
