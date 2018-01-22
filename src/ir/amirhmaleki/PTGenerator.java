package ir.amirhmaleki;

import java.io.PrintStream;
import java.util.Vector;

public class PTGenerator {

    private Vector<GrammarRule> rules;
    private PrintStream outputStream;

    public PTGenerator(){
        outputStream = System.out;
    }

    public void generateAutomata(Vector<GrammarRule> rules) throws Exception {
        this.rules = rules;
        GrammarRule start = getStartSymbol();
        if (start == null)
            throw new Exception("Grammar has no starting symbol/rule!");
        start = (GrammarRule) start.clone();
        start.setCursorIndex(0);
        Vector<GrammarRule> production = new Vector<GrammarRule>();
        production.add(start);
        State s0 = new State(0, production, rules, null);
        s0.initiateGeneration();
        s0.generateNextStates();
        System.out.println(s0);
    }

    private GrammarRule getStartSymbol() {
        for (GrammarRule rule : rules)
            if(rule.type == GrammarRule.ACCEPT)
                return rule;
        return null;
    }

    public void setPrintStream(PrintStream ps){
        outputStream = ps;
    }

}
