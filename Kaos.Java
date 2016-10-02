package learn;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Kaos extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Image                     the_picture;
    Image                     the_modified_picture;
    PixelGrabber              pg;
    PixelGrabber              pg2;
    MediaTracker              the_tracker;
    Panel                     panel2;
    Panel                     panel3;
    Panel                     panel4;
    Button                    button2;
    Button                    button3;
    Choice                    choice1;
    Label                     label1;
    Label                     label2;
    Label                     label3;
    Label                     label4;
    boolean                   image_ready;
    boolean                   modi             = false;
    int[]                     pixels;
    int[]                     yeni;
    int                       max_x;
    int                       max_y;
    int                       sayac;
    int                       on               = 0;
    int                       artis;
    int                       max;
    String                    paramString;
    
    public Kaos() {
        init();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Kaos();
            }
        });
    }
    
    void button2_Clicked() {
        this.artis -= 1;
        this.button3.setEnabled(true);
        if (this.artis == 0) {
            this.button2.setEnabled(false);
        }
        else {
            this.button2.setEnabled(true);
        }
        _KONTI();
        transform_image(false);
    }
    
    void button3_Clicked() {
        this.artis += 1;
        this.button2.setEnabled(true);
        if (this.artis == this.max) {
            this.button3.setEnabled(false);
        }
        else {
            this.button3.setEnabled(true);
        }
        _KONTI();
        transform_image(true);
    }
    
    void choice1_Action() {
        _DEGISTIR();
    }
    
    public void _KONTI() {
        this.label3.setText(String.valueOf(this.artis) + ". Adim");
    }
    
    void _DEGISTIR() {
        String str = "";
        switch (this.choice1.getSelectedIndex()) {
            case 0:
                str = "resim1.jpg";
                break;
            case 1:
                str = "resim2.jpg";
                break;
            case 2:
                str = "resim3.jpg";
                break;
            case 3:
                str = "ata.jpg";
                break;
            case 4:
                this.paramString = this.choice1.getItem(this.choice1.getSelectedIndex());
                str = this.paramString;
        }
        System.out.println(this.choice1.getSelectedIndex());
        // System.out.println(System.get str);
        Map<String, String> env = System.getenv();
        // Properties p = System.getProperties();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
        // p.list(System.out);
        System.out.println("user dir: " + System.getProperty("user.dir"));
        System.out.println("javaclass path: " + System.getProperty("java.class.path"));
        
        try {
            File img = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "resimler"
                            + System.getProperty("file.separator") + str);
            this.the_picture = ImageIO.read(img);
            // this.the_picture = ImageIO.
            // .read(Class.class.getResource(System.getProperty("user.dir") +
            // System.getProperty("file.separator") + str));
        }
        catch (Exception localException) {
            localException.printStackTrace();
            // System.out.println(localException.toString());
        }
        this.the_tracker.addImage(this.the_picture, 0);
        
        this.image_ready = false;
        this.modi = false;
        this.button2.setEnabled(false);
        this.button3.setEnabled(false);
        this.artis = (this.max = 0);
        this.label3.setText(String.valueOf(this.artis) + ". Adim");
        this.label4.setText("X Adim");
        run();
    }
    
    public int bul(int paramInt) {
        this.sayac = 0;
        int m = 0;
        int i = 0;
        int j = 1;
        while (m == 0) {
            int k = i;
            i = (i + j) % paramInt;
            j = (k + 2 * j) % paramInt;
            if ((i == 0) && (j == 1))
                m = 1;
            this.sayac += 1;
        }
        return this.sayac;
    }
    
    public void run() {
        int i = 100;
        while (!this.image_ready) {
            update();
            try {
                Thread.sleep(i);
            }
            catch (InterruptedException localInterruptedException) {
            }
        }
        if (this.image_ready) {
            _HAZIR();
            System.out.println("Buraya kadar geldi");
            ;
        }
        
    }
    
    public void _HAZIR() {
        int i = this.the_picture.getWidth(this);
        int j = this.the_picture.getHeight(this);
        if (i <= j) {
            this.on = i;
        }
        else {
            this.on = j;
        }
        this.max = bul(this.on);
        this.label4.setText(String.valueOf(this.max) + " Adim");
        this.button3.setEnabled(true);
        this.max_x = this.on;
        this.max_y = this.on;
        this.pixels = new int[this.max_x * this.max_y];
        this.yeni = new int[this.max_x * this.max_y];
        this.pg = new PixelGrabber(this.the_picture, 0, 0, this.max_x, this.max_y, this.pixels, 0, this.max_y);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
        }
        repaint();
    }
    
    public void init() {
        this.the_tracker = new MediaTracker(this);
        this.image_ready = false;
        this.artis = (this.max = 0);
        setLayout(null);
        addNotify();
        this.setSize(600, 500);
        setBackground(new Color(184, 186, 177));
        
        this.panel3 = new Panel();
        this.panel3.setLayout(null);
        this.panel3.setBounds(0, 0, 600, 24);
        this.panel3.setBackground(new Color(163, 166, 155));
        add(this.panel3);
        
        this.choice1 = new Choice();
        this.choice1.addItem("Resim 1");
        this.choice1.addItem("Resim 2");
        this.choice1.addItem("Resim 3");
        this.choice1.addItem("Atatürk");
        this.choice1.addItem("Parametre");
        this.choice1.setBounds(360, 0, 100, 21);
        this.panel3.add(this.choice1);
        
        this.label1 = new Label("KAOS: Arnold's Cat Map Algoritmasi");
        this.label1.setBounds(12, 0, 228, 24);
        this.label1.setFont(new Font("Dialog", 0, 12));
        this.label1.setBackground(new Color(163, 166, 155));
        this.panel3.add(this.label1);
        
        this.panel4 = new Panel();
        this.panel4.setLayout(null);
        this.panel4.setBounds(0, 476, 600, 24);
        this.panel4.setBackground(new Color(163, 166, 155));
        add(this.panel4);
        
        this.label2 = new Label("Sinan Duman, Ahmet Çubukçu: IKÜ Bitirme Tezi");
        this.label2.setBounds(12, 0, 264, 24);
        this.label2.setFont(new Font("Dialog", 0, 12));
        this.label2.setBackground(new Color(163, 166, 155));
        this.panel4.add(this.label2);
        
        this.button2 = new Button("<");
        this.button2.setBounds(350, 2, 20, 20);
        this.panel4.add(this.button2);
        
        this.button3 = new Button(">");
        this.button3.setBounds(450, 2, 20, 20);
        this.panel4.add(this.button3);
        
        this.label3 = new Label("0. Adim");
        this.label3.setBounds(380, 2, 80, 20);
        this.label3.setFont(new Font("Dialog", 0, 12));
        this.label3.setBackground(new Color(163, 166, 155));
        this.panel4.add(this.label3);
        
        this.label4 = new Label("X Adim");
        this.label4.setBounds(480, 2, 80, 20);
        this.label4.setFont(new Font("Dialog", 0, 12));
        this.label4.setBackground(new Color(163, 166, 155));
        this.panel4.add(this.label4);
        _DEGISTIR();
    }
    
    public void transform_image(boolean paramBoolean) {
        int i;
        int j;
        int k;
        int m;
        if (paramBoolean) {
            for (j = 0; j < this.max_y; j++) {
                for (i = 0; i < this.max_x; i++) {
                    k = (i + j) % this.max_x;
                    m = (i + 2 * j) % this.max_y;
                    
                    this.yeni[(m * this.max_y + k)] = this.pixels[(j * this.max_y + i)];
                }
            }
        }
        else {
            for (j = 0; j < this.max_y; j++) {
                for (i = 0; i < this.max_x; i++) {
                    m = (this.max_y + (j - i)) % this.max_y;
                    k = (this.max_y + (i - m)) % this.max_y;
                    
                    this.yeni[(m * this.max_y + k)] = this.pixels[(j * this.max_y + i)];
                }
            }
            
        }
        
        for (j = 0; j < this.max_y * this.max_x; j++) {
            this.pixels[j] = this.yeni[j];
        }
        
        this.modi = true;
        this.the_modified_picture = createImage(new MemoryImageSource(this.max_x, this.max_y, this.yeni, 0, this.max_y));
        repaint();
    }
    
    public void update() {
        if (this.the_tracker.checkID(0, true))
            this.image_ready = true;
    }
    
    public void paint(Graphics paramGraphics) {
        if (this.image_ready)
            try {
                if (this.modi) {
                    paramGraphics.drawImage(this.the_modified_picture, (600 - this.max_x) / 2,
                                    50 + (400 - this.max_y) / 2, this);
                }
                else
                    paramGraphics.drawImage(this.the_picture, (600 - this.max_x) / 2, 50 + (400 - this.max_y) / 2, this);
            }
            catch (Exception localException) {
                localException.printStackTrace();
            }
    }
    
    public boolean handleEvent(Event paramEvent) {
        if (paramEvent.target == this.choice1 && paramEvent.id == 1001) {
            choice1_Action();
            return true;
        }
        if (paramEvent.target == this.button2 && paramEvent.id == 1001) {
            button2_Clicked();
            return true;
        }
        if (paramEvent.target == this.button3 && paramEvent.id == 1001) {
            button3_Clicked();
            return true;
        }
        return super.handleEvent(paramEvent);
    }
}
