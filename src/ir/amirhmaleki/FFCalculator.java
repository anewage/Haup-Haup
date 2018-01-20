package ir.amirhmaleki;

import java.util.Vector;

public class FFCalculator {

    private Vector<GrammarRule> rules;

    private void calculateFirstSet(){

    }

    private void calculateFollowSet(){

    }

    /**
     * calculate First and Follow set
     * @param rules
     */
    public void calculate(Vector<GrammarRule> rules){
        this.rules = rules;
        calculateFirstSet();
        calculateFollowSet();
    }



}
