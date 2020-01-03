import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import static java.lang.Math.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/*
//背景画像を保持するクラス
class Background extends Component {
    BufferedImage bufferedImage = null;//[101]
    Background() {//JPEG読み込みその画像を持つbufferedImageオブジェクト作成
        try {
            bufferedImage = ImageIO.read(new File("map3.png"));//ImageIO.readはbufferedImage型を
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

*/


//.....↓Airplaneクラス(飛行機が持つの性質) ......//
class Airplane {
protected int x,y,vx,vy,ex,ey,size;               //ex,eyは画像の中心座標 x,yは左上の座標 sizeは大きさ
protected double kakudo;                          //進行方向に傾けるための角度
protected boolean flag;                           //クリックモード1で選択されたかを判定する変数
protected boolean coll;
protected boolean add;
protected boolean bcoll;
private ArrayList<Airplane> plane;
//protected int block[][];
//private JLabel label;
Airplane() {
        x = 0;                                   //初期座標と初期速度を設定
        y = 0;
        vx = 0;
        vy = 0;
        kakudo = Math.atan(-1*(float)vy/(float)vx); //最初の進行方向に応じた角度を設定
        if(vx<0) {kakudo = PI + kakudo; }
        size=(int)(Math.random()*30+40);          //大きさもランダム
        ex = x+size/2;                            //中心座標も保存(画像の回転に使用するため)
        ey = y+size/2;
        flag = false;                             //最初は選択されていないのでflaseにする
}

void update(int s) {                              //PlanePanelのactionPerformed内で0.1秒ごとに呼び出し。座標を更新。
        if(Math.random()<1) {x += vx; y += vy;} //カクカク動くようにランダムで更新
        if(x<0 || x+size>s) {vx *= -1;}           //画面端で跳ね返る
        if(y<0 || y+size>s) {vy *= -1;}
        ex = x+size/2;                            //中心座標も更新
        ey = y+size/2;
        kakudo = Math.atan(-1*(float)vy/(float)vx); //速度から角度を変更
        if(vx<0) {kakudo = PI + kakudo; }
}

void updatev(int a,int b){                        //(2:移動方向選択モード)でクリックした座標に向かうように速度を変更する関数
        vx = (int)(4*(a-ex)/Math.sqrt((a-ex)*(a-ex)+(b-ey)*(b-ey)));
        vy = (int)(4*(b-ey)/Math.sqrt((a-ex)*(a-ex)+(b-ey)*(b-ey)));
        kakudo = Math.atan(-1*(float)vy/(float)vx); //速度から角度を変更
        if(vx<0) {kakudo = PI + kakudo; }
}

void addplane(){
  x = (int)(Math.random()*400);                                   //初期座標と初期速度を設定
  y = (int)(Math.random()*400);
  vx = (int)(Math.random()*5);
  vy = (int)(Math.random()*5);
  kakudo = Math.atan(-1*(float)vy/(float)vx);
  add = true;
}

void removeplane(){
  vx = 0;
  vy = 0;
  x = 90;
  y = 30;
 changeadd();
}

void updatewarn(Airplane g){
      int x2 = g.getX();
      int y2 = g.getY();
      int size2 = g.getsize();

  double dist = Math.sqrt(Math.pow(ex-x2-size2/2,2)+Math.pow(ey-y2-size2/2,2));
  if(dist != 0){
  if(dist<size+size2){
    if(coll==false){
      changecoll();
    }
  }else{
    resetcoll();
  }
  }
  }

  void coll(Airplane g){
    int b[][] = addblock();
    for(int i=0; i<b.length; i++){
    int bx = b[i][0];
    int by = b[i][1];
    int bsize = b[i][2];

    double dist = Math.sqrt(Math.pow(ex-bx-bsize,2)+Math.pow(ey-by-bsize,2));
    if(dist<size/2+bsize){
      if(bcoll==false){
        changebcoll();
    }
  }else{
      resetbcoll();
    }
    }

  }

  //int[][] addblock(){
  //  int block[][]={{200,100,20},{300,400,20}};
  //  return block;
  //}


  boolean getbcoll(){
    return bcoll;
  }

  void changebcoll(){
   bcoll =! bcoll;
  }

  void resetbcoll(){
    bcoll = false;
  }

  boolean getcoll(){
    return coll;
  }
  void changecoll(){
    coll = !coll;
  }
  void resetcoll(){
    coll = false;
  }

