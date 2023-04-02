package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Alojamiento;

/**
 */
class SQLAlojamiento 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersitenciaAforoandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersitenciaAforoandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLAlojamiento (PersitenciaAforoandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAlojamiento (PersistenceManager pm, long identificacion, String nombre, String vinculo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlojamiento() () + "(identificacion, nombre, vinculo) values (?, ?, ?)");
        q.setParameters(identificacion, nombre, vinculo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorNombre (PersistenceManager pm, String nombreBar)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento () + " WHERE nombre = ?");
        q.setParameters(nombreBar);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorId (PersistenceManager pm, long idBar)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE id = ?");
        q.setParameters(idBar);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Alojamiento darClientePorId (PersistenceManager pm, long idBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento() + " WHERE id = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(idBar);
		return (Alojamiento) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<Alojamiento> darBaresPorNombre (PersistenceManager pm, String nombreBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento () + " WHERE nombre = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(nombreBar);
		return (List<Alojamiento>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Alojamiento> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento ());
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para aumentar en uno el número de sedes de los bares de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param ciudad - La ciudad a la cual se le quiere realizar el proceso
	 * @return El número de tuplas modificadas
	 */
	public long aumentarSedesBaresCiudad (PersistenceManager pm, String vinculo, long id)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamiento () + " SET vinculo ="+vinculo+ "WHERE ciudad = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
}
