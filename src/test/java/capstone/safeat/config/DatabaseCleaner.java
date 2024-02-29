package capstone.safeat.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

  private final List<String> tableNames = new ArrayList<>();

  @PersistenceContext
  private EntityManager entityManager;

  @PostConstruct
  @SuppressWarnings("unchecked")
  private void findDatabaseTableNames() {
    final List<Object[]> tableInfos = entityManager.createNativeQuery("SHOW TABLES")
        .getResultList();
    for (Object[] tableInfo : tableInfos) {
      String tableName = (String) tableInfo[0];
      tableNames.add(tableName);
    }
  }

  private void truncate() {
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;").executeUpdate();
    for (String tableName : tableNames) {
      entityManager.createNativeQuery(String.format("TRUNCATE TABLE %s", tableName))
          .executeUpdate();
    }
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE;").executeUpdate();
  }

  @Transactional
  public void clear() {
    entityManager.clear();
    truncate();
  }
}
