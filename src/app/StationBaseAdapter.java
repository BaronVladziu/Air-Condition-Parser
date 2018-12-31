package app;

import gios.Parameter;
import parser.GiosParser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class StationBaseAdapter {

    private StationBase stationBase = new StationBase();

    public void printIndex(String[] args) {
        args = this.translateStrings(args);
        stationBase.printIndex(Arrays.asList(args).subList(1, args.length));
    }

    public void printValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printActualValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Arrays.asList(args).subList(2, args.length));
    }

    public void printLowestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printActualLowestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestParam(LocalDateTime.now(), Arrays.asList(args).subList(2, args.length));
    }

    public void printHighestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printActualHighestParameter(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestParam(LocalDateTime.now(), Arrays.asList(args).subList(2, args.length));
    }

    public void printLowestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualLowestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printHighestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualHighestNValues(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printLowestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    public void printActualLowestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printLowestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), 1, Arrays.asList(args).subList(2, args.length));
    }

    public void printHighestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    public void printActualHighestValue(String[] args) {
        args = this.translateStrings(args);
        stationBase.printHighestNValues(Parameter.valueOf(args[1].toUpperCase()), LocalDateTime.now(), 1, Arrays.asList(args).subList(2, args.length));
    }

    public void printMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualMean(String[] args) {
        args = this.translateStrings(args);
        stationBase.printMean(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    public void printVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualVariance(String[] args) {
        args = this.translateStrings(args);
        stationBase.printVariance(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    public void printRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualRange(String[] args) {
        args = this.translateStrings(args);
        stationBase.printRange(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), LocalDateTime.now(), Arrays.asList(args).subList(3, args.length));
    }

    public void printFigure(String[] args) {
        args = this.translateStrings(args);
        stationBase.printFigure(Parameter.valueOf(args[1].toUpperCase()), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedFigure(String[] args) {
        args = this.translateStrings(args);
        stationBase.printFigure(Parameter.valueOf(args[1].toUpperCase()), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

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
