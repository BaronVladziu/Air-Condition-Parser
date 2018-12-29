package app;

import gios.Parameter;
import parser.GiosParser;

import java.util.Arrays;
import java.util.Date;

public class StationBaseAdapter {

    private StationBase stationBase = new StationBase();

    public void printIndex(String[] args) {
        stationBase.printIndex(Arrays.asList(args).subList(1, args.length));
    }

    public void printValue(String[] args) {
        stationBase.printValues(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printActualValue(String[] args) {
        stationBase.printValues(Parameter.valueOf(args[1]), new Date(), Arrays.asList(args).subList(2, args.length));
    }

    public void printLowestParameter(String[] args) {
        stationBase.printLowestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printActualLowestParameter(String[] args) {
        stationBase.printLowestParam(new Date(), Arrays.asList(args).subList(2, args.length));
    }

    public void printHighestParameter(String[] args) {
        stationBase.printHighestParam(GiosParser.parseDate(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printActualHighestParameter(String[] args) {
        stationBase.printHighestParam(new Date(), Arrays.asList(args).subList(2, args.length));
    }

    public void printLowestNValues(String[] args) {
        stationBase.printLowestNValues(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualLowestNValues(String[] args) {
        stationBase.printLowestNValues(Parameter.valueOf(args[1]), new Date(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printHighestNValues(String[] args) {
        stationBase.printHighestNValues(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), Integer.parseInt(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualHighestNValues(String[] args) {
        stationBase.printHighestNValues(Parameter.valueOf(args[1]), new Date(), Integer.parseInt(args[2]), Arrays.asList(args).subList(3, args.length));
    }

    public void printLowestValue(String[] args) {
        stationBase.printLowestNValues(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    public void printActualLowestValue(String[] args) {
        stationBase.printLowestNValues(Parameter.valueOf(args[1]), new Date(), 1, Arrays.asList(args).subList(2, args.length));
    }

    public void printHighestValue(String[] args) {
        stationBase.printHighestNValues(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), 1, Arrays.asList(args).subList(3, args.length));
    }

    public void printActualHighestValue(String[] args) {
        stationBase.printHighestNValues(Parameter.valueOf(args[1]), new Date(), 1, Arrays.asList(args).subList(2, args.length));
    }

    public void printMean(String[] args) {
        stationBase.printMean(Parameter.valueOf(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedMean(String[] args) {
        stationBase.printMean(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualMean(String[] args) {
        stationBase.printMean(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), new Date(), Arrays.asList(args).subList(3, args.length));
    }

    public void printVariance(String[] args) {
        stationBase.printVariance(Parameter.valueOf(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedVariance(String[] args) {
        stationBase.printVariance(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualVariance(String[] args) {
        stationBase.printVariance(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), new Date(), Arrays.asList(args).subList(3, args.length));
    }

    public void printRange(String[] args) {
        stationBase.printRange(Parameter.valueOf(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedRange(String[] args) {
        stationBase.printRange(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualRange(String[] args) {
        stationBase.printRange(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), new Date(), Arrays.asList(args).subList(3, args.length));
    }

    public void printFigure(String[] args) {
        stationBase.printFigure(Parameter.valueOf(args[1]), Arrays.asList(args).subList(2, args.length));
    }

    public void printRangedFigure(String[] args) {
        stationBase.printFigure(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), GiosParser.parseDate(args[3]), Arrays.asList(args).subList(4, args.length));
    }

    public void printActualFigure(String[] args) {
        stationBase.printFigure(Parameter.valueOf(args[1]), GiosParser.parseDate(args[2]), new Date(), Arrays.asList(args).subList(3, args.length));
    }

}
