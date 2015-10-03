package prima.plus.verb.compile;

import prima.plus.main.filereader;
import prima.plus.main.mainscript;

public class verb
{
  private static final String[] konjnames = {"a\n","e\n","i\n","kons\n"}; //namen der konjugationen
  
  public String grundform1 = "";
  public String grundform2 = "";
  public String grundform3 = "";
  public String grundform4 = "";
  
  String text[] = new String[67];
  
  public byte zeitform = 0;//zeit 0= präsens, 1=perfect, 2=plusquamperfect,3=imperfect,4=Futur,5=imperativ
  public byte person = 0;
  public boolean sg = true;
  public boolean aktiv = true;
  public boolean indikativ = true; //indikativ oder Konjungtiv?
  public byte konjugation = 0; //0= undef,1=a,2=e,i,kons.
  public String compileerror = "";
  
  public String koninf = "\n";//vorgehens weise
  public String[] zspeicher3;//zur konjugations bestimmung benötigt
  public String zspeicher4 = "";//zur konjugations bestimmung benötigt
  public char zspeicher5 = ' ';//zur konjugations bestimmung benötigt
  
  public String compile = "false";//compile output
  mainscript mains = new mainscript();
  
  filereader freder = new filereader();

  void getkonjugation()
  {
    this.zspeicher3 = this.grundform1.split("re", 2);

    this.zspeicher4 = this.zspeicher3[0];

    this.zspeicher5 = this.zspeicher4.charAt(this.zspeicher4.length() - 1);

    if (this.zspeicher5 == 'a')//a-konjugation
    {
      this.konjugation = 1;
    }
    else if (this.zspeicher5 == 'e')
    {
      this.zspeicher4 = this.grundform2;
      this.zspeicher5 = this.zspeicher4.charAt(this.zspeicher4.length() - 2);

      if (this.zspeicher5 == 'e')//e-konjugation
      {
        this.konjugation = 2;
      }
      else//kons-konjugation
      {
        this.konjugation = 4;
        this.compile = this.compile.substring(0, this.compile.length() - 1);
      }
    }
    else if (this.zspeicher5 == 'i')//i-konjugation
    {
      this.konjugation = 3;
    }
    else//kons-konjugation
    {
      this.konjugation = 4;
      this.compile = this.compile.substring(0, this.compile.length() - 1);
    }
    
    this.koninf += text[30] + konjnames[this.konjugation-1];
  }

  String getstandartend(boolean a, byte b, boolean m)//m oder o?
  {
    if ((a) && (b == 1))
    {
      if (m)
      {
        return "-m";
      }

      return "-o";
    }
    if ((a) && (b == 2))
    {
      return "-s";
    }
    if ((a) && (b == 3))
    {
      return "-t";
    }
    if ((!a) && (b == 1))
    {
      return "-mus";
    }
    if ((!a) && (b == 2))
    {
      return "-tis";
    }
    if ((!a) && (b == 3))
    {
      return "-nt";
    }

    
    compileerror = text[59];
    return "false";
    
  }

  String getperfectend(boolean a, byte b)
  {
    if ((a) && (b == 1))
    {
      return "-i";
    }
    if ((a) && (b == 2))
    {
      return "-isti";
    }
    if ((a) && (b == 3))
    {
      return "-it";
    }
    if ((!a) && (b == 1))
    {
      return "-imus";
    }
    if ((!a) && (b == 2))
    {
      return "-istis";
    }
    if ((!a) && (b == 3))
    {
      return "-erunt";
    }
    compileerror = text[59];
    return "false";
  }

  String getkonjungtivesse(boolean p, boolean a, byte b)//präsens oder imperativ?
  {
	  if(p)//präsens
	  {
		  if ((a) && (b == 1))
		    {
		      return " sim";
		    }
		    if ((a) && (b == 2))
		    {
		      return " sis";
		    }
		    if ((a) && (b == 3))
		    {
		      return " sit";
		    }
		    if ((!a) && (b == 1))
		    {
		      return " simus";
		    }
		    if ((!a) && (b == 2))
		    {
		      return " sitis";
		    }
		    if ((!a) && (b == 3))
		    {
		      return " sint";
		    }
	  }
	  else//imperfekt
	  {
		  this.zspeicher4 = " esse" + this.getstandartend(a, b, true).substring(1);//abschneiden des -
		  return this.zspeicher4;
	  }
    compileerror = text[59];
    return "false";
  }

  String getpassivend(boolean a, byte b, boolean o)//or 0der r?
  {
    if ((a) && (b == 1))
    {
    	if(o)//or
    	{	
           return "-or";
    	}
    	return "-r";
    }
    if ((a) && (b == 2))
    {
      return "-ris";
    }
    if ((a) && (b == 3))
    {
      return "-tur";
    }
    if ((!a) && (b == 1))
    {
      return "-mur";
    }
    if ((!a) && (b == 2))
    {
      return "-mini";
    }
    if ((!a) && (b == 3))
    {
      return "-ntur";
    }
    compileerror = text[59];
    return "false";
  }
  
