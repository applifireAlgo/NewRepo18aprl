package com.app.server.repository.appinsight.health;
import com.athena.server.repository.SearchInterface;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.util.List;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;

@SourceCodeAuthorClass(createdBy = "shweta.zagade@algorhythm.co.in", updatedBy = "", versionNumber = "1", comments = "Repository for TESTB Master table Entity", complexity = Complexity.LOW)
public interface TESTBRepository<T> extends SearchInterface {

    public List<T> findAll() throws SpartanPersistenceException;

    public T save(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> save(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void delete(String id) throws SpartanPersistenceException;

    public void update(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void update(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> findByBc(String bc) throws Exception, SpartanPersistenceException;

    public List<T> findByRrrr(String rrrr) throws Exception, SpartanPersistenceException;

    public List<T> findByAddressId(String addressId) throws Exception, SpartanPersistenceException;

    public List<T> findByContactId(String contactId) throws Exception, SpartanPersistenceException;

    public T findById(String aaaaa) throws Exception, SpartanPersistenceException;
}
