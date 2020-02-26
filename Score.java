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
import javax.imageio.ImageIO;

class Position {
  public static int ix, iy;
  int plane_size = 40;// 飛行機サイズ
  boolean flag_hit = true;// 当たり判定

  int[] a = { 50, 1700 };// x
  int[] b = { 50, 800 };// y

  public void setx(int s) {
    ix = a[s];
  }

  public void sety(int s) {
    iy = b[s];
  }
}

public class Score {
  public static int score = 0;

  int getscore() {
    return score;
  }

  void setscore(int s) {
    score++;
  }
}

// .....↓Airplaneクラス(飛行機が持つの性質) ......//
class Airplane extends Position {
  protected int x, y, vx, vy, ex, ey, size; // ex,eyは画像の中心座標 x,yは左上の座標 sizeは大きさ
  protected double kakudo; // 進行方向に傾けるための角度
  protected boolean flag; // クリックモード1で選択されたかを判定する変数
  protected boolean coll, stcoll; // stcoll,bstcoll障害物
  protected boolean add;
  protected boolean bcoll, bstcoll;
  protected boolean titleflag = true;
  private ArrayList<Airplane> plane;
  protected int ix;
  protected int iy;

  Airplane() {
    int s = (int) (Math.random() * 2);
    Position xy = new Position();
    xy.setx(s);
    xy.sety(s);
    ix = xy.ix; // 初期座標と初期速度を設定
    iy = xy.iy;
    x = ix;
    y = iy;
    vx = 0;
    vy = 0;
    kakudo = Math.atan(-1 * (float) vy / (float) vx); // 最初の進行方向に応じた角度を設定
    if (vx < 0) {
      kakudo = PI + kakudo;
    }
    size = 40; // 大きさ固定にした
    ex = x + size / 2; // 中心座標も保存(画像の回転に使用するため)
    ey = y + size / 2;
    flag = false; // 最初は選択されていないのでflaseにする
  }

  void update(int width, int height) { // PlanePanelのactionPerformed内で0.1秒ごとに呼び出し。座標を更新。
    x += vx;
    y += vy; // カクカク動くようにランダムで更新
    if (x < 0 || x + size > width) {
      vx *= -1;
    } // 画面端で跳ね返る
    if (y < 0 || y + size > height) {
      vy *= -1;
    }
    ex = x + size / 2; // 中心座標も更新
    ey = y + size / 2;
    kakudo = Math.atan(-1 * (float) vy / (float) vx); // 速度から角度を変更
    if (vx < 0) {
      kakudo = PI + kakudo;
    }
  }

  void updatev(int a, int b) { // (2:移動方向選択モード)でクリックした座標に向かうように速度を変更する関数
    vx = (int) (4 * (a - ex) / Math.sqrt((a - ex) * (a - ex) + (b - ey) * (b - ey)));
    vy = (int) (4 * (b - ey) / Math.sqrt((a - ex) * (a - ex) + (b - ey) * (b - ey)));
    kakudo = Math.atan(-1 * (float) vy / (float) vx); // 速度から角度を変更
    if (vx < 0) {
      kakudo = PI + kakudo;
    }
  }

  void addplane() {
    x = (int) (Math.random() * 1500) + 150;// 150~1650までのx座標整数乱数 //初期座標と初期速度を設定
    y = (int) (Math.random() * 715) + 150;// 150~865までのy座標整数乱数
    vx = (int) (Math.random() * 5);
    vy = (int) (Math.random() * 5);
    kakudo = Math.atan(-1 * (float) vy / (float) vx);
  }

  void storeplane() {
    vx = 1;
    vy = 0;
    int s = (int) (Math.random() * 2);
    Position p = new Position();
    p.setx(s);
    p.sety(s);
    ix = p.ix; // 初期座標と初期速度を設定
    iy = p.iy;
    x = ix;// ix,iy
    y = iy;// ここランダム
  }

  boolean store() {
    if (y == iy) {
      return true;
    } else {
      return false;
    }
  }

