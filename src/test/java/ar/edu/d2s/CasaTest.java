package ar.edu.d2s;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	        borrarObjetosDeClase(Habitacion.class);
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
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(miCasa);
		this.entityManager.getTransaction().commit();
		//Guardar la casa y ver que se genero una fila en la base de datos
		Casa otraCasa = this.entityManager.find(Casa.class, miCasa.getId());
		assertThat(miCasa, is(otraCasa));
		 this.entityManager.clear();
		 otraCasa = this.entityManager.find(Casa.class, miCasa.getId());
		 assertThat(miCasa, not(otraCasa));
		
	}


	@Test
	public void testActualizarCasa() {
		this.entityManager = entityManagerFactory.createEntityManager();
		// Obtener una casa existente
		this.entityManager .getTransaction().begin();
		Casa casaExistente2 = this.entityManager.find(Casa.class, this.casaExistente.getId() );
		// Cambiarle el atributo propietario
		casaExistente2.setPropietario("Jose");
		// Guardar ?
		
		this.entityManager .getTransaction().commit();
		// verificar el cambio desde el cliente SQL
			

		//Imprimirla por pantalla
		this.entityManager.clear();
		casaExistente2 = this.entityManager.find(Casa.class, this.casaExistente.getId() );
		assertThat(casaExistente2.getPropietario(),is("Jose"));
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
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(miCasa);
        this.entityManager.getTransaction().commit();
        
        this.entityManager.clear();
        
        miCasa = this.entityManager.find(Casa.class, miCasa.getId() );
		assertThat(miCasa.getHabitaciones().size(), is(1));
	}

    @Test
    public void testHerenciaCasa() {
        // Para esto ponemos create-drop en hbm2ddl
        // Crear 2 subtipos de casa y probar las 3 estrategias de herencia
        this.entityManager = entityManagerFactory.createEntityManager();

        Casa miCasa = new Mansion("Eze",1,2);

        // Guardar y ver estructura en base de datos para cada estrategia
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(miCasa);
        this.entityManager.getTransaction().commit();
        
        this.entityManager.clear();
        
        miCasa = this.entityManager.find(Casa.class, miCasa.getId() );

        assertThat(miCasa,instanceOf(Mansion.class));        
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
		EntityTransaction tx = this.entityManager.getTransaction();
		tx.begin();
		this.entityManager.persist(living);
		this.entityManager.persist(miCasa);
		tx.commit();

		this.entityManager.close();
		this.entityManager = entityManagerFactory.createEntityManager();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Casa> criteriaQuery = qb.createQuery(Casa.class);
		Root<Casa> p = criteriaQuery.from(Casa.class);
		Predicate condition = qb.equal(p.get("propietario"), "Eze");
		criteriaQuery.where(condition);
		TypedQuery<Casa> q = entityManager.createQuery(criteriaQuery);
		Casa casaGuardada2 = q.getSingleResult();

		assertNotNull(casaGuardada2);
		assertEquals(1, casaGuardada2.getHabitaciones().size());
	}

}