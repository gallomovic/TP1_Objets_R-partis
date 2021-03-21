import serverSide.Article;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.protobuf.Empty;
import com.leuville.grpc.simple.HightunesGrpc.HightunesImplBase;
import com.leuville.grpc.simple.Service.HelloReply;
import com.leuville.grpc.simple.Service.HelloRequest;
import com.leuville.grpc.simple.Service.QuantiteReply;

import io.grpc.stub.StreamObserver;

public class MyService extends HightunesImplBase {
	

	private Map<Integer,Article> catalogue;
	private int nextKey = 0;

	public MyService() {
		
		this.catalogue = new HashMap<>();
		int quantite = new Random().nextInt();

		for(int i = 0; i < quantite; i++) {
			Article n = new Article("text");
			this.catalogue.put(nextKey, n);
			nextKey++;
		}
		
	}
	
	public Map<Integer,Article> getCatalogue(){
		return this.catalogue;
	}
	
	public void addToCatalogue(Article a) {
		this.catalogue.put(nextKey, a);
		nextKey++;
	}
	
	public int getArticleQuantite(Article a) {
		return(this.catalogue.size());
	}
  
	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		String name = request.getName();
		System.out.println("MyService.sayHello " + name);
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + name).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void quantiteArticles(Empty request, StreamObserver<QuantiteReply> responseObserver) {
		int n= this.catalogue.size();
		System.out.println("MyService.QuantiteArticles " + n);
		QuantiteReply reply = QuantiteReply.newBuilder().setMessage(this.catalogue.size()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
