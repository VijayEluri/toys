import javax.microedition.lcdui.* ;
public class HelpScreen extends Form implements CommandListener
{
	public HelpScreen(String c)
	{
		super("˵��") ;
		append(c) ;
		addCommand(new Command("����",Command.BACK,1)) ;
		setCommandListener(this) ;
	}
	public void commandAction(Command c,Displayable s)
	{
		Navigator.flow(c.getLabel()) ;
	}
}
