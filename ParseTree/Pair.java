
package ParseTree;

/**
 *
 * @author flo
 */
public class Pair implements Comparable<Pair>{
    private Token tok;
    private int index;
    
    public Pair(Token t, int in){
        tok = t;
        index = in;
    }
    
    public Token getToken(){
        return tok;
    }
    
    public int getIndex(){
        return index;
    }
    
    @Override
    public String toString(){
        return tok.getElement()+","+index;
    }
    
    @Override
    public int compareTo(Pair other) {
        if(tok.getPrec() < other.getToken().getPrec()){
            return -1;
        }
        else if(tok.getPrec() > other.getToken().getPrec()){
            return 1;
        }
        return 0;
    }
}
