package com.app.server.service.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.appinsight.health.Atwo;
import java.util.List;
import com.athena.framework.server.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "", versionNumber = "1", comments = "Service for Atwo Master table Entity", complexity = Complexity.LOW)
public abstract class AtwoService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Atwo entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Atwo> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Atwo entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Atwo> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByGen(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