  void updatewarn(Airplane g) {
    int x2 = g.getX();
    int y2 = g.getY();
    int size2 = g.getsize();

    double dist = Math.sqrt(Math.pow(ex - x2 - size2 / 2, 2) + Math.pow(ey - y2 - size2 / 2, 2));
    if (x != x2) {
      if (dist < size + size2) {
        if (coll == false) {
          changecoll();
        }
      } else {
        resetcoll();
      }
    }
  }

  // stは障害物
  void updateobstacle(Airplane g) {
    int x2 = g.getX();
    int y2 = g.getY();
    int size2 = g.getsize();

    double dist = Math.sqrt(Math.pow(ex - x2 - size2 / 2, 2) + Math.pow(ey - y2 - size2 / 2, 2));
    if (x != x2) {
      if (dist < size + size2) {
        if (stcoll == false) {
          changestcoll();
        }
      } else {
        resetstcoll();
      }
    }
  }

  void coll(int b[][], int i) {
    int bx = b[i][0];
    int by = b[i][1];
    int bsize = b[i][2];

    double dist = Math.sqrt(Math.pow(ex - bx - bsize / 2, 2) + Math.pow(ey - by - bsize / 2, 2));
    if (dist < size / 3 + bsize / 2) {
      if (bcoll == false) {
        changebcoll();
      }
    } else {
      resetbcoll();
    }
  }

  // st
  void stcoll(int b[][], int i) {
    int bx = b[i][0];
    int by = b[i][1];
    int bsize = b[i][2];

    double dist = Math.sqrt(Math.pow(ex - bx - bsize / 2, 2) + Math.pow(ey - by - bsize / 2, 2));
    if (dist < size / 3 + bsize / 2) {
      if (bstcoll == false) {
        changebstcoll();
      }
    } else {
      resetbstcoll();
    }

  }

  int[][] addblock() {// ゴール
    int block[][] = { { 850, 430, 48 } };
    return block;
  }

  int[][] addobstacle() {// 障害物
    int obstacle[][] = { { 400, 400, 50 }, { 134, 567, 50 }, { 134, 178, 50 }, { 310, 780, 50 }, { 570, 60, 50 },
        { 595, 800, 50 }, { 650, 250, 50 }, { 690, 430, 50 }, { 770, 850, 50 }, { 830, 130, 50 }, { 500, 600, 50 },
        { 890, 610, 50 }, { 950, 60, 50 }, { 1000, 220, 50 }, { 1050, 350, 50 }, { 1100, 530, 50 }, { 1150, 770, 50 },
        { 1200, 420, 50 }, { 1250, 900, 50 }, { 1300, 610, 50 }, { 1350, 100, 50 }, { 1400, 300, 50 },
        { 1450, 870, 50 }, { 1500, 500, 50 }, { 1550, 710, 50 }, { 1600, 230, 50 }, { 100, 830, 50 } };
    return obstacle;
  }

  int getIX() {
    int s = (int) (Math.random() * 2);
    Position p = new Position();
    p.setx(s);
    ix = p.ix;
    return ix;
  }

  int getIY() {
    int s = (int) (Math.random() * 2);
    Position p = new Position();
    p.sety(s);
    iy = p.iy;
    return iy;
  }

  boolean getbcoll() {
    return bcoll;
  }

  void changebcoll() {
    bcoll = !bcoll;
  }

  void resetbcoll() {
    bcoll = false;
  }

  boolean getcoll() {
    return coll;
  }

  void changecoll() {
    coll = !coll;
  }

  void resetcoll() {
    coll = false;
  }

  // st
  boolean getbstcoll() {
    return bstcoll;
  }

  void changebstcoll() {
    bstcoll = !bstcoll;
  }

  void resetbstcoll() {
    bstcoll = false;
  }

  boolean getstcoll() {
    return stcoll;
  }

  void changestcoll() {
    stcoll = !stcoll;
  }

  void resetstcoll() {
    stcoll = false;
  }

  boolean getadd() {
    return add;
  }

  void changeadd() {
    add = true;
  }

