package greedLS;


/** 
 * Self-defined class to substitute tuples. Very useful
 */
public class Pair<L,R> {			

	  private L left;
	  private R right;

	  public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }
	  
	  public void setLeft(L l){ this.left = l; }
	  
	  public void setRight(R r) { this.right = r;}
	  
	  /*public void Pair(String str){
		  String[] lr = str.split(",");
		  this.left = Integer.parseInt(lr[0]);
		  this.right = Integer.parseInt(lr[1]);
	  }*/
	  
	  @Override
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (!(o instanceof Pair)) return false;
	    @SuppressWarnings("unchecked")
		Pair<L, R> pairo = (Pair<L, R>) o;
	    return this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight());
	  }

	}