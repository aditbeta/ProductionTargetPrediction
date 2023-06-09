import entity.Prediction;
import entity.Production;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prediksi Target Produksi");

        List<Production> productionList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("")) {
                break;
            }
            String[] inputData = input.split(",");
            productionList.add(new Production(
                    inputData[0],
                    Double.parseDouble(inputData[1]),
                    Double.parseDouble(inputData[2]),
                    Double.parseDouble(inputData[3])));
        }

        Prediction prediction = new Prediction();
        prediction.calculateTotal(productionList);
        prediction.calculatePrediction();

        printInput(productionList);
        printPrediction(productionList);
        printTotal(prediction);
        printRegression(prediction);
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+"s", string);
    }

    private static void printInput(List<Production> list) {
        System.out.println("=============== DATA PRODUKSI ===============");
        System.out.println(fixedLengthString("Bulan", 11)
                + fixedLengthString("Penjualan (X1)", 16)
                + fixedLengthString("Pemesanan (X2)", 16) +
                fixedLengthString("Target Produksi (Y)", 21));

        list.forEach(data -> {
            System.out.println(data.toString());
        });
    }

    private static void printPrediction(List<Production> list) {
        System.out.println("");System.out.println("");
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

        for (Production data : list) {
            System.out.println(data.toStringWithPrediction());
        }
    }

    private static void printTotal(Prediction prediction) {
        System.out.println("");System.out.println("");
        System.out.println("=============== TOTAL ===============");
        DecimalFormat format = new DecimalFormat("###");
        System.out.println(fixedLengthString("Total X1", 12) + fixedLengthString(format.format(prediction.getTotalX1()).toString(), 20));
        System.out.println(fixedLengthString("Total X2", 12) + fixedLengthString(format.format(prediction.getTotalX2()).toString(), 20));
        System.out.println(fixedLengthString("Total Y", 12) + fixedLengthString(format.format(prediction.getTotalY()).toString(), 20));
        System.out.println(fixedLengthString("Total X1^2", 12) + fixedLengthString(format.format(prediction.getTotalX1X1()).toString(), 20));
        System.out.println(fixedLengthString("Total X2^2", 12) + fixedLengthString(format.format(prediction.getTotalX2X2()).toString(), 20));
        System.out.println(fixedLengthString("Total Y^2", 12) + fixedLengthString(format.format(prediction.getTotalYY()).toString(), 20));
        System.out.println(fixedLengthString("Total X1Y", 12) + fixedLengthString(format.format(prediction.getTotalX1Y()).toString(), 20));
        System.out.println(fixedLengthString("Total X2Y", 12) + fixedLengthString(format.format(prediction.getTotalX2Y()).toString(), 20));
        System.out.println(fixedLengthString("Total X1X2", 12) + fixedLengthString(format.format(prediction.getTotalX1X2()).toString(), 20));
    }

    private static void printRegression(Prediction prediction) {
        System.out.println("");System.out.println("");
        System.out.println("=============== PERSAMAAN REGRESI ===============");
        System.out.println("b0 = " + prediction.getB0());
        System.out.println("b1 = " + prediction.getB1());
        System.out.println("b2 = " + prediction.getB2());
    }
}
