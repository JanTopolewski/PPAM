import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static void main(String[] args) {
        File dataFile = new File("liczby.txt");

        try(Scanner reader = new Scanner(dataFile)){
            ArrayList<java.lang.Integer> numbers = new ArrayList<>();
            int resultsNumber = 0;

            while(reader.hasNextLine()){
                String data = reader.nextLine();
                if(!data.isEmpty()){
                    int number = Integer.parseInt(data);

                    numbers.add(number);
                }
            }

            File resultsFile = new File("trojki.txt");

            try {
                if(resultsFile.createNewFile()){
                    System.out.println("Utworzono plik na uzyskane trójki");
                }
            }
            catch(IOException ex){
                System.out.println("Wystąpił błąd przy próbie tworzenia pliku");
                ex.printStackTrace();
            }

            try(FileWriter writer = new FileWriter("trojki.txt", true)){

                for(Integer number1 : numbers){
                    for(Integer number2 : numbers){
                        if(number2 % number1 != 0 || number1 >= number2){
                            continue;
                        }
                        for (Integer number3 : numbers) {
                            if (number3 % number2 == 0 && number2 < number3) {
                                resultsNumber++;
                                writer.write(number1 + " " + number2 + " " + number3+"\n");
                            }
                        }
                    }
                }
            }
            catch(IOException ex){
                System.out.println("Wystąpił błąd podczas próby stworzenia obiektu do aktualizacji pliku z trójkami");
                ex.printStackTrace();
            }

            System.out.println("a) " + resultsNumber);

            resultsNumber = 0;
            for(Integer number1 : numbers){
                for(Integer number2 : numbers){
                    if(number2 % number1 != 0 || number1 >= number2){
                        continue;
                    }
                    for (Integer number3 : numbers) {
                        if(number3 % number2 != 0 || number2 >= number3){
                            continue;
                        }
                        for (Integer number4 : numbers) {
                            if(number4 % number3 != 0 || number3 >= number4){
                                continue;
                            }
                            for (Integer number5 : numbers) {
                                if (number5 % number4 == 0 && number4 < number5) {
                                    resultsNumber++;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("b) " + resultsNumber);
        }
        catch(FileNotFoundException ex){
            System.out.println("Nie znaleziono pliku");
        }
    }
}