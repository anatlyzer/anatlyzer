package witness.generator;

import java.io.PrintWriter;
import java.io.StringWriter;

public class NullPrintWriter extends PrintWriter {

	public NullPrintWriter() {
		super(new StringWriter());
	}

	public void flush() {
		// do nothing
	}

	public void write(char[] buf, int off, int len) {
		// do nothing
	}

	public void write(int c) {
		// do nothing
	}

	public void write(String s, int off, int len) {
		// do nothing
	}

}