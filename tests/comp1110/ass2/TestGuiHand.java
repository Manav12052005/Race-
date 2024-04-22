package comp1110.ass2;

import java.util.Scanner;

public class TestGuiHand {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        System.out.println(Hand.handToCards(str));
    }
}
