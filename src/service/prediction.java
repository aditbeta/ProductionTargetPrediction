package service;

import entity.Prediction;

public class prediction {

    public static Prediction calculatePrediction(double a1, double b1, double c1, double d1,
                                                  double a2, double b2, double c2, double d2,
                                                  double a3, double b3, double c3, double d3) {
        double delta = solveUseCramer(
                a1, b1, c1,
                a2, b2, c2,
                a3, b3, c3);

        double x = solveUseCramer(
                d1, b1, c1,
                d2, b2, c2,
                d3, b3, c3) / delta;

        double y = solveUseCramer(
                a1, d1, c1,
                a2, d2, c2,
                a3, d3, c3) / delta;

        double z = solveUseCramer(
                a1, b1, d1,
                a2, b2, d2,
                a3, b3, d3) / delta;

        return new Prediction(x, y, z);
    }

    public static double solveUseCramer(double a1, double b1, double c1,
                                        double a2, double b2, double c2,
                                        double a3, double b3, double c3) {

        return (a1 * (b2*c3 - b3*c2))
                - (a2 * (b1*c3 - b3*c1))
                + (a3 * (b1*c2 - b2*c1));
    }
}
