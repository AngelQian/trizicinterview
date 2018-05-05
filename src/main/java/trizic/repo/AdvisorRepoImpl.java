package trizic.repo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import trizic.model.Advisor;

import java.util.Date;
import java.util.List;

/**
 * Created by AngelQian on 5/4/18.
 */
public class AdvisorRepoImpl implements AdvisorRepo{

    private ApplicationContext ctx = new AnnotationConfigApplicationContext(mongoCfg.class);
    private MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");

    public List<Advisor> find(String advisorId, int page, int size)  throws Exception{
        Query query = new Query();
        if(advisorId != null && !advisorId.trim().isEmpty()){
            query.addCriteria(Criteria.where("advisorId").is(advisorId));
        }
        if(page > 0 && size > 0){
            query.skip((page-1)*size);
            query.limit(size);

        }
        List<Advisor> listUser = mongoOperation.find(query, Advisor.class);
        return listUser;
    }

    public Advisor save(Advisor newUser)  throws Exception{
        Query searchUserQuery = new Query(Criteria.where("name").is(newUser.getName()));
        int cp = Integer.valueOf(newUser.getCashHoldingPercentage());
        int aa = Integer.valueOf(newUser.getDriftPercentage());
        if(cp + aa != 100)
            throw new Exception("Unexpected Element");

        Advisor exist = mongoOperation.findOne(searchUserQuery, Advisor.class);
        if (exist != null) {
            if (!exist.equals(newUser)) {
                Update update = new Update();
                update.set("description", newUser.getDescription());
                update.set("cashHoldingPercentage", newUser.getCashHoldingPercentage());
                update.set("driftPercentage", newUser.getDriftPercentage());
                update.set("modelType", newUser.getModelType());
                update.set("rebalanceFrequency", newUser.getRebalanceFrequency());
                update.set("assetAllocations", newUser.getAssetAllocations());
                update.set("advisorId", newUser.getAdvisorId());

                mongoOperation.updateFirst(searchUserQuery, update, Advisor.class);
            }
        } else {
            newUser.setCreatedOn(new Date());
            mongoOperation.save(newUser);
        }
        return mongoOperation.findOne(searchUserQuery, Advisor.class);
    }

}
