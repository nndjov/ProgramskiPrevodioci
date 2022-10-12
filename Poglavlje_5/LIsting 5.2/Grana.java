import java.awt.*;
import java.awt.Color;
import java.util.Vector;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.*;

//class Grana extends Entitet {
class Grana extends Entitet {

	String znak;
	private boolean povratna;
	int brojPovratnih;

    int  x1=-7;
    int  y1=-7;
    int  x2=-7;
    int  y2=-7;

    int  x3=0;
    int  y3=0;
    int  x4=0;
    int  y4=0;

    int  x5=0;
    int  y5=0;

	int xpom;
	int ypom;

    // grana ide x1,y1  --- x3,y3 ---- x4,y4------x2,y2

    int prviCvor, drugiCvor;
    DFACvor prethodni, sledeci;
    Thread nit;

    public Grana(){
         super();
         tip = "veza";
         povratna = false;
         prviCvor = -1;
         drugiCvor = -1;
         brojPovratnih = 1;
    }

    public void setPovratna(boolean p){
		povratna = p;
	}

    public void crtaj(Graphics g){
		g.setColor(Color.blue);
			if(x3==0 & y3==0 & x4==0 & y4==0){
				if(povratna){
					x1 = x1 + 20;
					y1 = y1 - 10;
					x2 = x2-20;
					y2 = y2 -10;
					x3 = x1 +35;
					y3 = y1 - 30;
					x4 = x1 - 35;
					y4 = y1 - 30;
					x5 = x1;
					y5 = y1 - 40;
				} else {
					int xpom = (x1+x2)/2;
					int ypom = (y1+y2)/2;
					x3 = (x1 + xpom)/2;
					y3 = (y1 + ypom)/2;
					x4 = (x2 + xpom)/2;
					y4 = (y2 + ypom)/2;
				}

			}

			int x1Staro = x1;
			int y1Staro = y1;
			int x2Staro = x2;
			int y2Staro = y2;

			x1 = Integer.parseInt(prethodni.nadjiVrednostSvojstva( "kordX"))+25;
			y1 = Integer.parseInt(prethodni.nadjiVrednostSvojstva( "kordY"))+25;
			x2 = Integer.parseInt(sledeci.nadjiVrednostSvojstva( "kordX"))+25;
			y2 = Integer.parseInt(sledeci.nadjiVrednostSvojstva( "kordY"))+25;

			if(x1 != x1Staro | y1 != y1Staro | x2 != x2Staro | y2 != y2Staro){
				if(povratna){
					x3 = x1 + 35;
					y3 = y1 - 30;
					x4 = x1 - 35;
					y4 = y1 - 30;
					x5 = x1;
					y5 = y1 - 40;
				} else {
					xpom = (x1+x2)/2;
					ypom = (y1+y2)/2;
					x3 = (x1 + xpom)/2;
					y3 = (y1 + ypom)/2;
					x4 = (x2 + xpom)/2;
					y4 = (y2 + ypom)/2;
				}
			} else{
					x5 = x3 - 35;
					y5 = y3 - 10;
			}

			Font f = new Font("Arial", Font.BOLD,17);
			g.setFont(f);

			if (povratna){
				g.drawLine(x1, y1, x3, y3);
				g.drawLine(x3, y3, x5, y5);
				g.drawLine(x5, y5, x4, y4);
				g.drawLine(x4, y4, x2, y2);
			} else{
				g.drawLine(x1, y1, x3, y3);
				g.drawLine(x3, y3, x4, y4);
				g.drawLine(x4, y4, x2, y2);
			}
			int x11 = (x3+x4)/2;
			int y11 = (y3+y4)/2;
			g.drawString(znak, x11, y11);
			crtajStrelicu((Graphics2D)g, x4, y4, (x4+x2)/2, (y4+y2)/2);

			g.setColor(Color.red);
			g.setColor(Color.blue);
    }

    public void dodajZnak(){
		znak = JOptionPane.showInputDialog(null, "ULAZ:");
	}

    public void setZnak(String znak){
		this.znak = znak;
	}

	public boolean proveriVezu(int x, int y){
        if ((x3-5)<=x & (x3+5)>=x & (y3-5)<=y & (y3+5)>=y ){
            return true;
        } else if ((x4-5)<=x & (x4+5)>=x & (y4-5)<=y & (y4+5)>=y ){
            return true;
        } else
            return false;
	}

	public String proveriTackeVeze(int x, int y){
		if ((x3-5)<=x & (x3+5)>=x & (y3-5)<=y & (y3+5)>=y ){
            return "X3";
        } else if ((x4-5)<=x & (x4+5)>=x & (y4-5)<=y & (y4+5)>=y ){
            return "X4";
        } else
            return "";
	}
	 public void crtajStrelicu(Graphics2D g2d, int xCenter, int yCenter, int x, int y) {
		  double aDir=Math.atan2(xCenter-x,yCenter-y);
		  float stroke = (float)1;
		  g2d.drawLine(x,y,xCenter,yCenter);
		  int x1 = (x+xCenter)/2;
		  int y1 = (y + yCenter)/2;
		  x1 = (x1+x)/2;
		  y1 = (y1 + y)/2;

		  x = (x1+x)/2;
		  y = (y1 + y)/2;
		  g2d.setStroke(new BasicStroke(1f));
		  Polygon tmpPoly=new Polygon();
		  int i1=12+(int)(stroke*2);
		  int i2=6+(int)stroke;
		  tmpPoly.addPoint(x,y);
		  tmpPoly.addPoint(x+xCor(i1,aDir+.5),y+yCor(i1,aDir+.5));
		  tmpPoly.addPoint(x+xCor(i2,aDir),y+yCor(i2,aDir));
		  tmpPoly.addPoint(x+xCor(i1,aDir-.5),y+yCor(i1,aDir-.5));
		  tmpPoly.addPoint(x,y);
		  g2d.drawPolygon(tmpPoly);
		  g2d.fillPolygon(tmpPoly);

	   }
	   private static int yCor(int len, double dir) {return (int)(len * Math.cos(dir));}
	   private static int xCor(int len, double dir) {return (int)(len * Math.sin(dir));}

}
