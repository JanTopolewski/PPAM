import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int[] GenerateRandomNumbers(int length){
        int[] generatedNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            generatedNumbers[i] = (int) (Math.random() * 6) + 1;
        }
        return generatedNumbers;
    }

    static int calculatePoints(int[] numbers){
        Arrays.sort(numbers);
        int sum = 0;
        int occurancesNumber = 1;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != numbers[i - 1]) {
                if(occurancesNumber >= 2){
                    sum += numbers[i - 1] * occurancesNumber;
                }
                occurancesNumber = 1;
            }
            else{
                occurancesNumber++;
            }
        }

        if(occurancesNumber >= 2){
            sum += numbers[numbers.length - 1] * occurancesNumber;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int randomizationsNumber = 0;

        do{
            System.out.println("Ile kostek chcesz rzucić?(3 - 10)");
            try {
                randomizationsNumber = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Podano niepoprawny format liczby");
            }
        }while(randomizationsNumber < 3 || randomizationsNumber > 10);

        String continueRandomizing;
        do{
            int[] randomNumbers = GenerateRandomNumbers(randomizationsNumber);
            for(int i = 0; i < randomNumbers.length; i++){
                System.out.println("Kostka " + (i + 1) + ": " + randomNumbers[i]);
            }
            System.out.println("Liczba uzyskanych punktów: " + calculatePoints(randomNumbers));
            do {
                System.out.println("Jeszcze raz? (t/n)");
                continueRandomizing = scanner.nextLine();
            }while(!continueRandomizing.equals("n") && !continueRandomizing.equals("t"));
        }while(continueRandomizing.equals("t"));
    }
}