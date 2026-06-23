package generateTheme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		
		String historyFile = getServletContext().getRealPath("/WEB-INF/data/history.csv");
		String favoriteFile = getServletContext().getRealPath("/WEB-INF/data/favorite.csv");
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
		int savingSize = 3;
        int favoriteSize = 10;
		
		
		// Postデータ受け取り
		request.setCharacterEncoding("UTF-8");
		String g1 = request.getParameter("action");
		
		
		// CSVのお題一覧読み込み
		List<String[]> themeList = new ArrayList<String[]>();
		viewTheme( themeList );
		String[] chosenTheme = new String[ themeList.size() ];
		
		
		// CSVのお気に入り一覧読み込み
		String favoriteFile = getServletContext().getRealPath("/WEB-INF/data/favorite.csv");
		List<String> favoriteTheme = new LinkedList<String>();
		favoriteTheme = readDocument( favoriteFile );

		
		
		if ( g1.equals( "generate" ) ) {		
		
			
			String g2 = request.getParameter("difficulty");
			
		
			// お題内容を抽選
			int dif = Integer.parseInt( g2 );
			chooseTheme( dif, random, chosenTheme, themeList );
			String themeText = makeThemeText( chosenTheme );
		
		
		
			// お題を履歴に保存
			List<String> savedTheme = new LinkedList<String>();
			String savingFile = getServletContext().getRealPath( "/WEB-INF/data/history.csv" );
			savedTheme = readDocument( savingFile );
			saveTheme( themeText, savedTheme, savingSize );
			keepDocument( savedTheme, savingFile );
		
        
			request.setAttribute( "chosenTheme", chosenTheme );
			request.setAttribute( "dif", dif );
			request.setAttribute( "themeText", themeText );
			
			RequestDispatcher rd = request.getRequestDispatcher( "/result.jsp" );
	        rd.forward( request, response );
	        
	        
	        
		} else if ( g1.equals( "favorite" ) ) {
			
			
			// Postデータ受け取り
			String g3 = request.getParameter("favo");
			String g4 = request.getParameter("themeText");
			
			
			if ( g3.equals( "y" ) ) {
				
				if ( favoriteTheme.size() < favoriteSize ) {
					
					saveTheme( g4, favoriteTheme, favoriteSize );
					keepDocument( favoriteTheme, favoriteFile );
					
					request.setAttribute( "favoChoice", g3 );
			        
					RequestDispatcher rd = request.getRequestDispatcher( "/message.jsp" );
					rd.forward( request, response );
					
				} else {
					
					request.setAttribute( "favoriteTheme", favoriteTheme );
					request.setAttribute( "themeText", g4 );
					
					RequestDispatcher rd = request.getRequestDispatcher( "deleteFavorite.jsp" );
					rd.forward( request, response );
					
				}
				
				
			} else {
				
				request.setAttribute( "favoChoice", g3 );
        
				RequestDispatcher rd = request.getRequestDispatcher( "/message.jsp" );
				rd.forward( request, response );
				
			}	
			
			
		} else if ( g1.equals( "deleteFavorite" ) ) {
			
			
			String g4 = request.getParameter( "themeText" );
			String g5 = request.getParameter( "deleteChoice" );
			int choice = Integer.parseInt( g5 );
			
			
			if ( choice < favoriteTheme.size() ) {
				
				removeFavoriteTheme( g4, favoriteTheme, choice );
				keepDocument( favoriteTheme, favoriteFile );
				
				String s = "d";
				request.setAttribute( "favoChoice", s );
				request.setAttribute( "choice", choice );
				
				RequestDispatcher rd = request.getRequestDispatcher( "/message.jsp" );
				rd.forward( request, response );
				
				
			} else {
				
				String s = "c";
				request.setAttribute( "favoChoice", s );
				
				RequestDispatcher rd = request.getRequestDispatcher( "/message.jsp" );
				rd.forward( request, response );
				
			}
			
			
		}
        
		
	}
	
	
	public void viewTheme( List<String[]> themeList ) {
		
		
		String fileName = getServletContext().getRealPath("/WEB-INF/data/themeList.csv");
		File fn = new File(fileName);
		BufferedReader br = null;
		
		
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
		
	}
	
	
	public List<String> readDocument( String fileName ) {
		
		
		File fn = new File( fileName );
    	List<String> lists = new LinkedList<String>();
    	BufferedReader br = null;
    	
    	
    	if ( fn.exists() ) {
    		
    		try {
    			
    			br = new BufferedReader( new FileReader( fn ) );
    			
    			String line;
    			while ( ( line = br.readLine() ) != null ) {
    				lists.add(line);
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
	
	
	public void chooseTheme( int dif, Random random, String[] chosenTheme, List<String[]> themeList ) {
		
		
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
		
		
	}
	
	
	public static String makeThemeText( String[] chosenTheme ) {
    	
    	String s = "";
    	
    	for (int i = 0; i < chosenTheme.length; i ++ ) {
			if ( chosenTheme[i] != null ) {
				s += chosenTheme[i] + "/";
			} else {
				break;
			}
		}
		s = s.substring( 0, s.length()-1 );
		return s;
    	
    }
	
	
	public void saveTheme( String themeText, List<String> documents, int size ) {
    	
    	if (documents.size() < size) {
    		documents.add( themeText );
    		
    	} else if (documents.size() >= size) {
    		documents.remove(0);
    		documents.add( themeText );
    	}
    	
    }
	
	
	public void addFavoriteTheme( String themeText, List<String> documents, int size ) {
		
		documents.add( themeText );
		
	}
	
	
	public void removeFavoriteTheme( String themeText, List<String> documents, int choice ) {
		
		documents.remove( choice );
		documents.add( themeText );	
		
	}
	
	
	public void keepDocument( List<String> documents, String fileName ) {
    	
		
    	PrintWriter pw = null;
    	
    	try {
    		
    		pw = new PrintWriter( new FileWriter( fileName ) );
    		
    		if ( documents != null && !documents.isEmpty() ) {
    			
    			for ( String s : documents ) {
    				
    				if ( s != null ) {
    					pw.println( s );
    				}
    				
    			}
    			
    		}	
    		
    	} catch ( IOException e ) {
    		
    		e.printStackTrace();
    		
    	} finally {
    		
    		if ( pw != null ) {
    			pw.close();
    		}
    		
    	}
    	
    }
	

}
