//////////////////////////////////////////////////////////////////
///
///  Klasa Glavni
///  18.01.2022
///  Nenad Jovanovic, copyrght c 2022
///
//////////////////////////////////////////////////////////////////
import java_cup.runtime.Symbol;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;

import java.util.Scanner;
import java.util.Stack;
import java.util.*;

import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Glavni extends JPanel{

    JToolBar monitor;
    JPanel panel;
    JToolBar  ulaz;
    JToolBar  specifikacija;
    JToolBar  gramatika;
    JTextArea  ulazT ;
    JEditorPane  specifikacijaT ;
    JEditorPane  monitorT ;
    JEditorPane  gramatikaT;

    JToolBar  ulazPaleta;
    JToolBar  gramatikaPaleta;
    JToolBar  specifikacijaPaleta;

    JScrollPane skrol1, skrol2, skrol3;

    Font f, fl;

    File selectedFile;
    File specFile;
    File gramFile;
    File ulazFile;

    static String REZULTAT = "";
    static String REZULTAT_;

	Glavni(){
		super();
        setSize(1201, 700);
        f = new Font("Courier", Font.PLAIN,19);

		monitor = new JToolBar();
		monitorT = new JEditorPane();
		monitorT.setFont(f);
		monitor.setLayout(new BorderLayout());
		monitor.setPreferredSize(new Dimension(250, 200));
		panel = new JPanel();

		ulaz = new JToolBar();
		//ulazT = new JEditorPane();
		ulazT = new JTextArea();
		//ulazT.setLineWrap(false);
		skrol3 = new JScrollPane(ulazT) ;
		ulazT.setFont(f);
		ulaz.setLayout(new BorderLayout());
		ulazPaleta =  new JToolBar();
        //////////////////////
           JButton ulazFileB = new JButton(new ImageIcon(getClass().getResource("/slike/open.png")));
           ulazFileB.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/open1.png")));
           ulazFileB.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
					try {
						//
						ulazT.setText(procitajDatoteku(otvoriDatoteku().toString()));
					} catch (Exception ex) {
						System.err.println("Greska: " + ex.getMessage());
					}
                }
		   }
           );
           ulazPaleta.add(ulazFileB);
           //ulazFileB.setToolTipText("");
        /////////////////////
           JButton saveulaz = new JButton(new ImageIcon(getClass().getResource("/slike/save.png")));
           saveulaz.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/save1.png")));
           saveulaz.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
				  try{
					 File f = pisiFile();
					 snimiFile(f, ulazT.getText());
				  }  catch (Exception e1) {System.out.println(e1.getMessage());}
                }
		   }
           );
           ulazPaleta.add(saveulaz);
           saveulaz.setToolTipText("Save");
        //////////////////////
           JButton brisi = new JButton(new ImageIcon(getClass().getResource("/slike/delete.png")));
           brisi.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/delete1.png")));
           brisi.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    ulazT.setText("");
                    monitorT.setText("");
                    //k.tabelaSimbola = new LinkedList<>();
                }
		   }
           );
           ulazPaleta.add(brisi);
           //ulazPaleta.setToolTipText("Delete");
        /////////////////////
        JButton start = new JButton("Skener");
           //JButton start = new JButton(new ImageIcon(getClass().getResource("/slike/start.png")));
           //start.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/start1.png")));
           start.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
					//String ulaz = ulazT.getText();
					//Reader r = new StringReader(ulaz);
					//Reader r = new StringReader("2+3;");
	 				// InputStreamReader in = new InputStreamReader("PrimeriUlaza/ulaz.txt");
      				//BufferedReader r = new BufferedReader(in);
					try {
						REZULTAT="";
						String str = ulazT.getText().replace("\n", "");
						//String str = ulazT.getText();
						snimiFile(new File("test.txt"), str);
						File file = new File("test.txt");
						BufferedReader r = new BufferedReader(new FileReader(file));
						Yylex lexer = new Yylex(r);
						Symbol token = lexer.next_token();
						while(token.sym != sym.EOF) {
							System.out.println("token.sym="+sym.vratiToken(token.sym));
							REZULTAT = REZULTAT + " " + sym.vratiToken(token.sym);
							monitorT.setText(REZULTAT);
							token = lexer.next_token();
						}
					} catch (Exception ee) {
						System.err.println("Greska:" + ee.getMessage());
					}
					monitorT.setText(REZULTAT);
                }
		   }
           );
           ulazPaleta.add(start);
           start.setToolTipText("SKENER");

        /////////////////////

        fl = new Font("Arial", Font.PLAIN,19);
		ulaz.add(skrol3, BorderLayout.CENTER);
		ulaz.add(ulazPaleta, BorderLayout.SOUTH);
		JLabel l1 = new JLabel("------ INPUT ---------");
		l1.setFont(fl);
        l1.setOpaque(true);
        l1.setForeground(Color.blue);
        l1.setBackground(Color.lightGray);
		ulaz.add(l1, BorderLayout.NORTH);

		ulazT.setText(procitajDatoteku("test.txt"));
		ulazFile = new File("test.txt");


