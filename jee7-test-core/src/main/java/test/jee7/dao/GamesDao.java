package test.jee7.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.jee7.domain.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@LocalBean
@Stateless
public class GamesDao {
    public static final Logger logger = LoggerFactory.getLogger(GamesDao.class);

    @PersistenceContext(name = "games")
    EntityManager em;

    public void test() {
        //TODO remove this, this was just to test mappings
        getAllEntities(Developer.class);
        getAllEntities(Game.class);
        getAllEntities(Company.class);
        getAllEntities(Console.class);
        getAllEntities(User.class);
        getAllEntities(Tag.class);
    }

    private void getAllEntities(Class<?> entityClass) {
        List<?> entityList = em.createQuery(String.format("from %s %s", entityClass.getSimpleName(), entityClass.getSimpleName().toLowerCase()) , entityClass).getResultList();
        for (Object entity : entityList) {
            logger.info(entity.toString());
        }
    }
}
