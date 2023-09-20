package jdbc;

/**
 * Classe métier Etudiant
 * Permet l'instanciation d'un étudiant
 */
public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String email;
	private String matricule;

	/**
	 * Constructeur
	 * @param id : id de l'étudiant
	 * @param nom : nom de l'étudiant
	 * @param prenom : prénom de l'étudiant
	 * @param dateNaissance : date de naissance de l'étudiant
	 * @param email : adresse e-mail de l'étudiant
	 * @param matricule : matricule de l'étudiant
	 */
	public Etudiant(int id, String nom, String prenom, String dateNaissance, String email, String matricule) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.matricule = matricule;
	}

	/**
	 * Constructeur
	 * @param nom : nom de l'étudiant
	 * @param prenom : prénom de l'étudiant
	 * @param dateNaissance : date de naissance de l'étudiant
	 * @param email : adresse e-mail de l'étudiant
	 * @param matricule : matricule de l'étudiant
	 */
	public Etudiant(String nom, String prenom, String dateNaissance, String email, String matricule) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.matricule = matricule;
	}


	/**
	 * Getter et Setter de l'id de l'étudiant
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Getter et Setter du prénom de l'étudiant
	 */
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	/**
	 * Getter et Setter du nom de l'étudiant
	 */
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * Getter et Setter de la date de naissance de l'étudiant
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	/**
	 * Getter et Setter de l'email de l'étudiant
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Getter et Setter du matricule de l'étudiant
	 */
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}



	/**
	 * toString de l'étudiant
	 * @return le nom et le prénom de l'étudiant
	 */
	@Override
	public String toString() {
		return  nom + ' ' + prenom;
	}
}
