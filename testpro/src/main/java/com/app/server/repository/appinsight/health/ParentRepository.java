package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.appinsight.health.Child;

@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "2", comments = "Repository for Parent Transaction table", complexity = Complexity.MEDIUM)
public interface ParentRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void deleteChild(List<Child> child);

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public T findById(String pid) throws Exception;
}
