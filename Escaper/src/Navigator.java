import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Navigator
{
	final public static int MAIN_SCREEN = 1 ;
	final public static int GAME_SCREEN = 2 ;
	final public static int SET_SCREEN = 3 ;
	final public static int GAME_HELP_SCREEN = 4 ;
	final public static int SET_HELP_SCREEN = 5 ;
	final public static int HIGH_SCREEN = 6 ;
	public static Gauge hard = new Gauge("��Ϸ�Ѷ�",true,5,1) ;
	public static Gauge volme= new Gauge("����",true,10,3) ;
	public static MIDlet midlet ;
	public static Display display ;
	public static int current ;
	public static int flag; 
	
	public static void show(Object obj)
	{
		switch(current)
		{
			case MAIN_SCREEN :
				display.setCurrent(MainScreen.getInstance()) ;
				break ;
			case GAME_SCREEN :
				display.setCurrent(GameScreen.getInstance()) ;				
				break ;
			case SET_SCREEN :
				display.setCurrent(SetScreen.getInstance()) ;
				break ;
			case GAME_HELP_SCREEN :
				display.setCurrent(new HelpScreen((String)obj)) ;
				break ;
			case SET_HELP_SCREEN :
				display.setCurrent(new HelpScreen((String)obj)) ;
				break ;
			case HIGH_SCREEN :
				display.setCurrent(new HelpScreen((String)obj)) ;
				break ;
		}
	}
	public static void flow(String cmd)
	{
		switch(current)
		{
			case MAIN_SCREEN :
				if(cmd.equals("��ʼ��Ϸ"))
				{
					current = GAME_SCREEN ;
					show(null) ;
				}else if(cmd.equals("��Ϸ����"))
				{
					current = SET_SCREEN ;
					show(null) ;
				}else if(cmd.equals("�߷��Ű�"))
				{
					current = HIGH_SCREEN ;
					display.setCurrent(HighScreen.getInstance()) ;
				}else if(cmd.equals("��Ϸ˵��"))
				{
					display.setCurrent(CopyScreen.getInstance()) ;
				}else if(cmd.equals("�˳�"))
				{
					midlet.notifyDestroyed() ;
				}
				break ;
			case GAME_SCREEN :
				if(cmd.equals("����"))
				{
					current = MAIN_SCREEN ;
					show(null) ;
				}
				break ;
			case SET_SCREEN :
				if(cmd.equals("����"))
				{
					current = MAIN_SCREEN ;
					show(null) ;
				}
				break ;
			case GAME_HELP_SCREEN :
				if(cmd.equals("����"))
				{
					current = GAME_SCREEN ;
					show(null) ;
				}
				break ;
			case SET_HELP_SCREEN :
				if(cmd.equals("����"))
				{
					current = SET_SCREEN ;
					show(null) ;
				}
				break ;
			case HIGH_SCREEN :
				if(cmd.equals("����"))
				{
					current = MAIN_SCREEN ;
					show(null) ;
				}
				break ;
		}
	}
}
