package gianni.provaMongoDB;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		String uri = "mongodb://localhost:27017/";
		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("prova");
			MongoCollection<Document> collection = database.getCollection("utenti");

			// trova document con nome lucia
			Document doc = collection.find(eq("nome", "Gianni")).first();
			if (doc != null) {
				System.out.println("caso FIRST" + doc.toJson());
			} else {
				System.out.println("No matching documents found.");
			}

			// estrae tutti i document con età diversa da 33
			FindIterable<Document> doc1 = collection.find(ne("eta", 33));

			if (!doc1.cursor().hasNext()) {
				System.out.println("No matching documents found FindIterable.");
			}

			for (Document doct : doc1) {
				System.out.println("caso senza AND" + doct.toJson());
			}

			// estrae tutti i document con età diversa da 33
			FindIterable<Document> doc2 = collection.find(and(eq("nome", "Gianni"), ne("eta", 40)));

			if (!doc2.cursor().hasNext()) {
				System.out.println("No matching documents found FindIterable AND.");
			}

			for (Document doct : doc2) {
				System.out.println("caso AND" + doct.toJson()); 
			}

			// inserisce document
			// collection.insertOne(new Document()
			// .append("nome", "Gianni")
			// .append("cognome", "Lucifera")
			// .append("eta", 37)
			// .append("sesso", "M"));
		}
	}

}
