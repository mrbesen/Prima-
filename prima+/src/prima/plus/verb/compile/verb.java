package prima.plus.verb.compile;

import prima.plus.main.mainscript;

public class verb
{
  public String grundform1;
  public String grundform2;
  public String grundform3;
  public String grundform4;
  
  public byte zeitform = 0;
  public byte person = 0;
  public boolean sg = true;
  public boolean aktiv = true;
  public byte konjugation = 0;

  public String koninf = ": \n";
  public String[] zspeicher3;
  public String zspeicher4 = "";
  public char zspeicher5 = ' ';

  public String compile = "a";

  mainscript mains = new mainscript();

  void getkonjugation()
  {
    this.zspeicher3 = this.grundform1.split("re", 2);

    this.zspeicher4 = this.zspeicher3[0];

    this.zspeicher5 = this.zspeicher4.charAt(this.zspeicher4.length() - 1);

    if (this.zspeicher5 == 'a')
    {
      this.konjugation = 1;
    }
    else if (this.zspeicher5 == 'e')
    {
      this.zspeicher4 = this.grundform2;
      this.zspeicher5 = this.zspeicher4.charAt(this.zspeicher4.length() - 2);

      if (this.zspeicher5 == 'e')
      {
        this.konjugation = 2;
      }
      else
      {
        this.konjugation = 4;
        this.compile = this.compile.substring(0, this.compile.length() - 1);
      }
    }
    else if (this.zspeicher5 == 'i')
    {
      this.konjugation = 3;
    }
    else
    {
      this.konjugation = 4;
      this.compile = this.compile.substring(0, this.compile.length() - 1);
    }

    this.koninf += " - herausfinden der Konjugation\n";
  }

  String getstandartend(boolean a, byte b)
  {
    if ((a) && (b == 1))
    {
      if ((this.zeitform == 3) || ((this.person == 1) && ((this.konjugation == 3) || (this.konjugation == 4)) && (this.sg) && (this.zeitform == 4)))
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

    return "false";
  }

  public String compile()
  {
    if (this.aktiv)
    {
      if (this.zeitform == 0)
      {
        this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
        this.koninf += " - wegdenken von \"re\"\n";

        getkonjugation();

        if ((this.sg) && (this.person == 1) && (this.konjugation == 1))
        {
          this.compile = (this.compile.substring(0, this.compile.length() - 1) + "-o");
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 3))
        {
          this.compile += "-u";
          this.koninf += "ausnahme: \"Sprech-u\"\n";
        }

        if (((this.sg) && ((this.person == 3) || (this.person == 2))) || ((!this.sg) && ((this.person == 1) || (this.person == 2)) && (this.konjugation == 4)))
        {
          this.compile += "-i";
          this.koninf += "ausnahme: \"Sprech-i\"\n";
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 4))
        {
          this.compile += "-u";
          this.koninf += " - ausnahme: \"Sprech-u\"\n";
        }

        this.koninf += " - anhängen der Personalendung.";
        return this.compile + getstandartend(this.sg, this.person);
      }
      if (this.zeitform == 1)
      {
        this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
        this.koninf = " - wegdenken von -isse\n";
        this.koninf = " - anhängen der Personalendung\n";
        return this.compile + this.mains.getperfectend(this.sg, this.person);
      }
      if (this.zeitform == 2)
      {
        this.compile = this.grundform3.substring(0, this.grundform3.length() - 4);
        this.koninf = " - wegdenken von -isse\n";
        this.koninf += " - anhängen der Personalendung\n";
        return this.compile + "-era" + getstandartend(this.sg, this.person);
      }
      if (this.zeitform == 3)
      {
        this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
        this.koninf += " - wegdenken von \"re\"\n";
        getkonjugation();

        if ((this.konjugation == 1) || (this.konjugation == 2))
        {
          this.koninf += " - anhängen der Imperativ-endung hier: -ba\n";
          this.compile += "-ba";
        }
        else if ((this.konjugation == 4) || (this.konjugation == 3))
        {
          this.compile += "-eba";
          this.koninf += " - anhängen der Imperativ-endung hier: -eba\n";
        }
        this.koninf += " - anhängen der Personalendung.";
        return this.compile + getstandartend(this.sg, this.person);
      }
      if (this.zeitform == 4)
      {
        this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
        this.koninf += " - wegdenken von \"re\"\n";

        getkonjugation();

        if ((this.konjugation == 1) || (this.konjugation == 2))
        {
          if ((this.sg) && (this.person == 1))
          {
            this.compile += "-b";
            this.koninf += " - anhengen der Futurendung hier:\"-b\"\n";
          }
          if ((!this.sg) && (this.person == 3))
          {
            this.compile += "-bu";
            this.koninf += " - anhengen der Futurendung hier:\"-bu\"\n";
          }
          else
          {
            this.compile += "-bi";
            this.koninf += " - anhengen der Futurendung hier:\"-bi\"\n";
          }
          this.koninf += " - anhängen der Personalendung\n";
          return this.compile + getstandartend(this.sg, this.person);
        }
        if ((this.konjugation == 3) || (this.konjugation == 4))
        {
          if ((this.sg) && (this.person == 1))
          {
            this.compile += "-a";
            this.koninf += " - anhengen der Futurendung hier:\"-a\"\n";
          }
          else
          {
            this.compile += "-e";
            this.koninf += " - anhengen der Futurendung hier:\"-e\"\n";
          }
          this.koninf += " - anhängen der Personalendung\n";
          return this.compile + getstandartend(this.sg, this.person);
        }
      }
      else
      {
        if (this.zeitform == 5)
        {
          this.compile = this.grundform1.substring(0, this.grundform1.length() - 2);
          this.koninf += " - wegdenken von \"re\" \n";

          getkonjugation();

          if ((this.sg) && (this.konjugation == 4))
          {
            this.compile += "-e";
            this.koninf += " - ausnahme: anhängen eines \"Sprech-e's\"\n";
          }

          if ((!this.sg) && (this.konjugation == 4))
          {
            this.compile += "-i";
            this.koninf += " - ausnahme: anhängen eines \"Sprech-i's\"\n";
          }

          if (this.sg)
          {
            this.koninf += " - Das wars schon! (es existiert keine Personalendung im imperativ Sg!)";
            return this.compile;
          }

          this.koninf += " - anhängen der Pl-Imperativ-Endung";
          return this.compile + "-te";
        }

        return "false";
      }

    }
    else //passiv
    {
      if (this.zeitform == 0)
      {
        this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
        this.koninf += " - wegdenken von \"ri\"\n";

        getkonjugation();

        if ((this.sg) && (this.person == 1) && (this.konjugation == 1))
        {
          this.compile = (this.compile.substring(0, this.compile.length() - 1) + "-o");
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 3))
        {
          this.compile += "-u";
          this.koninf += "ausnahme: \"Sprech-u\"\n";
        }

        if (((this.sg) && ((this.person == 3) || (this.person == 2))) || ((!this.sg) && ((this.person == 1) || (this.person == 2)) && (this.konjugation == 4)))
        {
          this.compile += "-i";
          this.koninf += "ausnahme: \"Sprech-i\"\n";
        }

        if ((!this.sg) && (this.person == 3) && (this.konjugation == 4))
        {
          this.compile += "-u";
          this.koninf += " - ausnahme: \"Sprech-u\"\n";
        }
        if ((this.sg) && (this.person == 2) && (this.konjugation == 4))
        {
          this.compile += "-e";
          this.koninf += " - ausnahme: \"Sprech-e\"\n";
        }

        this.koninf += " - anhängen der Personalendung.";
        return this.compile + this.mains.getpassivend(this.sg, this.person);
      }

