package com.app.server.service.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.appinsight.health.TestThree;
import java.util.List;
import com.athena.framework.server.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "shweta.zagade1209@gmail.com", updatedBy = "shweta.zagade1209@gmail.com", versionNumber = "4", comments = "Service for TestThree Transaction table", complexity = Complexity.MEDIUM)
public abstract class TestThreeService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(TestThree entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<TestThree> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(TestThree entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<TestThree> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByGen(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByAddressId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
