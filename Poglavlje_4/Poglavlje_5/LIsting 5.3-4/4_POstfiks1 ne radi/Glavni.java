//////////////////////////////////////////////////////////////////
///
///  Klasa Glavni
///  18.01.2022
///  Nenad Jovanovic, copyrght c 2022
///
//////////////////////////////////////////////////////////////////

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
    JEditorPane  ulazT ;
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

    Skener k;

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
		ulazT = new JEditorPane();
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
					//
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
					monitorT.setText("");
                    k = new Skener(ulazT.getText());
					String s = "";
					if(k!=null){
						Token t = k.skeniraj();
						s=s+"[";
						while(t!=null){
							s=s+t;
							t = k.skeniraj();
							if(t!=null)
							   s=s+", ";
						}
						s=s+"]";
						monitorT.setText(s);
					}
                }
		   }
           );
           ulazPaleta.add(start);
           start.setToolTipText("RUN");
        /////////////////////
           JButton tabela = new JButton("Tabela simbola");
           //start.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/start1.png")));
           tabela.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
					monitorT.setText(monitorT.getText() + "\n"+k.tabelaSimbola());
                }
		   }
           );
           ulazPaleta.add(tabela);
           tabela.setToolTipText("RUN");
        /////////////////////
           JButton post = new JButton("Postfiks");
           //start.setRolloverIcon(new ImageIcon(getClass().getResource("/slike/start1.png")));
           post.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e){
					   try {
						  monitorT.setText("");
						  /*
						  String str = ulazT.getText().replace("\n", "");
						  File file = new File("test.txt");
						  snimiFile(file, str);
				          Yylex lexer = new Yylex( new FileReader(file));
						  parser p = new parser(lexer);
                          */
		  Reader r = new StringReader("2*(4+3*(5+2));");
		  //Reader r = new StringReader(ulazT.getText().replace("\n", ""));
		  parser p = new parser(new Yylex(r));
		 			  p.parse();
					   } catch (Exception ee) {
							//e.printStackTrace();
					   }
					  System.out.println("Postfiks izraz = "+Izraz.izraz);
                      k = new Skener(Izraz.izraz);
					  String s = "";
					  String kod = "";
					  if(k!=null){
							Token t = k.skeniraj();
							if(t!=null && t.tip == Token.OPERATOR){
								if(t.leksema.equals("+")){
									kod = kod + "ADD\n"	;
								}else if(t.leksema.equals("-")){
									kod = kod + "SUB\n"	;
								} else if(t.leksema.equals("*")){
									kod = kod + "MUL\n"	;
								} else if(t.leksema.equals("/")){
									kod = kod + "DIV\n"	;
								}
							} else if(t!=null && t.tip == Token.NUMBER){
								kod = kod + "PUSH " + t.leksema + "\n";
							} else if(t!=null && t.tip == Token.IDENTIFIER){
								kod = kod + "PUSH " + t.leksema + "\n";
							}
						    s=s+"[";
						    while(t!=null){
								  t = k.skeniraj();
								  if(t!=null){
									  System.out.println("TOKEN = " + t.leksema);
										s=s+", ";
										if(t.tip == Token.OPERATOR){
											if(t.leksema.equals("+")){
												kod = kod + "ADD\n"	;
											}else if(t.leksema.equals("-")){
												kod = kod + "SUB\n"	;
											} else if(t.leksema.equals("*")){
												kod = kod + "MUL\n"	;
											} else if(t.leksema.equals("/")){
												kod = kod + "DIV\n"	;
											}
										} else if(t.tip == Token.NUMBER){
											kod = kod + "PUSH " + t.leksema + "\n";
										} else if(t.tip == Token.IDENTIFIER){
											kod = kod + "PUSH " + t.leksema + "\n";
										}
								  } //if
								  s=s+t;
							}// while
						}
						s=s+"]";
						monitorT.setText(s);
						monitorT.setText("Postfiks izraz = "+Izraz.izraz+"\n"+kod);

                }//actionPerformed
		   }
           );
           ulazPaleta.add(post);
           post.setToolTipText("POSTFIKS");
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