  void draw(Graphics g) {
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  int getsize() {
    return size;
  }

  public void setX(int s) {
    this.x = s;
  }

  public void setY(int s) {
    this.y = s;
  }

  boolean getflag() {
    return flag;
  }

  void changeflag() {
    flag = !flag;
  }

  // titleに戻る
  public void gotitle() {
    if (titleflag) {
      Start.reStart();
      titleflag = false;
    }
  }
}

class A extends Airplane { // Aタイプの飛行機
  BufferedImage bufferedImagea = null;
  BufferedImage bufferedImageax = null;

  A() {
    try {
      bufferedImagea = ImageIO.read(new File("a.png"));
      bufferedImageax = ImageIO.read(new File("ax.png"));
    } catch (IOException e) {
      System.out.println("image file not found.");
    }
  }

  void draw(Graphics g) { // 進行方向に回転させて描画
    double rotatekakudo = 0.5 * PI - kakudo;
    Graphics2D g2d = (Graphics2D) g;
    if (flag) {
      g2d.rotate(rotatekakudo, ex, ey);
      g2d.drawImage(bufferedImageax, x, y, size, size, null);
      g2d.rotate(-rotatekakudo, ex, ey);
    } else {
      g2d.rotate(rotatekakudo, ex, ey);
      g2d.drawImage(bufferedImagea, x, y, size, size, null);
      g2d.rotate(-rotatekakudo, ex, ey);
    }
  }
}

class B extends Airplane { // Bタイプの飛行機
  BufferedImage bufferedImageb = null;
  BufferedImage bufferedImagebx = null;

  B() {
    try {
      bufferedImageb = ImageIO.read(new File("b.png"));
      bufferedImagebx = ImageIO.read(new File("bx.png"));
    } catch (IOException e) {
      System.out.println("image file not found.");
    }
  }

  void draw(Graphics g) {
    double rotatekakudo = 0.5 * PI - kakudo;
    Graphics2D g2d = (Graphics2D) g;
    if (flag) {
      g2d.rotate(rotatekakudo, ex, ey);
      g2d.drawImage(bufferedImagebx, x, y, size, size, null);
      g2d.rotate(-rotatekakudo, ex, ey);
    } else {
      g2d.rotate(rotatekakudo, ex, ey);
      g2d.drawImage(bufferedImageb, x, y, size, size, null);
      g2d.rotate(-rotatekakudo, ex, ey);
    }
  }
}

// ..................↓PlanePanelクラス(飛行機などの描画を処理) .........................//
class PlanePanel extends JPanel implements ActionListener, MouseListener {
  private Timer timer; // 0.1秒毎に更新
  private final static int NUM = 1; // 各飛行機の数
  private ArrayList<Airplane> plane; // 全飛行機を格納するArrayList
  private JLabel label; // クリック座標を表示するJLabel
  private int clickmode; // クリックモードを判断する変数 (1:飛行機選択モード 2:移動方向選択モード)
  private int sizex, sizey; // 画面サイズ
  private int block[][];
  private int obstacle[][];
  private int NUM_plane = 0;
  boolean stp = false;

  // ..................↓コンストラクタ.........................//
  PlanePanel(int width, int height) { // sに以下、Airfieldのサイズを渡す
    plane = new ArrayList<Airplane>(); // ArraListを初期化して、指定した数、飛行機を追加していく。
    for (int i = 0; i < NUM; i++) {
      plane.add(new B());
      plane.add(new A());
    }

    sizex = width;
    sizey = height; // 画面の大きさを保存
    this.addMouseListener(this); // マウスリスナーに登録
    this.setOpaque(false);
    clickmode = 1; // クリックモードを(1:飛行機選択モード)にする
    label = new JLabel("x:?,y:?", JLabel.CENTER);// 座標を表示させるラベルを追加
    label.setLayout(new GridLayout(2, 1)); // レイアウトを2,1にする
    this.add(label); // パネルにラベルを追加する

    timer = new Timer(90, this); // 0.05秒毎にactionPerformedを呼び出し
    timer.start(); // タイマーをスタート
    if (stp == true)
      timer.stop();
  }
  // ..................↑コンストラクタ.........................//

