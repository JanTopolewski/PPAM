import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File dataFile = new File("liczby.txt");

        try(Scanner myReader = new Scanner(dataFile)){
            int maxFactorsNumber = 0;
            int maxDifferentFactorsNumber = 0;
            String numberWithMaxFactors = "";
            String numberWithMaxDifferentFactors = "";
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                if(!data.isEmpty()){
                    int number = Integer.parseInt(data);
                    int currentFactorsNumber = 0;
                    int currentDifferentFactorsNumber = 0;
                    int factor = 2;
                    if(number % factor == 0){
                        currentDifferentFactorsNumber++;
                    }
                    while(number > 1){
                        if(number % factor != 0){
                            factor++;
                            if (number % factor == 0){
                                currentDifferentFactorsNumber++;
                            }
                        }
                        else{
                            currentFactorsNumber++;
                            number = number / factor;
                        }
                    }

                    if(currentFactorsNumber > maxFactorsNumber){
                        maxFactorsNumber = currentFactorsNumber;
                        numberWithMaxFactors = data;
                    }

                    if(currentDifferentFactorsNumber > maxDifferentFactorsNumber){
                        maxDifferentFactorsNumber = currentDifferentFactorsNumber;
                        numberWithMaxDifferentFactors = data;
                    }
                }
            }
            System.out.println(numberWithMaxFactors+" "+maxFactorsNumber+" "+numberWithMaxDifferentFactors+" "+maxDifferentFactorsNumber);
        }
        catch(FileNotFoundException ex){
            System.out.println("Nie znaleziono pliku");
        }
    }
}