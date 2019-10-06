import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class M6_pt3_Dvaquer_binario {
	public static void main(String[] args) throws IOException {
		menu();
	}

	public static void menu() throws IOException {
		Scanner myObj = new Scanner(System.in);
		File file;
		int opcion = 0;
		boolean semaforo = true;
		file = new File("src\\becadades.dat");
		while (semaforo) {
			System.out.println("1. INTRODUIR\n2. LLISTAR\n3. BACKUP");
			if (myObj.hasNextInt()) {
				opcion = myObj.nextInt();
				myObj.nextLine();
				if (opcion < 1 || opcion > 3) {
					System.out.println("Posa una opcio de 1-3\n");
				} else if ((opcion == 2 || opcion == 3) && !(file.exists())) {
					System.out.println("No existeix el ficher de dades becadades.dat");
				} else {
					semaforo = false;
					opcions(opcion);
				}
			} else {
				myObj.nextLine();
				System.out.println("Posa un int\n");
			}
		}
	}

	public static void opcions(int opcion) throws IOException {
		Scanner myObj = new Scanner(System.in);
		File file;
		if (opcion == 1) {
			file = new File("src\\becadades.dat");
			if (!(file.exists())) {
				file.createNewFile();
			}
			DataInputStream leerBinario = new DataInputStream(new FileInputStream(file));
			byte[] array = new byte[leerBinario.available()];
			for (int i = 0; i < array.length; i++) {
				array[i]=leerBinario.readByte();
			}
			DataOutputStream escriure = new DataOutputStream(new FileOutputStream(file));
			for (int i = 0; i < array.length; i++) {
				escriure.writeByte(array[i]);
			}
			String nomCognom = "";
//			ESCRIURE NOM
			System.out.println("Digues el teu nom i cognom");
			while (nomCognom.equals("")) {
				nomCognom = myObj.nextLine();
			}
			escriure.writeUTF(nomCognom);
//			ESCRIURE SEXE
			System.out.println("Sexe (H | M) ");
			nomCognom = "R";
			while (!(nomCognom.equals("H") || nomCognom.equals("M"))) {
				nomCognom = myObj.nextLine();
				if (!(nomCognom.equals("H") || nomCognom.equals("M"))) {
					System.out.println("Intenta posar (H | M)");
				}
			}
			escriure.writeChar(nomCognom.charAt(0));
//			ESCRIURE EDAT
			int edat = 0;
			System.out.println("Digues la teva edat (20 | 60)");
			while (edat < 20 || edat > 60) {
				if (myObj.hasNextInt()) {
					edat = myObj.nextInt();
					myObj.nextLine();
					if (edat < 20 || edat > 60) {
						System.out.println("Posa una opcio de 20-60\n");
					}
				} else {
					myObj.nextLine();
					System.out.println("Posa un int\n");
				}
			}
			escriure.writeInt(edat);
//			Numero de suspensos del curs anterior (0 | 4)
			edat = -1;
			System.out.println("Numero de suspensos del curs anterior (0 | 4)");
			while (edat < 0 || edat > 4) {
				if (myObj.hasNextInt()) {
					edat = myObj.nextInt();
					myObj.nextLine();
					if (edat < 0 || edat > 4) {
						System.out.println("Posa una opcio de 0-4\n");
					}
				} else {
					myObj.nextLine();
					System.out.println("Posa un int\n");
				}
			}
			escriure.writeInt(edat);
//			Residència familiar (SI | NO) 
			System.out.println("Residència familiar (SI | NO) ");
			nomCognom = "";
			while (!(nomCognom.equals("SI") || nomCognom.equals("NO"))) {
				nomCognom = myObj.nextLine();
				if (!(nomCognom.equals("SI") || nomCognom.equals("NO"))) {
					System.out.println("Intenta posar SI | NO");
				}
			}
			escriure.writeUTF(nomCognom);
//			Ingressos anuals de la família.
			double ingresos = -1;
			System.out.println("Ingressos anuals de la família.");
			while (ingresos < 0) {
				if (myObj.hasNextDouble()) {
					ingresos = myObj.nextDouble();
					myObj.nextLine();
					if (ingresos < 0) {
						System.out.println("Posa uns ingresos familiars positius\n");
					}
				} else {
					myObj.nextLine();
					System.out.println("Posa un numero\n");
				}
				escriure.writeDouble(ingresos);
			}
			escriure.close();
		} else if (opcion == 2) {
			file = new File("src\\becadades.dat");
			DataInputStream leerBinario = new DataInputStream(new FileInputStream(file));
			int contadorPersona = 1;
			while (leerBinario.available() > 0) {
				System.out.println("-------Persona " + contadorPersona + ": \nNom i Cognom: " + leerBinario.readUTF());
				System.out.println("Sexe: " + leerBinario.readChar());
				System.out.println("Edat: " + leerBinario.readInt());
				System.out.println("Suspensos: " + leerBinario.readInt());
				System.out.println("Residencia familiar: " + leerBinario.readUTF());
				System.out.println("Ingresos anuals familiars: " + leerBinario.readDouble());
				contadorPersona++;
			}
			System.out.println("");
		} else if (opcion == 3) {
			file = new File("src\\becadades.dat");
			File fileBackup;
			fileBackup = new File("src\\becadadesBK.dat");
			DataInputStream leerBinario = new DataInputStream(new FileInputStream(file));
			byte[] array = new byte[leerBinario.available()];
			for (int i = 0; i < array.length; i++) {
				array[i]=leerBinario.readByte();
			}
			DataOutputStream escriure = new DataOutputStream(new FileOutputStream(fileBackup));
			for (int i = 0; i < array.length; i++) {
				escriure.writeByte(array[i]);
			}
			System.out.println("Backup fet!");
		}
		menu();
	}

}
