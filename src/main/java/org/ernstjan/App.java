package org.ernstjan;

import org.ernstjan.advent.day1.ReportRepair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException {
        InputStream is = App.class.getClassLoader().getResourceAsStream("day1_input.txt");
        ArrayList<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer number = Integer.valueOf(line);
                numbers.add(number);
            }
        }

        int answer = ReportRepair.find(numbers);

        System.out.println("Answer: " + answer);
    }
}
