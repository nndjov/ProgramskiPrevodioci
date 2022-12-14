
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class DFASintaksnoStablo extends JFrame implements MouseListener, MouseMotionListener{
	DFACvorr koren;
	DFAStablo stablo;
	int redBroj = 1;
	String IME = "";
	JScrollPane scrollPane;
	JLabel sledeceL;
	JTextArea sledeceT ;
	JPanel contentPane;

/*
	public DFASintaksnoStablo(){
		super("Syntax tree");
        formirajStablo();
	    addMouseMotionListener(this);
	    addMouseListener(this);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
*/
	public DFASintaksnoStablo(DFACvorr koren){
		super("Syntax tree");
		this.koren = koren;
        formirajStablo();
	    addMouseMotionListener(this);
	    addMouseListener(this);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void formirajStablo(){
		//int index = 0;
        //koren = new DFACvorr();
        if(koren!=null){
			koren.setNivo(0);
			koren.setX(350);
			koren.setY(77);
		}
		stablo = new DFAStablo(koren);
	}

	public void uredi(DFACvorr r){
		if(r!= null){
			   if(r.getLeftChild() != null){
				   DFACvorr cvor = r.getLeftChild();
				   cvor.setNivo(r.getNivo()+1);
			   }
			   if(r.getRightChild() != null){
				   DFACvorr cvor = r.getRightChild();
				   cvor.setNivo(r.getNivo()+1);
			   }
			   uredi(r.getLeftChild());
			   uredi(r.getRightChild());
		}
	}
	public void urediXY(DFACvorr r){
		if(r!= null){
			   if(r.getLeftChild() != null){
				   DFACvorr cvor = r.getLeftChild();
				   int x = r.getX();
				   int y = r.getY();
				   cvor.setX(x-70);
				   cvor.setY(y+70);
			   }
			   if(r.getRightChild() != null){
				   DFACvorr cvor = r.getRightChild();
				   int x = r.getX();
				   int y = r.getY();
				   cvor.setX(x+70);
				   cvor.setY(y+70);
			   }
			   urediXY(r.getLeftChild());
			   urediXY(r.getRightChild());
		}
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

    public void pokreni() {
		this.setSize(777, 555);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.show();
    }
/*
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
*/
}