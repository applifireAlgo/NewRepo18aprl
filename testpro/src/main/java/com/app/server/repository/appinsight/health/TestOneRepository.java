package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "6", comments = "Repository for TestOne Master table Entity", complexity = Complexity.LOW)
public interface TestOneRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByGen(String gen) throws Exception;

    public List<T> findByAddressId(String addressId) throws Exception;

    public T findById(String tid) throws Exception;
}
