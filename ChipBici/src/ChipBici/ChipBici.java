/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChipBici;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author maniana
 */
public class ChipBici {

    //Constantes
    public static final double MIN_VERSION = 1.0;
    public static final double MAX_VERSION = 10.9;
    public static final int MIN_REVISION = 0;
    public static final int MAX_REVISION = 9;
    public static final double DEFAULT_VERSION = MIN_VERSION;
    public static final int DEFAULT_REVISION = MIN_REVISION;
    public static final int MAX_DISTANCIA_TRAYECTO = 140;
    public static final LocalDate MIN_FECHA_ADQUISICION
            = LocalDate.of(2020, Month.JUNE, 15);
    //Atributos inmutables
    private final int numSerie;
    private final LocalDate fechaAdquisicion;
    //Atributos de objeto
    private static int siguienteNumeroSerie = 1;
    private double versionFirmware;
    private int revisionFirmware;
    private boolean estaAlquilada = false;
    private double kmTotales = 0;
    private LocalDateTime fechaHoraAlquilerActual = null;
    private double kmRecorridosActuales = 0;
    private LocalDateTime fechaHoraInicio = null;
    private LocalDateTime fechaHoraFin = null;
    private double kmRecorridosDuranteAlq = 0;
    private DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    //Atributos de la clase
    private static int cantidadTotalBicis = 0;
    private static int cantidadBicisAlq = 0;
    private static double cantidadTotalKmBicis = 0;

    /**
     * Constructor de la clase ChipBici.
     *
     * @param fechaAdquisicion La fecha de adquisición de la bici.
     * @param versionFirmware La versión del firmware.
     * @param revisionFirmware La revisión del firmware.
     * @throws IllegalArgumentException Si los parámetros son inválidos.
     */
    public ChipBici(LocalDate fechaAdquisicion, double versionFirmware, int revisionFirmware) throws IllegalArgumentException {
        if (fechaAdquisicion.isBefore(MIN_FECHA_ADQUISICION)
                || fechaAdquisicion.equals(null)
                || fechaAdquisicion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(String.format("ERROR: Fecha Incorrecta: %s\n",
                    fechaAdquisicion.format(FORMATO_FECHA)));
        } else if (versionFirmware < MIN_VERSION || versionFirmware > MAX_VERSION) {
            throw new IllegalArgumentException(String.format("ERROR: La version es incorrecta: %f\n",
                    versionFirmware));
        } else if (revisionFirmware < MIN_REVISION || revisionFirmware > MAX_REVISION) {
            throw new IllegalArgumentException(String.format("ERROR: La revision es incorrecta: %d\n",
                    revisionFirmware));
        }
        this.numSerie = siguienteNumeroSerie;
        siguienteNumeroSerie++;
        cantidadTotalBicis++;
        this.fechaAdquisicion = fechaAdquisicion;
        this.versionFirmware = versionFirmware;
        this.revisionFirmware = revisionFirmware;
    }

    /**
     * Constructor de la clase ChipBici con fecha de adquisición actual.
     *
     * @param versionFirmware La versión del firmware.
     * @param revisionFirmware La revisión del firmware.
     */
    public ChipBici(double versionFirmware, int revisionFirmware) throws IllegalArgumentException {
        this(LocalDate.now(), versionFirmware, revisionFirmware);
    }

    /**
     * Constructor sin parámetros.
     */
    public ChipBici() {
        this(LocalDate.now(), DEFAULT_VERSION,
                DEFAULT_REVISION);
    }

    // Métodos Getter
    /**
     * Método getter para obtener el número de serie.
     *
     * @return El número de serie de la bicicleta.
     */
    public String getNumSerie() {
        return String.valueOf(this.numSerie);
    }

    /**
     * Método getter para obtener la fecha de adquisición.
     *
     * @return La fecha de adquisición de la bicicleta.
     */
    public LocalDate getFechaAdquisicion() {
        return this.fechaAdquisicion;
    }

    /**
     * Método getter para obtener la versión actual del firmware instalado en el
     * chip de la bicicleta.
     *
     * @return La versión actual del firmware.
     */
    public double getVersion() {
        return this.versionFirmware;
    }

    /**
     * Método getter para obtener la revisión actual del firmware instalado en
     * el chip de la bicicleta.
     *
     * @return La revisión actual del firmware.
     */
    public int getRevision() {
        return this.revisionFirmware;
    }

    /**
     * Método getter para obtener la versión y revisión actual del firmware
     * instalado en el chip de la bicicleta, simultáneamente en forma de cadena
     * de caracteres (String), separándolos por un punto.
     *
     * @return La versión y revisión del firmware en formato "version.revision".
     */
    public String getVersionRevision() {
        return String.format("%.1f.%d", this.versionFirmware,
                this.revisionFirmware);
    }

