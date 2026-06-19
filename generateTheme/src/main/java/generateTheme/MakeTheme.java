package generateTheme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/MakeTheme")
public class MakeTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Getデータ受け取り
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		String historyFile = getServletContext().getRealPath("/WEB-INF/history.csv");
		String favoriteFile = getServletContext().getRealPath("/WEB-INF/favorite.csv");
		List<String> savedTheme = new LinkedList<String>();
		List<String> favoriteTheme = new LinkedList<String>();
		
		
		if ( action.equals( "history" ) ) {
			
			savedTheme = readDocument( historyFile );
			
			request.setAttribute( "savedTheme", savedTheme );
	        
	        RequestDispatcher rd = request.getRequestDispatcher( "/history.jsp" );
	        rd.forward( request, response );
	        
			
		} else {
			
			favoriteTheme = readDocument( favoriteFile );
			
			request.setAttribute( "favoriteTheme", favoriteTheme );
	        
	        RequestDispatcher rd = request.getRequestDispatcher( "/favorite.jsp" );
	        rd.forward( request, response );
	        
			
		}
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Random random = new Random();
		
		
		// Postデータ受け取り
		request.setCharacterEncoding("UTF-8");
		String g1 = request.getParameter("difficulty");
		
		
		// ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
		// 　　　　　お題を生成
		// ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
		
		//CSVのお題一覧読み込み
		String fileName = getServletContext().getRealPath("/WEB-INF/data/themeList.csv");
		File fn = new File(fileName);
		BufferedReader br = null;
		List<String[]> themeList = new ArrayList<String[]>();
		
		
		if ( fn.exists() ) {
        	
        	try {
        		
        		br = new BufferedReader( new FileReader( fn ) );
        	
        		String line;
        		while (( line = br.readLine()) != null ) {
        		
        			if ( line.contains(",") ) {
        				String[] themes = line.split(",");
        				themeList.add(themes);
        			}	
        		
        		}
        		
        	} catch ( IOException e ) {
        		e.printStackTrace();
        		
        	} finally {
        		
        		if ( br != null ) {
        			try {
        				br.close();
        			} catch ( IOException e ) {
        				e.printStackTrace();
        			}
        			
        		}
        		
        	}	
        	
        }
		
		
		// お題内容を抽選
		String[] chosenTheme = new String[ themeList.size() ];
		int dif = Integer.parseInt(g1);
		
		for ( int i = 0; i < themeList.size(); i ++ ) {
        	chosenTheme[i] = null;
        }
        
        int level = 0;
        for ( int i = 0; i < themeList.size(); i ++ ) {
        	
        	int r = random.nextInt( ( themeList.get( i ) ).length );
        	chosenTheme[i] = ( themeList.get( i ) )[ r ];
        	level ++;
        	
        	if ( dif == 1 && level >= 2 ) {
        		break;
        	} else if ( dif == 2 && level >= 4 ) {
        		break;
        	} else if ( dif == 3 && level >= 5 ) {
        		break;
        	}
        	
        }
        
        
        request.setAttribute( "chosenTheme", chosenTheme );
        request.setAttribute( "dif", dif );
        
        RequestDispatcher rd = request.getRequestDispatcher( "/result.jsp" );
        rd.forward( request, response );
		
	}
	
	
	public List<String> readDocument( String fileName ) {
		
		
		File fn = new File( fileName );
    	List<String> lists = new LinkedList<String>();
    	BufferedReader br = null;
    	
    	
    	if ( fn.exists() ) {
    		
    		try {
    			
    			br = new BufferedReader( new FileReader( fn ) );
    			
    			String line;
    			int i = 0;
    			while ( ( line = br.readLine() ) != null ) {
    				lists.add(line);
    				i ++;
    			}
    			
    		} catch ( IOException e ) {
    			e.printStackTrace();
    			
    		} finally {
    			
    			if ( br != null ) {
    				
    				try {
    					br.close();
    				} catch ( IOException e ) {
    					e.printStackTrace();
    				}
    				
    			}
    			
    		}
    		
    	}	
		
    	return lists;
		
	}
	

}
