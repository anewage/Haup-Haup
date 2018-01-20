package ir.amirhmaleki;

/**
 * Created by Amir on 1/20/2018 in package PACKAGE_NAME of project Haup-Haup.
 */
public class GrammarRule {

    public static int ACCEPT = 1;
    public static int OTHER = 0;

    private String symbol;
    private int type;
    private String[] production;

    public GrammarRule(String symbol, int type, String[] production){
        this.symbol = symbol;
        this.type = type;
        this.production = production;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(symbol + "->");
        for (String sym :production)
            result.append(sym).append(" ");
        return result.toString();
    }
}
