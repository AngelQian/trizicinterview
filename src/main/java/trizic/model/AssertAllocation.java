package trizic.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by AngelQian on 5/4/18.
 */

@Entity
public class AssertAllocation implements Serializable{
    public String symbol;
    public double percentage;
}
