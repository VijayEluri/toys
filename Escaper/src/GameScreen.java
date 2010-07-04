import java.util.Date;
import java.util.Random;
import javax.microedition.lcdui.* ;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

public class GameScreen extends GameCanvas implements Runnable , CommandListener{
	private static Displayable instance ;
	synchronized public static Displayable getInstance()
	{
		if(instance == null)
			instance = new GameScreen() ;

		flag=Navigator.flag;
		if (flag==3) flag=0;
		if (flag==2){ 
			flag=1;
			d=new Date();
			d2=d.getTime();
			d1=d2-d1;
		}
		return instance ;
	}
	public static boolean conti = true;
	private int rate = 30; //����ˢ��Ƶ��
	private Graphics g;
	public int getWidth=getWidth(); 
	public int getHeight=getHeight(); 
	//�ֻ���Ļ��߳������������ʹ��

	private GameScreen()
	{
		super(false);
		g = this.getGraphics();	
		g.setColor(0x00000);
		g.fillRect(0, 0, getWidth, getHeight);
		addCommand(new Command("����",Command.BACK,1)) ;
		setCommandListener(this) ;

		random = new Random();
		start();
	}
	public void commandAction(Command c,Displayable s)
	{	
		Navigator.flag=flag;
		if (flag==3) flag=0;
		else if (flag==1){ 
			d=new Date();
			d2=d.getTime();
			d1=d2-d1;
			flag=2;
		}else flag=2;

		Navigator.flow(c.getLabel()) ;
	}	

	LayerManager lm_fly;
	Sprite fly;
	Bullet[] Bul;
	private double fx = getWidth/2;
	private double fy = getHeight/2;
	private double fx2 = 0;
	private double fy2 = 0;
	private double x = 0;
	private double y = 0;
	static int flag=0; //0 first 1 ��2�� 2 ��ͣ 3 ��ײ  

	static int MAX=0; //��Ϸ�����Ѷȶ�Ӧ���ӵ���Ŀ
	int MAX2=2;
	int MAX_ALL=95; //�Ѷ����ʱ�������ӵ�����Ŀ

	int n=0;
	double L=0; //�ӵ����˶���ľ���
	Random random;
	static Date d;
	static long d1=0; //��Ϸ��ʼ��ʱ��
	static long d2=0; //��Ϸ������ʱ�� 

	public double getxy(int x){  //����õ�һ��0~x��double��	
		double p;	
		p=Math.abs(random.nextDouble()*1000)%x;
		return p;
	}
	public int getintxy(int x){  //����ĵ�һ��0~x��int
		int p;

		if (x==0){ p=Math.abs(random.nextInt()); return p;}
		return random.nextInt(x);
	}
	public void run(){	

		long st;
		long et;
		lm_fly=new LayerManager();
		int i=0;
		Bul=new Bullet[MAX_ALL];
		for(i=0;i<MAX_ALL;i++){ 
			Bul[i]=new Bullet();
		}
		try { 
			Image im_fly = Image.createImage("/fly.png");
			fly=new Sprite(im_fly);
		}catch(Exception e){System.out.println(e); }			

		lm_fly.append(fly);
		for(i=0;i<MAX_ALL;i++){
			lm_fly.append(Bul[i].p);
		}

		while(conti){
			if (flag==2||flag==3){ //������ײ����ͣʱ
				continue;
			}
			else if (flag==0){ //��һ��Ҫ�����⴦����ʼÿ���ӵ���λ��
				d=new Date();
				d1=d.getTime();
				MAX=Navigator.hard.getValue()*15+20;

				for(i=0;i<MAX_ALL;i++){
					rand(Bul[i]);
				}
				flag=1;
				fx=getWidth/2;
				fy=getHeight/2;
			}
			n=n%MAX2;
			n++;
			st = System.currentTimeMillis();

			input();			
			render(g);			
			for(i=0;i<MAX;i++){ 
				check(Bul[i]);
			}
			et = System.currentTimeMillis();				
			if((et - st) < rate){
				try{
					Thread.sleep(rate - (et -st));
				}catch(Exception e){System.out.println(e);}
			}
		}
	}

	public void rand(Bullet B){  //�����ʼ���ӵ���λ��

		fx2=getintxy(getHeight);
		fy2=getintxy(getHeight);
		int p=getintxy(0)%4;
		switch(p){	
			case 1: B.x=getWidth+5;B.y=getxy(getHeight);break; //��Ļ�ұ߿�
			case 2: B.x=getxy(getWidth);B.y=-5;break; //��Ļ�ϱ߿�
			case 0: B.x=-5;B.y=getxy(getHeight); break; //��Ļ��߿�
			case 3: B.x=getxy(getWidth);B.y=getHeight+5;break; //��Ļ�±߿�		
		}		
		B.fx2=fx2; B.fy2=fy2; //ȷ�����������λ��
		L=Math.sqrt((B.fx2-B.x)*(B.fx2-B.x)+(B.fy2-B.y)*(B.fy2-B.y)); //�����������
		B.vx=(B.fx2-B.x)/L; //����ˮƽ�ٶ�
		B.vy=(B.fy2-B.y)/L; //���㴹ֱ�ٶ�
	}
	public void check(Bullet B){ //����ӵ����߽硢��ײ�����

		x=B.x;
		y=B.y;

		if (n==1){ //���ø��ٵ�
			fx2 = fx;//+getintxy(getWidth/4)-getWidth/8;
			fy2 = fy;//+getintxy(getHeight/4)-getHeight/8;
			B.fx2=fx2; B.fy2=fy2;
		}				
		if(x<-10||y<-10||x>getWidth+10||y>getHeight+10){ //�ӵ�Խ��
			rand(B);			
		}
		if(fly.collidesWith(B.p, true)){ //������ײ

			flag=3;	
			d=new Date();
			d2=d.getTime();

			g.fillRect(0, 0, getWidth, getHeight);
			g.setColor(0xFFFFFF);
			g.drawString("�ɼ�Ϊ: "+(double)((double)(d2-d1))/1000+" ��",getWidth/2-40,getHeight/2,0);			
			flushGraphics();		
		}else{
			B.x+=1.5*B.vx;
			B.y+=1.5*B.vy;
		}		
	}

	public void render(Graphics g){			
		g.setColor(0x00000);
		g.fillRect(0, 0, getWidth, getHeight);

		int i=0;
		for(i=0;i<MAX_ALL;i++)
			Bul[i].p.setPosition((int)Bul[i].x,(int) Bul[i].y);
		//Bul[i].p.move((int)Bul[i].vx,(int)Bul[i].vy);

		fly.setPosition((int)fx, (int)fy);
		lm_fly.paint(g,0,0);
		flushGraphics();
	}

	public void input(){
		int keyState = getKeyStates(); //��������
		if(((keyState & UP_PRESSED) != 0)&&fy>0){
			fy = fy - 1.9;
		}
		if(((keyState & DOWN_PRESSED) != 0)&&fy<getHeight-12){
			fy = fy + 1.9;
		}
		if(((keyState & LEFT_PRESSED) != 0)&&fx>0){
			fx = fx - 1.9;
		}
		if(((keyState & RIGHT_PRESSED) != 0)&&fx<getWidth-14){
			fx = fx + 1.9;
		}		
	}

	public void start(){ //�����߳�
		Thread t = new Thread(this);
		t.start();
	}	
	public void record(){
		System.out.println("record");
	}

}

