import javax.microedition.lcdui.* ;
public class CopyScreen extends Alert
{
	private static Displayable instance ;
	synchronized public static Displayable getInstance()
	{
		if(instance == null)
			instance = new CopyScreen() ;
		return instance ;
	}
	private CopyScreen()
	{
		super("��Ϸ˵��") ;
		setString("ʹ�÷�������Ʒɻ��ƶ�������ӵ��������ӵ�ʱ��Ϸ������\n\n\n" +
				"�汾��V1.0\n��Ȩ���� (C) ���� 2008") ;
		setType(AlertType.INFO) ;
		setTimeout(Alert.FOREVER) ;
	}
}
