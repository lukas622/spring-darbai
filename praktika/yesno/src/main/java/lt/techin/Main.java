package lt.techin;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String answer = "";

    while (!answer.equals("No")) {
      System.out.println("Yes or no?");
      answer = scanner.nextLine();
    }
  }
}
