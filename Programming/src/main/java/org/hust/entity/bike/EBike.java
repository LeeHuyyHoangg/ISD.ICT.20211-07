package org.hust.entity.bike;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.hust.utils.Utils;

/**
 * EBike class specifies electronic bike, extended from Bike abstract class
 *
 * @author hoang.lh194766
 */
@Getter
@NoArgsConstructor
public class EBike extends Bike {
    private int battery;
    private int usageTime;

    public EBike(String model, boolean status, double speed, String color,
                      double weight, String description, int value, String barcode, String imgUrl,
                 int battery){
        super(model, status, speed,color,weight,description,value, barcode,imgUrl);
        this.battery = battery;
        this.usageTime = this.getUsageTime();
        this.bikeType = EBike.class;
    }

    /**
     * check Bike.documentToBike for more information
     * @param document the document needed to be cast
     * @return EBike
     */
    @Override
    public EBike documentToBike(Document document) {

        EBike result = Utils.documentToObject(document,EBike.class);
        result.usageTime = result.getUsageTime();
        return result;
    }

    @Override
    public double priceCoefficient() {
        return 1.5f;
    }

    public int getUsageTime(){
        return this.battery * 2;
    }
}
