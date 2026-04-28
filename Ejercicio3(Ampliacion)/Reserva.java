
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Reserva {

    /**
     * Default constructor
     */
    public Reserva() {
    }

    /**
     * 
     */
    private int codigo;

    /**
     * 
     */
    private Date fechaInicio;

    /**
     * 
     */
    private Date fechaFin;

    /**
     * 
     */
    private boolean entregado;

    /**
     * 
     */
    private double precioTotal;

    /**
     * 
     */
    public Set<DetalleReserva> detalleReserva;

    /**
     * 
     */
    private Cliente cliente;

    /**
     * 
     */
    private Agencia agencia;

    /**
     * 
     */
    public void calcularPrecioTotal() {
        // TODO implement here
    }

}