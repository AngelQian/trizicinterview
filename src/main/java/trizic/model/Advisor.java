package trizic.model;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AngelQian on 5/4/18.
 */
@Document(collection = "advisor")
@Entity
public class Advisor implements Serializable {

    public static enum ModelType{QUALIFIED, TAXABLE};
    public static enum RebalanceFrequency{MONTHLY, QUARTERLY, SEMI_ANNUAL, ANNUAL};

    @Id
    @GeneratedValue
    private String guid;

    private String name;
    private String description;
    private int cashHoldingPercentage;
    private int driftPercentage;
    private ModelType modelType;
    private RebalanceFrequency rebalanceFrequency;

//    @OneToMany(mappedBy = "account")
private ArrayList<AssertAllocation> assetAllocations;

    private String advisorId;

//    @CreatedDate
    private Date createdOn;

    public Advisor(){}

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCashHoldingPercentage() {
        return cashHoldingPercentage;
    }

    public void setCashHoldingPercentage(int cashHoldingPercentage) {
        this.cashHoldingPercentage = cashHoldingPercentage;
    }

    public int getDriftPercentage() {
        return driftPercentage;
    }

    public void setDriftPercentage(int driftPercentage) {
        this.driftPercentage = driftPercentage;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) throws Exception {
        try {
            this.modelType = ModelType.valueOf(modelType);
        } catch (IllegalArgumentException ex) {
            throw new ValueException("Value is not valid , model type : "+modelType);
        }
    }

    public RebalanceFrequency getRebalanceFrequency() {
        return rebalanceFrequency;
    }

    public void setRebalanceFrequency(String rebalanceFrequency) {
        try {
            this.rebalanceFrequency = RebalanceFrequency.valueOf(rebalanceFrequency);
        } catch (IllegalArgumentException ex) {
            throw new ValueException("Value is not valid , model type : "+rebalanceFrequency);
        }
    }

    public ArrayList<AssertAllocation> getAssetAllocations() {
        return assetAllocations;
    }

    public void setAssetAllocations(ArrayList<AssertAllocation> assetAllocations) {
        this.assetAllocations = assetAllocations;
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append(name).toString();
    }

    public boolean equals(Advisor ad){
        if(this.name != null && !this.name.equals(ad.name))
            return false;
        if(this.description != null && !this.description.equals(ad.description))
            return false;
        if(this.cashHoldingPercentage != ad.cashHoldingPercentage)
            return false;
        if(this.driftPercentage != ad.driftPercentage)
            return false;
        if(this.modelType != null && !this.modelType.equals(ad.modelType))
            return false;
        if(this.rebalanceFrequency != null && !this.rebalanceFrequency.equals(ad.rebalanceFrequency))
            return false;
        if(this.assetAllocations != null && !this.assetAllocations.equals(ad.assetAllocations))
            return false;
        if(this.advisorId != null && !this.advisorId.equals(ad.advisorId))
            return false;
        return true;
    }
}
