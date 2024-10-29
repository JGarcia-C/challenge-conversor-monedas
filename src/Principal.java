import java.util.Scanner;

public class Principal {

    public static void main(String[] args){

        Conversor conversor = new Conversor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("*******************************************************");
        System.out.println("¡Bienvenido al Conversor de Divisas!");
        System.out.println("Este programa esta diseñado para la conversión de divisas con la actualización más reciente.");
        System.out.println("*******************************************************");

        conversor.realizarConversion();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Realizar una conversión");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            int opcion;
            String entradaInicial = Conversor.restriccion(scanner, "^[0-9]+$");
            opcion = Integer.parseInt(entradaInicial);

            switch (opcion) {
                case 1:
                    conversor.realizarConversion();
                    break;
                case 2:
                    conversor.mostrarHistorial();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        System.out.println("Gracias por usar el Conversor de Divisas. ¡Hasta pronto!");
        scanner.close();
    }
}










