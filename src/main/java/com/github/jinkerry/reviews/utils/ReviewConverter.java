package com.github.jinkerry.reviews.utils;


import com.github.jinkerry.reviews.meta.Review;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: jinfeng
 * Date: 14-2-25
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
public class ReviewConverter{

    private static String filename = "src/main/resources/review.xml";

    public static void convertToXML(Review review){

        FileWriter writer = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Review.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //remove xml first line
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            String xmlHeader =  "<?xml version='1.0' encoding='UTF-8'?>";

            writer = new FileWriter(filename);
            writer.write(xmlHeader);
            writer.write("\r\n");
            marshaller.marshal(review, writer);

            //System.out.print(xmlHeader);
            //marshaller.marshal(review, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Review convert(String filename){
        Review review= null;
        try {
            JAXBContext context = JAXBContext.newInstance(Review.class);
            Unmarshaller unMashaller = context.createUnmarshaller();

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader(filename));
            review = (Review)unMashaller.unmarshal(streamReader);

            return review;

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static Review generateReview(){
        String id = "http://api.douban.com/movie/subject/1424406";
        String content = "渡边唯一的仁慈，是让小鬼艾德带走了小狗...";
        String value = "4";
        String title = "终点之后";

        Review re = new Review(id, content, value, title);

        return re;
    }



    public static void main(String[] argv) {
        Review re = generateReview();
        //ReviewConverter.convertToXML(re);
        ReviewConverter.convert(filename);

    }


}
