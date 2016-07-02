import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;



public class BreedingTesting extends JFrame implements KeyListener{
	public newANN ann1;
	public newANN ann2;
	public static JFrame frame;
	public JFrame frame1;
	public JFrame frame2;
	
	
	public static void main(String args[]){
		BreedingTesting bt = new BreedingTesting();
		bt.start();
		
	}
	
	public void start(){
		
		ArrayList<Gene> gene1 = new ArrayList<Gene>();
		gene1.add(new Gene(0, 1, 12, -0.6542085566924972, false)); 
		gene1.add(new Gene(0, 1, 10, -0.33722239140323923, false)); 
		gene1.add(new Gene(0, 4, 12, 0.03856183544680172, false)); 
		gene1.add(new Gene(0, 1, 11, -0.5650184434780514, true)); 
		gene1.add(new Gene(0, 7, 9, -0.08933326193904481, false)); 
		gene1.add(new Gene(0, 3, 10, 0.15843289956835266, false)); 
		gene1.add(new Gene(0, 8, 12, 3.331020578879534, true)); 
		gene1.add(new Gene(0, 4, 9, 0.0334889211104754, true)); 
		gene1.add(new Gene(0, 8, 9, 0.017291466435375075, true)); 
		gene1.add(new Gene(0, 2, 9, 0.08950735881269994, true)); 
		gene1.add(new Gene(0, 3, 12, -0.1118757116516113, true)); 
		gene1.add(new Gene(0, 4, 11, 11.011876657825326, false)); 
		gene1.add(new Gene(0, 2, 12, 6.682501383471057, false)); 
		gene1.add(new Gene(0, 3, 9, -0.3643770008884381, false)); 
		gene1.add(new Gene(0, 0, 10, -0.18770040047600295, true)); 
		gene1.add(new Gene(1, 6, 13, 0.020537114929053434, false)); 
		gene1.add(new Gene(2, 13, 12, -10.785558557071793, false)); 
		gene1.add(new Gene(2, 1, 11, -2.338298338519486, true)); 
		gene1.add(new Gene(2, 6, 9, -0.6433987479775484, true)); 
		gene1.add(new Gene(2, 4, 13, -0.5901774195982351, true)); 
		gene1.add(new Gene(2, 7, 12, 0.22176772845245757, true)); 
		gene1.add(new Gene(2, 8, 10, -0.02869647788186494, true)); 
		gene1.add(new Gene(2, 1, 12, 16.584889746065038, false)); 
		gene1.add(new Gene(2, 4, 12, 0.3454174069941712, true)); 
		gene1.add(new Gene(2, 8, 13, -4.058914765612917, true)); 
		gene1.add(new Gene(2, 1, 9, -7.871699410982061, true)); 
		gene1.add(new Gene(3, 4, 14, -0.2653156045350895, true)); 
		gene1.add(new Gene(4, 14, 12, 0.19137610110157757, true)); 
		gene1.add(new Gene(4, 1, 9, 15.893675778166983, true)); 
		gene1.add(new Gene(4, 13, 11, 0.7961713675343333, false)); 
		gene1.add(new Gene(4, 5, 14, 0.5228546811453656, true)); 
		gene1.add(new Gene(4, 1, 14, 0.11573833207916781, true)); 
		gene1.add(new Gene(4, 7, 9, -0.5421691125685266, true)); 
		gene1.add(new Gene(4, 5, 14, -1.0058181012214353, true)); 
		gene1.add(new Gene(4, 7, 12, 0.3114378290225838, false)); 
		gene1.add(new Gene(4, 8, 13, -0.060282572704818446, true)); 
		gene1.add(new Gene(4, 2, 14, 1.7094234964631523, true)); 
		gene1.add(new Gene(4, 8, 11, 1.0963678728739787, false)); 
		gene1.add(new Gene(4, 4, 9, -2.014927753215984, true)); 
		gene1.add(new Gene(4, 7, 10, 7.424511416398898, true)); 
		gene1.add(new Gene(4, 6, 14, 0.7703404115227634, true)); 
		gene1.add(new Gene(5, 8, 15, 1.1102179607556297, false)); 
		gene1.add(new Gene(6, 15, 10, 0.40227422161930027, false)); 
		gene1.add(new Gene(6, 14, 10, -0.6719910462467957, true)); 
		gene1.add(new Gene(6, 2, 10, 0.3419596625357888, true)); 
		gene1.add(new Gene(6, 5, 13, 0.011056444468706133, true)); 
		gene1.add(new Gene(6, 5, 12, -0.03199332945683916, true)); 
		gene1.add(new Gene(6, 14, 12, -1.9721511182503197, false)); 
		gene1.add(new Gene(6, 5, 15, -7.216815490244262, true)); 
		gene1.add(new Gene(7, 4, 16, -0.05908756567468213, true)); 
		gene1.add(new Gene(8, 16, 12, 1.9263283658254355, false)); 
		gene1.add(new Gene(8, 8, 13, -0.817894461131193, false)); 
		gene1.add(new Gene(8, 8, 13, 0.46427468866807226, false)); 
		gene1.add(new Gene(8, 4, 14, -0.009648581431165917, false)); 
		gene1.add(new Gene(8, 13, 12, -0.12889699087406653, false)); 
		gene1.add(new Gene(8, 14, 12, -9.205264780464276, false)); 
		gene1.add(new Gene(9, 8, 17, -12.143767345481468, true)); 
		gene1.add(new Gene(10, 17, 11, -0.3856456829209719, false)); 
		gene1.add(new Gene(10, 5, 12, 2.5909707836569265, true)); 
		gene1.add(new Gene(10, 4, 15, -3.815742007885153, true)); 
		gene1.add(new Gene(10, 8, 12, 55.747218134433794, true)); 
		gene1.add(new Gene(10, 4, 13, 2.346516931458714, true)); 
		gene1.add(new Gene(11, 6, 18, -11.265428113545312, true)); 
		gene1.add(new Gene(12, 18, 14, 2.6087761776032616, true)); 
		gene1.add(new Gene(12, 0, 10, -0.6076316924096412, true)); 
		gene1.add(new Gene(12, 0, 15, -10.961808948909733, true)); 
		gene1.add(new Gene(12, 1, 17, -0.05383762024761791, true)); 
		gene1.add(new Gene(12, 1, 11, 0.4358196742791315, true)); 
		gene1.add(new Gene(12, 15, 12, 8.275116374367256, true)); 
		gene1.add(new Gene(12, 17, 16, 48.58727046896548, true)); 
		gene1.add(new Gene(13, 1, 19, -0.13488337871267841, true)); 
		gene1.add(new Gene(14, 19, 12, 0.09082334590721772, true)); 
		gene1.add(new Gene(14, 8, 18, -3.0744894894894164, true)); 
		gene1.add(new Gene(15, 13, 20, 0.5501462139155087, true)); 
		gene1.add(new Gene(16, 20, 12, -3.932390815154773, true)); 
		gene1.add(new Gene(16, 3, 18, -0.49241121641244584, true)); 
		gene1.add(new Gene(16, 19, 11, -0.018277258567726197, true)); 
		gene1.add(new Gene(16, 4, 16, -0.19287725724212157, true)); 
		gene1.add(new Gene(17, 2, 21, 1.3167287198532294, true)); 
		gene1.add(new Gene(18, 21, 12, 4.776933170219095, true)); 
		gene1.add(new Gene(19, 5, 22, -1.2713287037627927, true)); 
		gene1.add(new Gene(20, 22, 12, 14.707241888657753, true)); 
		gene1.add(new Gene(21, 1, 23, -21.356694214920825, true)); 
		gene1.add(new Gene(22, 23, 14, 8.777791143107674, true)); 
		gene1.add(new Gene(22, 13, 21, 3.460650240485964, false)); 
		gene1.add(new Gene(22, 6, 14, -0.35445327152426354, true)); 
		gene1.add(new Gene(23, 4, 24, 6.855094099411832, true)); 
		gene1.add(new Gene(24, 24, 12, 0.18908848001571518, true)); 
		gene1.add(new Gene(24, 5, 21, -6.736874260771832, false)); 
		gene1.add(new Gene(25, 5, 25, 1.5971012978451506, true)); 
		gene1.add(new Gene(26, 25, 21, 3.4221713595988916, true)); 
		gene1.add(new Gene(26, 21, 16, 1.1024499848124707, true)); 
		gene1.add(new Gene(26, 0, 23, 0.9938864975573729, true)); 
		gene1.add(new Gene(26, 6, 15, 5.684045059662042, true)); 
		gene1.add(new Gene(27, 1, 26, -4.750815218063371, true)); 
		gene1.add(new Gene(28, 26, 19, 2.277374931264691, true)); 
		gene1.add(new Gene(28, 20, 9, 0.11871787263352118, true)); 
		gene1.add(new Gene(29, 24, 27, -2.5931129645032476, true)); 
		gene1.add(new Gene(30, 27, 12, -1.3049616697177386, true)); 
		gene1.add(new Gene(31, 2, 28, 1.3033778971729135, true)); 
		gene1.add(new Gene(32, 28, 10, -8.89466066925769, true)); 
		gene1.add(new Gene(32, 0, 24, -1.297026142269869, true)); 
		gene1.add(new Gene(32, 18, 13, 1.455211340004627, true)); 
		gene1.add(new Gene(32, 19, 23, -1.1380627502622676, true)); 
		gene1.add(new Gene(33, 5, 29, 0.9362346305115636, true)); 
		gene1.add(new Gene(34, 29, 22, -4.3649861683901126, true)); 


//		Genome g1 = new Genome(gene1, 9, 4);
//		
//		ArrayList<Gene> gene2 = new ArrayList<Gene>();
//		gene2.add(new Gene(1,0,3, 1, true));
//		gene2.add(new Gene(2,1,3, 1, false));
//		gene2.add(new Gene(3,2,3, 1, true));
//		gene2.add(new Gene(4,1,4, 1, true));
//		gene2.add(new Gene(5,4,3, 1, false));
//		gene2.add(new Gene(6,4,5, 1, true));
//		gene2.add(new Gene(7,5,3, 1, true));
//		gene2.add(new Gene(9,2,4, 1, true));
//		gene2.add(new Gene(10,0,5, 1, true));
//		
//		Genome g2 = new Genome(gene2, 3, 1);
		
//		ann1 =  new newANN(2,1);
//		ann2 =  new newANN(2,1);
		
//		ann1 =  Genome.buildNewANN(g1);
//		ann2 =  Genome.buildNewANN(g2);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		frame1 = new JFrame("Frame 1");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(640, 480);
		frame1.setVisible(true);
		frame1.add(ann1);

		
		frame2 = new JFrame("Frame 2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(640, 480);
		frame2.setVisible(true);
		frame2.add(ann2);
		
		double[] inputs = {1,0,1};
		ann1.addInputs(inputs);
		System.out.println(ann1.getOutput()[0]);
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent event) {
		switch(event.getKeyCode()){
		case KeyEvent.VK_T:
			Genome g1 = new Genome(ann1);
			Genome g2 = new Genome(ann2);
			
			frame1.remove(ann1);
			ann1 = ann2;
			frame1.add(ann1);
			
			frame2.remove(ann2);
			ann2 = Genome.breedNewAnn(g2, g1);
			frame2.add(ann2);
			
			frame2.revalidate();
			frame1.revalidate();
			frame1.repaint();
			frame2.repaint();
			
			break;
		case KeyEvent.VK_U:
//			ann1.nodes.get(0).input.add((double) 5);
//			ann1.nodes.get(0).input.add((double) 1);
//			ann1.nodes.get(0).input.add((double) 2);
//			ann1.update(ann1.nodes.get(0));
//			ann1.update(ann1.nodes.get(1));
//			ann1.update(ann1.nodes.get(2));
//			System.out.println(ann1.nodes.get(3).finalValue);
			break;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