  String getesse(boolean p, boolean a, byte b)//präsens? (sonst imperfect), nummerus,person
  {
	if(p)//präsens
	{
		if ((a) && (b == 1))
	    {
	      return " sum";
	    }
	    if ((a) && (b == 2))
	    {
	      return " es";
	    }
	    if ((a) && (b == 3))
	    {
	      return " est";
	    }
	    if ((!a) && (b == 1))
	    {
	      return " sumus";
	    }
	    if ((!a) && (b == 2))
	    {
	      return " estis";
	    }
	    if ((!a) && (b == 3))
	    {
	      return " sunt";
	    }
	    compileerror = text[59];
	    return "false";
	}
	else//imperfect
	{
	  zspeicher4 = " era";
	  zspeicher4 = zspeicher4 + this.getstandartend(a, b, true);
	  return zspeicher4;
	}
  }
  
  public String compile()
  {
    if (this.aktiv)
    {
      if (this.zeitform == 0)//präsens
      {
        this.compile = grundform1.substring(0, grundform1.length() - 2);
        this.koninf += text[31] + this.compile +"\n";

        getkonjugation();

        if ((this.sg) && (this.person == 1) && (this.konjugation == 1))
        {
          this.compile = (this.compile.substring(0, this.compile.length() - 1) + "-o");
        }

        if ((!(this.sg)) && (this.person == 3) && (this.konjugation == 3))
        {
          this.compile += "-u";
          this.koninf += text[32] + "\n";
        }

        if (((this.sg) && ((this.person == 3) || (this.person == 2))) || ((!this.sg) && ((this.person == 1) || (this.person == 2)) && (this.konjugation == 4)))
        {
          this.compile += "-i";
          this.koninf += text[33] + "\n";
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 4))
        {
          this.compile += "-u";
          this.koninf += text[32] + "\n";
        }

        this.koninf += text[35] + getstandartend(this.sg, this.person, false);
        this.compile = this.compile + getstandartend(this.sg, this.person, false);
      }
      if (this.zeitform == 1)//perfect
      {
        this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
        this.koninf = text[36] + "\n";
        this.koninf = text[37] + "\n";
        this.compile = this.compile + this.getperfectend(this.sg, this.person);
      }
      if (this.zeitform == 2)//plusquamperfect
      {
        this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
        this.koninf = text[36] + "\n";
        this.koninf += text[35] + getstandartend(this.sg, this.person, false);
        this.compile = this.compile + "-era" + getstandartend(this.sg, this.person, false);
      }
      if (this.zeitform == 3)//imperfect
      {
        this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
        this.koninf += text[38] + "\n";
        getkonjugation();

        if ((this.konjugation == 1) || (this.konjugation == 2))
        {
          this.koninf += text[39] + "\n";
          this.compile += "-ba";
        }
        else if ((this.konjugation == 4) || (this.konjugation == 3))
        {
          this.compile += "-eba";
          this.koninf += text[40] + "\n";
        }
        this.koninf += text[35] + getstandartend(this.sg, this.person, true);
        this.compile = this.compile + getstandartend(this.sg, this.person, true);
      }
      if (this.zeitform == 4)//Futur
      {
        this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
        this.koninf += text[31] + "\n";

        getkonjugation();

        if ((this.konjugation == 1) || (this.konjugation == 2))
        {
          if ((this.sg) && (this.person == 1))
          {
            this.compile += "-b";
            this.koninf += text[41] + "\n";
          }
          if ((!this.sg) && (this.person == 3))
          {
            this.compile += "-bu";
            this.koninf += text[42] + "\n";
          }
          else
          {
            this.compile += "-bi";
            this.koninf += text[43] + "\n";
          }
          this.koninf += text[35] + getstandartend(this.sg, this.person,false);
          this.compile = this.compile + getstandartend(this.sg, this.person,false);
        }
        if ((this.konjugation == 3) || (this.konjugation == 4))
        {
          if ((this.sg) && (this.person == 1))
          {
            this.compile += "-a";
            this.koninf += text[44] + "\n";
          }
          else
          {
            this.compile += "-e";
            this.koninf += text[45] + "\n";
          }
          this.koninf += text[35] + getstandartend(this.sg, this.person,true);
          this.compile = this.compile + getstandartend(this.sg, this.person,true);
        }
      }
      else
      {
        if (this.zeitform == 5)//imperativ
        {
          this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
          this.koninf += text[31] + "\n";

          getkonjugation();

          if ((this.sg) && (this.konjugation == 4))
          {
            this.compile += "-e";
            this.koninf += text[45] + "\n";
          }

          if ((!this.sg) && (this.konjugation == 4))
          {
            this.compile += "-i";
            this.koninf += text[33] + "\n";
          }

          if (this.sg)
          {
            this.koninf += text[47];
          }

          this.koninf += text[48];
          this.compile = this.compile + "-te";
        }
      }

    }
    else //passiv
    {
      if (this.zeitform == 0)//präs
      {
        this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
        this.koninf += text[49] + "\n";

        getkonjugation();

        if ((this.sg) && (this.person == 1) && (this.konjugation == 1))
        {
          this.compile = (this.compile.substring(0, this.compile.length() - 1) + "-o");
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 3))
        {
          this.compile += "-u";
          this.koninf += text[32] + "\n";
        }

        if (((this.sg) && ((this.person == 3) || (this.person == 2))) || ((!this.sg) && ((this.person == 1) || (this.person == 2)) && (this.konjugation == 4)))
        {
          this.compile += "-i";
          this.koninf += text[33] + "\n";
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 4))
        {
          this.compile += "-u";
          this.koninf += text[32] + "\n";
        }
        if ((this.sg) && (this.person == 2) && (this.konjugation == 4))
        {
          this.compile += "-e";
          this.koninf += text[46] + "\n";
        }