  // ..........↓マウスクリック時の操作..............//
  public void mouseReleased(MouseEvent e) {
    Point point = e.getPoint(); // クリック位置を取得
    label.setText("x:" + point.x + ",y:" + point.y); // ラベルにクリック位置を表示
    if (clickmode == 1) {// 1:飛行機選択モード↓↓↓↓
      for (Airplane f : plane) {
        if (f.getX() < point.x && point.x < f.getX() + f.getsize()) {
          if (f.getY() < point.y && point.y < f.getY() + f.getsize()) {
            f.changeflag(); // クリック位置が飛行機と触れているかをチェック、フラグを変更し画像を変更
            clickmode = 2;
            return;
          }
        }
      }
    }
    // 1:飛行機選択モード↑↑↑↑↑↑

    if (clickmode == 2) {// 2:移動方向選択モード↓↓↓↓↓↓
      for (Airplane f : plane) {
        if (f.getflag()) { // モード1で選択されている飛行機かをチェック
          f.updatev(point.x, point.y);// クリックした点の座標を与えて速度を変更
          f.changeflag();
          clickmode = 1;
          return;
        }
      }
    }
    // 2:移動方向選択モード↑↑↑↑↑↑↑
  }
  // ..........↑マウスクリック時の操作..............//

  // ..........↓その他..............//
  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Airplane b = new Airplane();
    block = b.addblock();
    for (int i = 0; i < block.length; i++) {
      int bx = block[i][0];
      int by = block[i][1];
      int bsize = block[i][2];
      g.fillOval(bx, by, bsize, bsize);
    }
    for (Airplane f : plane) {
      f.draw(g);
    }
  }

  public void actionPerformed(ActionEvent e) {
    boolean coll, bcoll;
    boolean stcoll, bstcoll;
    boolean store;
    int block[][];
    int obstacle[][];
    int ix, iy;
    int c = 0;
    Airplane b = new Airplane();
    block = b.addblock();
    obstacle = b.addobstacle();

    Airplane st = new Airplane();
    ix = st.getIX();
    iy = st.getIY();

    for (Airplane f : plane) {
      f.update(sizex, sizey);
      if (f.getY() == iy) {
        f.addplane();
      } else {
        if (f.getY() == iy) {
          f.storeplane();
        }
      }
      // ゴールの判定と呼び出し
      for (int i = 0; i < block.length; i++) {
        f.coll(block, i);
        bcoll = f.getbcoll();
        if (bcoll) {
          label.setText("warning");
          f.storeplane();
          Score.score++;
        } else {
          label.setText(String.valueOf(c));
        }
      }
      // st
      for (int i = 0; i < obstacle.length; i++) {
        f.stcoll(obstacle, i);
        bstcoll = f.getbstcoll();
        if (bstcoll) {
          stp = true;
          f.gotitle();
        } else {
        }

        this.repaint();

      }
    }
  }
}
// ..................↑PlanePanelクラス.........................//

// 背景画像を保持するクラス
class Background extends Component {
  BufferedImage bufferedImage = null;

  Background() {// JPEG読み込みその画像を持つbufferedImageオブジェクト作成
    try {
      bufferedImage = ImageIO.read(new File("back_image.jpg"));// ImageIO.readはbufferedImage型を
    } catch (IOException e) { // 返すメソッド
      System.out.println("image file not found. [" + "back_image.jpg" + "]");
    }
  }

  public void paint(Graphics graphics) {
    graphics.drawImage(bufferedImage, 0, 0, null);// 画像表示
  }

  public Dimension getPreferredSize() {// getPreferredSizeメソッド
    int width = 100;// 画像が読み込めれば画像サイズに、なければ100,100サイズに画面固定
    int height = 100;
    if (bufferedImage != null) {
      width = bufferedImage.getWidth(null);
      height = bufferedImage.getHeight(null);
    }
    return new Dimension(width, height);
  }
}

// 障害物の描画
class PaintImageComponent extends Background {
  int r = 255;
  int g = 0;
  int b = 0;
  int t = 80;

