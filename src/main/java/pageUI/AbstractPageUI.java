package pageUI;

public class AbstractPageUI {
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_LIST_MENU_BANK = "//a[contains(text(),'%s')]";
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String MESSAGE_ERROR = "//label[@id='%s']";
	public static final String INFO_REGISTER = "//td[contains(text(),'%s')]//following-sibling::td";
	
}
