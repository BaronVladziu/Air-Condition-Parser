package gios;

/**
 * Enum with parameters of air quality index
 */
public enum Parameter {

    ST,
    SO2,
    NO2,
    CO,
    PM10,
    PM25,
    O3,
    C6H6;

    /**
     * number of parameter values
     */
    public static int size = values().length;

}
