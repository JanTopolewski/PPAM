import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static int GetFirstDigit(int number){
        while(number >= 10){
            number = number - (number % 10);
            number = number / 10;
        }
        return number;
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        File dataFile = new File("liczby.txt");

        try(Scanner myReader = new Scanner(dataFile)){
            String firstCorrectNumber = "";
            int correctNumbers = 0;
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                if(!data.isEmpty()){
                    int number = Integer.parseInt(data);
                    int lastDigit = number % 10;
                    int firstDigit = GetFirstDigit(number);
                    if(firstDigit == lastDigit){
                        correctNumbers++;
                        if(firstCorrectNumber.isEmpty()){
                            firstCorrectNumber = data;
                        }
                    }
                }
            }
            System.out.println(correctNumbers+" "+firstCorrectNumber);
        }
        catch(FileNotFoundException ex){
            System.out.println("Nie znaleziono pliku");
        }
    }
}