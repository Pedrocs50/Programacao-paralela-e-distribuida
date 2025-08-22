public class Main{
	public static void main(
		String ...args){
		Dice dice = new Dice(6);
		for(int i=0; i<10; i++){
			System.out.println(dice.roll());
		}
	}
}