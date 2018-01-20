package ir.amirhmaleki;

/**
 * Created by Amir on 1/20/2018 in package PACKAGE_NAME of project Haup-Haup.
 */
public class GrammarRule {

    public static int ACCEPT = 1;
    public static int OTHER = 0;

    public String lhs;
    public int type;
    public String[] rhs;

    public GrammarRule(String symbol, int type, String[] rhs){
        this.lhs = symbol;
        this.type = type;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(lhs + " -> ");
        for (String sym :rhs)
            result.append(sym).append(" ");
        return result.toString();
    }
}
