package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("3VYa1BMcXVHuZaWaoVgcHVwmvWHYudBD3w5P1XRNQHjwsIFnwt");
        appmenus.setMenuIcon("Ypz3XyscoQecvdpVcoIZji1dfZO4OVTmnt7Q4vfkFQKCRBqGVb");
        appmenus.setRefObjectId("PrvGoczwNyukqT8JQgYANMcdk9QXpGWVQWzjCvWUd2cmgJalmY");
        appmenus.setAppType(1);
        appmenus.setMenuLabel("YOdbIn41LhAjex3SYjIC9vm0uKpiNVZGK1OEOa7gDN7ybRPJz0");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuHead(true);
        appmenus.setUiType("Hsc");
        appmenus.setMenuAction("BtC79o3yuOpd7CwOFAZvuZkz9ooqKtDMdH8QX2SqH3cHopI5Wu");
        appmenus.setAutoSave(true);
        appmenus.setMenuAccessRights(11);
        appmenus.setMenuTreeId("PmPVCSDurAtPA7ALkcL0KhiqPYCmnA34ckjONCKQZpNqtMeXRU");
        appmenus.setAppId("gOdubshepKsZ1LfkA1Kc4GHv6W3nFgtnAEGok9VR8qm9BamtME");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuCommands("kSHOpZO4iLz3d3EznROJTYDDKBDaGW08CgNs52qT3PF8MpSYZR");
            appmenus.setMenuIcon("mFRn3HtfTA9I6MtCqu9kWqWlersuCA2mYLHprQqXwaWBxe3LEp");
            appmenus.setRefObjectId("TbXFyJihAxWWTqun9Bn9WXsc9G2WorkC1vCsKqrKf5gW30CDFN");
            appmenus.setAppType(2);
            appmenus.setMenuLabel("qoabKT2enDj3Cnp3ff1REkpik51knWhuBRmiStlCRtk4UsTE8I");
            appmenus.setVersionId(1);
            appmenus.setUiType("wy2");
            appmenus.setMenuAction("P2XPCU64B5dka9yir89O2ydR7fSk8KGXdcsbNNqZTBRrIoFzDl");
            appmenus.setMenuAccessRights(11);
            appmenus.setMenuTreeId("h6gevRq8PcEEMX1BrvbbxXW4Hd82dTXMj8hAS2ATEokZOQzB8Y");
            appmenus.setAppId("xlfYrnU7LlOLLZx1J7fU9KpnZ8R7huToF2zQ1GJ53iZSdCRwWL");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "OX1IgzZzBHmPlYUMcMoMszRjEmC1vPOHbbNX8bjjdEStw7j2dIMzIGKh5bh992j8amLxNECUSUqOTjGSDRpjEnOvEnkacpnD4pyzqczTHu2bZ6IZO53bliuqtb5tCgKLz7M7FWjEsYqws5G1hcvlKgFHJk4gidnLyZ7o9K0kCcByFPFFwjPaB2X6sup43HqaWWUKB8j7Fp5zjoNQnxWSBcXB4LdP1gpSCplvPVGqncO0d70xcIsbhkWHrFak5Zj97"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "Me5yOS16TydqYwIs2HLxR7Y1v1Q2D7SfAfiN6ItGKnkJHuz18e2euMKfymdpQVJP3QkHJefeIQzp4Mz3CJyiPpsd6Z5j9isNV8Zv1hE8FKccpwT2TMuqLqeUu1KLMdSLJ00wlEL0Qhjb2DmwHZuYjm13ggmUPxLiNft1Wgv8Y3gJM4twGJdRfFbTU0nuuN4CKeBaxLRSdMZaKMqMTipwVhkJv3jQhnMAKuAqWmfzzsQlXCzrxYQv2vfZxLBzcvSnV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "EQQlmS7Qc7gJW1MOmpPzasyzHm2MmJ3hvWQRfo0XUsHUIrM0igRmtHSrmkGyrw849ELpWAs0wy1NzEceW2WoWRbPeYpdxqpZiN95UkFzRZjegIWGdq9cCTA2jN6ppMqRTODVX7dgZ0LuWorKpgmkuGvDHtU4JLHt2LdwKHb5d9JkgN3nOI2agJRWulGpQaWJJqLFsHPUt5PWkIowPwAfpgx1XR585iDyFLEcnlban6kzVLbrkcz80sbrNXlMOcgHM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "CWEw2L7NolDQQ9ajOoZcZNQTfbOqVJFaD9W4tn5ddaZHigAe80DzMcffx0W3ZPns6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "w68FInMWZ5RHlH8ie6sZKonplNK3CAqDIuy9GBpunYTbMX1ce0yZdVNTLVuB29iCTDmsBvkp2JVuTeLYp9TgT7ob4GpAuPt4H0eNL8OXciSKVMnCqbhLIhl1T5bU8jn6PrtdIXw5n8ZsepBVrkzuw1zT3PgsQJk6PX1bePZKkpQIIzMtx4JcczkzbZAjqodd1vqrw9HAXFIQRYMbSHRK0UKpsT3OWesbIXv0MxM6TDOzritfuhbRKi93ZbGU87Baw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "fdiM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "tQjDYmXqbpuArxMkNH8yN9SyWcfY49Opz63YXdZ8ROalXTSouBe0p45sACGAv2kq1JPysMTUP9LhwDIvIy6KvGynZ10Zi2jD0SBobLC2LeXTFjV173KN6wKQNDpuMNT9GtOwE7aMx6sKMTwItGXUMSc0Rl9xv6oY1NuOrJ01yTcv1AQAr5fbarLLlZI741NConOY0GBjwhrHHXm19eGHRO1KXPuhgwW0yt82kCKfXNZ3zmMh5o8LdcpPoyPuRx3sT"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "Va3rjkg5PavtS5LCq5aXFxQpsx9ZX9XdFUlUnkaILUYgDvv1RVNDeKhQn8TJVjCzH49MJZRyVHbjiZZ65IH38yGFMzjuenKLIea1l2eQuvPkc1HBiMIRp2PHPy1AUtofLKxDtGLgo3Jrc4L7ERbBW9Zy2YEMWGIWG8jPImO4L365cfMignNFke8ak7LubMR7mCOertl3YU2vtTy9a0cmB5uIZqCVrJrqQLtFLXeRP6iBY3xq4Zlj4aQbF1yapEyZV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
