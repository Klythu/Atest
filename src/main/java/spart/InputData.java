package spart;

import java.util.Scanner;

public class InputData {
    public String[] enterData() {
        Scanner iScanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите через ПРОБЕЛ Ф.И.О, дату рождения в формате: dd.mm.yyyy, номер телефона - только цифры, пол - f/m ");
            String data = iScanner.nextLine();
            String[] arrayData = data.split(" ");
            if (arrayData.length == 6) {
                return arrayData;
            } else if (arrayData.length < 6){
                System.out.println("Введены не все данные");
            } else System.out.println("Введены лишние данные");
        }

    }
}