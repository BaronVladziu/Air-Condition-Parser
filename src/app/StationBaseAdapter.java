package app;

import gios.Parameter;
import parser.GiosParser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * Class for adapting console commands to StationBase methods.
 * @author Bartłomiej Kuśmirek
 */
public class StationBaseAdapter {

    private StationBase stationBase = new StationBase();

    /**
     * Prints air quality index for given stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1..] are station names
     */
    public void printIndex(String[] args) {
        args = this.translateStrings(args);
        stationBase.printIndex(Arrays.asList(args).subList(1, args.length));
    }

    /**
     * Prints values of given parameter, date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is date, [3..] are station names
     */
    public void printValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints actual values of given parameter and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printActualValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints parameter, which values are the lowest for given date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is date, [2..] are station names
     */
    public void printLowestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints parameter, which values actually are the lowest for given stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1..] are station names
     */
    public void printActualLowestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestParam(LocalDateTime.now(), Arrays.asList(args).subList(1, args.length));
    }

    /**
     * Prints parameter, which values are the highest for given date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is date, [2..] are station names
     */
    public void printHighestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints parameter, which values actually are the highest for given stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1..] are station names
     */
    public void printActualHighestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestParam(LocalDateTime.now(), Arrays.asList(args).subList(1, args.length));
    }

    /**
     * Prints N lowest values of given parameter, date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is date, [3] is N, [4..] are station names
     */
    public void printLowestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints N actually lowest values of given parameter and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is N, [3..] are station names
     */
    public void printActualLowestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints N highest values of given parameter, date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is date, [3] is N, [4..] are station names
     */
    public void printHighestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints N actually highest values of given parameter and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is N, [3..] are station names
     */
    public void printActualHighestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints the lowest value of given parameter, date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is date, [3..] are station names
     */
    public void printLowestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints the lowest actual value of given parameter and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printActualLowestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), 1, Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints the highest value of given parameter, date and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2] is date, [3..] are station names
     */
    public void printHighestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints the highest actual value of given parameter and stations. When no station is given, all will be considered.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printActualHighestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), 1, Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints mean value of given parameter and stations.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints mean value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date, [3] is end date, [4..] are station names
     */
    public void printRangedMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints mean value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date (and date is set to now), [3..] are station names
     */
    public void printActualMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints variance value of given parameter and stations.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints variance value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date, [3] is end date, [4..] are station names
     */
    public void printRangedVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints variance value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date (and date is set to now), [3..] are station names
     */
    public void printActualVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints highest and lowest value of given parameter and stations.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints highest and lowest value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date, [3] is end date, [4..] are station names
     */
    public void printRangedRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints highest and lowest value of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date (and date is set to now), [3..] are station names
     */
    public void printActualRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    /**
     * Prints bar chart of given parameter and stations.
     * @param args [0] is method name, [1] is parameter code, [2..] are station names
     */
    public void printFigure(String[] args) {
        args = this.translateStrings(args);
        stationBase.printFigure(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    /**
     * Prints bar chart of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date, [3] is end date, [4..] are station names
     */
    public void printRangedFigure(String[] args) {
        args = this.translateStrings(args);
        stationBase.printFigure(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    /**
     * Prints bar chart of given parameter, time period and stations.
     * @param args [0] is method name, [1] is parameter code, [2] is start date (and date is set to now), [3..] are station names
     */
    public void printActualFigure(String[] args) {
        args = this.translateStrings(args);
        stationBase.printFigure(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    private String[] translateStrings(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "PM2.5":
                    args[i] = "PM25";
                    break;
            }
        }
        return args;
    }

}
