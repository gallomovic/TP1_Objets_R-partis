import com.google.protobuf.Empty;
import com.leuville.grpc.simple.HightunesGrpc;
import com.leuville.grpc.simple.HightunesGrpc.HightunesBlockingStub;
import com.leuville.grpc.simple.Service.HelloReply;
import com.leuville.grpc.simple.Service.HelloRequest;
import com.leuville.grpc.simple.Service.QuantiteReply;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class Client {

	private ManagedChannel channel;
	protected HightunesBlockingStub blockingStub;
	
	public Client(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		initStubs(channel);
	}
	
	protected void initStubs(ManagedChannel channel) {
		blockingStub = HightunesGrpc.newBlockingStub(channel);
	}

	public String sayHello(String name) {
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply reply = blockingStub.sayHello(request);
		return reply.getMessage();
	}
	
	public int quantiteArticles() {
		QuantiteReply reply = blockingStub.quantiteArticles(null);
		return reply.getMessage();
	}
		
	public static void main(String[] args) {
		Client client = new Client("localhost", 1664);
		
		System.out.println("Attente saisie clavier");
		System.out.println(client.sayHello("Grpc"));
		System.out.println(client.quantiteArticles());
		System.out.println("Fin");
	}

}