    /**
     * Método getter para indicar si la bicicleta en la que está instalado el
     * chip se encuentra alquilada.
     *
     * @return true si la bicicleta está alquilada, false si no lo está.
     */
    public boolean isAlquilada() {
        return this.estaAlquilada;
    }

    /**
     * Método getter para devolver el total de kilómetros recorridos por la
     * bicicleta hasta el momento.
     *
     * @return El total de kilómetros recorridos.
     */
    public double getKilometrosTotales() {
        return this.kmTotales;
    }

    /**
     * Método getter para devolver la fecha y hora de inicio del alquiler actual
     * de la bicicleta.
     *
     * @return La fecha y hora de inicio del alquiler actual.
     */
    public LocalDateTime getRegistroInicioAlquilerActual() {
        return this.fechaHoraInicio;
    }

    /**
     * Método getter para devolver los kilómetros recorridos por la bicicleta
     * durante el alquiler actual.
     *
     * @return Los kilómetros recorridos durante el alquiler actual.
     */
    public double getKilometrosRecorridosAlquilerActual() {
        return this.kmRecorridosDuranteAlq;
    }

    /**
     * Método getter para devolver la fecha y hora de inicio del último alquiler
     * de la bicicleta.
     *
     * @return La fecha y hora de inicio del último alquiler.
     */
    public LocalDateTime getRegistroInicioUltimoAlquiler() {
        return this.fechaHoraInicio;
    }

    /**
     * Método getter para devolver la fecha y hora de fin del último alquiler de
     * la bicicleta.
     *
     * @return La fecha y hora de fin del último alquiler.
     */
    public LocalDateTime getRegistroFinUltimoAlquiler() {
        return this.fechaHoraFin;
    }

    /**
     * Método getter para devolver los kilómetros recorridos por la bicicleta
     * durante el último alquiler.
     *
     * @return Los kilómetros recorridos durante el último alquiler.
     */
    public double getKilometrosRecorridosUltimoAlquiler() {
        return this.kmRecorridosDuranteAlq;
    }

    /**
     * Devuelve la cantidad de chips (y, por tanto, de bicis) creados hasta el
     * momento.
     *
     * @return La cantidad de bicicletas creadas hasta el momento.
     */
    public int getNumBicis() {
        return this.cantidadTotalBicis;
    }

    /**
     * Devuelve la cantidad de bicis alquiladas en ese momento.
     *
     * @return La cantidad de bicicletas alquiladas en ese momento.
     */
    public int getNumBicisAlquiladas() {
        return this.cantidadBicisAlq;
    }

    /**
     * Devuelve el total de todos los kilómetros recorridos entre todas las
     * bicis hasta el momento.
     *
     * @return El total de kilómetros recorridos por todas las bicicletas hasta
     * el momento.
     */
    public double getKilometrosTodasLasBicis() {
        return this.cantidadTotalKmBicis;
    }

    /**
     * Método vacio que simula el alquiler de bicicletas.
     *
     * @throws IllegalStateException Si la bicicleta ya esta alquilada.
     */
    public void alquilar() throws IllegalStateException {
        if (this.isAlquilada()) {
            throw new IllegalStateException("Bici ya alquilada");
        } else {
            this.estaAlquilada = false;
            this.fechaHoraInicio = LocalDateTime.now();
            this.fechaHoraAlquilerActual = LocalDateTime.now();
            cantidadBicisAlq++;
        }
    }

    /**
     * Método vacio que simula la devolucion de bicicletas.
     *
     * @throws IllegalStateException Si la bicicleta no esta alquilada.
     */
    public void devolver() throws IllegalStateException {
        if (!this.isAlquilada()) {
            throw new IllegalStateException("Bici no alquilada");
        } else {
            this.estaAlquilada = true;
            this.fechaHoraFin = LocalDateTime.now();
            this.kmRecorridosDuranteAlq += this.kmRecorridosActuales;
            this.kmTotales += this.kmRecorridosActuales;
            cantidadBicisAlq--;
            cantidadTotalKmBicis += this.kmRecorridosActuales;
            this.kmRecorridosActuales = 0;
        }
    }

    /**
     * Método que verifica si la bicicleta esta alquilada y la distancia es
     * válida.
     *
     * @param distancia la distancia a recorrer en kilometros.
     * @return la cantidad total de kilómetros recorridos durante el alquiler
     * actual.
     * @throws IllegalArgumentException Si la distancia es negativa o excede la
     * distancia máxima permitida.
     * @throws IllegalStateException Si la bicicleta no está alquilada.
     */
    public double recorrerTrayecto(double distancia) throws IllegalArgumentException, IllegalStateException {
        if (!isAlquilada()) {
            throw new IllegalStateException("Bici no alquilada");
        }
        if (distancia < 0 || distancia > MAX_DISTANCIA_TRAYECTO) {
            throw new IllegalArgumentException(String.format("Distancia incorrecta: %.2f km",
                    distancia));
        }
        kmRecorridosActuales += distancia;
        kmRecorridosDuranteAlq += distancia;
        kmTotales += distancia;

        return kmRecorridosDuranteAlq;
    }

