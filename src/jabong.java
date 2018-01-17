//import java.net.URL;
import java.util.ArrayList;
import java.util.*;


//import javax.servlet.RequestDispatcher;

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




public class jabong {
	
	public jabong(){
		
	}
	public static void main(String[] args)
			 throws Exception{
		String url = "https://www.jabong.com/men/clothing/denims/jeans";
			 Document document=Jsoup.connect(url).get();
			 //MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
			
			
			 /*DB db = mongo.getDB("newdb2");
			 DBCollection collection= db.getCollection("clothes");
			 
			 BasicDBObject userquery=new BasicDBObject();
			 DBObject d1 = new BasicDBObject();
			 DBObject d2 = new BasicDBObject(); */
			 
			 ArrayList<String> imageList=new ArrayList<String>();
			 Elements img = document.select("img");
			 
			  for (Element el : img) {
				 String a = el.attr("data-img-config");
            	 String[] params = a.split(",");
            	 String baseLength = params[params.length-1];
                 try{
            	 String value = baseLength.substring(13);
                 String source = value.substring(0,value.lastIndexOf("\""));
                 String src = source+".jpg";
                 imageList.add(src);
              
               }
                 
                 catch(StringIndexOutOfBoundsException e){
                	 System.err.println(e);
                 }
                }
			  
			 //}
			 for(int j=1;j<imageList.size();j++)
				 System.out.println(imageList.get(j));
			 String price=document.select(".standard-price").text();
			 String[] priceList=price.split(" ");
			 String discount=document.select(".discount").text();
			 String[] discountList=discount.split(" ");
			 
			 ArrayList<Integer> dis=new ArrayList<Integer>();
			 for(int i=0;i<discountList.length;i++){
				 String act_dis=discountList[i].substring(2, 4);
				 dis.add(Integer.parseInt(act_dis));
			 }
			 
			 for(int i=0;i<dis.size();i++)
				 System.out.println(dis.get(i));
			 
			 
		/*	 DBObject dnew= new BasicDBObject();
			 
			
			 List<DBObject> documents = new ArrayList<>();
			
			 for(int i=1, j=1,k=1;i<priceList.length&&j<discountList.length&&k<img.size();i+=2,j=j+1,k=k+1)
			 {   dnew.put("id",j);
			 dnew.put("price",priceList[i]);
			 dnew.put("discount", discountList[j]);
			 dnew.put("url","www.jabong.com/men/clothing/denims/jeans/");
			 dnew.put("category","jeans:men");
			 dnew.put("image_src",imageList.get(k));
			 documents.add( (DBObject) ((BasicDBObject) dnew).copy());}
			 collection.insert(documents); */
			/* for( int i=1;i<priceList.length;i=i+2){

				 System.out.println(priceList[i]);}
			
			 for(int j=0;j<discountList.length;j++)
	System.out.println("Discount("+j+")"+discountList[j]);*/
	
	

}}