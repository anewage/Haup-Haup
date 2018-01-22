package ir.amirhmaleki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    private Scanner sc;
    private Vector<GrammarRule> rules;
    private FFCalculator ffCalculator;
    private PTGenerator ptGenerator;

    /**
     * Constructor method
     */
    public Main(){
        this.sc = new Scanner(System.in);
        this.rules = new Vector<GrammarRule>();
        ffCalculator = new FFCalculator();
        ptGenerator = new PTGenerator();
    }

    /**
     * Does the main job!
     */
    public void initiateParsing() throws Exception {
        // First read the grammar and identify its rules
        understandRules();
        // Calculate first and follow set
        ffCalculator.calculate(rules);
        ptGenerator.generateAutomata(rules);
    }

    /**
     * Reads the input grammar and saves the rules inside rules vector
     */
    private void understandRules() throws Exception {
        String[] tokens = null;
        tokens = sc.nextLine().split(" ");
        if (tokens[0].equalsIgnoreCase("end"))
            throw new Exception("Empty grammar!");
        GrammarRule accept = new GrammarRule(tokens[0], GrammarRule.ACCEPT, Arrays.copyOfRange(tokens, 2, tokens.length));
        rules.add(accept);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("end"))
                break;
            tokens = line.split(" ");
            GrammarRule tmp = new GrammarRule(tokens[0], GrammarRule.OTHER, Arrays.copyOfRange(tokens, 2, tokens.length));
            rules.add(tmp);
        }
    }

    public static void main(String[] args) {
        try {
            new Main().initiateParsing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
