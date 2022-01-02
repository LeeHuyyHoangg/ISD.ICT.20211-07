package org.hust.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.image.Image;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.entity.bike.Bike;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * @author nguyenlm Contains helper functions
 */
public class Utils {

    public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static Logger LOGGER = getLogger(Utils.class.getName());
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
    }

    public static Logger getLogger(String className) {
        return Logger.getLogger(className);
    }

    public static String getCurrencyFormat(int num) {
        Locale vietname = new Locale("vi", "VN");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
        return defaultFormat.format(num);
    }

    /**
     * Return a {@link java.lang.String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
     *
     * @author hieudm
     * @return the current time as {@link java.lang.String String}.
     */
    public static String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Return a {@link java.lang.String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @author hieudm vnpay
     * @param message - plain text as {@link java.lang.String String}.
     * @return cipher text as {@link java.lang.String String}.
     */
    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
    }

    /**
     * Return a {object of generic type T} that where casted from input document
     * the fields in the object which not exist in the document will have initial value
     *
     * @author hoang.lh194766
     * @param document - the document needed to cast.
     * @param objectClass - the class of the object needed to be transfer to
     * @return cipher text as {@link java.lang.String String}.
     */
    @SneakyThrows
    public static <T> T documentToObject(Document document, Class objectClass){
        String id = document.get("_id").toString();
        document.put("_id", id);
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(Bike.class, new Bike.BikeDeserializer());
        mapper.registerModule(module);
        final String json = document.toJson();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return (T) mapper.readValue(json, objectClass);
    }

    /**
     * Return document that where casted from a object
     * this function was created for the purpose of casting station/bike to document
     * to be saved to the Database
     *
     * @author hoang.lh194766
     * @param object the object needed to be cast
     * @return {@Link org.bson.Document Document}
     */
    public static Document objectToDocument(Object object){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(object);
            Document document = Document.parse(json);
            document.put("_id", new ObjectId(object.getClass().getField("_id").get(object).toString()));
            return document;
        } catch (NoSuchFieldException | IllegalAccessException | JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return a image of the url
     * @param source - the url of the image
     * @return Image
     */
    @SneakyThrows
    public static Image getImageFromUrl(String source) {
        URL url = new URL(source);
        Image image = new Image(url.openStream());
        return image;
    }

}