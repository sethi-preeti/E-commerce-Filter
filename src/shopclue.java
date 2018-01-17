import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class shopclue {
	public static void main(String[] args)
			 throws Exception{
			
			 MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
			 Document document2=Jsoup.connect("http://www.shopclues.com/mens-clothing-jeans.html").get();
			 DB db = mongo.getDB("asas5");
			 DBCollection collection2=db.getCollection("clothes2");
			 BasicDBObject userquery=new BasicDBObject();
			 DBObject d2 = new BasicDBObject();
			ArrayList<String> imageList2=new ArrayList<String>();
			ArrayList<String> imageList3=new ArrayList<String>();
			 Elements img = document2.select("img");
			 Elements rdr = document2.select(".row a");
			 
			 try{
			 for (Element el : img) {
            	 String a = el.attr("data-img");
            	 if(!a.isEmpty())
	            	 imageList2.add(a);
	            } 
			
	         
	           for (Element el : rdr) {
                   String a = el.attr("href");
	            	 if(!a.isEmpty())
	            	 imageList3.add(a);
	            	 }
			 }catch(Exception e){}
			 
             String price2 = document2.select(".p_price").text();
		     String[] priceList2 = price2.split(" ");
		     String discount2 = document2.select(".prd_discount").text();
		     String[] discountList2 = discount2.split(" ");
		     DBObject dnew= new BasicDBObject();
			 List<DBObject> documents2 = new ArrayList<>();
			 for(int m=0, j=0, k=1;m<priceList2.length&&j<discountList2.length&&k<imageList2.size();m+=1,j=j+2,k=k+1)
			 {   dnew.put("id",j);
			 dnew.put("price",priceList2[m]);
			 dnew.put("discount", discountList2[j]);
			dnew.put("category","men");
			 dnew.put("image_src",imageList2.get(k));
			 documents2.add( (DBObject) ((BasicDBObject) dnew).copy());
			 }
			 
			 collection2.insert(documents2);
         for(int i=0; i<priceList2.length; i=i+1) {
    	System.out.println("Price ("+i+")"+priceList2[i]);
          }
	for(int i=0; i<discountList2.length; i=i+2) {
	       System.out.println(discountList2[i]);}
	System.out.println(discountList2.length);
	 for(int i=0;i<imageList2.size();i=i+1){
	     	System.out.println(imageList2.get(i));}
	 for(int i=1;i<imageList3.size();i=i+2){
     	System.out.println(imageList3.get(i));}
}}
