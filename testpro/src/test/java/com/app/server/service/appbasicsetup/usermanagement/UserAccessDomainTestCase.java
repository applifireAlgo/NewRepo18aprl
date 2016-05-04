package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

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

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            String logManagerId = LogManagerFactory.createMultiLogManager(_path + "/");
            LogManager Log = LogManagerFactory.getInstance(logManagerId);
            System.setProperty("LOGGER_ID", logManagerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("Jl7bUadlYyDr6BYN2xrbb89inzznVYajV5ZQaYcUb1XwcumF2H");
        useraccessdomain.setDomainName("P8omyYnG7j5CMgZTfa84WVEsuPCzRvAQVK4fQ0nq3Es2r7Ahy8");
        useraccessdomain.setDomainDescription("iKO7uFzOZntNHqcM3r4PU8MGpSFaKPiwToAA5XojKH6gUfMaeF");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("r8D8nMahkzKWHDHBT4k8XXzVNxVt5Iwm1dhg0IrkEyck7wLdcR");
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
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainHelp("TGB6PYYaU3GSxzDuZ0304sg1l0Td1Dl8iPzmVcIIOfgKGD40GG");
            useraccessdomain.setDomainName("631oic4aQAqONcU9hUTLighzRoto6ruWoj6KJGi2suorY4FV5D");
            useraccessdomain.setDomainDescription("9nODRg320JLHAx4YrTK3rNnY7NzcDrqvAIZDztwTXbqyC5ERYK");
            useraccessdomain.setUserAccessDomain(22854);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("HD9ZGrjtMJp5ejrrasz6H7IfC9raiCDG0f7OBWknoKqXLMOmjJ");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 121208));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "sSNhLkpzd8Ph6wSCCsIIQWevPJQfNgQwopuN63SVxJAXHSKMQbzI7p0NZ1soWcMd8NAtne8ZLC12Jd8erzu6j21vEwvFMgK6yny5cyL2qviAqCnzeSbgAZlnA8JWPz0Pj7cf7iBNGElYr6jQwFQTNRciiBXhkXOWeGWeK492ov5CSwfU6QUpFMlBKVhh1u3pyxctKU2WNs5mEGcPEJaNGPhko4wNVt4sYdAK9veIIfpQNDu7hMRVbX38nTvB6V5Fp"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "POHS9WByJRtPjADzBqPbaVcLyk5EWhMILCQb5OfWUALOUMghF0WQ7to9DbZHwtJWgW19yrcXOYuyA4PdezshvTDoQOzTvL8I8cbqOWI7bd5xPLVOOE9w3BkZ9mS8DFhbbGsNSXEVZwWRE4ih17xdcVCJGFO6c82iVYRFYYgLliZiBA9HViM8PoxxiT5tjNWvh5GUOC6ShprkhjctpaLPrcutj2upIWNdJ5HemgdCVt29NLoKLjjA4QMFF6ByUQr0Z"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "nvqiyBa3j5L6KhZpjfhiyP5Dfxe3gfR9muQf2GixLUgewpWpHkXub0tIEMiYN3s3SqbB6nLg1UM5TXcCkT3fHIO9ieF0R1ZIBBbHmNZYHNjIoSZat8UGtQYTu2XlHjdEmDI2QBu37QigUPkXSm48RIICj29g2yQMVNgC3hkEGzwZVE76x7K8Rkfk4xRTXxSiI6cxzXRavLXFAGythYj08zlgEKPIp1NsGCyOIQ6HBxWZoPduttOwsHpeaxrWI3BBv"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
