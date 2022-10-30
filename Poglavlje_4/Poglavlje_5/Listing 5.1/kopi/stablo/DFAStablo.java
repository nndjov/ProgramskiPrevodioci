
import java.awt.*;

////
import javax.swing.*;
import java.net.*;
////////

class DFAStablo {
	DFACvorr root;
	DFACvorr rr = null;
	public String redosled = "";
	public String putanja = "";

    public static int redBrojCvora = 0;

	DFAStablo(){
		root = null;
	}

	DFAStablo(DFACvorr r){
		root = new DFACvorr();
		root = r;
	}

	public DFACvorr getRoot(){
		return root;
	}

	public void createStablo(DFACvorr n, DFACvorr left, DFACvorr right){
		n.leftChild = left;
		n.rightChild = right;
	}

    public void addChild(DFACvorr r,DFACvorr c){
         DFACvorr next = r;
         int x =0;
         int y = 0;
         int nivo = 0;

         while (next != null){
			 if (Integer.parseInt(c.getName()) >= Integer.parseInt(next.getName())){
				 // ide desno
				 if (next.getRightChild() == null){
					 //smestiti novi cvor desno
					 c.setNivo(next.getNivo()+1);
					 nivo = c.getNivo();
					 y = next.getY() + 70;
                     int sirina = 20;
                     if (nivo == 0 )
                        sirina = 120;
                     if (nivo == 1 )
                        sirina = 100;
                     if (nivo == 2 )
                         sirina = 80;
                     if (nivo == 3 )
                        sirina = 60;
                     if (nivo == 4 )
                        sirina = 40;
                     if (nivo == 5 )
                        sirina = 20;

                     x = next.getX()+sirina;

                     c.setX(x);
                     c.setY(y);
                     next.setRightChild(c);
                     next = null;
				 } else {
					 next = next.getRightChild();
				 }
			 } else {
				 // ide levo
				 if (next.getLeftChild() == null){
					 //smestiti novi cvor levo
					 c.setNivo(next.getNivo()+1);
					 nivo = c.getNivo();
					 y = next.getY() + 70;
                     int sirina = 20;
                     if (nivo == 0 )
                        sirina = 120;
                     if (nivo == 1 )
                        sirina = 100;
                     if (nivo == 2 )
                         sirina = 80;
                     if (nivo == 3 )
                        sirina = 60;
                     if (nivo == 4 )
                        sirina = 40;
                     if (nivo == 5 )
                        sirina = 20;
                     x = next.getX()-sirina;
                     c.setX(x);
                     c.setY(y);
                     next.setLeftChild(c);
                     next = null;
				 } else {
					 next = next.getLeftChild();
				 }

			 }
		 }
	}

	public boolean isEmpty(){
		if (root == null)
		    return true;
		else
		    return false;
	}

	public DFACvorr findCvor(DFACvorr r, String n){
		if(r!= null){
			if ((r.getName()).equals(n)){
				rr = r;
			} else {
			   findCvor(r.getLeftChild(), n);
			   findCvor(r.getRightChild(), n);
			}
		}
        return rr;
	}

    public DFACvorr ispitatiCvoroveStabla(int x, int y, DFACvorr r){

		if(r!= null){
			if (r.proveriCvor(x,y)){
				rr = r;
			} else {
				ispitatiCvoroveStabla(x, y, r.getLeftChild());
				ispitatiCvoroveStabla(x, y, r.getRightChild());
			}
		}
		return rr;
	}

	public String pronadjiPutanju(String name){
          DFACvorr next = findCvor(root, name);

          String putanja = "";

          while (next != null){
			  next.setBoja(next.CRVENA);
			  putanja = putanja + next.getName()+". ";
			  next = next.getFather();

		  }
		  return putanja;

	}

	public void preorder(DFACvorr r){
		if(r!= null){
			   redosled = redosled + r.getName() +". ";
			   preorder(r.getLeftChild());
			   preorder(r.getRightChild());
		}
	}

	public void inorder(DFACvorr r){
		if(r!= null){

			   inorder(r.getLeftChild());
			   redosled = redosled + r.getName() +". ";
			   inorder(r.getRightChild());
		}
	}

	public void postorder(DFACvorr r){
		if(r!= null){

			   postorder(r.getLeftChild());
			   postorder(r.getRightChild());
			   redosled = redosled + r.getName() +". ";
		}
	}

	public void refresh(DFACvorr r){
		if(r!= null){
			   r.setBoja(r.PLAVA);
			   refresh(r.getLeftChild());
			   refresh(r.getRightChild());
		}
	}



	public void paintStablo(Graphics g, DFACvorr r){
		if(r!= null){
                //crtaj cvor
                DFACvorr left = r.getLeftChild();
                if (left != null){
					g.drawLine(r.getX()+10, r.getY()+10, left.getX()+10, left.getY()+10);
				}
                DFACvorr right = r.getRightChild();
                if (right != null){
					g.drawLine(r.getX()+10, r.getY()+10, right.getX()+10, right.getY()+10);
				}
				r.paintCvor(g);
				paintStablo(g, r.getLeftChild());
				paintStablo(g, r.getRightChild());
		}
	}
}


