package jdbc;

import java.util.List;

/**
 * Classe DAO
 *
 * Pattern DAO (Data Access Object) : permet de faire le lien entre la couche métier (bo) et la couche persistante (dal)
 * afin de centraliser les mécanismes de mapping entre notre système de stockage et nos objets Java.
 *
 * Classe générique type <T> : implémentée pour créer des DAO pour des objets de types différents
 *
 * @param <T>
 */
public abstract class DAO<T> {

	public abstract List<T> getAll() throws DALException;
	public abstract T getOneById(int id) throws DALException;
	public abstract void save(T obj) throws DALException;
	public abstract void update(T obj) throws DALException;

}