  boolean getadd(){
    return add;
  }

void changeadd(){
  add =! add;
}

void draw(Graphics g) {
}
int getX(){
        return x;
}
int getY(){
        return y;
}
int getsize(){
        return size;
}
    public void setX(int s){
	this.x=s;
    }
    public void setY(int s){
	this.y=s;
    }
boolean getflag(){
        return flag;
}
void changeflag(){
        flag = !flag;
}
}

class A extends Airplane {           //Aタイプの飛行機
Image img1,img1x;
ImageIcon i1,ix1;
A(){
        i1 = new ImageIcon( "a.png" ); //画像読み込み
        ix1= new ImageIcon( "ax.png" );
        img1 = i1.getImage();
        img1x = ix1.getImage();
}

void draw(Graphics g) {              //進行方向に回転させて描画
        double rotatekakudo = 0.5*PI - kakudo;
        Graphics2D g2d = (Graphics2D)g;
        if(flag) {
                g2d.rotate(rotatekakudo,ex,ey);
                g2d.drawImage(img1x,x,y,size,size,null);
                g2d.rotate(-rotatekakudo,ex,ey);
        }else{
                g2d.rotate(rotatekakudo,ex,ey);
                g2d.drawImage(img1,x,y,size,size, null);
                g2d.rotate(-rotatekakudo,ex,ey);
        }
}
}

class B extends Airplane {      //Bタイプの飛行機
Image img2,img2x;
ImageIcon i2,ix2;
B(){
        i2 = new ImageIcon( "b.png" );
        ix2= new ImageIcon( "bx.png" );
        img2 = i2.getImage();
        img2x = ix2.getImage();
}

void draw(Graphics g) {
        double rotatekakudo = 0.5*PI - kakudo;
        Graphics2D g2d = (Graphics2D)g;
        if(flag) {
                g2d.rotate(rotatekakudo,ex,ey);
                g2d.drawImage(img2x,x,y,size,size,null);
                g2d.rotate(-rotatekakudo,ex,ey);
        }else{
                g2d.rotate(rotatekakudo,ex,ey);
                g2d.drawImage(img2,x,y,size,size, null);
                g2d.rotate(-rotatekakudo,ex,ey);
        }
}
}



//..................↓PlanePanelクラス(飛行機などの描画を処理) .........................//
class PlanePanel extends JPanel implements ActionListener,MouseListener {
private Timer timer;                  //0.1秒毎に更新
private final static int NUM=2;       //各飛行機の数
private ArrayList<Airplane> plane;    //全飛行機を格納するArrayList
private JLabel label;                 //クリック座標を表示するJLabel
private int clickmode;                //クリックモードを判断する変数 (1:飛行機選択モード 2:移動方向選択モード)
private int size;                     //画面サイズ
private ArrayList<ArrayList<Block>> block;



//..................↓コンストラクタ.........................//
PlanePanel(int s){                   //sに以下、Airfieldのサイズを渡す
        plane=new ArrayList<Airplane>(); //ArraListを初期化して、指定した数、飛行機を追加していく。
        for(int i=0; i<NUM; i++) {
                plane.add(new B());
                plane.add(new A());
        }
        block = new ArrayList<Block>;
        block={{200,100,20},{300,400,20}};

        size = s;                                   //画面の大きさを保存
        this.addMouseListener(this);                //マウスリスナーに登録
        this.setBackground(Color.white);            //背景を白
        clickmode = 1;                              //クリックモードを(1:飛行機選択モード)にする
        label =  new JLabel("x:?,y:?",JLabel.CENTER);//座標を表示させるラベルを追加
        label.setLayout(new GridLayout(2,1));       //レイアウトを2,1にする
        this.add(label);                            //パネルにラベルを追加する

        timer = new Timer(100, this);               // 0.1秒毎にactionPerformedを呼び出し
        timer.start();                              // タイマーをスタート
}

//..................↑コンストラクタ.........................//



