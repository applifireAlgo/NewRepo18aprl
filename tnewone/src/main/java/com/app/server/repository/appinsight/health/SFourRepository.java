package com.app.server.repository.appinsight.health;
import com.athena.server.repository.SearchInterface;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.util.List;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;

@SourceCodeAuthorClass(createdBy = "shweta.zagade@algorhythm.co.in", updatedBy = "shweta.zagade@algorhythm.co.in", versionNumber = "6", comments = "Repository for SFour Transaction table", complexity = Complexity.MEDIUM)
public interface SFourRepository<T> extends SearchInterface {

    public List<T> findAll() throws SpartanPersistenceException;

    public T save(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> save(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void delete(String id) throws SpartanPersistenceException;

    public void update(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void update(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> findBySds(String sds) throws Exception, SpartanPersistenceException;

    public List<T> findBySffdf(String sffdf) throws Exception, SpartanPersistenceException;

    public List<T> findBySdfs(String sdfs) throws Exception, SpartanPersistenceException;

    public List<T> findByFs(String fs) throws Exception, SpartanPersistenceException;

    public List<T> findByDfd(String dfd) throws Exception, SpartanPersistenceException;

    public T findById(String sFourid) throws Exception, SpartanPersistenceException;
}
