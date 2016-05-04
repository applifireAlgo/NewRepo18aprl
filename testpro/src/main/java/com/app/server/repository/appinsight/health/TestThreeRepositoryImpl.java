package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.TestThree;
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
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "4", comments = "Repository for TestThree Transaction table", complexity = Complexity.MEDIUM)
public class TestThreeRepositoryImpl extends SearchInterfaceImpl implements TestThreeRepository<TestThree> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(System.getProperty("LOGGER_ID"));

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<TestThree> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.appinsight.health.TestThree> query = emanager.createQuery("select u from TestThree u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public TestThree save(TestThree entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<TestThree> save(List<TestThree> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.TestThree obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appinsight.health.TestThree s = emanager.find(com.app.shared.appinsight.health.TestThree.class, id);
        emanager.remove(s);
        Log.out.println("AISHI328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(TestThree entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<TestThree> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.appinsight.health.TestThree obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<TestThree> findByGen(String gen) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestThree.findByGen");
        query.setParameter("gen", gen);
        java.util.List<com.app.shared.appinsight.health.TestThree> listOfTestThree = query.getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "findByGen", "Total Records Fetched = " + listOfTestThree.size());
        return listOfTestThree;
    }

    @Transactional
    public List<TestThree> findByAddressId(String addressId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestThree.findByAddressId");
        query.setParameter("addressId", addressId);
        java.util.List<com.app.shared.appinsight.health.TestThree> listOfTestThree = query.getResultList();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "findByAddressId", "Total Records Fetched = " + listOfTestThree.size());
        return listOfTestThree;
    }

    @Transactional
    public TestThree findById(String tttid) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("TestThree.findById");
        query.setParameter("tttid", tttid);
        com.app.shared.appinsight.health.TestThree listOfTestThree = (com.app.shared.appinsight.health.TestThree) query.getSingleResult();
        Log.out.println("AISHI324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestThreeRepositoryImpl", "findById", "Total Records Fetched = " + listOfTestThree);
        return listOfTestThree;
    }
}
