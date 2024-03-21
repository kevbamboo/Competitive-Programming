// Written by Kevin Song
// All code written according to hints
// Node class and Tree-making according to Prof Wu's CS540 2020 P2 Code

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class P2 {
  public static final String FILENAMEPREFIX = "C:\\Users\\DouDou\\Desktop\\Eclipse-Workspace\\CS540 P2\\src\\";
  public static BufferedWriter bw;
  
  public static int[][] cleanData(String filePath, int m) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    int[][] data = new int[1000][m];
    
    String line;
    int index = 0;
    while ((line = br.readLine()) != null) {
      String[] elements = line.split(",");
      boolean hasQ = false;
      for (int i = 0; i < elements.length; i++) {
        if (elements[i].equals("?")) hasQ = true;
        else data[index][i] = Integer.parseInt(elements[i]);
      }
      if (!hasQ) index++;
    }
    
    int[][] ret = new int[index][m];
    for (int i = 0; i < index; i++) {
      for (int j = 0; j < data[0].length; j++) {
        ret[i][j] = data[i][j];
      }
    }
    
    br.close();
    return ret;
  }
  
  public static double log2(double x) {
    return Math.log(x)/Math.log(2);
  }
  
  public static double binaryEntropy(double probability) {
    if (probability == 0 || probability == 1) return 0;
    else return -probability*log2(probability) - (1-probability)*log2(1-probability);
  }
  
  // Y = is case malignant
  // X = is feature > 5
  // double instead of int to avoid integer division
  public static double conditionalEntropy(double bN, double bP, double mN, double mP, double n) {
    double ret = 0.0;
    if (bN != 0 && (bN+mN) != 0) ret -= bN / n * log2(bN/(bN+mN));
    if (mN != 0 && (bN+mN) != 0) ret -= mN / n * log2(mN/(bN+mN));
    if (bP != 0 && (bP+mP) != 0) ret -= bP / n * log2(bP/(bP+mP));
    if (mP != 0 && (bP+mP) != 0) ret -= mP / n * log2(mP/(bP+mP));
    return ret;
  }
  
  public static double infoGain(int[][] data, int feature, int threshold) {
    int x = data[0].length;
    
    int numBenign = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i][x-1] == 2) numBenign++;
    }
    
    int bN = 0;
    int bP = 0;
    int mN = 0;
    int mP = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i][x-1] == 2) {
        if (data[i][feature-1] <= threshold) bN++;
        else bP++;
      } else {
        if (data[i][feature-1] <= threshold) mN++;
        else mP++;
      }
    }
    return binaryEntropy(1.0*numBenign/data.length) - conditionalEntropy(bN, bP, mN, mP, data.length);
  }
  
  public static ArrayList<int[][]> splitData(int[][] data, int feature, int threshold) {
    ArrayList<int[][]> ret = new ArrayList<int[][]>(2);
    int m = 11;
    int[][] less = new int[data.length][m];
    int lessIndex = 0;
    int[][] more = new int[data.length][m];
    int moreIndex = 0;
    
    for (int i = 0; i < data.length; i++) {
      if (data[i][feature-1] <= threshold) {
        for (int j = 0; j < m; j++) less[lessIndex][j] = data[i][j];
        lessIndex++;
      }
      else {
        for (int j = 0; j < m; j++) more[moreIndex][j] = data[i][j];
        moreIndex++;
      }
    }
    
    int[][] retLess = new int[lessIndex][m];
    for (int i = 0; i < lessIndex; i++) {
      for (int j = 0; j < m; j++) {
        retLess[i][j] = less[i][j];
      }
    }
    
    int[][] retMore = new int[moreIndex][m];
    for (int i = 0; i < moreIndex; i++) {
      for (int j = 0; j < m; j++) {
        retMore[i][j] = more[i][j];
      }
    }
    
    ret.add(retLess);
    ret.add(retMore);
    return ret;
  }
  
  public static int majorityLabel(int[][] data) {
    int negative = 0;
    int positive = 0;
    int m = 11;
    for (int i = 0; i < data.length; i++) {
      if (data[i][m-1] == 2) negative++;
      else positive++;
    }

    if (negative > positive) return 2;
    else return 4;
  }
  /*
  public static void makeTree(Node node) throws IOException {
    int[][] data = node.data;
    
    if (node.leftoverFeatures.length == 0) {
      node.label = majorityLabel(data);
      bw.write(" return " + node.label);
      node.leftChild = null;
      node.rightChild = null;
      return;
    }

    // check if all labels are the same
    int firstLabel = data[0][10];
    boolean allSame = true;
    for (int i = 1; i < data.length; i++) {
      if (data[i][10] != firstLabel) allSame = false;
    }
    if (allSame) {
      node.label = firstLabel;
      bw.write(" return " + node.label);
      node.leftChild = null;
      node.rightChild = null;
      return;
    }
    
    int[] leftoverFeatures = node.leftoverFeatures;
    
    double maxInfoGain = -Double.MAX_VALUE;
    int maxInfoGainIndex = -1;
    int maxInfoGainThreshold = -1;
    for (int i = 0; i < leftoverFeatures.length; i++) {
      for (int j = 1; j < 10; j++) {
        double infoGain = infoGain(data, leftoverFeatures[i], j);
        if (infoGain > maxInfoGain) {
          maxInfoGain = infoGain;
          maxInfoGainIndex = i;
          maxInfoGainThreshold = j;
        }
      }
    }
    node.threshold = maxInfoGainThreshold;
    
    if (maxInfoGain == 0) {
      node.label = majorityLabel(data);
      bw.write(" return " + node.label);
      return;
    }
    
    int[] childLeftoverFeatures = new int[leftoverFeatures.length-1];
    int i = 0;
    int j = 0;
    while (i < leftoverFeatures.length) {
      if (i == maxInfoGainIndex) {
        i++;
        continue;
      }
      childLeftoverFeatures[j++] = leftoverFeatures[i++];
    }
    
    ArrayList<int[][]> splitData = splitData(data, leftoverFeatures[maxInfoGainIndex], maxInfoGainThreshold);
    int[][] less = splitData.get(0);
    int[][] more = splitData.get(1);
    
    if (less.length != 0) {
      node.leftChild = new Node(less, childLeftoverFeatures, leftoverFeatures[maxInfoGainIndex]);
      bw.write("\n");
      bw.write("if (x" + leftoverFeatures[maxInfoGainIndex] + " <= " + maxInfoGainThreshold + ")");
      makeTree(node.leftChild);
    }
    else node.leftChild = null;
    
    if (more.length != 0) {
      node.rightChild = new Node(more, childLeftoverFeatures, leftoverFeatures[maxInfoGainIndex]);
      bw.write("\n");
      bw.write("else");
      makeTree(node.rightChild);
    }
    else node.rightChild = null;
    
    if (node.leftChild == null && node.rightChild == null) {
      node.label = majorityLabel(data);
      bw.write(" return " + node.label);
    }
  }
  */
  
  public static Node makeTree(int[][] data, int depth, int[] features) {
    boolean isLeaf = (depth == 6);
    Node node = null;
    
    if (!isLeaf) {
      if (features.length == 0) isLeaf = true;

      // check if all labels are the same
      int firstLabel = data[0][10];
      boolean allSame = true;
      for (int i = 1; i < data.length; i++) {
        if (data[i][10] != firstLabel) allSame = false;
      }
      
      if (allSame) isLeaf = true;
      else {
        double maxInfoGain = -Double.MAX_VALUE;
        int maxInfoGainIndex = -1;
        int maxInfoGainThreshold = -1;
        for (int i = 0; i < features.length; i++) {
          for (int j = 1; j < 10; j++) {
            double infoGain = infoGain(data, features[i], j);
            if (infoGain > maxInfoGain) {
              maxInfoGain = infoGain;
              maxInfoGainIndex = i;
              maxInfoGainThreshold = j;
            }
          }
        }
        if (maxInfoGain == 0) isLeaf = true;
        else {
          ArrayList<int[][]> splitData = splitData(data, features[maxInfoGainIndex], maxInfoGainThreshold);
          int[][] less = splitData.get(0);
          int[][] more = splitData.get(1);
          if (less.length == 0 || more.length == 0) isLeaf = true;
          else {
            int[] childFeatures = new int[features.length-1];
            int i = 0;
            int j = 0;
            while (i < features.length) {
              if (i == maxInfoGainIndex) {
                i++;
                continue;
              }
              childFeatures[j++] = features[i++];
            }
            
            node = new Node(features[maxInfoGainIndex], maxInfoGainThreshold, -1);
            node.leftChild = makeTree(less, depth+1, childFeatures);
            node.rightChild = makeTree(more, depth+1, childFeatures);
          }
        }
      }
    }
    if (isLeaf) { node = new Node(-1, -1, majorityLabel(data)); }
    return node;
  }
  
  public static void main(String[] args) throws IOException {
    int[][] data = cleanData(FILENAMEPREFIX + "breast-cancer-wisconsin.data", 11);
    int m = data[0].length;
    
    // q1p1
    int numBenign = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i][m-1] == 2) numBenign++;
    }
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q1p1.txt"));
    bw.write(numBenign + "," + (data.length-numBenign));
    bw.close();
    
    // q2p1
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q2p1.txt"));
    bw.write(Math.round(binaryEntropy(1.0*numBenign/data.length)*10000)/10000.0 + "");
    bw.close();
    
    // q3p1
    // assume threshold of 5
    int bN = 0;
    int bP = 0;
    int mN = 0;
    int mP = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i][m-1] == 2) {
        if (data[i][5] <= 5) bN++;
        else bP++;
      } else {
        if (data[i][5] <= 5) mN++;
        else mP++;
      }
    }
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q3p1.txt"));
    bw.write(bN + "," + bP + "," + mN + "," + mP);
    bw.close();
    
    // q4p1
    double infoGain = infoGain(data, 6, 5);
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q4p1.txt"));
    bw.write(Math.round(infoGain*10000)/10000.0 + "");
    bw.close();
    
    // q5p2
    Node root = makeTree(data, 0, new int[] {10,8,4,5,3,7});
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q5p2.txt"));
    root.print(bw);
    bw.close();
    
    // q7p2
    int[][] testData = cleanData(FILENAMEPREFIX + "test.txt", 10);
    bw = new BufferedWriter(new FileWriter(FILENAMEPREFIX + "q7p2.txt"));
    for (int i = 0; i < testData.length; i++) {
      bw.write(root.predict(testData[i]) + "");
      if (i != testData.length-1) bw.write(",");
    }
    bw.close();
   }
}