      if (this.zeitform == 1)
      {
        
      }  
      if (this.zeitform == 2)
      {
        	
      }
      if (this.zeitform == 3)
      {
            this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
            this.koninf += " - wegdenken von \"ri\"\n";

            getkonjugation();

            if ((this.konjugation == 1) || (this.konjugation == 2))
            {
              this.koninf += " - anhängen der Imperativ-endung hier: -ba\n";
              this.compile += "-ba";
            }
            else if ((this.konjugation == 4) || (this.konjugation == 3))
            {
              this.compile += "-eba";
              this.koninf += " - anhängen der Imperativ-endung hier: -eba\n";
            }
            this.koninf += " - anhängen der Personalendung.";
            return this.compile + this.mains.getpassivend(this.sg, this.person);
          }

          if (this.zeitform == 4)
          {
            this.compile = this.grundform4.substring(0, this.grundform4.length() - 2);
            this.koninf += " - wegdenken von \"ri\"\n";

            getkonjugation();

            if ((this.konjugation == 1) || (this.konjugation == 2))
            {
              if ((this.sg) && (this.person == 1))
              {
                this.compile += "-b";
                this.koninf += " - anhengen der Futurendung hier:\"-b\"\n";
              }
              if ((!this.sg) && (this.person == 3))
              {
                this.compile += "-bu";
                this.koninf += " - anhengen der Futurendung hier:\"-bu\"\n";
              }
              else if ((this.sg) && (this.person == 2))
              {
                this.compile += "-be";
                this.koninf += " - anhengen der Futurendung hier:\"-be\"\n";
              }
              else
              {
                this.compile += "-bi";
                this.koninf += " - anhengen der Futurendung hier:\"-bi\"\n";
              }

              this.koninf += " - anhängen der Personalendung\n";
              return this.compile + this.mains.getpassivend(this.sg, this.person);
            }
            if ((this.konjugation == 3) || (this.konjugation == 4))
            {
              if ((this.sg) && (this.person == 1))
              {
                this.compile += "-a";
                this.koninf += " - anhengen der Futurendung hier:\"-a\"\n";
              }
              else
              {
                this.compile += "-e";
                this.koninf += " - anhengen der Futurendung hier:\"-e\"\n";
              }
              this.koninf += " - anhängen der Personalendung\n";
              return this.compile + this.mains.getpassivend(this.sg, this.person);
            }
            return "true";

          }
          else
          {
            return "false";
          }
      }
    return null;
    }//passiv else
}