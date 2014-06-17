public class Elemento {

	
		private int pos, n;
		
		public Elemento() {
			pos = -1;
			n = -1;
		}
		public Elemento(int p, int l){
			pos = p;
			n = l;

		}
		
		public String toString(){
			return "("+pos+":"+ n + ")";
		}
		
		public int getPos() {
			return pos;
		}
	
		public int getNum() {
			return n;
		}
}