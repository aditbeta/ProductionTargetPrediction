import entity.ProductionData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prediksi Target Produksi");

        List<ProductionData> productionDataList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("")) {
                break;
            }
            String[] inputData = input.split(",");
            productionDataList.add(new ProductionData(
                    inputData[0],
                    Double.parseDouble(inputData[1]),
                    Double.parseDouble(inputData[2]),
                    Double.parseDouble(inputData[3])));
        }

        System.out.println("=============== DATA PRODUKSI ===============");
        System.out.println(fixedLengthString("Bulan", 11)
                + fixedLengthString("Penjualan (X1)", 16)
                + fixedLengthString("Pemesanan (X2)", 16) +
                fixedLengthString("Target Produksi (Y)", 21));

        productionDataList.forEach(data -> {
            System.out.println(data.toString());
        });

        System.out.println("");
        System.out.println("");
        System.out.println("=============== DATA PRODUKSI DIBULATKAN ===============");
        System.out.println(fixedLengthString("Bulan", 11)
                + fixedLengthString("Penjualan (X1)", 16)
                + fixedLengthString("Pemesanan (X2)", 16) +
                fixedLengthString("Target Produksi (Y)", 21));

        productionDataList.forEach(data -> {
            data.setSell(data.getSell()/10000);
            data.setOrder(data.getOrder()/10000);
            data.setTarget(data.getTarget()/10000);

            System.out.println(data.toString());
        });

        System.out.println("");
        System.out.println("");
        System.out.println("=============== PREDIKSI ===============");
        System.out.println(fixedLengthString("Bulan", 11)
                + fixedLengthString("Penjualan (X1)", 16)
                + fixedLengthString("Pemesanan (X2)", 16) +
                fixedLengthString("Target Produksi (Y)", 21) +
                fixedLengthString("X1²", 20) +
                fixedLengthString("X2²", 20) +
                fixedLengthString("Y²", 20) +
                fixedLengthString("Y*X1", 20) +
                fixedLengthString("Y*X2", 20) +
                fixedLengthString("X1*X2", 20));

        double x1 = 0;
        double x2 = 0;
        double y = 0;
        double x1x1 = 0;
        double x2x2 = 0;
        double yy = 0;
        double x1y = 0;
        double x2y = 0;
        double x1x2 = 0;

        for (ProductionData data : productionDataList) {
            x1 += data.getSell();
            x2 += data.getOrder();
            y += data.getTarget();
            x1x1 += data.getX1x1();
            x2x2 += data.getX2x2();
            yy += data.getYy();
            x1y += data.getX1y();
            x2y += data.getX2y();
            x1x2 += data.getX1x2();

            System.out.println(data.toStringWithPrediction());
        }

        System.out.println();
        System.out.println();
        System.out.println("=============== TOTAL ===============");
        DecimalFormat format = new DecimalFormat("###");
        System.out.println(fixedLengthString("Total X1", 12) + fixedLengthString(format.format(x1).toString(), 20));
        System.out.println(fixedLengthString("Total X2", 12) + fixedLengthString(format.format(x2).toString(), 20));
        System.out.println(fixedLengthString("Total Y", 12) + fixedLengthString(format.format(y).toString(), 20));
        System.out.println(fixedLengthString("Total X1^2", 12) + fixedLengthString(format.format(x1x1).toString(), 20));
        System.out.println(fixedLengthString("Total X2^2", 12) + fixedLengthString(format.format(x2x2).toString(), 20));
        System.out.println(fixedLengthString("Total Y^2", 12) + fixedLengthString(format.format(yy).toString(), 20));
        System.out.println(fixedLengthString("Total X1Y", 12) + fixedLengthString(format.format(x1y).toString(), 20));
        System.out.println(fixedLengthString("Total X2Y", 12) + fixedLengthString(format.format(x2y).toString(), 20));
        System.out.println(fixedLengthString("Total X1X2", 12) + fixedLengthString(format.format(x1x2).toString(), 20));

        System.out.println();
        System.out.println();
        System.out.println("=============== PERSAMAAN REGRESI ===============");
        calculate(12, x1, x2,
                x1, x1x1, x1x2,
                x2, x1x2, x2x2,
                y, x1y, x2y);
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+"s", string);
    }

    private static void calculate(double b01, double b11, double b21,
                                  double b02, double b12, double b22,
                                  double b03, double b13, double b23,
                                  double d1, double d2, double d3) {
        double delta = cramer(
                b01, b11, b21,
                b02, b12, b22,
                b03, b13, b23);

        double x = cramer(
                d1, b11, b21,
                d2, b12, b22,
                d3, b13, b23) / delta;

        double y = cramer(
                b01, d1, b21,
                b02, d2, b22,
                b03, d3, b23) / delta;

        double z = cramer(
                b01, b11, d1,
                b02, b12, d2,
                b03, b13, d3) / delta;

        System.out.println("b1 = " + y);
        System.out.println("b2 = " + z);
        System.out.println("b0 = " + x);
    }

    private static double cramer(double a1, double b1, double c1,
                                 double a2, double b2, double c2,
                                 double a3, double b3, double c3) {
        return (a1 * (b2*c3 - b3*c2))
                - (a2 * (b1*c3 - b3*c1))
                + (a3 * (b1*c2 - b2*c1));
    }
}
