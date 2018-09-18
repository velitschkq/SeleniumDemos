
public class Identifiers {

	// central urls

	final static String home = "https://www.wikipedia.org";

	// ElementsById
	final static public String searchInputId = "searchInput";
	final static public String languageId = "searchLanguage";
	final static public String firstHeading = "firstHeading";
	final static public String searchInputAfterBlankInput = "ooui-php-1";
	final static public String tableOfContents = "toc";

	// ElementsByCSSSelector
	final static public String noArticleBodyDE = "p.mw-search-createlink > b:nth-child(1)";
	final static public String wikiTable = "table[class *=\"wikitable float-right\"]";

	// typeahead Suggestions, code breaks if char x also present at other position
	final static public String typeahead1 = "#typeahead-suggestions > div > a:nth-child(x)";

}
