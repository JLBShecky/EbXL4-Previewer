// Using a single 



import java.util.Random;

public class IterativeLandLayerGenerator extends BaseLandLayerGenerator {
  
  private int octaves = 8;
  private double persistance = 0.76D;
  private double frequency = 2.125D;
  private double detail = 5120D;
  
  private double balance;
  private double [] offsetValues;
  
  private SimplexNoise[] noise;
  
  public IterativeLandLayerGenerator(Random rnd) {
    super(rnd);
    
    // Create the area where water can spawn
    balance = 0D;

    // Create the list of noise octaves
    noise = new SimplexNoise[octaves];
    offsetValues = new double[octaves];
    for(int octave = 0; octave < octaves; octave++) {
      noise[octave] = new SimplexNoise(seed.nextLong());
      offsetValues[octave] = seed.nextDouble();
    }
  }
  
  @Override
  public boolean Generate(double posX, double posY) {
    // Starting constants
    double pow = 1D;
    double val = 0D;
    double detail1 = detail;
    
    // Calculate the level
    for(int octave = 0; octave < octaves; octave++) {
      double offset = (octave % 2 == 0) ? offsetValues[octave] : -offsetValues[octave];
      val += noise[octave].noise(((posX) / detail1) + offset, ((posY) / detail1) - offset) * pow;
      detail1 /= frequency;
      pow *= persistance;
    }
    
    if(val < balance) {
        return false;
    } else {
      return true;
    }
  }

}
