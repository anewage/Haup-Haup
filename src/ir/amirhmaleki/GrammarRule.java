package ir.amirhmaleki;

/**
 * Created by Amir on 1/20/2018 in package PACKAGE_NAME of project Haup-Haup.
 */
public class GrammarRule implements Cloneable {

    public static int ACCEPT = 1;
    public static int OTHER = 0;

    public static boolean isTerminal(String symbol) {
        if (symbol.contains("\""))
            return true;
        return false;
    }

    public String lhs;
    public int type;
    public String[] rhs;
    public int cursorIndex;

    public GrammarRule(String symbol, int type, String[] rhs){
        this.lhs = symbol;
        this.type = type;
        this.rhs = rhs;
        cursorIndex = -1;
    }

    public void setCursorIndex(int index){
        this.cursorIndex = index;
    }


    /**
     * * Returns dotted rule. If the index is -1, no dos would appear in the rule.
     * @return String
     */
    @Override
    public String toString() {
        if (cursorIndex < -1 || cursorIndex > rhs.length + 1)
            return "ERROR";
        StringBuilder result = new StringBuilder(lhs + " -> ");
        for (int i=0; i<rhs.length; i++){
            String sym = rhs[i];
            result.append((i == cursorIndex ? ". " : "") + sym).append(" ");
        }
        if (cursorIndex == rhs.length)
            result.append(" .");
        return result.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        GrammarRule toCompare = (GrammarRule) obj;
        boolean flag = true;
        if (this.rhs.length == toCompare.rhs.length)
            for (int i=0; i<rhs.length; i++) {
                flag &= this.rhs[i].equals(toCompare.rhs[i]);
            }
        else
            return false;
        flag &= this.lhs.equals(toCompare.lhs)
                && this.type == toCompare.type
                && this.cursorIndex == toCompare.cursorIndex ;
        return flag;
    }
}
