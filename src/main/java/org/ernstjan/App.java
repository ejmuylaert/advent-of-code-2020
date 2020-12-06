package org.ernstjan;

import org.ernstjan.advent.day1.ReportRepair;
import org.ernstjan.advent.day2.PasswordValidator;
import org.ernstjan.advent.day3.Slope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
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
        long answer_triple = ReportRepair.find_triple(numbers);

        System.out.println("Answer (pair)  : " + answer);
        System.out.println("Answer (triple): " + answer_triple);

        /* --- */
        URL url = App.class.getClassLoader().getResource("day2_input.txt");
        List<String> lines = Files.readAllLines(Path.of(url.toURI()));

        long validRentalCount = PasswordValidator.countValidRental(lines);
        long validTobogganCount = PasswordValidator.countValidToboggan(lines);
        System.out.println("Answer (valid rental count)  : " + validRentalCount);
        System.out.println("Answer (valid Toboggan count)  : " + validTobogganCount);

        /* --- */
        url = App.class.getClassLoader().getResource("day3_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Slope slope = new Slope(lines);
        int trees = slope.treeEncounter(3, 1);
        long mult = slope.treeEncounterMultiplication();
        System.out.println("Answer (encountered trees)  : " + trees);
        System.out.println("Answer (multiplication)  : " + mult);

    }
}