        this.koninf += text[35] + getpassivend(this.sg, this.person,true);
        this.compile = this.compile + this.getpassivend(this.sg, this.person,true);
      }

      if (this.zeitform == 1)//perfect
      {
    	  this.compile = grundform4.substring(0, this.grundform4.length()-4);
    	  this.koninf += text[36] + "\n";//wegdenken von isse
    	  
    	  if(sg)
			{
				this.compile += "-tus";
				this.koninf += text[63] + "\n";//anhängen von tus
			}
			else
			{
				this.compile += "-ti";
				this.koninf += text[64] + "\n";//anhängen von ti
			}
    	  
    	  this.compile += getesse(false, this.sg, this.person);
    	  this.koninf += text[55] + "\n"; //hinzufügen von esse
    	  //ppp + präsens esse
      }  
      if (this.zeitform == 2)//plusquamperfect
      {
    	  this.compile = grundform4;
    	  this.compile += getesse(true, this.sg, this.person);
    	  this.koninf += text[56] + "\n"; //hinzufügen von imperfect esse
    	  //ppp + imperfect esse
      }
      if (this.zeitform == 3)//imperativ
      {
            this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
            this.koninf += text[49] + "\n";

            getkonjugation();

            if ((this.konjugation == 1) || (this.konjugation == 2))
            {
              this.koninf += text[39] + "\n";
              this.compile += "-ba";
            }
            else if ((this.konjugation == 4) || (this.konjugation == 3))
            {
              this.compile += "-eba";
              this.koninf += text[40] + "\n";
            }
            this.koninf += text[35] + getpassivend(this.sg, this.person,false);
            this.compile = this.compile + this.getpassivend(this.sg, this.person,false);
          }

          if (this.zeitform == 4)//futur
          {
            this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
            this.koninf += text[39] + "\n";

            getkonjugation();

            if ((this.konjugation == 1) || (this.konjugation == 2))
            {
              if ((this.sg) && (this.person == 1))
              {
                this.compile += "-b";
                this.koninf += text[41] + "\n";
              }
              if ((!this.sg) && (this.person == 3))
              {
                this.compile += "-bu";
                this.koninf += text[42] + "\n";
              }
              else if ((this.sg) && (this.person == 2))
              {
                this.compile += "-be";
                this.koninf += text[50] + "\n";
              }
              else
              {
                this.compile += "-bi";
                this.koninf += text[43] + "\n";
              }

              this.koninf += text[35] + getpassivend(this.sg, this.person,true);
              this.compile = this.compile + this.getpassivend(this.sg, this.person,true);
            }
            if ((this.konjugation == 3) || (this.konjugation == 4))
            {
              if ((this.sg) && (this.person == 1))
              {
                this.compile += "-a";
                this.koninf += text[44] + "\n";
              }
              else
              {
                this.compile += "-e";
                this.koninf += text[45] + "\n";
              }
              this.koninf +=  text[35] + getpassivend(this.sg, this.person,false);
              this.compile = this.compile + this.getpassivend(this.sg, this.person,false);
            }

          }
          else
          {
            this.compile =  "false";
            this.compileerror = text[62];
          }//imperativ
          
     }//passiv else
    
   return this.compile;
  }//compile void

  
  public String compilekonjungtiv()
  {        
	  this.compile = "false";
	  if (this.aktiv) {
			if (this.zeitform == 0)// präsens
			{
				this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
		        this.koninf += text[31] + this.compile +"\n";
		        
		        this.getkonjugation();
		        
		        if(this.konjugation == 1)//falls a konjugation
		        {
		        	this.compile = this.compile + "-e";
			        this.koninf += text[34] + "\n";
		        }
		        else//fals nicht a konjugation
		        {
		        	this.compile = this.compile + "-a";
			        this.koninf += text[45] + "\n";	
		        }
		        this.compile = this.compile + this.getstandartend(sg, person, true);
		        this.koninf += text[35] + this.getstandartend(sg, person, true);
		        
			}
			if (this.zeitform == 1)// perfect
			{
				this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
		        this.koninf = text[36] + "\n";//wegdenken von isse
		        this.koninf = text[37] + "\n";//perso endung
		        this.compile = this.compile + "-eri" + this.getstandartend(sg, person, true).substring(1);
		        
			}
			if (this.zeitform == 2)// plusquamperfect
			{
				
		        this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
		        this.koninf = text[36] + "\n";//isse weg
		        this.compile = this.compile + "-isse";
		        this.koninf = this.koninf + text[51] + "\n";//isse hin wichtig wegen "-"
		        this.koninf = text[37] + "\n";//perso endung
		        this.compile = this.compile + this.getstandartend(this.sg, this.person, true);
			}
			if (this.zeitform == 3)// imperfekt
			{
				this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);//re weg
		        this.koninf += text[38] + "\n";
		        getkonjugation();
		        
		        this.compile += "-re";//hinzufügen von re WICHTIG wegen "-"
		        this.koninf += text[52] + "\n";
		        
		        this.koninf += text[35] + getstandartend(this.sg, this.person, true);
		        this.compile = this.compile + getstandartend(this.sg, this.person, true);		//person(mit m)
			}
			else// imperativ etc Futur
			{
				this.compile = "false";
				this.compileerror = text[62];
			}
	 } 
	 else // passiv
	{
		if (this.zeitform == 0)// präsens
		{
			this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
	        this.koninf += text[31] + this.compile +"\n";
	        
	        this.getkonjugation();
	        
	        if(this.konjugation == 1)//falls a konjugation
	        {
	        	this.compile = this.compile + "-e";
		        this.koninf += text[53] + "\n";
	        }
	        else
	        {
	        	this.compile = this.compile + "-a";
		        this.koninf += text[54] + "\n";	
	        }
	        this.compile = this.compile + this.getpassivend(sg, person, false);
	        this.koninf += text[35] + this.getpassivend(sg, person, false);
		}
		if (this.zeitform == 1)// perfect
		{
			this.compile = this.grundform4;
			
			this.compile = this.compile.substring(0, this.compile.length()-4);//weg von isse
			this.koninf = text[36] + "\n";//isse weg
			
			if(sg)
			{
				this.compile += "-tus";
				this.koninf += text[63] + "\n";//anhängen von tus
			}
			else
			{
				this.compile += "-ti";
				this.koninf += text[64] + "\n";//anhängen von ti
			}
			
			this.compile += this.getkonjungtivesse(true,sg, person);
			this.koninf += text[65] + "\n";//anhängen von konjugtiv präsens esse
		}
		else if(this.zeitform == 2)//plusquamperfekt
		{
            this.compile = this.grundform4;
			
			this.compile = this.compile.substring(0, this.compile.length()-4);//weg von isse
			this.koninf = text[36] + "\n";//isse weg
			
			if(sg)
			{
				this.compile += "-tus";
				this.koninf += text[63] + "\n";//anhängen von tus
			}
			else
			{
				this.compile += "-ti";
				this.koninf += text[64] + "\n";//anhängen von ti
			}
			
			this.compile += this.getkonjungtivesse(false,sg, person);
			this.koninf += text[66] + "\n";//anhängen von konjugtiv imperfekt esse
			
		}
		else if (this.zeitform == 3)// imperfekt
		{
	    	this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
	        this.koninf += text[31] + this.compile +"\n";
	        
	        this.getkonjugation();
	        
	        if(this.konjugation == 1)//falls a konjugation
	        {
	        	this.compile = this.compile + "-e";
		        this.koninf += text[61] + "\n";
	        }
	        else
	        {
	        	this.compile = this.compile + "-a";
		        this.koninf += text[60] + "\n";	
	        }
	        this.compile = this.compile + this.getpassivend(sg, person, false);
	        this.koninf += text[35] + this.getpassivend(sg, person, true);
	    }

		else// futur etc
	    {
	    	this.compile = "false";
	    	this.compileerror = text[62];//fehlerhafte zeit bla
	    }
	    
    }// passiv else

   return this.compile;
  }//compile void
  
  

  public verb(String[] str)
  {
	 text = str.clone();
	 
  }
  
}//class