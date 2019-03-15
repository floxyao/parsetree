
package ParseTree;
import java.util.Scanner;

public class Demo {
    public static void main(String arg[]){
        char key = 'a';
        Scanner in = new Scanner(System.in);
        
        do{
            ParseTree p = new ParseTree();
            //(3*x^2-6*x+(4+y)*(3+y))
        
            System.out.println("\nEnter an expression: ");
            String str = in.next();
            String regex = "(?<=op)|(?=op)".replace("op", "[-+*/()^]");
            String [] split = str.split(regex);

            int size = split.length;
            Token[] token_list = new Token[size];

            for(int i=0; i<size; i++){
                String temp = split[i];
                //System.out.println("split token is "+temp);
                token_list[i] = new Token(temp);
            }

            System.out.println();
            p = p.parse(token_list, 0, size-1);
            p.print(0);
            
            ParseTree f = new ParseTree();
            f = p.setVariables(p);

            System.out.println("\n = " + ParseTree.eval(f));     

            System.out.println("\nEnter 'y' to try again\n");
            key = in.next().charAt(0);
            
        }while(key == 'y');
    }
}


