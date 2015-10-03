package prima.plus.main;

import java.util.Scanner;
import prima.plus.verb.compile.verb;

public class haupt
{
  static String eingabe = "0";
  static byte konjobest = 0;
  static byte zspeicher = 0;
  static String zspeicher2 = "0";
  static boolean continu = true;

  static Scanner s = new Scanner(System.in);

  public static void main(String[] args)
  {
    while (continu)
    {
      continu = false;

      System.out.println("-----------------------------------------------");
      System.out.println("Willkommen in Prima plus deklinierer von Yannis");
      System.out.println("-----------------------------------------------");
      System.out.println("");
      System.out.println("Bitte geben sie an ob sie eine form >>konjugieren<< oder >>bestimmen<< wollen.");

      konjobest = mainscript.eingabetest("konjugieren", "bestimmen");
      if (konjobest == 1)
      {
        System.out.println("");

        verb v = new verb();

        System.out.println("Sie haben konjugieren ausgewählt.");
        System.out.println("Bitte geben sie an ob sie ihr Wort im >>Aktiv<< oder >>Passiv<< haben wollen.");
        if (mainscript.eingabetest("aktiv", "passiv") == 1)
        {
          v.aktiv = true;
        }
        do
        {
          System.out.println("Bitte geben sie die Zeitform an. (0=präsens, 1=perfect, 2=plusquamperfect, 3=imperfekt, 4=Futur, 5=Imperativ)");
          v.zeitform = s.nextByte();
          if ((v.zeitform < 0) || (v.zeitform > 5))
          {
            System.out.println("Fehler! es giebt keine " + v.zeitform + ". Zeitform! Versuche es nochmal.");
          }
        }
        while ((v.zeitform < 0) || (v.zeitform > 5));

        System.out.println("Bitte geben sie an ob sie ihr Wort im >>Sg<< oder >>Pl<< haben wollen.");

        zspeicher = mainscript.eingabetest("Sg", "Pl");
        if (zspeicher == 1)
        {
          v.sg = true;
        }
        else if (zspeicher == 2)
        {
          v.sg = false;
        }
        if (v.zeitform != 5)
        {
          do
          {
            System.out.println("Bitte geben sie die Person an.(1,2 oder 3)");
            v.person = s.nextByte();
            if ((v.person < 1) || (v.person > 3))
            {
              System.out.println("Fehler! es giebt keine " + v.person + ". person!");
            }
          }
          while ((v.person < 1) || (v.person > 3));
        }
        System.out.println("Bitte geben sie nun das Verb in der Grundform an. (1. form)");
        zspeicher2 = s.next();
        v.grundform1 = zspeicher2;
        System.out.println("Nun Bitte die 2. form.");
        v.grundform2 = s.next();
        if ((v.zeitform == 1) || (v.zeitform == 2))
        {
          System.out.println("Jetzt bitte die 3. grundform.");
          v.grundform3 = s.next();
        }
        if (!v.aktiv)
        {
          System.out.println("nun nurnoch die passiv-grundform.");
          v.grundform4 = s.next();
        }

        if (v.compile().equals("flase"))
        {
          System.out.println("Es gab leider ein  Fehler! :( Vieleicht existiert diese Form nicht? oder sie wurde noch nicht programmiert");
        }
        else
        {
          System.out.println("Fertig! Das Wort lautet:" + v.compile);
          if (v.sg)
          {
            zspeicher2 = "Sg.";
          }
          else
          {
            zspeicher2 = "Pl.";
          }
          System.out.println("Infos: " + v.person + ". person " + zspeicher2 + "\n so bin ich vorgegeangen" + v.koninf);
        }
        System.out.println("\n\n");
        System.out.println("Wollen sie das Programm >>schließen<< oder >>neustarten<< ?");
        if (mainscript.eingabetest("schließen", "neustarten") == 1)
        {
          System.exit(1);
        }
        else
        {
          continu = true;
        }
      }
    }
  }
}