package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("vMj5peHyhrk8fJPiZDUVvrxvx7mv8ldWJt9YMjejlYkpIkuLJ1");
        useraccessdomain.setDomainIcon("A2MPJpZg3kcy4tnPGBH2iGiZYtcIzCuABSk4D61G5eBYD7b4Er");
        useraccessdomain.setDomainName("6pvpREw0yjfOp3OeyiRGIM04Af4JQaCz0KGeoErzdRROp65zXC");
        useraccessdomain.setDomainDescription("YpVar9fNePGZlzzMhMAYkfoWak4D2MoqBZKz79lsBIIaX78TOr");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(68254);
            useraccessdomain.setDomainHelp("0ixTYXhYrp5lQaSGwggRUpmHUpAgddUH4dgeW8Rwy7ubqwHmmE");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("MkPKnMpVt2cK0Nu64mv3vQKxTvpgq6gQ7l2rszLwvdcAy1oKUK");
            useraccessdomain.setDomainName("4oA8Hvh5zCaPuoLsiAOh6MYnHumF0GQI9vwyMGjBVmoySVObSr");
            useraccessdomain.setDomainDescription("g3YGlnRAb9MNIplvIZXWHGrLiRUmz0Bxmi2OTWDONWOspEYusD");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 137644));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "uXrMiFemr4VmuIgRnncT0YkAdvMXdU0lDuxxaSLRieiDSmPjlvkO1yFNUT4p4nzDEjtHypyWTgC52VTzE5U858nYNDftAdoTPxzMEsNsN5r7Fh3D7296iRM7Fr3ira1ZS2IokJ8HvYdi2dft5UnCLzwzZkxO1WERd9Q7rUdjpc679HZkUI97UKUqZkqaToYsdZCvLL7XOs5YPweqkFr8IAV4uPBnKG1risFkWGbXusTCkb2qxcoTGDTlmX2zvOdaf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "7RgVXBWFdo2ZL46oKbnfgc1P0pnzXFX4rVPpqbGWgGSIOBWbgQjrD1bffZYNVeCy92ShuQ5zWLnCD5ni4b8BWilU0xaGs4vNe2fafuqpodWFKuR67tOwxNMNiRKmqjTuBpeyNvbQ3uOFEMcJUJr5GjGIevxphEmEnGqg9ggOkvXDkECWWr3nLg55D4Uw3t9jCUK0jeJ77I7sqltzQanbdK26yrfiKYi7h9IBFPivdi1FdHkWEYPk6HBKTUAiOrdsV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "AMALbgR0SRiXVZMdzLsAIkmtczOmjNEjFqIf9tXR1tEKqO1ETHGG9acnNqurQchaU10dEiQD9Au1w9rM5u35JBrNPX8Jy4naSo9Gwc4u6qakn3UGaQRMUdmMDXVsreL2seZydhPBTh3IWC1ku6bGgyakZp5UsNOFViNFRtRCHqNIvr6OKUTNEvDheOpbvHV1qYCfBnUEas0u8O0EVaTpMmgTrgJKu1D7de1m8FF25SsCInv4ejYbUuYwo7RU82CLo"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
