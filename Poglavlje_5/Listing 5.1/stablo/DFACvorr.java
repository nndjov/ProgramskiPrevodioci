
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DFACvorr implements	ActionListener{
	public static final int CRNA = 0;
	public static final int BELA = 1;
	public static final int SIVA = 2;
	public static final int CRVENA = 3;
	public static final int PLAVA = 4;
    String name;
    String label;
    String prethodni;
    String sledeci;
    DFACvorr leftChild;
    DFACvorr rightChild;
    DFACvorr father;

    int nivo;
    int boja;
    // 0 - crna, 1- bela , 2 - siva , 3 - crvena

    int x;
    int y;

    public JPopupMenu popup;
    DFACvorr(){
		DFAStablo.redBrojCvora++;
		label ="";
		leftChild = null;
		rightChild = null;
		father = null;
		nivo = 0;
		x =0;
		y = 0;
		boja = PLAVA;
		popup = new JPopupMenu();
        ActionListener al1 = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (getLeftChild() == null){
				    DFACvorr novi = new DFACvorr();
				    int nivo = getNivo();
                    novi.setNivo(nivo+1);
                    int sirina = 15;
                    if (nivo == 0 )
                    sirina = 65;
                    if (nivo == 1 )
                    sirina = 45;
                    if (nivo == 2 )
                    sirina = 30;
                    if (nivo == 3 )
                        sirina = 20;

			        novi.setX(getX()-sirina);
			        novi.setY(getY()+70);

					setLeftChild(novi);
				} else {
				    JOptionPane.showMessageDialog(null,"Levi cvor vec postoji!!! ", "Info",JOptionPane.PLAIN_MESSAGE);
				}
            }
        };

        ActionListener al2 = new ActionListener() {
            public void actionPerformed(ActionEvent e){
				if (getRightChild() == null){
				    DFACvorr novi = new DFACvorr();
				    int nivo = getNivo();
                    novi.setNivo(nivo+1);
                    int sirina = 15;
                    if (nivo == 0 )
                        sirina = 65;
                    if (nivo == 1 )
                        sirina = 45;
                    if (nivo == 2 )
                        sirina = 30;
                    if (nivo == 3 )
                        sirina = 20;

			        novi.setX(getX()+sirina);
			        novi.setY(getY()+70);

				    setRightChild(novi);
				} else {
					JOptionPane.showMessageDialog(null,"Desni cvor vec postoji!!! ", "Info",JOptionPane.PLAIN_MESSAGE);
				}
            }
        };

        ActionListener al3 = new ActionListener() {
            public void actionPerformed(ActionEvent e){
				setLabel(JOptionPane.showInputDialog("Unesi oznaku cvora: " ));

            }
        };

        JMenuItem st1 = new JMenuItem("Add Left Child");
        st1.addActionListener(al1);
        popup.add(st1);
        JMenuItem st2 = new JMenuItem("Add Right Child");
        st2.addActionListener(al2);
        popup.add(st2);
        popup.addSeparator();

        JMenuItem st3 = new JMenuItem("Change label");
        st3.addActionListener(al3);
        popup.add(st3);

        st1.addActionListener( this );
        st2.addActionListener( this );
        st3.addActionListener( this );
	}

	public void actionPerformed( ActionEvent event ){
		// Add action handling code here
	}


	public void setName(String n){
		name = n;
	}
	public void setLabel(String l){
		label = l;
	}
	public void setPrethodni(String l){
		prethodni = l;
	}
	public void setSledeci(String l){
		sledeci = l;
	}
	public void setLeftChild(DFACvorr l){
		leftChild = l;
		l.setFather(this);
	}
	public void setRightChild(DFACvorr r){
		rightChild = r;
		r.setFather(this);
	}

	public void setFather(DFACvorr r){
		father = r;
	}

	public void setNivo(int x){
		this.nivo = x;
	}

	public void setBoja(int x){
		this.boja = x;
	}

	public int getNivo(){
		return nivo;
	}
	public String getPrethodni(){
		return prethodni;
	}
	public String getSledeci(){
		return sledeci;
	}
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

    public String getName(){
		return name;
	}

    public String getLabel(){
		return label;
	}

	public DFACvorr getLeftChild(){
		return leftChild;
	}

	public DFACvorr getRightChild(){
		return rightChild;
	}

	public DFACvorr getFather(){
		return father;
	}

	public void paintCvor(Graphics g){
        if (boja == SIVA)
            g.setColor(Color.gray);
        if (boja == CRVENA)
            g.setColor(Color.red);
        if (boja == PLAVA)
            g.setColor(Color.blue);
		g.fillOval(x,y, 20, 20);
		g.setColor(Color.white);
        Font f = new Font("Arial", Font.PLAIN,12);
		g.setFont(f);
		g.drawString(label, x+6, y+13);
		g.setColor(Color.black);
        f = new Font("Arial", Font.PLAIN,9);
		g.setFont(f);
		if(prethodni != null)
		   g.drawString(prethodni, x-prethodni.length()*5, y+10);
		if(sledeci != null)
		   g.drawString(sledeci, x+7+sledeci.length()*5, y+10);
	}

	public boolean proveriCvor(int x, int y){
		int kordX = getX();
		int kordY = getY();
		if ((kordX)<=x & (kordX+20)>=x & (kordY)<=y & (kordY+20)>=y ){
            return true;
        } else
            return false;

	}

	public void prikazi(MouseEvent e){
		popup.show(e.getComponent(), getX()+20, getY()+20);
	}
}