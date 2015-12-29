package Solver;

import java.util.Scanner;

public class Solve {
	public int N;
	public double X;
	public double Y;
	public double[] x;
	public double[] y;
	public double[] radiuses;
	public double fx;
	public double fy;
	public double radius;
	
	public double Distance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
	}
	
	public boolean Check(double x1, double y1, double x2, double y2)
	{
		boolean result;
		if(Math.abs(x1 - x2) < 0.0001 && Math.abs(y1 - y2) < 0.00001)
		{
			fx = (x1 + x2)/2.0;
			fy = (y1 + y2)/2.0;
			
			return false;
		}
		for(int i = 0; i < N; i++)
		{
			if((Distance(x1, y1, x[i], y[i]) <= radiuses[i] + radius) &&
					(Distance(x1, y2, x[i], y[i]) <= radiuses[i] + radius) &&
					(Distance(x2, y1, x[i], y[i]) <= radiuses[i] + radius) &&
					(Distance(x2, y2, x[i], y[i]) <= radiuses[i] + radius))
			{
				return true;
			}
		}
		result = Check(x1, y1, (x1+x2)/2.0, (y1 + y2)/2.0) && 
				Check((x1 + x2)/2.0, y1, x2, (y1+y2)/2.0) &&
				Check(x1, (y1 + y2)/2.0, (x1+x2)/2.0, y2) &&
				Check((x1 + x2)/2.0, (y1+y2)/2.0, x2, y2);
		return result;
	}

	public static void main(String[] args) {
		Solve solver = new Solve();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		double X, Y;
		int N;
		solver.X = scan.nextDouble();
		solver.Y = scan.nextDouble();
		solver.N = scan.nextInt();
		N = solver.N;
		X = solver.X;
		Y = solver.Y;
		solver.x = new double[N];
		solver.y = new double[N];
		solver.radiuses = new double[N];
		for(int i = 0; i < N; i++)
		{
			solver.x[i] = scan.nextDouble();
			solver.y[i] = scan.nextDouble();
			solver.radiuses[i] = scan.nextDouble();
		}
		
		double left_border = 0.0;
		double right_border = (X < Y ? X/2.0 : Y/2.0);
		while(Math.abs(left_border - right_border) > 0.00001)
		{
			solver.radius = (left_border + right_border)/2.0;
			if(solver.Check(solver.radius, solver.radius, X - solver.radius, Y - solver.radius))
			{
				right_border = solver.radius;
			}
			else left_border = solver.radius;
			
		}
		
		System.out.format("%.5f %.5f %.5f", solver.fx, solver.fy, solver.radius);

	}

}
