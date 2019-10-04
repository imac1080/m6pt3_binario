import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class M6_pt3_Dvaquer_binario {
	public static void main(String[] args) throws IOException {
		Scanner myObj = new Scanner(System.in);
		File file;
		int opcion = 0;
		while (opcion < 1 || opcion > 3) {
			menu();
			if (myObj.hasNextInt()) {
				opcion = myObj.nextInt();
				myObj.nextLine();
				if (opcion < 1 || opcion > 3) {
					System.out.println("Posa una opcio de 1-3\n");
				}
			} else {
				myObj.nextLine();
				System.out.println("Posa un int\n");
			}
		}
		if (opcion == 1) {
			file = new File("src\\becadades.dat");
			if (!(file.exists())) {
				file.createNewFile();
			}
			DataOutputStream escriure = new DataOutputStream(new FileOutputStream(file));
			String nomCognom = "";
			System.out.println("Digues el teu nom i cognom");
			while (nomCognom.equals("")) {
				nomCognom = myObj.nextLine();
			}
			escriure.writeUTF(nomCognom);
			System.out.println("Sexe (H | M) ");
			nomCognom = "R";
			while (!(nomCognom.equals("H") || nomCognom.equals("M"))) {
				nomCognom = myObj.nextLine();
			}
			escriure.writeChar(nomCognom.charAt(0));
			escriure.close();
		} else if (opcion == 2) {

		} else if (opcion == 3) {

		}

	}

	public static void menu() {
		System.out.println("1. INTRODUIR\n2. LLISTAR\n3. BACKUP");
	}
}
