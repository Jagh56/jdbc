package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAOEtudiants
 * Implémente la classe abstraite DAO
 * BD : Table Etudiant
 */
public class DAOEtudiant extends DAO<Etudiant> {
	
	/*
	 * Data Access Object pattern :
	 * Sépare l'interface client d'une ressource de données de ses mécanismes d'accès aux données
	 */

	/**
	 * PATTERN SINGLETON : contraint l'instanciation d'une UNIQUE instance de classe
	 */
	private static DAOEtudiant instanceDAOEtudiants;

	/**
	 * Pattern Singleton
	 * @return DAOEtudiants
	 */
	public static synchronized DAOEtudiant getInstance() {
		if (instanceDAOEtudiants == null) {
			instanceDAOEtudiants = new DAOEtudiant();
		}
		return instanceDAOEtudiants;
	}


	/**
	 * LES REQUÊTES SQL
	 *
	 * Les requêtes de sélection
	 */
	private static final String sqlSelectAll = "select * from Etudiants order by nom";
	private static final String sqlSelectIdByNomPrenom = "select id from Etudiants where nom = ? and prenom = ?"
			+ " order by nom";
	private static final String sqlSelectOneById = "select * from Etudiants where id = ?";

	/**
	 * Les requêtes de mise à jour
	 */
	private static final String sqlUpdateOne = "update Etudiants set nom = ?, prenom = ?, date_naissance = ?, "
			+ "email = ?, matricule = ? where id = ?";

	/**
	 * Les requêtes d'insertion
	 */
	private static final String sqlInsertOne = "insert into Etudiants (nom, prenom, date_naissance, email, matricule)"
			+ " values (?,?,?,?,?)";

	/**
	 * Les requêtes de suppression
	 */
    private static final String sqlDeleteEtudiantById = "delete from Etudiants where id = ?";
    
    
	
	/**
	 * Permet de récupérer tous les Etudiants stockés dans la table Etudiant
	 * @return la liste des Etudiants
	 * @throws DALException : message d'erreur
	 */
	@Override
	public List<Etudiant> getAll() throws DALException {
		List<Etudiant> EtudiantList = new ArrayList<>();
		try {
			Connection cnx = JdbcTools.getConnection();
			Statement rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(sqlSelectAll);
			while (rs.next()) {
				//instanciation de Etudiant
				Etudiant etu = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("date_naissance"), rs.getString("email"), rs.getString("matricule"));
                EtudiantList.add(etu);
            }
            rqt.close();
		} catch (SQLException e) {
			//Exception personnalisée
			throw new DALException(e.getMessage(),e);
		}
		return EtudiantList;
	}


	/**
	 * Permet de récupérer l'id d'un Etudiant connaissant le nom (contrainte d'unicité sur le nom)
	 * @param nom : nom de Etudiant recherché
	 * @return l'id de Etudiant
	 * @throws DALException : message d'erreur
	 */
	public Integer getIdByNomPrenom(String nom, String prenom) throws DALException {
		Integer etuid = null;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(sqlSelectIdByNomPrenom);
			rqt.setString(1, nom);
			rqt.setString(2, prenom);
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				//instanciation de Etudiant
				etuid = rs.getInt("id");
			}
			rqt.close();
		} catch (SQLException e) {
			//Exception personnalisée
			throw new DALException(e.getMessage(),e);
		}
		return etuid ;
	}


	/**
	 * Permet de récupérer un Etudiant à partir de son id
	 * @param id : l'id de Etudiant
	 * @return le Etudiant dont l'id est transmis en paramètre
	 * @throws DALException : message d'erreur
	 */
	@Override
	public Etudiant getOneById(int id) throws DALException {
		Etudiant etu = null;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(sqlSelectOneById);
			rqt.setInt(1, id);

			ResultSet rs = rqt.executeQuery();

			while (rs.next()) {
				etu = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("date_naissance"), rs.getString("email"), rs.getString("matricule"));
			}
			rqt.close();
		} catch (SQLException e) {
			//Exception personnalisée
			throw new DALException(e.getMessage(),e);
		}
		return etu;
	}



	/**
	 * Permet l'insertion d'un nouvel Etudiant
	 * @param obj : Etudiant à inserer
	 * @throws DALException : message d'erreur
	 */
	@Override
	public void save(Etudiant obj) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(sqlInsertOne);
			rqt.setString(1, obj.getNom());
			rqt.setString(2, obj.getPrenom());
			rqt.setString(3, obj.getDateNaissance());
			rqt.setString(4, obj.getEmail());
			rqt.setString(5, obj.getMatricule());
			rqt.executeUpdate();
			rqt.close();
			//mise à jour de l'id de Etudiant nouvellement créé - le champ nom est UNIQUE
			obj.setId(this.getIdByNomPrenom(obj.getNom(), obj.getPrenom()));
		} catch (SQLException e) {
			//Exception personnalisée
			throw new DALException(e.getMessage(),e);
		}
	}
	
	
	
	@Override
	/**
	 * Permet de mettre à jour uniquement les settings de Etudiant dans la base de données
	 * @param obj : Etudiant à mettre à jour
	 * @throws DALException : message d'erreur
	 */
	public void update(Etudiant obj) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(sqlUpdateOne);
			rqt.setString(1, obj.getNom());
			rqt.setString(2, obj.getPrenom());
			rqt.setString(3, obj.getDateNaissance());
			rqt.setString(4, obj.getEmail());
			rqt.setString(5, obj.getMatricule());
			rqt.setInt(6, obj.getId());
			rqt.executeUpdate();
			rqt.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * Permet de supprimer le Etudiant en fonction de son id
	 * @param etuid : id de Etudiant
	 * @throws DALException : message d'erreur
	 */
	public void deleteEtudiantById(int etuid) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(sqlDeleteEtudiantById);
			rqt.setInt(1, etuid);
			rqt.executeUpdate();
			rqt.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(),e);
		}
	}


}
