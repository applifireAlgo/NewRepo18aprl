package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.Aone;
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

@Repository
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "", versionNumber = "1", comments = "Repository for Aone Transaction table", complexity = Complexity.MEDIUM)
public class AoneRepositoryImpl extends SearchInterfaceImpl implements AoneRepository<Aone> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(System.getProperty("LOGGER_ID"));

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Aone> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appinsight.health.Aone> query = emanager.createQuery("select u from Aone u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Aone save(Aone entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Aone> save(List<Aone> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.Aone obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appinsight.health.Aone s = emanager.find(com.app.shared.appinsight.health.Aone.class, id);
        emanager.remove(s);
        Log.out.println("AISHI328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Aone entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Aone> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.Aone obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public Aone findById(String aid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Aone.findById");
        query.setParameter("aid", aid);
        com.app.shared.appinsight.health.Aone listOfAone = (com.app.shared.appinsight.health.Aone) query.getSingleResult();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AoneRepositoryImpl", "findById", "Total Records Fetched = " + listOfAone);
        return listOfAone;
    }
}
