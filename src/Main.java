import entity.Prediction;
import entity.Production;
import repository.PredictionRepository;
import repository.ProductionObject;
import repository.ProductionRepository;
import show.MainFrame;
import show.ProductionInput;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Prediksi Target Produksi");
        new MainFrame();

        List<Production> productions = ProductionRepository.readAll();

        Prediction prediction = new Prediction();
        prediction.calculateTotal(productions);
        prediction.calculatePrediction();
        PredictionRepository.insert(prediction);
    }
}
