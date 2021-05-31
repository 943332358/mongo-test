package org.yx.mongotest.gridFs;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yangxin
 */
@Component
public class GridFsService {

    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * 获取默认的客户端logo
     */
    public GridFSFile getDefaultLogo() {
        return Optional.ofNullable(gridFsTemplate.findOne(Query.query(Criteria.where("metadata.default").is(true))))
                .orElseThrow(() -> new RuntimeException("Default file not found"));
    }
}
