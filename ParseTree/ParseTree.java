
package ParseTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author flo
 */
public class ParseTree {
    public Token root;
    public ParseTree left;
    public ParseTree right;

    public ParseTree(){
        root = null;
        left = null;
        right = null;

    }
    
    public ParseTree(Token t, ParseTree l, ParseTree r){
        root  = t;
        left  = l;
        right = r;
    }
    
    public ParseTree setVariables(ParseTree tree){
        ParseTree temp = new ParseTree();
        Scanner s = new Scanner(System.in);
        if(tree == null){
            //System.out.println("Null tree, returning");
            return null;
        }
        if(tree.root.getPrec() == 5){
            System.out.println("Current node = "+tree.root.getElement() + "\nSet to what value?");
            String num = s.next();
            temp.root = new Token(num);
            //System.out.println("Element set to = " + num);
        }
        else{
            temp.root = tree.root;
        }
        temp.left = setVariables(tree.left);
        temp.right = setVariables(tree.right);
        return temp;
    }
    
//    public static int eval(ParseTree tree, int variable){
//        if(tree.root.getPrec() == 0){
//            //integer
//            //System.out.print("root is an integer" + tree.root.getElement());
//            int n = Integer.parseInt(tree.root.getElement());
//            //System.out.print("root is an integer" + n);
//            return n;
//        }
//        if(tree.root.getPrec() == 5){
//            //System.out.println("Variable detected, returning " + variable);
//            return variable;
//        }
//        if(tree.root.getElement().equals("*")){
//            return eval(tree.left, variable) * eval(tree.right, variable);
//        }
//        if(tree.root.getElement().equals("/")){
//            return eval(tree.left, variable) / eval(tree.right, variable);
//        }
//        if(tree.root.getElement().equals("+")){
//            return eval(tree.left, variable) + eval(tree.right, variable);
//        }
//        if(tree.root.getElement().equals("-")){
//            return eval(tree.left, variable) - eval(tree.right, variable);
//        }
//        if(tree.root.getElement().equals("^")){
//            return (int) Math.pow(eval(tree.left, variable), eval(tree.right, variable));
//        }
//    return 0;
//}
    
    public static int eval(ParseTree tree){
        if(tree.root.getPrec() == 0){
            //integer
            //System.out.print("root is an integer" + tree.root.getElement());
            int n = Integer.parseInt(tree.root.getElement());
            //System.out.print("root is an integer" + n);
            return n;
        }
        if(tree.root.getElement().equals("*")){
            return eval(tree.left) * eval(tree.right);
        }
        if(tree.root.getElement().equals("/")){
            return eval(tree.left) / eval(tree.right);
        }
        if(tree.root.getElement().equals("+")){
            return eval(tree.left) + eval(tree.right);
        }
        if(tree.root.getElement().equals("-")){
            return eval(tree.left) - eval(tree.right);
        }
        if(tree.root.getElement().equals("^")){
            return (int) Math.pow(eval(tree.left), eval(tree.right));
        }
        return 0;
    }
 
    
    public ParseTree parse(Token a[], int l, int r){
        ParseTree temp = new ParseTree();
        
        if(l == r){
            //System.out.println("l == r ... returning tree with only "+a[l]+" as the root");
            return new ParseTree(a[l], null, null);
        }
        
        int i = getIndex(a, l, r);

        if(i>=0){
            //no parenthesis
            //System.out.println("\ngot lowest prec op at last index = " + a[i]);
            temp.root = a[i];
            temp.left = parse(a, l, i-1);
            temp.right = parse(a, i+1, r);
            return temp;
        }
        else{
            //parenthesis
            return parse(a, l+1, r-1);
        }
    }
    
    //i go through the token array
    //pushing operators with the same precedence in an array list of Pairs
    //a Pair is a token and the index of where it is located in the token array
    public int getIndex(Token a[], int start, int end){
        ArrayList<Pair> prec1 = new ArrayList<>();
        ArrayList<Pair> prec2 = new ArrayList<>();
        ArrayList<Pair> prec3 = new ArrayList<>();
        int parenthesis = 0;
        
        for(int i=start;i<end;i++){
            if(a[i].getElement().equals("(")){
                parenthesis++;
            }
            
            if((a[i].getElement().equals("+") || a[i].getElement().equals("-")) && parenthesis == 0){
               prec1.add(new Pair(a[i], i));
               //pair is a token and index number
            }
            if ((a[i].getElement().equals("/") || a[i].getElement().equals("*")) && parenthesis == 0){
               prec2.add(new Pair(a[i], i));
            }
            if((a[i].getElement().equals("^")) && parenthesis == 0){
               prec3.add(new Pair(a[i], i));
            }   

            if(a[i].getElement().equals(")")){
                parenthesis--;
            }
        }
        
        if(!prec1.isEmpty()){
            Collections.sort(prec1);
            int last_index = prec1.get(prec1.size()-1).getIndex();
            //System.out.println("Prec1 op: "+prec1.get(prec1.size()-1));
            //System.out.println("Returning "+last_index);
            return last_index;
        }
        if(!prec2.isEmpty()){
            Collections.sort(prec2);
            int last_index = prec2.get(prec2.size()-1).getIndex();
            //System.out.println("Prec2 op: "+prec2.get(prec2.size()-1));
            //System.out.println("Returning "+last_index);
            return last_index;
        }
        if(!prec3.isEmpty()){
            Collections.sort(prec3);
            int last_index = prec3.get(prec3.size()-1).getIndex();
            //System.out.println("Prec3 op: "+prec3.get(prec3.size()-1));
            //System.out.println("Returning "+last_index);
            return last_index;
        }
        
        System.out.println("Parenthesis! returning -1");
        return -1;
       
    }
    
    public void print(int level){
        //System.out.println("\nPrinting");
        for(int i=0; i<level; i++){  
            System.out.print(" ");
        }
        
        if(root != null){
            System.out.print(root);
            System.out.println();
            if(left != null){
                left.print(level+4);
            }
            if(right != null){  
                right.print(level+4);
            }
        }

    }
}
