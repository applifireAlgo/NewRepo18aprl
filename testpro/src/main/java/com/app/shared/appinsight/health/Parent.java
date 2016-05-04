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
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.spartan.pluggable.exception.security.InvalidDataException;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Parent_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "2", comments = "Parent", complexity = Complexity.LOW)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "pid")
@NamedQueries({ @javax.persistence.NamedQuery(name = "Parent.findById", query = "select e from Parent e where e.systemInfo.activeStatus=1 and e.pid =:pid") })
public class Parent implements Serializable, CommonEntityInterface, Comparator<Parent> {

    @Column(name = "pName")
    @JsonProperty("pName")
    @NotNull
    @Size(min = 1, max = 256)
    private String pName;

    @Column(name = "age")
    @JsonProperty("age")
    @NotNull
    @Min(-2147483648L)
    @Max(2147483647)
    private Integer age;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "pid")
    @JsonProperty("pid")
    @GeneratedValue(generator = "UUIDGenerator")
    private String pid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Child> child;

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

    public String getpName() {
        return pName;
    }

    public void setpName(String _pName) {
        if (_pName != null) {
            this.pName = _pName;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer _age) {
        if (_age != null) {
            this.age = _age;
        }
    }

    public String getPrimaryKey() {
        return pid;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String _pid) {
        this.pid = _pid;
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

    public Parent addChild(Child _child) {
        if (this.child == null) {
            this.child = new java.util.ArrayList<com.app.shared.appinsight.health.Child>();
        }
        if (_child != null) {
            _child.setParent(this);
            this.child.add(_child);
        }
        return this;
    }

    public Parent removeChild(Child _child) {
        if (this.child != null) {
            this.child.remove(_child);
        }
        return this;
    }

    public Parent addAllChild(List<Child> _child) {
        if (this.child == null) {
            this.child = new java.util.ArrayList<com.app.shared.appinsight.health.Child>();
        }
        if (_child != null) {
            for (int i = 0; i < _child.size(); i++) {
                _child.get(i).setParent(this);
            }
            this.child.addAll(_child);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfChild() {
        if (this.child != null) {
            return this.child.size();
        }
        return 0;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> _child) {
        for (int i = 0; i < _child.size(); i++) {
            if (_child.get(i).getParent() == null) {
                _child.get(i).setParent(this);
            }
        }
        this.child = _child;
    }

    @JsonIgnore
    public List<Child> getDeletedChildList() {
        List<Child> childToRemove = new java.util.ArrayList<Child>();
        for (Child _child : child) {
            if (_child.isHardDelete()) {
                childToRemove.add(_child);
            }
        }
        child.removeAll(childToRemove);
        return childToRemove;
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
        setValidatorchild(_validateFactory);
    }

    private void setValidatorchild(EntityValidatorHelper<Object> _validateFactory) {
        for (int i = 0; i < child.size(); i++) {
            child.get(i).setEntityValidator(_validateFactory);
        }
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
        if (this.child == null) {
            this.child = new java.util.ArrayList<Child>();
        }
        for (Child _child : child) {
            _child.setEntityAudit(customerId, userId, recordType);
            _child.setSystemInformation(recordType);
        }
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
        if (this.child == null) {
            this.child = new java.util.ArrayList<Child>();
        }
        for (Child _child : child) {
            _child.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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
    public int compare(Parent object1, Parent object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((pName == null ? " " : pName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (pid == null) {
            return super.hashCode();
        } else {
            return pid.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appinsight.health.Parent other = (com.app.shared.appinsight.health.Parent) obj;
            if (pid == null) {
                return false;
            } else if (!pid.equals(other.pid)) {
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
