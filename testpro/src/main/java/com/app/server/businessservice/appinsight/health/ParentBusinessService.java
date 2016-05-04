package com.app.server.businessservice.appinsight.health;
import com.app.server.repository.appinsight.health.ParentRepository;
import com.app.shared.appinsight.health.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ParentBusinessService {

    @Autowired
    private ParentRepository parentRepository;

    public void update(Parent entity) throws Exception {
        if (entity.isHardDelete()) {
            parentRepository.delete(entity.getPid());
        } else {
            parentRepository.deleteChild(entity.getDeletedChildList());
            parentRepository.update(entity);
        }
    }

    public void update(List<Parent> entity) throws Exception {
        for (Parent _parent : entity) {
            if (_parent.isHardDelete()) {
                parentRepository.delete(_parent.getPid());
            } else {
                parentRepository.deleteChild(_parent.getDeletedChildList());
                parentRepository.update(_parent);
            }
        }
    }
}
