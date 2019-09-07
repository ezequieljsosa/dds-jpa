package ar.edu.d2s;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CasaTest {

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager = null;
	private Casa casaExistente = null;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("db");
		//hbm2ddl.auto - create | update | validate | create-drop
		

	}
		
	private  <T> void borrarObjetosDeClase(Class<T> class1) {
	        this.entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = this.entityManager.getTransaction();
                transaction.begin();
                CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();              
                CriteriaDelete<T> query = builder.createCriteriaDelete(class1);
                query.from(class1);
                this.entityManager.createQuery(query).executeUpdate();
                transaction.commit();
        }


	@Before
	public void setUp() {
	        borrarObjetosDeClase(Casa.class        );
	        this.casaExistente = new Casa("CasaExistente");
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(casaExistente);
			this.entityManager.getTransaction().commit();
		
	}


	@Test
	public void testObtenerCasa() {
		this.entityManager = entityManagerFactory.createEntityManager();
		//Crear una casa con un cliente SQL


		//Imprimirla por pantalla
	}

	@Test
	public void testGuardarCasa() {
		this.entityManager = entityManagerFactory.createEntityManager();
		Casa miCasa = new Casa("Eze");

		//Guardar la casa y ver que se genero una fila en la base de datos
		
	}


	@Test
	public void testActualizarCasa() {
		this.entityManager = entityManagerFactory.createEntityManager();
		// Obtener una casa existente
		// Cambiarle el atributo propietario
		// Guardar ?
		// verificar el cambio desde el cliente SQL
		//Imprimirla por pantalla
	}

	@Test
	public void testGuardarCasaConHab() {
	    // Para esto ponemos create-drop en hbm2ddl
		// Con esto vamos a probar las 2 direccionalidades y la bidireccional
		this.entityManager = entityManagerFactory.createEntityManager();

        Casa miCasa = new Casa("Eze");
        
        Habitacion living = new Habitacion();
        living.setAmbiente("living");

        miCasa.addHabitacion(living);
        
        //Guardar y ver estructura en base de datos
	}

    @Test
    public void testHerenciaCasa() {
        // Para esto ponemos create-drop en hbm2ddl
        // Crear 2 subtipos de casa y probar las 3 estrategias de herencia
        this.entityManager = entityManagerFactory.createEntityManager();
        // Guardar y ver estructura en base de datos para cada estrategia       
    }


    @Ignore
	@Test
	public void testCompleto() {
		this.entityManager = entityManagerFactory.createEntityManager();
		// Genero la casa
		Casa miCasa = new Casa("Eze");
		Habitacion living = new Habitacion();
		living.setAmbiente("living");

		miCasa.addHabitacion(living);

		// Guardo la casa
		// Buscar una casa por criterio 'nombre'
	}

}