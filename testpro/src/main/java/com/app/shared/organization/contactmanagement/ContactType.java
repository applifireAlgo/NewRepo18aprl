package com.app.shared.organization.contactmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.spartan.pluggable.exception.security.InvalidDataException;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_ContactType_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "", versionNumber = "1", comments = "ContactType", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "ContactType.DefaultFinders", query = "select e from ContactType e where e.systemInfo.activeStatus=1 and e.contactType LIKE :contactType"), @javax.persistence.NamedQuery(name = "ContactType.findById", query = "select e from ContactType e where e.systemInfo.activeStatus=1 and e.contactTypeId =:contactTypeId") })
public class ContactType implements Serializable, CommonEntityInterface, Comparator<ContactType> {

    @Column(name = "contactType")
    @JsonProperty("contactType")
    @NotNull
    @Size(min = 0, max = 128)
    private String contactType;

    @Column(name = "contactTypeDesc")
    @JsonProperty("contactTypeDesc")
    @Size(min = 0, max = 256)
    private String contactTypeDesc;

    @Column(name = "contactTypeIcon")
    @JsonProperty("contactTypeIcon")
    @Size(min = 0, max = 128)
    private String contactTypeIcon;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "contactTypeId")
    @JsonProperty("contactTypeId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String contactTypeId;

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

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String _contactType) {
        if (_contactType != null) {
            this.contactType = _contactType;
        }
    }

    public String getContactTypeDesc() {
        return contactTypeDesc;
    }

    public void setContactTypeDesc(String _contactTypeDesc) {
        this.contactTypeDesc = _contactTypeDesc;
    }

    public String getContactTypeIcon() {
        return contactTypeIcon;
    }

    public void setContactTypeIcon(String _contactTypeIcon) {
        this.contactTypeIcon = _contactTypeIcon;
    }

    public String getPrimaryKey() {
        return contactTypeId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return contactTypeId;
    }

    public String getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(String _contactTypeId) {
        this.contactTypeId = _contactTypeId;
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
    public int compare(ContactType object1, ContactType object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((contactType == null ? " " : contactType));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (contactTypeId == null) {
            return super.hashCode();
        } else {
            return contactTypeId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.contactmanagement.ContactType other = (com.app.shared.organization.contactmanagement.ContactType) obj;
            if (contactTypeId == null) {
                return false;
            } else if (!contactTypeId.equals(other.contactTypeId)) {
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
