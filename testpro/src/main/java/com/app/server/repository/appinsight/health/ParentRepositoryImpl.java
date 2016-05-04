package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.Parent;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.app.shared.appinsight.health.Child;

@Repository
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "2", comments = "Repository for Parent Transaction table", complexity = Complexity.MEDIUM)
public class ParentRepositoryImpl extends SearchInterfaceImpl implements ParentRepository<Parent> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(System.getProperty("LOGGER_ID"));

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Parent> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appinsight.health.Parent> query = emanager.createQuery("select u from Parent u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Parent save(Parent entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Parent> save(List<Parent> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.Parent obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appinsight.health.Parent s = emanager.find(com.app.shared.appinsight.health.Parent.class, id);
        emanager.remove(s);
        Log.out.println("AISHI328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteChild(List<Child> child) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.appinsight.health.Child _child : child) {
            com.app.shared.appinsight.health.Child s = emanager.find(com.app.shared.appinsight.health.Child.class, _child.getCid());
            emanager.remove(s);
        }
    }

    @Override
    @Transactional
    public void update(Parent entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Parent> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.Parent obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public Parent findById(String pid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Parent.findById");
        query.setParameter("pid", pid);
        com.app.shared.appinsight.health.Parent listOfParent = (com.app.shared.appinsight.health.Parent) query.getSingleResult();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ParentRepositoryImpl", "findById", "Total Records Fetched = " + listOfParent);
        return listOfParent;
    }
}
