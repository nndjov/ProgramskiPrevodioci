
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sat Jan 29 09:47:41 CET 2022
//----------------------------------------------------

import java.util.*;
import java.io.*;
import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sat Jan 29 09:47:41 CET 2022
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\017\000\002\002\004\000\002\002\004\000\002\002" +
    "\003\000\002\003\006\000\002\006\003\000\002\006\005" +
    "\000\002\007\003\000\002\007\005\000\002\010\003\000" +
    "\002\010\005\000\002\010\003\000\002\004\003\000\002" +
    "\004\003\000\002\005\003\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\004\015\005\001\002\000\006\002\uffff\015" +
    "\uffff\001\002\000\004\013\011\001\002\000\006\002\010" +
    "\015\005\001\002\000\006\002\001\015\001\001\002\000" +
    "\004\002\000\001\002\000\010\011\016\014\015\015\014" +
    "\001\002\000\016\004\ufffb\005\ufffb\006\ufffb\007\ufffb\010" +
    "\ufffb\012\ufffb\001\002\000\016\004\ufffd\005\ufffd\006\ufffd" +
    "\007\026\010\027\012\ufffd\001\002\000\016\004\ufff7\005" +
    "\ufff7\006\ufff7\007\ufff7\010\ufff7\012\ufff7\001\002\000\016" +
    "\004\ufff9\005\ufff9\006\ufff9\007\ufff9\010\ufff9\012\ufff9\001" +
    "\002\000\010\011\016\014\015\015\014\001\002\000\010" +
    "\004\023\005\022\006\021\001\002\000\010\011\016\014" +
    "\015\015\014\001\002\000\010\011\ufff5\014\ufff5\015\ufff5" +
    "\001\002\000\010\011\ufff6\014\ufff6\015\ufff6\001\002\000" +
    "\006\002\ufffe\015\ufffe\001\002\000\016\004\ufffc\005\ufffc" +
    "\006\ufffc\007\026\010\027\012\ufffc\001\002\000\010\011" +
    "\016\014\015\015\014\001\002\000\010\011\ufff4\014\ufff4" +
    "\015\ufff4\001\002\000\010\011\ufff3\014\ufff3\015\ufff3\001" +
    "\002\000\016\004\ufffa\005\ufffa\006\ufffa\007\ufffa\010\ufffa" +
    "\012\ufffa\001\002\000\010\005\022\006\021\012\032\001" +
    "\002\000\016\004\ufff8\005\ufff8\006\ufff8\007\ufff8\010\ufff8" +
    "\012\ufff8\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\030\000\006\002\005\003\003\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\003\006\001\001\000\002" +
    "\001\001\000\002\001\001\000\010\006\016\007\012\010" +
    "\011\001\001\000\002\001\001\000\004\005\024\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\006\030\007" +
    "\012\010\011\001\001\000\004\004\017\001\001\000\006" +
    "\007\023\010\011\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\005\024\001\001\000\004" +
    "\010\027\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\004\017\001\001\000\002\001\001" +
    "" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {


	TabelaSimbola tabela = new TabelaSimbola();
	DFASintaksnoStablo stablo;
	
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // mul_op ::= DIV 
            {
              String RESULT =null;
		 RESULT="div"; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("mul_op",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // mul_op ::= MUL 
            {
              String RESULT =null;
		 RESULT="mul"; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("mul_op",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // add_op ::= SUB 
            {
              String RESULT =null;
		 RESULT="sub"; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("add_op",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // add_op ::= ADD 
            {
              String RESULT =null;
		 RESULT="add"; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("add_op",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // factor ::= IDENTIFAER 
            {
              DFACvorr RESULT =null;
		int idenleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int idenright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String iden = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
           DFACvorr c = new DFACvorr();
           c.setLabel(""+iden);                    
           RESULT = c;
           
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // factor ::= LPAREN expr RPAREN 
            {
              DFACvorr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		DFACvorr e = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 
           RESULT = e;
           
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // factor ::= NUMBER 
            {
              DFACvorr RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Integer n = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 
           DFACvorr c = new DFACvorr();
           c.setLabel(""+n);
           RESULT = c; 
           
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // term ::= term mul_op factor 
            {
              DFACvorr RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		DFACvorr t = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int opright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String op = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int fleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		DFACvorr f = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 
	DFACvorr c = new DFACvorr();
	if(op.equals("mul")){
		c.setLabel("*");
	}else{
		c.setLabel("/");
	}
	DFACvorr c1 = t; 
	DFACvorr c2 = f; 
	c.setLeftChild(c1);
	c.setRightChild(c2);
	RESULT = c;		
	
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // term ::= factor 
            {
              DFACvorr RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		DFACvorr f = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
        RESULT = f; 
        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // expr ::= expr add_op term 
            {
              DFACvorr RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		DFACvorr e = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int opright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String op = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		DFACvorr t = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 
	DFACvorr c = new DFACvorr();
	if(op.equals("add")){
		c.setLabel("+");
	}else{
		c.setLabel("-");
	}
	
	DFACvorr c1 = e; 
	DFACvorr c2 = t; 
	c.setLeftChild(c1);
	c.setRightChild(c2);
	RESULT = c;
	
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // expr ::= term 
            {
              DFACvorr RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		DFACvorr t = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 
        RESULT = t;      
        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // expr_part ::= IDENTIFAER ASSIG expr SEMI 
            {
              Object RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		DFACvorr e = (DFACvorr)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 
	      RESULT = e;
	      DFACvorr c = e;
	      DFACvorr assig = new DFACvorr();
	      assig.setLabel(":=");
	      DFACvorr iden = new DFACvorr();
	      iden.setLabel(id);
	      assig.setLeftChild(iden);
	      assig.setRightChild(c);	      
	      stablo = new DFASintaksnoStablo(assig);
              stablo.uredi(assig);
              stablo.urediXY(assig);
	      stablo.pokreni();	      
	      TabelaSimbolaStavka stavka = new TabelaSimbolaStavka(id, "IDENTIFIER", ""+e);
	      tabela.dodaj(stavka);	
	      
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr_part",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // expr_list ::= expr_part 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr_list",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= expr_list EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // expr_list ::= expr_list expr_part 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr_list",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

