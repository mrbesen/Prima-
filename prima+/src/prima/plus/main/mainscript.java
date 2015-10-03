package prima.plus.main;

import java.util.Scanner;

public class mainscript
{
  static String eingabe = "0";

  static Scanner s = new Scanner(System.in);

  boolean eingabetest(String a)
  {
    eingabe = s.next();
    if (eingabe.equalsIgnoreCase(a))
    {
      return true;
    }

    return false;
  }

  static byte eingabetest(String a, String b)
  {
    if (a.equals(b))
    {
      return 127;
    }
    while (true)
    {
      eingabe = s.next();
      if (eingabe.equalsIgnoreCase(a))
      {
        return 1;
      }
      if (eingabe.equalsIgnoreCase(b))
      {
        return 2;
      }
      System.out.println("versuchen sie es bitte nochmal!");
    }
  }

  public String getperfectend(boolean a, byte b)
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

    return "false";
  }

  public String getpassivend(boolean a, byte b)
  {
    if ((a) && (b == 1))
    {
      return "-or";
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

    return "false";
  }
}