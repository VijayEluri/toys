import javax.microedition.lcdui.* ;

public class HighScreen extends Form implements CommandListener
{
	private static Displayable instance ;
	synchronized public static Displayable getInstance()
	{
		if(instance == null)
			instance = new HighScreen() ;
		return instance ;
	}
	private HighScreen()
	{
		super("�߷��Ű�") ;
		//setType(AlertType.INFO) ;
		//setTimeout(Alert.FOREVER) ;
		append("\n\n\n��1���� �׽��� 100��\n��2���� ¬��ǿ 99��\n��3���� ����ϼ 90��\n��4���� � 85��\n��5���� ���� 80��\n��6���� ������ 80��\n��7���� ������ 79��\n��7���� ���� 62��\n��8���� ����֥ 61��\n");
		addCommand(new Command("����",Command.BACK,1)) ;	
		setCommandListener(this) ;
	}
	public void commandAction(Command c,Displayable s)
	{
		Navigator.flow(c.getLabel()) ;
	}
}
