/*
 * Filename: Ktalk.java
 * Author: Jianjun Kong
 * CopyRight @ kongove.cn
 * Data: June 25,2008
 * License GPLv3
*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.net.*;

public class Ktalk
{
    public static class kSocket // ����ͨ���࣬ר���ù���Socket�շ�����
    {
        DatagramSocket dSocket;
        DatagramPacket inPacket;
        DatagramPacket outPacket;
        InetAddress Addr;

        byte[] inBuffer = new byte[100];
        byte[] outBuffer;
        String str = "";

        kSocket()
        {
            try{
                dSocket = new DatagramSocket(1986);     // socket �˿�1986
                Addr = InetAddress.getByName(local);
            }
            catch (IOException e){
                System.out.println("IOException occurred with socket.");
                System.out.println(e);
                e.printStackTrace();
            }
        }

        void sendMsg(String ss,InetAddress addr)        // ��������
        {
            System.out.println("Send message :"+addr.toString()+" :"+ss);

            try{
                outBuffer = ss.getBytes();
                outPacket = new DatagramPacket(outBuffer, outBuffer.length, addr, 1986  );  //������д��socket
                dSocket.send(outPacket);
            }
            catch (IOException e){
                System.out.println("IOException occurred with socket.");
                System.out.println(e);
                e.printStackTrace();
            }
        }
        String receiveMsg()     // ��������
        {
            System.out.println("Receive message......");

             try{
                inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                dSocket.receive(inPacket);      // ��socket��ȡ����
                Addr = inPacket.getAddress();
                str = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Receive message: "+Addr+" : " + str);

            }
            catch (IOException e){
                System.out.println("IOException occurred with socket.");
                System.out.println(e);
                e.printStackTrace();
            }
            return str;
       }

    }

    private static kSocket s;
    public static String local="192.168.1.12";
    public static int []flag;   //����û��Ӵ����Ƿ��½���0Ϊδ�½���1Ϊ���½�

    public static class subWindow //extends Frame   //�Ӵ�����
    {

        private JTextArea outText;
        private  JTextArea inText;
        private  JTextField nameText;
        private  JTextField autoText;
        public InetAddress addr;
        private JFrame app;
        public JCheckBox huifuRB;
        public Boolean fb;
        private int N;

        subWindow(String ss,String name,int i)  //��ʼ���Ӵ�����
        {
            try{

                addr=InetAddress.getByName(local);
            }
            catch(IOException e){
                System.out.println("IOException occurred with socket.");
                System.out.println(e);
                e.printStackTrace();
            }

            int port=1986;
            fb=false;
            N=i;

            app=new JFrame("SubWindow:["+name+"]Talks With["+ss+"]");
            Container container=app.getContentPane();
            container.setLayout(new FlowLayout());
            outText=new JTextArea("",20,57);
            inText=new JTextArea("",7,57);

            JTextField idText=new JTextField("�û���:",4);
            idText.setEditable(false);
            nameText=new JTextField(name,5);
            JButton blodButton=new JButton("B");
            JButton iButton=new JButton("I");
            huifuRB=new JCheckBox("�Զ��ظ�");
            autoText=new JTextField("�����£�������ϵ��",25);
            JButton sendButton=new JButton("����");
            JButton closeButton=new JButton("�ر�");

            container.add(new JScrollPane(outText));

            container.add(blodButton);
            container.add(iButton);
            container.add(huifuRB);
            container.add(autoText);
            container.add(new JScrollPane(inText));
            container.add(sendButton);
            container.add(closeButton);
            //container.add(new JScrollPane(b));
            outText.setEditable(false);
            app.setSize(650,600);
            app.setVisible(true);
            app.setResizable(false);
            //app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            huifuRB.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    if(fb)
                        fb=false;
                    else
                        fb=true;
                }
            });

            closeButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    flag[N]=0;
                    app.setVisible(false);
                }
            });
            sendButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    Date d=new Date();
                    String str=new String();
                    Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    str=format.format(d)+" "+nameText.getText()+" :\n"+inText.getText()+"\n";
                    outText.append(str);
                    inText.setText("");
                    s.sendMsg(str,s.Addr);
                }
            });
        }
        public void print(String str)   //�����Ϣ�����촰���ı���
        {
            outText.append(str);

        }
    }

    public static class mainWindow extends Frame  //��������
    {
        int max=10;     //��������û�����
        int i=0;
        int j=0;

        public subWindow []sub;
        private static JButton []user;
        private JTextField nameText;
        public static InetAddress []addr;
        //public static int []flag;
        Container container;

        mainWindow()    //��ʼ����������
        {
            s=new kSocket();
            sub=new subWindow[max];
            user=new JButton[max];
            addr=new InetAddress[max];
            flag=new int[max];
            JTextField statusText;
            JFrame app=new JFrame("mainWindow");
            container=app.getContentPane();
            container.setLayout(new FlowLayout());
            //JButton idButton=new JButton("����");
            JTextField idText=new JTextField("�ǳ�:",3);
            idText.setEditable(false);
            nameText=new JTextField("�����û�",8);
            //container.add(idButton);
            container.add(idText);
            container.add(nameText);
            //JTextArea Text=new JTextArea("",30,16);

            nameText.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try{
                        s.sendMsg("//login:"+nameText.getText(),InetAddress.getByName("192.168.1.255"));
                        // ������ͨ����㲥������Ϣ
                    }
                    catch (IOException e){
                         System.out.println("IOException occurred with socket.");
                         System.out.println(e);
                         e.printStackTrace();
                    }
                }
            });

            for(i=0;i<max;i++)  // ��ʼ�����ݽṹ
            {
                flag[i]=0;
                user[i]=new JButton("�����ŵ�");
                user[i].setEnabled(false);

                try{
                    addr[i]=InetAddress.getByName("");
                }
                catch (IOException e){
                    System.out.println("IOException occurred with socket.");
                    System.out.println(e);
                    e.printStackTrace();
                }

                container.add(user[i]);
                user[i].setSize(200,300);
            }

                //Ϊ��ť��Ӵ����Ӵ�����ļ����¼�
                user[0].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(0);
                    }
                });
                user[1].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(1);
                    }
                });
                user[2].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(2);
                    }
                });
                user[3].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(3);
                    }
                });
                user[4].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(4);
                    }
                });
                user[5].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(5);
                    }
                });
                user[6].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(6);
                    }
                });
                user[7].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(7);
                    }
                });
                user[8].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(8);
                    }
                });
                user[9].addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        makeSub(9);
                    }
                });

            statusText=new JTextField("CopyRight @ �׽���",12);
            statusText.setEditable(false);
            JButton loginB=new JButton("����");
            JButton exitB=new JButton("�˳�");
            app.add(statusText);
            app.add(loginB);
            app.add(exitB);

            // Ϊ���߰�ť��Ӽ����¼�
            loginB.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    try{
                        s.sendMsg("//login:"+nameText.getText(),InetAddress.getByName("192.168.1.255"));
                        // �㲥������ʾ��Ϣ
                    }
                    catch (IOException e){
                        System.out.println("IOException occurred with socket.");
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
            });
            exitB.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                    try{
                        s.sendMsg("//exit:",InetAddress.getByName("192.168.1.255"));
                        // ������ͨ���෢��������Ϣ
                        //s.dSocket.close();
                    }
                    catch (IOException e){
                        System.out.println("IOException occurred with socket.");
                        System.out.println(e);
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            });

            app.setSize(150,445);
            app.setVisible(true);
            app.setResizable(false);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            last();         // ���������������е��ˣ�����whileѭ����������
        }
        public void makeSub(int i)  //�����Ӵ�����
        {
            if(flag[i]==0)
            {
                sub[i]=new subWindow(user[i].getLabel(),nameText.getText(),i);
                flag[i]=1;
            }
        }
         public void last()
         {
           Boolean f;
          while(true)       // ����whileѭ����������
            {
                f=false;
                String str=s.receiveMsg();      // ������ͨ�����������


                if(str.startsWith("//exit"))    // �û����ߴ���
                {
                        for(i=0;i<=j;i++)
                        {
                            System.out.println("hhhhh:  addr[i]: +"+addr[i]+"s.Addr: "+s.Addr+" : "+i);
                            if(addr[i].equals(s.Addr))
                            {
                                System.out.println("addr[i]����s.Addr  "+i+"  "+j);

                                for(int p=i;p<j-1;p++)
                                {
                                    user[p].setLabel(user[p+1].getLabel());
                                    addr[p]=addr[p+1];
                                }
                                user[j-1].setText("�����ŵ�");
                                user[j-1].setEnabled(false);
                                try{
                                    addr[j-1]=InetAddress.getByName(local);
                                }
                                catch(IOException e){
                                    System.out.println("IOException occurred with socket.");
                                    System.out.println(e);
                                    e.printStackTrace();
                                }
                                j--;
                                f=true;
                                break;
                            }
                        }
                        if(f)
                        {
                            continue;
                        }
                }


                if(str.startsWith("//login") && j<max)  // �û����ߴ�����Ӱ�ť�����°�ť
                {

                    try{
                        System.out.println("s.Addr:"+s.Addr+" :  ");

                        for(i=0;i<=j;i++)
                        {
                            System.out.println("hhhhh:  addr[i]: +"+addr[i]+"s.Addr: "+s.Addr+" : "+i);
                            if(addr[i].equals(s.Addr))
                            {
                                System.out.println("addr[i]����s.Addr");
                                f=true;
                                break;
                            }
                        }
                        if(f)
                        {
                            user[i].setLabel(str.split(":")[1]);
                            continue;
                        }

                        if(s.Addr.equals(InetAddress.getByName(local)));
                        {
                            System.out.println("���յ�Localhost��������");
                            //if(false)
                            //break;
                        }

                        user[j].setLabel(str.split(":")[1]);
                        user[j].setEnabled(true);
                    }
                    catch (IOException e){
                        System.out.println("IOException occurred with socket.");
                        System.out.println(e);
                        e.printStackTrace();
                    }
                    if(j>0 && !str.startsWith("//login_reply"))
                    {
                        s.sendMsg("//login_reply:"+nameText.getText(),s.Addr);  // ���״η���������ʾ����������Ӧ��Ϣ
                        System.out.println("send //login_reply to"+s.Addr);
                    }

                    addr[j++]=s.Addr;   // ������ͨ��ip��ַ���洢�ڶ�Ӧ������Ԫ����
                }
                for(int i=0;i<j;i++)
                {

                    if(flag[i]==1)      // �Ӵ����Ѿ�����
                    {

                        System.out.println(addr[i]+"||"+s.Addr);
                        if(addr[i].equals(s.Addr))  //�ҵ���ַ��Ӧ���Ӵ��ڣ�����ʾ��Ϣ
                        {
                            sub[i].print(str);

                            if(sub[i].fb)
                            {
                                Date d=new Date();
                                String str2=new String();
                                Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                str2=format.format(d)+" "+nameText.getText()+" :\n"+"�Զ��ظ�:"+sub[i].autoText.getText()+"\n";
                                sub[i].outText.append(str2);
                                s.sendMsg(str2,s.Addr);
                            }
                        }
                    }
                    else
                    {
                        if(addr[i].equals(s.Addr) && !str.startsWith("//login") && !str.startsWith("//exit"))
                        // �ҵ���ַ��Ӧ���Ӵ��ڣ�str��Ϊ���ߡ�������ʾ��Ϣ
                        {
                            makeSub(i);
                            sub[i].print(str);
                            if(sub[i].fb)
                            {
                                Date d=new Date();
                                String str2=new String();
                                Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                str2=format.format(d)+" "+nameText.getText()+" :\n"+"�Զ��ظ�:"+sub[i].autoText.getText()+"\n";
                                sub[i].outText.append(str2);
                                s.sendMsg(str2,s.Addr);
                            }
                        }
                    }
                }

                for(int k=0;k<max;k++)
                {
                    System.out.println("flag["+k+"]:"+flag[k]);
                    System.out.println("addr["+k+"]:"+addr[k]);
                }

            }
        }
    }

    public static void main(String args[])  //main������
    {
        mainWindow main=new mainWindow();   //������������
    }

}
