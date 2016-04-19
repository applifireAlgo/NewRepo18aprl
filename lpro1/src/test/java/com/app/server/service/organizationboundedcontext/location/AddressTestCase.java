package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Address;
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
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        City city = new City();
        city.setCityCode(1);
        city.setCityLongitude(4);
        city.setCityLatitude(7);
        State state = new State();
        state.setStateDescription("5FCOUhE4aPX6ODq9IxPuCKTMS6r4mhqS4eEkerrIj7GgD7BoFk");
        state.setStateCode(1);
        state.setStateCapital("iv3krENAYyqnCAoAx6zap8gJ76lSKEW88jlzv5Qy8h5DKxrSve");
        state.setStateName("Qt1uMjBdJ0ubVkfpuMMf1jWd2ZuLG7pH5bZBRyccpvUF5YERfE");
        state.setStateCodeChar2("9pcxdnB2zIw5UUbyDvYZnOgi0AMU8wUZ");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("iuBYVLWrpOiMCEdS1zvWkmTcEMD62k0G");
        state.setStateCapitalLatitude(9);
        Country country = new Country();
        country.setCurrencyName("1i9aIYBFuA1jv662qlKeFoWEpQHWwdf7ThNB0TWY2wDylHHkl8");
        country.setCountryCode1("xjw");
        country.setIsoNumeric(869);
        country.setCountryName("XZm1fk7R7Ve0RMoGSNCekGgACctdh40BLIzxqODbuOmdoauTKM");
        country.setCapital("ktWht3TPSYeKdbf1OJvOZvXjy5CkqMLc");
        country.setCapitalLatitude(2);
        country.setCurrencySymbol("9OYWkH6TRUuODfXW55ws4mN33FIsYgVw");
        country.setCurrencyCode("6sv");
        country.setCapitalLongitude(6);
        country.setCountryFlag("XlegKKU1hZlkWT1KQ8hSn4xA8cclJX01h89ciYUDIkmrrrDvf9");
        country.setCountryCode2("lpt");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateDescription("Ji4JNspUAHndFQvp7vanCVfgk8ufmqmZ1zGzfSg915x98xPeS7");
        state.setStateCode(1);
        state.setStateCapital("lByUOp30zKNQdsyO7cyqOyCyDFpmD0DoM5jo9IzxVo49Y06uYP");
        state.setStateName("lcJQr2qLQ7J7WqSZUSU6O3aCZ49hAvQnjqHWB0Ld1xAwCQLkHH");
        state.setStateCodeChar2("l05J7E3OtSGXmEnvW44GZ6EoSJ26S2M9");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("eP8HEWnOovck155cOpg3b9nV5iTyUi2R");
        state.setStateCapitalLatitude(9);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("bThWcNwHrAfMtt6Fj7NZDdUfU3MBVIhSU80FsjdSShPgCR4OWv");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCode(3);
        city.setCityLongitude(10);
        city.setCityLatitude(11);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("0GCpP3PkEEWR8HGdHphZWFjnDHjCES1cA5txx816l5qLSI27eA");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("s1xYOyaemmauVtPuIP9IBP1wz5IEM9Z9dJpPWIyZdQW0IveOaE");
        city.setCityDescription("9mGAwdF4Lmedz8ntl6jtMIszvPJqv76ZQpdthleVhKr6NGbeLt");
        city.setCityCodeChar2("0UdmVCDAHjNJEpLo2FviYyrrIOBJhb9I");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("vNlXSaLnPQy5RHS4UZA6xOVmGxHletUp9kollgQdX8A7P17fz1");
        addresstype.setAddressType("Q5cqqfDU12Yvtgf7s7pdoVHdq0S2fnudo4tsZp8Ju6V6RfG2iN");
        addresstype.setAddressTypeDesc("mFMIvOwqw5NT3EEDNqYPLwNIrRseTQX1leP2vA3hltbQF8EiIP");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setLongitude("bwXmcfBhbhR5PDL2tBwKez6KxCqwbRugWjvaAlcpiqSxHeRNmj");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("FNVJmSSuc30hFuF4t52lGO89kN2trIdFdk8UTNmkCSRNoUKA6N");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("f1fjPi");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setLatitude("WrdpktutrfxrPDjOzxrGLH1wXDLxwQzm3qm8HTtsZDRifmdHB8");
        address.setAddress1("AQ8rjvZp9N1TTM9RGWczwv0cXQRLoU6eaVPDGcEbKRZ2nGM88t");
        address.setAddressLabel("CRFKKtqBzQM");
        address.setAddress3("7qD405LUsU4mjfocgW4Zc29aYO37vDHNPtRSlBZP2wToHzyqep");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setLongitude("8WBIA1zBY18s8ZzXxtSNylBxJxBdf7JNitsogIvtNBsb6AQ7Or");
            address.setAddress2("UtE273Kg3J0dcGWaOpv5crcaIoBQvsiUQu86R59F6sIyqR6CJv");
            address.setZipcode("ieZhIS");
            address.setLatitude("svdGmkILCI0NYE1jAfS78c8D8ebp4rtzolRIatVm1M4JJ2w63u");
            address.setAddress1("BQLIeFfj08fr78GIBX61uZTUkX5QR1HzD8UqGfq4GDFDEneMH6");
            address.setAddressLabel("LQ03meiVEEF");
            address.setAddress3("NmpmWvKgxlzFXf3vCzzGTo2blFZzavIl373lQhumyDQpCJGT37");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "ZKHMofRY0peV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "itegRE3rnsiQTQH5ZL5uSZ9uWxPWfXGEgpWPtZW2OCmNc899od8eis3uh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "zee6FAToWIOpFvdXyYIeOCcXIM58ZNrur46KTaGVqK4GkColgBfxHlDUY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "ksRyb6LL9MTckGETFXzSLUVgyLhnPaR63XohJ3doUdyQ3LhHYuaooYR0u"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "QH8EJKP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "0uIZhhxf9b97dM2oQ6lug1ovK8hKiOyrq94S9DVJ34qrhOhA91zwMEoDpHJxVums2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "0AuiGFmK0iZvhKbGX1nCBvKOtD0rWo6ht8KB7klPDo3sYl5G5ZLLnYZeQJkogiLg8"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
