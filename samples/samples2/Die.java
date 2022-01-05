import java.util.Scanner;

public class Die {

  public static final int COMMON_MAX_FACES = 6;
  private int maxFaces;
  private int faceValue;

  public Die(int maxFaces, int faceValue) {
    if (isValid(maxFaces, faceValue)) {
       this.maxFaces = maxFaces;
       this.faceValue = faceValue;
    } else {
       System.out.println("Fatal error");
       System.exit(0);
    }
  }

  public Die(int maxFaces) {
    if (isValid(maxFaces)) {
       this.maxFaces = maxFaces;
       roll();
    } else {
       System.out.println("Fatal error");
       System.exit(0);
    }
  }

  public Die() {
     maxFaces = COMMON_MAX_FACES;
     roll();
  }

  public int getMaxFaces() {
    return maxFaces;
  }

  public boolean setMaxFaces(int maxFaces) {
    if (maxFaces > 0) {
       this.maxFaces = maxFaces;
       return true;
    } else
       return false;
  }

  public int getFaceValue() {
   return faceValue;
  }

  public void roll() {
    faceValue = (int)(Math.random() * maxFaces + 1); 
  }

  public static boolean isValid(int maxFaces, int faceValue) {
    return maxFaces > 0 && faceValue >= 1 && faceValue <= maxFaces;
  }

  public static boolean isValid(int maxFaces) {
    return isValid(maxFaces, 1);
  }

  public String toString() {
     return "Max Faces: " + maxFaces + ", Face Value: " + faceValue;
  }

  public boolean equals(Die other) {
     if (other == null)
        return false;
     else
               // name.equals(other.name) &&
        return maxFaces == other.maxFaces &&
            faceValue == other.faceValue;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter max faces:");
    int maxFaces = input.nextInt();
    Die die1;
    if (Die.isValid(maxFaces)) {
       die1 = new Die(maxFaces);
    } else {
       System.out.println("Invalid value.  Use Common value of 6 instead");
       die1 = new Die();
    }
    System.out.println(die1.toString());
  }
}

