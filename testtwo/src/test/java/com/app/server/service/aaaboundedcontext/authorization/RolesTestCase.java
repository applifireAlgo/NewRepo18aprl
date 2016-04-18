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
import com.app.server.repository.aaaboundedcontext.authorization.RolesRepository;
import com.app.shared.aaaboundedcontext.authorization.Roles;
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
import com.app.shared.aaaboundedcontext.authorization.RoleMenuBridge;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Roles roles = new Roles();
        roles.setRoleName("XNknWKGBXvffB6jaZw20uCRIcxLrucRGMcBogj1vOh7y1bbfw3");
        roles.setRoleIcon("CmlHxM2R1d0ndSK0jMZIKM90zinKtSFpLCkDh8aY1fvRrnhqWv");
        roles.setRoleDescription("WR3h3XKLYBA7oxMPX8CnPveZ268ZMLFZmmsoObaPyKvcqIoDM0");
        roles.setRoleHelp("YFuGFJL1LGvj0yVoDdfhcaZYWgYZG8Xs3UF4bjAAaXT3CadWGr");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("RNgboFqrEFaWafa52OIWctcZJUKljn1EI0cmFlaSRqaMznayJM");
        appmenus.setMenuIcon("pweOoePSrEXzL6U9hLVPQauGpsYC5EOliR87U5RwdNJ0uZks7W");
        appmenus.setRefObjectId("Qp5M1sZGAC8LHoCd8bVN6py0LeRihK8L071oels4gzCGvNsmYR");
        appmenus.setAppType(1);
        appmenus.setMenuLabel("NfZRjrtZQtXUJ4SDuXw6HMbdPygb6jvokeLtHUHKbhQN9e7wES");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuHead(true);
        appmenus.setUiType("sEq");
        appmenus.setMenuAction("5ClC19NFWMpAbMyb8jCbvf1KZXYGaIjO1Y83KlZnxvVHkMY2lt");
        appmenus.setAutoSave(true);
        appmenus.setMenuAccessRights(4);
        appmenus.setMenuTreeId("yKc30Ih2tsyh56edOx96QCasxP4ieEFhYdsz8yo3mnU1TrsizK");
        appmenus.setAppId("4nxR3ciYE51rUNczaV8EecHX5j2NhfH3dvzrzMOet1JUqvqmEJ");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleName("so2PA0JcPyjOd0QWe7aMjJICv3h4aAKWS1aNIrwwdvQOtb2EDY");
            roles.setRoleIcon("BKcx9UKZsIeTHZhZ4nz0dBMlJTjgh2X7vunG3pviRPwnarwVcd");
            roles.setVersionId(1);
            roles.setRoleDescription("PSPWDvCSUjUCq7RNTydpmJSO1iNIgNebBDeW5Uv3iAGZ1lI0K9");
            roles.setRoleHelp("eDTg5kJgpuv2Zcb5bXsEFqF5GcSgmrqpv8u6Gnmp9AAw5iNwNg");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "HZZEDAVz01Eexzsui7IrhJ8TAIRLvnhDhGBePlXBxdw8jlA5lnnKADx9TJ6K8O0fIW2uIRJ8rkMETQkpvpBNlsPqMUd8aU97bEiItOIHKrr0CP3MtyCrhekRyAsnTeeRPQeDEhbZtwB4Cim0ufScKSj9gm6xqqgtuf6f8GXmZAzE1wgClXCA8W9EUZdtXTE6HHxtCM8inVKBRUpCjy6c2jzts0qZXGFtXwM1uDr4v8DaC6ATsMG72be1dayKkVqLi"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "5Z1m4fvpuzHX8C6tgGRMZui9TXEaCZcLIyYpI3fS2wo6aEfhRE83vAsiKsLnfrARnk3wpK7UOZ5UEjNQKFmlB5Yy9qMzmJcaBjmktAeduIBPYzPExho6tqPnJtgRu4zXXg3AyAUFKdhrMJVSRqbEFWrLGDtqUNZvSxfZ0hTSImzlf83OyKpAh9J2vLt06AafNM8krwUsn1geypEwF3Myd6G4cmJjLUgLElNHz4OXo1aD3NdI2rDq1163K2dXCIryz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "ZCgUP5TQMhtnvupSgDH1mFjtkOS919Tlx5EEIYk8oQPmx1wKVhpnXouO8SxjMNsmB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "zNSQGeXj3s7QV6Recw0UO2Wh1i0z7MibAiJ4hnHRRfSdJzqvJlYcZ50BsaObQKEdFp7nrVRX4BmmPSiD4YGbXfBZHPETn2YRLYJy9L3DsJ5gS4qsVLc8bgccHx4i8HTiE4DwMg6ftgxpx2LY7HHiQDcOdsL5gkE8aNTlmAkJnX6VG2of9ix3J57Djiv32P0fTpMyNyw3bpWjZK3bsmvSS6KgjeGsEH9xiksOSYBkgGtyPcDQ3xsBxnh5PtwtitxKg"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
