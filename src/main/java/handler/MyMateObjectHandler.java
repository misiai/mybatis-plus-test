package handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyMateObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill方法");
        // 先判断条件是否为空，若是则填充数据
        Object password = getFieldValByName("password", metaObject);
        if (password == null) {
            this.strictInsertFill(metaObject, "password", Object.class, "99999");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
