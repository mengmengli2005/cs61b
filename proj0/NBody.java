public class NBody {

	//方法们
	public static double readRadius(String F) {
		In in = new In(F);
		int planetsNum = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String F) {
		Planet[] allPlanets = new Planet[5];
		In in = new In(F);
		int planetsNum = in.readInt();
		double radius = in.readDouble();
		for (int i = 0; i < 5; i ++) {
			allPlanets[i] = new Planet(allPlanets[i]); //为什么要把allPlanets[i]加进后面的括号里？PS 不加则显示“找不到合适的构造器”
			allPlanets[i].xxPos = in.readDouble();
			allPlanets[i].yyPos = in.readDouble();
			allPlanets[i].xxVel = in.readDouble();
			allPlanets[i].yyVel = in.readDouble();
			allPlanets[i].mass = in.readDouble();
			allPlanets[i].imgFileName = in.readString();
		}
		return allPlanets;
	}

	//main函数
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] allPlanets = NBody.readPlanets(filename);

		//Draw the universe
		StdDraw.setScale(-radius, radius);
		// StdDraw.clear();
		// StdDraw.picture(0, 0, "images/starfield.jpg");
		// StdDraw.show();
		// // StdDraw.pause(2000);

		// //Draw all the planets
		// for (int i = 0; i < allPlanets.length; i++) {
		// 	allPlanets[i].draw();
		// }

		//move our planets
		StdDraw.enableDoubleBuffering(); //buffering
		double t = 0.0;
		while(t < T) {
			StdDraw.clear();
			double[] xForce = new double[5];
			double[] yForce = new double[5];
			for (int i = 0; i < allPlanets.length; i++) {
				xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].update(dt, xForce[i], yForce[i]);
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t + dt; 
		}

		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}
}