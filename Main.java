package jdbc;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws DALException {
		System.out.println("Bienvenue");
		switchChoices();
	}
	
	
	public static void switchChoices() throws DALException {
		Scanner userInput = new Scanner(System.in);		// Création du scanner
		String choix = "0";
		do {					// Tant que la variable "choix" est différentes de 9
			System.out.println("1. Afficher les étudiants");
			System.out.println("2. Insérer un nouvel étudiant");
			System.out.println("3. Modifier un étudiant");
			System.out.println("4. Supprimer un étudiant");
			System.out.println("9. Quitter");
			try {
				System.out.print("Choix : ");
				choix = userInput.nextLine();			// Enregistrement du choix de l'utilisateur sous format String
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			switch (choix) {							// En fonction du choix, ...
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
				System.out.println("\n");
				break;
			}
			
			case "2": {		// Insertion d'un nouvel étudiant
				System.out.print("Entrez le nom du nouvel l'élève : ");
				String unNom = userInput.next();
				System.out.print("Entrez le prénom du nouvel l'élève : ");
				String unPrenom = userInput.next();
				System.out.print("Entrez la date de naissance du nouvel l'élève : ");
				String uneDateNaissance = userInput.next();
				System.out.print("Entrez le mail du nouvel l'élève : ");
				String unEmail = userInput.next();
				System.out.print("Entrez le matricule du nouvel l'élève : ");
				String unMatricule = userInput.next();
				
				EtudiantController.getInstance().saveEtudiantStandalone(null, unPrenom, unNom, uneDateNaissance, unEmail, unMatricule);
				EtudiantController.getInstance().refresh();
				System.out.println("\n");
				break;
			}
			
			case "3": {		// Modification d'un étudiant
				System.out.println("Entrez le nom, puis le prénom de l'étudiant à modifier : ");
				String oldNom = userInput.next();
				String oldPrenom = userInput.next();
				Etudiant etudiantManaged = EtudiantController.getInstance().getEtudiantById
						(EtudiantController.getInstance().getEtudiantIdByNomPrenom(oldNom, oldPrenom));
				
				System.out.print("Entrez le nouveau nom de l'élève : ");
				String newNom = userInput.next();
				System.out.print("Entrez le nouveau prénom de l'élève : ");
				String newPrenom = userInput.next();
				System.out.print("Entrez la nouvelle date de naissance de l'élève : ");
				String newDateNaissance = userInput.next();
				System.out.print("Entrez le nouveau mail de l'élève : ");
				String newEmail = userInput.next();
				System.out.print("Entrez le nouveau matricule de l'élève : ");
				String newMatricule = userInput.next();
				
				EtudiantController.getInstance().saveEtudiantStandalone(etudiantManaged,
						newPrenom, newNom, newDateNaissance, newEmail, newMatricule);
				EtudiantController.getInstance().refresh();
				System.out.println("\n");
				break;
			}
			
			case "4": {		// Suppression d'un étudiant
				System.out.println("Entrez le nom, puis le prénom de l'étudiant à supprimer : ");
				String nom = userInput.next();
				String prenom = userInput.next();
				Etudiant etudiantManaged = EtudiantController.getInstance().getEtudiantById
						(EtudiantController.getInstance().getEtudiantIdByNomPrenom(nom, prenom));
				
				EtudiantController.getInstance().deleteEtudiant(etudiantManaged);
				EtudiantController.getInstance().refresh();
				System.out.println("\n");
				break;
			}
			
			case "9": {		// Quitter l'appli
				System.out.println("Au revoir");
				break;
			}
			
			default:		// Si un chiffre différent de ceux précédents est utilisé, message d'erreur, puis boucle
				System.out.println("Erreur dans l'entrée du nombre");
			}
		} while (!choix.equals("9"));
		userInput.close();
	}
}
