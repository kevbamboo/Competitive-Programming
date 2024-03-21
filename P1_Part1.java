// Written by Kevin Song
// All code written according to hints
// Code Attribution to Prof. Wu's 2020 CS540 P1

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class P1_Part1 {

  private static final int EPOCHS = 100;
  private static final double ALPHA  = 0.01;
  
  public static double[][] getData(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    double[][] data = new double[20000][785];
    
    String line;
    int index = 0;
    while ((line = br.readLine()) != null) {
      String[] lineData = line.split(",");

      if (lineData[0].equals("5")) data[index][0] = 0.0;
      else if (lineData[0].equals("9")) data[index][0] = 1.0;
      else continue;
      
      for (int i = 1; i < lineData.length; i++) {
        data[index][i] = Double.parseDouble(lineData[i]) / 255.0;
      }

      index++;
    }
    
    double[][] ret = new double[index][785];
    for (int i = 0; i < index; i++) {
      for (int j = 0; j < data[0].length; j++) {
        ret[i][j] = data[i][j];
      }
    }

    br.close();
    return ret;
  }

  public static double[][] getTestData(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    double[][] data = new double[20000][784];
    
    String line;
    int index = 0;
    while ((line = br.readLine()) != null) {
      String[] lineData = line.split(",");
      
      for (int i = 0; i < lineData.length; i++) {
        data[index][i] = Double.parseDouble(lineData[i]) / 255.0;
      }

      index++;
    }

    double[][] ret = new double[index][784];
    for (int i = 0; i < index; i++) {
      for (int j = 0; j < data[0].length; j++) {
        ret[i][j] = data[i][j];
      }
    }

    br.close();
    return ret;
  }

  public static double sigmoid(double x) {
    return 1.0 / (1.0 + Math.exp(-x));
  }
  
  public static void A(String[] args) throws IOException {
    double[][] data = getData("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\src\\mnist_train.csv");
    int m = 784;
    
    // q1
    BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\src\\q1p1.txt"));
    double[] firstRow = data[0];
    for (int i = 1; i < firstRow.length-1; i++) {
      bw.write(Math.round(firstRow[i]*100)/100.0 + ",");
    }
    bw.write(firstRow[firstRow.length-1] + "");
    bw.close();
    
    // q2
    Random r = new Random();
    double[] weights = new double[m];
    double bias = 2*r.nextDouble() - 1;
    for (int i = 0; i < weights.length; i++) {
      weights[i] = 2*r.nextDouble() - 1;
    }

    double prevLoss = 0.0;
    for (int epoch = 0; epoch < EPOCHS; epoch++) {
      double[] a = new double[data.length];
      for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
        double sum = 0.0;
        for (int j = 0; j < weights.length; j++) {
          sum += weights[j] * data[dataIndex][j+1];
        }
        a[dataIndex] = sigmoid(sum + bias);
      }
      
      for (int j = 0; j < weights.length; j++) {
        double weightChange = 0.0;
        for (int i = 0; i < data.length; i++) {
          weightChange += (a[i] - data[i][0]) * data[i][j+1];
        }
        weights[j] -= weightChange*ALPHA;
      }
      
      double biasChange = 0.0;
      for (int i = 0; i < data.length; i++) {
        biasChange += (a[i] - data[i][0]);
      }
      bias -= biasChange*ALPHA;

      double loss = 0.0;
      for (int i = 0; i < a.length; i++) {
        double a_i = a[i];

        if (a_i < 0.0001 || a_i > 0.9999) loss += 100.0;
        else if (data[i][0] == 0.0) loss -= Math.log(1-a_i);
        else loss -= Math.log(a_i);
      }
      
      // Prof's code for checking error
      double loss_reduction = prevLoss-loss;
      prevLoss = loss;

      double correct = 0.0;
      for (int ind = 0; ind < data.length; ind++) {
          if ((data[ind][0] == 1.0 && a[ind] >= 0.5) || (data[ind][0] == 0.0 && a[ind] < 0.5))
              correct += 1.0;
      }
      // count correct
      double acc = correct / data.length;
      System.out.println("epoch = " + epoch + ", loss = " + loss + ", loss reduction = " + loss_reduction
              + ", correctly classified = " + acc);
    }
    bw = new BufferedWriter(new FileWriter("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\src\\q2p1.txt"));
    for (int i = 0; i < weights.length; i++) {
      bw.write(Math.round(weights[i]*10000)/10000.0 + ",");
    }
    bw.write(Math.round(bias*10000)/10000.0 + "");
    bw.close();

    // q3
    double[][] testData = getTestData("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\test (1).txt");
    double[] testA = new double[testData.length];
    for (int testDataIndex = 0; testDataIndex < testData.length; testDataIndex++) {
      double sum = 0.0;
      for (int j = 0; j < weights.length; j++) {
        sum += weights[j] * testData[testDataIndex][j];
      }
      testA[testDataIndex] = sigmoid(sum + bias);
    }
    bw = new BufferedWriter(new FileWriter("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\src\\q3p1.txt"));
    for (int i = 0; i < testA.length-1; i++) {
      bw.write(Math.round(testA[i]*100.0)/100.0 + ",");
    }
    bw.write(Math.round(testA[testA.length-1]*100.0)/100.0 + "");
    bw.close();

    // q4
    bw = new BufferedWriter(new FileWriter("C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P1\\src\\q4p1.txt"));
    for (int i = 0; i < testA.length-1; i++) {
      bw.write(Math.round(testA[i]) + ",");
    }
    bw.write(Math.round(testA[testA.length-1]) + "");
    bw.close();
  }
}