package com.app.shared.appinsight.health;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_SFour_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "shweta.zagade@algorhythm.co.in", updatedBy = "shweta.zagade@algorhythm.co.in", versionNumber = "6", comments = "SFour", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SFour.findBySds", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.sds=:sds"), @javax.persistence.NamedQuery(name = "SFour.findBySffdf", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.sffdf=:sffdf"), @javax.persistence.NamedQuery(name = "SFour.findBySdfs", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.sdfs=:sdfs"), @javax.persistence.NamedQuery(name = "SFour.findByFs", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.fs=:fs"), @javax.persistence.NamedQuery(name = "SFour.findByDfd", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.dfd=:dfd"), @javax.persistence.NamedQuery(name = "SFour.findById", query = "select e from SFour e where e.systemInfo.activeStatus=1 and e.sFourid =:sFourid") })
public class SFour implements Serializable, CommonEntityInterface, Comparator<SFour> {

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "sFourid")
    @JsonProperty("sFourid")
    @GeneratedValue(generator = "UUIDGenerator")
    private String sFourid;

    @Column(name = "sds")
    @JsonProperty("sds")
    private String sds;

    @Column(name = "sffdf")
    @JsonProperty("sffdf")
    private String sffdf;

    @Column(name = "sdfs")
    @JsonProperty("sdfs")
    private String sdfs;

    @Column(name = "fs")
    @JsonProperty("fs")
    private String fs;

    @Column(name = "dfd")
    @JsonProperty("dfd")
    private String dfd;

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

    public String getPrimaryKey() {
        return sFourid;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return sFourid;
    }

    public String getsFourid() {
        return sFourid;
    }

    public void setsFourid(String _sFourid) {
        this.sFourid = _sFourid;
    }

    public String getSds() {
        return sds;
    }

    public void setSds(String _sds) {
        this.sds = _sds;
    }

    public String getSffdf() {
        return sffdf;
    }

    public void setSffdf(String _sffdf) {
        this.sffdf = _sffdf;
    }

    public String getSdfs() {
        return sdfs;
    }

    public void setSdfs(String _sdfs) {
        this.sdfs = _sdfs;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String _fs) {
        this.fs = _fs;
    }

    public String getDfd() {
        return dfd;
    }

    public void setDfd(String _dfd) {
        this.dfd = _dfd;
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
    public boolean isValid() throws SpartanConstraintViolationException, SpartanIncorrectDataException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new SpartanIncorrectDataException("Entity validator is not set");
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
    public int compare(SFour object1, SFour object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (sFourid == null) {
            return super.hashCode();
        } else {
            return sFourid.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appinsight.health.SFour other = (com.app.shared.appinsight.health.SFour) obj;
            if (sFourid == null) {
                return false;
            } else if (!sFourid.equals(other.sFourid)) {
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