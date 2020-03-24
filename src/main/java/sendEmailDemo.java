import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.nio.charset.CoderMalfunctionError;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class sendEmailDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.163.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        try {
            // 使用java发送邮件5步骤
            // 1.创建sesssion
            Session session = Session.getInstance(prop);
            // 开启session的调试模式，可以查看当前邮件发送状态
            session.setDebug(true);

            Transport ts;
            // 2.通过session获取Transport对象（发送邮件的核心API）
            ts = session.getTransport();
            // 3.通过邮件用户名密码链接
            ts.connect("yanglei20011123@163.com", "ZYCSSOVCAQOLQUVR");
            // 4.创建邮件
            Message msg = createSimpleMail(session);
            // 5.发送电子邮件
            ts.sendMessage(msg, msg.getAllRecipients());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static MimeMessage createSimpleMail(Session session)
            throws AddressException, MessagingException {
        // 创建邮件对象
        MimeMessage mm = new MimeMessage(session);
        // 设置发件人
        mm.setFrom(new InternetAddress("yanglei20011123@163.com"));
        // 设置收件人
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(
                "yanglei20011123@163.com"));
        // 设置抄送人
        //mm.setRecipient(Message.RecipientType.CC, new InternetAddress(
        //      "用户名@163.com"));

        mm.setSubject("Hello");
        // 标题
//        mm.setContent("Hello World", "text/html;charset=utf-8");
        // 正文
//        mm.setText("hello world","UTF-8");
        // 文本
        sendEmailDemo.creatHtml();
        // 创建文件
        mm.setText(getHtmlString(),"UTF-8", "html");
        // html

        return mm;
    }
    public static String getHtmlString()  {
        /**
         * 读取index.html文件，返回一个字符串
         */
        StringBuilder reception= new StringBuilder();
        try {
            File file = new File("/Users/yanglei/Desktop/MavenDemo/index.html");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                reception.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(reception);
    }
    public static void creatHtml()  {
        /*
         *  下载文件
         */
        String cmd="fuck wget -O index.html www.google.com";
        try {
            Runtime.getRuntime().exec(cmd);
            Thread.sleep(10000);
            // 停一下
        }catch (IOException | InterruptedException e){
            System.out.println("创建文件失败");
        }


    }
}
