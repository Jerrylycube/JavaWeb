import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {


    JButton register = new JButton();

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField passwordAgain = new JPasswordField();


    public RegisterJFrame() {
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("ptgame\\image\\register\\注册用户名.png"));
        usernameText.setBounds(100, 135, 80, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框

        username.setBounds(195, 135, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("ptgame\\image\\register\\注册密码.png"));
        passwordText.setBounds(100, 190, 80, 20);
        this.getContentPane().add(passwordText);
        //4.密码输入框
        password.setBounds(195, 190, 200, 30);
        this.getContentPane().add(password);

        //5.再次输入密码提示
        JLabel passwordAgainText = new JLabel(new ImageIcon("ptgame\\image\\register\\再次输入密码.png"));
        passwordAgainText.setBounds(90, 255, 100, 16);
        this.getContentPane().add(passwordAgainText);
        //5.再次密码输入框
        passwordAgain.setBounds(195, 250, 200, 30);
        this.getContentPane().add(passwordAgain);

        //6.添加注册按钮
        register.setBounds(180, 310, 128, 47);
        register.setIcon(new ImageIcon("ptgame\\image\\register\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("ptgame\\image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object option = e.getSource();
        if (option == register) {
            String u = username.getText();
            String passwordContent = new String(password.getPassword());
            String passwordAgainContent = new String(passwordAgain.getPassword());
            String usernameContent = username.getText();

            if (usernameContain(u)) {
                showJDialog("用户名已存在");
            } else if (usernameContent.isEmpty()) {
                showJDialog("用户名为空");
            } else if (passwordContent.isEmpty()) {
                showJDialog("注册密码为空");
            } else if (passwordAgainContent.isEmpty()) {
                showJDialog("再次注册密码为空");
            } else if (!passwordContent.equals(passwordAgainContent)) {
                showJDialog("俩次输入密码不一致");
            } else {
                User uTemp = new User(usernameContent, passwordContent);
                LoginJFrame.allUsers.add(uTemp);
                showJDialog("注册成功，请返回登录");
                this.setVisible(false);
                new LoginJFrame();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object option = e.getSource();
        if (option == register) {
            register.setIcon(new ImageIcon("ptgame\\image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object option = e.getSource();
        if (option == register) {
            register.setIcon(new ImageIcon("ptgame\\image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    public boolean usernameContain(String u) {
        for (int i = 0; i < LoginJFrame.allUsers.size(); i++) {
            String username = LoginJFrame.allUsers.get(i).getUsername();
            if (username.equals(u)) return true;
        }
        return false;
    }


}