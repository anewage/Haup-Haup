package ir.amirhmaleki;

import java.util.Hashtable;
import java.util.Vector;

public class FFCalculator {

    private Vector<GrammarRule> rules;
    private Hashtable<String, Vector<String>> first;
    private Hashtable<String, Vector<String>> follow;

    public FFCalculator(){
        first = new Hashtable<String, Vector<String>>();
        follow = new Hashtable<String, Vector<String>>();
    }

    /**
     * Calculates and saves the FirstSet for each symbol, inside the follow table
     */
    private void calculateFirstSet(){
        // First, Iterating over all of the terminals
        boolean changed = false;
        for (GrammarRule rule : rules) {
            // Adding the terminal symbol to first set
            if (GrammarRule.isTerminal(rule.rhs[0])){
                Vector<String> tmp = first.getOrDefault(rule.lhs, new Vector<String>());
                if (!vectorAlreadyContains(tmp, rule.rhs[0], false)){
                    tmp.add(rule.rhs[0]);
                    first.put(rule.lhs, tmp);
                    changed = true;
                }
            }
        }

        // Now, Iterating over the non-terminals
        while(changed){
            changed = false;
            for (GrammarRule rule : rules) {
                if (!GrammarRule.isTerminal(rule.rhs[0])){
                    Vector<String> tempFirst = first.getOrDefault(rule.rhs[0], new Vector<String>());
                    Object[] result = unionStringArray(tempFirst, first.getOrDefault(rule.lhs, new Vector<String>()));
                    boolean flag = (Boolean) result[1];
                    if (flag) {
                        changed = true;
                        first.put(rule.lhs, (Vector<String>) result[0]);
                    }
                }
            }
        }
    }


    /**
     * Calculates and saves the FollowSet for each symbol, inside the follow table
     */
    private void calculateFollowSet(){
        // TODO
        // First, Iterating over all of the terminals
        boolean changed = false;
        for (GrammarRule rule : rules) {
            for (int i = 0; i < rule.rhs.length; i++) {
                String symbol = rule.rhs[i];
                if (!GrammarRule.isTerminal(symbol)){

                }
            }
        }
    }

    private boolean vectorAlreadyContains(Vector<String> vec, String str, boolean ignoreCase){
        for (String s : vec) {
            if (ignoreCase) {
                if (s.equalsIgnoreCase(str)) {
                    return true;
                }
            } else {
                if (s.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Object[] unionStringArray(Vector<String> arr1, Vector<String> arr2){
        Vector<String> res = new Vector<String>();
        Boolean changed = false;
        res.addAll(arr2);
        for (String str1 : arr1) {
            if (!vectorAlreadyContains(res, str1,false)){
                res.add(str1);
                changed = true;
            }
        }
        Object[] result = {res, changed};
        return result;
    }

    /**
     * calculate First and Follow set
     * @param rules
     */
    public void calculate(Vector<GrammarRule> rules){
        this.rules = rules;
        calculateFirstSet();
        System.out.println("First Set: " + first);
//        calculateFollowSet();
//        System.out.println(follow);
    }



}
