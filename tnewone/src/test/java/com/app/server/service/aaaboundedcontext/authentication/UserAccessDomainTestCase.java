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
        useraccessdomain.setDomainDescription("fNJaY1TTaLIiL3oW70wq3paOSQaeRFtZf0yHFJ9YMpCp4Uk61N");
        useraccessdomain.setDomainName("dMu8aazYowSrFUSaO11fTBhyJNAO9vmQyWUQIeH8kiVPNunKTS");
        useraccessdomain.setDomainHelp("qIIl9kQ0rYAwr9kBDWwpaGkufhdpNrwXeaVAnsDaePGTRtLU1X");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("diJMiyaoNkh7MNnGxM3o0Uxbnpd65LOyybhthVAJ7a6jJqtrwg");
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
            useraccessdomain.setDomainDescription("X7iGy53VMIsZaZLlpHpcRrPzyJDAWsKKKwZIx35q1r4xZ15vRp");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("UOH19SiBTdMOw0DTCrOFE7zzLuqV9tUp73UwwRJKUN6LpCE2pt");
            useraccessdomain.setDomainHelp("tMA29RAz5w9u7btJKuIdKUQxqKkRP7ESshdhiVDLzFVe3wMGdm");
            useraccessdomain.setUserAccessDomain(67274);
            useraccessdomain.setDomainIcon("2TVgH7NkTMhKKot025ek0ZayogESqPRfz07ZeiduMcy4M4xGQ3");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 160187));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "ulvdbIrbQ3fQ3vNhWljCTLiRSNUcE2DWaBNVgMUiSZC5erQkXQyf7gMEA7M8rdkTxF9Rid6GucyjOXTAX54YQLE4Wil7KaI4xh19a28JAIJq4GDkZseVVXwhD1RJhUjUxYYoCs6BsJ5U8MwSib0A8tLgPe3dRb695qEV7LmNyqBfRTXkUcmBqcKIrq0ZE8REOLbvUSfK93oSFCNPyHqyWBL4NEhjFAosmHuO3umRd2kCx3IHiMjeakLUSdSJZAPny"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "Ag2AajpQYLR9DEipxU40sJG9W75qbg77NrBT71qBQRtHI0M4L7apwFW1LC4KS766wfQWrijG438nrtEyUGEAxxrR3PjzFVo0BHSYLONOZyOj39v9EiaFGsBqwBAzF0l4SHGQYFEfDG71wJ0YC4obP1QRoSLSJnbheZ4azdzEhPSv5ipwm1bDnuzxkLkMpTS45IqRpwvxFWSLjY1CKySBjYBdy2vsp0ggxFeCJPkzcKfRB51oSYQ2CUMLDdDZAPDv2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "Mr7XjkXbzzlmktmypZa5mxyOVmb81xoGNvtgT8AUfuyC22fauhWs8Jwj0Z7scGTTiKo57OPTAIuMNryEBme6zN24BayPuIVfqhkCFa1TBYST2XGMktjEkGCklF2Ps703WIJOyvTpdvSobCeZdZjxpQlnqnqXrDZDOlNLhG8DgThaqG2Y7pAM14fJI9mazXyvyC8UIrTcWQnezjgI5RWMcw8ORO8DcltWVMG1D1JaJiABKEr959vjwN3UmzlJ7HzaOAlwRyrEgDu9Tb4gczh0JkGpwdpHjWYMdd5m1hlu6ys5aEGNQNMMmBLVRhhYXf2HYrS1b16HPJVAVI5oPYWWElvhrOLKX1LZlUkQcYMCjsps0K0pwptz4hHdoLlUHczYLArPL1CJXSU6Wvt8ruj1RQrDPKpZ0O5HDa5ecDOmWnSQDrQBX9zpxO5oN1F5ZrDwlfK0XbTIKLRCevQz18re7YcwYqyr38nJdoH3lUQ5tnQnacbaxpSFrFKGQBQlwViCsnCKbcSz8RoEXaDSb9UrK7rIicmE2JTrDeCT9hMoplVTNKXAfA8tJXCC9FWO0eHSOpSLo4JayWk0jLsXSs412STyD76BUpmPF0MTuIyBY29jEAJfeKYyvQtB4CDUDLxrEUXp08EhQ3jfuvZVBGmbDs6jemppC6MwuUkyrNIToymNMo1R3h5rt7NPaq5l93jIGPXTRnDdsN3nlymRK9k7OBGNdkkqHlpIGR4TV4q5z0aXnVcqcYDvYJGNnsYeineXkMTn39BjtGr9ilMCEUQiNi22qhKqr5uzYgM8VKPNjneo5cwWWv6X8Z5mhXrcwEuRX81imb0AvtD9NIpTwsIrAYen96F1ozhtYDcG1uf2lkAoq9JGRlIRxKejWNWziMOcjCGyCoGx8pZFldzAPNuBBuwKKWVtYuEefJqGYogpokurBk9zyE0GkmoyJBnmxqN6YGuKYBozkRFw2MXysQtnsrIzQRKUdQY5ctFR0RxF5z6bavmv1I9PFPNioQgA0bXTcJZCjNveJQbTULIMn4PhCPVx7sBhrYVgs0nM1kKhiOOUzIlBqi93vMGSZfocFL7D9eTgsMTPQiwXl2KTTYyMlNH3fokqP3Mh7euLIwZlYsB33PCS37YZNT66JJtzzhWAkSIUrPTthSyRDLhkZxFn3IiAbQgUV1KF5qV9d3uqBFxYhhLxsKDtICmJSoL9cOtODUBRJdq0wVf9ZYzJJHGineFTLt6evfIoUxA5m2tzO0Qx1Slhm6ScIuLMAg1VrlC7mqI8lUOPOum2vyZyMctwjAWgF9eyOZ4sKEhDq6cvVi9xFSC6NnnlniTmJILCURPCMLHivd2HfmHf3vmIlmSCZtrWj1cXGxijPdqmf06VFI2c5OAULicOvwXenShAJK4dJqE4UETBTPida6Sv1qjtFYmWxsaYbF2r4nIBk5m3zvtoM6ZHuKMq0Rg0KXxc4vnDHxkA7FiKyVn5q8pNeM1QPSr1KyuVX2cJJbvfI7XXet4CjWd6J1fw9uQJBnHvxfYlK3LnH4F4wvG7pNOapPib4HkUiYsVU7nDuNHtMXpKelYZi85Nkk1g0I6cFkJOBoiNOF1GxOIUkR7YHsPIVHwJYIazUtntFtzv01GFR6vkP0DrDaV3LSanvrDTX1QZ0HPqm6BfFOBTyr57GkCON3KVpuLx8oT4fvQ2vpIdKWmYbcNFppxTWECGK28RfVKjYxdhG7hH47KtsI3Yje9MSjWNsF3FrE2f9mvw7yOU3aeqK0bmo58nZzg4Fs0lqPxfY9n44iN5d611br8CUihFoNEiV8NPq9jkzExLOxi4EHS0eNab6GEGZLUKBc7AmySQeSH4JQReaFpItOmwRPSqSfCnEQNA6iCYGZ2NdULuWR1Da4aQfhZDHjLcFU43IBdrTQfJeOVRov2D4gA0vu2tbf05WEnDDg3Gi4QeGUBcVPnwqMu8y85ndoTPNhkuikSoiBhn9wgyGXOcGirLajZrznHfGnzD43K917hhVOHsZBeeQNbgvlURE1m860zwuTyyWX5mU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "XqYEvE4dHhF2Mzfo07TYN2Ak2lsM7EeWrMZMV7ExDZOqgBBHaOyJz6wEeHwz3mEF2RCaNaeEnv4LT7vSPm4XRwjm6n97sGH3JTXHTewXgm5mZmSUMhQI24Y000EHlQPf86dZlmnpTeAF1FCrwZfm8JDdQ2UwGZv3Fz88JLDwjq44fn9Xa4g7ZGP7fivUYFXRs2rdvAe8YTNzz6frK6Eby8SaX4Oo2PAiOwGHNrSgWlmIsZidAPYJwV5nM0GGamuss"));
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
