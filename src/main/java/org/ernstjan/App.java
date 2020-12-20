package org.ernstjan;

import org.ernstjan.advent.day1.ReportRepair;
import org.ernstjan.advent.day10.Adapter;
import org.ernstjan.advent.day11.WaitingAreaPlan;
import org.ernstjan.advent.day2.PasswordValidator;
import org.ernstjan.advent.day3.Slope;
import org.ernstjan.advent.day4.PassportDatabase;
import org.ernstjan.advent.day5.Plane;
import org.ernstjan.advent.day6.CustomsDeclaration;
import org.ernstjan.advent.day7.Graph;
import org.ernstjan.advent.day8.Computer;
import org.ernstjan.advent.day8.Debugger;
import org.ernstjan.advent.day9.Decrypt;

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

        /* --- */
        url = App.class.getClassLoader().getResource("day4_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        PassportDatabase passportDatabase = new PassportDatabase();
        passportDatabase.addPassports(lines);
        int passports = passportDatabase.size();
        long validPassports = passportDatabase.validPassports();
        System.out.println("Answer (valid passports)  : " + passports);
        System.out.println("Answer (validated passports)  : " + validPassports);

        /* --- */
        url = App.class.getClassLoader().getResource("day5_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Plane plane = new Plane();
        plane.addSeats(lines);
        int id = plane.highestSeatId();
        int availableId = plane.availableSeat();
        System.out.println("Answer (highest seat id)  : " + id);
        System.out.println("Answer (available seat id)  : " + availableId);

        /* --- */
        url = App.class.getClassLoader().getResource("day6_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        CustomsDeclaration declaration = new CustomsDeclaration();
        int sum = declaration.declare(lines);
        long allSum = declaration.declareCommon(lines);
        System.out.println("Answer (sum)  : " + sum);
        System.out.println("Answer (common)  : " + allSum);

        /* --- */
        url = App.class.getClassLoader().getResource("day7_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Graph graph = new Graph();
        graph.read(lines);
        long possibilities = graph.possibilities("shiny gold bag");
        long containing = graph.contains("shiny gold bag");
        System.out.println("Answer (gold bag possibilities)  : " + possibilities);
        System.out.println("Answer (gold bag containing)  : " + containing);

        /* --- */
        url = App.class.getClassLoader().getResource("day8_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Computer computer = new Computer(lines);
        computer.run();
        int accumulator = computer.getAccumulator();
        Debugger debugger = new Debugger(lines);
        int fixed = debugger.fix();
        System.out.println("Answer (accumulator) :" + accumulator);
        System.out.println("Answer (fixed) :" + fixed);

        /* --- */
        url = App.class.getClassLoader().getResource("day9_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));
        Decrypt decrypt = Decrypt.fromLines(lines, 25);
        long l = decrypt.firstError();
        long weak = decrypt.weakness();

        System.out.println("Answer (decrypt) :" + l);
        System.out.println("Answer (weakness) :" + weak);

        /* --- */
        url = App.class.getClassLoader().getResource("day10_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));
        long jolts = Adapter.calculate(lines);
        long combinations = Adapter.combinations(lines);

        System.out.println("Answer (jolts) :" + jolts);
        System.out.println("Answer (combinations) :" + combinations);

        /* --- */
        url = App.class.getClassLoader().getResource("day11_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        long iterations = WaitingAreaPlan.simulate(lines);
        long iterationsNoFloor = WaitingAreaPlan.simulateNoFloor(lines);
        System.out.println("Answer (iterations) :" + iterations);
        System.out.println("Answer (no floor) :" + iterationsNoFloor);
    }
}
