package app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }
        StationBaseAdapter stationBaseAdapter = new StationBaseAdapter();
        Class c = stationBaseAdapter.getClass();
        Class parameter = String[].class;
        try {
            Method method = c.getDeclaredMethod(args[0], parameter);
            method.invoke(stationBaseAdapter, args);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "\n" +
                "'printIndex [stations]' - prints actual index. You can omit 'stations' to search for every one\n" +
                "\n" +
                "'printValues parameter date [stations]' - prints values of given 'parameter'\n" +
                "'printLowestParameter date [stations]' - prints parameter with lowest value\n" +
                "'printLowestValue parameter date [stations]' - prints lowest value of given 'parameter'\n" +
                "'printLowestNValues parameter date n [stations]' - prints 'n' lowest value of given 'parameter'\n" +
                "You can replace 'Lowest' with 'Highest'. You can also add 'Actual' after 'print' and omit 'date'.\n" +
                "\n" +
                "'printRangedMean parameter startDate endDate [stations]' - prints mean value of given 'parameter'\n" +
                "'printRangedVariance parameter startDate endDate [stations]' - prints variance of given 'parameter'\n" +
                "'printRangedRange parameter startDate endDate [stations]' - prints range of values of given 'parameter'\n" +
                "'printRangedFigure parameter startDate endDate [stations]' - prints bar chart of values from '[stations]'\n" +
                "You can replace 'Ranged' with 'Actual' to set 'endDate' to now. You can also omit 'Ranged' and both dates.\n");
    }

}
