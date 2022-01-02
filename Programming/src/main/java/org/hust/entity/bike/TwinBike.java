package org.hust.entity.bike;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.hust.utils.Utils;

/**
 * TwinBike class specifies twin bike type which is built for two cyclists, extended from Bike abstract class
 *
 * @author hoang.lh194766
 */
@Getter
@NoArgsConstructor
public class TwinBike extends Bike{
    public TwinBike( String model, boolean status, double speed, String color,
                      double weight, String description, int value, String barcode, String imgUrl){
        super(model, status, speed,color,weight,description,value, barcode,imgUrl);
        this.bikeType = TwinBike.class;
    }

    /**
     * check Bike.documentToBike for more information
     * @param document the document needed to be cast
     * @return TwinBike
     */
    @Override
    public TwinBike documentToBike(Document document) {
        return Utils.documentToObject(document,TwinBike.class);
    }

    @Override
    public double priceCoefficient() {
        return 1.5f;
    }
}
