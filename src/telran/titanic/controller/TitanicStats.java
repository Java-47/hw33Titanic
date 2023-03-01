package telran.titanic.controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TitanicStats {

	public static void main(String[] args) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("train.csv"));

			String str = br.readLine();
			String[] cells = str.split(",");

			double totalFares = 0;
			int class1Count, class2Count, class3Count, survivedCount, notSurvivedCount, survivedWoman, survivedMen,
					survivedChildrens, NotSurvivedWoman, NotSurvivedMen, NotSurvivedChildrens;
			class1Count = class2Count = class3Count = survivedCount = notSurvivedCount = 
					survivedWoman = survivedMen = survivedChildrens = 
					NotSurvivedWoman = NotSurvivedMen = NotSurvivedChildrens = 0;

			double class1Fare, class2Fare, class3Fare;
			class1Fare = class2Fare = class3Fare = 0;

			str = br.readLine();
			while (str != null) {

				cells = str.split(",");
				totalFares += Double.parseDouble(cells[10]);
				// Fares
				if (Integer.parseInt(cells[2]) == 1) {
					class1Count++;
					class1Fare += Double.parseDouble(cells[10]);
				} else if (Integer.parseInt(cells[2]) == 2) {
					class2Count++;
					class2Fare += Double.parseDouble(cells[10]);
				} else if (Integer.parseInt(cells[2]) == 3) {
					class3Count++;
					class3Fare += Double.parseDouble(cells[10]);
				}

				// Not Survived
				if (Integer.parseInt(cells[1]) == 0) {
					notSurvivedCount++;
					if (cells[5].equals("male") && (cells[6].isEmpty() ? 0 : Double.parseDouble(cells[6])) >= 18) {
						NotSurvivedMen++;
					} else if (cells[5].equals("female")
							&& (cells[6].isEmpty() ? 0 : Double.parseDouble(cells[6])) >= 18) {
						NotSurvivedWoman++;
					} else if (!cells[6].isEmpty() && Double.parseDouble(cells[6]) < 18) {
						NotSurvivedChildrens++;
					}

					// Survived
				} else {
					survivedCount++;
					if (cells[5].equals("male") && (cells[6].isEmpty() ? 0 : Double.parseDouble(cells[6])) >= 18) {
						survivedMen++;
					} else if (cells[5].equals("female")
							&& (cells[6].isEmpty() ? 0 : Double.parseDouble(cells[6])) >= 18) {
						survivedWoman++;
					} else if (!cells[6].isEmpty() && Double.parseDouble(cells[6]) < 18) {
						survivedChildrens++;
					}
				}

				str = br.readLine();
			}
			br.close();
			System.out.println("Total Fares: " + String.format("%.2f", totalFares));
			System.out.println("Average fare for class 1: " + String.format("%.2f", class1Fare / class1Count));
			System.out.println("Average fare for class 2: " + String.format("%.2f", class2Fare / class2Count));
			System.out.println("Average fare for class 3: " + String.format("%.2f", class3Fare / class3Count));

			System.out.println("Total survived passengers: " + survivedCount);
			System.out.println("Total not survived passengers: " + notSurvivedCount);

			System.out.println("Survived men: " + survivedMen);
			System.out.println("Not survived men: " + NotSurvivedMen);

			System.out.println("Survived womens: " + survivedWoman);
			System.out.println("Not survived womens: " + NotSurvivedWoman);

			System.out.println("Survived childrens: " + survivedChildrens);
			System.out.println("Not survived childrens: " + NotSurvivedChildrens);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
