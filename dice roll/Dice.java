import java.util.Random;

public class Dice {
	private int sides = 6;
	private final Random random = new Random();
	public Dice(){}
	public Dice(int sides){
		this.sides = sides;
	}
	public int roll(){
		return this.random.nextInt()+1;
	}
	public int getSide(){
		return this.sides;
	}
}
