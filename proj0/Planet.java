public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	//Planet构造函数
	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet P) {
	}

	//helpers
	public double calcDistance(Planet P) {
		double dx = this.xxPos - P.xxPos;
		double dy = this.yyPos - P.yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Planet P) {
		double G = 6.67e-11; 
		double F = (G * this.mass * P.mass) / (this.calcDistance(P) * this.calcDistance(P));
		return F;
	}

	public double calcForceExertedByX(Planet P) {
		double Fx = this.calcForceExertedBy(P) * (P.xxPos - this.xxPos) / this.calcDistance(P);
		return Fx;
	}

	public double calcForceExertedByY(Planet P) {
		double Fy = this.calcForceExertedBy(P) * (P.yyPos - this.yyPos) / this.calcDistance(P);
		return Fy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double FNetx = 0;
		for (int i = 0; i < allPlanets.length; i ++) {
			if (!this.equals(allPlanets[i])) {
				FNetx = FNetx + this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return FNetx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double FNety = 0;
		for (int i = 0; i < allPlanets.length; i ++) {
			if (!this.equals(allPlanets[i])) {
				FNety = FNety + this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return FNety;
	}

	public boolean equals(Planet P) {
		if (this == P) {
			return true;
		}
		return false;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + aX * dt;
		this.yyVel = this.yyVel + aY * dt;
		this.xxPos = this.xxPos + xxVel * dt;
		this.yyPos = this.yyPos + yyVel * dt;
	}

	public void draw() {
		String drawPic = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, drawPic);
		//StdDraw.show();
		// StdDraw.pause(2000);//这里需要延时吗???
	}
}