  public void paint(Graphics graphics) {// paintはこのオブジェクトの最初の表示で自動呼び出しされる
    Graphics2D graphics2D1 = bufferedImage.createGraphics();// [202]
    graphics2D1.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    BasicStroke wideStroke = new BasicStroke(2.0f);// [204]
    graphics2D1.setStroke(wideStroke);// [205]
    graphics2D1.fillOval(400, 400, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D2 = bufferedImage.createGraphics();// [202]
    graphics2D2.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D2.setStroke(wideStroke);// [205]
    graphics2D2.fillOval(134, 567, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D3 = bufferedImage.createGraphics();// [202]
    graphics2D3.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D3.setStroke(wideStroke);// [205]
    graphics2D3.fillOval(134, 178, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D4 = bufferedImage.createGraphics();// [202]
    graphics2D4.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D4.setStroke(wideStroke);// [205]
    graphics2D4.fillOval(310, 780, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D5 = bufferedImage.createGraphics();// [202]
    graphics2D5.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D5.setStroke(wideStroke);// [205]
    graphics2D5.fillOval(570, 60, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D6 = bufferedImage.createGraphics();// [202]
    graphics2D6.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D6.setStroke(wideStroke);// [205]
    graphics2D6.fillOval(595, 800, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D7 = bufferedImage.createGraphics();// [202]
    graphics2D7.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D7.setStroke(wideStroke);// [205]
    graphics2D7.fillOval(650, 250, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D8 = bufferedImage.createGraphics();// [202]
    graphics2D8.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D8.setStroke(wideStroke);// [205]
    graphics2D8.fillOval(690, 430, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D9 = bufferedImage.createGraphics();// [202]
    graphics2D9.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D9.setStroke(wideStroke);// [205]
    graphics2D9.fillOval(770, 850, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D10 = bufferedImage.createGraphics();// [202]
    graphics2D10.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D10.setStroke(wideStroke);// [205]
    graphics2D10.fillOval(830, 130, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D11 = bufferedImage.createGraphics();// [202]
    graphics2D11.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D11.setStroke(wideStroke);// [205]
    graphics2D11.fillOval(500, 600, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D12 = bufferedImage.createGraphics();// [202]
    graphics2D12.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D12.setStroke(wideStroke);// [205]
    graphics2D12.fillOval(890, 610, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D13 = bufferedImage.createGraphics();// [202]
    graphics2D13.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D13.setStroke(wideStroke);// [205]
    graphics2D13.fillOval(950, 60, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D14 = bufferedImage.createGraphics();// [202]
    graphics2D14.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D14.setStroke(wideStroke);// [205]
    graphics2D14.fillOval(1000, 220, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D15 = bufferedImage.createGraphics();// [202]
    graphics2D15.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D15.setStroke(wideStroke);// [205]
    graphics2D15.fillOval(1050, 350, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D16 = bufferedImage.createGraphics();// [202]
    graphics2D16.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D16.setStroke(wideStroke);// [205]
    graphics2D16.fillOval(1100, 530, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D17 = bufferedImage.createGraphics();// [202]
    graphics2D17.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D17.setStroke(wideStroke);// [205]
    graphics2D17.fillOval(1150, 770, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D18 = bufferedImage.createGraphics();// [202]
    graphics2D18.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D18.setStroke(wideStroke);// [205]
    graphics2D18.fillOval(1200, 420, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D19 = bufferedImage.createGraphics();// [202]
    graphics2D19.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D19.setStroke(wideStroke);// [205]
    graphics2D19.fillOval(1250, 900, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D20 = bufferedImage.createGraphics();// [202]
    graphics2D20.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D20.setStroke(wideStroke);// [205]
    graphics2D20.fillOval(1300, 610, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D21 = bufferedImage.createGraphics();// [202]
    graphics2D21.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D21.setStroke(wideStroke);// [205]
    graphics2D21.fillOval(1350, 100, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D22 = bufferedImage.createGraphics();// [202]
    graphics2D22.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D22.setStroke(wideStroke);// [205]
    graphics2D22.fillOval(1400, 300, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D23 = bufferedImage.createGraphics();// [202]
    graphics2D23.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D23.setStroke(wideStroke);// [205]
    graphics2D23.fillOval(1450, 870, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D24 = bufferedImage.createGraphics();// [202]
    graphics2D24.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D24.setStroke(wideStroke);// [205]
    graphics2D24.fillOval(1500, 500, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D25 = bufferedImage.createGraphics();// [202]
    graphics2D25.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D25.setStroke(wideStroke);// [205]
    graphics2D25.fillOval(1550, 710, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D26 = bufferedImage.createGraphics();// [202]
    graphics2D26.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D26.setStroke(wideStroke);// [205]
    graphics2D26.fillOval(1600, 230, 50, 50);// (左上x,左上y,幅,高さ)

    Graphics2D graphics2D27 = bufferedImage.createGraphics();// [202]
    graphics2D27.setColor(new Color(r, g, b, t));// [203](r,g,b,濃度)
    graphics2D27.setStroke(wideStroke);// [205]
    graphics2D27.fillOval(100, 830, 50, 50);// (左上x,左上y,幅,高さ)
    graphics.drawImage(bufferedImage, 0, 0, null);// 画像表示
  }
}

// 滑走路の描画
class End extends Component {
  BufferedImage bufferedImage = null;

  End() {// JPEG読み込みその画像を持つbufferedImageオブジェクト作成
    try {
      bufferedImage = ImageIO.read(new File("end.png"));// ImageIO.readはbufferedImage型を
    } catch (IOException e) { // 返すメソッド
      System.out.println("image file not found. [" + "end.png" + "]");
    }
  }

  public void paint(Graphics graphics) {
    graphics.drawImage(bufferedImage, 0, 0, null);
  }
}

// レイヤーの設定
class Pane extends JLayeredPane {
  Background background = null;
  End end = null;
  PlanePanel planepanel = null;
  private final static int plane_size = 40;// 飛行機サイズ

  Pane() {
    setLayout(null);
    background = new PaintImageComponent();
    background.setBounds(0, 0, 1800, 1015);
    add(background);
    setLayer(background, DEFAULT_LAYER, 0);// 第３引数高いほど表示優先度高

    planepanel = new PlanePanel(1800, 1015); // RandomPanelをAirfieldに追加
    planepanel.setSize(new Dimension(1800, 1015));
    add(planepanel);
    setLayer(planepanel, PALETTE_LAYER, 10);// endより優先表示

    end = new End();
    end.setBounds(850, 430, 48, 48);
    add(end);
    setLayer(end, PALETTE_LAYER, 0);
  }
}

class View2 extends JFrame {
  private Pane pane;

  View2() {
    pane = new Pane();
    add(pane);
    setVisible(true);
    setTitle("管制官シミュレーションゲーム");
    setBounds(0, 0, 1800, 1015);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void closeflame() {
    dispose();
  }

  public static void main() { // mainの引数を変更し外部から呼び出し可能にしました
    new View2();
  }
}

// ここから伊藤作成

class Start extends JFrame implements ActionListener { // これを実行すればゲームが開始します
  int s; // 成功数の変数
  int t;

  Start() {
    s = Score.score;
    JPanel a = new JPanel();

    this.add(a);

    this.setLayout(null);
    this.setSize(1800, 1015);
    this.setTitle("ゲーム準備画面");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel title = new JLabel("管制官シュミレーションゲーム");
    title.setFont(new Font("Mairyo", Font.PLAIN, 60));
    title.setBounds(530, 30, 1000, 300);
    this.add(title);

    if (s == 0) {
      t = 0;
    } else {
      t = s;
    }
    JLabel score = new JLabel("今回の着陸成功数　：　" + t + "　機", JLabel.CENTER);
    score.setFont(new Font("Mairyo", Font.PLAIN, 40));
    score.setBounds(600, 200, 600, 300);
    this.add(score);

    JButton button = new JButton("Start");
    button.setFont(new Font("Mairyo", Font.PLAIN, 80));
    button.addActionListener(this);
    button.setBounds(700, 550, 400, 160);
    this.add(button);

    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent ev) {
    View2.main();
    dispose();
  }

  static void reStart() { // 外部から触れるようにmainではない名前で設定しました
    // ゲームのほうのフレームをdispose();してから開くようにしてください
    // Start();を開く前に着陸成功数の値をsに代入すればそれが画面に反映されます
    new Start();

  }

  public static void main(String argv[]) {
    new Start();
  }

}

// View2のクラスの中でゲームが動く見積もりです
