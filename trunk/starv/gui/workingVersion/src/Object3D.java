import java.util.Random;


public class Object3D {

	public String indentifier;
	public Object3D()
	{
		indentifier = nameGenerator();
	}
	private String nameGenerator()
	{
		String name ="";
		Random generator = new Random();
		int length = generator.nextInt(20);
		for(int blah = 0;blah <= length;blah++)
		{
			int temp = generator.nextInt(26);
			switch(temp){
				case 0:
					name+= "z";
					break;
				case 1:
					name += "a";
					break;
				case 2:
					name += "b";
					break;
				case 3:
					name += "c";
					break;
				case 4:
					name += "d";
					break;
				case 5:
					name += "e";
					break;
				case 6:
					name += "f";
					break;
				case 7:
					name += "g";
					break;
				case 8:
					name += "h";
					break;
				case 9:
					name += "i";
					break;
				case 10:
					name += "j";
					break;
				case 11:
					name += "k";
					break;
				case 12:
					name += "l";
					break;
				case 13:
					name += "m";
					break;
				case 14:
					name += "n";
					break;
				case 15:
					name += "o";
					break;
				case 16:
					name += "p";
					break;
				case 17:
					name += "q";
					break;
				case 18:
					name += "r";
					break;
				case 19:
					name += "s";
					break;
				case 20:
					name += "t";
					break;
				case 21:
					name += "u";
					break;
				case 22:
					name += "v";
					break;
				case 23:
					name += "w";
					break;
				case 24:
					name += "x";
					break;
				case 25:
					name += "y";
					break;
					
			}//end switch
			name += length;
	}
		return name;
	}//end name generator
	public String getIdentifier()
	{
		return indentifier;
	}
}
