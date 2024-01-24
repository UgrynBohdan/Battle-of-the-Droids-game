import Droids.Effects.Attack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = Definition_droids(1);
        Player player2 = Definition_droids(2);
        Player[] players = new Player[]{player1, player2};

        String game = Start_game(players);
        System.out.println("Бажаєте записати гру в файл \n1 - Так \n0 - Ні");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        if (a == 1) {
            try (FileOutputStream fos = new FileOutputStream("C:\\Users\\ACER\\Desktop\\GameL3.txt")) {
                byte[] bytes = game.getBytes(); // Перетворення рядка в байти
                fos.write(bytes); // Запис байтів у файл
                System.out.println("Дані успішно записано у файл.");
            } catch (IOException e) {
                e.printStackTrace();
            }


            try (FileInputStream fis = new FileInputStream("C:\\Users\\ACER\\Desktop\\GameL3.txt")) {
                byte[] data = new byte[fis.available()];
                fis.read(data);
                String content = new String(data);
                System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }

    public static Player Definition_droids(int num) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Гравець №" + num + " Виберіть дроїдів:\n1 - Battle Droid\n2 - Medic Droid\n3 - Amplifier Droid\n4 - Toxic Droid");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");
        return new Player(numbers);
    }

    public static String Start_game(Player[] players) {
        String text_game = "";
        // Створення команд
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int turn = random.nextInt(2);
        turn = 0;
        int step = 0, target = 0;
        Attack attack = null;

        while (true) {
            // Процес гри
            for (int i = 0; i < 3; i++) {
                if (players[(turn + 1) % 2].AllDroidsDead()) {
                    System.out.println(Print_game(players[0], players[1]));
                    System.out.println("\n\n\u001B[43mПереміг гравець №" + (turn + 1) + "!!!\u001B[0m");
                    text_game += Print_game(players[0], players[1]) + "\n" + "\n\n\u001B[43mПереміг гравець №" + (turn + 1) + "!!!\u001B[0m" + "\n";
                    return text_game;
                }
                if (!players[turn].DroidAlive(i))
                    continue;
                System.out.println("\u001B[31mЧерга " + (turn + 1) + "-го гравця\u001B[0m");
                System.out.println("\u001B[34mЧерга дроїда №" + (i + 1) + "\n\u001B[0m");
                text_game += "\u001B[31mЧерга " + (turn + 1) + "-го гравця\u001B[0m" + "\n" + "\u001B[34mЧерга дроїда №" + (i + 1) + "\n\u001B[0m" + "\n";
                for (Player player : players)
                    player.Step();
                System.out.println(Print_game(players[0], players[1]));
                text_game += Print_game(players[0], players[1]) + "\n";
                // Зчитування дії
                while (attack == null) {
                    System.out.println("Виберіть крок:\n1 - атака\t2 - підтримка\t3 - особлива здібність");
                    text_game += "Виберіть крок:\n1 - атака\t2 - підтримка\t3 - особлива здібність" + "\n";
                    step = scanner.nextInt();
                    text_game += "\u001B[92m" + step + "\u001B[0m\n";
                    System.out.println("Оберіть номер мішені");
                    text_game += "Оберіть номер мішені" + "\n";
                    target = scanner.nextInt() - 1;
                    text_game += "\u001B[92m" + (target + 1) + "\u001B[0m\n";

                    // Обробка дії
                    attack = players[turn].Play(i, step);
                    if (attack == null) {
                        System.out.println("Дія неможлива!");
                        text_game += "Дія неможлива!" + "\n";
                    }
                }
                if (attack.damage > 0)
                    players[(turn + 1) % 2].Receive(target, attack); // атакуємо
                else
                    if (players[turn].DroidAlive(target))
                        players[turn].Receive(target, attack); // допомагаємо
                attack = null;
            }
            turn = (turn + 1) % 2;
            players[turn].Turn();
        }
    }

    public static String Print_game(Player player1, Player player2) {
        String text = "";
        text += "Гравець 1\t\t\t\t\t\t\tГравець 2\n";
        for (int i = 0; i < 3; i++) {
            text += "Дроїд №" + (i + 1) + " " + player1.get_name(i) + "\t\t\t\t" + "Дроїд №" + (i + 1) + " " + player2.get_name(i) + "\n";
            text += "HP = " + player1.get_droid_health(i) + "\t\t\t\t\t";
            text += "HP = " + player2.get_droid_health(i) + "\n";

            text += "Damage = " + player1.get_droid_damage(i) + "\t\t\t\t\t\t";
            text += "Damage = " + player2.get_droid_damage(i) + "\n";

            text += "Shield = " + player1.get_droid_shield(i) + "\t\t\t\t\t\t";
            text += "Shield = " + player2.get_droid_shield(i) + "\n";

            text += "Cool down = " + player1.get_droid_cool_down(i) + "\t\t\t\t\t\t";
            text += "Cool down = " + player2.get_droid_cool_down(i) + "\n";
        }
        return text;
    }


}










