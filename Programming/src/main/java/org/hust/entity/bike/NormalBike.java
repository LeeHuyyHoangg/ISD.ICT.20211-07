package org.hust.entity.bike;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.hust.utils.Utils;

@Getter
@NoArgsConstructor()
public class NormalBike extends Bike {

    public NormalBike( String model, boolean status, double speed, String color,
                double weight, String description, int value, String barcode, String imgUrl){
        super(model, status, speed,color,weight,description,value, barcode,imgUrl);
        this.bikeType = NormalBike.class;
    }
    /**
     * check Bike.documentToBike for more information
     * @param document the document needed to be cast
     * @return NormalBike
     */
    @Override
    public NormalBike documentToBike(Document document) {
        return Utils.documentToObject(document,NormalBike.class);
    }
}