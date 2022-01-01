package org.hust.model.Bike;

import lombok.Getter;
import lombok.ToString;
import org.bson.Document;
import org.hust.utils.Utils;

@Getter
@ToString
public class EBike extends Bike {
    private int battery;
    private int usageTime;

    @Override
    protected EBike documentToBike(Document document) {
        EBike result = new EBike();

        result = Utils.documentToObject(document,EBike.class);
        result.usageTime = result.getUsageTime();
        return result;
    }

    private int getUsageTime(){
        return this.battery * 2;
    }
}
