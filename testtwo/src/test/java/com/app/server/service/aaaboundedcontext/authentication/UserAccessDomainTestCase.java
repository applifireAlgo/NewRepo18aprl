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
        useraccessdomain.setDomainName("y8vh6DAalZhoRyd68dnDlHQYsQSU5mY819KUrU3kNux6fJ6rm9");
        useraccessdomain.setDomainIcon("Wrixwe1tlbu9QJr6vtjFK3uJ0UfHjtQ0y1fEbqEAzw2NWAUSaZ");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("7HQct0LI0wBT1PBhyo3VDnFEn4gZaO3o9bElPQibup4AkNhl45");
        useraccessdomain.setDomainHelp("EjCJ3X6Z2GXACc35IQKbdtojYQbcoSmM2AlhtmtuXXD6hAn8Tc");
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
            useraccessdomain.setDomainName("MqELm2zRH3LmslR0YYgzFR9ySkVjOmAu0RN2QliwbQkXuveT7N");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("dOdnJvMwCFanG8Dh3KaODMTevsQq1HsgYEkSw3AR02PdMry6a9");
            useraccessdomain.setUserAccessDomain(41125);
            useraccessdomain.setDomainDescription("YgF36rIyCMpnI7KlatUevoPNg6l6anWZ6tBlvKX5zdZ0hRTa77");
            useraccessdomain.setDomainHelp("E7fInElWwr90ZRiue7jKfVNZWFpeSS0cvvhsVbwTV2EyGAFBFO");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 186474));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "Vau74oZohgnMF60uDFxPIVny6gfMSOJNVrRvH6Q3OWbjcR0HgxBdFu1M9FfrXjEQnqRsQz2sAkfY4RgS7XLBDitwf4PjM8bjYYtsG0GEAVVDscN31sv3NtsW3SZHCDVdRqQ51xB2r1V836nehPW0JMtX9I3O4mQ9dL4Uxv4TwQGN8XNDrWgotblS92Ok0LlcHhEVIaRSvzy2Kns6L05lnvx7Zf4DVI2iP0Wp6YlL32rdGtZ80dnsWK6Nx7RM8kjFJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "pKvsiq4D7xynqfIxY4SzxpTNj32KFtTIGt1rACZ3VkKbcSptCxBXCShhtE3HCPN5Rv1FVHDsVy5giVffkjZYb4w3u03t3rFPMN34UBxzlnTuQdM33ivZ4qEZ6UG69B3H0EoqnTGuNgzDH1MmRS5puhfP0YX9YnfhnNb5qlwgEhtqLygZYxwWeclRuyTuHmUcH1uQ3sdMwm9Vjy1oVCIQ05QmJkWtpBkfFmBhW74QxBjOlCMrizNy2za2yuOVegkwC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "PfKFVA5w7nA1a49ySiShj38S3o6VntYC3uX5RPCKxamjvYFfjOUOTQ02UqIj0Bt9dcwWpVyNYCS5Nnwhn71ZyQ1Pu1vorxsSv8tkigrZUoYDIW3vDzauhJU5hodxs0DUyO77wjOWtigqtHy1DErs0Enm5bE9Qz2RAYmRLDJCys5uZXcFTOLkOYuETgUlJKeypo9xyoFM5VT4L5rvjjAtgWkCYAVhVgAG0iOxgfjI2Nb57LgVUU1wMOh4aFzSryriP"));
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
