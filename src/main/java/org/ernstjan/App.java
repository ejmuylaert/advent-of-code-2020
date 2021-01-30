package org.ernstjan;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.ernstjan.advent.day1.ReportRepair;
import org.ernstjan.advent.day10.Adapter;
import org.ernstjan.advent.day11.WaitingAreaPlan;
import org.ernstjan.advent.day12.Boat;
import org.ernstjan.advent.day12.WaypointBoat;
import org.ernstjan.advent.day13.Departure;
import org.ernstjan.advent.day2.PasswordFileParser;
import org.ernstjan.advent.day2.PasswordLine;
import org.ernstjan.advent.day3.Delta;
import org.ernstjan.advent.day3.Slope;
import org.ernstjan.advent.day4.PassportDatabase;
import org.ernstjan.advent.day5.BoardingPass;
import org.ernstjan.advent.day5.Plane;
import org.ernstjan.advent.day6.CustomsDeclaration;
import org.ernstjan.advent.day7.Graph;
import org.ernstjan.advent.day8.Computer;
import org.ernstjan.advent.day8.Debugger;
import org.ernstjan.advent.day9.Decrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(App.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Autowired
    private PassportDatabase passportDatabase;

    @Override
    public void run(String... args) throws Exception {

        InputStream is = App.class.getClassLoader().getResourceAsStream("day1_input.txt");
        ArrayList<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                Integer number = Integer.valueOf(line);
                numbers.add(number);
            }
        }

        ReportRepair reportRepair = new ReportRepair(2020);
        long answer = reportRepair.find(2, numbers).get();
        long answer_triple = reportRepair.find(3, numbers).get();

        System.out.println("Answer (pair)  : " + answer);
        System.out.println("Answer (triple): " + answer_triple);

        /* --- */
        URL url = App.class.getClassLoader().getResource("day2_input.txt");

        CharStream inputStream = CharStreams.fromPath(Path.of(url.toURI()));
        List<PasswordLine> passwordLines = PasswordFileParser.parse(inputStream);

        System.out.println("Answer (valid rental count)  : " + PasswordLine.countValid(passwordLines, PasswordLine::isValidRental));
        System.out.println("Answer (valid Toboggan count)  : " + PasswordLine.countValid(passwordLines, PasswordLine::isValidToboggan));

        /* --- */
        url = App.class.getClassLoader().getResource("day3_input.txt");
        List<String> lines = Files.readAllLines(Path.of(url.toURI()));


        Slope slope = new Slope(lines);
        int trees = slope.treeEncounter(new Delta(3, 1));
        List<Delta> travels = List.of(new Delta(1, 1),
                new Delta(3, 1),
                new Delta(5, 1),
                new Delta(7, 1),
                new Delta(1, 2));
        long mult = slope.treeEncountersProduct(travels);
        System.out.println("Answer (encountered trees)  : " + trees);
        System.out.println("Answer (multiplication)  : " + mult);

        /* --- */
        url = App.class.getClassLoader().getResource("day4_input.txt");
        inputStream = CharStreams.fromPath(Path.of(url.toURI()));
//        PassportDatabase passportDatabase = new PassportDatabase();
        passportDatabase.addPassports(inputStream);
        int passports = passportDatabase.size();
        long validPassports = passportDatabase.numberOfValidPassports();
        System.out.println("Answer (valid passports)  : " + passports);
        System.out.println("Answer (validated passports)  : " + validPassports);

        /* --- */
        url = App.class.getClassLoader().getResource("day5_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Plane plane = new Plane();
        plane.board(BoardingPass.fromCodes(lines));
        long id = plane.highestSeatId();
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

        /* --- */
        url = App.class.getClassLoader().getResource("day12_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        Boat boat = new Boat();
        boat.move(lines);
        int distance = boat.manhattanDistance();

        WaypointBoat waypointBoat = new WaypointBoat();
        waypointBoat.move(lines);

        System.out.println("Answer (distance) :" + distance);
        System.out.println("Answer (waypoint distance) :" + waypointBoat.manhattanDistance());

        /* --- */
        url = App.class.getClassLoader().getResource("day13_input.txt");
        lines = Files.readAllLines(Path.of(url.toURI()));

        System.out.println("Answer (answer) :" + Departure.answer(lines));
//        System.out.println("Answer (departure time) :" + Departure.answer(lines.get(1)));
    }
}