    //..........↓マウスクリック時の操作..............//

public void mouseReleased(MouseEvent e){
        Point point = e.getPoint();                   //クリック位置を取得
        label.setText("x:" + point.x + ",y:" + point.y); //ラベルにクリック位置を表示
        if(clickmode==1) {//1:飛行機選択モード↓↓↓↓
                for(Airplane f: plane) {
                        if( f.getX() < point.x &&  point.x < f.getX()+f.getsize() ) {
                                if( f.getY() < point.y &&  point.y < f.getY()+f.getsize() ) {
                                        f.changeflag(); //クリック位置が飛行機と触れているかをチェック、フラグを変更し画像を変更
                                        clickmode = 2;
                                        return;
                                }
                        }
                }
        }
        //1:飛行機選択モード↑↑↑↑↑↑

        if(clickmode==2) {//2:移動方向選択モード↓↓↓↓↓↓
                for(Airplane f: plane) {
                        if(f.getflag()) { //モード1で選択されている飛行機かをチェック
                                f.updatev(point.x,point.y);//クリックした点の座標を与えて速度を変更
                                f.changeflag();
                                clickmode = 1;
                                return;
                        }
                }
        }
        //2:移動方向選択モード↑↑↑↑↑↑↑
}
//..........↑マウスクリック時の操作..............//

//..........↓その他..............//
public void mouseEntered(MouseEvent e){
}
public void mouseExited(MouseEvent e){

}
public void mousePressed(MouseEvent e){
}
public void mouseClicked(MouseEvent e){
}
public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Airplane b = new Airplane();
        //int block[][] = b.addblock();
        g.fillOval(100,100,20,20);
        for(int i=0;i<block[0].length;i++){
          int bx = block[i][0];
          int by =block[i][1];
          int bsize = block[i][2];
          g.fillOval(bx,by,bsize,bsize);
        }
        //g.fillRect(200,100,20,20);
        //g.fillRect(300,400,20,20);
        //int block[][];
        //block = new int[2][3];
        //int block[][]={{200,100,20},{300,400,20}};
	for(Airplane f: plane){
	    f.draw(g);
    }
	//plane.forEach(i -> label.setText(String.valueOf(i)));
}


public void actionPerformed(ActionEvent e){
boolean coll,bcoll;
boolean add;
        for(Airplane f: plane) {
                f.update(size);
                add = f.getadd();
                if(add==false){
                double r = Math.random();
                //if(r>0.2){
                  f.addplane();
                //}
              }
                  for(Airplane g:plane){

                  f.updatewarn(g);
                  f.coll(g);
                  coll = g.getcoll();
                  bcoll = g.getbcoll();
                  if(bcoll){
                    label.setText("warning");
                //    g.removeplane();
                    //f.endgame();
                    //g.endgame();
                  }else if(coll){
                    label.setText("Failure");
                  }else{
                    label.setText("noun");
                  }
                }
        }
        this.repaint();
      }
}
//..................↑PlanePanelクラス.........................//



class Airfield extends JFrame {
    private int size;                                           //画面のサイズ
    public Airfield(){
	     size = 600;
        this.setTitle("Random Frame");
        this.setSize(size,size);
        this.add(new PlanePanel(size));                    //RandomPnelをAirfieldに追加
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String argv[]) {
        new Airfield();
    }
}

/*class Collision extends Airplane{//到着判定
    private int x, y, width, height;
    private Image image;
    private Airfield panel;
    private static final Point Strage = new Point(-20,-20);
    private boolean coll;
    private int NUM =2;

    public Collision(Airfield field){
	x = Strage.x;
	y = Strage.y;
	this.panel=panel;
	loadImage();
    }

    public Point getPos(){
	return new Point(x,y);
    }

    public void setPos(int x, int y){
	this.x=x;
	this.y=y;
    }

    public int getHeight(){
	return height;
    }

    public int getWidth(){
	return width;
    }

    public void store(){
	x=Strage.x;
	y=Strage.y;
    }

    public boolean InStrage(){
	if(x==Strage.x && y==Strage.x)
	    return true;
	return false;
    }

    public void draw(Graphics g){
	g.drawImage(image,x,y,null);
    }

    private void loadImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("a.png"));
        image = icon.getImage();
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }


    public boolean getPlane(Collision plane){
        Rectangle rectplane1 = new Rectangle(x,y,width,height);
        Point pos = plane.getPos();
        Rectangle rectplane2 = new Rectangle(pos.x,pos.y,plane.getWidth(),plane.getHeight());
        return rectplane1.intersects(rectplane2);
    }
    public void restore(){
	setPos(Strage.x,Strage.y);
    }
    /*  private void CollisionDecision(){
	ImageIcon[] p = {i1,i2};
	for(int i=0;i<NUM;i++){
	    for(int j=i+1;j<NUM;j++){
		if(p[i].getPlane(p[j])){
		    p[i].restore();
		    p[j].restore();
		}
	    }
	}
	}
}*/
