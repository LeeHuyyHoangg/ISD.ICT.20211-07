package org.hust.entity.bike;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.hust.utils.Utils;

/**
 * NormalBike class specifies normal bike type, extended from Bike abstract class
 *
 * @author hoang.lh194766
 */
@Getter
@NoArgsConstructor()
public class NormalBike extends Bike {

    public NormalBike(String model, boolean status, double speed, String color,
                      double weight, String description, int value, String barcode, String imgUrl) {
        super(model, status, speed, color, weight, description, value, barcode, imgUrl);
        this.bikeType = NormalBike.class;
    }

    /**
     * @param document the document needed to be cast
     * @return NormalBike
     * @see org.hust.entity.bike.Bike#documentToBike(Document)
     */
    @Override
    public NormalBike documentToBike(Document document) {
        return Utils.documentToObject(document, NormalBike.class);
    }

    @Override
    public double getPriceCoefficient() {
        return 1;
    }

    @Override
    public String getBikeType() {
        return "Bike";
    }
}