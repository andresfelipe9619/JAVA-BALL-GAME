
import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

public class cancha extends Thread implements Serializable {

    private Balloon[] balones;
    private int rebotesTotal;
    public final static int MAX_BALONES=6;
    public cancha() {
        balones = new Balloon[MAX_BALONES];
        balones[0] = new Balloon(Color.BLACK, 50, 50);
        balones[1] = new Balloon(Color.GREEN, 80, 80);
        balones[2] = new Balloon(Color.BLUE,  70, 70);
        balones[3] = new Balloon(Color.ORANGE, 50, 50);
        balones[4] = new Balloon(Color.RED, 60, 60);
        balones[5] = new Balloon(Color.YELLOW, 80, 80);
    }
    public boolean llego(){
		boolean ll=false;
		int llego=0;
		for (int i = 0; i < balones.length; i++) {
			if(balones[i].isAtrapado()==true){
				llego++;
				
			}
			if(llego==MAX_BALONES){
				ll=true;
			}
		}
		return ll;
	}
    
    public int totalRebotes(){
    	for(int i=0;i<MAX_BALONES;i++){
    		
    		rebotesTotal+=balones[i].getRebotes();
    		
    	}
    	
    	return rebotesTotal;
    }
    public void run(){
    	for (int i = 0; i < MAX_BALONES; i++) {
			balones[i].start();
		}
    	
    }

    public Balloon[] getBalones() {
        return balones;
    }

    public void setBalones(Balloon[] balones) {
        this.balones = balones;
    }
    
    

}
