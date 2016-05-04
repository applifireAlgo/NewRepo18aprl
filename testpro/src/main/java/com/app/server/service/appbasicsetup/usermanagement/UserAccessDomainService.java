package com.app.server.service.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import java.util.List;
import com.athena.framework.server.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "", versionNumber = "1", comments = "Service for UserAccessDomain Master table Entity", complexity = Complexity.LOW)
public abstract class UserAccessDomainService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(UserAccessDomain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<UserAccessDomain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(UserAccessDomain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<UserAccessDomain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
