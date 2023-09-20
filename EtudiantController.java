package jdbc;

import jdbc.Etudiant;
import jdbc.DALException;
import jdbc.DAOEtudiant;
import java.util.List;

public class EtudiantController {

	/**
	 * PATTERN SINGLETON : contraint l'instanciation d'une UNIQUE instance de classe
	 */
	private static EtudiantController instanceCtrl;
	private List<Etudiant> etudiants;

	/**
	 * Pattern Singleton
	 * @return EtudiantController
	 */
	public static synchronized EtudiantController getInstance() {
		if(instanceCtrl == null) {
			instanceCtrl = new EtudiantController();
		}
		return instanceCtrl;
	}

	
	/**
	 * Constructeur
	 * Chargement de la liste des membres
	 * En private : pattern Singleton
	 */
	private EtudiantController() {
		try {
			this.etudiants = DAOEtudiant.getInstance().getAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de récupérer l'ensemble des membres ayant été jury
	 * @return la liste des membres
	 */
	public List<Etudiant> getAll() {
		List<Etudiant> etudiants = null;
		try {
			etudiants = DAOEtudiant.getInstance().getAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return etudiants;
	}


	/**
	 * Permet de persister le membre
	 * @param etudiantManaged : membre
	 * @param prenom : prénom du membre
	 * @param nom : nom du membre
	 * @param email : mail du membre
	 */
	public void saveEtudiantStandalone(Etudiant etudiantManaged, String prenom, String nom, String dateNaissance, String email, String matricule) {
		if (etudiantManaged != null) {
			etudiantManaged.setPrenom(prenom);
			etudiantManaged.setNom(nom);
			etudiantManaged.setDateNaissance(dateNaissance);
			etudiantManaged.setEmail(email);
			etudiantManaged.setMatricule(matricule);
			//persistance : Update
			try {
				DAOEtudiant.getInstance().update(etudiantManaged);
			} catch (DALException e) {
				e.printStackTrace();
			}
		} else {
			// Nouveau membre
			etudiantManaged = new Etudiant(prenom, nom, dateNaissance, email, matricule);
			//persistance : Insert
			try {
				DAOEtudiant.getInstance().saveEtudiant(etudiantManaged);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Permet de récupérer l'id d'un membre en fonction de son prénom, de son nom et de son mail
	 * @param prenom : prénom du membre
	 * @param nom : nom du membre
	 * @param mail : mail du membre
	 * @return l'id du membre
	 */
	public int getEtudiantIdByNomPrenom(String nom, String prenom) {
		int idetu = 0;
		try {
			idetu = DAOEtudiant.getInstance().getIdByNomPrenom(nom, prenom);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return idetu;
	}
	
	
	/**
	 * Permet de supprimer un étudiant
	 * @param etudiantManaged : etudiant
	 */
	public void deleteHackathon(Etudiant etudiantManaged) {
		try {
			etudiants.remove(DAOEtudiant.getInstance().getOneById(etudiantManaged.getId()));
			
			//suppression de l'étudiant en fonction de son ID
			DAOEtudiant.getInstance().deleteEtudiantById(etudiantManaged.getId());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh(){
        try {
            this.etudiants = DAOEtudiant.getInstance().getAll();
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}
