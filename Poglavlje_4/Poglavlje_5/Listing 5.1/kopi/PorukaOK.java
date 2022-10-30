import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.applet.*;

class PorukaOK extends Frame implements  ActionListener {
      Label l;
      int duzina = 0;
      PorukaOK(){
           super("Poruka");
           setBackground(Color.blue);
           addWindowListener(new UpravljacDogadjajima());
      }

      public void prikazPoruke(String poruka) {
           setLayout(null);
           duzina = 7*poruka.length();
           if (duzina < 250)
                duzina = 250;
           l=new Label("", Label.CENTER);
           l.setText(poruka);
           l.setFont(new Font("Helvetica", Font.BOLD,12));
           add("Center", l);
           l.setForeground(Color.white);
           l.reshape(10, 60, duzina, 15);

           Button ok = new Button("OK");
           add(ok);
           ok.addActionListener(this);
           ok.reshape(duzina/2-35, 100, 77, 25);

           resize(duzina+20, 150);
           show();

       }

/*   public Insets getInsets() {
      return new Insets(20,0,25,0);
   }
*/
   class UpravljacDogadjajima extends WindowAdapter{
         public void windowClosing(WindowEvent e){
               hide();
         }
   }

      public void actionPerformed(ActionEvent e){
             String str = e.getActionCommand();
             if (str.equals("OK")){
                    hide();
             }
      }
///
}