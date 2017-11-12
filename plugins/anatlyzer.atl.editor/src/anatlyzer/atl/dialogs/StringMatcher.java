package anatlyzer.atl.dialogs;

public class StringMatcher {

	private String pattern;

	public StringMatcher(String pattern, boolean b, boolean c) {
		this.pattern = pattern;
	}

	public boolean match(Object element, String txt) {
		return new StringOnlyMatcher(pattern, true, false).match(txt);
	}

}
