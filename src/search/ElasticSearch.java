package search;

import static org.elasticsearch.node.NodeBuilder.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.search.SearchResponse.*;
import org.elasticsearch.action.search.SearchType.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.FilterBuilders.*;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.node.Node;

public class ElasticSearch {
	public static Node node;
	public static Client client;
	
	public static void main(String[] args){
		long time_one = System.currentTimeMillis();
		node = nodeBuilder().node();
		client = node.client();
		SearchResponse response;
		long time_two = System.currentTimeMillis();
		for(int i = 0 ; i < 100 ; i++){
			response = client.prepareSearch("newenergy").setTypes("split").setSearchType(SearchType.DFS_QUERY_AND_FETCH).setQuery(QueryBuilders.queryString("保温 加热")).setSize(20).execute().actionGet();
		}
		long time_three = System.currentTimeMillis();
		//System.out.println(response.toString());
		long time_four = System.currentTimeMillis();
		node.close();
		long time_five = System.currentTimeMillis();
		System.out.println("Initialize time: " + (time_two - time_one));
		System.out.println("Search time: " + ((time_three - time_two)/100.0));
		System.out.println("Print time: " + (time_four - time_three));
		System.out.println("Close time: " + (time_five - time_four));
	}

}
