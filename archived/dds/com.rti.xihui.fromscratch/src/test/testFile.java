package test;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.Test;

public class testFile {

	@Test
	public void test() {
		InputStream stream = testFile.class.getResourceAsStream("test.xml");
		System.out.println(stream);
		assertNotNull(stream);
	}

}