////////////////////////////////////////////////////
//SPECIFIKACIJA GRAMATIKE
////////////////////////////////////////////////////

		gramatika = new JToolBar();
		gramatikaT = new JEditorPane();
		skrol2 = new JScrollPane(gramatikaT) ;
		gramatikaT.setFont(f);
		gramatika.setLayout(new BorderLayout());
		gramatikaPaleta =  new JToolBar();

        //////////////////////
           JButton openGramFile = new JButton(new ImageIcon(getClass().getResource("/slike/open.png")));
           openGramFile.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/open1.png")));
           openGramFile.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){

					try {
						//otvoriGramFile();
						gramFile = otvoriDatoteku();
						gramatikaT.setText(procitajDatoteku(gramFile.toString()));

					} catch (Exception ex) {
						System.err.println("Greska: " + ex.getMessage());
					}
                }
		   }
           );
           gramatikaPaleta.add(openGramFile);
           openGramFile.setToolTipText("Open file");
        /////////////////////
        /////////////////////
           JButton savegram = new JButton(new ImageIcon(getClass().getResource("/slike/save.png")));
           savegram.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/save1.png")));
           savegram.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){

				  try{
					 File f = pisiFile();
					 snimiFile(f, gramatikaT.getText());
					 gramFile = f;
				  }  catch (Exception e1) {System.out.println(e1.getMessage());}

                }
		   }
           );
           gramatikaPaleta.add(savegram);
           savegram.setToolTipText("Save");
        //////////////////////
           JButton brisiGramatiku = new JButton(new ImageIcon(getClass().getResource("/slike/delete.png")));
           brisiGramatiku.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/delete1.png")));
           brisiGramatiku.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    specifikacijaT.setText("");
                }
		   }
           );
           gramatikaPaleta.add(brisiGramatiku);
           //ulazPaleta.setToolTipText("Izracunava FIRST funkciju");
        /////////////////////

        //skrol1.add(specifikacijaT);
		gramatika.add(skrol2, BorderLayout.CENTER);
		gramatika.add(gramatikaPaleta, BorderLayout.SOUTH);
		JLabel l3 = new JLabel("---- GRAMER SPECIFICATION -----");
		l3.setFont(fl);
        l3.setOpaque(true);
        l3.setForeground(Color.blue);
        l3.setBackground(Color.lightGray);
		gramatika.add(l3, BorderLayout.NORTH);
		//gramatikaT.setText(procitajDatoteku("default/gram.txt"));
		//gramFile = new File("default/gram.txt");

//////////////////////////////////////////////////////
//MONITOR
/////////////////////////////////////////////////////

        JScrollPane skrol4 = new JScrollPane(monitorT);
        skrol4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//skrol2.setPreferredSize(new Dimension(250, 200));

		monitor.add(skrol4, BorderLayout.CENTER);
		JLabel l4 = new JLabel("---- RESULT -----");
		l4.setFont(fl);
        l4.setOpaque(true);
        l4.setForeground(Color.blue);
        l4.setBackground(Color.lightGray);
		monitor.add(l4, BorderLayout.NORTH);

///////////////////////////////////////////////////

//////////////////////////////////////////////////

		panel.setLayout(new GridLayout(1,3));
		panel.add(ulaz);
//		panel.add(specifikacija);
//		panel.add(gramatika);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		add(monitor, BorderLayout.SOUTH);

	}
/*
	public String obradi(int token){
		switch(token){
			case sym.ADD: return "PLUS";
			case sym.MUL: return "PUTA";
			case sym.NUMBER: return "Broj";
			case sym.SEMI: return "TACKA_ZAREZ";
			case sym.LPAREN: return "OTVORENA_ZAGRADA";
			case sym.RPAREN: return "ZATVORENA_ZAGRADA";
		}
		return null;
	}
*/
	public File otvoriDatoteku()throws Exception{
		String ul;

		JFrame f = new JFrame("");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Gramar Documents", "txt");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(f);
		//target.imeFajla = fileChooser.getSelectedFile().getName();

		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
		}
		return selectedFile;
	}

	public String procitajDatoteku(String file){
		String ul = "";
		String tekst = "";
		//selectedFile = new File(file);
        try {
            FileReader fileReader =  new FileReader(file);
            BufferedReader ulaz = new BufferedReader(fileReader);

            ul =  ulaz.readLine();

            while(ul != null) {
				tekst = tekst + ul + "\n";
				ul =  ulaz.readLine();
            }
            ulaz.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + file + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file + "'");
        }

        return tekst;
	}

      public File pisiFile() throws Exception {
		  File fajl = null;

     		 JFrame f = new JFrame("");
			 JFileChooser fileChooser = new JFileChooser();
			 fileChooser.setDialogTitle("Save");
			 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			 FileNameExtensionFilter filter = new FileNameExtensionFilter("Documents", "txt");
			 fileChooser.addChoosableFileFilter(filter);
			 fileChooser.setFileFilter(filter);

			 int userSelection = fileChooser.showSaveDialog(f);
			 if (userSelection == JFileChooser.APPROVE_OPTION) {
				 fajl = fileChooser.getSelectedFile();
			}
			return fajl;
      }

      public void snimiFile (File fajl, String s)throws Exception{
		  FileWriter u = new FileWriter(fajl);
		  BufferedWriter b = new BufferedWriter(u);
		  PrintWriter ulaz = new PrintWriter(b,true);

          ulaz.println(s);
          ulaz.close();
	  }

	public static void main(String[] args) {
		JFrame p = new JFrame("Scanner&Parser");
	    Glavni  g = new Glavni();
	    p.add(g);
	    p.setSize(777, 557);
	    p.setVisible(true);
	}
// klraj klse
}


class ColorColumnRenderer extends DefaultTableCellRenderer{
   Color bkgndColor, fgndColor;
   public ColorColumnRenderer(Color bkgnd, Color foregnd) {
      super();
      bkgndColor = bkgnd;
      fgndColor = foregnd;
   }
   public Component getTableCellRendererComponent
	    (JTable table, Object value, boolean isSelected,
	     boolean hasFocus, int row, int column)
   {
      Component cell = super.getTableCellRendererComponent
         (table, value, isSelected, hasFocus, row, column);

      cell.setBackground( bkgndColor );
      cell.setForeground( fgndColor );

      return cell;
   }
}