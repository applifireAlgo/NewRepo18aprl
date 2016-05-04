package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.TestOne;
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
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "6", comments = "Repository for TestOne Master table Entity", complexity = Complexity.LOW)
public class TestOneRepositoryImpl extends SearchInterfaceImpl implements TestOneRepository<TestOne> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(System.getProperty("LOGGER_ID"));

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<TestOne> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appinsight.health.TestOne> query = emanager.createQuery("select u from TestOne u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public TestOne save(TestOne entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<TestOne> save(List<TestOne> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.TestOne obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appinsight.health.TestOne s = emanager.find(com.app.shared.appinsight.health.TestOne.class, id);
        emanager.remove(s);
        Log.out.println("AISHI328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(TestOne entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<TestOne> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.TestOne obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<TestOne> findByGen(String gen) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestOne.findByGen");
        query.setParameter("gen", gen);
        java.util.List<com.app.shared.appinsight.health.TestOne> listOfTestOne = query.getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "findByGen", "Total Records Fetched = " + listOfTestOne.size());
        return listOfTestOne;
    }

    @Transactional
    public List<TestOne> findByAddressId(String addressId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestOne.findByAddressId");
        query.setParameter("addressId", addressId);
        java.util.List<com.app.shared.appinsight.health.TestOne> listOfTestOne = query.getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "findByAddressId", "Total Records Fetched = " + listOfTestOne.size());
        return listOfTestOne;
    }

    @Transactional
    public TestOne findById(String tid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestOne.findById");
        query.setParameter("tid", tid);
        com.app.shared.appinsight.health.TestOne listOfTestOne = (com.app.shared.appinsight.health.TestOne) query.getSingleResult();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestOneRepositoryImpl", "findById", "Total Records Fetched = " + listOfTestOne);
        return listOfTestOne;
    }
}
