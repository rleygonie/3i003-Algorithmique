
public class TestGloutonCompatible {
	
	public static void main (String args[]) {
		int s = 43;
		int k = 5;
		int[] vS = new int[k];
		vS[0] = 1;
		
		int Pmax = 20;
		int cpt = 1;
		int[] v = new int[k];
		
		v = aleatoireEtcroissante(Pmax, cpt, k, vS);
		
		//afficher le tableau v en aleatoire et croissante
		//et tester si il est compatible ou pas
		System.out.println("le tableau v: ");
		for(int i=0; i<k; i++) {
			System.out.print(v[i] + "\t");
		}
		
		System.out.println();
		
		if(!TestGloutonCompatible(k, v)) {
			System.out.println("Non compatible");
		}
		else {
			System.out.println("compatible");
		}
		System.out.println();
		
		
		//calculer la proportion sur 1000 exemples
		boolean result = true;
		int nbTrue = 0;
		int nbFalse = 0;
		
		for(int i=0; i<1000; i++) {
			v = aleatoireEtcroissante(Pmax, cpt, k, vS);
			result = TestGloutonCompatible(k,v);
			if(result == false) {
				nbFalse++;
			}
			else {
				nbTrue++;
			}
		}
		double proportion = (double)nbTrue/(nbTrue+nbFalse);
		
		System.out.println("la proportion sur 1000 exemples: " + proportion*100 + "%");
		
		
		//calculer l'ecart moyen et l'ecart pire en Pmax = 20, f = 500
		int f=500;
		int[] tab = new int[Pmax*f-Pmax];
		int Algo2;
		int Algo3;
		
		for(int i=0; i<Pmax*f-Pmax; i++) {
			Algo2 = AlgoDyn(v,i,k);
			Algo3 = Glouton(i,v,k);
			tab[i] = Math.abs(Algo2-Algo3);
		}
		
		int pire = 0;
		int moy = 0;
		
		for(int i=0; i<Pmax*f-Pmax; i++) {
			moy = moy+tab[i];
			if(tab[i]>pire) {
				pire = tab[i];
			}
		}
		
		moy = moy/(Pmax*f - Pmax);
		
		System.out.println();
		System.out.println("l'ecart moyen: " + moy);
		System.out.println("l'ecart pire: " + pire);
		
		
	}
	
	public static boolean TestGloutonCompatible(int k, int[] v) {
		//int s = 0;
		//int j = 0;
		int s=v[2]+2;
		if(k >= 3) {
			int r = v[k-2]+v[k-1]-1;
			while(s <= r) {
				for(int j=0; j<k; j++) {
					if(v[j]<s && Glouton(s, v, k) > 1+Glouton(s-v[j], v, k)) {
						return false;
					}
				}
				s++;
			}
		}
		return true;
	}
	
	//retourne nombre total
	public static int Glouton(int s, int[] v, int k){
    	int nbt = 0;
    	int n = 0;
    	
    	for(int i=k-1; i>=0; i--) {
    		n = s/v[i];
    		s = s - n*v[i];
    		nbt += n;
    	}
    	
        return nbt;
    }
    
	//retourne le tableau ce qu'on utilise
    public static int[] Glouton2(int s, int[] v, int k, int nb[]){

    	int n = 0;
    	
    	for(int i=k-1; i>=0; i--) {
    		n = s/v[i];
    		s = s - n*v[i];
    		nb[i] = n;
    	}
    	
        return nb;
    }
    
    public static int AlgoDyn(int[] v, int s, int k) {
        
		int cptV = 0;
        int[][] m = new int[s+1][k+1];
        
        //inisialiser
        for (int i = 1; i < s+1; i++) {
			m[i][0] = Integer.MAX_VALUE;// infini positif
		}
        
        //afficher(m,s+1,k+1);
        //System.out.println();
        
        for (int i = 1; i < s+1; i++) {
			for (int j = 1; j < k+1; j++) {
				
				cptV = j - 1;
				
				if(i >= v[cptV]){
					m[i][j] = min(m[i][j-1], m[i - v[cptV]][j] + 1);//on peut utiliser la plus grand bocal
				}
				else {
					m[i][j] = min(m[i][j-1], Integer.MAX_VALUE);//on ne peut pas utiliser la plus grand bocal
				}
				
				//afficher(m,s+1,k+1);
				//System.out.println();
			}
		}
        return m[s][k];
    }
	
	public static int min(int x, int y) {
		if(x >= y) {
			return y;
		}
		else {
			return x;
		}
	}
	
	
	public static int[] aleatoireEtcroissante(int Pmax, int cpt, int k, int[] v) {
		//aleatoire et pas de redouble
		
		while(cpt < k) {
			int num = 2 + (int)(Math.random()*(Pmax-2));
			boolean ok = true;
			for(int j=0; j<cpt; j++) {
				if(num == v[j]) {
					ok = false;
					break;
				}
			}
			if(ok) {
				v[cpt] = num;
				cpt++;
			}
		}
		
		
		//ordre croissante
		for(int i=0; i<v.length-1; i++){
			for(int j=i+1; j<v.length; j++){
				if(v[i]>v[j]){
					int temp = v[i];
					v[i]=v[j];
					v[j] = temp;
				}
			}
		}
		
		return v;
	}
	
}


