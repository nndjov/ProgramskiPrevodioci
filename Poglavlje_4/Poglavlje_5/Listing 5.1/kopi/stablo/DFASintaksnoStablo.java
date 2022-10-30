
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class DFASintaksnoStablo extends JFrame implements MouseListener, MouseMotionListener{
	//String regex;
   // DFASyntaxTree st;
    //DFANode root; //
	//int index = 0;
	DFACvorr koren;
	DFAStablo stablo;
	int redBroj = 1;
	String IME = "";
	JScrollPane scrollPane;

	JLabel sledeceL;
	JTextArea sledeceT ;
	JPanel contentPane;

	public DFASintaksnoStablo(){
		super("Syntax tree");
        formirajStablo();
	    addMouseMotionListener(this);
	    addMouseListener(this);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void formirajStablo(){
		//
		int index = 0;
        koren = new DFACvorr();
        koren.setNivo(0);
        koren.setX(350);
        koren.setY(77);
		//koren.setName(root.getName());
		//koren.setLabel(root.getSymbol());
		//koren.setPrethodni(""+root.getFirstPos());
		//koren.setSledeci(""+root.getLastPos());
		stablo = new DFAStablo(koren);
////////////


			   DFACvorr c1 = new DFACvorr();
			   c1.setName("1");
			   c1.setLabel("S");
        	   c1.setX(koren.getX()-60);
               c1.setY(koren.getY()+65);
			   koren.setLeftChild(c1);
			   index++;
			   //listaCvor[index] = c;
			   redBroj++;

			   DFACvorr c2 = new DFACvorr();
			   c2.setName("2");
			   c2.setLabel("B");
        	   c2.setX(koren.getX()+60);
               c2.setY(koren.getY()+65);
			   koren.setRightChild(c2);
			   index++;
			   redBroj++;

			   DFACvorr c3 = new DFACvorr();
			   c3.setName("3");
			   c3.setLabel("c");
        	   c3.setX(koren.getX()+80);
               c3.setY(koren.getY()+65);
			   c2.setRightChild(c3);
			   index++;
			   redBroj++;
	}

	public void dodajCvor(DFACvorr koren,String ime, String label, boolean levi){
		DFACvorr c = new DFACvorr();
		c.setName(ime);
		c.setLabel(label);
	    c.setX(koren.getX()+60);
	    c.setY(koren.getY()+65);
	    if(levi){
		   koren.setLeftChild(c);
	    }else{
		   koren.setRightChild(c);
	    }
	    //index++;
	    redBroj++;
	}

    public void paint(Graphics g){
		super.paint(g);
		if(koren != null)
		    stablo.paintStablo(g, koren);

    }

    public void prikazi() {
          resize(900,550);
          setVisible(true);
    }

    public void mouseDragged(MouseEvent e) {
        e.consume();
               if (koren != null){
				   // ispitati da li se na lokaciji e.getX(), e.getY() nalazi cvor
				   DFACvorr cvor = stablo.ispitatiCvoroveStabla(e.getX(), e.getY(), koren);
				   if (cvor != null){
					   cvor.setX(e.getX());
					   cvor.setY(e.getY());
				   }
			   }
			   repaint();
    }
   public void mousePressed(MouseEvent e) {
        e.consume();
        DFACvorr cvor;
			// Pronaci cvor na kome je kliknuto misem
               if (koren != null){
				   // ispitati da li se na lokaciji e.getX(), e.getY() nalazi cvor
				   cvor = stablo.ispitatiCvoroveStabla(e.getX(), e.getY(), koren);
				   if (cvor != null)
				       IME = cvor.getName();
			   }


    }

    public void mouseMoved(MouseEvent e) {
	   e.consume();
    }

    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
         repaint();
    }
    ////////

    public static void main (String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				DFASintaksnoStablo f = new DFASintaksnoStablo();
				f.setSize(777, 555);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.show();
            }
        });



    }

}