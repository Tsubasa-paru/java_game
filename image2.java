import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
//背景画像を保持するクラス
class Background extends Component {
    BufferedImage bufferedImage = null;//[101]
    Background() {//JPEG読み込みその画像を持つbufferedImageオブジェクト作成
        try {
            bufferedImage = ImageIO.read(new File("1583312.jpg"));//ImageIO.readはbufferedImage型を
        } catch (IOException e) {                                 //返すメソッド
            System.out.println("image file not found. [" + "1583312.jpg" + "]");//[107]
        }
    }
    public void paint(Graphics graphics) {
        graphics.drawImage(bufferedImage, 0, 0, null);//画像表示
    }
    public Dimension getPreferredSize() {//getPreferredSizeメソッド
        int width = 100;//画像が読み込めれば画像サイズに、なければ100,100サイズに画面固定
        int height = 100;
        if (bufferedImage != null) {
            width = bufferedImage.getWidth(null);
            height = bufferedImage.getHeight(null);
        }
        return new Dimension(width, height);//[128]
    }
    void writeImage() {//空のwriteImageメソッド
    }
}
//障害物の描画
class PaintImageComponent extends Background {//[200]
    int r = 255;
    int g = 0;
    int b = 0;
    int t = 80;
    public void paint(Graphics graphics) {//paintはこのオブジェクトの最初の表示で自動呼び出しされる
        Graphics2D graphics2D1 = bufferedImage.createGraphics();//[202]
        graphics2D1.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        BasicStroke wideStroke = new BasicStroke(2.0f);//[204]
        graphics2D1.setStroke(wideStroke);//[205]
        graphics2D1.fillOval(400, 400, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D2 = bufferedImage.createGraphics();//[202]
        graphics2D2.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D2.setStroke(wideStroke);//[205]
        graphics2D2.fillOval(134, 567, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D3 = bufferedImage.createGraphics();//[202]
        graphics2D3.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D3.setStroke(wideStroke);//[205]
        graphics2D3.fillOval(134, 178, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D4 = bufferedImage.createGraphics();//[202]
        graphics2D4.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D4.setStroke(wideStroke);//[205]
        graphics2D4.fillOval(310, 780, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D5 = bufferedImage.createGraphics();//[202]
        graphics2D5.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D5.setStroke(wideStroke);//[205]
        graphics2D5.fillOval(570, 60, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D6 = bufferedImage.createGraphics();//[202]
        graphics2D6.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D6.setStroke(wideStroke);//[205]
        graphics2D6.fillOval(595, 800, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D7 = bufferedImage.createGraphics();//[202]
        graphics2D7.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D7.setStroke(wideStroke);//[205]
        graphics2D7.fillOval(650, 250, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D8 = bufferedImage.createGraphics();//[202]
        graphics2D8.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D8.setStroke(wideStroke);//[205]
        graphics2D8.fillOval(690, 430, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D9 = bufferedImage.createGraphics();//[202]
        graphics2D9.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D9.setStroke(wideStroke);//[205]
        graphics2D9.fillOval(770, 850, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D10 = bufferedImage.createGraphics();//[202]
        graphics2D10.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D10.setStroke(wideStroke);//[205]
        graphics2D10.fillOval(830, 130, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D11 = bufferedImage.createGraphics();//[202]
        graphics2D11.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D11.setStroke(wideStroke);//[205]
        graphics2D11.fillOval(500, 600, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D12 = bufferedImage.createGraphics();//[202]
        graphics2D12.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D12.setStroke(wideStroke);//[205]
        graphics2D12.fillOval(890, 610, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D13 = bufferedImage.createGraphics();//[202]
        graphics2D13.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D13.setStroke(wideStroke);//[205]
        graphics2D13.fillOval(950, 60, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D14 = bufferedImage.createGraphics();//[202]
        graphics2D14.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D14.setStroke(wideStroke);//[205]
        graphics2D14.fillOval(1000, 220, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D15 = bufferedImage.createGraphics();//[202]
        graphics2D15.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D15.setStroke(wideStroke);//[205]
        graphics2D15.fillOval(1050, 350, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D16 = bufferedImage.createGraphics();//[202]
        graphics2D16.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D16.setStroke(wideStroke);//[205]
        graphics2D16.fillOval(1100, 530, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D17 = bufferedImage.createGraphics();//[202]
        graphics2D17.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D17.setStroke(wideStroke);//[205]
        graphics2D17.fillOval(1150, 770, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D18 = bufferedImage.createGraphics();//[202]
        graphics2D18.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D18.setStroke(wideStroke);//[205]
        graphics2D18.fillOval(1200, 420, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D19 = bufferedImage.createGraphics();//[202]
        graphics2D19.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D19.setStroke(wideStroke);//[205]
        graphics2D19.fillOval(1250, 900, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D20 = bufferedImage.createGraphics();//[202]
        graphics2D20.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D20.setStroke(wideStroke);//[205]
        graphics2D20.fillOval(1300, 610, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D21 = bufferedImage.createGraphics();//[202]
        graphics2D21.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D21.setStroke(wideStroke);//[205]
        graphics2D21.fillOval(1350, 100, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D22 = bufferedImage.createGraphics();//[202]
        graphics2D22.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D22.setStroke(wideStroke);//[205]
        graphics2D22.fillOval(1400, 300, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D23 = bufferedImage.createGraphics();//[202]
        graphics2D23.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D23.setStroke(wideStroke);//[205]
        graphics2D23.fillOval(1450, 870, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D24 = bufferedImage.createGraphics();//[202]
        graphics2D24.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D24.setStroke(wideStroke);//[205]
        graphics2D24.fillOval(1500, 500, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D25 = bufferedImage.createGraphics();//[202]
        graphics2D25.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D25.setStroke(wideStroke);//[205]
        graphics2D25.fillOval(1550, 710, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D26 = bufferedImage.createGraphics();//[202]
        graphics2D26.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D26.setStroke(wideStroke);//[205]
        graphics2D26.fillOval(1600, 230, 50, 50);//(左上x,左上y,幅,高さ)

        Graphics2D graphics2D27 = bufferedImage.createGraphics();//[202]
        graphics2D27.setColor(new Color(r,g,b,t));//[203](r,g,b,濃度)
        graphics2D27.setStroke(wideStroke);//[205]
        graphics2D27.fillOval(100, 830, 50, 50);//(左上x,左上y,幅,高さ)
        graphics.drawImage(bufferedImage, 0, 0, null);//画像表示
    }
}
//自機の描画
class Plane extends Component {
    BufferedImage bufferedImage = null;//[101]
    Plane() {//JPEG読み込みその画像を持つbufferedImageオブジェクト作成
        try {
            bufferedImage = ImageIO.read(new File("plane.png"));//ImageIO.readはbufferedImage型を
        } catch (IOException e) {                                 //返すメソッド
            System.out.println("image file not found. [" + "plane.png" + "]");
        }
    }
    public void paint(Graphics graphics) {
        graphics.drawImage(bufferedImage, 0, 0, null);//画像表示
    }
    public Dimension getPreferredSize() {//getPreferredSizeメソッド
        int width = 100;//画像が読み込めれば画像サイズに、なければ100,100サイズに画面固定
        int height = 100;
        if (bufferedImage != null) {
            width = bufferedImage.getWidth(null);
            height = bufferedImage.getHeight(null);
        }
        return new Dimension(width, height);//[128]
    }
    void writeImage() {//空のwriteImageメソッド
    }
}

//滑走路の描画
class End extends Component {
    BufferedImage bufferedImage = null;//[101]
    End() {//JPEG読み込みその画像を持つbufferedImageオブジェクト作成
        try {
            bufferedImage = ImageIO.read(new File("end.png"));//ImageIO.readはbufferedImage型を
        } catch (IOException e) {                                 //返すメソッド
            System.out.println("image file not found. [" + "end.png" + "]");
        }
    }
    public void paint(Graphics graphics) {
        graphics.drawImage(bufferedImage, 0, 0, null);//画像表示
    }
}

class Pane extends JLayeredPane {
    Plane plane = null;//component型
    Background background = null;
    End end = null;
    Pane() {
         setLayout(null);
         background = new PaintImageComponent();
         background.setBounds(0,0,1800,1015);
         add(background);
         setLayer(background, DEFAULT_LAYER, 0);//第３引数高いほど表示優先度高

         plane = new Plane();
         plane.setBounds(30,30,32,32);//仮決め、この位置はmodelからgetする
         add(plane);
         setLayer(plane,PALETTE_LAYER, 10);//endより優先

         end = new End();
         end.setBounds(850,430,48,48);
         add(end);
         setLayer(end,PALETTE_LAYER, 0);
    }
}


class View2 extends JFrame implements Observer {
    private Pane pane;
    View2() {
        pane = new Pane();
        add(pane);
        setVisible(true);
        setTitle("メイン画面サンプル");
        setBounds(0,0,1800,1015);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //public void paintComponent(Graphics g) {//再描画に応じ自動呼び出し
    //super.paintComponent(g);　　　　　　　 //自機の向きをgetで入手してその向きを反映させる
        //g.set(g.get());
    //}

    public void update(Observable o,Object arg){//クリックに反応し自機の再描画(向きを変更)
        repaint();                                //paintComponentの呼び出し
    }

    public static void main(String[] args) {
        new View2();
    }
}
