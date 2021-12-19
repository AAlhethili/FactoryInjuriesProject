package simulation;

public class ToolsofStrings {
	public ToolsofStrings() {
	}
	public static String padRight(String s, int n) {
	    return String.format("%-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	   return String.format("%" + n + "s", s);  
	}
	public String didOrDidNot(boolean dd) {
		String dod;
		if(dd) {
			dod=" was treated by ";
		}
		else {
			dod= " was not treated by ";
		}
		return dod;
	}
	public String wasno(boolean dd) {
		String dod;
		if(dd) {
			dod=" was a ";
		}
		else {
			dod= " was no ";
		}
		return dod;
	}
	public String doesor(boolean dd) {
		String dod;
		if(dd) {
			dod=" does ";
		}
		else {
			dod= " doesn't ";
		}
		return dod;
	}
}
