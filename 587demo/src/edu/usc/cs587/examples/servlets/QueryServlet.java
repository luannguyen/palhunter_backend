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
			String resultString = "";
			List<UserMessage> ret = handler.retrieveAllRecords();
			if (ret.size() == 0) {
				
			}else {
				Gson gson = new Gson();
				resultString = gson.toJson(ret);
			}
			
			System.out.println(resultString);
			out.write(resultString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
