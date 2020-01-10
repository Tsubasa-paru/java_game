import java.awt.Rectangle.*;
import java.util.Random;

public class Arrival {//到着判定
private int portX, portY;
private boolean arr, coll;

public boolean getPort(Air air){
        Rectangle rectport = new Rectangle(portX,portY,w,h);
        Point pos = air.getPos();
        Rectangle rectair = new Rectangle(pos.x,pos.y,air.getWidth(),air.getHeight());
        return recport.intersects(rectair);
}
private void ArrivalDecision(){
        for(int i; i<Num_port; i++) {
                for(int j; j<Num_air; j++) {
                        if(air[j].getPort(port[i])) {
                                return arr=true;
                                break;
                        }
                }
        }
}
}


/*public class Collision {
   public boolean collideWith(Shot shot) {
        Rectangle rectPort = new Rectangle(x, y, width, height);
        Point pos = plane.getPos();
        Rectangle rectPlane = new Rectangle(pos.x, pos.y, plane.getWidth(), plane.getHeight());
        return rectPort.intersects(rectPlane);
   }
   private void collisionDetection() {
        for (int i=0; i<NUM_Plane; i++) {
                for (int j=0; j<NUM_Port; j++) {
                        if (Port[i].collideWith(Plane[j])) {
                                Plane[i].Arrival();
                                break;
                        }
                }
        }
   }
   }*/

public class AppearPlane {//出現座標
private int px,py;
public Appear(){
        Random random = new Random();
        px = random.nextInt(1800);
        py = random.nextInt(1015);
}
}
