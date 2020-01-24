import moss.algorithm.ComparisonStrategy;
import moss.algorithm.TokenHashingStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader file1 = new BufferedReader(new FileReader("test_program1.txt"));
        BufferedReader file2 = new BufferedReader(new FileReader("test_program2.txt"));

        BufferedReader file1cpp = new BufferedReader(new FileReader("test_program1.cpp"));
        BufferedReader file2cpp = new BufferedReader(new FileReader("test_program2.cpp"));
        ComparisonStrategy comparisonAlgo = new TokenHashingStrategy();
        ComparisonStrategy comparisonAlgo2 = new TokenHashingStrategy();
        Double result1 = comparisonAlgo.compare(file1, file2);
        Double result2 = comparisonAlgo2.compare(file1cpp, file2cpp);

        System.out.println("Java comparison: " + result1);
        System.out.println("C++ comparison: " + result2);

    }
}
