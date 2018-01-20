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

    /**
     * Constructor method
     */
    public Main(){
        this.sc = new Scanner(System.in);
        this.rules = new Vector<GrammarRule>();
        ffCalculator = new FFCalculator();
    }

    /**
     * Does the main job!
     * @throws Exception
     */
    public void initiateParsing() throws Exception {
        understandRules();
        ffCalculator.calculate(rules);
    }

    /**
     * Reads the input grammar and saves the rules inside rules vector
     */
    private void understandRules() {
        String[] tokens = null;
        tokens = sc.nextLine().split(" ");
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
