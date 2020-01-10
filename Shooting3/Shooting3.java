///////////////////////////////////////////////////////////////////////////////
//	シューティングゲーム３　敵登場・仮想画面
//	ファイル名 : Shooting3.java
//	アプレットサイズ 300×400
//	使用画像：
//	jiki.gif（自機画像上向き、32×32ピクセル）
//  tama.gif（弾画像、8×8ピクセル）
//	teki.gif（敵画像下向き、64×64ピクセル）

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Shooting3 extends Applet implements Runnable, KeyListener {
Image jiki;     // 自機画像
int jx, jy;    // 自機の座標
Image tama;     // 弾画像
int tx, ty;    // 弾の座標
boolean tflag;    // 弾の発射フラグ
Image teki;     // 敵画像
int ex, ey;    // 敵の座標
boolean keyLeft, keyRight, keySpace;  // キー押下フラグ
Thread gameThread;   // ゲームスレッド
Image offImage;    // 仮想画面

// 初期化
public void init() {
								// 自機の初期座標を設定する
								jx = 134; jy = 352;
								// 敵の初期座標を設定する
								ex = (int)(Math.random() * (300 - 32));
								ey = -32;
								// 弾フラグをクリアする
								tflag = false;
								// キーフラグをクリア
								keyLeft = keyRight = keySpace = false;
								// 背景色を黒色に設定
								setBackground(Color.black);
								// 前景色を白色に設定
								setForeground(Color.white);
								// 画像読み込み
								jiki = getImage(getDocumentBase(), "jiki.gif");
								tama = getImage(getDocumentBase(), "tama.gif");
								teki = getImage(getDocumentBase(), "teki.gif");
								// 仮想画面の生成
								offImage = createImage(300, 400);
								// キー入力の受け付け開始
								addKeyListener(this);
								// フォーカスを要求
								requestFocus();
}

// ゲームスレッドの開始
public void start() {
								if(gameThread == null) {
																gameThread = new Thread(this);
																gameThread.start();
								}
}

// ゲームスレッドの停止
public void stop() {
								gameThread = null;
}

// ゲームスレッドのメイン
public void run() {
								while (gameThread == Thread.currentThread()) {
																try {
																								Thread.sleep(20);
																} catch (InterruptedException e) {
																								break;
																}
																// ゲームメイン処理
																// 左移動
																if (keyLeft) {
																								jx -= 8;
																								if (jx < 0) jx = 0;
																}
																// 右移動
																if (keyRight) {
																								jx += 8;
																								if (jx > 300 - 32) jx = 300 - 32;
																}
																// 弾発射
																if (keySpace) {
																								if (!tflag) {
																																tx = jx + 12;
																																ty = jy;
																																tflag = true;
																								}
																}
																// 弾消滅
																if (tflag) {
																								if (ty < 0) tflag = false;
																								else ty -= 8;
																}
																// 敵移動
																ey += 8;
																if (ey >= 400) {
																								ex = (int)(Math.random() * (300 - 32));
																								ey = -32;
																}
																repaint();
								}
}

// キーが押されたときの処理
public void keyPressed(KeyEvent e) {
								switch (e.getKeyCode()) {
								// 左キーが押されたとき
								case KeyEvent.VK_LEFT: keyLeft = true; break;
								// 右キーが押されたとき
								case KeyEvent.VK_RIGHT: keyRight = true; break;
								// スペースキーが押されたとき
								case KeyEvent.VK_SPACE: keySpace = true; break;
								}
}

// キーが離されたときの処理
public void keyReleased(KeyEvent e) {
								switch (e.getKeyCode()) {
								// 左キーが離されたとき
								case KeyEvent.VK_LEFT: keyLeft = false; break;
								// 右キーが離されたとき
								case KeyEvent.VK_RIGHT: keyRight = false; break;
								// スペースキーが離されたとき
								case KeyEvent.VK_SPACE: keySpace = false; break;
								}
}

// キーがタイプされたときの処理
public void keyTyped(KeyEvent e) {
}

// 描画
public void paint(Graphics g) {
								Graphics gv = offImage.getGraphics();
								// 裏画面の消去
								gv.clearRect(0, 0, 300, 400);
								// 弾が出現しているときは弾の描画
								if (tflag) gv.drawImage(tama, tx, ty, this);
								// 自機の描画
								gv.drawImage(jiki, jx, jy, this);
								// 敵の描画
								gv.drawImage(teki, ex, ey, this);
								// 裏画面から表画面へ転送
								g.drawImage(offImage, 0, 0, this);
}

// 更新
public void update(Graphics g) {
								paint(g);
}
}
