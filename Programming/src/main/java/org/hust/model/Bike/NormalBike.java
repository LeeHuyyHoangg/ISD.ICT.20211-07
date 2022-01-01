package org.hust.model.Bike;


import lombok.Getter;
import lombok.ToString;
import org.bson.Document;
import org.hust.utils.Utils;

@Getter
@ToString
public class NormalBike extends Bike {
    @Override
    protected NormalBike documentToBike(Document document) {
        return Utils.documentToObject(document,NormalBike.class);
    }
}