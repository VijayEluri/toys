import java.awt.*;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

import javax.swing.*;

class MouseClass 
{
	int     seed,stayTime;    //������õ��Ĳ����͵���ͣ����ʱ��
	int		gameLevel,livingTime[];			//��Ϸ�ȼ���ÿ�ֵ���Ĵ��ʱ��
	int     X,Y,width,height;
	int     MouseNum,level;			//�����ţ���Ϸ�ȼ�
	
	boolean visible;
	Image   MouseImage[],HitMouseImage,LaughMouseImage,Hole;		//����ͼƬ,���к�ĵ���ͼƬ�͵ض�ͼƬ
	Applet  Game;          //����Applet����
	Random  R;             //�������������
	boolean showMouse,showHitMouse,showLaughMouse;		//�ж��Ƿ�ɳ��ֵ���ʹ��к�ĵ���Ĳ���	

	public MouseClass(Image MouseImag[],Image HitMouseImage,Image LaughMouseImage,Image Hole,Applet Game)
	{
		R = new Random();
		MouseNum = 0;
		stayTime = 0;
		this.MouseImage = new Image[MouseImag.length];			//����ͼƬ�ռ�
		
		level = 1;					//����Ĭ���Ѷ� ��
		livingTime =	new int[MouseImag.length-3];			//����int�ռ�	
		livingTime[0]= 400;			
		livingTime[1]= 300;
		livingTime[2]= 500;
		livingTime[3]= 200;
		livingTime[4]= 100;
		livingTime[5]= 150;
		
		for(int i=0;i<MouseImag.length;i++)					//�������ͼƬ����
			this.MouseImage[i]=MouseImag[i];
		
		this.HitMouseImage = HitMouseImage;			//���к�ĵ���
		this.LaughMouseImage = LaughMouseImage;			//Ц�ĵ���
		this.Hole       = Hole;				
		this.Game        = Game;
		
		showMouse	= false;	
		showHitMouse       = false;		
		showLaughMouse     = false;
		
		setVisible(true);		//���ÿɼ�������˲���Ϊ�˿�����ĳ���ض��͵��󲻿ɼ���һ��Ϊ���еض�����ʾ����
	}	
	public void setGameLevel(int gamelevel)
	{
		level=gamelevel;
		if(level==1)
		{
			livingTime[0]= 400;
			livingTime[1]= 300;
			livingTime[2]= 500;
			livingTime[3]= 200;
			livingTime[4]= 100;
			livingTime[5]= 150;
		}
		else if(level==2)
		{
			livingTime[0]= 250;
			livingTime[1]= 180;
			livingTime[2]= 400;
			livingTime[3]= 100;
			livingTime[4]= 100;
			livingTime[5]= 100;
		}
		else if(level==3)
		{
			livingTime[0]= 180;
			livingTime[1]= 150;
			livingTime[2]= 200;
			livingTime[3]= 80;
			livingTime[4]= 100;
			livingTime[5]= 70;
		}
	}
	public void setLocation(int X,int Y)   //��λ
	{
	    this.X = X;
	    this.Y = Y;
	}
	public void setVisible(boolean v)
	{
	    visible = v;
	}
	public void updateState()		//�ı����״̬�ķ���
	{
		//������������ı����״̬�����ֵ�Ƶ�ʺ�ͣ����ʱ����ɵ���
		//����һ�ּ򵥵Ŀ��Ʒ���,���и��õ��뷨�ɸĽ��˴�
		if(!showMouse && !showLaughMouse && !showHitMouse)		//û�е���ʱִ��
		{
			if(R.nextInt(seed) % 2000 < level*5)			//�жϺ�ʱ���ֵ���ͷ��
			{
				showMouse = true;
				MouseNum = R.nextInt(5);
			}
			else if(R.nextInt(seed+1000)%3000 < 1)			//�жϺ�ʱ���ֵ���
			{
				showMouse = true;
				MouseNum = R.nextInt(4)+5;	
			}
		}			
		else if(showHitMouse && stayTime > 50)		//�����еĵ�����ʾ0.5��
		{
			showHitMouse = false;
		}	
		
		if((showMouse || showLaughMouse))	//4�ֵ���ֱ�ͣ��2�룬4�룬5�룬1��
		{
			if(MouseNum == 0)
			{	
				if(stayTime > livingTime[0])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
			else if(MouseNum == 1)
			{
				if(stayTime > livingTime[1])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
			else if(MouseNum == 2)
			{
				if(stayTime > livingTime[2])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
			else if(MouseNum == 3)
			{
				if(stayTime > livingTime[3])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
			else if(MouseNum == 4)
			{
				if(stayTime > livingTime[4])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
			else			//ע�⣬��Ϊ�趨��1��δ���������ͷ���ΪЦ��ͼƬ�����Դ˴����ߵ�ͣ��ʱ����ò�Ҫ����1�룬�������Ц��ͼƬ
			{
				if(stayTime > livingTime[5])
				{	
					showMouse = false;
					showLaughMouse = false;
				}
			}
		}			
	}
	public int getMouseNum()
	{
	    return MouseNum;
	}
	public void setMouseNum(int MouseNum)
	{
		this.MouseNum = MouseNum;
	}
	//�����Ƿ���ֵķ���
	public void setshowMouse(boolean showMouse)
	{
		this.showMouse = showMouse;
	}
	public void setshowHitMouse(boolean showHitMouse)
	{
		this.showHitMouse = showHitMouse;
	}
	public void setshowLaughMouse(boolean showLaughMouse)
	{
		this.showLaughMouse = showLaughMouse;
	}
	
	public void paintGame(Graphics g)
	{
		if(visible == true)
		{
			g.drawImage(Hole,X,Y,Game);			//���Ƶض�ͼ��X,YΪ����

			if(showMouse == true)				//����showMouse�����ж��Ƿ���Ƶ���ͼ��
				g.drawImage(MouseImage[MouseNum],X + 12,Y + 18,Game);
			else if(!showLaughMouse && showHitMouse )
				g.drawImage(HitMouseImage,X + 12,Y + 18,Game);
			else if(!showHitMouse && showLaughMouse)
				g.drawImage(LaughMouseImage,X + 12,Y + 18,Game);			
		}
	}

	public void setSeed(int seed)
	{
		this.seed = seed;
	}

	//�ж��Ƿ���еķ�������������Ϊ������꣬����ͼƬ�ĳ��͸�
	public boolean hit(int X,int Y,int MouseWidth,int MouseHeight)
	{
		if((this.X + MouseWidth >= X) && (this.Y + (MouseHeight) + 42>= Y) &&
				(X >= this.X) && (Y >= this.Y) && (showMouse || showLaughMouse))
		{
			if(MouseNum < 5 || showLaughMouse)			//������е��ǵ���
			{	
				//��ʾ�����еĵ���
				showMouse 		= false;
				showLaughMouse  = false;
				showHitMouse 	= true;
			}
			else				//����ǵ���			
				showMouse = false;
			
			return true;
		}
		else
			return false;
	}
}

public class HitMouse extends JApplet
       implements Runnable,MouseListener,MouseMotionListener,ActionListener
{
	private static final long serialVersionUID = 1L;
	int         			AppletWidth,AppletHeight,HoleWidth,HoleHeight,
                			countX,countY,MouseWidth,MouseHeight,score,
                			GameSecond;   //��Ϸʱ��
	int        				CountTime,FlagTime,DoubleScoreTime,CoolHitTime,SequenceHit,SequenceCoolHit;		//������¼ʱ��ĸ�������,��־����ʱ��,˫������ʱ��,��¼�����ĸ���ʱ���������������,��������
	AudioClip   			startSound,hitMouseSound,hitPropertySound,laughSound;		//��ʼ���֣��������Ч��Ц��
    Image        			holeImage,hitMouseImage,laughMouseImage,Screen,SplitImage1,SplitImage2,bkImage;  
    Image					mouseImage[];	
    Thread       			newThread;			//����߳�
    Graphics     			drawScreen;     //����Screen��Graphics����
    MediaTracker 			MT;				//���ý��׷��������������ͼ��
    MouseClass    			mouseSprite[];		//�������
    
    Box 					boxV;				//box���ֶ��󣬷��ð�ť
    
    Font 					F1,F2,F3; 				//�������  
    
    JPanel        			Status1,Status2,MainPanel;		//��Ϸ���,��ʾ��������壬�����
  
    JLabel        			TimeTitle,ScoreTitle,showScoreTitle,SequenceHitTitle,SequenceCoolHitTitle;			//��ʾʱ��ͷ���,������������,������������
    JButton      		 	settingButton,helpButton,pauseButton,endButton;				//��ʼ�ͽ�����ť
    boolean      			isStartGame,isEndGame,isPlayMusic,isAddControlPanel,isDoubleScore,showFlag,showSequenceHit;		//�жϿ�ʼ�ͽ���,��ʼ�˵���ѡ���Ƿ�ѡ�У��Ƿ���ӿ�����壬�Ƿ���˫����Ϸʱ�䣬������ʾ��־�������Ĳ���
   
    StartScreen  			S_Screen;				//������ʼ������Ķ���
    CloseDialog  			CD;					//���������Ի�����Ķ���
    settingDialog 			setGame;
    
    String					helpWords;    //��������
    
    Toolkit 				toolkit=Toolkit.getDefaultToolkit(); //�õ�Ĭ�ϵ�ToolKit����
    Image 					hammerImage1=toolkit.getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/HAMMER1.GIF")); //�õ�ͼ��
	Image 					hammerImage2=toolkit.getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/HAMMER2.GIF")); 
	Cursor 					hammerCursor1=toolkit.createCustomCursor(hammerImage1,new Point(0,30),"MyCursor1"); //�Զ��������
	Cursor 					hammerCursor2=toolkit.createCustomCursor(hammerImage2,new Point(0,30),"MyCursor2");
    GregorianCalendar 		time;			//����GregorianCalendar�Ķ���������ȡʱ��

    public void init()
    {
    	addMouseListener(this);				//��APPLET�������¼�����
    	addMouseMotionListener(this);		//��APPLET�������ƶ��¼�����
    	this.setCursor(hammerCursor1);		//���ù��
        
   
    	AppletWidth    = 815;			//Applet��͸ߣ��ɵ���
        AppletHeight   = 670;
    	 
        helpWords      = "��ͷ����ʱ�������������Ŀ��ӷ֣�û���в��ӷ֡�\n" +
		   				 "0.3���ڴ���Ϊ�����������5�֡���������3�ο�ʼ��\n" +
		   				 "������������������ʱ�䶼��0.5������Ϊ��������2����\n" +
		   				 "������ʼ��������������������������������������ӷ�.\n" +
		   				 "�������ӷַǳ��ࡣ�����ͣ��ť��ͣ��Ϸ��\n" +
		   				 "��Ϸ��������ͷ���ֱ��ǳմ���������׶���ͱ�ŭ��\n" +
		   				 "4����ͷ���ֵ�ʱ�����εݼ�����Ȼ�����еĵ÷����ε���!\n" +
		   				 "����һ����Ա���ǲ��ܴ�ģ����п۷�.���⻹��4�ֵ���\n" +
		   				 "���÷ֱ��ǻ���ȫ��Ļ���г��ֵ���ͷ�������һ���ķ�\n" +
		   				 "(��С����Ϊ����);ȫ��������Ա��(�����õ���);ʮ����\n" +
		   				 "����Ӧ�÷�������!\n" +
		   				 "ף����죡";
        
        countX        = 3;								//ˮƽ����������
        countY        = 3;								//��ֱ����������
        score         = 0;								//����	
        GameSecond    = 0;								//��Ϸʱ��
        CountTime     = 0;								//������ʱ�ı���
        FlagTime     	= 0;
        DoubleScoreTime = 0;
        CoolHitTime		= 1;
        SequenceHit     = 0;
        SequenceCoolHit = 0;
        //�����Ǽ���boolean����ֵ�ĳ�ʼ��
        isStartGame     	= true;
        isEndGame       	= false;
        isPlayMusic			= true;
        isAddControlPanel   = true ;
        isDoubleScore  		= false;
        showFlag      		= false;
        showSequenceHit 	= false;
        
        //�����ǿ���������ݳ�ʼ��
        boxV=Box.createVerticalBox();				//��ֱ����
    	
    	F1     = new Font("TimesRoman",Font.BOLD + Font.ITALIC,72);				//���ü�������
    	F2     = new Font("TimesRoman",Font.BOLD + Font.ITALIC,50);
    	F3     = new Font("TimesRoman",Font.BOLD,15);
    	
    	CD     = new CloseDialog(this,new JFrame());		//��ʼ�������Ի���
    	
    	time   = new GregorianCalendar();
    	
    	TimeTitle    = new JLabel("ʱ��: 0");		//���ñ�ǩ
    	TimeTitle.setFont(F3);
    	
    	showScoreTitle  = new JLabel("����:  ");
    	showScoreTitle.setFont(F3);
    	ScoreTitle  = new JLabel("         0");
    	ScoreTitle.setFont(F2);
    	ScoreTitle.setForeground(Color.blue);
    	
    	SequenceHitTitle = new JLabel("");
    	SequenceHitTitle.setFont(F2);
    	SequenceHitTitle.setForeground(Color.ORANGE);
    	
    	SequenceCoolHitTitle = new JLabel("");
    	SequenceCoolHitTitle.setFont(F2);
    	SequenceCoolHitTitle.setForeground(Color.BLACK);
    	
    	endButton      = new JButton("������Ϸ");
    	helpButton     = new JButton("��Ϸ����");
    	settingButton  = new JButton("��Ϸ����");
    	pauseButton    = new JButton("��ͣ��Ϸ");
    	pauseButton.setEnabled(false);				//��ͣ����ʼ���ɵ��
    	endButton.addActionListener(this);
    	settingButton.addActionListener(this);
    	helpButton.addActionListener(this);
    	pauseButton.addActionListener(this);
    	
    	//��ʼ�����
    	Status1   = new JPanel();
    	Status2   = new JPanel();
    	MainPanel = new JPanel(); 	
        Status1.setLayout(new GridLayout(3,1));
        Status2.setLayout(new GridLayout(2,1));
        MainPanel.setLayout(new GridLayout(3,1));
  
        Status1.add(TimeTitle);
        Status1.add(showScoreTitle);
        Status1.add(ScoreTitle);
      
        Status2.add(SequenceHitTitle);
        Status2.add(SequenceCoolHitTitle);
        
      	boxV.add(Box.createVerticalStrut(15));
        boxV.add(Box.createHorizontalStrut(30));
        boxV.add(settingButton);	
        boxV.add(Box.createHorizontalStrut(30));
        boxV.add(pauseButton);  
        boxV.add(Box.createHorizontalStrut(30));
        boxV.add(helpButton); 
        boxV.add(Box.createHorizontalStrut(30));       
        boxV.add(endButton);
        boxV.add(Box.createVerticalStrut(75));
        
        MainPanel.add(Status1);   
        MainPanel.add(Status2);      
        MainPanel.add(boxV);
 
        setLayout(new BorderLayout());    //����applet����
        
        //�����������ļ��ĳ�ʼ��
        hitMouseSound        = getAudioClip(Thread.currentThread().getContextClassLoader().getResource("sourcefile/hitMouseSound.wav"));
        hitPropertySound	 = getAudioClip(Thread.currentThread().getContextClassLoader().getResource("sourcefile/hitPropertySound.wav"));
        laughSound      	 = getAudioClip(Thread.currentThread().getContextClassLoader().getResource("sourcefile/piglaugh.wav"));
        startSound      	 = getAudioClip(Thread.currentThread().getContextClassLoader().getResource("sourcefile/kaitou.wav"));
        
        //������ͼƬ�ļ��ĳ�ʼ���ͼ���
        
        mouseImage		   = new Image[9];
        mouseImage[0]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pig.gif"));			//����ͼƬ
        mouseImage[1]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pig1.gif"));			//����ͼƬ
        mouseImage[2]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pig2.gif"));			//����ͼƬ
        mouseImage[3]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pig3.gif"));			//����ͼƬ
        mouseImage[4]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pig4.gif"));			//����ͼƬ
        mouseImage[5]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/allhit.gif"));			
        mouseImage[6]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/radomscore.gif"));			
        mouseImage[7]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/allpig.gif"));			
        mouseImage[8]      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/doublescore.gif"));
        
        hitMouseImage      = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/hitpig.gif"));			//���к�ĵ���ͼƬ
        laughMouseImage    = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/laughpig.gif"));		//Ц�ĵ���ͼƬ
        
        holeImage    	   = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/hole.png"));					//�ض�ͼƬ
        
        SplitImage1        = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pighead1.gif"));			//��ʼ�����Ʈ��ͼƬ
        SplitImage2 	   = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/pighead2.gif"));
        bkImage        	   = getImage(Thread.currentThread().getContextClassLoader().getResource("sourcefile/back.jpg"));			//����ͼƬ
        
        MT       = new MediaTracker(this);
        for(int i=0;i<mouseImage.length;i++)		//����ͼƬ
        	MT.addImage(mouseImage[i],0);
        
        MT.addImage(hitMouseImage,0);
        MT.addImage(laughMouseImage,0);
        MT.addImage(holeImage,0);
        MT.addImage(SplitImage1,0);
        MT.addImage(SplitImage2,0);
        MT.addImage(bkImage,0);
        //����ͼ��
        try
        {
        	MT.waitForAll();
        }
        catch(InterruptedException E){ }

        HoleWidth  = holeImage.getWidth(this);      //�ض����
        HoleHeight = holeImage.getHeight(this);		//�ض��߶�

        mouseSprite   = new MouseClass[9];		//9������
        for(int i=0;i<9;i++)
        {
        	mouseSprite[i] = new MouseClass(mouseImage,hitMouseImage,laughMouseImage,holeImage,this);	//��ʼ���������
        	mouseSprite[i].setLocation(i%countX*HoleWidth,i/countY*HoleHeight);		//����ÿ�����������
        	mouseSprite[i].setSeed(2000);			
        	//���ò��������ʱҪ�õ��ĸ�������
        }

        MouseWidth    = mouseImage[0].getWidth(this);				//����ͼƬ���
        MouseHeight   = mouseImage[0].getHeight(this);				//����ͼƬ����
        
        setGame     = new settingDialog(this,new JFrame());		//��ʼ����Ϸ���öԻ���
        S_Screen 	= new StartScreen(AppletWidth,AppletHeight,this,SplitImage1,SplitImage2,bkImage); //��ʼ����        
        Screen      = createImage(AppletWidth,AppletHeight);   //ScreenΪ������Ļͼ��
        drawScreen  = Screen.getGraphics();     //��ʼ��drawScreen
 
   
        resize(AppletWidth,AppletHeight);
    }
    public void addControlPanel()				//����������
    {      
        getContentPane().add(MainPanel,BorderLayout.EAST);
    }
    public void start()
    {
    	newThread = new Thread(this);		//startʱ��ʼ���߳�		
    	newThread.start();
    }

    public void stop()
    {
    	newThread = null;					//stopʱ�ͷ��߳�  (����������,Ϊʲôȥ�����Ͳ�������)
    }

    public void paint(Graphics g)
    {
    	drawScreen.clearRect(0,0,AppletWidth,AppletHeight);			//����   	
   	
    	if(isStartGame)
    	{
    		S_Screen.paintScreen(drawScreen);     //���ƿ�ʼ���� 
    	}
    	else						//������Ϸ����
    	{
    		for(int i=0;i<9;i++)
    			mouseSprite[i].paintGame(drawScreen);
    		if(showFlag)
    			paintFlag(drawScreen);
    	}
    	
    	g.drawImage(Screen,0,35,this);
    }
    public void paintFlag(Graphics g)
    {
    	g.setFont(F1);
    	g.setColor(Color.RED);
    	g.drawString("Cool !", 200, 300);
    }
    
    public void update(Graphics g)   //����update������ʹ�䲻���ԭ��ͼ���ֻ�ǵ���paint����
    {
    	paint(g);
    }

    public void run()				//�̵߳�run����
    {
    	if(isStartGame && isPlayMusic)					//��ʼ���沥�ſ�ʼ����
			startSound.loop();
    	
		while(newThread!= null)
    	{							   			
    		try
    		{
    			Thread.sleep(10);
    		}
    		catch(InterruptedException E){ }
    		
    		if(isStartGame)				//Ʈ��ͼ��  		
    		{	
    			S_Screen.UpdateStatus();   
    			if(!isPlayMusic)
    				startSound.stop();
    		}
    		else
    		{ 			
    			if(isAddControlPanel)		//������Ϸ���������������
    			{
    				addControlPanel();
    				resize(HoleWidth*countX+220,HoleHeight*countY+70); 
    				
    			//	resize(AppletWidth,AppletHeight);   //������С,����������
    				isAddControlPanel = false;
    			}	
    			
    			if(pauseButton.isEnabled()==false)		//��ͣ����Ϊ�ɵ��
    				pauseButton.setEnabled(true);
    			if(CountTime%100 == 0)					//����ʱ��,GameSecondÿ���1
    			{
    				GameSecond++;      				
    	
    				if(SequenceHit<3)		//��������С��3�������ʾ,�ŵ���������Ϊ������ÿ���ж�һ�Σ�����ʾʱ������Ϊһ��
    				{	
    					SequenceHitTitle.setText("");	
    					showSequenceHit = false;
    				}
    				
    				if(SequenceCoolHit<2)		//������С��2�������ʾ
    				{	
    					SequenceCoolHitTitle.setText("");	
    				}
    			}   
    			if(CountTime > DoubleScoreTime)
    			{
    				DoubleScoreTime = 0;
    				isDoubleScore = false;
    			}
    			if(FlagTime > 100)
    			{
    				showFlag=false;      //Flag��ʾһ��
    				FlagTime = 0;
    			}
    			TimeTitle.setText("ʱ��: " + GameSecond + "��");   //��ʾʱ��
    			
    			if(showSequenceHit && SequenceHit>=3)		//���ڵ���3��������ʾ��������
    			{
    				SequenceHitTitle.setText("\n\n\n"+SequenceHit+"����!");
    			}
    			
    			if(SequenceCoolHit >= 2)		//���ڵ���2��������ʾ����������
    			{
    				SequenceCoolHitTitle.setText("\n\n\n"+SequenceCoolHit+"����!");
    			}
    			
        		for(int i=0;i<9;i++)
    			{	
    				mouseSprite[i].updateState();		//����仯״̬
    				
    				//������ʾʱstayTime++
    				if(mouseSprite[i].showMouse || mouseSprite[i].showHitMouse || mouseSprite[i].showLaughMouse)	
    					mouseSprite[i].stayTime++;
    				else		//û����ʱͣ��ʱ������
    					mouseSprite[i].stayTime=0;				
    			}
        		
        		for(int i=0;i<9;i++)
    			{
    				if(mouseSprite[i].stayTime > 150)  //ͣ��ʱ�����1.5����ʾЦ�ĵ���
    				{
    					mouseSprite[i].setshowMouse(false);
    					mouseSprite[i].setshowLaughMouse(true);  
    					if(isPlayMusic)
    						laughSound.play();
    				}
    			}	
        		
    		}
    		
    		CountTime++;   //��ʱ��������++
    		if(showFlag)
    			FlagTime++;
    	}
    }

   public void setEndGame(boolean isEndGame)
   {
      this.isEndGame = isEndGame;
   }
   public void upDateScore(int i)			//���·����ķ���
   {
	   //4�ֵ�����ݳ���ʱ��Ĳ�ͬ�Ӳ�ͬ�ķ�
	   if(!isDoubleScore)     //�Ƿ���˫���÷�ʱ��
	   {
		   if(mouseSprite[i].getMouseNum() == 0)				
			   score += 10;	
		   else if(mouseSprite[i].getMouseNum() == 1)
			   score += 8;		
		   else if(mouseSprite[i].getMouseNum() == 2)
			   score += 5;		  
		   else if(mouseSprite[i].getMouseNum() == 3)
		   	   score += 15;				   
		   else if(mouseSprite[i].getMouseNum() == 4)
		   {  
			   score -= 20;		   
			   SequenceHit = 0;
		   }
	   }
	   else
	   {
		   if(mouseSprite[i].getMouseNum() == 0)				
			   score += 20;	
		   else if(mouseSprite[i].getMouseNum() == 1)
			   score += 16;		
		   else if(mouseSprite[i].getMouseNum() == 2)
			   score += 10;		  
		   else if(mouseSprite[i].getMouseNum() == 3)
		   	   score += 30;				   
		   else if(mouseSprite[i].getMouseNum() == 4)
		   {  
			   score -= 40;		   
			   SequenceHit = 0;
		   }
	   }
   }
   public void mouseExited(MouseEvent e){}
  
   public void mouseClicked(MouseEvent e)
   {
	   if(isStartGame)
	   {			
		   	if((e.getX() > S_Screen.ItemX && e.getX() < S_Screen.ItemX + S_Screen.ItemWidth) && 
		   			((e.getY() > S_Screen.ItemY+15+S_Screen.ItemCap) && 
				   			(e.getY() < S_Screen.ItemY+S_Screen.ItemCap+15+S_Screen.ItemHeigth)))
		   	{
		   		isStartGame = false;						   		
		   		startSound.stop();
				Screen      = createImage(AppletWidth-250,AppletHeight);   //ScreenΪ������Ļͼ��
        		drawScreen  = Screen.getGraphics();     //��ʼ��drawScreen		
		   	} 
		   	else if((e.getX() > S_Screen.ItemX && e.getX() < S_Screen.ItemX + S_Screen.ItemWidth) && 
		   			((e.getY() > S_Screen.ItemY+S_Screen.ItemHeigth+15+S_Screen.ItemCap) && 
				   			(e.getY() < S_Screen.ItemY+S_Screen.ItemHeigth+15+S_Screen.ItemCap+S_Screen.ItemHeigth)))
		   	{
		   		setGame.setVisible(true);
		   	} 	
		   	else if((e.getX() > S_Screen.ItemX && e.getX() < S_Screen.ItemX + S_Screen.ItemWidth) && 
		   			((e.getY() > S_Screen.ItemY+S_Screen.ItemHeigth*2+15+S_Screen.ItemCap) && 
				   			(e.getY() < S_Screen.ItemY+S_Screen.ItemHeigth*2+15+S_Screen.ItemCap+S_Screen.ItemHeigth)))
		   	{
		   		JOptionPane.showMessageDialog(this,helpWords,"����",JOptionPane.INFORMATION_MESSAGE);
		   	} 	
		   	if((e.getX() > S_Screen.ItemX && e.getX() < S_Screen.ItemX + S_Screen.ItemWidth) && 
		   				((e.getY() > S_Screen.ItemY+15+S_Screen.ItemHeigth*3+S_Screen.ItemCap) && 
				   				(e.getY() < S_Screen.ItemY+15+S_Screen.ItemHeigth*3+S_Screen.ItemCap+S_Screen.ItemHeigth)))
		   	{
		   		System.exit(0);
		   	} 		
	   }
	   
   }

   public void mouseEntered(MouseEvent e)
   {
	   showStatus("X:" + e.getX() + "," + "Y:" + e.getY());
   }

   public void mousePressed(MouseEvent e)
   {
	   if(isStartGame) return;				//��ʼ�����������Ч
	   this.setCursor(hammerCursor2);		//����仯���
	 
	   int X = e.getX();
	   int Y = e.getY();
	   showSequenceHit = false;	      
	   
	   for(int i=0;i<9;i++)
	   {
		   if(mouseSprite[i].hit(X,Y,MouseWidth, MouseHeight) == true)
		   {			   
			   if(mouseSprite[i].getMouseNum() < 5 && isPlayMusic)				//�жϲ������е���������������е��ߵ�����
				   hitMouseSound.play();
			   else if(mouseSprite[i].getMouseNum() >= 5 && isPlayMusic)
				   hitPropertySound.play();
			   			   
			   if(mouseSprite[i].showLaughMouse)			//ֹͣЦ��
				   laughSound.stop();
			   
			   if((CountTime - CoolHitTime) < 50)		//�������в��Ҷ���0.5���ڴ������������
				   SequenceCoolHit++;			//����������1
			   else
				   SequenceCoolHit = 0 ;			//û��0.5���ڴ�������������������
			   
			   CoolHitTime = CountTime;
			   
			   if(mouseSprite[i].stayTime < 50)			//0.5���ڴ�����ʾcool������ӷ�
			   {
				   showFlag=true;
				   if(!isDoubleScore)
					   score += 5;
				   else
					   score += 10;
			   }
			   
			   mouseSprite[i].stayTime = 0;  //��������ͣ��ʱ������
			   
			   upDateScore(i);				//����������·���
			   
			   if(mouseSprite[i].getMouseNum() == 5)		//ȫ����ߣ���ȫ��Ļ�ܵ÷ֵĵ���ȫ��
			   {  
				   	for(int j=0;j<9;j++)
				   	{				   
				   		if(mouseSprite[j].showMouse || mouseSprite[j].showLaughMouse)
				   		{
				   			if(mouseSprite[j].getMouseNum() < 4)
				   			{
				   				mouseSprite[j].showMouse = false;
				   				mouseSprite[j].showLaughMouse = false;
				   				mouseSprite[j].showHitMouse = true;
				   				if(isPlayMusic)
				   					hitMouseSound.play();
				   				
				   				upDateScore(j);				//����������·���
				   				
				   				mouseSprite[j].stayTime = 0;		//�����У�ͣ��ʱ������
				   			}   
				   		}		
				   	}
		 
			   }
			  
			   else if(mouseSprite[i].getMouseNum() == 6)		//����ӷֵĵ���
			   {  
				   Random r		=	new Random();
				   score 		-=  r.nextInt(200)-30;			   //�н�С���ʼ���
				   if(!isDoubleScore)
					   score    += r.nextInt(200)-30;
				   else
					   score    += (r.nextInt(200)-30)*2;
			   }			   
			   else if(mouseSprite[i].getMouseNum() == 7)		
			   {  		
				   for(int j=0;j<9;j++)
				   {					   	
					   	mouseSprite[j].setMouseNum(4);
					   	mouseSprite[j].showMouse = true;
	   					mouseSprite[j].showLaughMouse = false;
	   					mouseSprite[j].showHitMouse = false;
	   					mouseSprite[j].stayTime = 0;
				   }
			   }
			   else if(mouseSprite[i].getMouseNum() == 8)		
			   {  
				   isDoubleScore 	= true;
				   DoubleScoreTime 	= CountTime + 1000;			//10��˫������ʱ��
			   }
			   
			   if(SequenceHit>=3)	
				   score += SequenceHit*2;				//����������������ӷ�
			  
			   if(SequenceCoolHit>=2)	
				   score += SequenceCoolHit*10;				//������������������ӷ�
			   
			   ScoreTitle.setText("  "+score);
			   
			   showSequenceHit = true;
			   SequenceHit++;			//����������1
		   }
	   }
	   if(showSequenceHit==false)		//û������������������,��������������
	   {	
		   SequenceHit		= 0;	   		 
		   SequenceCoolHit  = 0;
	   }
   }

   public void mouseReleased(MouseEvent e)
   {
	   if(isStartGame) return;
	   this.setCursor(hammerCursor1);
   }

   public void mouseMoved(MouseEvent e)
   {
	   if(isStartGame)			//����Ƶ��Ĳ˵�����ɫ
	   {	
		   for(int i=0;i<S_Screen.isItemSelect.length;i++)
		   {
		   		if((e.getX() > S_Screen.ItemX && e.getX() < S_Screen.ItemX + S_Screen.ItemWidth) && 
		   				((e.getY() > S_Screen.ItemY+15+S_Screen.ItemHeigth*i+S_Screen.ItemCap) && 
				   				(e.getY() < S_Screen.ItemY+15+S_Screen.ItemHeigth*i+S_Screen.ItemCap+S_Screen.ItemHeigth)))
		   		{
		   			S_Screen.isItemSelect[i]=true;
		   		} 	
		   		else
		   			S_Screen.isItemSelect[i]=false;
		   }
	   }
	   showStatus("X:" + e.getX() + "," + "Y:" + e.getY());
   }

   public void mouseDragged(MouseEvent e){} 

   public void actionPerformed(ActionEvent e)
   {
	   if(e.getSource() == settingButton)
	   {	   
		   newThread = null;		
		   setGame.setVisible(true);		   
		   newThread = new Thread(this);
		   newThread.start();	
	   }
	   else if(e.getSource() == pauseButton)
	   {
		   if(pauseButton.getText()=="��ͣ��Ϸ")
		   {
			   newThread = null;
			   pauseButton.setText("������Ϸ");
		   }
		   else
		   {   
			   	pauseButton.setText("��ͣ��Ϸ");
			   	newThread = new Thread(this);
		   		newThread.start();
		   }	   
	   }
	   else if(e.getSource() == helpButton)
	   {
		   newThread = null;
		   JOptionPane.showMessageDialog(this,helpWords,"����",JOptionPane.INFORMATION_MESSAGE);
		   newThread = new Thread(this);
	   	   newThread.start();
	   }
	   else if(e.getSource() == endButton)
	   {		  
		   newThread = null;		
		   CD.setVisible(true);
		   
		   newThread = new Thread(this);
		   newThread.start();	   
	   }
   	}
   	public void clearData()			//���ݻָ���ʼֵ
   	{
   		score         = 0;								//����	
      	GameSecond    = 0;								//��Ϸʱ��
      	CountTime     = 0;								//������ʱ�ı���
      	FlagTime     	= 0;
      	DoubleScoreTime = 0;
      	CoolHitTime		= 1;
       	SequenceHit   = 0;
       	SequenceCoolHit = 0;
       	//�����Ǽ���boolean����ֵ�ĳ�ʼ��
       	isStartGame     	= true;
       	isEndGame       	= false;
       	isAddControlPanel 	= true ;
       	isDoubleScore  		= false;
       	showFlag      		= false;
       	showSequenceHit 	= false;
	    Screen      		= createImage(AppletWidth,AppletHeight);   //ScreenΪ������Ļͼ��
        drawScreen  		= Screen.getGraphics();     //��ʼ��drawScreen	
       	getContentPane().remove(MainPanel);	
      
        resize(AppletWidth,AppletHeight);   //������С	  
   	}
}

class StartScreen
{
	int     width,height,StringWidth,StringHeight,Ascent,Descent,X,Y;
	int     ImageLeftBound,ImageRightBound,ImageX,ImageY,ImageWidth,
			ImageHeight,VX;
	int 	ItemX,ItemY,ItemWidth,ItemHeigth,ItemCap;		//�˵�������꣬�����꣬���ȣ��߶ȣ���ֱ���
	Font    F1,F2,F3;
	Image   Normal,Hit,currentImage,bkImage;
	String  ChineseTitle,EnglishTitle,PressEnter,ItemString[];	//����Ͳ˵����ַ���
	Applet  Game;
	Random  R;
	boolean showPressEnter,isItemSelect[];			//�˵����Ƿ�ѡ��
	FontMetrics FM;   //�˴�����FontMetrics���󣬱��ڻ�ȡ��������

	public StartScreen(int AppletWidth,int AppletHeight,HitMouse Game,Image normal,Image hit, Image bk)
	{
		R      = new Random();

		F1     = new Font("TimesRoman",Font.BOLD,72);
		F2     = new Font("TimesRoman",Font.BOLD + Font.ITALIC,36);
		F3     = new Font("TimesRoman",Font.BOLD,20);

		ChineseTitle    = "����ͷ";
		EnglishTitle    = "Hit Pig's Head";

		width           = AppletWidth;
		height          = AppletHeight;
      
		Normal          = normal;				//��ʼ״̬�µ�Ʈ��ͼ��
		Hit             = hit;				//ײǽ֮���Ʈ��ͼ��
		bkImage         = bk;					//����ͼ

		ImageWidth      = Normal.getWidth(Game);	//Ʈ��ͼ��Ŀ�Game��ImageObserver,�����
		ImageHeight     = Normal.getHeight(Game);
		ImageLeftBound  = 0;					//Ʈ��ͼ����߽�
		ImageRightBound = Game.bkImage.getWidth(Game) -160;		//Ʈ��ͼ���ұ߽�
		ImageX          = ImageRightBound;	//Ʈ��ͼ����ʼ�����꣬��������Ʈ���ɸ��ģ�

		ItemCap = 5 ;			//�˵�������Ϊ25
		
		VX              = -1;				//��������Ʈ���ٶȵĲ���
		this.Game       = Game;
		currentImage    = Normal;				//��ǰƮ����ͼ��
		
		ItemString = new String[4];
		ItemString[0] = "�� ʼ �� Ϸ";
		ItemString[1] = "�� Ϸ �� ��";
		ItemString[2] = "�� Ϸ �� ��";
		ItemString[3] = "�� �� �� Ϸ";
		
		showPressEnter  = true;
		isItemSelect = new boolean[4]; 
		isItemSelect[0]   = false;
		isItemSelect[1]   = false;
		isItemSelect[2]   = false;
		isItemSelect[3]   = false;
	}

	public void UpdateStatus()		//�ı�Ʈ��ͼ��
	{
		ImageX = ImageX + VX;			//ͨ������VX�任Ʈ��ͼ�������

		if(ImageX <= ImageLeftBound)			//ײ��߽�
		{
			currentImage = Hit;				//�仯Ʈ��ͼ��

			ImageX = ImageLeftBound;			//Ʈ��ͼ������������߽������
			VX     = -VX;						//����ȡ��
		}

		if(ImageX >= ImageRightBound)		//ײ�ұ߽磬������ͬײ��߽�
		{
			currentImage = Normal;

			ImageX = ImageRightBound;
			VX     = -VX;
		}
	}

	public void paintScreen(Graphics g)
	{
		g.setFont(F1);
		FM = g.getFontMetrics();               //��ȡ���������

		Ascent       = FM.getAscent();          //���ߵ����嶥���ľ���
		Descent      = FM.getDescent();		   //���ߵ�����ײ��ľ���
		StringWidth  = FM.stringWidth(ChineseTitle);			//��ȡ�ַ������ȣ�ע���ǳ��Ȳ����ַ��ĸ�����
		StringHeight = Ascent + Descent;						//��ȡ����߶�

		X            = (width - StringWidth) / 2;				//�������������
		Y            = Ascent;								//�������������꣨�����ɵ�����

		g.drawImage(bkImage, 0, 0, Game);					//���Ʊ���ͼ
		g.setColor(Color.white);							//���ñ�ˢ��ɫ
     
		g.drawString(ChineseTitle,X,Y); 					//д�����	

		Y            = StringHeight;
		g.drawLine(X,Y,X+StringWidth,Y);					//��ֱ��

		X            = X + 30;
		Y            = Y + 5;
		g.drawLine(X,Y,X+StringWidth-60,Y);		//��ֱ��     

		g.setFont(F2);					//˵��ͬ�ϣ����������EnglishTitle
		FM = g.getFontMetrics();

		Ascent       = FM.getAscent();
		Descent      = FM.getDescent();
		StringWidth  = FM.stringWidth(EnglishTitle);
		StringHeight = Ascent + Descent;

		X            = (width - StringWidth) / 2;
		Y            = Y + Ascent;
		g.drawString(EnglishTitle,X,Y);

		g.setFont(F3);				//�˵���
		FM = g.getFontMetrics();
		Ascent       = FM.getAscent();
		Descent      = FM.getDescent();
		ItemWidth    = FM.stringWidth(ItemString[0]);
		ItemHeigth 	 = Ascent + Descent;

		ItemX        = (width - ItemWidth)/2 ;
		ItemY        = height - 150;
		for(int i=0;i<isItemSelect.length;i++)
		{
			if(!isItemSelect[i])				//ѡ�еĲ˵����Ϊ��ɫ������״̬Ϊ��ɫ
	    		g.setColor(Color.white);
	    	else
	    		g.setColor(Color.yellow);
	  
	    	g.drawString(ItemString[i], ItemX, ItemY+ItemHeigth*i+ItemCap);	    	
		}
				
		ImageY       =  150;			//Ʈ��ͼ��������
		g.drawImage(currentImage,ImageX,ImageY,Game);			//����Ʈ��ͼ��
	}
}

class CloseDialog extends JDialog implements ActionListener   //�����Ի���
{  
	private static final long serialVersionUID = 1L;
	Panel        P1,P2;
    Button       B1,B2;
    HitMouse	 Game;

    public CloseDialog(HitMouse Game,JFrame owner)
    {
    	super(owner,"�뿪��Ϸ...",true);

    	this.Game = Game;

    	setLayout(new GridLayout(2,1));
    	P1 = new Panel();
    	P2 = new Panel();

    	P1.add(new Label("�˳���Ϸ?"));
    	P2.add(B1 = new Button("ȷ��"));
    	P2.add(B2 = new Button("ȡ��"));
    	B1.addActionListener(this);
    	B2.addActionListener(this);

    	getContentPane().add(P1);
    	getContentPane().add(P2);
    	pack();
    }

    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource() == B1)
    	{
    		Game.setEndGame(true);
    		Game.clearData();
    		setVisible(false);
    	}
    	else if(e.getSource() == B2)
    	{	Game.setEndGame(false);
    		setVisible(false);
    	}
    }
}

class settingDialog extends JDialog implements ItemListener,ActionListener   //��Ϸ���öԻ���
{  
	private static final long serialVersionUID = 1L;
	int gameLevel;
	boolean playMusic;
	JPanel        P1,P2,P3;
	JLabel		levelLabel, musicSetLabel;
	JRadioButton levelButton1,levelButton2,levelButton3,musicOnButton,musicOffButton;
	JButton    confirmButton,cancelButton;
	ButtonGroup levelGroup,musicSetGroup;
    HitMouse	 Game;
    
    public settingDialog(HitMouse Game,JFrame owner)
    {
    	super(owner,"��Ϸ����",true);
    	this.Game = Game;
    	
    	confirmButton = new JButton("ȷ��");
    	cancelButton  = new JButton("ȡ��");
    	confirmButton.addActionListener(this);
    	cancelButton.addActionListener(this);
    	
    	gameLevel = 1;				//Ĭ���Ѷ�Ϊ�򵥣�����Ϊ��
    	playMusic = true;
    	
    	P1 = new JPanel();  
    	P2 = new JPanel();   
    	P3 = new JPanel();
    	
    	setLayout(new GridLayout(3,1));				//3��ÿ��һ�����
    	levelLabel 		= new JLabel("��ѡ���Ѷ�");
    	musicSetLabel   = new JLabel("�Ƿ����Ч");
    	
    	levelGroup=new ButtonGroup();					//�Ѷ�ѡ��
    	levelButton1=new JRadioButton("��");
    	levelButton2=new JRadioButton("�е�");   	
    	levelButton3=new JRadioButton("����"); 
    	levelButton1.addItemListener(this);
    	levelButton2.addItemListener(this);
    	levelButton3.addItemListener(this);
    	levelGroup.add(levelButton1);
    	levelGroup.add(levelButton2);
    	levelGroup.add(levelButton3);
    	
    	musicSetGroup=new ButtonGroup();				//�Ƿ������
    	musicOnButton=new JRadioButton("��");
    	musicOffButton=new JRadioButton("��"); 
    	musicOnButton.addItemListener(this);
    	musicOffButton.addItemListener(this);
    	musicSetGroup.add(musicOnButton);
    	musicSetGroup.add(musicOffButton);
    
    	P1.add(levelButton1);
    	P1.add(levelButton2);
    	P1.add(levelButton3);
    	P2.add(musicOnButton);
    	P2.add(musicOffButton);
    	P3.add(confirmButton);
    	P3.add(cancelButton);
    	
    	getContentPane().add(levelLabel);
    	getContentPane().add(P1);
    	getContentPane().add(musicSetLabel);
    	getContentPane().add(P2);
    	getContentPane().add(P3);
    	pack();
    }
	
	public void itemStateChanged(ItemEvent arg0) 
	{
		if(arg0.getItemSelectable() == levelButton1)
		{
			gameLevel = 1;			
		}
		else if(arg0.getItemSelectable() == levelButton2)
		{
			gameLevel = 2;			
		}
		else if(arg0.getItemSelectable() == levelButton3)
		{
			gameLevel = 3;
		}
		
		if(arg0.getItemSelectable() == musicOnButton)
			playMusic=true;
		else if(arg0.getItemSelectable() == musicOffButton)
			playMusic=false;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == confirmButton)
		{
			for(int i=0;i<Game.mouseSprite.length;i++)		//�����ѶȺ����ֿ���
			{		
				Game.mouseSprite[i].setGameLevel(gameLevel);					
			}
			
			if(Game.isStartGame && !Game.isPlayMusic && playMusic)		//��ʼ�����û��������������ʱBUG�Ľ��
				Game.startSound.loop();
			
			Game.isPlayMusic = playMusic;
			setVisible(false);
		}
		else if(arg0.getSource() == cancelButton)
		{
			setVisible(false);
		}
		
	}
   
}