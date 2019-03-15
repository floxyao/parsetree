
package ParseTree;

/**
 *
 * @author flo
 */
public class Token{
    private int prec;
    private String element;
    
    
    public Token(){
        element = null;
        prec = 0;
    }
    
    public Token(String elt){
        //if f is an operator, assign precedence
        element = elt;
        prec();
    }
    
    public int getPrec(){
        return prec;
    }
    
    public String getElement(){
        return element;
    }
    
    private void prec(){
        switch(element){
            case "+":
                prec = 1;
                break;
            case "-":
                prec = 1;
                break;
            case "/":
                prec = 2;
                break;
            case "*":
                prec = 2;
                break;
            case "^":
                prec = 3;
                break;
            case "(":
                prec = 4;
                break;
            case ")":
                prec = 4;
                break;
            case "y":
                prec = 5;
                break;
            case "x":
                prec = 5;
                break;
            case "z":
                prec = 5;
                break;
            default:
                prec = 0;
                break;  
        }
    }
    
//    @Override
//    public int compareTo(Token other) {
//        if(prec > other.getPrec()){
//            return -1;
//        }
//        else if(prec < other.getPrec()){
//            return 1;
//        }
//        return 0;
//    
//    }
    
    public void setElement(String in){
        element = in;
    }
    
    @Override
    public String toString(){
        return element;
    }
}
