package com.app.shared.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.app.shared.organization.locationmanagement.Address;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.spartan.pluggable.exception.security.InvalidDataException;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_TestThree_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "4", comments = "TestThree", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "TestThree.findByGen", query = "select e from TestThree e where e.systemInfo.activeStatus=1 and e.gen=:gen"), @javax.persistence.NamedQuery(name = "TestThree.findByAddressId", query = "select e from TestThree e where e.systemInfo.activeStatus=1 and e.address.addressId=:addressId"), @javax.persistence.NamedQuery(name = "TestThree.findById", query = "select e from TestThree e where e.systemInfo.activeStatus=1 and e.tttid =:tttid") })
public class TestThree implements Serializable, CommonEntityInterface, Comparator<TestThree> {

    @Column(name = "ttnm")
    @JsonProperty("ttnm")
    @NotNull
    @Size(min = 1, max = 256)
    private String ttnm;

    @Column(name = "nnno")
    @JsonProperty("nnno")
    @NotNull
    @Size(min = 1, max = 256)
    private String nnno;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "tttid")
    @JsonProperty("tttid")
    @GeneratedValue(generator = "UUIDGenerator")
    private String tttid;

    @Column(name = "gen")
    @JsonProperty("gen")
    private String gen;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "addd", referencedColumnName = "addressId")
    private Address address;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getTtnm() {
        return ttnm;
    }

    public void setTtnm(String _ttnm) {
        if (_ttnm != null) {
            this.ttnm = _ttnm;
        }
    }

    public String getNnno() {
        return nnno;
    }

    public void setNnno(String _nnno) {
        if (_nnno != null) {
            this.nnno = _nnno;
        }
    }

    public String getPrimaryKey() {
        return tttid;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return tttid;
    }

    public String getTttid() {
        return tttid;
    }

    public void setTttid(String _tttid) {
        this.tttid = _tttid;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String _gen) {
        this.gen = _gen;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address _address) {
        this.address = _address;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws InvalidDataException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new com.spartan.pluggable.exception.security.InvalidDataException();
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(TestThree object1, TestThree object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((ttnm == null ? " " : ttnm));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (tttid == null) {
            return super.hashCode();
        } else {
            return tttid.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appinsight.health.TestThree other = (com.app.shared.appinsight.health.TestThree) obj;
            if (tttid == null) {
                return false;
            } else if (!tttid.equals(other.tttid)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}