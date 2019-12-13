import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import static java.lang.Math.*;

//.....↓Airplaneクラス(飛行機が持つの性質) ......//
class Airplane {
  protected int x,y,vx,vy,ex,ey,size;             //ex,eyは画像の中心座標 x,yは左上の座標 sizeは大きさ
  protected double kakudo;                        //進行方向に傾けるための角度
  protected boolean flag;                         //クリックモード1で選択されたかを判定する変数
  Airplane() {
    x = 10;                                       //初期座標と初期速度を設定
    y = (int)(Math.random()*200);
    vx = (int)(Math.random()*5);
    vy = (int)(Math.random()*5);
    kakudo = Math.atan(-1*(float)vy/(float)vx);   //最初の進行方向に応じた角度を設定
    if(vx<0){kakudo = PI + kakudo; }
    size=(int)(Math.random()*30+40);              //大きさもランダム
    ex = x+size/2;                                //中心座標も保存(画像の回転に使用するため)
    ey = y+size/2;
    flag = false;                                 //最初は選択されていないのでflaseにする
  }

  void update(int s) {                            //PlanePanelのactionPerformed内で0.1秒ごとに呼び出し。座標を更新。
    if(Math.random()<0.5){x += vx; y += vy;}      //カクカク動くようにランダムで更新
    if(x<0 || x+size>s){vx *= -1;}                //画面端で跳ね返る
    if(y<0 || y+size>s){vy *= -1;}
    ex = x+size/2;                                //中心座標も更新
    ey = y+size/2;
    kakudo = Math.atan(-1*(float)vy/(float)vx);   //速度から角度を変更
    if(vx<0){kakudo = PI + kakudo; }
  }

  void updatev(int a,int b){                      //(2:移動方向選択モード)でクリックした座標に向かうように速度を変更する関数
    vx = (int)(4*(a-ex)/Math.sqrt((a-ex)*(a-ex)+(b-ey)*(b-ey)));
    vy = (int)(4*(b-ey)/Math.sqrt((a-ex)*(a-ex)+(b-ey)*(b-ey)));
    kakudo = Math.atan(-1*(float)vy/(float)vx);   //速度から角度を変更
    if(vx<0){kakudo = PI + kakudo; }
  }
  void draw(Graphics g) {}
  int getX(){return x;}
  int getY(){return y;}
  int getsize(){return size;}
  boolean getflag(){ return flag;}
  void changeflag(){flag = !flag;}
}

class A extends Airplane {           //Aタイプの飛行機
  Image img1,img1x;
  ImageIcon i1,ix1;
  A(){
  i1 = new ImageIcon( "a.png" );     //画像読み込み
  ix1= new ImageIcon( "ax.png" );
  img1 = i1.getImage();
  img1x = ix1.getImage();
}

  void draw(Graphics g) {            //進行方向に回転させて描画
    double rotatekakudo = 0.5*PI - kakudo;
    Graphics2D g2d = (Graphics2D)g;
    if(flag){
      g2d.rotate(rotatekakudo,ex,ey);
      g2d.drawImage(img1x,x,y,size,size,null);
      g2d.rotate(-rotatekakudo,ex,ey);
    }
    else{
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
    if(flag){
      g2d.rotate(rotatekakudo,ex,ey);
      g2d.drawImage(img2x,x,y,size,size,null);
      g2d.rotate(-rotatekakudo,ex,ey);
    }
    else{
      g2d.rotate(rotatekakudo,ex,ey);
      g2d.drawImage(img2,x,y,size,size, null);
      g2d.rotate(-rotatekakudo,ex,ey);
    }
  }
}



//..................↓PlanePanelクラス(飛行機などの描画を処理) .........................//
class PlanePanel extends JPanel implements ActionListener,MouseListener{
  private Timer timer;                //0.1秒毎に更新
  private final static int NUM=2;     //各飛行機の数
  private ArrayList<Airplane> plane;  //全飛行機を格納するArrayList
  private JLabel label;               //クリック座標を表示するJLabel
  private int clickmode;              //クリックモードを判断する変数 (1:飛行機選択モード 2:移動方向選択モード)
  private int size;                   //画面サイズ

 //..................↓コンストラクタ.........................//
  PlanePanel(int s){                 //sに以下、Airfieldのサイズを渡す
    plane=new ArrayList<Airplane>();  //ArraListを初期化して、指定した数、飛行機を追加していく。
    for(int i=0;i<NUM;i++){
      plane.add(new B());
      plane.add(new A());
    }
    size = s;                                       //画面の大きさを保存
    this.addMouseListener(this);                    //マウスリスナーに登録
    this.setBackground(Color.white);                //背景を白
    clickmode = 1;                                  //クリックモードを(1:飛行機選択モード)にする
    label =  new JLabel("x:?,y:?",JLabel.CENTER);   //座標を表示させるラベルを追加
    label.setLayout(new GridLayout(2,1));           //レイアウトを2,1にする
    this.add(label);                                //パネルにラベルを追加する

    timer = new Timer(100, this);                   // 0.1秒毎にactionPerformedを呼び出し
    timer.start();                                  // タイマーをスタート
  }
  //..................↑コンストラクタ.........................//


  //..........↓マウスクリック時の操作..............//

  public void mouseReleased(MouseEvent e){
    Point point = e.getPoint();                       //クリック位置を取得
    label.setText("x:" + point.x + ",y:" + point.y);  //ラベルにクリック位置を表示

    if(clickmode==1){//1:飛行機選択モード↓↓↓↓
    for(Airplane f: plane){
      if( f.getX() < point.x &&  point.x < f.getX()+f.getsize() ){
      if( f.getY() < point.y &&  point.y < f.getY()+f.getsize() ){
         f.changeflag(); //クリック位置が飛行機と触れているかをチェック、フラグを変更し画像を変更
         clickmode = 2;
         return;
    }}}}
     //1:飛行機選択モード↑↑↑↑↑↑

    if(clickmode==2){//2:移動方向選択モード↓↓↓↓↓↓
      for(Airplane f: plane){
        if(f.getflag()){ //モード1で選択されている飛行機かをチェック
          f.updatev(point.x,point.y);//クリックした点の座標を与えて速度を変更
          f.changeflag();
          clickmode = 1;
          return;
     }}}
    //2:移動方向選択モード↑↑↑↑↑↑↑
  }
  //..........↑マウスクリック時の操作..............//

  //..........↓その他..............//
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for(Airplane f: plane){
        f.draw(g);
    }}
  public void actionPerformed(ActionEvent e){
      for(Airplane f: plane){
        f.update(size);
      }
      this.repaint();
    }
}
//..................↑PlanePanelクラス.........................//



class Airfield extends JFrame {
    private int size;                                       //画面のサイズ
    public Airfield(){
      size = 400;
      this.setTitle("Random Frame");
      this.setSize(size,size);
      this.add(new PlanePanel(size));                      //RandomPnelをAirfieldに追加
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new Airfield();
   }
}
