package stone;

public class StoneException extends RuntimeException {
   public StoneException(String m){
      super(m);
   }
//   public stone.StoneException(String m, ASTree t) {
//      super(m + " " + t.location());
//   }
}
