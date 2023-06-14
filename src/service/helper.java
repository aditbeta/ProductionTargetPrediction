package service;

import entity.Prediction;

public class helper {

    private static final Integer ROUNDER = 10000;

    public static double round(double n) {
        int rem = Integer.parseInt(String.valueOf(n).split("\\.")[1].split("")[0]);
        boolean isNegative = n < 0;

        n = (rem >= 5) ? Math.ceil(Math.abs(n)) : Math.floor(Math.abs(n));

        return isNegative ? (n * -1) : n;
    }

    public static Prediction calculatePrediction(Prediction prediction,
                                                 double a1, double b1, double c1, double d1,
                                                 double a2, double b2, double c2, double d2,
                                                 double a3, double b3, double c3, double d3) {
        double delta = solveUseCramer(
                a1, b1, c1,
                a2, b2, c2,
                a3, b3, c3);

        double x = formatCount(solveUseCramer(
                d1, b1, c1,
                d2, b2, c2,
                d3, b3, c3) / delta);
        prediction.setB0(x);

        double y = formatCount(solveUseCramer(
                a1, d1, c1,
                a2, d2, c2,
                a3, d3, c3) / delta);
        prediction.setB1(y);

        double z = formatCount(solveUseCramer(
                a1, b1, d1,
                a2, b2, d2,
                a3, b3, d3) / delta);
        prediction.setB2(z);

        return prediction;
    }

    private static double solveUseCramer(double a1, double b1, double c1,
                                         double a2, double b2, double c2,
                                         double a3, double b3, double c3) {
        return (a1 * (b2*c3 - b3*c2))
                - (a2 * (b1*c3 - b3*c1))
                + (a3 * (b1*c2 - b2*c1));
    }

    private static double formatShow(double n) {
        return round(n*ROUNDER)/ROUNDER;
    }

    private static double formatCount(double n) {
        return round(n*1000000000)/1000000000;
    }

    private static double nPrecision(double v, int precision) {
        int divider = (int) Math.pow(10, precision);
        return round(v*divider)/divider;
    }

}
