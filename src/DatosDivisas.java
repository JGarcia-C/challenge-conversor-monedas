import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatosDivisas {

    private String codigoOrigen; //base_code
    private String codigoDestino; //target_code
    private BigDecimal tasaConversion; //conversion_rate
    private BigDecimal resultadoConversion; //conversion_result
    private String fechaUsuario;
    private BigDecimal montoFinal;

    public DatosDivisas (DatosDivisasApi datosApi) {

        this.codigoOrigen = datosApi.base_code();
        this.codigoDestino = datosApi.target_code();
        this.tasaConversion = datosApi.conversion_rate();
        this.resultadoConversion = datosApi.conversion_result();
        this.montoFinal = datosApi.conversion_result().divide(datosApi.conversion_rate(), 2, RoundingMode.HALF_UP);;

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String fechaHoraFormateada = ahora.format(formato);
        this.fechaUsuario = fechaHoraFormateada;
    }

    @Override
    public String toString() {
        return "\nCONVERSIÓN: "+
                "\n-Moneda origen= " + codigoOrigen +
                "\n-Moneda destino= " + codigoDestino +
                "\n-Cantidad a convertir= " + montoFinal +
                "\n-Tasa de conversión= " + tasaConversion +
                "\n-Resultado conversión= " + resultadoConversion +
                "\n-Fecha busqueda= " + fechaUsuario;
    }
}
