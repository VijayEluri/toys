import javax.microedition.lcdui.* ;

public class SetScreen extends Form implements CommandListener
{
	private static Displayable instance ;
	synchronized public static Displayable getInstance()
	{
		if(instance == null)
			instance = new SetScreen() ;
		
		return instance ;
	}
	
	TextField url ;

	private SetScreen()
	{
		super("��Ϸ����") ;
		append(Navigator.hard);
		append(Navigator.volme);
		url = new TextField("������������","���������",8,TextField.URL) ;
		append(url) ;

		addCommand(new Command("����",Command.BACK,1)) ;
		setCommandListener(this) ;
	}
	public void commandAction(Command c,Displayable s)
	{
		Navigator.flag=0;
		Navigator.flow(c.getLabel()) ;
	}
}
