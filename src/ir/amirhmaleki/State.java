package ir.amirhmaleki;

import java.util.Vector;

/**
 * Created by Amir on 1/22/2018 in package ir.amirhmaleki of project Haup-Haup.
 */
public class State {

    private int number;
    public Vector<GrammarRule> stateRules;
    private Vector<GrammarRule> grammar;
    private Vector<State> nextStates;
    private State parent;

    public State(int number, Vector<GrammarRule> production, Vector<GrammarRule> grammar, State parent){
        this.number = number;
        this.grammar = grammar;
        this.parent = parent;
        stateRules = new Vector<GrammarRule>();
        nextStates = new Vector<State>();
        stateRules.addAll(production);
    }

    private void processInitialProductions() throws CloneNotSupportedException {
        boolean added = false;
        do{
            Vector<GrammarRule> tmp = new Vector<>();
            for (GrammarRule rule : stateRules) {
                // Reduce Happens
                if (rule.cursorIndex == rule.rhs.length){
                    added = false;
                    break;
                }
                if(!GrammarRule.isTerminal(rule.rhs[rule.cursorIndex])){
                    for (GrammarRule extended :grammar) {
                        if (extended.lhs.equals(rule.rhs[rule.cursorIndex])){
                            GrammarRule clonned = (GrammarRule) extended.clone();
                            clonned.setCursorIndex(0);
                            boolean toAdd = true;
                            for (GrammarRule toCheck : tmp)
                                toAdd &= !toCheck.equals(clonned);
                            for (GrammarRule toCheck : stateRules)
                                toAdd &= !toCheck.equals(clonned);
                            if (toAdd)
                                tmp.add(clonned);
                        }
                    }
                }
            }
            if (tmp.size() > 0){
                stateRules.addAll(tmp);
                added = true;
            } else
                added = false;
        }while (added);
    }

    public void generateNextStates() throws Exception {
        for (GrammarRule rule: stateRules) {
            Vector<GrammarRule> nextStateProductions = new Vector<>();
            GrammarRule clonned = (GrammarRule) rule.clone();
            if (clonned.cursorIndex == clonned.rhs.length)
                break;
            clonned.setCursorIndex(clonned.cursorIndex+1);
            nextStateProductions.add(clonned);
            for (GrammarRule rule2: stateRules){
                if (!rule.equals(rule2)){
                    if (rule.rhs[rule.cursorIndex].equals(rule2.rhs[rule2.cursorIndex])){
                        GrammarRule clonned2 = (GrammarRule) rule2.clone();
                        clonned2.setCursorIndex(clonned2.cursorIndex+1);
                        nextStateProductions.add(clonned2);
                    }
                }
            }
            if (nextStateProductions.size() > 0){
                State next = new State(number + nextStates.size() + 1, nextStateProductions, grammar, this);
                next.initiateGeneration();
                if (allowStateAddition(next)) {
                    nextStates.add(next);
                    next.generateNextStates();
                }

            }
        }
    }

    protected boolean allowStateAddition(State s){
        if (this.equals(s))
            return false;
        for (State toCheck : nextStates)
            if (toCheck.equals(s))
                return false;
        if (parent == null)
            return true;
        else
            return parent.allowStateAddition(s);
    }

    public void initiateGeneration() throws Exception {
        processInitialProductions();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("State #" + number + ":\n");
        stateRules.forEach(grammarRule -> res.append("\t").append(grammarRule).append("\n"));
        nextStates.forEach(res::append);
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        State toCheck = (State) obj;
        if (toCheck.stateRules.size() != this.stateRules.size())
            return false;
        for (GrammarRule rule : this.stateRules) {
            boolean found = false;
            for (GrammarRule rule2 : toCheck.stateRules) {
                if (rule.equals(rule2)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }
}
