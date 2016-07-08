import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customtools.Dataget;
import model.Bhpost;

public class Test {

	@org.junit.Test
	public void test() throws IOException {
		
		Util.Sentiment sent=new Util.Sentiment();
		sent.SentimentInit();
	    int moody= sent.DefineMoody(sent.gethappyCount("This is a sample".split(" ")), sent.getSadCount("This is a sample".split(" ")));
	    System.out.println(sent.getSadCount("This is a sample".split(" ")));
	System.out.println(moody);
	
	}

}