    /**
     * Realiza un trayecto en la bicicleta por la distancia máxima permitida,
     * registrando la distancia recorrida.
     *
     * @return La cantidad total de kilómetros recorridos durante el alquiler
     * actual.
     * @throws IllegalStateException Si la bicicleta no está alquilada.
     */
    public double recorrerTrayecto() throws IllegalStateException {
        return recorrerTrayecto(MAX_DISTANCIA_TRAYECTO);
    }

    /**
     * Actualiza el firmware del chip de la bicicleta a la versión y revisión
     * especificadas.
     *
     * @param nuevaVersion La nueva versión del firmware.
     * @param nuevaRevision La nueva revisión del firmware.
     * @throws IllegalStateException Si la bicicleta está alquilada.
     * @throws IllegalArgumentException Si se intenta actualizar a una versión o
     * revisión incorrecta, o si no se está realizando un "upgrade".
     */
    public void actualizarFirmware(double nuevaVersion, int nuevaRevision) throws IllegalStateException, IllegalArgumentException {
        if (isAlquilada()) {
            throw new IllegalStateException("No se puede actualizar el firmware a una bici alquilada");
        }

        if (nuevaVersion < MIN_VERSION || nuevaVersion > MAX_VERSION) {
            throw new IllegalArgumentException(String.format("Versión incorrecta: %.1f",
                    nuevaVersion));
        }

        if (nuevaRevision < MIN_REVISION || nuevaRevision > MAX_REVISION) {
            throw new IllegalArgumentException(String.format("Revisión incorrecta: %d",
                    nuevaRevision));
        }

        if (nuevaVersion < versionFirmware || (nuevaVersion == versionFirmware && nuevaRevision <= revisionFirmware)) {
            throw new IllegalArgumentException(String.format("Es necesario actualizar a una versión superior a la actual: %.1f <= %d.%d",
                    nuevaVersion, versionFirmware,
                    revisionFirmware));
        }

        this.versionFirmware = nuevaVersion;
        this.revisionFirmware = nuevaRevision;
    }

    /**
     * Sobrecarga del método actualizarFirmware para actualizar solo la versión
     * del firmware.
     *
     * @param nuevaVersion La nueva versión del firmware.
     * @throws IllegalStateException Si la bicicleta está alquilada.
     * @throws IllegalArgumentException Si se intenta actualizar a una versión
     * incorrecta o si no se está realizando un "upgrade".
     */
    public void actualizarFirmware(double nuevaVersion) throws IllegalStateException, IllegalArgumentException {
        actualizarFirmware(nuevaVersion, DEFAULT_REVISION);
    }

    /**
     * Reinicia toda la información del chip de la bicicleta, excepto el número
     * de serie y la fecha de adquisición.
     */
    public void reset() {
        this.versionFirmware = DEFAULT_VERSION;
        this.revisionFirmware = DEFAULT_REVISION;
        this.estaAlquilada = false;
        this.kmTotales = 0;
        this.fechaHoraAlquilerActual = null;
        this.kmRecorridosActuales = 0;
        this.fechaHoraInicio = null;
        this.fechaHoraFin = null;
        this.kmRecorridosDuranteAlq = 0;
    }

    /**
     * Método estático que simula una fábrica de chips.
     *
     * @param cantidadBici La cantidad de bicicletas a crear.
     * @return El array de bicicletas creadas.
     * @throws IllegalArgumentException Si la cantidad de bicicletas es menor
     * que 1.
     */
    public ChipBici[] crearArrayChipBici(int cantidadBici) throws IllegalArgumentException {
        if (cantidadBici < 1) {
            throw new IllegalArgumentException(String.format("Cantidad no válida: %d",
                    cantidadBici));
        }
        ChipBici[] chip = new ChipBici[cantidadBici];
        for (int i = 0; i < chip.length; i++) {
            chip[i] = new ChipBici();
        }
        return chip;
    }

    @Override
    public String toString() {
        return String.format("{ NS: %d; %s; fw: %.1f; $s; %2.f km totales; Alquiler actual: %s ,%.2f km; Último alquiler: %s , %s , %.2f }",
                this.numSerie, this.fechaAdquisicion,
                this.versionFirmware, this.revisionFirmware,
                this.estaAlquilada, this.kmTotales,
                this.fechaHoraAlquilerActual,
                this.kmRecorridosActuales,
                this.fechaHoraInicio, this.fechaHoraFin,
                this.kmRecorridosDuranteAlq);
    }

}
