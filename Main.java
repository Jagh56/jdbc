
package jdbc;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws DALException {
		String choix;
		do {
			choix = "0";
			
			System.out.println("Bienvenue");
			System.out.println("1. Afficher les étudiants");
			System.out.println("2. Insérer un nouvel étudiant");
			System.out.println("3. Modifier un étudiant (pas encore implémenté)");
			System.out.println("4. Supprimer un étudiant (pas encore implémenté)");
			System.out.println("9. Quitter");
			try {
				System.out.println("Choix :");
				choix = userInput();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			switchChoices(choix);
		} while (choix != "9" && choix != "0");
	}
	
	
	
	public static String userInput() {
		Scanner userInput = new Scanner(System.in);
		String retourInput = userInput.nextLine(); // Lire l'input
		userInput.close();
		return retourInput;
	}
	
	
	
	public static void switchChoices(String choix) {
		switch (choix) {
		case "1": {		// Affichage de la liste de tous les étudiants
			List<Etudiant> etudiants = EtudiantController.getInstance().getAll();
			
			String resNom = "NOM";
			String resPrenom = "PRENOM";
			String resDateNaissance = "DATE NAISSANCE";
			String resEmail = "EMAIL";
			String resMatricule = "MATRICULE";
			
			System.out.println(resNom + " | " + resPrenom + " | " + resDateNaissance + " | "+ resEmail
					+ " | " + resMatricule);
			
			for (Etudiant unEtudiant : etudiants) {
				resNom = unEtudiant.getNom();
				resPrenom = unEtudiant.getPrenom();
				resDateNaissance = unEtudiant.getDateNaissance();
				resEmail = unEtudiant.getEmail();
				resMatricule = unEtudiant.getMatricule();
				
				System.out.println(resNom + " | " + resPrenom + " | " + resDateNaissance + " | "+ resEmail
						+ " | " + resMatricule);
			}
			break;
		}
		
		case "2": {		// Insertion d'un nouvel étudiant
			System.out.println("Entrez le nom du nouvel l'élève : ");
			String unNom = userInput();
			System.out.println("Entrez le prénom du nouvel l'élève : ");
			String unPrenom = userInput();
			System.out.println("Entrez la date de naissance du nouvel l'élève : ");
			String uneDateNaissance = userInput();
			System.out.println("Entrez le mail du nouvel l'élève : ");
			String unEmail = userInput();
			System.out.println("Entrez le matricule du nouvel l'élève : ");
			String unMatricule = userInput();
			
			EtudiantController.getInstance().saveEtudiantStandalone(null, unPrenom, unNom, uneDateNaissance, unEmail, unMatricule);
			EtudiantController.getInstance().refresh();
			break;
		}
		
		case "3": {		// Modification d'un étudiant
			System.out.println("Entrez le nom, puis le prénom de l'étudiant à modifier : ");
			String oldNom = userInput();
			String oldPrenom = userInput();
			Etudiant etudiantManaged = EtudiantController.getInstance().getEtudiantById
					(EtudiantController.getInstance().getEtudiantIdByNomPrenom(oldNom, oldPrenom));
			
			System.out.println("Entrez le nouveau nom de l'élève : ");
			String newNom = userInput();
			System.out.println("Entrez le nouveau prénom de l'élève : ");
			String newPrenom = userInput();
			System.out.println("Entrez la nouvelle date de naissance de l'élève : ");
			String newDateNaissance = userInput();
			System.out.println("Entrez le nouveau mail de l'élève : ");
			String newEmail = userInput();
			System.out.println("Entrez le nouveau matricule de l'élève : ");
			String newMatricule = userInput();
			
			EtudiantController.getInstance().saveEtudiantStandalone(etudiantManaged,
					newPrenom, newNom, newDateNaissance, newEmail, newMatricule);
			EtudiantController.getInstance().refresh();
			break;
		}
		
		case "4": {		// Suppression d'un étudiant
			System.out.println("Entrez le nom, puis le prénom de l'étudiant à supprimer : ");
			String nom = userInput();
			String prenom = userInput();
			Etudiant etudiantManaged = EtudiantController.getInstance().getEtudiantById
					(EtudiantController.getInstance().getEtudiantIdByNomPrenom(nom, prenom));
			
			EtudiantController.getInstance().deleteEtudiant(etudiantManaged);
			EtudiantController.getInstance().refresh();
			break;
		}
		
		case "9": {		// Quitter l'appli
			System.out.println("Au revoir : ");
			break;
		}
		
		default:
			System.out.println("Erreur dans l'entrée du nombre");
		}
	}
}
