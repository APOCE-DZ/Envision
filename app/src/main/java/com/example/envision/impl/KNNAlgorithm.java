package com.ml.machinelearning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KNNAlgorithm {
    static String creditCardData = "creditcarddetails.csv";
    static String bankruptcyData = "bankrupcy.csv";
    static String breastCancerData = "BreastCancertrainingset.csv";

    public static HashMap<ArrayList<String>, Double> customSort(HashMap<ArrayList<String>, Double> hm) {
        List<Map.Entry<ArrayList<String>, Double>> currentRows = new LinkedList<Map.Entry<ArrayList<String>, Double>>(hm.entrySet());
        Collections.sort(currentRows, new Comparator<Map.Entry<ArrayList<String>, Double>>() {
            public int compare(Map.Entry<ArrayList<String>, Double> record1,
                               Map.Entry<ArrayList<String>, Double> record2) {
                return (record1.getValue()).compareTo(record2.getValue());
            }
        });
        HashMap<ArrayList<String>, Double> sortedrows = new LinkedHashMap<ArrayList<String>, Double>();
        for (Map.Entry<ArrayList<String>, Double> aa : currentRows) {
            sortedrows.put(aa.getKey(), aa.getValue());
        }
        return sortedrows;
    }

    public static boolean getPrediction(ArrayList<Double> testData, int dataset) {
        BufferedReader csvReader = null;
        int resCount = 0;
        HashMap<ArrayList<String>, Double> dict = new HashMap<ArrayList<String>, Double>();
        String trainingData = "";
        AssetManager asset = getAssets();
        if (dataset == 1) {
            trainingData = creditCardData;
        } else if (dataset == 2) {
            trainingData = breastCancerData;
        } else {
            trainingData = bankruptcyData;
        }
        try {
            csvReader = new BufferedReader(new InputStreamReader(asset.open(trainingData), Charset.forName("UTF-8")));
            String row = "";
            csvReader.readLine()
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                ArrayList<String> tuple = new ArrayList(Arrays.asList(data));
                double distance = euclideanlength(tuple, testData);
                dict.put(tuple, distance);

            }
            HashMap<ArrayList<String>, Double> sortedDic = customSort(dict);
            int i = 0;
            for (Map.Entry<ArrayList<String>, Double> pair : sortedDic.entrySet()) {
                int result = Integer.parseInt(pair.getKey().get(pair.getKey().size() - 1));
                if (result == 1) {
                    resCount++;
                } else {
                    resCount--;
                }
                i++;
                if (i == 3) {
                    break;
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resCount > 0 ? true : false;
    }

    private static double euclideanlength(ArrayList<String> trainingtuple, ArrayList<Double> testData) {
        double length = 0.0;
        for (int i = 0; i < testData.size() - 1; i++) {
            length += Math.pow((Double.parseDouble(trainingtuple.get(i)) - testData.get(i)), 2);
        }

        return Math.sqrt(length);
    }

}
