public class Tp2 {

	public static int saisirDegreDifficulte(){
		//String level = "";// degre de difficulte
		int level;
		boolean acces = true;
		
		do{
			System.out.print("Choisissez un degre de difficulte\n 1: facile\n 2: moyen\n 3: difficile\n 4: extreme\n");// saisir le degre de difficulte
		    level = Clavier.lireInt(); // valider le degre de difficulte
		
		  //  if( level.compareTo("facile")==0 || level.compareTo("moyen")==0 || level.compareTo("difficile")==0 || level.compareTo("MOYEN")==0 || level.compareTo("FACILE")==0 || level.compareTo("DIFFICILE")==0){// si l utilisateur veut effectuer une operation
		   //acces = false   ;
		    if(level == 1 || level == 2  || level == 3 || level == 4){
			   acces = false;
			   String niveau = "";
			   
			   switch(level){
			   case 1:
				   niveau = "Facile";
				   break;
			   case 2:
				   niveau = "Moyen";
				   break;
			   case 3:
				   niveau = "Difficile";
				   break;
			   case 4:
				   niveau = "Extreme";
				   break;	
			   }
			   
			   System.out.println("Niveau choisi: " + niveau + "\n");
		   }
		   else{
			   System.out.println("*** choix invalide");// si le choix est invalide
		   }
		
		} while(acces);
	    
	    return level;
	}
	    
	    /*
	   public static String saisirReponseOuiNon(){
	    String reponse = "";
	    boolean acces1 =true ;
	   do{
	    System.out.println("");   
	    
	    reponse = Clavier.lireString();
	
	    if( reponse.compareTo("o")==0 || reponse.compareTo("n")==0 || reponse.compareTo("O")==0 || reponse.compareTo("N")==0 || reponse.compareTo("oui")==0 || reponse.compareTo("non")==0|| reponse.compareTo("OUI")==0 || reponse.compareTo("NON")==0){
	       acces1 = false   ;
	   }else{
	   System.out.println("*** choix invalide");
	    }
	    }while(acces1);
	    
	    return reponse;
	    } 
	  
	    */
	
	
	
	public static void main (String[] params){
		String nom = "";    //nom de l utilisateur 
		int difficulte; // la difficulte de l opertaion   
		//String choix="";
		int operande1;
		int operande2;
		int correction = 0;// les 2 nombres necessaire pour faire les calculs et le resultat obtenu et la correction si l utilisateur echoue apres 3 tentatives
		String resultat = "";
		boolean acces2 = true;
		boolean acces3 = true;
		int i = 0;
		int j = 0; //compteur
		float score = 0;
		char operation = ' ';
		String pourcentage = "";
		
		System.out.println("Ce programme demande a l'utilisateur de saisir son nom, s'il veut faire une operation, choisir le degre de difficulte, et continue de proposer des operations arithmetiques tant que l utilisateur choisi oui,OUI,O ou o.et si l utilisateur choisi non il lui donne le resultat obtenu en fonction des operations faites et du nombre de tentatives faites avant de trouver le bon resultas(3 tentatives max)");
		System.out.print("Veuillez entrer votre nom : ");
		
		nom = Clavier.lireString();
		System.out.println("Bienvenue "+nom+" !");
		difficulte = saisirDegreDifficulte();
		
		do{
			i=0;
			acces2 = true;
			j++;  
			
			JeuArithmetique.choisirDegreDifficulte(difficulte);
		 // choix = saisirReponseOuiNon();
		   
		  //if(choix.compareTo("OUI")==0 || choix.compareTo("O")==0 || choix.compareTo("o")==0 || choix.compareTo("oui")==0 ){
		

		  /*
		  if(difficulte.compareTo("moyen")==0 || difficulte.compareTo("MOYEN")==0)
		  {
		     JeuArithmetique.choisirDegreDifficulte ( JeuArithmetique.MOYEN );
		  }
		  if(difficulte.compareTo("facile")==0 || difficulte.compareTo("FACILE")==0)
		  {
		    JeuArithmetique.choisirDegreDifficulte ( JeuArithmetique.FACILE );
		  }
		  if(difficulte.compareTo("difficile")==0 || difficulte.compareTo("DIFFICILE")==0)
		  {
		    JeuArithmetique.choisirDegreDifficulte ( JeuArithmetique.DIFFICILE );
		  }
		  */
		  
		 
		  
			operande1 = JeuArithmetique.operandeAuHasard ();
			operande2 = JeuArithmetique.operandeAuHasard ();
			operation = JeuArithmetique.operationAuHasard ();
			System.out.println("Inscrire 'fin' dans la reponse d'une equation pour terminer et obtenir votre score.");
			System.out.println(operande1+" "+operation+" "+operande2+" = ?\n" );
		  
		  //System.out.println("");
			do{
				i++;    
				System.out.print("Entrez votre reponse : ");  
				resultat = Clavier.lireString();
		  
				if(resultat.toLowerCase().equals("fin")){
					acces3 = false;
					acces2 = false;
					j--;
				}
				else{
					if ( operation == '+')
						correction = operande1  + operande2 ;
					if ( operation == '*')
						correction = operande1  * operande2 ;
					if ( operation == '-')
						correction = operande1  - operande2 ;
					if ( operation == '/')
						correction = operande1  / operande2 ;
					if ( operation == '%')
						correction = operande1  % operande2 ;
		  
					if(Integer.parseInt(resultat) == correction){
						System.out.println("Bravo "+nom+" ! Bonne reponse !");
						acces2=false; 
					}
					else{ 
						System.out.println("Desole "+nom+", ce n'est pas la bonne reponse.");
					}
					
					if(i==3 && acces2){
						System.out.println(nom+", la reponse est : "+correction);
						acces2 = false;
						i++;
					}	    	  
				}
			} while(acces2);// ajouter la condition de s il trouve la reponse juste avant trois tentative.
			      
			if(acces3)
				score = score + 4 - i; 
			    
		}while(acces3); 
		
		score = (score/3)/j;
		if ( score < 0.45 ) {
			pourcentage = "mediocre";                            //le programme donnera la mention appropriee en fonction du score obtenu
		}else if ( ( score >= 0.45) && ( score < 0.60) ){
			pourcentage = "passable";
		}else if ( ( score >= 0.60) && ( score < 0.75) ){
			pourcentage = "bien";
		}else if ( ( score >= 0.75) && ( score < 0.90) ){
			pourcentage = "tres bien";
		}else if ( score >= 0.90) {
			pourcentage = "excellent";
		}
		
		System.out.println("score = "+((int)Math.ceil(score*100))+" avec la mention: "+pourcentage);
		acces3 = false;
	    } // main
} // Tp2
