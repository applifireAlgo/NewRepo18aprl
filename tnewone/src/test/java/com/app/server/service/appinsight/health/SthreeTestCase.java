package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.SthreeRepository;
import com.app.shared.appinsight.health.Sthree;
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
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SthreeTestCase extends EntityTestCriteria {

    @Autowired
    private SthreeRepository<Sthree> sthreeRepository;

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

    private Sthree createSthree(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Address address = new Address();
        address.setAddress3("R8JeamG3pmYLgBffjhPKJ9Dnph6NOp8nmiEhzGm8Zc7IiOb56n");
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("6NLjKnQdZ1jEwa42BB8YEvmoDwbrOjGFISLFHJ2yZ8I1WbERBb");
        addresstype.setAddressTypeDesc("Pk77yisCcQfBl3D3y8mqVxg4DR0saHNPMgItWAsCJQpk95xQhz");
        addresstype.setAddressTypeIcon("7DLkTDd3oF4JKDm37JpfmeOgEqBtnOhJcq1uluF26t21AZ2maw");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCode(2);
        state.setStateName("oCaLdsVafEq4LJAt54jiXDfA9bidVphpsKPCeuUIB3H4TE2VW4");
        state.setStateCodeChar3("Ror5sqox9FKZtYvIUqQWE1ewK7Xyooks");
        state.setStateCapital("2yzsua1IodekXgvSf0K8j4ZyaVd8hKQ3vj60Ydv5WEGXLHQ1OX");
        state.setStateCapitalLongitude(8);
        state.setStateFlag("zfKXSWGnYLccsOlYsb7XGtMjs6Uyd6MIuQE4Flgw4KIcwPwN8L");
        state.setStateCodeChar2("1cbyIKUXVxVQvkJnKL3d9QyG0gVoR7Vg");
        Country country = new Country();
        country.setCapital("za9Bhlib8Xjf9VUTCzBEfqbJ9OVPwteP");
        country.setCurrencyCode("P5T");
        country.setCountryName("hEk9Mf9NzNITAlbYfSPLWAhc9gcRbEZN20h2PaPoI9tRIi8Q6A");
        country.setCurrencySymbol("QMQ3GFfMsSNOOad1Rv194RkjceWoMKjV");
        country.setCountryCode2("C2a");
        country.setCurrencyName("w73F8yOe4U3yVayMkK7O6lWS0ozUMx5rx8uNmybzLyvFTo5GIG");
        country.setCapitalLatitude(7);
        country.setIsoNumeric(63);
        country.setCountryFlag("95pLBiUVxZf3VhIsNLkeZgsYNXuLszKTKlUgnfM4rw9Wmfx6Og");
        country.setCapitalLongitude(8);
        country.setCountryCode1("LOc");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateName("m2fsJc7cnvMyu0UTpaXMjTNRyUWFnTFgZ9zr9IujFPdhnCffAN");
        state.setStateCodeChar3("TIa0xVPqX5qlMBgkOh2kP2eyGqYFunqX");
        state.setStateCapital("BawH7dRNwZpBhQc9VtCVFVPe7N4A3uYgwDUvTQX7saiuFKSMUs");
        state.setStateCapitalLongitude(8);
        state.setStateFlag("P9r4dP9IjmJpxMTUsN5ljhbMyemzKXtk7Vt2ptQeuiwCyBK9BH");
        state.setStateCodeChar2("qPSmVTFGIiZo1AwDVJn6ziq6vsqN5yd8");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(7);
        state.setStateDescription("8Pwm1YJBKbVaPxgtHDTnwB5wAyv1GsCPGko5vOjctT7SBHuFLC");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityFlag("PS78Ie3gw3Jrt6yAsSUYBaCwsYPKSEzNLb7hhBBmlzist4RSeg");
        city.setCityDescription("ewvLgLDlgGaLSJlpYHPvsbKqmqUMrW0bmPscyW05sT1CLrW7Gs");
        city.setCityLatitude(4);
        city.setCityName("AzLj69runUoOgmyX83C2VbAdBuoVNKQ4ugtOpxccyH9zY3X3CJ");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(7);
        city.setCityCodeChar2("pglvx262UfMf0eJUIetvDcD8J4kv4nk4");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress3("yyarcNVr6Drrn9P3d6EH87u8Vv5JD1OUsTyg52INo2hlFpSke9");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("UBJEs34U7Jo");
        address.setAddress1("2nCdXjFOcVHkGpFYqGiQ4ioIyRYwqXDv6o0W65yRgcbpcAx50L");
        address.setLongitude("eQBVUwamw2XRpBFQ4y5eTbBFuFyn9GmpNKPP2564Kcog7Iyo23");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("4mco32");
        address.setAddress2("7OIcQ9yPGCrgeX1s1hO7WLfFChBDyMSqKozDEvELO2exHBCNFc");
        address.setLatitude("mVFQiqerG3fyTXpyYacZqCwsG4PgtEMXvHjpmZsGvrePa8ecKO");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        Sthree sthree = new Sthree();
        address.setAddressId(null);
        sthree.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        sthree.setAnm("nEFKOE5oHQLLg2ILsfGWbCGXsQV8kKwtyyny8Ww13weOUoKVpF");
        sthree.setEntityValidator(entityValidator);
        return sthree;
    }

    @Test
    public void test1Save() {
        try {
            Sthree sthree = createSthree(true);
            sthree.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            sthree.isValid();
            sthreeRepository.save(sthree);
            map.put("SthreePrimaryKey", sthree._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("SthreePrimaryKey"));
            Sthree sthree = sthreeRepository.findById((java.lang.String) map.get("SthreePrimaryKey"));
            sthree.setAnm("odYh38KAy4GU87Um5lY8uHEGASEuJgBSjX9T7pionOxdRG1cin");
            sthree.setVersionId(1);
            sthree.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            sthreeRepository.update(sthree);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SthreePrimaryKey"));
            sthreeRepository.findById((java.lang.String) map.get("SthreePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("SthreePrimaryKey"));
            sthreeRepository.delete((java.lang.String) map.get("SthreePrimaryKey")); /* Deleting refrenced data */
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateSthree(EntityTestCriteria contraints, Sthree sthree) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            sthree.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            sthree.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            sthree.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            sthreeRepository.save(sthree);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "anm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "anm", "rg7cLbp43GZtovQSyeZAbLgWRKGKOPzisuU7sv3kh5IaplDGZExSwqEDlLJRE8nTUCwBGdXmCi3qrxXSGLX958j2CBRk1SQKLQMJOMdynFXcrgYXG7qXFL79auVUejvz7Id3qhXFaXA0417BfZQdPMkEXdqMol0VwRJPtEDOnGAgIc7i6qY8rBOeF6j6hPX5WZbvCP6X2Ay7no3flmfVinw7MmRkUGrfElpf7WSssAY80f7AQYzIbAIrJBNNMJULB"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Sthree sthree = createSthree(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = sthree.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(sthree, null);
                        validateSthree(contraints, sthree);
                        failureCount++;
                        break;
                    case 2:
                        sthree.setAnm(contraints.getNegativeValue().toString());
                        validateSthree(contraints, sthree);
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
