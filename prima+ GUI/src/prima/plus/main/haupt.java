package prima.plus.main;


import java.io.File;
import java.io.IOException;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import prima.plus.verb.compile.verb;

public class haupt
{
  static String eingabe = "";
  static byte konjobest = 0;
  static byte zspeicher = 0;
  static String zspeicher1 = "";
  static boolean continu = true;
  static String compile = "false";
  
  static filereader freder = new filereader();
  static filereader config = new filereader();

  static File asdf = new File("");//user dir
  static String dir = asdf.getAbsolutePath();
  static byte n = 67;//lines
  
  static String[] irregulareverben = {"esse", "ire", "ferre", "velle"};
  
  static JButton compilebutton = new JButton("OK");
  static JButton close = new JButton("Schließen");
  static JTextField grundform1 = new JTextField("Grundform1");
  static JTextField grundform2 = new JTextField("Grundform2");
  static JTextField grundform3 = new JTextField("Grundform3");
  static JTextField grundform4 = new JTextField("Grundform4");
  static JRadioButton ind = new JRadioButton("Indikativ");
  static JRadioButton kon = new JRadioButton("Konjungtiv");
  static JRadioButton akt = new JRadioButton("Aktiv");
  static JRadioButton pas = new JRadioButton("Passiv");
  static JRadioButton sg = new JRadioButton("Singular");
  static JRadioButton pl = new JRadioButton("Plural");
  static JRadioButton p1 = new JRadioButton("1.Person");
  static JRadioButton p2 = new JRadioButton("2.Person");
  static JRadioButton p3 = new JRadioButton("3.Person");
  static JTextArea ausgabe = new JTextArea("Willkommen im Prima+ deklinierer von Yannis!");  
  static String[] tensnames = { "Präsens", "Perfekt", "Plusquamperfekt", "Imperfekt", "Futur" , "Imperativ"};
  static JComboBox<String> zeiten = new JComboBox<String>(tensnames);
  
public static void main(String[] args) throws IOException
  {	  	
	
    @SuppressWarnings("unused")
	String[] text = gettransdata(n);//filereader
	//mainscript helper = new mainscript();
	
	JFrame frame = new JFrame("Prima+");//frame
	frame.setBounds(0, 0, 800, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setTitle("Prima+");
	frame.setResizable(false);
	
	compilebutton.setBounds(500, 360, 80, 20);//buttons
	compilebutton.addActionListener(new mainscript());
	compilebutton.setActionCommand("compile");
	close.addActionListener(new mainscript());
	close.setActionCommand("close");
	grundform1.addActionListener(new mainscript());
	grundform1.setActionCommand("grundform1");
	
	JPanel okp = new JPanel();
	okp.setBounds(450, 300,150,100);//okp
	okp.setLayout(new GridLayout(0,2));
	okp.add(compilebutton,BorderLayout.WEST);
	okp.add(close, BorderLayout.EAST);
		
	JPanel grundformen = new JPanel();
	grundformen.setBounds(0,0,75, 200);//grundformen
	grundformen.setLayout(new GridLayout(4,1));
	grundformen.add(grundform1);
	grundformen.add(grundform2);
	grundformen.add(grundform3);
	grundformen.add(grundform4);
	
	JPanel options = new JPanel(new GridLayout(3,0,0,0));//options
	//options.setBounds(400,300,75,200);
	options.add(ind);
	options.add(kon);
	options.add(akt);
	options.add(pas);
	options.add(sg);
	options.add(pl);
	options.add(p1);
	options.add(p2);
	options.add(p3);
	ButtonGroup indoderkon = new ButtonGroup();
	ind.setSelected(true);
	indoderkon.add(ind);
	indoderkon.add(kon);
	ButtonGroup aktoderpas = new ButtonGroup();
	akt.setSelected(true);
	aktoderpas.add(akt);
	aktoderpas.add(pas);
	ButtonGroup sgoderpl = new ButtonGroup();
	sg.setSelected(true);
	sgoderpl.add(sg);
	sgoderpl.add(pl);
	ButtonGroup pers = new ButtonGroup();
	p1.setSelected(true);
	pers.add(p1);
	pers.add(p2);
	pers.add(p3);
	
	
	zeiten.setSelectedIndex(0);
	zeiten.addActionListener(new mainscript());
	zeiten.setSize(100, 20);
	
	options.add(zeiten);

	ausgabe.setRows(35);
    ausgabe.setVisible(true);
	ausgabe.setAutoscrolls(true);
	ausgabe.setEditable(false);
    
    JPanel anzeige = new JPanel();
    anzeige.add(ausgabe);
    
	frame.setLayout(new BorderLayout());//add
	frame.add(grundformen, BorderLayout.WEST);
	frame.add(anzeige, BorderLayout.EAST);
	frame.add(options, BorderLayout.CENTER);
	frame.add(okp, BorderLayout.SOUTH);
  }
  
  private static String getpath()
  {
	  
	  String systemname = System.getProperty("os.name");
	  
	  if(systemname.substring(0, 3).equalsIgnoreCase("Win"))//pfat organizer
	  {
		  dir.replace("\\", "\\\\");//verdoppelt \
		  
		  dir = dir + "\\";//fügt am ende ein \ an.
	  }
	  else
	  {
		  dir = dir + "/";//fügt am ende ein / an.
	  }  
	  return dir;
    }
  
  private static String[] gettransdata(byte n) throws IOException
  {
	  dir = getpath();  
	  
		String[] text = new String[n];
		String[] language = config.read(dir + "conf.txt", 1);
		language[0] = language[0].substring(10,12);
		
		text = freder.read(dir + language[0] + ".txt", n);
		return text;
   
  }
  
   
  public static void printdata(String data)
  {
	if(ausgabe.getLineCount() > 30)
	{
		String[] zspeicher6 = ausgabe.getText().split("\n",8);
		ausgabe.setText(zspeicher6[7]);
	}
	
	ausgabe.setText(ausgabe.getText() + "\n" + data);
  }
  
  public static void compile() throws IOException
  {
	  
	  String[] text = gettransdata(n);
		verb v = new verb(text);
	    //variablen
		v.grundform1 = grundform1.getText();
		v.grundform2 = grundform2.getText();
		v.grundform3 = grundform3.getText();
		v.grundform4 = grundform4.getText();
		
		v.sg = sg.isSelected();
		v.aktiv = akt.isSelected();
		v.indikativ = ind.isSelected();
		v.zeitform = (byte) zeiten.getSelectedIndex();
		
		if(p1.isSelected())
		{
			v.person = 1;
		}
		else if(p2.isSelected())
		{
			v.person = 2;
		}
		else if(p3.isSelected())
		{
			v.person = 3;
		}
		
		//-------compile--------
	      
	      if(!v.indikativ)
	      {
	    	  compile = v.compilekonjungtiv();//konjungtiv
	      }
	      else
	      {
	    	  compile = v.compile();//indikativ
	      }  
	      
	      //-------ausgabe--------
	      if (compile.equals("false") || !(v.compileerror.equalsIgnoreCase("")))//fehler erkennung
	      {
	       //printdata(text[14]);
	        printdata(v.compileerror);
	      }
		   
	      printdata("\n\n---------------\n" + compile);
	      printdata(v.koninf);
  }

  public static void grundform1()
  {
	  if(grundform1.getText().equalsIgnoreCase(irregulareverben[0]) ||grundform1.getText().equalsIgnoreCase(irregulareverben[1]) || grundform1.getText().equalsIgnoreCase(irregulareverben[2]) || grundform1.getText().equalsIgnoreCase(irregulareverben[3]))
      {//irregulareverben
    	  printdata("Achtung! Irregulares Verb");
      }  
	else
	{
		printdata(grundform1.getText() + " Acept!");
	}
  }  
}