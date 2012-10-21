/**
 * 
 */
package edu.usc.cs587.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.usc.cs587.examples.dbhandlers.DatabaseHandler;
import edu.usc.cs587.examples.objects.UserMessage;

/**
 * @author Ling
 *
 */
public class QueryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4221334610986805006L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	protected void doRequest(HttpServletRequest req, HttpServletResponse response) {
		
		try {
			DatabaseHandler handler = new DatabaseHandler();
//			response.setContentType("application/string");
//			response.setContentType("text/x-json;charset=UTF-8");           
			response.setContentType("text/html;charset=UTF-8");           
	        response.setHeader("Cache-Control", "no-cache");
	        PrintWriter out = response.getWriter();
	        
	        String action = req.getParameter("action");
	        
	        if(action.compareTo("insertPeople")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?id=2&first_name=yuwei&last_name=tan&created_time=1347774599449&action=insertPeople
				String first_name = req.getParameter("first_name");
				String last_name = req.getParameter("last_name");
				String pid = req.getParameter("id");
				int id = Integer.parseInt(pid);
				long created_date = Long.parseLong(req.getParameter("created_time"));
				System.out.println(created_date);
				handler.insertPeopleToDB(id, first_name, last_name,created_date);
	        } else if (action.compareTo("addFriend")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?action=addFriend&pid1=1&pid2=4
	        	String pid1 = req.getParameter("pid1");
	        	String pid2 = req.getParameter("pid2");
	        	boolean success = handler.addFriend(pid1,pid2);
	        	if(success)
	        		out.write("Successfully insert!");
	        	else
	        		out.write("Failed to insert!");
	        } else if (action.compareTo("queryPeopleId")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?id=4&action=queryPeopleId
	        	String pid = req.getParameter("id");
	        	String result = handler.queryPeople(pid);
	        	out.write(result);
	        } else if (action.compareTo("queryPastLocations")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?id=4&action=queryPastLocations
	        	String pid = req.getParameter("id");
	        	String result = handler.queryPastLocations(pid);
	        	out.write(result);
	        }else if (action.compareTo("findAllFriends")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?id=12&action=findAllFriends
	        	String pid = req.getParameter("id");
	        	String result = handler.findAllFriends(pid);
	        	out.write(result);
	        }else if (action.compareTo("findAllNonFriends")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?id=12&action=findAllNonFriends
	        	String pid = req.getParameter("id");
	        	String result = handler.findAllNonFriends(pid);
	        	out.write(result);
	        }else if (action.compareTo("findAllPeople")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?action=findAllPeople
	        	String result = handler.findAllPeople();
	        	out.write(result);
	        }else if (action.compareTo("queryPeopleName")==0){
	        	//example: http://localhost:8080/587demo/QueryServlet?first_name=yuwei&last_name=tan&action=queryPeopleName
				String first_name = req.getParameter("first_name");
				String last_name = req.getParameter("last_name");
	        	String result = handler.queryPeopleName(first_name,last_name);
	        	out.write(result);
	        } 
			
			
			String resultString = "";
			//List<UserMessage> ret = handler.retrieveAllRecords();
//			if (ret.size() == 0) {
//				
//			}else {
//				Gson gson = new Gson();
//				resultString = gson.toJson(ret);
//			}
			
			//System.out.println("heello world");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
